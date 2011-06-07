/**
 * 
 */
package jbs2011.sahar.maze;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeBoard;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

/**
 * @author sahar
 *
 */
public class SaharPlayer extends MazePlayer {
	
	
	public SaharPlayer (String n){
		super(n);

	}
	
	public String getName(){
		return this.name;
		
	}
	/**
	 * 
	 * Given a current position and a list of gems, tells you if there are any gems if you go straight from your position to the edge of the maze.
	 * @param me Position of your character
	 * @param jewels ArrayList of MazePositions (where the Jewels are)
	 * @return Direction of the first matching gem. If none is found, return null;
	 */
	private Direction findGem(MazePosition me, ArrayList<MazePosition> jewels){
		for(MazePosition gem : jewels){
			if ((gem.row == me.row) && (gem.col > me.col)) {
				//go north
				return Direction.NORTH;
			}
			if ((gem.row == me.row) && (gem.col<me.col)){
				//go south
				return Direction.SOUTH;
			}
			if ((gem.col == me.col) && (gem.row>me.row)){
				return Direction.EAST;
			}
			if ((gem.col == me.col) && (gem.row<me.row)){
				return Direction.WEST;
			}	
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see jbs2011.tjhickey.maze.MazePlayer#nextMove(java.util.HashMap, java.util.ArrayList, jbs2011.tjhickey.maze.MazeView)
	 */
	@Override
	/**
	 * This player will try to see if a gem is in a straight line from her current position. 
	 * For the first gem that matches this, move straight in that direction, even through walls.
	 */
	public Direction nextMove(HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, MazeView maze) {

		// According to Bjorn (and intuition), the Mazeview is a mistake. We should be given MazeBoards. Since we are implementing an abstract class, we don't have the luxury of changing it in code.
		// Instead, I am going to assume every MazeView maze is actually a MazeBoard maze.
		// Thing is, the MazeView interface is clearly insuffient because it doesn't let us see the actual, you know, maze.
		
		MazeBoard board = (MazeBoard)maze;
		MazePosition whereAmI = players.get(this.getName());

		Direction goD = this.findGem(whereAmI, jewels);
		if (goD != null) {
			return goD;
		}
		 
		
		// there is no gem in any direction, go a random direction
		//random direction code copied from RandomPlayer.java
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}

}
