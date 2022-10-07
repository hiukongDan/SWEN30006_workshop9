package com.unimelb.swen30006.MonopolyExpress.Strategy;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class Rule3Strategy extends RuleStrategy{

	@Override
	public int sumMoneyPoint(BoardGame board) {
		int ret = 0;
		for(SquareSet ss: board.getInCompleteGroup()) {
			ret += ss.getSumValue();
		}
		for(SquareSet ss: board.getCompleteGroup()) {
			ret += ss.getSumValue();
		}
		return ret;
	}

}
