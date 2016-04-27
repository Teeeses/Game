package com.example.game;

import java.util.ArrayList;

public class SlowEnemies {
	
	private Logic logic;
	
	public ArrayList <Float> _slowEnemiesX = new ArrayList <Float>();
	public ArrayList <Float> _slowEnemiesY = new ArrayList <Float>();
	
	public SlowEnemies(Logic logic) {
		this.logic = logic;
		for(int i = 0; i < logic.getColumns(); i++)
		{
			_slowEnemiesX.add((float)(i*logic.getWidthEnemies() + logic.getWidthEnemies()/2));
			_slowEnemiesY.add((float)(logic.getHeight() - logic.getWidthEnemies()));
		}
	}
	
	public void addOneSlowEnemies(int space, int k, float felsh) {
		float x = space*logic.getWidthEnemies() + logic.getWidthEnemies()/2;
		float y = -(k)*logic.getWidthEnemies();
		_slowEnemiesX.add(x);
		_slowEnemiesY.add(y);
		logic.setHeightNumberCubs(felsh+k);
	}
	
	public void moveEnemies(float dif) {
		for(int i = 0; i < _slowEnemiesY.size(); i++) {
			float newEl = _slowEnemiesY.get(i) + dif;
			_slowEnemiesY.set(i, newEl);
    	}
	}
	
	public void deleteEnemies() {
		for(int i = 0; i < _slowEnemiesY.size(); i++) {
    		if(_slowEnemiesY.get(i) >= logic.getHeight()) {
    			_slowEnemiesY.remove(i);
    			_slowEnemiesX.remove(i);
    		}
    	}
	}
}
