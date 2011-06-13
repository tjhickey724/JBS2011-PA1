package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Axe is an Item.
 * 
 * Axe can take jewels from other players who do not have an axe.
 * @author Jackie
 *
 */
public class Axe extends Item {
	String name;

	public Axe(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	/**
	 * See if there is a person in direction d
	 * If so, and they don't have an axe steal a jewel from them.
	 */
	public void action(HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, MazeView maze, MazePosition mazeXY,
			MazePlayer currPlayer, MazePlayer oppPlayer) {
		
			if(oppPlayer.backpack.isEmpty()){
				maze.score.put(oppPlayer.name, maze.score.get(oppPlayer)-5);
				maze.score.put(currPlayer.name, maze.score.get(currPlayer)+5);
			}
	}
	

}
