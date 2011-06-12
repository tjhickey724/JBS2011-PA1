package jbs2011.tjhickey.maze;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class HumanPlayer extends MazePlayer {

	public HumanPlayer(String n) {
		super(n);
	}


	public int nextMove(HashMap<String, MazePosition> players,
			ArrayList<MazePosition> jewels, MazeView maze,
			ArrayList<MazePosition> bombs, ArrayList<MazePosition> chainlinks) {
		//1=north 2=south 3=east 4=west 5=bomb 6=hookshot 7=howManyBombs 8=howManyChains
		int hold=0;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a number1-6: 1=north 2=south 3=east 4=west 5=bomb 6=hookshot");
		hold=input.nextInt();
		switch(hold){
		case(5)://checks if it can bomb a player if it is close by and if it has a bomb.
			if(maze.otherPlayer(players, players.get(name))&& this.bombsAway()){
				this.setOtherPlater(maze.getOtherPlayer());
				return 5;
			}else{
				System.out.println("You had no bomb or no player was next to you: Default move North.");
				return 1;
			}
		case(6)://checks if there is a jewel in the vacinity that the hookshot can reach.
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
			System.out.println("You have no chains or a jewel is not close enough:Default move South.");
			return 2;
		case(7): System.out.println(this.numberBombs());
		case(8):System.out.println(this.chains());
		}
		
		if(hold>0&&hold<5){
			return hold;
		}else{
			System.out.println("Error: you did not enter a single digit number 1-6--try again:");
		}

		return 1;
	}
}