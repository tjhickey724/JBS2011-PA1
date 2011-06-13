package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;

public interface MazeView {
	  public HashMap<String,Integer> score = null;
	  public HashMap<MazePosition, ArrayList<MazePlayer>> positionPlayer=null;
      public int getWidth();
      public int getDepth();
	  public boolean canMove(MazePosition p, Direction d);
	  
}
