package jbs2011.gaspar.maze;

import jbs2011.tjhickey.maze.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.HashMap;
/**
 * class represents a player 
 * the player object  will have a nextMove method that will be given
 *     a maze object (implementing the MazeBoard interface)
 *     a position (implementing the MazePosition interface)
 *     an ArrayList of MazePositions of the competitors
 *     an ArrayList of MazePositions of the jewels
 * and will return the next move (N,S,E,W,P) for North, South, East, West, and Pass.
 * @author gasparobimba
 *
 */
public class GasparPlayer1 extends MazePlayer{

	//creates an empty constructor
	public GasparPlayer1(){
		
	}
	
	//this player constructor takes in the name of the player
	public GasparPlayer1(String name){
		super(name);
	}
	/**
	 * This is the dumb player class
	 * The player checks for the distance of all the jewels and chooses the
	 * one closer to him, if the jewel is above him, he moves up. If the jewel is below him,
	 * he moves down. Otherwise, he stays wherever he is 
	 */
	@Override
	public Direction nextMove(
			HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, 
			MazeView maze, LinkedList<Integer> jewelValue) {
		
		//player checks the distance between him and the closest jewel
		MazePosition pos=players.get(name);
		double dist,slope=0,tmp=0;
		
		for (int i=0;i<jewels.size();i++){
			dist=Math.sqrt(Math.pow((jewels.get(i).col-pos.col),2)+
					(Math.pow((jewels.get(i).row-pos.row),2)));
			
			double denom=(jewels.get(i).row-pos.row);
			if (denom!=0)
			 slope=(jewels.get(i).col-pos.col)/denom;
			
			if (dist<tmp && slope>0)	//if the closest jewel is above the player
				return Direction.NORTH;

			else if(dist<tmp && slope<0)//if the closest jewel is below the player
				return Direction.SOUTH;
			
			else dist=tmp;
		}
		return Direction.EAST;
	}
	
	
}
