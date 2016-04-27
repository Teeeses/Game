package com.example.game;

import java.util.ArrayList;
import java.util.Random;

public class LongEnemies {
	
	private Logic logic;
	
	public ArrayList <Float> longEnemiesArrayX = new ArrayList <Float>();
	public ArrayList <Float> longEnemiesArrayY = new ArrayList <Float>();
	
	public LongEnemies(Logic logic) {
		this.logic = logic;
	}
	
	private int choiceSpace() {
		Random random = new Random();
		int rand = random.nextInt(logic.getColumns() - 1);
		return rand;
	}
	
	//number - высота, space - место(-1 - рандом), flesh - distance
	public void createLongEnemies(int number, int space, float felsh) {
		int spaceP;
		if(space == -1)
			spaceP = choiceSpace();
		else
			spaceP = space;
		for(int i = 1; i < number+1; i++)
		{
			addOneLongEnemies(spaceP, i);
		}
		logic.setHeightNumberCubs(felsh+number);
	}
	
	private void addOneLongEnemies(int space, int k) {
		for(int i = 0; i < logic.getColumns(); i++) {
			if(i != space && i != (space + 1)) {
				longEnemiesArrayX.add((float)(i*logic.getWidthEnemies() + logic.getWidthEnemies()/2));
				longEnemiesArrayY.add((float)-(k)*logic.getWidthEnemies());
			}
		}
	}
	
	public void deleteEnemies() {
		for(int i = 0; i < longEnemiesArrayY.size(); i++) {
    		if(longEnemiesArrayY.get(i) >= logic.getHeight()) {
    			longEnemiesArrayY.remove(i);
    			longEnemiesArrayX.remove(i);
    		}
    	}
	}
	
	public void moveLongEmenies(float dif) {
		for(int i = 0; i < longEnemiesArrayY.size(); i++) {
			float newEl = longEnemiesArrayY.get(i) + dif;
			longEnemiesArrayY.set(i, newEl);
    	}
	}
}
