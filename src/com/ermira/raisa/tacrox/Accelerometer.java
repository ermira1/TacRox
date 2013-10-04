package com.ermira.raisa.tacrox;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

//This class is an activity that uses accelerometer to change the quotes in the screen 
public class Accelerometer extends Activity implements SensorEventListener{
	
private MediaPlayer mp;
private float mLastX, mLastY, mLastZ;
private boolean mInitialized;
private final float NOISE = (float) 4.0;
private TextView quote;
private TextView author;
private SensorManager sManager;
float xLast,xCurrent;
int id;
int total;
float x;
boolean first=false;
String s1,s2;

StressDB quotedatabase = new StressDB(this);


   
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stres_quote);
        mInitialized = false;
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(getResources().getString(R.string.poptitle1))
        .setMessage(getResources().getString(R.string.popmessage1))
        .setPositiveButton("OK", null)
        .show();
        
        //get the TextView from the layout file
        quote = (TextView) findViewById(R.id.textView3);
        author = (TextView) findViewById(R.id.textView4);
        id=1;
        
        //get a hook to the sensor service
        
        sManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
        Sensor s =sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        sManager.registerListener(this,s ,SensorManager.SENSOR_DELAY_NORMAL);
        mp=MediaPlayer.create(getApplicationContext(), R.raw.stress);
        mp.start();
        s1= quotedatabase.getQuote(id);
        s2= quotedatabase.getAuthor(id);
        total=quotedatabase.getQuotesCount();

        quote.setText(s1);
      	author.setText(s2);
      	
      	
    }
    }
    
    
    
    //when this Activity starts
    @Override
 protected void onResume(){
  super.onResume();
  sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
 }

  //When this Activity isn't visible anymore
 @Override
 protected void onStop(){
  //unregister the sensor listener
  sManager.unregisterListener(this);
  super.onStop();
 }

 
 @Override
 public void onAccuracyChanged(Sensor arg0, int arg1){
  //Do nothing.
 }
 
 
//When the user shakes the phone the quotes in the database are shown one by one in the screen
 @Override
 public void onSensorChanged(SensorEvent event){
	 float x = event.values[0];
	 float y = event.values[1];
 	 float z = event.values[2];
 	 if (!mInitialized) {
     mLastX = x;
     mLastY = y;
     mLastZ = z;
     mInitialized = true;
 }
   else {if(x>0){
  
                }
         else{
        	float deltaX = Math.abs(mLastX - x);
        	float deltaY = Math.abs(mLastY - y);
        	float deltaZ = Math.abs(mLastZ - z);
        	if (deltaX < NOISE) deltaX = (float)0.0;
        	if (deltaY < NOISE) deltaY = (float)0.0;
        	if (deltaZ < NOISE) deltaZ = (float)0.0;
        	mLastX = x;
        	mLastY = y;
        	mLastZ = z;
        	if (deltaX + deltaY+deltaZ>15) {
        		if(id==total)
        		{id=1;
        		}
        		else{
        			id++;
        			}
        		s1= quotedatabase.getQuote(id);
        		s2= quotedatabase.getAuthor(id);
        		quote.setText(s1);
        		author.setText(s2);
          }
       } 
    }
  }
 @Override
 
 //finish this activity and start the MainActivity activity when the back button is pressed
 public void onBackPressed() {
  // TODO Auto-generated method stub
     sManager.unregisterListener(this);
     id=1;
     Intent backIntent = new Intent(getApplication(), MainActivity.class);
  	 mp.stop();
     finish();
     startActivity(backIntent);
  
     }

 
 }