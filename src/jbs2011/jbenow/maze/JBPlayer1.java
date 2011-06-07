package jbs2011.jbenow.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;


/**
 * This is my smarter player
 *
 */
public class JBPlayer1 extends MazePlayer{
	
	HashMap<String,MazePosition> players;
	ArrayList<MazePosition> jewels;
	MazeView maze;
	int jewelDistribution[][];
	boolean debugging = false;
	String name;

	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jbenow.maze.JBPlayer1("jb1"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim2"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	public JBPlayer1(String n) {
		super(n);
		this.name = n;
	}
	
	
	/**
	 * Uses an 2d array that maps out how far each position is from a jewel. Makes an
	 * optimal move based on this array.
	 * 
	 */

	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		
		   this.players = players;
		   this.jewels = jewels;
		   this.maze = maze;
		   
		   
		   createJewelDist();
		   
		   if(debugging){
			   int jd[][] = jewelDistribution;
			   
			   for (int cols=0 ; cols<maze.getDepth(); cols++){
				   for(int rows=0 ; rows<maze.getWidth(); rows++){
					  System.out.print(jd[rows][cols]);
				   }
				   System.out.println();
			   }
		   }
		   
		   
		   
		   //Checks jewel distribution matrix to see what move is optimal, makes 
		   // optimal move
		   MazePosition playerXY = players.get(name);
		   if(debugging){
			   System.out.println("This Player is at position:"+  playerXY.row + "," + playerXY.col);
		   }
		   int bestMoveLength = jewelDistribution[playerXY.row][playerXY.col];
		   Direction nextMove = Direction.CENTER;
		   
		   
		   if(maze.canMove(playerXY, Direction.NORTH)){
			   int tempMoveLength = jewelDistribution[playerXY.row][playerXY.col+1];
			   if(tempMoveLength <= bestMoveLength){
				   bestMoveLength = tempMoveLength;
				   nextMove = Direction.NORTH;
			   }
		   }
		   if(maze.canMove(playerXY, Direction.SOUTH)){
			   int tempMoveLength = jewelDistribution[playerXY.row][playerXY.col-1];
			   if(tempMoveLength <= bestMoveLength){
				   bestMoveLength = tempMoveLength;
				   nextMove = Direction.SOUTH;
			   }
		   }
		   if(maze.canMove(playerXY, Direction.EAST)){
			   int tempMoveLength = jewelDistribution[playerXY.row+1][playerXY.col];
			   if(tempMoveLength <= bestMoveLength){
				   bestMoveLength = tempMoveLength;
				   nextMove = Direction.EAST;
			   }
		   }
		   if(maze.canMove(playerXY, Direction.WEST)){
			   int tempMoveLength = jewelDistribution[playerXY.row-1][playerXY.col];
			   if(tempMoveLength <= bestMoveLength){
				   bestMoveLength = tempMoveLength;
				   nextMove = Direction.WEST;
			   }
		   }
		   
		
		return nextMove;
	   }
	   
	   
	   /**
	    * Create an 2d array that positions the jewels and marks how far away each point
	    * is from the jewels.
	    * 
	    * @return a 2d array
	    */
	   public void createJewelDist(){
		   jewelDistribution = new int[maze.getWidth()][maze.getDepth()];
		   
		   
		   Iterator iterator = jewels.iterator();
		   
		  
		   //Replace every jeweled cell in the maze with -1
		   while(iterator.hasNext()){
			   MazePosition jewel = (MazePosition) iterator.next();
			   jewelDistribution[jewel.row][jewel.col]=-1;   
		   }
		   
		  
		   
		   //Fill every not filled spot with the longest pathsize possible (depthxwidth)
		   for (int cols=0 ; cols<maze.getDepth(); cols++){
			   for(int rows=0 ; rows<maze.getWidth(); rows++){
				   if(jewelDistribution[rows][cols]==0){
					   jewelDistribution[rows][cols]=maze.getDepth()*maze.getWidth();
				   }
			   }
		   }
		   
		   //For every jewel, call the recursive pathfinder
		   iterator = jewels.iterator();
		   while(iterator.hasNext()){
			   jewelfillRec((MazePosition) iterator.next(), 1);
		   }
		   
		   
		   if(debugging){
			   System.out.println("filled maze");
		   }
		   
	   }
	   
	   /**
	    * Sees if any part of the array is not filled.
	    * @param jewelDist- how the jewels are distributed and how far each cell is from a jewel
	    * @return true/false whether the matrix is completely filled.
	    */
	   public boolean jewelDistNotFilled(){
		   for (int cols=0 ; cols<maze.getDepth(); cols++){
			   for(int rows=0 ; rows<maze.getWidth(); rows++){
				   if(jewelDistribution[rows][cols]==0){
					   return true;
				   }
			   }
		   }
		   return false;
	   }
	   
	   
	   
	   
	   /**
	    * Recursively goes from a starting point (a jewel) and places all moves from
	    * this position with a number depicting how far away the cell is from the jewel.
	    * 
	    * @param cellSpot = current cell spot, checks if can move in any direction
	    * @param iter = the counter of how far away the next cell will be from a jewel
	    * 
	    */
	   public void jewelfillRec(MazePosition cellSpot, int iter){
		   if(maze.canMove(cellSpot,Direction.NORTH)){
			   jewelCheck(cellSpot, iter, Direction.NORTH);
		   }
		   if(maze.canMove(cellSpot,Direction.SOUTH)){
			   jewelCheck(cellSpot, iter, Direction.SOUTH);
		   }
		   if(maze.canMove(cellSpot,Direction.EAST)){
			   jewelCheck(cellSpot, iter, Direction.EAST);
		   }
		   if(maze.canMove(cellSpot,Direction.WEST)){
			   jewelCheck(cellSpot, iter, Direction.WEST);
		   }
	   }
	   
	   
	   /**
	    * Helps recursive method, this is the 'junk' within the if statements.  Depending on which
	    * directions, it checks to see if the new cell move already has an iterative number, if so,
	    * will replace it if it's too big.  Otherwise, moves on with business.
	    * 
	    * @param cellSpot = current cell spot, moves forward by given direction
	    * @param iter = the counter of how far away this new move is from a jewel
	    * @param dir = the direction the new cell is from the old cell (helps when adding)
	    */
	   public void jewelCheck(MazePosition cellSpot, int iter, Direction dir){
		   MazePosition newMove = null;
		   if (dir==Direction.NORTH){
			   newMove = new MazePosition(cellSpot.row, cellSpot.col+1);
		   }
		   else if (dir == Direction.SOUTH){
			   newMove = new MazePosition(cellSpot.row, cellSpot.col-1);
		   }
		   else if (dir == Direction.EAST){
			   newMove = new MazePosition(cellSpot.row+1, cellSpot.col);
		   }
		   else if (dir == Direction.WEST){
			   newMove = new MazePosition(cellSpot.row-1, cellSpot.col);
		   }
		   
		   if(jewelDistribution[newMove.row][newMove.col] > iter){
			   jewelDistribution[newMove.row][newMove.col]=iter;
			   jewelfillRec(newMove,iter+1);
		   }
	   }

}

