package jbs2011.tjhickey.maze;

public class MazeWall {
  private boolean isOpen = false;
  public MazeWall(boolean isOpen){
	  this.isOpen=isOpen;
  }
  public boolean getIsOpen()
  {
	  return this.isOpen;
  }
  
  public void setIsOpen(boolean val){
	  this.isOpen = val;
  }
}
