package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;

public class TimPlayer1 extends MazePlayer {

	public TimPlayer1(String n) {
		super(n);
	}
	
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		return Direction.NORTH;
	}
}
