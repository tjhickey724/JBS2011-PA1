package jbs2011.taha.maze;

import java.util.ArrayList;
import java.util.HashMap;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class BasicPlayerByTaha extends MazePlayer {

	public BasicPlayerByTaha(String n) {
		super(n);
	}
	/**
	 * THIS PLAYER USES MAZE.JAVA
	 * This player basically looks towards the first gem it can see and starts moving towards it.
	 * This player lacks skill because he may move toward a gem very far away, without searching nearby.
	 * If no path to any jewel is available, the player stays in its place.
	 */
	public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		for (int i=0; i<jewels.size();i++){
			int endX = jewels.get(i).row*2-1;
			int endY = jewels.get(i).col*2-1;
			int begX = players.get(this.name).row*2-1;
			int begY = players.get(this.name).col*2-1;
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
		}
		return Direction.values()[1];
	}
}
