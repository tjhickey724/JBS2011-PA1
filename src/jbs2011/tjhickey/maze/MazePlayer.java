package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A MazePlayer is a named object that participates in a MazeGame by
 * taking moves based on its current position and the positions of all players
 * and all jewels.
 * @author tim
 *
 */
public abstract class MazePlayer {

   String name;
   
   public MazePlayer() {
	   
   }
   
   public MazePlayer(String name){
	  this.name=name;
   }
   
   /**
    * A MazePlayer object defines a strategy for playing the mazegame.
    * It determines the players next move based on the following data.
    * Note that an implementation of this abstract class can store information
    * about its previous choices in the game (e.g. positions visited) that can
    * be used to make a better choice in the future.
    * @param players - the list of positions of all players (self included)
    * @param jewels - the list of positions of all jewels
    * @param maze - a maze (with read-only access)
    * @return
    */
   public abstract Direction nextMove(
		   HashMap<String,MazePosition> players,
		   ArrayList<MazePosition> jewels,
		   MazeView maze) ;
   
   
}
