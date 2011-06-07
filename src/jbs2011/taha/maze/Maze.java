package jbs2011.taha.maze;

import java.util.*;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class Maze {

	//fields
	private int m;
	private int n;
	private LinkedList<LinkedList<Integer>> maze;
	
	
	//constructors
	public Maze(int n, int m){
		this.m = m;
		this.n = n;
	}
	
	//methods
	public void load(String key){
		LinkedList<LinkedList<Integer>> maze = new LinkedList<LinkedList<Integer>>();
		int temp;
		int i = 0;
		for(int k=-1; i<key.length() && k<n; k++){
			LinkedList<Integer> row = new LinkedList<Integer>();
			for(int j=0; j<m; j++){
				if(key.charAt(i)=='0'){
					temp = 0;
				}
				else{
					temp = 1;
				}
				row.add(temp);
				i++;
			}
			maze.add(row);
		}
		this.maze = maze;
	}
	
	public int getM(){
		return this.m;
	}
	
	public int getN(){
		return this.n;
	}
	
	public LinkedList<Integer> getRow(int i){
		return this.maze.get(i-1);
	}
	
	
	public void display(){
		for(int i = 1; i<=n; i++){
			for(int j=0; j<this.getRow(i).size(); j++){
				System.out.print(this.getRow(i).get(j));
			}
			System.out.println();
		}
	}
	
	
	//This function returns a linked list of integers (sequential x and y coordinates) of open neighboring cells
	public static LinkedList<Integer> openNeighbors(int x, int y, MazeView board){
		LinkedList<Integer> openNeighbors = new LinkedList<Integer>();
		MazePosition p = new MazePosition((x+1)/2,(y+1)/2);
		for (Direction d:Direction.values()){
			if (board.canMove(p, d) == true && d!=Direction.valueOf("CENTER")){
				int i = 0;
				int j = 0;
				if (d==Direction.valueOf("NORTH")){
					j = 2;
				}
				if (d==Direction.valueOf("SOUTH")){
					j = -2;
				}
				if (d==Direction.valueOf("EAST")){
					i = 2;
				}
				if (d==Direction.valueOf("WEST")){
					i = -2;
				}				
				openNeighbors.add(x+i);
				openNeighbors.add(y+j);
			}
		}
		return openNeighbors;
		
		
	}
	
	//This method checks if two Linked Lists of Integers (storing successive x and y coordinates
	//have any overlapping set of coordinates.
	public static boolean overlapCheck(LinkedList<Integer> one, LinkedList<Integer> two){
		int checkDigit = 0;
		boolean check;
			for (int i = 0; i<one.size();){
				for (int j = 0; j<two.size();){
					if (one.get(i)==two.get(j) && one.get(i+1)==two.get(j+1)){
						checkDigit = 1;
					}
					j++;
					j++;
				}
				i++;
				i++;
			}
		if (checkDigit == 1){
			check = true;
		}
		else{
			check = false;
		}
		return check;
	}
	
	public static LinkedList<Integer> openNeighborsToAll(LinkedList<Integer> input, MazeView board){
		LinkedList<Integer> output = new LinkedList<Integer>();
		for (int x=0; x<input.size();){
			output.addAll(openNeighbors(input.get(x),input.get(x+1),board));
			x=x+2;
		}
		return output;
	}

	//returns true id a solution from one set of coordinates to another exists
	public static boolean solve(int begX, int begY, int endX, int endY, MazeView board){
		boolean answer = false;
		LinkedList<Integer> traversed = new LinkedList<Integer>();
		LinkedList<Integer> temp_1 = new LinkedList<Integer>();
		LinkedList<Integer> end = new LinkedList<Integer>();
		LinkedList<Integer> home = new LinkedList<Integer>();
		home.add(begX);
		home.add(begY);
		end.add(endX);
		end.add(endY);
		while (home.size()!=0 && overlapCheck(home, end)!=true){
			traversed.addAll(home);
			temp_1.addAll(openNeighborsToAll(home, board));
			home.clear();
			home.addAll(filter(traversed, temp_1));
		}
		if (overlapCheck(home, end)==true){
			answer = true;
		}
		return answer;
	}
	
	// data-structure pointer error avoider (machine specific problem) 
	public static LinkedList<Integer> same (LinkedList<Integer> same){
		LinkedList<Integer> copy = new LinkedList<Integer>();
		copy.addAll(same);
		return copy;
	}
	
	
	//returns a linked list of coordinates that map a solvable path from one place to another on a maze
	public static LinkedList<Integer> trace(int begX, int begY, int endX, int endY, MazeView board){
		LinkedList<Integer> path = new LinkedList<Integer>();
		LinkedList<Integer> traversed = new LinkedList<Integer>();
		LinkedList<Integer> temp_1 = new LinkedList<Integer>();
		LinkedList<Integer> end = new LinkedList<Integer>();
		LinkedList<Integer> home = new LinkedList<Integer>();
		LinkedList<LinkedList<Integer>> levelRecorder = new LinkedList<LinkedList<Integer>>();
		home.add(begX);
		home.add(begY);
		end.add(endX);
		end.add(endY);
		levelRecorder.add(home);
		while (home.size()!=0 && overlapCheck(home, end)!=true){
			traversed.addAll(home);
			temp_1.addAll(openNeighborsToAll(home, board));
			home.clear();
			home.addAll(filter(traversed, temp_1));
			levelRecorder.add(same(home));
		}
		int reverseCounter = levelRecorder.size()-1;
		home.clear();
		home.add(begX);
		home.add(begY);
		path.addAll(end);
		while(overlapCheck(home,end)!=true){
			for (int i = 0; i<levelRecorder.get(reverseCounter).size();){
				LinkedList<Integer> temp = new LinkedList<Integer>();
				LinkedList<Integer> temp_neighbors = openNeighborsToAll(end, board);
				temp.add(levelRecorder.get(reverseCounter).get(i));
				temp.add(levelRecorder.get(reverseCounter).get(i+1));
				if(overlapCheck(temp,temp_neighbors)==true){
					end.clear();
					end.addAll(temp);
					path.addAll(temp);
				}
				i = i + 2;
			}
			reverseCounter=reverseCounter-1;
		}
		Collections.reverse(path);
		for(int i = 0; i < path.size();){
			int y = path.get(i);
			int x = path.get(i+1);
			path.remove(i);
			path.remove(i);
			path.add(i, x);
			path.add(i+1, y);
			i = i+2;
		}
		path.remove(0);
		path.remove(0);
		return path;
	}
	
	//This method filters a set of coordinates, removing all repeats and those that have already been traversed
	public static LinkedList<Integer> filter(LinkedList<Integer> traversed, LinkedList<Integer> mixture){
		LinkedList<Integer> filterate = new LinkedList<Integer>();
		LinkedList<Integer> current = new LinkedList<Integer>();
		for(int i = 0; i<mixture.size();){
			current.add(mixture.get(i));
			current.add(mixture.get(i+1));
			if (overlapCheck(filterate, current)!=true && overlapCheck(current, traversed)!=true){
				filterate.addAll(current);
			}
			current.clear();
			i = i +2;
		}
		return filterate;
	}
	
}
