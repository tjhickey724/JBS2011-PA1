
package jbs2011.tjhickey.maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class RandomPlayer extends MazePlayer {

	public RandomPlayer(String n) {
		super(n);
	}
	/**
	 * This player simply picks a random direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */

	public int nextMove(HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, MazeView maze,
			ArrayList<MazePosition> bombs, ArrayList<MazePosition> chainlinks) {
		// TODO Auto-generated method stub
		//1=north 2=south 3=east 4=west 5=bomb 6=hookshot
		
		//checks if theres another player in vacinity, and then bombs them!
		if(maze.otherPlayer(players, players.get(name))&& this.bombsAway()){
			this.setOtherPlater(maze.getOtherPlayer());
			return 5;
		}
		//checks if there are jewels to be hookshotted and then acts.
		for(int x=0; x<jewels.size(); x++){
			if(jewels.get(x).col==players.get(name).col){
				if(jewels.get(x).row>players.get(name).row){
					if(this.chains()>=jewels.get(x).row-players.get(name).row){
						this.setPointShot(jewels.get(x));
						this.hookShot(this.chains());
						return 6;
					}
				}else{
					if(this.chains()>=players.get(name).row-jewels.get(x).row){
						this.setPointShot(jewels.get(x));
						this.hookShot(this.chains());
						return 6;
					}
				}
			}else if(jewels.get(x).row==players.get(name).row){
				if(jewels.get(x).col>players.get(name).col){
					if(this.chains()>=jewels.get(x).col-players.get(name).col){
						this.setPointShot(jewels.get(x));
						this.hookShot(this.chains());
						return 6;
					}
				}else{
					if(this.chains()>=players.get(name).col-jewels.get(x).col){
						this.setPointShot(jewels.get(x));
						this.hookShot(this.chains());
						return 6;
					}
				}
			}
		}
		Random rand=new Random();
		if(rand.nextInt(4)+1==1){
			return 1;
		}else if(rand.nextInt(4)+1==2){
			return 2;
		}else if(rand.nextInt(4)+1==3){
			return 3;
		}else{
			return 4;
		}
	}
}
