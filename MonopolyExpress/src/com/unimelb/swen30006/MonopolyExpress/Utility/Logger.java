package com.unimelb.swen30006.MonopolyExpress.Utility;

import java.io.*;

import com.unimelb.swen30006.MonopolyExpress.Player;
import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class Logger implements Observer{
	private static Logger _instance;
	
	public static Logger getInstance() {
		if (_instance == null) {
			synchronized(Logger.class){
				if (_instance == null) {
					_instance = new Logger();
				}
			}
		}
		return _instance;
	}
	
	private Logger() {
		
	}
	
	public void log(Player player, BoardGame boardGame) {
		try {
			File file = new File("log/DiceValues_"+player.getName()+".txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			if(file.createNewFile()) {
				writer.write("Group, Number\n");
			}
			for (SquareSet s:boardGame.getCompleteGroup()) {
				writer.write(s.getGroupName() + ", " + s.getSumValue() + "\n");
			}
			writer.close();
		}
		catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void update(Object o) {
		TurnData data = (TurnData)o;
		log(data.player, data.boardGame);
	}
}
