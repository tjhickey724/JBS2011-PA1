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


public class NoWallsPlayer extends MazePlayer {
	
	public static void main(String[] args){
		MazeGame.debugging=false;
		MazeGame.playTournament(new NoWallsPlayer("nwp"),new MinDistPlayer("mdp"),20,20,100,50);
	}
	


	public NoWallsPlayer(String n) {
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
		   
		   MazeDist md = new MazeDist(new NoWallsMaze(maze.getWidth(),maze.getDepth()),jewels);
		   Direction d = md.goFrom(players.get(this.name));
		  if (d!=Direction.CENTER)
			  return d;
		  
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}
	   
	   
	public class NoWallsMaze implements MazeView {
		int width,depth;
		
	  public int getDepth(){
		  return depth;
	  }
	  
	  public int getWidth(){
		  return width;
	  }
		
	  public NoWallsMaze(int width, int depth) {
		  this.width=width; 
		  this.depth=depth;
	  }
	  
	  public boolean canMove(MazePosition p, Direction d){
		  switch(d) {
		  case NORTH: return p.row<depth-1;
		  case SOUTH: return p.row>0;
		  case EAST: return p.col<width-1;
		  case WEST: return p.col>0;
		  }
		  return false;
	  }
	}
	
	public Direction nextMove(HashMap<String, MazePosition> players, 
			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
		return this.nextMove(players, jewels, maze);
		
	}
}


