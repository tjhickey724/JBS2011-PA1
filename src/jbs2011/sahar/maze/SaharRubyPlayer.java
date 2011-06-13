package jbs2011.sahar.maze;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.Direction;

public class SaharRubyPlayer extends MazePlayer {

	private int jewelModifier;
	private int rubyReward;
	private int playerPenalty;
	private int wallPenalty;
	private int stayOnBoardDetterent;
	
	
	public SaharRubyPlayer(String n) {
		super(n);
		jewelModifier = 8;
		rubyReward = 6;
		playerPenalty = -3;
		wallPenalty = -2;
		stayOnBoardDetterent = -10;
	} 
	
	public SaharRubyPlayer(String n, int j, int r, int p, int w) {
		super(n);
		jewelModifier = j;
		rubyReward = r;
		playerPenalty = p;
		wallPenalty = w;
		stayOnBoardDetterent = -10;
	}

	public SaharRubyPlayer(String n, int j, int r, int p, int w, int s) {
		super(n);
		jewelModifier = j;
		rubyReward = r;
		playerPenalty = p;
		wallPenalty = w;
		stayOnBoardDetterent = s;
	}
	
	@Override
	//5-inputs. Now, with JEWELS!
	public Direction nextMove(HashMap<String, MazePosition> players, 
		ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
		//Algorithm:
		//Look at each 4 directions
		//For each direction:
		//Give a weight (positive) to: closest jewel, is there a ruby
		//Give a weight (negative) to: number of walls, is there another player
		
		MazePosition here = players.get(super.name);
		//(name, "is at row " + here.row + " , column " + here.col);
		
		int n = getWeightNorth(here, players, jewels, rubies, maze);
		int e = getWeightEast(here, players, jewels, rubies, maze);
		int s = getWeightSouth(here, players, jewels, rubies, maze);
		int w = getWeightWest(here, players, jewels, rubies, maze);
		
		//Highest weight =?
		
		int h1 = (n > e) ? n : e;
		int h2 = (s > w) ? s: w;
		int h = (h1 > h2) ? h1 : h2;
		
		if (n >= h) { //Log.d(this.name, "Go North for " + n); 
		return Direction.NORTH;}
		if (s >= h) { //Log.d(this.name, "Go south for " + s);
		return Direction.SOUTH;}
		if (e >= h) { //Log.d(this.name, "Go east for " + e); 
		return Direction.EAST;}
		if (w >= h) { //Log.d(this.name, "Go west for " + w); 
		return Direction.WEST;}
		
		Log.d(this.name, "Staying put");
		return Direction.CENTER;
		
		
	}

	//to go North, you add X.
	//to go EAst, you add y.
	
	//for each jewel in the same direction, add 8-(distance away) weight (min 1)
	//for each ruby in the same direction, add 6 weight
	//for each player in the same direction subtract 3
	//for each wall, subtract 2
	
	private int getWeightEast(MazePosition p, HashMap<String, MazePosition> players, 
		ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
		
		boolean firstJewel = false;
		int weight = 0;
		int a = p.row;
		int b = a + 1;
		MazePosition n;
		
		if (b == maze.getDepth()){
			//on the edge of the map
			//Log.d(this.name, " is on the edge of the map. Let's not go further east." );
			return stayOnBoardDetterent;
		}
		
		while(b<maze.getDepth()){
			
			n = new MazePosition(b, p.col);
			if (!firstJewel) {weight += ( maze.canMove(n, Direction.WEST) ? 0 : wallPenalty); }
			//yes, west. Essentially, you are asking "did I just walk through a wall?"
			//Also, notice you only get penalized for walls BEFORE you hit your first jewel
			
			weight += (players.containsValue(n) ? playerPenalty : 0);
			weight += (rubies.contains(n)? rubyReward: 0);
			int jewelPrize = Math.max(jewelModifier - (b - a) , 1);
			if (jewels.contains(n)) firstJewel = true;
			weight += ( jewels.contains(n) ? jewelPrize: 0  );
			
			b++;
		}
		return weight;	
	}
	
	
	private int getWeightWest(MazePosition p, HashMap<String, MazePosition> players, 
		ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
			
		int weight = 0;
		int a = p.row;
		int b = a - 1;
		MazePosition n;
		boolean firstJewel = false;
		
		if (a == 0){
			//on the edge of the map
			//Log.d(this.name, " is on the edge of the map. Let's not go further west." );
			return stayOnBoardDetterent;
		}
		
		while(b>0){
			n = new MazePosition(b, p.col);
			if (!firstJewel) {weight += ( maze.canMove(n, Direction.EAST) ? 0 : wallPenalty);}
			weight += (players.containsValue(n) ? playerPenalty : 0);
			weight += (rubies.contains(n)? rubyReward: 0);
			if (jewels.contains(n)) firstJewel = true;
			int jewelPrize = Math.max(jewelModifier - (a - b) , 1);
			weight += ( jewels.contains(n) ? jewelPrize: 0  );
			b--;
		}
		return weight;	
	}
	
	
	private int getWeightNorth(MazePosition p, HashMap<String, MazePosition> players, 
			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
			
		int weight = 0;
		int a = p.col;
		int b = a + 1;
		MazePosition n;
		boolean firstJewel = false;
		
		if (b == maze.getWidth()){
			//on the edge of the map
			//Log.d(this.name, " is on the edge of the map. Let's not go further north." );
			return stayOnBoardDetterent;
		}
		
		while(b<maze.getWidth()){
			n = new MazePosition(p.row , b);
			if (!firstJewel) {weight += ( maze.canMove(n, Direction.NORTH) ? 0 : wallPenalty);}
			weight += (players.containsValue(n) ? playerPenalty : 0);
			weight += (rubies.contains(n)? rubyReward: 0);
			if (jewels.contains(n)) firstJewel = true;
			int jewelPrize = Math.max(jewelModifier - (b - a) , 1);
			weight += ( jewels.contains(n) ? jewelPrize: 0  );
			b++;
		}
		return weight;	
	}
	
	private int getWeightSouth(MazePosition p, HashMap<String, MazePosition> players, 
			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
			
		int weight = 0;
		int a = p.col;
		int b = a - 1;
		MazePosition n;
		boolean firstJewel = false;
		
		if (a == 0){
			//on the edge of the map
			//Log.d(this.name, " is on the edge of the map. Let's not go more south." );
			return stayOnBoardDetterent;
		}
		
		
		while(b>0){
			n = new MazePosition(p.row , b);
			if (!firstJewel) {weight += ( maze.canMove(n, Direction.SOUTH) ? 0 : wallPenalty);}
			weight += (players.containsValue(n) ? playerPenalty : 0);
			weight += (rubies.contains(n)? rubyReward: 0);
			if (jewels.contains(n)) firstJewel = true;
			int jewelPrize = Math.max(jewelModifier - (a - b) , 1);
			weight += ( jewels.contains(n) ? jewelPrize: 0  );
			b--;
		}
		return weight;	
	}	
	
	
}
