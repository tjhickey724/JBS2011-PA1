package jbs2011.sahar.maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;


public class SaharBetterPlayer extends MazePlayer {

	public SaharBetterPlayer(String n){
		super(n);
	}
	
	//given a list of jewels, and your position, find the CLOSEST gem that is a straight line away from you - and make a beeline for it.
	private Direction findClosestGem(MazePosition me, ArrayList<MazePosition> jewels){
		int closest=-1;
		int dist;
		Direction temp = Direction.CENTER;
		for(MazePosition gem : jewels){
			if ((gem.row == me.row) && (gem.col > me.col)) {
				dist = gem.col - me.col;
				if ((dist < closest) || (closest == -1)){
					closest = dist;
					temp = Direction.NORTH;
				}
			}
			
			if ((gem.row == me.row) && (gem.col<me.col)){
				dist = me.col - gem.col;
				if ((dist < closest) || (closest == -1)) {
					closest = dist;
					temp = Direction.SOUTH;
				}
			}
			
			if ((gem.col == me.col) && (gem.row>me.row)){
				dist = gem.row - me.row;
				if ((dist < closest) || (closest == -1)) {
					closest = dist;
					temp = Direction.EAST;
				}
			}
			
			
			if ((gem.col == me.col) && (gem.row<me.row)){
				dist = me.row - gem.row;
				if ((dist < closest) || (closest == -1)) {
					closest = dist;
					temp = Direction.WEST;
				}
			}	
		}
		return temp;
	}
	
	
	
	
	
	@Override
	/**
	 * This player will try to see if a gem is in a straight line from her current position. 
	 * For the CLOSEST gem that matches this, move straight in that direction, even through walls.
	 */
	public Direction nextMove(HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, MazeView maze) {
			
		MazePosition whereAmI = players.get(super.name);

		Direction goD = this.findClosestGem(whereAmI, jewels);
		if (goD != null) {
			return goD;
		}
		 
		
		// there is no gem in any direction, go a random direction
		//random direction code copied from RandomPlayer.java
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
		
	}
		
}
