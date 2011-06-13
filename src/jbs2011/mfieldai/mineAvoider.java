
package jbs2011.mfieldai;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class mineAvoider extends MazePlayer {

	private String n;
	public mineAvoider(String n) {
		super(n);
		this.n=n;
	}
	/**
	 * This player simply picks a random direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */
	
		
	
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze, ArrayList<MazePosition>mines) {
		int pick = new Random().nextInt(Direction.values().length);
		//picks a random direction
		
		//if the coordient of the direction chosen results in a mine don't move there. 
		if((maze.canMove(players.get(n),Direction.EAST)==true))
		{
			return Direction.NORTH;
		}
		if((maze.canMove(players.get(n),Direction.EAST)==true))
		{
			return Direction.SOUTH;
		}
		if((maze.canMove(players.get(n),Direction.EAST)==true))
		{
			return Direction.EAST;
		}
		else
		{
			return Direction.WEST;
		}
		
	}
}
