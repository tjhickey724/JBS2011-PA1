package jbs2011.gaspar.maze;

import jbs2011.tjhickey.maze.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
public class GasparPlayer2 extends MazePlayer{

	//creates an empty constructor
	public GasparPlayer2(){

	}

	//this player constructor takes in the name of the player
	public GasparPlayer2(String name){
		super(name);
	}
	/**
	 * This is the smart player class
	 * STRATEGY:
	 * The player finds the distance between him and the closest jewel
	 * He also checks for the distance of all the other competitors with respect to that jewel.
	 * If there is any competitor closer to the said jewel than the player, the player must chose the
	 * next closest jewel and also determine the distance of others.
	 * If he finds a jewel that hes closest to and no other player is closer, he movers in the direction of the
	 * jewel. otherwise he moves in the direction of the jewel that has him as the second closest 
	 */

	ArrayList<MazePosition> jewelsCloser=new ArrayList<MazePosition>();
	double dist2,dist=0,slope=0,tmp=100000000;



	@Override
	public Direction nextMove(
			HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, 
			MazeView maze, LinkedList<Integer> jewelValue) {
		jewelsCloser=jewels;
		MazePosition pos=players.get(name);
		
		int close=returnClosest(pos);

		//check the distance between other players and that jewel
		for (int j=0;j<players.size();j++){
			try{
			dist2=Math.sqrt(Math.pow((players.get(j).col-jewels.get(close).col),2)+
					(Math.pow((players.get(j).row-jewels.get(close).row),2)));

			if(tmp<dist2 && tmp!=100000000){	//if there is no competitor closer to the jewel than player

				if(pos.row>jewels.get(close).row)			//if jewel is below player
					return Direction.SOUTH;
				else if(pos.row<jewels.get(close).row)	//if jewel is above player
					return Direction.NORTH;
				else
					return Direction.CENTER;				//stay at centre
				} 

			
			else 
				close=returnClosest(jewelsCloser.remove(close));}
			catch (Exception e){
				
			} 

		}//end check dist btn others and the jewel

		return Direction.EAST;
	}//end nextmove
	
	/**
	 * helper method that gets the closest jewel to the player
	 * @param pos
	 * @return
	 */
	public int returnClosest(MazePosition pos){ //gets the closest jewel
		int closest=0;
		//player checks the distance between him and the closest jewel
		//if(jewelsCloser0){
			for (int i=0;i<jewelsCloser.size();i++){
				dist=Math.sqrt(Math.pow((jewelsCloser.get(i).col-pos.col),2)+
						(Math.pow((jewelsCloser.get(i).row-pos.row),2)));
				if(dist<tmp){
					tmp=dist;
					closest =i;
				}
			}

		return closest;
	}



}
