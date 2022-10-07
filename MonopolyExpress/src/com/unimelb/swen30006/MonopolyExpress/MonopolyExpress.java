/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Dice.DiceCup;
import com.unimelb.swen30006.MonopolyExpress.Dice.Die;
import com.unimelb.swen30006.MonopolyExpress.Utility.GameStatus;
import com.unimelb.swen30006.MonopolyExpress.Utility.Logger;
import com.unimelb.swen30006.MonopolyExpress.Utility.TurnData;



public class MonopolyExpress{
	
	public static void main(String[] args) {
		
		BoardGame board = new BoardGame();
		ArrayList<Player> players = new ArrayList<Player>();
		
		MonopolyExpress monopolyExpress = new MonopolyExpress();
		
		// initialize logger
		Logger.getInstance();
		
		players.add(new Player("A"));
		players.add(new Player("B"));
		
		Scanner in = new Scanner(System.in);
		
		boolean hasWinner = false;
		
		DiceCup cup = new DiceCup();
		
		while(!hasWinner) {
			Player currentPlayer = players.remove(0);
			currentPlayer.newTurn();
			System.out.println("====== "+currentPlayer.getName()+"'s turn ====");
			cup.reset();
			
			boolean turnEnds = false;
			do {
				//Roll the dice and show the faces
				ArrayList<Die> dice = new ArrayList<Die>(cup.roll());
				System.out.println("Dice are: ");
				for(int i = 0; i < dice.size(); i++) {
					Die die = dice.get(i);
					System.out.println((i+1) + ": " + die.getCurrentFaceName());
				}
				
				//Check PoliceDice and place on the board
				ArrayList<Die> diceCpy = new ArrayList<Die>();
				for(Die die: dice) {
					if(die.getCurrentFaceName() == "Police") {
						board.placeDie(die);
						cup.removeFromRemaining(die);
					}
					else {
						diceCpy.add(die);
					}
				}
				dice = diceCpy;
				
				ArrayList<Die> remaining = new ArrayList<Die>();
				for(Die d: dice) {
					if(d.getCurrentFaceName() != "<blank>") {
						remaining.add(d);
					}
				}
				
				System.out.println(board.show());
				
				if(board.isAllFilled("Police")) {
					//do something
					System.out.println("3 Police pieces, " + currentPlayer.getName() + " skips this turn!");
					turnEnds = true;
				}else {
					//Ask the player to pick the number dice
					int index = 0;
					int remainingDice = 0;
					
					do {
						System.out.println("------ Remaining Dice ----");
						//show dice faces
						System.out.println("Remaining Dice: ");
						remainingDice = remaining.size();
						for(int i = 0; i < remainingDice; i++) {
							System.out.println((i+1)+": " +remaining.get(i).getCurrentFaceName());
						}
						
						System.out.print("["+currentPlayer.getName()+"]Pick a number die (1-"+remainingDice+") or -1 (no pick):");
						index = in.nextInt();
						
						// remove die from DiceCup and remaining cache
						if (index >= 1 && index <= remainingDice) {
							Die die = remaining.get(index-1);
							cup.removeFromRemaining(die);
							remaining.remove(die);
							board.placeDie(die);
						}
						
						System.out.println("Current board:\n" + board.show());
						
					}while(remaining.size() != 0 && index != -1);
					
					if (remaining.size() == 0) {
						turnEnds = true;
					}
					else {
						System.out.print("["+currentPlayer.getName()+"] Keep rolling? (y/n):");
						String answer = in.next();
						
						if(answer.toLowerCase().equals("n")) {
							turnEnds = true;
						}
					}
				}
	
			}while(!turnEnds);
			System.out.println("Turn ends");
			
			//Calculate score
			
			
			// Notify subscribers
			GameStatus.getInstance().update(new TurnData(currentPlayer, board));
			
			players.add(currentPlayer);
			board.reset();
		}
		
		in.close();
		
	}

	
	
}
