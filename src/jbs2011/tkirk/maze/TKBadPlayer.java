/**
 * Name: Todd Kirkland
 * Date: 6/6/11
 * Email: tkirk@brandeis.edu
 * W2L1: creates a bad maze player
 * Bugs: None
 */

package jbs2011.tkirk.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;

public class TKBadPlayer extends MazePlayer {
	int closestJewel;
	Double currDistance;
	
	/**
	 * constructs a maze player based off the super class
	 * @param n
	 */
	public TKBadPlayer(String n) {
		super(n);
		currDistance = 0.0;
	}
	
	/**
	 * This player finds the shortest direct path to a jewel and starts to go in that direction.
	 * this player does not ignore walls.
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
		
		//if the player's position is more south in relation to the jewel   
		if(players.get(name).row > jewels.get(closestJewel).row) {
			return Direction.NORTH;	
			
		//if the player's position is more north in relation to the jewel
		} else if(players.get(name).row < jewels.get(closestJewel).row) {
			return Direction.SOUTH;
			
		//if the player's position is more east in relation to the jewel
		} else if (players.get(name).col > jewels.get(closestJewel).col) {
			return Direction.WEST;
			  
		//if the player's position is more west in relation to the jewel
		} else if (players.get(name).col < jewels.get(closestJewel).col) {
			return Direction.EAST;
		} else {
			return Direction.CENTER;
		}
	 }	
}