/**
 * Name: Todd Kirkland
 * Date: 6/6/11
 * Email: tkirk@brandeis.edu
 * W2L1: creates a good maze player
 * Bugs: None
 */

package jbs2011.tkirk.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import java.util.ArrayList;
import java.util.HashMap;

public class TKGoodPlayer extends MazePlayer {
	//fields
	int closestJewel;
	Double currDistance;
	int restrictor;
	
	/**
	 * constructs a maze player based off the super class
	 * @param n
	 */
	public TKGoodPlayer(String n) {
		super(n);
		currDistance = 0.0;
		restrictor = -1;
	}
	/**
	 * This player finds the shortest direct path to a jewel and starts to go in that direction.
	 * if the player encounters a wall, it sees if it can go around the wall 
	 * if it can't, it goes through the wall
	 * if it can, it goes around
	 * this player cannot go back 1 space
	 */
	public Direction nextMove(
		   HashMap<String,MazePosition> players,
		   ArrayList<MazePosition> jewels,
		   MazeView maze) {
		   
		Double tempDistance = Math.pow(2, maze.getDepth() * maze.getWidth());
		
	    for (int i=0;i<jewels.size();i++){  
	       //uses the pythagorean theorem to determine the shortest distance
		   currDistance = Math.sqrt(Math.pow((jewels.get(i).col - players.get(name).col), 2) + (Math.pow((jewels.get(i).row - players.get(name).row), 2)));
		   
		   //sets new closestJewel if applicable
		   if(currDistance < tempDistance){
			   tempDistance = currDistance;
			   closestJewel = i;
		   }
	   }
	   
	   MazePosition pos = new MazePosition(players.get(name).row, players.get(name).col);
	    
	   //looks at the relative position of the coin in relation to the player and makes a move based off of this information
		
	   //if the player's position is more south in relation to the jewel
	   if(players.get(name).row > jewels.get(closestJewel).row && restrictor != 0) {
			if (maze.canMove(pos, Direction.NORTH)) {
				restrictor = 1;
				return Direction.NORTH;
			//if there is a wall, then go west or east in coordination to which direction will get the player closer to the jewel
			} else { 
				if (players.get(name).col > jewels.get(closestJewel).col) {
					if (maze.canMove(pos, Direction.WEST)) {
						return Direction.WEST;
					} 
				} else if (players.get(name).col < jewels.get(closestJewel).col) {
					if (maze.canMove(pos, Direction.EAST)) {
						return Direction.EAST;
					} 
				//if there is a wall to the immediate left or right of the player, attempt to go through the wall
				} else {
					return Direction.NORTH;
				}	
			}
		//if the player's position is more north in relation to the jewel
		} else if(players.get(name).row < jewels.get(closestJewel).row && restrictor != 1) {
			if (maze.canMove(pos, Direction.SOUTH)) {
				restrictor = 0;
				return Direction.SOUTH;
			//if there is a wall, then go west or east in coordination to which direction will get the player closer to the jewel
			} else {
				if (players.get(name).col > jewels.get(closestJewel).col) {
					if (maze.canMove(pos, Direction.WEST)) {
						return Direction.WEST;
					}
				} else if (players.get(name).col < jewels.get(closestJewel).col) {
					if (maze.canMove(pos, Direction.EAST)) {
						return Direction.EAST;
					}
				//if there is a wall to the immediate left or right of the player, attempt to go through the wall
				} else {
					return Direction.SOUTH;
				}
			} 
		//if the player's position is more north in relation to the jewel
		} else if (players.get(name).col > jewels.get(closestJewel).col  && restrictor != 2) {
			if (maze.canMove(pos, Direction.WEST)) {
				restrictor = 3;
				return Direction.WEST;
			//if there is a wall, then go north or south in coordination to which direction will get the player closer to the jewel
			} else {
				if (players.get(name).row > jewels.get(closestJewel).row) {
					if (maze.canMove(pos, Direction.NORTH)) {
						restrictor = 1;
						return Direction.NORTH;
					} 
				} if (players.get(name).row < jewels.get(closestJewel).row) {
					if (maze.canMove(pos, Direction.SOUTH)) {
						restrictor = 0;
						return Direction.SOUTH;
					}
				//if there is a wall to the immediate north or south of the player, attempt to go through the wall
				} else {
					return Direction.WEST;
				}
			}
		//if the player's position is more north in relation to the jewel
		} else if (players.get(name).col < jewels.get(closestJewel).col && restrictor != 3) {
			if (maze.canMove(pos, Direction.EAST)) {
				restrictor = 2;
				return Direction.EAST;
			//if there is a wall, then go north or south in coordination to which direction will get the player closer to the jewel
			} else {
				if (players.get(name).row > jewels.get(closestJewel).row) {
					if (maze.canMove(pos, Direction.NORTH)) {
						restrictor = 1;
						return Direction.NORTH;
					}
				} else if (players.get(name).row < jewels.get(closestJewel).row) {
					if (maze.canMove(pos, Direction.SOUTH)) {
						restrictor = 0;
						return Direction.SOUTH;
					}
				//if there is a wall to the immediate north or south of the player, attempt to go through the wall
				} else { 
					return Direction.EAST;
				}
			}
		}
		//else it stays still
		return Direction.CENTER;
	}
}