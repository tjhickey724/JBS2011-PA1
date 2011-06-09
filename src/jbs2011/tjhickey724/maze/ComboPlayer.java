package jbs2011.tjhickey724.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazeGame;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.MazeDist;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;


public class ComboPlayer extends MazePlayer {
	
	MazePlayer md,nw;
	
	public static void main(String[] args){
		MazeGame.debugging=false;
		MazeGame.playTournament(new ComboPlayer("cp"),new MinDistPlayer("mdp"),60,3,100,50);
	}
	


	public ComboPlayer(String n) {
		super(n);
		md = new MinDistPlayer(n);
		nw = new NoWallsPlayer(n);
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
		   
		   Direction d1 = md.nextMove(players,jewels,maze);
		   Direction d2 =nw.nextMove(players, jewels, maze);
		   
		  if (d1 !=Direction.CENTER)
			  return d1;
		  else if (d2 != Direction.CENTER)
			  return d2;
		  
		  
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}
	   
	   

}


