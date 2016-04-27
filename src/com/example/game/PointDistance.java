package com.example.game;

public class PointDistance {

	private int block;
	private float distance;
	private int space;
	
	public PointDistance(int block, float distance) {
		this.block = block;
		this.distance = distance;
	}
	
	public PointDistance(int block, float distance, int space) {
		this.block = block;
		this.distance = distance;
		this.space = space;
	}
	
	public int getSpace() {
		return space;
	}
	
	public int getBlock() {
		return block;
	}
	
	public float getDistance() {
		return distance;
	}
}
