package com.unimelb.swen30006.MonopolyExpress.Strategy;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class Rule2Strategy extends RuleStrategy {
	@Override
	public int sumMoneyPoint(BoardGame board) {
		int ret = 0;
		for(SquareSet ss: board.getInCompleteGroup()) {
			if (ss.getSumValue() > ret) {
				ret = ss.getSumValue();
			}
		}
		return ret;
	}
}
