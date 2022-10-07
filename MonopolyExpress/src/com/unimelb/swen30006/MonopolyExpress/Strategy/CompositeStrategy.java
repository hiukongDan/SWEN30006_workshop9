package com.unimelb.swen30006.MonopolyExpress.Strategy;

import java.util.ArrayList;

import com.unimelb.swen30006.MonopolyExpress.Player;
import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;

public abstract class CompositeStrategy extends RuleStrategy{
	protected ArrayList<RuleStrategy> strategies = new ArrayList<>();
	
	public void addStrategy(RuleStrategy s) {
		strategies.add(s);
	}
}
