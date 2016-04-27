package com.example.game;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyDialog {

	private GameActivity game;
	private Dialog dialog;
	private Button buttonResume;
	private Button buttonExit;
		
	public MyDialog(GameActivity game) {
		this.game = game;
		init();
	}
	
	@SuppressLint("InflateParams")
	private void init() {
		LayoutInflater inflater = LayoutInflater.from(game);
		View layout = inflater.inflate(R.layout.dialog, null);
		layout.setPadding(20, 20, 20, 20);
		buttonResume = (Button) layout.findViewById(R.id.buttonResume);
		buttonExit = (Button) layout.findViewById(R.id.buttonExit);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(game);
		builder.setView(layout);
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		buttonResume.setOnClickListener(clickButtonResume);
		buttonExit.setOnClickListener(clickButtonExit);
	}
	
	public void show() {
		dialog.show();
	}
	
	OnClickListener clickButtonResume = new OnClickListener() {
	    @Override
        public void onClick(View v) {
	    	game.playSound(game.soundKnopka);
	    	dialog.cancel();
	    	if(game.logic.getCanGame())
	    	{
	    		game.setAnimation();
	    	}
	    	else {
	    		game.logic.setClickPause(false);
				game.buttonPause.setEnabled(true);
	    	}
        }
    };
	    
    OnClickListener clickButtonExit = new OnClickListener() {
 	    @Override
        public void onClick(View v) {
 	       game.playSound(game.soundKnopka);
 		   dialog.cancel();
 		   game.finish();
        }
     };
}
