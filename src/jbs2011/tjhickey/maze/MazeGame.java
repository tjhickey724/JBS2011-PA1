package jbs2011.tjhickey.maze;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Random;

import jbs2011.taha.maze.AdvancedPlayerByTaha;
import jbs2011.taha.maze.BasicPlayerByTaha;
/**
 * A Maze Game is played by multiple players that are initially assigned random positions
 * in a maze. The maze also contains some jewels. The goal of the players is to move through
 * the maze and collect jewels. The players can sometimes push through a wall but they lose 
 * points for doing so.  When a jewel is found it is removed from the board and a new jewel
 * is placed at another random location on the board.  Players may not move on top of each other.
 * 
 * @author tim
 *
 */
public class MazeGame {
  public HashMap<String,Integer> score;
  public HashMap<String,MazePlayer> player;
  public HashMap<String,MazePosition> playerPosition;
  public ArrayList<MazePosition> jewelPosition;
  public ArrayList<MazePosition> freeSpace;
  public MazeBoard theBoard;
  public static boolean debugging = false;
  
  //sahar edits: things with rubies
  public ArrayList<MazePosition> rubyPosition;
  public int extraMoves = 0;
  public MazePlayer extraMovesPlayer;
  /**
   * This creates a maze of the specified size and adds up to 10 jewels to the board.
   * @param w width of the board
   * @param d depth of the board
   */
  public MazeGame(int w, int d) {
	  player = new HashMap<String,MazePlayer>();
	  playerPosition = new HashMap<String,MazePosition>();
	  jewelPosition = new ArrayList<MazePosition>();
	  theBoard = new MazeBoard(w,d);
	  score = new HashMap<String,Integer>();
	  rubyPosition = new ArrayList<MazePosition>();
	  // next we add all free positions into a list and shuffle it!
	  freeSpace = new ArrayList<MazePosition>();
	  for (int i=0; i<w; i++)
		  for(int j=0;j<d;j++)
			  freeSpace.add(new MazePosition(i,j));
	  // shuffling the freeSpace allows us to pick a random elt from the list ...
	  Collections.shuffle(freeSpace);
	  
	  // here we add up to 20 jewels to the board
	  for (int i=0;i<Math.min(20,w*d);i++){
		  MazePosition q = getEmptySpace();
		  if (debugging) System.out.println("adding a jewel at position "+q);
		  jewelPosition.add(q);
	  }
	  //add 0-2 rubies to the board
	  Random r = new java.util.Random();
	  for (int i= r.nextInt(2);i<Math.min(20,3);i++){
		  MazePosition q = getEmptySpace();
		  if (debugging) System.out.println("adding a ruby at position "+q);
		  rubyPosition.add(q);
	  }

  }
  
  /**
   * This checks to see if the requested move is valid. If so it moves the player and then 
   * checks to see if there is a jewel on the new position. If so, it adds 5 points to the score
   * of the player, removes the jewel and places a new jewel on a free space on he board.
   * If there is no jewel there, then it adjusts the freeSpace collection appropriately 
   * and returns
   * 
   * @param p - the MazePlayer
   * @param d - the Direction it requests to move (this might not be a valid move!)
   * @return - it returns true if it was able to make the move, false otherwise.
   */
  public boolean movePlayer(MazePlayer p, Direction d) {
//	  System.out.println("Player "+p.name+" requests to move in direction "+d);
// players can move through walls with some small probability!
		  // calculate the new position after the move 
		  MazePosition oldPos = playerPosition.get(p.name);
		  MazePosition newPos = theBoard.tryMove(oldPos,d);

		  if(extraMoves > 0) {
			  extraMoves--;
		  }
		  else {
			  extraMoves = 0;  
		  }
		  
		  //SAHAR EDIT
		  //check if is there is another player at that position
		  // and if there is someone there then teleport!
		  if (playerPosition.containsValue(newPos)){
			  if (!newPos.equals(oldPos)){
			  	//teleport!
			  	MazePosition m = getEmptySpace();
			  	playerPosition.remove(p.name);
			  	playerPosition.put(p.name, m);
			  	
			  	freeSpace.remove(m);
			  	freeSpace.add(oldPos);
			  	return false; //perhaps you should return true. this is a special case.
			  	
			  }
				  
			  else {
				  if (debugging) System.out.println(p.name+" stays at "+oldPos);
			  return false;
		  }
		  }
		  
		  //otherwise, make the move
		  playerPosition.put(p.name,newPos);
		  if (debugging) System.out.println(p.name+": "+oldPos+" -> "+newPos);
		  
		  //take off points if you moved through a wall
		  if (!theBoard.canMove(oldPos,d)){
			  score.put(p.name,score.get(p.name)-2);
			  if (debugging) System.out.println(p.name+" moved through a wall");
		  }
		  
		  // mark that old space as "free space"
		  freeSpace.add(0,oldPos);
		  
		  // check to see if there is a jewel in the new position.
		  int i = jewelPosition.indexOf(newPos);
		  if (i > -1) {
			  // add 5 to the score
			  score.put(p.name,score.get(p.name)+5);
			  // remove the jewel
			  jewelPosition.remove(i);
			  if (debugging) System.out.println("and lands on a jewel!, score is now " +score.get(p.name));
			  // add another jewel
			  MazePosition q = getEmptySpace();
			  jewelPosition.add(q);
			  if (debugging) System.out.println("adding a new jewel at "+q);
			  
		  }
		 
		  //check to see if there is a ruby in the new position
		  int j = rubyPosition.indexOf(newPos);
		  if (j > -1) {
			  
			  
			  // remove the ruby
			  rubyPosition.remove(j);
			  if (debugging) System.out.println("and lands on a ruby!");
			  //get 5 free moves
			  extraMoves = 5;
			  extraMovesPlayer = p;
			  
			  movePlayer(p,d);
			  
			  // add another ruby
			  MazePosition q = getEmptySpace();
			  rubyPosition.add(q);
			  if (debugging) System.out.println("adding a new ruby at "+q);
			  
		  }
		  else {
			  // if no ruby, then remove the space from the freeSpace list
			  freeSpace.remove(newPos);
		  }
		  
		  
		  return true;

  }
  
  public void addPlayer( MazePlayer p) {
     player.put(p.name,p);
     score.put(p.name,0);
     MazePosition q = getEmptySpace();
     playerPosition.put(p.name, q);
     if (debugging) System.out.println("added player "+p.name+" to the board at position "+q);
  }
  
  public void addJewel() {
	  jewelPosition.add(getEmptySpace());
  }
    	 
  public MazePosition getEmptySpace() {
	  // We should consider the case where there is no more freespace!!
	  // Currently it will throw an index out of bounds exception
	  MazePosition p = freeSpace.remove(freeSpace.size()-1);
	  return p;
  }
  
  /**
   * This method plays a round-robin tournament against a set of players
   * and prints out the results in a table
   */
  public static void playTournament(ArrayList<MazePlayer> players) {
	 playTournament(players,5,10,10,100);
  }
  
  public static void playTournament(
		 MazePlayer p1, MazePlayer p2,
		  int width, int depth, 
		  int reps, int steps) {
	  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
	  players.add(p1);
	  players.add(p2);
	  playTournament(players,width,depth,reps,steps);
  }
  /**
   * This method plays a round-robin tournament against a set of players
   * and prints out the results in a table
   */
  public static void playTournament(
		  ArrayList<MazePlayer> players,
		  int width, int depth, 
		  int reps, int steps) {
	  System.out.println("Playing tournament!");

	  int[][] winners = new int[players.size()][players.size()];
	  for (MazePlayer p1:players)
		  for (MazePlayer p2:players)
		   if (!p1.equals(p2))
		    for (int k=0;k<reps;k++){
			  MazeGame g = new MazeGame(width,depth);
			  g.addPlayer(p1);
			  g.addPlayer(p2);

			  for(int i=0;i<steps;i++){
					if (MazeGame.debugging) System.out.println("\n\n************\nround "+i);
					if (MazeGame.debugging) System.out.println(g.theBoard.drawBoard(g.playerPosition,g.jewelPosition));
				    for (MazePlayer p: g.player.values()){
				    	Direction d = p.nextMove(g.playerPosition,g.jewelPosition, g.rubyPosition, g.theBoard);
					  	g.movePlayer(p,d);
					  //rubies give you extra moves
					   }
			  }
			  int scoreDiff = g.score.get(p1.name)-g.score.get(p2.name);
			  System.out.println(p1.name+" vs "+p2.name+" = "+scoreDiff);
			  if (scoreDiff>0){
				  winners[players.indexOf(p1)][players.indexOf(p2)] += 1;
			  }
			  else {
				  winners[players.indexOf(p2)][players.indexOf(p1)] += 1;			  
			  }
		  }
	  System.out.println("Results");
	  for (int i=0;i<players.size();i++){
		  int sum=0;
		  for(int j=0;j<players.size();j++){
			  sum += winners[i][j];
			  System.out.print("\t"+winners[i][j]);
		  }
		  System.out.println("\t"+sum+"\t"+players.get(i).name);
	  }
	  for (int j=0;j<players.size();j++){
		  int losses=0;
		  for (int i=0;i<players.size();i++) {
			  losses += winners[i][j];
		  }
		  System.out.print("\t"+losses);
	  }
	  System.out.println();
  }
  
  public static void main(String[] args) {
	  MazeGame g = new MazeGame(10,5);
	  System.out.println("The board is\n"+g.theBoard);
	 // MazePlayer p1 = new jbs2011.jsoued.maze.JsouedPlayer("goN");
	  //MazePlayer p2 = new RandomPlayer("rand1");
	//  MazePlayer p3 = new RandomPlayer("rand2");
	  MazePlayer p1 = new jbs2011.sahar.maze.SaharRubyPlayer("s1");
	  MazePlayer p2 = new jbs2011.sahar.maze.SaharRubyPlayer("s2");
	  g.addPlayer(p1);
	  g.addPlayer(p2);
//	  g.addPlayer(p3);
	 // for(int i=0;i<10;i++) g.addPlayer(new RandomPlayer("newrand"+i));
	  for (int i=0;i<100;i++){
		if (g.debugging) System.out.println("\n\n************\nround "+i);
		if (g.debugging) System.out.println(g.theBoard.drawBoard(g.playerPosition,g.jewelPosition));
	    for (MazePlayer p: g.player.values()){
		  Direction d = p.nextMove(g.playerPosition,g.jewelPosition, g.rubyPosition, g.theBoard);
//		  if (g.debugging) System.out.println("Trying to move player "+p.name+" in direction "+d);
		  g.movePlayer(p,d);
	    }
	  }
	  System.out.println("Game Over!");
	  System.out.println("Final Scores");
	  for(String p: g.score.keySet())
		  System.out.println(p + ": "+g.score.get(p));
	  
	  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
	  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
	  players.add(new RandomPlayer("rand3"));
	  playTournament( players);
  }
  
  
}
