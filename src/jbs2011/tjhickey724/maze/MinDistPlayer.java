package jbs2011.tjhickey724.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazeGame;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;
import jbs2011.tjhickey.maze.MazeDist;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;


public class MinDistPlayer extends MazePlayer {
	
	public static void main(String[] args){
		MazeGame.debugging=false;
		MazeGame.playTournament(new TJHplayer("tim1"),new MinDistPlayer("tim2"),20,20,100,50);
	}
	


	public MinDistPlayer(String n) {
		super(n);
	}
	/**
	 * This player simply picks a random direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   
		   MazeDist md = new MazeDist(maze,jewels);
		   Direction d = md.goFrom(players.get(this.name));
		  if (d!=Direction.CENTER)
			  return d;
		  
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}
}
