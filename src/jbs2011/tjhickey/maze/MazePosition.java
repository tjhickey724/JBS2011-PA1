package jbs2011.tjhickey.maze;

public class MazePosition {
  public int row;
  public int col;
  
  public MazePosition(int r, int c){
	  this.row=r; this.col=c;
  }
  
  public boolean equals(MazePosition x){
	  return (this.row==x.row)&&(this.col==x.col);
  }
  
  public MazePosition move(Direction d) {
	  switch (d) {
		  case NORTH:
			  return new MazePosition(row, col+1);
		  case SOUTH:
			  return new MazePosition(row, col-1);
		  case WEST:
			  return new MazePosition(row+1, col);
		  case EAST:
			  return new MazePosition(row-1, col);
	  }
	return new MazePosition(row, col);
  }
  

  
  public boolean equals(Object x){
	  if (x instanceof MazePosition){
		  MazePosition y = (MazePosition) x;
		  return (this.row==y.row)&&(this.col==y.col);
	  }
	  else return false;  
  }
  
  public String toString(){
	  return "<"+row+","+col+">";
  }
  
 
}
