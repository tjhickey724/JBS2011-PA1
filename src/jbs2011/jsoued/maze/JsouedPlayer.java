package jbs2011.jsoued.maze;


import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;


public class JsouedPlayer extends MazePlayer {
	

	public static void main(String[] args){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer("jsoued1"));
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer2("jsoued2"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}
	
	public JsouedPlayer(String n) {
		super(n);
	}
	
	public Direction nextMove(HashMap<String,MazePosition> players, ArrayList<MazePosition> jewels, MazeView maze) {
		
		ArrayList<Integer> shortest = new ArrayList <Integer>();
		
		for(int i = 0; i < jewels.size(); i++){
			
			int rowdistance = jewels.get(i).row - players.get(super.name).row;
			
			if (rowdistance < 0){
				
				rowdistance = rowdistance * (-1);
			}
			
			int coldistance = jewels.get(i).col - players.get(super.name).col;
			
			if (coldistance < 0){
				
				coldistance = coldistance * (-1);
			}
			
			int distance = rowdistance + coldistance;
			
			shortest.add(distance);
		}
		
		int shortest2 = shortest.get(0);
		
		int index = 0;
		
		for (int j = 1; j < shortest.size(); j++){
			
			if (shortest.get(j) < shortest2){
				
				shortest2 = shortest.get(j);
				
				index = j;
			}	
		}
		
		if (players.get(super.name).row == jewels.get(index).row){
			
			if (players.get(super.name).col > jewels.get(index).col){
				
				return Direction.WEST;
		
			} else if (players.get(super.name).col < jewels.get(index).col){
				
				return Direction.EAST;
				
			} else {
				
				return Direction.CENTER;
			}
			
		} else if (players.get(super.name).col == jewels.get(index).col){
			
			if (players.get(super.name).row > jewels.get(index).row){
				
				return Direction.NORTH;
		
			} else if (players.get(super.name).row < jewels.get(index).row){
				
				return Direction.SOUTH;
				
			} else {
				
				return Direction.CENTER;
			}
			
		} else {
			
			int first = 0;
			int last = 0;
			
			int row_wall_count = 0;
			int col_wall_count = 0;
			
			boolean way = true;
			
			if (players.get(super.name).col > jewels.get(index).col){
				
				first = jewels.get(index).col;
				last = players.get(super.name).col;
				way = false;
				
			} else {
				
				first = players.get(super.name).col;
				last = jewels.get(index).col;
				way = true;
			}
			
			int z = first;
			
			for (int k = first; k < last; k++){
				
				if (way == true){
					
					if (!maze.canMove(new MazePosition(players.get(super.name).row, k), Direction.EAST)){
						
						col_wall_count++;
					}
		
				} else {
					
					if (!maze.canMove(new MazePosition(players.get(super.name).row, z), Direction.WEST)){
						
						col_wall_count++;
					}
				}
				
				if (z > 0){
					
					z--;
				}	
			}
			
			if (players.get(super.name).row > jewels.get(index).row){
				
				first = jewels.get(index).row;
				last = players.get(super.name).row;
				way = false;
				
			} else {
				
				first = players.get(super.name).row;
				last = jewels.get(index).row;
				way = true;
			}
			
			int a = first;
			
			for (int l = first; l < last; l++){
				
				if (way == true){
					
					if (!maze.canMove(new MazePosition(l, players.get(super.name).col), Direction.SOUTH)){
						
						row_wall_count++;
					}
		
				} else {
					
					if (!maze.canMove(new MazePosition(a, players.get(super.name).col), Direction.NORTH)){
						
						row_wall_count++;
					}
				}
				
				if (a > 0){
					
					a--;
				}	
			}
			
			if (row_wall_count > col_wall_count){
				
				if (players.get(super.name).col > jewels.get(index).col){
					
					return Direction.WEST;
			
				} else {
					
					return Direction.EAST;
					
				}
				
			} else {
				
				if (players.get(super.name).row > jewels.get(index).row){
					
					return Direction.NORTH;
			
				} else {
					
					return Direction.SOUTH;
					
				} 
			}
		}
	}	
}