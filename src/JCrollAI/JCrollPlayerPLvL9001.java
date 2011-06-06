package JCrollAI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;

public class JCrollPlayerPLvL9001 extends MazePlayer
{
	public static void main(String[] args)
{
	  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
	  //players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
	  players.add(new RandomPlayer("tim2rand"));
	  players.add(new JCrollPlayerPLvL1("JCroll"));
	  players.add(new JCrollPlayerPLvL9001("JCrollSSJ"));
	  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	  
}
	  private String n;
	  private  MazePosition opponent; 
	  public JCrollPlayerPLvL9001(String n)
	  {
		  super(n);
		  this.n=n;
	  }
	
/**
* This player moves towards the nearest jewel, taking into account the other players position
* he doesn't however take into account walls that would get in the way 
*   Superior to tjWest + tjRand. :D , has a slight advantage over JCrollPlayerPLvL1
*/
 public Direction nextMove(
		   HashMap<String,MazePosition> players,
		   ArrayList<MazePosition> jewels,
		   MazeView maze)
 		{
	 
	   //Finds the MazePosition of the opponent 
	   Iterator itr = players.keySet().iterator();
	   String Pindex = (String) itr.next();
	   
	   if (n == Pindex)
	   {
		   opponent = players.get(itr.next()); 
	   }
	   else
	   {
		   opponent = players.get(Pindex); 
	   }
	   
	   int mover=9000;
	   MazePosition index = jewels.get(0);
	   //Finds the mazeposition of the nearest jewel
	   for (int i=0; i<jewels.size(); i++)
	   {
		   int temp= Math.abs(jewels.get(i).col-players.get(n).col) + Math.abs(jewels.get(i).row- players.get(n).row);
		   int opponentTemp= Math.abs(jewels.get(i).col-opponent.col) + Math.abs(jewels.get(i).row- opponent.row);

		   
		 if(temp<mover && temp < opponentTemp)
		 {
			//Sees if the Jewel is closer to this player than the other player
			 
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
