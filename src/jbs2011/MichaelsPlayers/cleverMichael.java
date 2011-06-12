package jbs2011.michaelsPlayers;
import jbs2011.tjhickey.maze.*;

import java.util.ArrayList;
import java.util.HashMap;

public class cleverMichael extends MazePlayer {
	ArrayList<Direction> directions;
	
	public cleverMichael(String n) {
		super(n);
	}

	public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		int shortRoute=100;
		int hold=0;
		MazePosition route=null;
		MazePosition tester=players.get(name);
		//find the shortest distance to a jewel by subtracting row and column from jewel-player. Takes this route.
		for(int x=0; x<jewels.size(); x++){
			hold=players.get(name).row-jewels.get(x).row+
			players.get(name).col-jewels.get(x).col;
			if(hold<shortRoute){
				shortRoute=hold;
				route=jewels.get(x);
			}
		}//finds the route based if row/col alligns or not.
		if(tester.row==route.row){
			if(tester.col>route.col){
				if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
					return Direction.NORTH;
				}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
					return Direction.EAST;
				}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
					return Direction.WEST;
				}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
					return Direction.SOUTH;
				}
			}
			if(tester.col<route.col){
				if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
					return Direction.SOUTH;
				}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
					return Direction.EAST;
				}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
					return Direction.WEST;
				}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
					return Direction.NORTH;
				}
			}
		}
		if(tester.col==route.col){
			if(tester.row>route.row){
				if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
					return Direction.EAST;
				}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
					return Direction.NORTH;
				}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
					return Direction.SOUTH;
				}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
					return Direction.WEST;
				}
			}if(tester.row<route.row){
				if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
					return Direction.WEST;
				}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
					return Direction.NORTH;
				}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
					return Direction.SOUTH;
				}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
					return Direction.EAST;
				}
			}
		}
		if(tester.row>route.row){
			if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
				return Direction.EAST;
			}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
				return Direction.NORTH;
			}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
				return Direction.SOUTH;
			}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
				return Direction.WEST;
			}
		}if(tester.row<route.row){
			if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
				return Direction.WEST;
			}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
				return Direction.NORTH;
			}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
				return Direction.SOUTH;
			}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
				return Direction.EAST;
			}
		}
		if(tester.col>route.col){
			if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
				return Direction.NORTH;
			}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
				return Direction.EAST;
			}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
				return Direction.WEST;
			}if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
				return Direction.SOUTH;
			}
		}
		if(tester.col<route.col){
			if(maze.canMove(tester, Direction.SOUTH)&&tester.col+1<maze.getDepth()){
				return Direction.SOUTH;
			}if(maze.canMove(tester, Direction.EAST)&&tester.row-1>0){
				return Direction.EAST;
			}if(maze.canMove(tester, Direction.WEST)&&tester.row+1<maze.getWidth()){
				return Direction.WEST;
			}if(maze.canMove(tester, Direction.NORTH)&&tester.col-1>0){
				return Direction.NORTH;
			}
		}
		return Direction.NORTH;
	}
}