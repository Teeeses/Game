package com.example.game;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

public class MoveEnemies {
	private Logic logic;
	private Random random;
	public ArrayList <Float> moveEnemiesArrayX = new ArrayList <Float>();
	public ArrayList <Float> moveEnemiesArrayY = new ArrayList <Float>();
	public ArrayList <Point> arrayDirection = new ArrayList <Point>();
	public int sp;
	
	public MoveEnemies(Logic logic, int w) {
		this.logic = logic;
		sp = (int)(w/240);
		random = new Random();
	}
	
	public void addOneMoveEnemies(int x, float felsh) {
		int start = selectNumberStartCube(x);
		int y = selectDirection(selectSpeed());
		//y - скорость(2, -2), x - колличество кубиков
		Point point = new Point(x, y);
		arrayDirection.add(point);
		for(int i = start; i < (point.x)+start; i++) {
			moveEnemiesArrayX.add((float)(i*logic.getWidthEnemies() + logic.getWidthEnemies()/2));
			moveEnemiesArrayY.add((float)-logic.getWidthEnemies());
		}
		logic.setHeightNumberCubs(felsh);
	}
	
	public void addOneMoveEnemies(int x, int speed, float felsh) {
		int start = selectNumberStartCube(x);
		//speed - скорость(2, -2), x - колличество кубиков
		Point point = new Point(x, selectDirection(speed));
		arrayDirection.add(point);
		for(int i = start; i < (point.x)+start; i++) {
			moveEnemiesArrayX.add((float)(i*logic.getWidthEnemies() + logic.getWidthEnemies()/2));
			moveEnemiesArrayY.add((float)-logic.getWidthEnemies());
		}
		logic.setHeightNumberCubs(felsh);
	}
	
	private int selectDirection(int speed) {
		int rand = random.nextInt(1);
		if(rand == 1)
			return speed;
		else
			return -speed;
	}
	
	private int selectNumberStartCube(int number) {
		int rand = random.nextInt(logic.getColumns() - number);
		return rand;
	}

	
	private int selectSpeed() {
		int rand = random.nextInt(2) + 1;
		int speed = sp*rand;
		return speed;
	}
	
	public void moveMoveEmenies(float dif) {
		for(int i = 0; i < moveEnemiesArrayY.size(); i++) {
			float newEl = moveEnemiesArrayY.get(i) + dif;
			moveEnemiesArrayY.set(i, newEl);
		}
	}
	
	public void deleteEnemies() {
		boolean b = true;
		for(int i = 0; i < moveEnemiesArrayY.size(); i++) {
    		if(moveEnemiesArrayY.get(i) >= logic.getHeight()) {
    			moveEnemiesArrayY.remove(i);
    			moveEnemiesArrayX.remove(i);
    			b = false;
    		}
    	}
		if(!b) {
			arrayDirection.remove(0);
		}
	}
	
	public void moveMoveEnemiesX() {
		int index = 0;
		float indexStart;
		float indexFinish;
		for(int i = 0; i < arrayDirection.size(); i++)
		{
			indexStart = moveEnemiesArrayX.get(index);
			int number = arrayDirection.get(i).x;
			indexFinish = indexStart + number*logic.getWidthEnemies();
			if(indexStart <= logic.getWidthEnemies()/2 || indexFinish >= logic.getWidth() - logic.getWidthEnemies()/2)
			{
				invertDirection(i);
			}
			for(int j = 0; j < arrayDirection.get(i).x; j++)
			{
				float n = moveEnemiesArrayX.get(index) + arrayDirection.get(i).y;
				moveEnemiesArrayX.set(index, n);
				index++;
			}
		}
	}
	
	private void invertDirection(int index) {
		arrayDirection.get(index).y = -arrayDirection.get(index).y;
	}
	
	private void addOneMoveEnemies(int start, Point point, int k) {
		arrayDirection.add(point);
		for(int i = start; i < (point.x)+start; i++) {
			moveEnemiesArrayX.add((float)(i*logic.getWidthEnemies() + logic.getWidthEnemies()/2));
			moveEnemiesArrayY.add((float)-(k)*logic.getWidthEnemies());
		}
	}
	
	public void addTwoEnemiesCubes(float felsh) {
		Point point1 = new Point(1, -sp);
		Point point2 = new Point(1, sp);
		addOneMoveEnemies(0, point1, 1);
		addOneMoveEnemies(logic.getColumns() - 1, point2, 2);
		logic.setHeightNumberCubs(felsh);
	}
	
	public void addTwoEnemiesCubes2(float felsh) {
		Point point1 = new Point(1, -sp);
		Point point2 = new Point(1, sp);
		addOneMoveEnemies(0, point1, 2);
		addOneMoveEnemies(logic.getColumns() - 1, point2, 1);
		logic.setHeightNumberCubs(felsh);
	}
}
