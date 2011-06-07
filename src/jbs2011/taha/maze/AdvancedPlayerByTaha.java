package jbs2011.taha.maze;

import java.util.ArrayList;
import java.util.HashMap;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class AdvancedPlayerByTaha extends MazePlayer {

	public AdvancedPlayerByTaha(String n) {
		super(n);
	}
	/**
	 * THIS PLAYER USES MAZE.JAVA
	 * This player is very smart. Finds the closest gem without crossing any walls and moves to obtain it.
	 * If no path to any jewel is available, the player stays in its place.
	 */
	public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		MazePosition myGem = findClosestLegalGem(players.get(this.name), jewels, maze);
			int endX = myGem.row*2-1;
			int endY = myGem.col*2-1;
			int begX = players.get(this.name).row*2-1;
			int begY = players.get(this.name).col*2-1;
			if (endX == begX && endY ==begY){
				return Direction.values()[1];
			}
			if(Maze.solve(begX,begY,endX,endY,maze)==true){
				int finX = Maze.trace(begX,begY,endX,endY,maze).get(0);
				int finY = Maze.trace(begX,begY,endX,endY,maze).get(1);
				if (finX>begX){
					return Direction.values()[2];
				}
				if (finX<begX){
					return Direction.values()[3];
				}
				if (finY>begY){
					return Direction.values()[0];
				}
				if (finY<begY){
					return Direction.values()[1];
				}
			}
		return Direction.values()[1];
	}
	
	
	//Method to find the closest legal gem's position
	public static MazePosition findClosestLegalGem(MazePosition self, ArrayList<MazePosition> jewels, MazeView maze){
		MazePosition safety = new MazePosition(self.row,self.col);
		int min = maze.getDepth()*maze.getWidth();
		for (int i=0; i<jewels.size();i++){
			int endX = jewels.get(i).row*2-1;
			int endY = jewels.get(i).col*2-1;
			int begX = self.row*2-1;
			int begY = self.col*2-1;
			if(Maze.solve(begX,begY,endX,endY,maze)==true){
				if(Maze.trace(begX,begY,endX,endY,maze).size()<min){
					min = Maze.trace(begX,begY,endX,endY,maze).size();
					safety = new MazePosition(jewels.get(i).row, jewels.get(i).col);
				}
			}	
		}
		return safety;
	}
}
