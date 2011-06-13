package jbs2011.mfieldai;


import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;


import java.util.ArrayList;
import java.util.LinkedList;

import java.util.HashMap;

public class MFieldDOMINATOR extends MazePlayer
{		
	private String n;
	private int initialX;
	private int initialY;
	private int finalX;
	private int finalY;

		public static void main(String[] arrrrrgs)
		{
			  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		
			  //players.add(new MFieldDOMINATOR("Mike1"));
			  players.add(new mineAvoider("mineAvoider"));
			  players.add(new RandomPlayer("tim2Random"));
			  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
		}

		public MFieldDOMINATOR(String n) 
		{
			super(n);
			this.n=n;
		}
		
		   public Direction nextMove(
				   //you have a hash map of players
				   HashMap<String,MazePosition> players,
				   ArrayList<MazePosition> jewels,
				   MazeView maze, ArrayList<MazePosition> mines) {
			   //MazePosition player=players.get(n);
			   
			   
			//int pick = new Random().nextInt(Direction.values().length);
			   
			//loop through all the positions of the 
			int initialDistance=9999;
			//gets the closest jewel
			MazePosition closestJewel=jewels.get(0);
			for(int i=0; i<jewels.size(); i++)
			{
				
					
					//abs value of 
					//this is distance from the initial position to the diamond
				int distance=Math.abs((jewels.get(i).col-players.get(n).col)+Math.abs(jewels.get(i).row-players.get(n).row));
			
					//reset the distance
				
					if(distance<initialDistance)
					{
						initialDistance=distance;
						
						closestJewel=jewels.get(i);
						
					}
					
				
			}
				initialX=players.get(n).row;
				finalX=closestJewel.row;
			   	initialY=players.get(n).col;
				finalY=closestJewel.col;
				
			
				
				if(finalY>initialY)
				{
					//System.out.println(closestJewel.col+" "+closestJewel.row);
					//System.out.println(players.get(n).col+" "+players.get(n).row);
					
					//System.out.println("goingNorth!");
					
					
					
					return Direction.NORTH;
				}
				else if(finalY<initialY)
				{
					//System.out.println("GoingSouth!");
					
				
					return Direction.SOUTH;
				}
				else{
					
				
				if(finalX>initialX)
				{
					//System.out.println("goingEast!");
					
				
					return Direction.EAST;
				}
				else
				{
					//System.out.println("goingWest!");
				
				
					return Direction.WEST;
				}
				}
		
			
	}
}
