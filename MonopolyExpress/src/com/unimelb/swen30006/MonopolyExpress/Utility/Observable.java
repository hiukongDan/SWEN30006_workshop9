package com.unimelb.swen30006.MonopolyExpress.Utility;

import java.util.ArrayList;

public abstract class Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void subscribe(Observer o) {
		observers.add(o);
	}
	
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}
	
	public void update(Object o) {
		for(Observer ob: observers) {
			ob.update(o);;
		}
	}
}
