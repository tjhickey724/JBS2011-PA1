package jbs2011.jwdunn;

import java.util.ArrayList;
import java.util.HashMap;


import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;

public class JwdunnD extends MazePlayer{
	  private String n;
	  
	  public JwdunnD(String n){
		  super(n);
	  }

	
/**
 * here is my Dumb player he will move north until he can go no further at which point he will go east if possible if not west. south
 * doesnt work because as soon as he goes one position south he wants to go back north

*/
	   
public Direction nextMove(HashMap<String, MazePosition> players,ArrayList<MazePosition> jewels, MazeView maze) {
	
	  Direction choise = Direction.NORTH;
	  MazePosition Jplayer = players.get(this.name);
	  

		  if((maze.canMove(Jplayer, Direction.CENTER))){
			  	choise = Direction.CENTER;
		  }
		  else if((maze.canMove(Jplayer, Direction.NORTH))){
			  		choise = Direction.NORTH;
		  }	  
		  else if ((maze.canMove(Jplayer, Direction.EAST))){
			  		choise = Direction.EAST;
		  }
		  else if ((maze.canMove(Jplayer, Direction.WEST))){
			  		choise = Direction.WEST;
		  }
		  else if((maze.canMove(Jplayer, Direction.SOUTH))){
			  		choise = Direction.SOUTH;
		  }
		  return choise;
  }
 }

