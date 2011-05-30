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
