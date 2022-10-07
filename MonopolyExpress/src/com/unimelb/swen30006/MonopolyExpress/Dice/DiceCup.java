package com.unimelb.swen30006.MonopolyExpress.Dice;
import java.util.ArrayList;
import java.util.LinkedList;
public class DiceCup {
	private ArrayList<Die> dice;
	private LinkedList<Die> remainingDice;
	
	public DiceCup() {
		dice = new ArrayList<Die>();
		dice.add(new Die1());
		dice.add(new Die2());
		dice.add(new Die34());
		dice.add(new Die34());
		dice.add(new Die5());
		dice.add(new DieUtility());
		dice.add(new DieUtility());
		dice.add(new DiePolice());
		dice.add(new DiePolice());
		dice.add(new DiePolice());
		
		remainingDice = new LinkedList<>();
		
		reset();
	}
	
	public void reset() {
		remainingDice.clear();
		remainingDice.addAll(dice);
	}
	
	public LinkedList<Die> roll(){
		for(Die die: remainingDice) {
			die.roll();
		}
		return remainingDice;
	}
	
	public void removeFromRemaining(Die die) {
		remainingDice.remove(die);
	}
	
}
