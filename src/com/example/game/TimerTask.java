package com.example.game;

import java.util.TimerTask;

import android.widget.TextView;

class MyTimerTask extends TimerTask {

	private GameActivity game;
	private TextView textScore;

	public MyTimerTask(GameActivity game, TextView textScore) {
		this.game = game;
		this.textScore = textScore;
	}
	
	@Override
	public void run() {
		game.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				String str = Integer.toString(game.logic.score);
				textScore.setText(str + " meters");
			}
		});
	}
}
