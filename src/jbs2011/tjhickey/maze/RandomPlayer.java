
package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class RandomPlayer extends MazePlayer {

	public RandomPlayer(String n) {
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
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}
}
