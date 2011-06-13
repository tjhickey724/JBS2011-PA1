package jbs2011.tjhickey724.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazeGame;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;
import jbs2011.tjhickey.maze.MazeDist;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

/**
 * Racer looks for a jewel which is closer to it than to any other player ...
 * THIS IS UNDER CONSTRUCTION
 * @author tim
 *
 */
public class Racer extends MazePlayer {
	
	public static void main(String[] args){
		MazeGame.debugging=true;
		MazeGame.playTournament(new Racer("racer"),new MinDistPlayer("md"),20,5,1,50);
	}
	
	
	public Racer(String n) {
		super(n);
	}
	/**
	 * This player simply picks a random direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		  // find min dist from each cell to a foe
		   ArrayList<MazePosition> foes = new ArrayList<MazePosition>();
		   for(String s:players.keySet()){
			   if (!s.equals(this.name)){
				   foes.add(players.get(s));
			   }
		   }
		   
		   // find min dist from each cell to me
		   MazeDist m1 = new MazeDist(maze,foes);
		   ArrayList<MazePosition> me = new ArrayList<MazePosition>();
		   me.add(players.get(this.name));
		   MazeDist m2 = new MazeDist(maze,me);
		   
		   // find the closest jewel which is closer to me than anyone else
		   // and find the min dist of each position to that jewel
		   // and use that to determine the next move!
		   int maxDist = maze.getWidth()*maze.getDepth();
		   int bestDist = maxDist;
		   MazePosition bestPos = new MazePosition(0,0);
		   for(MazePosition p:jewels){
			   if (m1.minDist(p)>m2.minDist(p)){
				   if (m2.minDist(p)<bestDist) {
					   bestDist = m2.minDist(p);
					   bestPos=p;
				   }
			   }
		   }
		   if (bestDist == maxDist) return Direction.CENTER;
		   
		   MazeDist toJewel = new MazeDist(maze,bestPos);
		   return toJewel.goFrom(players.get(this.name));
		   
	}
	   
	   public Direction nextMove(HashMap<String, MazePosition> players, 
	 			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
	 		return this.nextMove(players, jewels, maze);
	 		
	 	}
}
