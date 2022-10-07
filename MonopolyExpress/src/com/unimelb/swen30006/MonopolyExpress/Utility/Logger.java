package com.unimelb.swen30006.MonopolyExpress.Utility;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;

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
		GameStatus.getInstance().subscribe(this);
	}
	
	public void log(Player player, BoardGame boardGame) {
		try {
			File file = new File(this.getClass().getResource("").getPath(), "DiceValues_"+player.getName()+".txt");
			System.out.println(this.getClass().getResource("").getPath() + "DiceValues_"+player.getName()+".txt");
			boolean isNew = false;
			if (!file.exists()) {
				file.createNewFile();
				isNew = true;
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			if(isNew) {
				writer.append("Group, Number \n");
			}
			
			writer.append("============Turn: " + player.getTurn() + "============\n");
			writer.append(DateFormat.getDateTimeInstance().format(new Date()));
			writer.append("\n");
			
			writer.append("Complete group: \n");
			for (SquareSet s:boardGame.getCompleteGroup()) {
				writer.append(s.getGroupName() + ", " + s.countFilled() + "\n");
			}
			writer.append("Incomplete group: \n");
			for(SquareSet s: boardGame.getInCompleteGroup()) {
				writer.append(s.getGroupName() + ", " + s.countFilled() + "\n");
			}
			
			
			writer.flush();
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Logged");
	}
	
	public void update(Object o) {
		TurnData data = (TurnData)o;
		log(data.player, data.boardGame);
	}

}
