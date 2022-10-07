package com.unimelb.swen30006.MonopolyExpress.Strategy;


public class RuleStrategyFactory {
	private static RuleStrategyFactory _instance;
	public static RuleStrategyFactory getInstance() {
		if(_instance == null) {
			synchronized(RuleStrategyFactory.class) {
				if (_instance == null) {
					_instance = new RuleStrategyFactory();
				}
			}
		}
		return _instance;
	}
	private RuleStrategyFactory() {
		
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
