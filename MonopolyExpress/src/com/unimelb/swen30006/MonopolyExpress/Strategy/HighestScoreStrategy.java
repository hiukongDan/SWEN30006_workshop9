package com.unimelb.swen30006.MonopolyExpress.Strategy;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;

public class HighestScoreStrategy extends CompositeStrategy{

	@Override
	public int sumMoneyPoint(BoardGame board) {
		int ret = 0;
		for(RuleStrategy s : strategies) {
			int sum = s.sumMoneyPoint(board);
			if(sum > ret) {
				ret = sum;
			}
		}
		return ret;
	}

}
