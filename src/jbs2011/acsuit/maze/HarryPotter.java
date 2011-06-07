package jbs2011.acsuit.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;


public class HarryPotter extends MazePlayer {
	String n;
	
	public HarryPotter(String n) {
		super(n);
		this.n = n;
	}
	/** My (slightly more) complex player. This player measures the 
	 * position of the player relative to a jewel, checks that
	 *  it can move in that direction, and then moves
	 * towards that jewel. */
	public Direction nextMove(HashMap<String,
			MazePosition> players,
			ArrayList<MazePosition> jewels, 
			MazeView maze) {
		int pick = new Random().nextInt(Direction.values().length);
		for (int i = 0; i < jewels.size(); i++){
			MazePosition jewel = jewels.get(i);
			MazePosition player = players.get(this.n);
			if (player.col > jewel.col && 
					(maze.canMove(players.get(this.n) , Direction.EAST))){
				return Direction.EAST;
			}
			else if (player.col < jewel.col && 
					(maze.canMove(players.get(this.n) , Direction.WEST))){
				return Direction.WEST;
			}
			else if (player.row > jewel.row && 
					(maze.canMove(players.get(this.n) , Direction.NORTH))){
				return Direction.NORTH;
			}
			else if (player.row < jewel.row && 
					(maze.canMove(players.get(this.n) , Direction.SOUTH))){
				return Direction.SOUTH;
			}
		}
		return Direction.WEST;
	}

}
