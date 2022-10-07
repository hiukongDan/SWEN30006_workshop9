package com.unimelb.swen30006.MonopolyExpress.Strategy;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class Rule1Strategy extends RuleStrategy{
	@Override 
	public int sumMoneyPoint(BoardGame board) {
		int ret = 0;
		for(SquareSet ss: board.getCompleteGroup()) {
			switch(ss.getGroupName()) {
			case "50":
				ret += 600;
				break;
			case "100":
				ret += 1000;
				break;
			case "150":
				ret += 1500;
				break;
			case "200":
				ret += 1800;
				break;
			case "250":
				ret += 2200;
				break;
			case "300":
				ret += 2700;
				break;
			case "400":
				ret += 3000;
				break;
			case "500":
				ret += 3500;
				break;
			case "Railroads":
				ret += 2500;
				break;
			case "Utilities":
				ret += 800;
				break;
			default:
				break;
			}
		}
		return ret;
	}
}
