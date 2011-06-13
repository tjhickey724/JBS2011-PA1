package jbs2011.tjhickey.maze;

import java.util.ArrayList;


/**
 * This class allows one to compute the distance from any MazePosition to any set
 * of MazePositions. It also allows one to choose the direction to move in to get to
 * one of the target positions most quickly.  The constructor allows one to assign a penalty
 * for trying to move through a wall, but that feature is currently unimplemented.
 * @author tim
 *
 */
public class MazeDist {
	private int dist[][];
	private MazeView maze;
	
	/**
	 * This calculates the minimum distance from any point in the maze to any of the target points
	 * listsed in the ArrayList of MazePositions.  The penalty field is currently unused, but it
	 * corresponds to the cost of going through a wall...
	 * @param maze
	 * @param target
	 */
	public MazeDist(MazeView maze,ArrayList<MazePosition> target){
		this.maze=maze;
		initDist(target);
		findMinDist();
	//	System.out.println(this.toString()+"\n\n");

	}
	
	public MazeDist(MazeView maze,MazePosition p){
		ArrayList<MazePosition> target = new ArrayList<MazePosition>();
		target.add(p);
		this.maze=maze;
		initDist(target);
		findMinDist();
	//	System.out.println(this.toString()+"\n\n");

	}
	
	private void initDist(ArrayList<MazePosition> target){
		
		int w = maze.getWidth();
		int d = maze.getDepth();
		dist = new int[w][d];
		for(int i=0;i<w;i++)
			for(int j=0;j<d;j++){
				dist[i][j]=-1;
			}
		for (MazePosition p:target){
			dist[p.row][p.col]=0;
		}
		System.out.println(this);
		System.out.println("after init\n");
	}
	
	private void findMinDist(){
		MazePosition p = new MazePosition(0,0);
		int w = maze.getWidth();
		int d = maze.getDepth();
		for(int k=1;k<w*d;k++){
			for(int i=0;i<w;i++)
				for (int j=0;j<d;j++) {
					p.row=i; p.col=j;
					if (dist[i][j]==-1){
						if (maze.canMove(p,Direction.NORTH)&&(j+1<d)&&(dist[i][j+1]==k-1)){
							dist[i][j]=k; continue;
						}
						if(maze.canMove(p,Direction.SOUTH)&&(j>0)&&(dist[i][j-1]==k-1)){
							dist[i][j]=k; continue;
						}
						
						if(maze.canMove(p,Direction.WEST)&&(i-1>0)&&(dist[i-1][j]==k-1)){
							dist[i][j]=k; continue;
						}	
						
						if(maze.canMove(p,Direction.EAST)&&(i+1<w)&&(dist[i+1][j]==k-1)){
							dist[i][j]=k; continue;
						}
					}
				}
			//System.out.println("maze after step "+k+" is\n"+this+"\n");
		}
	}
				
	/**
	 * This returns the minimum distance from the mazeposition to one of the target positions
	 * @param p
	 * @return
	 */
	public int minDist(MazePosition p) {
		if ((p.row >=0) && (p.row < maze.getWidth()) && (p.col>=0)&& (p.col < maze.getDepth()))
			return dist[p.row][p.col];
		else
			return -1;
	}
	
	/**
	 * return a direction that leads most quickly to one of the target positions.
	 * If no target position is reachable from the current position it returns CENTER
	 * @param p
	 * @return
	 */
	public Direction goFrom(MazePosition p){
		int currentMin = maze.getWidth()*maze.getDepth();
		Direction minDir = Direction.CENTER;
		for (Direction d:Direction.values()){
			if (maze.canMove(p, d)){
				int distance = minDist(p.move(d));
				if ( (distance !=-1) && (distance < currentMin)) {
					currentMin =distance;
					minDir = d;
//					System.out.println("space moving "+d+" = "+distance);
				}
			}
					
		}

//		System.out.println("try to move in direction "+minDir);
		return minDir;
			
		
	}
	
	/**
	 * This is a debugging routine which prints out a string representation of the minDist array
	 */
	public String toString(){
		java.lang.StringBuffer buf = new StringBuffer();
		int w = maze.getWidth();
		int d = maze.getDepth();
		for (int j = d-1; j>=0 ; j--){
			for (int i=0; i<w ; i++){
				buf.append(dist[i][j]+" ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

}
