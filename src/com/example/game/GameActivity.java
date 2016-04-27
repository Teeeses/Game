package com.example.game;

import java.util.Timer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class GameActivity extends Activity implements OnTouchListener {

	public Logic logic;
	private Surface surf;
	private MyDialog dialog;
	private Timer mTimer;
	private MyTimerTask mMyTimerTask;
	
	private boolean jumped = false;
	
	private TextView textScore;
	private RelativeLayout gameRelative;
	public ImageButton buttonPause;
	private TextView textCount;
	
	private Animation anim;
	private SoundPool soundPool;
	public int soundKnopka;
	private int soundMove;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    	if(displaymetrics.widthPixels >= displaymetrics.heightPixels)
    	{
    		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	} 
		logic = new Logic(displaymetrics.widthPixels, displaymetrics.heightPixels);	
		surf = new Surface(this, logic);
		dialog = new MyDialog(this);
		FrameLayout game = new FrameLayout(this); 
		createPause();
		createTextScore();
		createTextTime();
		createSound();
		createRelativeLayout(); 
		
		logic._counts = 0;
		game.addView(surf);  
		game.addView(gameRelative); 
		setContentView(game); 
		game.setOnTouchListener(this);
		buttonPause.setOnClickListener(clickButtonPause);
		setTimer();
		/*}catch(Exception e) {
			Toast toast = Toast.makeText(this, "Ошибка: " + e, Toast.LENGTH_LONG); 
			toast.show(); 
		}*/
	}
	
	@SuppressWarnings("deprecation")
	private void createSound() {
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundKnopka = soundPool.load(this, R.raw.click, 1);
        soundMove = soundPool.load(this, R.raw.jump, 1);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	@SuppressWarnings("deprecation")
	private void createPause() {
		buttonPause = new ImageButton(this);
		/*RelativeLayout.LayoutParams paramsButton = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		paramsButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
		paramsButton.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);*/
		Drawable dr = getResources().getDrawable(R.drawable.bluepause);
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		int width = bitmap.getWidth();
		float scaleWidth = ((float) (logic.getWidth()/8)) / width;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, width, matrix, true);
		Drawable pauseDraweble = new BitmapDrawable(getResources(), resizedBitmap);
		if(android.os.Build.VERSION.SDK_INT < 16) {
		    buttonPause.setBackgroundDrawable(pauseDraweble);
		}
		else {
		    buttonPause.setBackground(pauseDraweble);
		}
		buttonPause.setX(10);
		buttonPause.setY(10);
	}
	
	private void createTextScore() {
		textScore = new TextView(this);
		RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);  
		paramsText.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);  
		paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);  
		textScore.setLayoutParams(paramsText);
		textScore.setTextColor(Color.BLACK);
		textScore.setTextSize(25);
		if(MainActivity.clickButtonMenu == 2)
		{
			textScore.setVisibility(View.INVISIBLE);
		}
	}
	
	private void createTextTime() {
		textCount = new TextView(this);	
		RelativeLayout.LayoutParams paramsTextCount = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		paramsTextCount.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE); 
		paramsTextCount.setMargins(0, logic.getHeight()/3, 0, 0);
		textCount.setLayoutParams(paramsTextCount);
		textCount.setTextColor(Color.BLACK);
		textCount.setTextSize(25);
		textCount.setHeight(100);
	}
	
	private void createRelativeLayout() {
		gameRelative = new RelativeLayout(this);  
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		gameRelative.setLayoutParams(params2); 	
		gameRelative.addView(buttonPause);     
		gameRelative.addView(textScore);
		gameRelative.addView(textCount);
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			if(jumped == false)
			{
				jumped = true;
				logic.setCanGame(true);
			}
			definitionClickScreen((int)event.getX());
			break;
		default:
			break;
		}
		return true;
	}
	
	private void definitionClickScreen(int x) {
		playSound(soundMove);
		if(x <= logic.getWidth()/2)
			logic.moveLeft();
		else
			logic.moveRight();
	}
	
	public void playSound(int sound) {
		if(logic.getCanGame() && !logic.getClickPause())
		soundPool.play(sound, 1, 1, 0, 0, 1);
	}
	
	private void setTimer() {	
		Runnable runnable = new Runnable() {
	        public void run() {
                synchronized(this) {
                    try {
                    	mTimer = new Timer();
                    	mMyTimerTask = new MyTimerTask(GameActivity.this, textScore);
                    	mTimer.schedule(mMyTimerTask, 0, 200);
                    } catch (Exception e) {
                    }
	            }
	        }
	    };
	    Thread thread = new Thread(runnable);
	    thread.start();
	}
	
	public void setAnimation() {
		anim = null;
		anim = AnimationUtils.loadAnimation(this, R.anim.animresume);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {		
			public void run() {
				textCount.setText("3");
				textCount.startAnimation(anim);
			}
		}, 0);
		handler.postDelayed(new Runnable() {		
			public void run() {
				textCount.setText("2");
				textCount.startAnimation(anim);
			}
		}, 1000);
		handler.postDelayed(new Runnable() {		
			public void run() {
				textCount.setText("1");
				textCount.startAnimation(anim);
			}
		}, 2000);
		handler.postDelayed(new Runnable() {		
			public void run() {
				textCount.setText("");
				logic.setClickPause(false);
				buttonPause.setEnabled(true);
			}
		}, 3000);
	}
	
	 
	OnClickListener clickButtonPause = new OnClickListener() {
	   @Override
       public void onClick(View v) {
		   setParamsPause();
       }
    };
	
    private void setParamsPause() {
       this.onPause();
       logic.setClickPause(true);
 	   buttonPause.setEnabled(false);
 	   dialog.show();
    }
    
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    }
	
    @Override
    protected void onResume() {
      super.onResume();
      if(logic.getCanGame() && !logic.getClickPause())
      {
    	  setParamsPause();
      }
      setTimer();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	mTimer.cancel();
    	mTimer.purge();
    }
 
    @Override
    protected void onStop() {
    	super.onStop();
    }
}
