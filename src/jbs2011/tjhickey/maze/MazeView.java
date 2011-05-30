package jbs2011.tjhickey.maze;

public interface MazeView {
      public int getWidth();
      public int getDepth();
	  public boolean canMove(MazePosition p, Direction d);
	  
}
