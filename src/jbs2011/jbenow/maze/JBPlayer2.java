package jbs2011.jbenow.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;

public class JBPlayer2 extends MazePlayer{

	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jbenow.maze.JBPlayer2("jb2"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	String name;
	
	public JBPlayer2(String n) {
		super(n);
		this.name=n;
	}
	/**
	 * First checks to see if there is a jewel in a surrounding position, if so, moves there.
	 * Otherwise, randomly moves between the 4 given directions.
	 */
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   
		   //First sees if there is a jewel in any surrounding position
		   MazePosition playerXY = players.get(name);
		   
		   if(jewels.contains(playerXY)){
			   return Direction.CENTER;
		   }
		   if(playerXY.row != maze.getWidth()){
			   if(jewels.contains(new MazePosition(playerXY.row+1, playerXY.col))){
				   return Direction.NORTH;
			   }
		   }
		   if(playerXY.row != 0){
			   if(jewels.contains(new MazePosition(playerXY.row-1, playerXY.col))){
				   return Direction.SOUTH;
			   }
		   }
		   if(playerXY.col != maze.getDepth()){
			   if(jewels.contains(new MazePosition(playerXY.row, playerXY.col+1))){
				   return Direction.EAST;
			   }
		   }
		   if(playerXY.col != 0){
			   if(jewels.contains(new MazePosition(playerXY.row, playerXY.col-1))){
				   return Direction.WEST;
			   }
		   }
		
		
		   /*
		    * Otherwise, will pick a random direction that the piece
		    * can move to (without hitting walls)
		    */
		   ArrayList<Direction> directs = new ArrayList();
		   directs.add(Direction.CENTER);
		   directs.add(Direction.NORTH);
		   directs.add(Direction.SOUTH);
		   directs.add(Direction.EAST);
		   directs.add(Direction.WEST);
		   
		   boolean stillMove=true;
		   Direction nextMove;
		   while(stillMove){
			   //gets random position
			   int randDirect = new Random().nextInt(directs.size());
			   nextMove = directs.get(randDirect);
			   //sees if move is possible, if so, player will move there
			   // otherwise, removes that direction from list
			   if(maze.canMove(playerXY, nextMove)){
				   return nextMove;
			   }
			   else{
				   directs.remove(randDirect);
			   }
			   //Makes sure arraylist is not empty, if so, will just
			   // stay put.  Otherwise, does process over again.
			   if(directs.isEmpty()){
				   stillMove=false;
			   }
		   }
		   
		return Direction.CENTER;
	}
}
