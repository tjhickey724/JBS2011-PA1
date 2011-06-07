package jbs2011.tkirk.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;

public class TKGoodPlayer extends MazePlayer {
	int closestJewel;
	
	public TKGoodPlayer(String n) {
		super(n);
	}
	/**
	 * This player simply picks a direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */
	public Direction nextMove(
		   HashMap<String,MazePosition> players,
		   ArrayList<MazePosition> jewels,
		   MazeView maze) {
		   
	   Double currDistance = 0.0;
	   Double tempDistance = Math.pow(2, maze.getDepth() * maze.getWidth());

	   for (int i=0;i<jewels.size();i++){
		   
		   currDistance = Math.sqrt(Math.pow((jewels.get(i).col - players.get(name).col),2)+
		   (Math.pow((jewels.get(i).row - players.get(name).row),2)));
		   
		   if(currDistance < tempDistance){
			   tempDistance = currDistance;
			   closestJewel = i;
		   }
	   }
	   MazePosition pos = new MazePosition(players.get(name).row, players.get(name).col);
	    
		if(players.get(name).row > jewels.get(closestJewel).row) {
			if (maze.canMove(pos, Direction.NORTH)) {
				return Direction.NORTH;
				} else { 
					if (players.get(name).col > jewels.get(closestJewel).col) {
						if (maze.canMove(pos, Direction.WEST)) {
							return Direction.WEST;
						} 
					} else if (players.get(name).col < jewels.get(closestJewel).col) {
						if (maze.canMove(pos, Direction.EAST)) {
							return Direction.EAST;
						} 
					} else {
						return Direction.NORTH;
					}	
				}
			} else if(players.get(name).row < jewels.get(closestJewel).row) {
				if (maze.canMove(pos, Direction.SOUTH)) {
					return Direction.SOUTH;
				} else {
					if (players.get(name).col > jewels.get(closestJewel).col) {
						if (maze.canMove(pos, Direction.WEST)) {
							return Direction.WEST;
						}
					} else if (players.get(name).col < jewels.get(closestJewel).col) {
						if (maze.canMove(pos, Direction.EAST)) {
							return Direction.EAST;
						}
					} else {
						return Direction.SOUTH;
					}
				} 
			} else if (players.get(name).col > jewels.get(closestJewel).col) {
				if (maze.canMove(pos, Direction.WEST)) {
					return Direction.WEST;
				} else {
					if (players.get(name).row > jewels.get(closestJewel).row) {
						if (maze.canMove(pos, Direction.NORTH)) {
							return Direction.NORTH;
						} 
					} if (players.get(name).row < jewels.get(closestJewel).row) {
						if (maze.canMove(pos, Direction.SOUTH)) {
							return Direction.SOUTH;
						}
					} else {
						return Direction.WEST;
					}
				}
			} else if (players.get(name).col < jewels.get(closestJewel).col) {
				if (maze.canMove(pos, Direction.EAST)) {
					return Direction.EAST;
				} else {
					if (players.get(name).row > jewels.get(closestJewel).row) {
						if (maze.canMove(pos, Direction.NORTH)) {
							return Direction.NORTH;
						}
					} else if (players.get(name).row < jewels.get(closestJewel).row) {
						if (maze.canMove(pos, Direction.SOUTH)) {
							return Direction.SOUTH;
						}
					} else { 
						return Direction.EAST;
					}
				}
			} else {
			return Direction.CENTER;
		}
		return Direction.CENTER;
	}
}