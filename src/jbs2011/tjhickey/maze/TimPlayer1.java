package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This defines a player that calculates the shortest path to the nearest jewel
 * and then takes one step in that direction! 
 * @author tim
 *
 */
public class TimPlayer1 extends MazePlayer {

	int[][] shortestPath;
	
	public TimPlayer1(String n) {
		super(n);
	}
	
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   int w = maze.getWidth();
		   int d = maze.getDepth();
		   shortestPath = new int[maze.getWidth()][maze.getDepth()];
		   for (int i=0;i<w;i++)
			   for(int j=0;j<d;j++){
				   shortestPath[i][j]=-1;
			   }
		   for (MazePosition p:jewels){
			   shortestPath[p.row][p.col]=0;
		   }
		   for (int k=0;k<w+d;k++){
			   for(int i=0;i<w;i++)
				   for(int j=0;j<d;j++)
					 if (shortestPath[i][j] == -1)
				     {
					   MazePosition p = new MazePosition(i,j);
					   if (maze.canMove(p,Direction.NORTH) && shortestPath[i][j+1]==k)
						   shortestPath[i][j]=k+1;
					   if (maze.canMove(p,Direction.SOUTH) && shortestPath[i][j-1]==k)
						   shortestPath[i][j]=k+1;
					   if (maze.canMove(p,Direction.EAST) && shortestPath[i+1][j]==k)
						   shortestPath[i][j]=k+1;
					   if (maze.canMove(p,Direction.WEST) && shortestPath[i-1][j]==k)
						   shortestPath[i][j]=k+1;

				   }
			   /*
			   // print out table
			   System.out.println("k="+k);
			   for (int j=d-1;j>=0;j--){
				   for (int i=0;i<w;i++){
					   System.out.print("\t"+shortestPath[i][j]);
				   }
				   System.out.println();
			   }
			   System.out.println("\n--------\n\n");
			   */
		   }/*
		   // print out table
		   for (int j=d-1;j>=0;j--){
			   for (int i=0;i<w;i++){
				   System.out.print("\t"+shortestPath[i][j]);
			   }
			   System.out.println("");
		   }
		   System.out.println("\n*****\n\n");
		   */
		   // move in the direction with the shortestPath
		   MazePosition p = players.get(this.name);
		   if (shortestPath[p.row][p.col]==-1) {
			   // if you can not reach a jewel from here, then move in a random direction
			   // and possibly break through a wall!
				int pick = new Random().nextInt(Direction.values().length);
			    return Direction.values()[pick];
		   }else {
			   // otherwise find the direction which is closest to the nearest jewel
			   // and return that value ..
			   int minDist = 100000;
			   Direction d1=Direction.NORTH;
			   int i = p.row;
			   int j = p.col;
			   if (maze.canMove(p,Direction.NORTH) && shortestPath[i][j+1]<minDist){
				   d1=Direction.NORTH; minDist = shortestPath[i][j+1];
			   }
			   if (maze.canMove(p,Direction.SOUTH) && shortestPath[i][j-1]<minDist){
				   d1=Direction.SOUTH; minDist = shortestPath[i][j-1];
			   }
			   if (maze.canMove(p,Direction.EAST) && shortestPath[i+1][j]<minDist){
				   d1=Direction.EAST; minDist = shortestPath[i+1][j];
			   }
			   if (maze.canMove(p,Direction.WEST) && shortestPath[i-1][j]<minDist){
				   d1=Direction.WEST; minDist = shortestPath[i-1][j];
			   }
			   return d1;
		   }
	}
}
