package com.example.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Surface extends SurfaceView implements SurfaceHolder.Callback {
	
	public Logic logic;
	private Paint paintWhite;
	private Paint paintBlack;
	private Bitmap box;
	private Bitmap enemie;
	private Bitmap sky;
	private BitmapFactory.Options options;
	
	public GameThread mThread;
	
	public Surface(Context context, Logic log) {
        super(context);
        logic = log;
        createPaint();
        createBitmap();
        getHolder().addCallback(this);
    }
    
    protected void onDraw(Canvas canvas) {  
    	canvas.drawBitmap(sky,0, 0, null);
    	for(int i = 0; i <= logic.getNumberHeight(); i++) {
    		canvas.drawBitmap(enemie,-logic.getWidthEnemies()/2, logic.wallArray.get(i), null);
    		canvas.drawBitmap(enemie,logic.getWidth() - logic.getWidthEnemies()/2, logic.wallArray.get(i), null);
    	}
    	for(int i = 0; i < logic.slowEnemies._slowEnemiesX.size(); i++) {
    		canvas.drawBitmap(enemie,logic.slowEnemies._slowEnemiesX.get(i), logic.slowEnemies._slowEnemiesY.get(i), null);
    	}
    	for(int i = 0; i < logic.longEnemies.longEnemiesArrayX.size(); i++) {
    		canvas.drawBitmap(enemie,logic.longEnemies.longEnemiesArrayX.get(i), logic.longEnemies.longEnemiesArrayY.get(i), null);
    	}
    	for(int i = 0; i < logic.moveEnemies.moveEnemiesArrayX.size(); i++) {
    		canvas.drawBitmap(enemie,logic.moveEnemies.moveEnemiesArrayX.get(i), logic.moveEnemies.moveEnemiesArrayY.get(i), null);
    	}
    	canvas.drawBitmap(box, (int)logic.getXBox(), (int)logic.getYBox(), null);
    }	
    
    private void createPaint() {
    	paintWhite = new Paint();
		paintWhite.setColor(Color.WHITE);
		paintBlack = new Paint();
		paintBlack.setColor(Color.BLACK);
    }
    
    @SuppressWarnings("deprecation")
	private void createBitmap() {
    	options = new BitmapFactory.Options();
    	options.inPurgeable = true;
        
        box = BitmapFactory.decodeResource(getResources(), R.drawable.romb, options);
        box = Bitmap.createScaledBitmap(box, logic.getSizeBox(), logic.getSizeBox(), true);
        sky = BitmapFactory.decodeResource(getResources(), R.drawable.sky, options);
        sky = Bitmap.createScaledBitmap(sky, logic.getWidth(), logic.getHeight(), true);
        enemie = BitmapFactory.decodeResource(getResources(), R.drawable.wall, options);
        enemie = Bitmap.createScaledBitmap(enemie, logic.getWidthEnemies(), logic.getWidthEnemies(), true);
    }
    
    public void surfaceDestroyed(SurfaceHolder holder) 
    {
	    boolean retry = true;
	    mThread.setRunning(false);
	    while (retry)
	    {
	        try
	        {
	            mThread.join();
	            retry = false;
	        }
	        catch (InterruptedException e) { }
	    }
    }

    public void surfaceCreated(SurfaceHolder holder) 
    {
    		mThread = new GameThread(getHolder(), this);
        	mThread.setRunning(true);
        	mThread.start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
    {

    }
    
    public class GameThread extends Thread
    {
        private SurfaceView view;	 
        private SurfaceHolder surfaceHolder;
        public boolean running = false;
        
        public GameThread(SurfaceHolder surfaceHolder, SurfaceView view) 
        {
        	  this.surfaceHolder = surfaceHolder;
              this.view = view;
        }

        public void setRunning(boolean run) 
        {
              running = run;
        }

        @SuppressLint("WrongCall")
		public void run()
        {
        	Canvas canvas;
            while (running)
            {
            	canvas = null;
                try
                {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (view.getHolder())
                    {
                        onDraw(canvas);
                        if(logic.getCanGame() && !logic.getClickPause()) 
                        {
                        	logic.moveBox();
                        	logic.moveEnemies.moveMoveEnemiesX();
                        }
                    }
                }
                catch (Exception e) { }
                finally
                {
                    if (canvas != null)
                    {
                    	try
                    	{
                    		surfaceHolder.unlockCanvasAndPost(canvas);
                    	}catch(Exception e)
                    	{
                    		Log.d("TAG", "Îøèáêà: " + e);
                    	}
                	}
                }
            }
        }
    }
}