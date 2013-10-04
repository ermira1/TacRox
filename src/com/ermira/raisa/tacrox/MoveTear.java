package com.ermira.raisa.tacrox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//This class uses canvas to draw on the screen and the microphone to move the image on the screen
public class MoveTear extends Activity {
	Bitmap balloon;
	Bitmap _balloon;
	Bitmap background;
    Bitmap _background;
    Bitmap buble;
    Bitmap _buble;

    int i,j,k,l;
    private MediaPlayer mp;
    Bitmap foto;
    Bitmap _foto;

	DrawBalloon myView;
	long x,y,sensorX, sensorY;
	SensorManager sm;
	Microphone mic;
	int width, height;
	boolean count=false;
	
	



public class DrawBalloon extends SurfaceView implements Runnable {
	
	SurfaceHolder ourHolder ;
	Thread ourThread = null;
	boolean isRunning=true;
	
	public DrawBalloon(Context context) {
		super(context);
		 mp = MediaPlayer.create(context, R.raw.cry);
		ourHolder= getHolder();
		
	}
	
	public void pause() {
		isRunning=false;
		while(true){
			try{
				ourThread.join();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			break;
		}
		ourThread=null;
		finish();
	}
	
	public void resume(){
		isRunning=true;
		ourThread = new Thread(this);
		ourThread.start();
		
		
	}
	@Override
	public void run (){
		
		while(isRunning){
			if(!ourHolder.getSurface().isValid())
				continue;
            Canvas canvas = ourHolder.lockCanvas();
            
           
         if(canvas!=null){
			updateMic();
			canvas.drawBitmap(_background,0,0,null);
			if(!count){
			canvas.drawBitmap(_buble,0,0,null);
			}
			canvas.drawBitmap(_balloon, sensorX, sensorY,null);
			checkCollisions(canvas);
			ourHolder.unlockCanvasAndPost(canvas);
         }
		}
	}
}



@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	Intent intent= getIntent();
	i=intent.getIntExtra("b1", 0);
	j=intent.getIntExtra("b2", 0);
	k=intent.getIntExtra("b3", 0);
	l=intent.getIntExtra("b4", 0);
	mic = new Microphone();
	balloon = BitmapFactory.decodeResource(getResources(), i);
	background = BitmapFactory.decodeResource(getResources(), j);
	buble = BitmapFactory.decodeResource(getResources(), l);

	DisplayMetrics displaymetrics = new DisplayMetrics();

	getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	height = displaymetrics.heightPixels;
	width = displaymetrics.widthPixels; 
	_background = getResizedBitmap(background,height,width);
	_balloon = getResizedBitmap(balloon,height/12,width/14);
	_buble=getResizedBitmap(buble, (width-_balloon.getWidth())/3, (width-_balloon.getWidth())/3);
	sensorX=width/4;
	sensorY=3*height/5;

	myView= new DrawBalloon (this);
	myView.resume();
	setContentView(myView);
}

  //This method returns the max amplitude recorded by the microphone
  public void updateMic(){
	  if(mic!=null){
		  long level = mic.getLevel();
		  sensorY+=level;
	  }
	}


   //This method returns a resized bitmap
   public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	   int width = bm.getWidth();
	   int height = bm.getHeight();
	   float scaleWidth = ((float) newWidth) / width;
	   float scaleHeight = ((float) newHeight) / height;

	   // Create a matrix for the manipulation
	   Matrix matrix = new Matrix();

    	// Resize the bit map
	   matrix.postScale(scaleWidth, scaleHeight);

	   // Recreate the new Bitmap
   	   Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
   	   return resizedBitmap;
   }

 //This method checks if the image has reached the desired coordinates
  private void checkCollisions(Canvas canvas){

	  foto=BitmapFactory.decodeResource(getResources(), k);
	  _foto = getResizedBitmap(foto,2*height/6,(width*4)/5);
	  if(sensorY<=height-20&&sensorY>=height-50){
		  canvas.drawBitmap(_foto, (width-_foto.getWidth()),0, null);
	      mp.start();
		  count=true;
	  }
	  else{
		  
		if (sensorY>height-40){
		       canvas.drawBitmap(_foto,(width-_foto.getWidth()),0, null);
		       if(!count){
		    	   mp.start();
		    	   count=true;
		       }

		}
	}
}


  //This method finishes this activity, stops the microphone and media player and calls MainActivity activity 
  @Override
  public void onBackPressed() {
	// TODO Auto-generated method stub
	  mic.stopMic();
	  Intent objIntent = new Intent(getApplication(), MainActivity.class);
	  mp.stop();
	  myView.pause();
	  startActivity(objIntent);
	  
	 }

}

