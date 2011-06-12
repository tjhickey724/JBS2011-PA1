package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;

public interface MazeView {
      public int getWidth();
      public int getDepth();
	  public boolean canMove(MazePosition p, Direction d);
	  public boolean otherPlayer(HashMap<String, MazePosition>players, MazePosition f);
	  public String getOtherPlayer();
	  
}
