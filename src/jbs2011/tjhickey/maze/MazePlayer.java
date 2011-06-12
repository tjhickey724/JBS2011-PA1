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

   public String name;
   public int bombs;
   int chainlink;
   public String playerToBomb;
   MazePosition pointShot;
   
   public MazePlayer() {
	   
   }
   
   public MazePlayer(String name){
	  this.name=name;
   }
   public void setOtherPlater(String x){
	   playerToBomb=x;
   }
   public String getOtherPlayer(){
	   return playerToBomb;
   }
   public int numberBombs(){
	   return bombs;
   }
   public boolean addBomb(){
	   if(bombs<3){
	   bombs+=1;
	   return true;
	   }
	   return false;
   }
   
   public boolean bombsAway(){
	   if(bombs>0){
		   bombs--;
		   return true;
	   }
	   return false; 
   }
   public boolean addChain(){
	   if(chainlink<3){
	   chainlink++;
	   return true;
	   }
	   return false;
   }
   public int chains(){
	   return chainlink;
   }
   public boolean hookShot(int x){
	   if(chainlink<=x){
		   chainlink=chainlink-x;
		   return true;
	   }
	   return false;
   }
   public void setPointShot(MazePosition x){
	   pointShot=x;
   }
   public MazePosition pointShot(){
	   
	   return pointShot;
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
   public abstract int nextMove(
		   HashMap<String,MazePosition> players,
		   ArrayList<MazePosition> jewels,
		   MazeView maze,
		   ArrayList<MazePosition> bombs,
		   ArrayList<MazePosition> chainlinks) ;
   
   
}
