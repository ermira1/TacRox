package com.ermira.raisa.tacrox;

import java.io.IOException;

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
import android.view.View;

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
		 mp = MediaPlayer.create(context, R.raw.yeehaaa);
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
	/*sm= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
		Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		sm.registerListener(this,s ,SensorManager.SENSOR_DELAY_NORMAL);
	}*/
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

//x=y=sensorX=sensorY=0;

myView= new DrawBalloon (this);
myView.resume();
setContentView(myView);
}



/*@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}



@Override
public void onSensorChanged(SensorEvent event) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(20);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sensorX=event.values[0];
	sensorY=event.values[1];
	
}

*/
public void updateMic(){
	if(mic!=null){
long level = mic.getLevel();
	
	sensorY+=level;
	}
}



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

/*public int getSizeX(){
WindowManager w = this.getWindowManager();
Display d = w.getDefaultDisplay();
DisplayMetrics metrics = new DisplayMetrics();
d.getMetrics(metrics);
// since SDK_INT = 1;
int widthPixels = metrics.widthPixels;


if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17){
try {
    widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
    
} catch (Exception ignored) {
}
}
// includes window decorations (statusbar bar/menu bar)
if (Build.VERSION.SDK_INT >= 17){
try {
    Point realSize = new Point();
    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
    widthPixels = realSize.x;
    
} catch (Exception ignored) {
}
}

int x=widthPixels;
return x;

}

public int getSizeY(){
WindowManager w = this.getWindowManager();
Display d = w.getDefaultDisplay();
DisplayMetrics metrics = new DisplayMetrics();
d.getMetrics(metrics);
// since SDK_INT = 1;

int heightPixels = metrics.heightPixels;

if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17){
try {
   
    heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
} catch (Exception ignored) {
}
}
// includes window decorations (statusbar bar/menu bar)
if (Build.VERSION.SDK_INT >= 17){
try {
    Point realSize = new Point();
    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
    
    heightPixels = realSize.y;
} catch (Exception ignored) {
}
}


int y=heightPixels;
return y;
}*/

private void checkCollisions(Canvas canvas){
	//Rect b_ship = ship.copyBounds();
//	Rect b_ob1 = ob1.copyBounds();
//	Rect b_ob2 = ob2.copyBounds();
//	if (b_ship.top <= b_ob1.bottom && b_ship.left <= b_ob1.right){
//		// Hits ob1
//    	resetGraphics(canvas);
//	}
//	if (){
//		//Hits ob2
//    	resetGraphics(canvas);
//	}
    //if (b_ship.top <= 25 && b_ship.left <= SCREEN_WIDTH/2+50 && b_ship.left >= SCREEN_WIDTH/2-50){
    	// Wins!
	foto=BitmapFactory.decodeResource(getResources(), k);
	 _foto = getResizedBitmap(foto,2*height/6,(width*4)/5);

		if(sensorY<=height-20&&sensorY>=height-50){
	       
	       
	       canvas.drawBitmap(_foto, (width-_foto.getWidth()),0, null);
	       
		   mp.start();
		   count=true;
	}else{
		if (sensorY>height-40){
		       canvas.drawBitmap(_foto,(width-_foto.getWidth()),0, null);
		       if(!count){
		    	   mp.start();
		    	   count=true;
		       }

		}
	}
}



@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	
	mic.stopMic();
	  Intent objIntent = new Intent(getApplication(), MainActivity.class);
	  myView.pause();
	  startActivity(objIntent);
	  
	 }

}

