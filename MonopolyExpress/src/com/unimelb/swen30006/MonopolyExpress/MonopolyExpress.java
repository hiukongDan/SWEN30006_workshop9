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
import com.unimelb.swen30006.MonopolyExpress.Utility.Observable;
import com.unimelb.swen30006.MonopolyExpress.Utility.TurnData;



public class MonopolyExpress extends Observable{
	
	private static MonopolyExpress _instance;
	private MonopolyExpress() {
		
	}
	
	public MonopolyExpress getInstance() {
		if (_instance == null) {
			synchronized(MonopolyExpress.class) {
				if(_instance == null) {
					_instance = new MonopolyExpress();
				}
			}
		}
		return _instance;
	}
	

	public static void main(String[] args) {
		
		BoardGame board = new BoardGame();
		ArrayList<Player> players = new ArrayList<Player>();
		
		MonopolyExpress monopolyExpress = new MonopolyExpress();
		
		players.add(new Player("A"));
		players.add(new Player("B"));
		
		Scanner in = new Scanner(System.in);
		
		boolean hasWinner = false;
		
		DiceCup cup = new DiceCup();
		
		while(!hasWinner) {
			Player currentPlayer = players.remove(0);
			System.out.println("====== "+currentPlayer.getName()+"'s turn ====");
			cup.reset();
			
			boolean turnEnds = false;
			do {
				//Roll the dice and show the faces
				LinkedList<Die> dice = cup.roll();
				System.out.println("Dice are: ");
				for(int i = 0; i < dice.size(); i++) {
					Die die = dice.get(i);
					System.out.println(i + ": " + die.getCurrentFaceName());
				}
				
				//Check PoliceDice and place on the board
				LinkedList<Die> diceCpy = new LinkedList<>(dice);
				for(int i = 0; i < dice.size(); i++) {
					if(dice.get(i).getCurrentFaceName() == "Police") {
						board.placeDie(dice.get(i));
						diceCpy.remove(dice.get(i));
						cup.removeFromRemaining(dice.get(i));
					}
				}
				dice = diceCpy;
				
				LinkedList<Die> remaining = new LinkedList<Die>();
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
						}
						
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
			_instance.update(new TurnData(currentPlayer, board));
			
			players.add(currentPlayer);
			board.reset();
		}
		
		in.close();
		
	}

	
	
}
