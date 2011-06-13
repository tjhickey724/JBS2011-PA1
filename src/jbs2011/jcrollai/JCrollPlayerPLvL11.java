package jbs2011.jcrollai;

import java.util.ArrayList;
import java.util.HashMap;
import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class JCrollPlayerPLvL11 extends MazePlayer 
{
	public static void main(String[] args)
	{
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new JCrollPlayerPLvL1("JCroll"));
		  players.add(new JCrollPlayerPLvL11("JCrollPLvL11"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
		  
	}
		  private String n;
		  public JCrollPlayerPLvL11(String n)
		  {
			  super(n);
			  this.n=n;
		  }
		
	/**
	 * This player moves towards the nearest jewel,
	 * but he doesn't take into account walls that would get in the way 
	 * or other players,  Superior to tjWest + tjRand. :D
	 */
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   ArrayList<MazePosition> teleJ,
			   MazeView maze)
	   {
		   if(getTeleJ() == true)
		   {
			   System.out.println("Old Pos: " + players.get(name).toString());
			   tele(players,jewels,teleJ,maze);
			   System.out.println("New Pos: " + players.get(name).toString());
			   System.out.println(getTeleJ());
		   }
		   
		   int mover=9000;
		   MazePosition index = jewels.get(0);
		   //Finds the maze the nearest jewel
		   for (int i=0; i<jewels.size(); i++)
		   {
			   int temp= Math.abs(jewels.get(i).col-players.get(n).col) + Math.abs(jewels.get(i).row- players.get(n).row);
			   
			 if(temp<mover)
			 {
				 mover = temp;
				 index = jewels.get(i);
			 }
		   }
		   if (index.col > players.get(n).col)
		   {
			   return Direction.NORTH;
		   }
		   else if (index.col < players.get(n).col)
		   {
			   return Direction.SOUTH;
		   }
		   else
		   {
			   if (index.row > players.get(n).row)
			   {
				   return Direction.EAST;
			   }
			   else 
			   {  
				   return Direction.WEST;
			   }
		   }
	   }
}
