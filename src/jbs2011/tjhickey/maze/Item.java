package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * This is the abstract Item class.
 * It formulates each Item to only have one action, and
 * to have a name (if necessary).
 * @author Jackie
 *
 */
public abstract class Item {
	public String name;

	public Item(){
		
	}
	
	public Item (String name){
		this.name = name;
	}
	
	public abstract void action(
			HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze,
			   MazePosition mazeXY,
			   MazePlayer currentplayer,
			   MazePlayer oppPlayer);
}
