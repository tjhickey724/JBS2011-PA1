package MFieldAI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;

public class MFieldConfusedOne extends MazePlayer
{		
	private String n;
	private int initialX;
	private int initialY;
	private int finalX;
	private int finalY;
	private LinkedList<Integer>distToD;
	private int initialDistance=999;
		public static void main(String[] arrrrrgs)
		{
			  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
			  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
			  players.add(new MFieldConfusedOne("whereamI??"));
			  players.add(new RandomPlayer("tim2rand"));
			  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
		}

		public MFieldConfusedOne(String n) 
		{
			super(n);
			this.n=n;
		}
		/**
		 * This player simply picks a random direction and tries to move that way.
		 * It doesn't even check to see if the move is possible... and relies on the
		 * GameController to handle impossible move requests responsibly.... 
		 */
		   public Direction nextMove(
				   //you have a hash map of players
				   HashMap<String,MazePosition> players,
				   ArrayList<MazePosition> jewels,
				   MazeView maze) {
			   MazePosition temp=players.get(n);
			   initialX=temp.col;
			   initialY=temp.row;
			   
			//int pick = new Random().nextInt(Direction.values().length);
			//loop through all the positions of the 
			
			for(int i=0; i<jewels.size(); i++)
			{
				
				//this is the maze position of the starting player 
				//if the distance is less than the previous distance...
				//System.out.println(temp);
				//System.out.println(jewels.get(i));
				
					
					//abs value of 
					//this is distance from the initial position to the diamond
				int distance=Math.abs((jewels.get(i).col+jewels.get(i).row)-(initialX+initialY));
				//distToD.add(distance);
				//System.out.println("This is the distance: "+distance);
					if(distance<initialDistance)
					{
						initialDistance=distance;
						finalX=jewels.get(i).col;
						finalY=jewels.get(i).row;
					}
				
			}
				//now that you know the shortest distance....you want to head to the 
				//coordinate of the final position
				//now final X and final Y =
				//go north
				//if moving north moves you closer to the diamond
				//if the difference in Y decreases by moving a space upwards
			System.out.println("The initial coordinents: "+initialX+" "+initialY);
			System.out.println("The final coordinents: "+finalX+" "+finalY);
			//System.out.println(initialDistance);
				if(finalY>initialY)
				{
					
					System.out.println("goingNorth!");
					//initialY=initialY+1;
					temp.row=temp.row+1;
					
					return Direction.NORTH;
				}
				else if(finalY<initialY)
				{
					System.out.println("GoingSouth!");
					//initialY=initialY-1;
					temp.row=temp.row-1;
					return Direction.SOUTH;
				}
				else if(finalX<initialX)
				{
					System.out.println("goingEast!");
					//initialX=initialX+1;
					temp.col=temp.col-1;
					return Direction.EAST;
				}
				else
				{
					System.out.println("goingWest!");
					//initialY=initialY-1;
					temp.col=temp.col+1;
					return Direction.WEST;
				}
			//return Direction.WEST;
			
	}

		   public Direction nextMove(HashMap<String, MazePosition> players, 
		 			ArrayList<MazePosition> jewels, ArrayList<MazePosition> rubies, MazeView maze){
		 		return this.nextMove(players, jewels, maze);
		 	}

}
