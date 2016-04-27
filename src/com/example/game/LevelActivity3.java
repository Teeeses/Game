package com.example.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class LevelActivity3 extends Activity implements OnClickListener {
	
	private int numberButtons = 15;
	private Button[] arrayButtons;
	private ImageButton buttonLeft;
	private ImageButton buttonRight;
	private int ScreenWidth;
	
	private LinearLayout layout;
	public static int levelClick;
	private boolean howManyClick;
	private SoundPool soundPool;
	private int soundKnopka;
	private Animation anim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.someOpenLevelList = 3;
		setContentView(R.layout.level);
		layout = (LinearLayout) findViewById(R.id.layout);
		arrayButtons = new Button[numberButtons];

		createSize();
		createSound();
		createButtonRightLeft();
		createButtons();
	}
	
	private void createSize() 
	{
		DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    	if(displaymetrics.widthPixels >= displaymetrics.heightPixels)
    	{
    		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	} 
		ScreenWidth = displaymetrics.widthPixels;
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void createButtonRightLeft()
	{
		buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
		buttonRight = (ImageButton) findViewById(R.id.buttonRight);
		Drawable drRight = getResources().getDrawable(R.drawable.r);
		Drawable drLeft = getResources().getDrawable(R.drawable.l);
		Bitmap bitmapRight = ((BitmapDrawable) drRight).getBitmap();
		Bitmap bitmapLeft = ((BitmapDrawable) drLeft).getBitmap();
		int width = bitmapRight.getWidth();
		float scaleWidth = ((float) (ScreenWidth/8)) / width;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap resizedBitmapRight = Bitmap.createBitmap(bitmapRight, 0, 0, width, width, matrix, true);
		Drawable drawebleRight = new BitmapDrawable(getResources(), resizedBitmapRight);
		Bitmap resizedBitmapLeft = Bitmap.createBitmap(bitmapLeft, 0, 0, width, width, matrix, true);
		Drawable drawebleLeft = new BitmapDrawable(getResources(), resizedBitmapLeft);
		if(android.os.Build.VERSION.SDK_INT < 16) {
			buttonLeft.setBackgroundDrawable(drawebleLeft);
			buttonRight.setBackgroundDrawable(drawebleRight);
		}
		else {
			buttonLeft.setBackground(drawebleLeft);
			buttonRight.setBackground(drawebleRight);
		}
		buttonRight.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onClick(View v) 
	{
		if (howManyClick == false) {
			howManyClick = true;
			soundPool.play(soundKnopka, 1, 1, 0, 0, 1);
			Intent i = null;
			switch (v.getId()) {
			case R.id.button1:
				levelClick = 1;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button2:
				levelClick = 2;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button3:
				levelClick = 3;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button4:
				levelClick = 4;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button5:
				levelClick = 5;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button6:
				levelClick = 6;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button7:
				levelClick = 7;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button8:
				levelClick = 8;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button9:
				levelClick = 9;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button10:
				levelClick = 10;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button11:
				levelClick = 11;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button12:
				levelClick = 12;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button13:
				levelClick = 13;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button14:
				levelClick = 14;
				i = new Intent(this, GameActivity.class);
				break;
			case R.id.button15:
				levelClick = 15;
				i = new Intent(this, GameActivity.class);
				this.finish();
				break;
			case R.id.buttonLeft:
				i = new Intent(this, LevelActivity2.class);
				this.finish();
				break;
			default:
				break;
			}
			this.startActivity(i);
		}
	}
	
	private void createButtons()
	{	
		Button button1 = (Button) findViewById(R.id.button1);
		arrayButtons[0] = button1;
		Button button2 = (Button) findViewById(R.id.button2);
		arrayButtons[1] = button2;
		Button button3 = (Button) findViewById(R.id.button3);
		arrayButtons[2] = button3;
		Button button4 = (Button) findViewById(R.id.button4);
		arrayButtons[3] = button4;
		Button button5 = (Button) findViewById(R.id.button5);
		arrayButtons[4] = button5;
		Button button6 = (Button) findViewById(R.id.button6);
		arrayButtons[5] = button6;
		Button button7 = (Button) findViewById(R.id.button7);
		arrayButtons[6] = button7;
		Button button8 = (Button) findViewById(R.id.button8);
		arrayButtons[7] = button8;
		Button button9 = (Button) findViewById(R.id.button9);
		arrayButtons[8] = button9;
		Button button10 = (Button) findViewById(R.id.button10);
		arrayButtons[9] = button10;
		Button button11 = (Button) findViewById(R.id.button11);
		arrayButtons[10] = button11;
		Button button12 = (Button) findViewById(R.id.button12);
		arrayButtons[11] = button12;
		Button button13 = (Button) findViewById(R.id.button13);
		arrayButtons[12] = button13;
		Button button14 = (Button) findViewById(R.id.button14);
		arrayButtons[13] = button14;
		Button button15 = (Button) findViewById(R.id.button15);
		arrayButtons[14] = button15;
		
		for(int i = 0; i < numberButtons; i++)
		{
			if(MainActivity.availableLevel >= (2*numberButtons + i + 1))
			{
				arrayButtons[i].setTextSize(18);
				arrayButtons[i].setBackgroundResource(R.drawable.box_button);
				arrayButtons[i].setText(Integer.toString(2*numberButtons + i + 1));
			}
			else
			{
				arrayButtons[i].setBackgroundResource(R.drawable.close_button);
				arrayButtons[i].setText("");
				arrayButtons[i].setEnabled(false);
			}
			arrayButtons[i].setOnClickListener(this);
		}
		buttonLeft.setOnClickListener(this);
	}
	
	@SuppressWarnings("deprecation")
	private void createSound()
	{
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundKnopka = soundPool.load(this, R.raw.click, 1);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	private void turnOnAnimation()
	{
		anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        layout.startAnimation(anim);
	}
	
	@Override
    protected void onResume() 
	{
      super.onResume();
      howManyClick = false;
	  //turnOnAnimation();
    }
}
