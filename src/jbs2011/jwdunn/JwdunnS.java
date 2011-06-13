package jbs2011.jwdunn;

import java.util.ArrayList;
import java.util.HashMap;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeBoard;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;

public class JwdunnS extends MazePlayer{
	  private String n;
	  
	  public JwdunnS(String n){
		  super(n);
	  }

	  public static void main(String[] args)
	  {
	  	  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
	  	  //players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
	  	  players.add(new RandomPlayer("tim2rand"));
	  	  players.add(new JwdunnS("Js"));
	  	  players.add(new JwdunnD("JD"));
	  	  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	  	  
	  }
	
/**
 * here is my smart player first he finds the distance to the nearest jewel if the distance is under three then he goes through walls to get there
 * if the distance to the jewel is over three he traces the maze(the trace function is not great, it has potential to be mutch better if i had a better 
 * tracing algorithm

*/
	   
public Direction nextMove(HashMap<String, MazePosition> players,ArrayList<MazePosition> jewels, MazeView maze) {
	
	  Direction choise = Direction.NORTH;
	  MazePosition Jplayer = players.get(this.name);
	  System.out.println(Jplayer);
	  
	  double min = 2^50;
	  MazePosition jewelPos= jewels.get(0);
	  
	  for (int i=0; i<jewels.size()-1; i++){
		  //finds the shortest distance to a jewel
		   double x=(jewels.get(i).col-Jplayer.col);
		   double y =(jewels.get(i).row- Jplayer.row);
		   double dist= Math.abs(x) + Math.abs(y);
		   if(dist<min){
			   min = dist;
			   jewelPos = jewels.get(i);
		   	} 
		 }
	  
	  if (min<=3){
		  if (jewelPos.col > players.get(this.name).col){
			  choise = Direction.NORTH;	  
		  }
		  else if (jewelPos.col < players.get(this.name).col){
			  choise =Direction.SOUTH;  
		  }
	   
		  else{
			  if (jewelPos.row > players.get(this.name).row){
			   choise = Direction.EAST; 
			  }
			  else {  
				  choise =Direction.WEST; 
			  }
		  }
		  return choise;
	  }
	  // if the jewel is more than a distance of three away it traces the maze
	  else{
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
}