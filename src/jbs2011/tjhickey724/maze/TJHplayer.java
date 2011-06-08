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
	
	public static void main(String[] args){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
		  players.add(new jbs2011.taha.maze.BasicPlayerByTaha("taha-basic"));
		  players.add(new jbs2011.taha.maze.AdvancedPlayerByTaha("taha-adv"));
		  players.add(new jbs2011.gaspar.maze.GasparPlayer1("gasp1"));
		  players.add(new jbs2011.gaspar.maze.GasparPlayer2("gasp2"));
		  players.add(new jbs2011.MichaelsPlayers.cleverMichael("michael-clever"));
		  players.add(new jbs2011.MichaelsPlayers.dumbMichael("michael-dumb"));
		  players.add(new jbs2011.jbenow.maze.JBPlayer1("JB1"));
		  players.add(new jbs2011.jbenow.maze.JBPlayer2("JB2"));
		  
		  players.add(new jbs2011.sahar.maze.SaharBetterPlayer("sm2"));
		  players.add(new jbs2011.sahar.maze.SaharPlayer("sm1"));

		  players.add(new jbs2011.jcrollai.JCrollPlayerPLvL1("jc1"));
		  players.add(new jbs2011.jcrollai.JCrollPlayerPLvL9001("jc2"));
		  
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer("js1"));
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer2("js2"));
		  
		  players.add(new jbs2011.acsuit.maze.HarryPotter("as1"));
		  players.add(new jbs2011.acsuit.maze.Voldemort("as2"));
		  
		  players.add(new jbs2011.tkirk.maze.TKBadPlayer("tk1"));
		  players.add(new jbs2011.tkirk.maze.TKGoodPlayer("tk2"));


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
