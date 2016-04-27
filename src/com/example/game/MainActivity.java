package com.example.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button start;
	private Button level;
	private TextView textScore;
	private SoundPool soundPool;
	private int soundKnopka;
	public static int clickButtonMenu;
	public static int someOpenLevelList;
	
	public static int score = 0;	
	public static int availableLevel = 1;
	// это будет именем файла настроек
	public static final String APP_PREFERENCES = "mysettings";
	//показания счетчика
	public static final String APP_PREFERENCES_SCORE = "score";
	public static final String APP_PREFERENCES_AVAILABLELEVEL = "availableLevel";
	SharedPreferences mSettings;
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    	if(displaymetrics.widthPixels >= displaymetrics.heightPixels)
    	{
    		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	} 
    	
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundKnopka = soundPool.load(this, R.raw.click, 1);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        availableLevel = 15;
		start = (Button) findViewById(R.id.start);
		level = (Button) findViewById(R.id.level);
		setWidthHeightButton(displaymetrics.widthPixels, displaymetrics.heightPixels);
		textScore = (TextView) findViewById(R.id.textScore);
		textScore.setTextColor(Color.BLACK);
		//load();
		start.setOnClickListener(this);
		level.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void setWidthHeightButton(int ScreenWidth, int ScreenHeight) {
		Drawable dr = getResources().getDrawable(R.drawable.long_button);
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scaleWidth = ((float) (ScreenWidth/1.6)) / width;
		float scaleHeight = ((float) (ScreenHeight/8) / height);
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		Drawable draweble = new BitmapDrawable(getResources(), resizedBitmap);
		if(android.os.Build.VERSION.SDK_INT < 16) {
			start.setBackgroundDrawable(draweble);
			level.setBackgroundDrawable(draweble);
		}
		else {
			start.setBackground(draweble);
			level.setBackground(draweble);
		}
	}
	
	@Override
	public void onClick(View v) {
		Intent i;
		soundPool.play(soundKnopka, 1, 1, 0, 0, 1);
		switch (v.getId()) {
		case R.id.start:
			clickButtonMenu = 1;
			i = new Intent(this, GameActivity.class);
			this.startActivity(i);
			break;
		case R.id.level:
			clickButtonMenu = 2;
			i = new Intent(this, LevelActivity1.class);
			this.startActivity(i);
			break;
		default:
			break;
		}
	}
	
	private void load() {
		//если ли нужный нам ключ
    	if (mSettings.contains(APP_PREFERENCES_SCORE)) {
    		//Получаем число из настроек
    		score = mSettings.getInt(APP_PREFERENCES_SCORE, 0);
    	}
    	if(mSettings.contains(APP_PREFERENCES_AVAILABLELEVEL)) {
    		availableLevel = mSettings.getInt(APP_PREFERENCES_AVAILABLELEVEL, 0);
    	}
	}
	
	public void save()
    {
    	Editor editor = mSettings.edit();
    	editor.putInt(APP_PREFERENCES_SCORE, score);
    	editor.commit();
    	Editor editor2 = mSettings.edit();
    	editor2.putInt(APP_PREFERENCES_AVAILABLELEVEL, availableLevel);
    	editor2.commit();
    }
	
	@Override
    protected void onResume() {
      super.onResume();
      textScore.setText("Your score: " + score);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	save();
    }
}
