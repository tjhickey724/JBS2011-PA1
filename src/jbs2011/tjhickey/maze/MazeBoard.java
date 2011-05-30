package jbs2011.tjhickey.maze;

import java.util.HashMap;
import java.util.ArrayList;

public class MazeBoard implements MazeView {
  public static final double edginess=0.6;
  public static final double WALL_PROB = 0.25;
  public int width, depth;
  MazeCell[][] board;
  
  public int getWidth(){
	  return width;
  }
  
  public int getDepth(){
	  return depth;
  }
  
  public static void main(String[] args){
	  MazeBoard b = new MazeBoard(15,5);
	  System.out.println("the maze is below\n\n");
	  System.out.println(b.toString());
  }
  
  public boolean canMove(MazePosition p, Direction d){
	  switch(d){
	  case NORTH: return board[p.row][p.col].north.getIsOpen();
	  case SOUTH: return board[p.row][p.col].south.getIsOpen();
	  case EAST: return board[p.row][p.col].east.getIsOpen();
	  case WEST: return board[p.row][p.col].west.getIsOpen();
	  }
	  return false;
  }
  
  public MazePosition tryMove(MazePosition p, Direction d) {
	  int newrow=p.row,newcol=p.col;
	  switch(d){
	  case NORTH: newcol++; if (newcol>=depth) newcol=p.col; break;
	  case SOUTH: newcol--; if (newcol<0) newcol=p.col; break;
	  case EAST: newrow++; if (newrow>=width) newrow=p.row; break;
	  case WEST: newrow--; if (newrow < 0) newrow=p.row; break;
	  }
	  // here we allow users to move through walls with probability WALL_PROB
	  if (canMove(p,d)){
	      return new MazePosition(newrow,newcol);
	  }
	  else if (Math.random()<WALL_PROB){
	      return new MazePosition(newrow,newcol);
	  }
	  else
		  return p;
  }
  
  public MazeBoard(int w, int d){
	  this.width=w;
	  this.depth=d;
	  
	  board = new MazeCell[w][d];
	  /*
	   * first we create four random walls for every cell in the maze
	   * Each wall may or may not be open (depending on a random choice of edginess)
	   */
	  for (int i=0;i<w;i++)
		  for (int j=0; j<d; j++) {
			  board[i][j] = 
				  new MazeCell(randWall(),randWall(),randWall(),randWall());
		  }
	  /*
	   * next we throw away most of the south and west walls and replace them with 
	   * the neighboring cells north and east cells
	   */
	  for (int i=1;i<w;i++)
		  for (int j=1;j<d;j++){
			  board[i][j].west = board[i-1][j].east;
			  board[i][j].south = board[i][j-1].north;
		  }
	  for (int i=1;i<w;i++){
		  board[i][0].west = board[i-1][0].east; 
	  }
	  for (int j=1;j<d;j++){
		  board[0][j].south = board[0][j-1].north;
		  
	  }
	  
	  /*
	   * finally we make the border solid
	   */
	  for (int i=0;i<w;i++){
		  board[i][0].south.setIsOpen(false);
		  board[i][depth-1].north.setIsOpen(false);
	  }
	  for (int j=0; j<d; j++){
		  board[0][j].west.setIsOpen(false);
		  board[width-1][j].east.setIsOpen(false);
	  }
	  
  }
  
  private MazeWall randWall() {
	  return new MazeWall(Math.random() < MazeBoard.edginess);
  }
  
  /**
   * this draws the maze board
   */
  public String toString() {
	  StringBuffer buf = new StringBuffer();
	  System.out.println("printing!!");
	  System.out.println("depth="+depth);

	  for(int j=depth-1; j>=0;j--){
		  /*
		   *  for each cell in each row of the board we add three rows to the printed version
		   *    the bottom row of corners and south walls
		   *    the middle row of west/east walls and inner cell spaces
		   *    the top row of corners and north walls
		   */

		  // finally add the top row of characters

		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  buf.append('+');
			  if (cell.north.getIsOpen()) buf.append("   "); else buf.append("___");
			  buf.append('+');
		  }
		  buf.append("\n");

		  
		  //now add the middle rows of characters ...
		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  if (cell.west.getIsOpen()) buf.append(" "); else buf.append("|");
			  buf.append("   ");
			  if (cell.east.getIsOpen()) buf.append(" "); else buf.append("|");
		  }
		  buf.append("\n");
		  System.out.println("");
		  
		  // first the top walls
		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  buf.append('+');
			  if (cell.south.getIsOpen()) buf.append("   "); else buf.append("___");
			  buf.append('+');
		  }

		  // and then add the newline and prepare to do the next row
		  buf.append("\n");

		  
	  }
	  // finally, we convert the buffer to a string and return it!
	  return buf.toString();
  }
  
  /**
   * This draws the board and also draws the Players and the Jewels
   * @param playerPosition
   * @param jewelPosition
   * @return
   */
  public String drawBoard(
		  HashMap<String,MazePosition> playerPosition, 
		  ArrayList<MazePosition> jewelPosition) {
	  StringBuffer buf = new StringBuffer();
	  System.out.println("printing!!");
	  System.out.println("depth="+depth);

	  for(int j=depth-1; j>=0;j--){
		  /*
		   *  for each cell in each row of the board we add three rows to the printed version
		   *    the bottom row of corners and south walls
		   *    the middle row of west/east walls and inner cell spaces
		   *    the top row of corners and north walls
		   */

		  // finally add the top row of characters

		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  buf.append('+');
			  if (cell.north.getIsOpen()) buf.append("   "); else buf.append("___");
			  buf.append('+');
		  }
		  buf.append("\n");

		  MazePosition pos = new MazePosition(0,0);
		  //now add the middle rows of characters ...
		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  pos.row=i;pos.col=j;
			  if (cell.west.getIsOpen()) buf.append(" "); else buf.append("|");
			  if (playerPosition.containsValue(pos))
			       buf.append(" P ");
			  else if (jewelPosition.contains(pos))
				  buf.append(" * ");
			  else 
				  buf.append("   ");
			  if (cell.east.getIsOpen()) buf.append(" "); else buf.append("|");
		  }
		  buf.append("\n");
		  System.out.println("");
		  
		  // first the top walls
		  for (int i=0; i<width; i++){
			  MazeCell cell = board[i][j];
			  buf.append('+');
			  if (cell.south.getIsOpen()) buf.append("   "); else buf.append("___");
			  buf.append('+');
		  }

		  // and then add the newline and prepare to do the next row
		  buf.append("\n");

		  
	  }
	  // finally, we convert the buffer to a string and return it!
	  return buf.toString();
  }
}
