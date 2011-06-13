package jbs2011.MichaelsPlayers;
import jbs2011.tjhickey.maze.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class dumbMichael extends MazePlayer  {

	public dumbMichael(String n) {
		super(n);
	}
	
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   //checks if player is aligned with jewel in row or column and follows this route.
		   for(int x=0; x<jewels.size(); x++){
			if(jewels.get(x).row==players.get(name).row){
				if(jewels.get(x).col>players.get(name).col && maze.canMove(jewels.get(x), Direction.SOUTH)){
					return Direction.SOUTH;
				}else{
					if(maze.canMove(jewels.get(x), Direction.NORTH)){
						return Direction.NORTH;
					}
				}
			}else if(jewels.get(x).col==players.get(name).col){
				if(jewels.get(x).row>players.get(name).row && maze.canMove(jewels.get(x), Direction.EAST)){
					return Direction.EAST;
				}else{
					if(maze.canMove(jewels.get(x), Direction.WEST)){
						return Direction.WEST;
					}
				}
			}
		   }//otherwise, random.
			int pick = new Random().nextInt(Direction.values().length);
			return Direction.values()[pick];
		}
	   public Direction nextMove(HashMap<String, MazePosition> players, 
	 			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
	 		return this.nextMove(players, jewels, maze);
	 		
	 	}
	   }