package com.example.game;

import java.util.ArrayList;

import android.util.Log;

public class LevelList {

	//private int numberObstacles;
	//массив припятствия и дистанции
	public ArrayList<PointDistance> array = new ArrayList<PointDistance>();
	private int index = 0;
	private int dist;
	
	public LevelList(int dist) {
		this.dist = dist;
		if(MainActivity.someOpenLevelList == 1)
		{
			createLevelList1();
		}
		if(MainActivity.someOpenLevelList == 2)
		{
			createLevelList2();
		}
		if(MainActivity.someOpenLevelList == 3)
		{
			createLevelList3();
		}
	}
	
	private void createLevelList1()
	{
		if(LevelActivity1.levelClick == 1)
		{
			array.add(new PointDistance(1, 3.5F));
			array.add(new PointDistance(2, 3.5F));
			array.add(new PointDistance(1, 3.5F));
			array.add(new PointDistance(3, 3.5F));
			array.add(new PointDistance(4, 3.5F));
			array.add(new PointDistance(3, dist + 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 2)
		{
			array.add(new PointDistance(2, 2.5F));
			array.add(new PointDistance(1, 3.5F));
			array.add(new PointDistance(3, 3.5F));
			array.add(new PointDistance(4, 3.5F));
			array.add(new PointDistance(3, 3.5F));
			array.add(new PointDistance(19, 3.0F));
			array.add(new PointDistance(5, 3.5F));
			array.add(new PointDistance(2, 3.5F));
			array.add(new PointDistance(8, dist + 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 3)
		{
			array.add(new PointDistance(19, 3.0F));
			array.add(new PointDistance(2, 3.0F));
			array.add(new PointDistance(3, 3.0F));
			array.add(new PointDistance(1, 3.0F));
			array.add(new PointDistance(19, 3.0F));
			array.add(new PointDistance(3, 3.0F));
			array.add(new PointDistance(2, 3.0F));
			array.add(new PointDistance(4, 3.0F));
			array.add(new PointDistance(19, 3.0F));
			array.add(new PointDistance(3, 3.0F));
			array.add(new PointDistance(2, 3.0F));
			array.add(new PointDistance(1, 3.0F));
			array.add(new PointDistance(10, 3.0F, 4));
			array.add(new PointDistance(19, dist + 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 4)
		{
			array.add(new PointDistance(10, 3.0F, 0));
			array.add(new PointDistance(10, 3.0F, 1));
			array.add(new PointDistance(10, 3.0F, 2));
			array.add(new PointDistance(10, 3.0F, 3));
			array.add(new PointDistance(10, 3.0F, 4));
			array.add(new PointDistance(10, 3.0F, 5));
			array.add(new PointDistance(10, 3.0F, 4));
			array.add(new PointDistance(10, 3.0F, 3));
			array.add(new PointDistance(10, 3.0F, 2));
			array.add(new PointDistance(10, 3.0F, 1));
			array.add(new PointDistance(10, 3.0F, 0));
			array.add(new PointDistance(10, dist + 4, 5));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 5)
		{
			array.add(new PointDistance(5, 2.5F));
			array.add(new PointDistance(8, 2.5F));
			array.add(new PointDistance(2, 2.5F));
			array.add(new PointDistance(10, 3F, 1));
			array.add(new PointDistance(10, 3F, 4));
			array.add(new PointDistance(10, 3F, 1));
			array.add(new PointDistance(10, dist + 4, 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 6)
		{
			array.add(new PointDistance(13, 3.5F, 1));
			array.add(new PointDistance(14, 3.5F, 1));
			array.add(new PointDistance(15, 3.0F, 1));
			array.add(new PointDistance(19, 2.5F));
			array.add(new PointDistance(1, 1.5F));
			array.add(new PointDistance(4, 1.5F));
			array.add(new PointDistance(3, 1.5F));
			array.add(new PointDistance(8, 2.0F));
			array.add(new PointDistance(9, 2.0F));
			array.add(new PointDistance(7, 2.0F));
			array.add(new PointDistance(6, 2.0F));
			array.add(new PointDistance(5, dist + 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 7)
		{
			array.add(new PointDistance(7, 3.0F));
			array.add(new PointDistance(5, 3.5F));
			array.add(new PointDistance(6, 3.0F));
			array.add(new PointDistance(8, 2.0F));
			array.add(new PointDistance(8, 2.0F));
			array.add(new PointDistance(10, 3.0F, 3));
			array.add(new PointDistance(11, 3.0F, 4));
			array.add(new PointDistance(12, 2.5F, 3));
			array.add(new PointDistance(9, 3.0F));
			array.add(new PointDistance(5, dist + 4));
			array.add(new PointDistance(18, 1));
		}
		
		if(LevelActivity1.levelClick == 8)
		{
			array.add(new PointDistance(12, 2.5F, 0));
			array.add(new PointDistance(7, 3.0F));
			array.add(new PointDistance(10, 4F, 4));
			array.add(new PointDistance(10, 2F, 0));
			array.add(new PointDistance(3, 2.5F));
			array.add(new PointDistance(2, 2.5F));
			array.add(new PointDistance(1, 2.0F));
			array.add(new PointDistance(7, 2.5F));
			array.add(new PointDistance(10, 2.5F, 2));
			array.add(new PointDistance(11, 3.5F, 4));
			array.add(new PointDistance(12, dist + 4, 2));
			array.add(new PointDistance(18, 1));
		}
	}
	
	private void createLevelList2()
	{
		if(LevelActivity1.levelClick == 1)
		{
			
		}
	}
	
	private void createLevelList3()
	{
		if(LevelActivity1.levelClick == 1)
		{
			
		}
	}
	
	public int getArraySize() {
		return array.size();
	}
	
	public int getIndex() {
		return index;
	}
	
	public void plusIndex() {
		index++;
	}
}
