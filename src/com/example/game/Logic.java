package com.example.game;

import java.util.ArrayList;
import java.util.Random;
import android.util.Log;

public class Logic {

	public SlowEnemies slowEnemies;
	public LongEnemies longEnemies;
	public MoveEnemies moveEnemies;
	public LevelList levelList;
	
	
	private  float _difference;
	private float _sum;
	private int _columns = 7;
	private int _width;
	private int _height;
	private int _widthEnemies;
	public int _counts = 0;
	public int score;
	private int _sizeBox;
	private boolean _canGame = false;
	private boolean clickPause = false;
	
	private float _XBox;
	private float _YBox;
	private int _time;
	private float felshion;
	private float meters = 0;
	private float _speedStart;
	private float _angle;
	private float _liftingHeight;
	private float _g; 
	private float heightNumberCubs;
	//0 - right, 1 - left.
	private int _direction;
	//необходимое количество блоков на высоту
	private int numberHeight;
	public ArrayList <Float> wallArray = new ArrayList <Float>();
	
	
	public Logic(int w, int h) {
		_width = w;
		_height = h;
		createWHEnemies();
		createWall();
		_sizeBox = _widthEnemies - (int)(0.5*_widthEnemies);
		_XBox = _width/2 - _sizeBox/2;
        _YBox = _height*0.5F;
         
        _speedStart = 20.0F;
        _angle = 65.0F;
        _liftingHeight = 2.5F*_widthEnemies;
        felshion = 3*_widthEnemies;
        
        createLevelList();
		slowEnemies = new SlowEnemies(this);
		longEnemies = new LongEnemies(this);
		moveEnemies = new MoveEnemies(this, _width);
	}
	
	private void createLevelList()
	{
		if(MainActivity.clickButtonMenu == 2)
		{
			levelList = new LevelList((int)(_height/(2*_widthEnemies)));
		}
	}
	
	private void createWHEnemies() {
		_widthEnemies = _width/(_columns + 1);
	}
	
	private void createWall() {
		//необходимое количество блоков на высоту
		numberHeight = (int)_height/_widthEnemies + 3;
		for(int i = 0; i <= numberHeight; i++) {
			wallArray.add((float)_widthEnemies*(i-2));
		}
	}
	
	private void moveWall() {
		meters += _difference;
		for(int i = 0; i <= numberHeight; i++) {
			float el = wallArray.get(i) + _difference;
			wallArray.set(i, el);
    	}
		if(wallArray.get(0) > -_widthEnemies) {
			wallArray.add(0, wallArray.get(0) - _widthEnemies);
			wallArray.remove(wallArray.size() - 1);
		}
		if(meters >= _widthEnemies) {
			meters = 0;
			score++;
		}		
	}
	
	public void deleteFinishEnemeis() {
		slowEnemies.deleteEnemies();
		longEnemies.deleteEnemies();
		//moveEnemies.deleteEnemies();
	}
	
	public void menuCreateEnemies() 
	{
		if(MainActivity.clickButtonMenu == 1)
		{
			Log.d("TAG", "SIZE: " + moveEnemies.moveEnemiesArrayX.size());
			Random random = new Random();
			int rand = random.nextInt(17) + 1;
			if((getFelshion() >= (getHeightNumberCubs())*getWidthEnemies())) 
			{
				setFelshion(0);
				selectLevel(rand);
				countPlus();
			}
		}
		if(MainActivity.clickButtonMenu == 2)
		{
			if((getFelshion() >= (getHeightNumberCubs())*getWidthEnemies())) 
			{
				/*if(levelList.getIndex() < levelList.getArraySize())
				{*/
					setFelshion(0);
					int block = levelList.array.get(levelList.getIndex()).getBlock();
					float dist = levelList.array.get(levelList.getIndex()).getDistance();
					int space = levelList.array.get(levelList.getIndex()).getSpace();
					selectLevel(block, dist, space);
					levelList.plusIndex();
					countPlus();
				/*}
				else {
					_canGame = false;
				}*/
			}
		}
		
		deleteFinishEnemeis();
		slowEnemies.moveEnemies(_difference);
		longEnemies.moveLongEmenies(_difference);
		moveEnemies.moveMoveEmenies(_difference);
		felshion = felshion + _difference;
		checkEnd();
	}
	
	private void checkEnd() 
	{
		if(whereBox()) {
			Log.d("TAG", "FINAL");
			_canGame = false;
		}
	}
	
	private boolean whereBox() {
		if(_YBox > _height - _sizeBox) {
			return true;
		}
		else 
			return false;
	}
	
	private void selectLevel(int i, float distance, int space) {
		switch (i) {
		case 1:
			slowEnemies.addOneSlowEnemies(1, 1, distance);	
			slowEnemies.addOneSlowEnemies(5, 1, distance);	
			break;
		case 2:
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(3, 1, distance);
			slowEnemies.addOneSlowEnemies(6, 1, distance);
			break;
		case 3:
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(3, 1, distance);
			break;
		case 4:
			slowEnemies.addOneSlowEnemies(3, 1, distance);	
			slowEnemies.addOneSlowEnemies(6, 1, distance);
			break;
		case 5:
			slowEnemies.addOneSlowEnemies(3, 1, distance);	
			slowEnemies.addOneSlowEnemies(0, 2, distance);
			slowEnemies.addOneSlowEnemies(6, 2, distance);
			slowEnemies.addOneSlowEnemies(3, 4, distance);
			slowEnemies.addOneSlowEnemies(2, 5, distance);
			slowEnemies.addOneSlowEnemies(3, 5, distance);
			slowEnemies.addOneSlowEnemies(4, 5, distance);
			break;
		case 6:
			slowEnemies.addOneSlowEnemies(1, 1, distance);	
			slowEnemies.addOneSlowEnemies(2, 1, distance);
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(0, 2, distance);
			slowEnemies.addOneSlowEnemies(6, 2, distance);	
			slowEnemies.addOneSlowEnemies(6, 3, distance);
			slowEnemies.addOneSlowEnemies(1, 2, distance);	
			slowEnemies.addOneSlowEnemies(5, 2, distance);
			slowEnemies.addOneSlowEnemies(4, 3, distance);	
			slowEnemies.addOneSlowEnemies(5, 3, distance);
			break;
		case 7:
			slowEnemies.addOneSlowEnemies(4, 1, distance);	
			slowEnemies.addOneSlowEnemies(5, 1, distance);
			slowEnemies.addOneSlowEnemies(0, 2, distance);	
			slowEnemies.addOneSlowEnemies(0, 3, distance);
			slowEnemies.addOneSlowEnemies(6, 1, distance);	
			slowEnemies.addOneSlowEnemies(6, 2, distance);
			slowEnemies.addOneSlowEnemies(1, 2, distance);	
			slowEnemies.addOneSlowEnemies(5, 2, distance);
			slowEnemies.addOneSlowEnemies(1, 3, distance);	
			slowEnemies.addOneSlowEnemies(2, 3, distance);
			break;
		case 8:
			slowEnemies.addOneSlowEnemies(1, 1, distance);	
			slowEnemies.addOneSlowEnemies(5, 1, distance);
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(0, 2, distance);
			slowEnemies.addOneSlowEnemies(6, 1, distance);	
			slowEnemies.addOneSlowEnemies(6, 2, distance);
			slowEnemies.addOneSlowEnemies(1, 2, distance);	
			slowEnemies.addOneSlowEnemies(5, 2, distance);
			slowEnemies.addOneSlowEnemies(3, 4, distance);
			break;
		case 9:
			slowEnemies.addOneSlowEnemies(3, 1, distance);
			slowEnemies.addOneSlowEnemies(0, 3, distance);
			slowEnemies.addOneSlowEnemies(1, 3, distance);
			slowEnemies.addOneSlowEnemies(5, 3, distance);
			slowEnemies.addOneSlowEnemies(6, 3, distance);
			slowEnemies.addOneSlowEnemies(3, 5, distance);
			break;
		case 10:
			longEnemies.createLongEnemies(1, space, distance);
			break;
		case 11:
			longEnemies.createLongEnemies(2, space, distance);
			break;
		case 12:
			longEnemies.createLongEnemies(3, space, distance);
			break;
		case 13:
			//space - скорость
			moveEnemies.addOneMoveEnemies(2, space, distance);
			break;
		case 14:
			//space - скорость
			moveEnemies.addOneMoveEnemies(3, space, distance);
			break;
		case 15:
			//space - скорость
			moveEnemies.addOneMoveEnemies(4, space, distance);
			break;
		case 16:
			moveEnemies.addTwoEnemiesCubes(distance);
			break;
		case 17:
			moveEnemies.addTwoEnemiesCubes2(distance);
			break;
		case 18:
			//Конец
			Log.d("TAGTAG", "КОНЕЦ!");
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(1, 1, distance);	
			slowEnemies.addOneSlowEnemies(2, 1, distance);
			slowEnemies.addOneSlowEnemies(3, 1, distance);	
			slowEnemies.addOneSlowEnemies(4, 1, distance);
			slowEnemies.addOneSlowEnemies(5, 1, distance);	
			slowEnemies.addOneSlowEnemies(6, 1, distance);
			slowEnemies.addOneSlowEnemies(0, 2, distance);	
			slowEnemies.addOneSlowEnemies(1, 2, distance);	
			slowEnemies.addOneSlowEnemies(2, 2, distance);
			slowEnemies.addOneSlowEnemies(3, 2, distance);	
			slowEnemies.addOneSlowEnemies(4, 2, distance);
			slowEnemies.addOneSlowEnemies(5, 2, distance);	
			slowEnemies.addOneSlowEnemies(6, 2, distance);
			_canGame = false;
			clickPause = true;
			break;
		case 19:
			slowEnemies.addOneSlowEnemies(0, 1, distance);	
			slowEnemies.addOneSlowEnemies(1, 1, distance);	
			slowEnemies.addOneSlowEnemies(5, 1, distance);
			slowEnemies.addOneSlowEnemies(6, 1, distance);	
			slowEnemies.addOneSlowEnemies(0, 2, distance);	
			slowEnemies.addOneSlowEnemies(1, 2, distance);	
			slowEnemies.addOneSlowEnemies(5, 2, distance);
			slowEnemies.addOneSlowEnemies(6, 2, distance);	
			break;
		default:
			break;
		}
		deleteFinishEnemeis();
	}
	
	private void selectLevel(int i) {
		switch (i) {
		case 1:
			slowEnemies.addOneSlowEnemies(1, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(5, 1, 3.5F);	
			break;
		case 2:
			slowEnemies.addOneSlowEnemies(0, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(3, 1, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 1, 3.5F);
			break;
		case 3:
			slowEnemies.addOneSlowEnemies(0, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(3, 1, 3.5F);
			break;
		case 4:
			slowEnemies.addOneSlowEnemies(3, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(6, 1, 3.5F);
			break;
		case 5:
			slowEnemies.addOneSlowEnemies(3, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(3, 4, 3.5F);
			slowEnemies.addOneSlowEnemies(2, 5, 3.5F);
			slowEnemies.addOneSlowEnemies(3, 5, 3.5F);
			slowEnemies.addOneSlowEnemies(4, 5, 3.5F);
			break;
		case 6:
			slowEnemies.addOneSlowEnemies(1, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(2, 1, 3.5F);
			slowEnemies.addOneSlowEnemies(1, 2, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 2, 3.5F);	
			slowEnemies.addOneSlowEnemies(6, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(5, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(4, 3, 3.5F);	
			slowEnemies.addOneSlowEnemies(5, 3, 3.5F);
			break;
		case 7:
			slowEnemies.addOneSlowEnemies(4, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(5, 1, 3.5F);
			slowEnemies.addOneSlowEnemies(1, 2, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 2, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(6, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(5, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(1, 3, 3.5F);	
			slowEnemies.addOneSlowEnemies(2, 3, 3.5F);
			break;
		case 8:
			slowEnemies.addOneSlowEnemies(1, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(5, 1, 3.5F);
			slowEnemies.addOneSlowEnemies(1, 2, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(0, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 1, 3.5F);	
			slowEnemies.addOneSlowEnemies(6, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(5, 2, 3.5F);
			slowEnemies.addOneSlowEnemies(3, 4, 3.5F);
			break;
		case 9:
			slowEnemies.addOneSlowEnemies(3, 1, 3.5F);
			slowEnemies.addOneSlowEnemies(0, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(1, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(5, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(6, 3, 3.5F);
			slowEnemies.addOneSlowEnemies(3, 5, 3.5F);
			break;
		case 10:
			longEnemies.createLongEnemies(1, 0, 4F);
			break;
		case 11:
			longEnemies.createLongEnemies(2, 0, 4F);
			break;
		case 12:
			longEnemies.createLongEnemies(3, 0, 4F);
			break;
		case 13:
			moveEnemies.addOneMoveEnemies(2, 5F);
			break;
		case 14:
			moveEnemies.addOneMoveEnemies(3, 5F);
			break;
		case 15:
			moveEnemies.addOneMoveEnemies(4, 5F);
			break;
		case 16:
			moveEnemies.addTwoEnemiesCubes(5F);
			break;
		case 17:
			moveEnemies.addTwoEnemiesCubes2(5F);
			break;
		default:
			break;
		}
		deleteFinishEnemeis();
	}
	
	public void countPlus() {
		_counts++;
	}
	
	public void moveLeft() {
		_direction = 1;
		_time = 0;
		_sum = 0;
		_g = (_speedStart*_speedStart*((float)Math.sin(Math.toRadians(_angle))*(float)Math.sin(Math.toRadians(_angle))))/(2*_liftingHeight);
	}
	
	public void moveRight() {
		_direction = 0;
		_time = 0;
		_sum = 0;
		_g = (_speedStart*_speedStart*((float)Math.sin(Math.toRadians(_angle))*(float)Math.sin(Math.toRadians(_angle))))/(2*_liftingHeight);

	}
	
	public void moveBox() {		
		_difference = _speedStart*((float)Math.sin((Math.toRadians(_angle))))*_time - (_g*_time*_time)/2 - _sum;
		_sum +=_difference;
    	switch (_direction) {
 		case 0:
 			_XBox = _XBox + (_speedStart/2)*((float)Math.cos((Math.toRadians(_angle))));
 			if((_YBox - _difference) < _height/2)
			{
 				if(_difference > 0) {
 					moveAndChoise();
 				}	
 				else
 				{
 					_YBox = _YBox - _difference;
 				}
			}
 			else
 				_YBox = _YBox - _difference;
 			_time++;
 			break;
 		case 1:
 			_XBox = _XBox - (_speedStart/2)*((float)Math.cos((Math.toRadians(_angle))));
 			if((_YBox - _difference) < _height/2)
			{
 				if(_difference > 0) 
 				{
 					moveAndChoise();
 				}	
 				else
 				{
 					_YBox = _YBox - _difference;
 				}
			}
 			else
 				_YBox = _YBox - _difference;
 			_time++;
 			break;
 		default:
 			break;
 		}
	}
	
	//Выбор между автоматической игрой и уровнем
	private void moveAndChoise()
	{
		if(MainActivity.clickButtonMenu == 1)
		{
			moveWall();
			menuCreateEnemies();
		}
		if(MainActivity.clickButtonMenu == 2)
		{
			moveWall();
			menuCreateEnemies();
		}
	}
	
	public Boolean getClickPause() {
		return clickPause;
	}
	
	public void setClickPause(boolean b) {
		clickPause = b;
	}
	
	public Boolean getCanGame() {
		return _canGame;
	}
	
	public void setCanGame(Boolean b) {
		_canGame = b;
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public int getWidthEnemies() {
		return _widthEnemies;
	}
	
	public float getSpeed() {
		return _speedStart;
	}
	
	public float getAngle() {
		return _angle;
	}
	
	public float getLift() {
		return _liftingHeight;
	}
	
	public int getColumns() {
		return _columns;
	}
	
	public float getFelshion() {
		return felshion;
	}
	
	public void setFelshion(float p) {
		felshion = p;
	}
	
	public float getXBox() {
		return _XBox;
	}
	
	public float getYBox() {
		return _YBox;
	}
	
	public float getNumberHeight() {
		return numberHeight;
	}
	
	public int getSizeBox() {
		return _sizeBox;
	}
	
	public void setHeightNumberCubs(float h) {
		heightNumberCubs = h;
	}
	
	public float getHeightNumberCubs() {
		return heightNumberCubs;
	}
}
