package com.unimelb.swen30006.MonopolyExpress.Strategy;
import com.unimelb.swen30006.MonopolyExpress.Player;
import com.unimelb.swen30006.MonopolyExpress.Board.*;


public abstract class RuleStrategy {
	public RuleStrategy() {

	}
	public abstract void sumMoneyPoint(Player player, BoardGame boardGame);
}
