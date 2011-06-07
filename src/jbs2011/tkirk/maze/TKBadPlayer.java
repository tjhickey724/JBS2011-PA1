package jbs2011.tkirk.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;

public class TKBadPlayer extends MazePlayer {
	int i;
	int j;
	
	public TKBadPlayer(String n) {
		super(n);
		i = 0;
		j = 0;
	}
	 public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   
	   if (i == 0) {
			if (j == 0) {
				j = 1;
				return Direction.EAST;
			} else {
				i = 1;
				j = 0;
				return Direction.EAST;
			}
		} else if (i == 1) {
			if (j == 0) {
				j = 1;
				return Direction.SOUTH;
			} else {
				i = 2;
				j = 0;
				return Direction.SOUTH;
			}
		} else if (i == 2) {
			if (j == 0) {
				j = 1;
				return Direction.WEST;
			} else {
				i = 3;
				j = 0;
				return Direction.WEST;
			}
		} else {
			if (j == 0) {
				j = 1;
				return Direction.NORTH;
			} else {
				i = 0;
				j = 0;
				return Direction.NORTH;
			}
		}
	}
}
