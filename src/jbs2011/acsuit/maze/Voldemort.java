package jbs2011.acsuit.maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;


public class Voldemort extends MazePlayer {
	String n;
	
	public Voldemort(String n) {
		super(n);
		this.n = n;
	}
	
	/** My simple player. All this player does is run through all of the directions 
	 * NESW and as long as it is within bounds, it will move there. If it
	 *  doesn't pick any, it picks a random one.
	 */
	public Direction nextMove(HashMap<String, 
			MazePosition> players,
			ArrayList<MazePosition> jewels,
			MazeView maze) {
		int pick = new Random().nextInt(Direction.values().length);
		if (maze.canMove(players.get(this.n) , Direction.NORTH) == true)
			return Direction.NORTH;
		else if (maze.canMove(players.get(this.n) , Direction.EAST) == true)
			return Direction.EAST;
		else if (maze.canMove(players.get(this.n) , Direction.SOUTH) == true)
			return Direction.SOUTH;
		else if (maze.canMove(players.get(this.n) , Direction.WEST) == true)
			return Direction.WEST;
		
		return Direction.values()[pick];
	}

}
