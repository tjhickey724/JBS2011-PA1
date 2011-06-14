package jbs2011.jsoued.maze;


import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;

//This player looks for the nearest jewel without taking into account the walls on its way. Then it calculates
//if is better to start moving through columns or through walls depending on the number of walls on both 
//pathways. Finally it moves towards that jewel avoiding the pathway with the most walls but trying to pass 
//through walls as many times as needed.
public class JsouedPlayer extends MazePlayer {
	
	private String n;
	
	public static void main(String[] args){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer("jsoued1"));
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer2("jsoued2"));
		  players.add(new jbs2011.tjhickey.maze.RandomPlayer("Rand"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tjhickey"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}
	
	public JsouedPlayer(String n) {
		super(n);
		this.n = n;
	}
	
	public Direction nextMove(HashMap<String,MazePosition> players, ArrayList<MazePosition> jewels, MazeView maze) {
		
		ArrayList<Integer> shortest = new ArrayList <Integer>();
		
		for(int i = 0; i < jewels.size(); i++){
			
			int rowdistance = jewels.get(i).row - players.get(n).row;
			
			if (rowdistance < 0){
				
				rowdistance = rowdistance * (-1);
			}
			
			int coldistance = jewels.get(i).col - players.get(n).col;
			
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
		
		int first = 0;
		int last = 0;
		
		int row_wall_count = 0;
		int col_wall_count = 0;
		
		boolean way = true;
		
		if (players.get(n).col > jewels.get(index).col){
			
			first = jewels.get(index).col;
			last = players.get(n).col;
			way = false;
			
		} else {
			
			first = players.get(n).col;
			last = jewels.get(index).col;
			way = true;
		}
		
		int z = first;
		
		for (int k = first; k < last; k++){
			
			if (way == true){
				
				if (!maze.canMove(new MazePosition(players.get(n).row, k), Direction.EAST)){
					
					col_wall_count++;
				}
	
			} else {
				
				if (!maze.canMove(new MazePosition(players.get(n).row, z), Direction.WEST)){
					
					col_wall_count++;
				}
			}
			
			if (z > 0){
				
				z--;
			}	
		}
		
		if (players.get(n).row > jewels.get(index).row){
			
			first = jewels.get(index).row;
			last = players.get(n).row;
			way = false;
			
		} else {
			
			first = players.get(n).row;
			last = jewels.get(index).row;
			way = true;
		}
		
		int a = first;
		
		for (int l = first; l < last; l++){
			
			if (way == true){
				
				if (!maze.canMove(new MazePosition(l, players.get(n).col), Direction.SOUTH)){
					
					row_wall_count++;
				}
	
			} else {
				
				if (!maze.canMove(new MazePosition(a, players.get(n).col), Direction.NORTH)){
					
					row_wall_count++;
				}
			}
			
			if (a > 0){
				
				a--;
			}	
		}
		
		if (row_wall_count < col_wall_count){
			
			if (players.get(n).col < jewels.get(index).col){
				
				return Direction.NORTH;
		
			} else if (players.get(n).col > jewels.get(index).col){
				
				return Direction.SOUTH;
				
			} else {
				
				if (players.get(n).row < jewels.get(index).row){
					
					return Direction.EAST;
			
				} else {
					
					return Direction.WEST;
				}
			}
			
		} else {
			
			if (players.get(n).row < jewels.get(index).row){
				
				return Direction.EAST;
		
			} else if (players.get(n).row > jewels.get(index).row){
				
				return Direction.WEST;
				
			} else {
				
				if (players.get(n).col < jewels.get(index).col){
					
					return Direction.NORTH;
			
				} else {
					
					return Direction.SOUTH;
				}
			} 
		}
	}	
}