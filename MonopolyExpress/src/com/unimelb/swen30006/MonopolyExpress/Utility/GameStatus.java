package com.unimelb.swen30006.MonopolyExpress.Utility;

public class GameStatus extends Observable{
	private static GameStatus _instance;
	private GameStatus() {
		
	}
	
	public static GameStatus getInstance() {
		if (_instance == null) {
			synchronized(GameStatus.class) {
				if (_instance == null) {
					_instance = new GameStatus();
				}
			}
		}
		return _instance;
	}
}
