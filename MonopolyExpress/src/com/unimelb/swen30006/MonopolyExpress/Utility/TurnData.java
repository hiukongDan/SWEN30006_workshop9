package com.unimelb.swen30006.MonopolyExpress.Utility;

import com.unimelb.swen30006.MonopolyExpress.Player;
import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;

public class TurnData {
	public Player player;
	public BoardGame boardGame;
	public TurnData(Player player, BoardGame boardGame) {
		this.player = player;
		this.boardGame = boardGame;
	}
}
