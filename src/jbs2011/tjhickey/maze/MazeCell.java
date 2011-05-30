package jbs2011.tjhickey.maze;
import jbs2011.tjhickey.maze.MazeWall;


public class MazeCell {
  MazeWall north;
  MazeWall south;
  MazeWall east;
  MazeWall west;
  
  public MazeCell() {
	  /* this is the default constructor */
  }
  
  public MazeCell(MazeWall n, MazeWall s, MazeWall e,MazeWall w) {
	  this.north=n; this.south=s; this.east=e; this.west=w;
  }
}
