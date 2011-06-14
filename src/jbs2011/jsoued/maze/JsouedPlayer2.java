package jbs2011.jsoued.maze;


import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;


//This player looks for the nearest jewel without taking into account the walls on its way. Then it moves
//towards that jewel trying to pass through walls as many times as needed.
public class JsouedPlayer2 extends MazePlayer {
	
	private String n;

	public static void main(String[] args){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer("jsoued1"));
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer2("jsoued2"));
		  players.add(new jbs2011.tjhickey.maze.RandomPlayer("Rand"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tjhickey"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}
	
	public JsouedPlayer2(String n) {
		super(n);
		this.n=n;
	}
	
	public Direction nextMove(HashMap<String,MazePosition> players, ArrayList<MazePosition> jewels, MazeView maze) {
		
		ArrayList<Integer> shortest = new ArrayList <Integer>();
		
		for(int i = 0; i < jewels.size(); i++){
			
			int rowdistance = jewels.get(i).row - players.get(n).row;
			
			if (rowdistance < 0){
				
				rowdistance = rowdistance * (-1);
			}
			
			int coldistance = jewels.get(i).col - players.get(n).col;
			
			if (coldistance < 0){
				
				coldistance = coldistance * (-1);
			}
			
			int distance = rowdistance + coldistance;
			
			shortest.add(distance);
		}
		
		int shortestx = shortest.get(0);
		
		int index = 0;
		
		for (int j = 1; j < shortest.size(); j++){
			
			if (shortest.get(j) < shortestx){
				
				shortestx = shortest.get(j);
				
				index = j;
			}	
		}
		
		if (players.get(n).col < jewels.get(index).col){
			
			return Direction.NORTH;
	
		} else if (players.get(n).col > jewels.get(index).col){
			
			return Direction.SOUTH;
			
		} else {
			
			if (players.get(n).row < jewels.get(index).row){
				
				return Direction.EAST;
		
			} else {
				
				return Direction.WEST;
			}
		}
	}	
}