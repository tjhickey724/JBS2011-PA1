package jbs2011.tjhickey724.maze;

import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;


public class TJHplayer extends MazePlayer {
	
	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim2"));
		  players.add(new RandomPlayer("tim2rand"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	public TJHplayer(String n) {
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
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.WEST;
	}
}
