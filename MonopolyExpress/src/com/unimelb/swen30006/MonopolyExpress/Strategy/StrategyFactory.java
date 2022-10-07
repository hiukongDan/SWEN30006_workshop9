package com.unimelb.swen30006.MonopolyExpress.Strategy;


public class StrategyFactory {
	private static StrategyFactory _instance;
	public StrategyFactory getInstance() {
		if(_instance == null) {
			synchronized(StrategyFactory.class) {
				if (_instance == null) {
					_instance = new StrategyFactory();
				}
			}
		}
		return _instance;
	}
	private StrategyFactory() {
		
	}
	
	public RuleStrategy getStrategy(int turn) {
		CompositeStrategy strategy = new HighestScoreStrategy();
		strategy.addStrategy(new Rule1Strategy());
		if (turn % 2 == 0) {
			strategy.addStrategy(new Rule2Strategy());
		}
		if (turn % 3 == 0) {
			strategy.addStrategy(new Rule3Strategy());
		}
		return strategy;
	}
}
