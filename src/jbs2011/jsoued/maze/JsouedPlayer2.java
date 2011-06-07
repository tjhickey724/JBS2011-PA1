package jbs2011.jsoued.maze;


import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class JsouedPlayer2 extends MazePlayer {
	
	private String n;

	public static void main(String[] args){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer("jsoued1"));
		  players.add(new jbs2011.jsoued.maze.JsouedPlayer2("jsoued2"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}
	
	public JsouedPlayer2(String n) {
		super(n);
		this.n=n;
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
		
		if (players.get(n).col == jewels.get(index).col){
			
			if (players.get(n).row > jewels.get(index).row){
				
				return Direction.NORTH;
		
			} else {
				
				return Direction.SOUTH;
			} 
			
		} else if (players.get(n).row == jewels.get(index).row){
			
			if (players.get(n).col > jewels.get(index).col){
				
				return Direction.WEST;
		
			} else {
				
				return Direction.EAST;
			}
			
		} else {
			
			Random rand = new Random();
			
			int choice = rand.nextInt(2);
			
			if (choice == 0){
				
				if (players.get(n).col > jewels.get(index).col){
					
					return Direction.WEST;
			
				} else {
					
					return Direction.EAST;
					
				}
				
			} else {
				
				if (players.get(n).row > jewels.get(index).row){
					
					return Direction.NORTH;
			
				} else {
					
					return Direction.SOUTH;
				}
			}
		}
	}	
}