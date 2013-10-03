package com.ermira.raisa.tacrox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends Activity implements SensorEventListener
{
  private float mLastX, mLastY, mLastZ;
     private boolean mInitialized;
     private final float NOISE = (float) 4.0;
 //a TextView
 private TextView quote;
 private TextView author;
 //the Sensor Manager
 private SensorManager sManager;
 float xLast,xCurrent;
 int id;
 int total;
 float x;
 boolean first=true;
 String s1,s2;
  StressDB quotedatabase = new StressDB(this);


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_quote);
        mInitialized = false;
        //get the TextView from the layout file
        quote = (TextView) findViewById(R.id.textView3);
        author = (TextView) findViewById(R.id.textView4);
id=1;
        //get a hook to the sensor service
        
        sManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
     if(sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
      Sensor s =sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
      sManager.registerListener(this,s ,SensorManager.SENSOR_DELAY_NORMAL);
      s1= quotedatabase.getQuote(id);
      s2= quotedatabase.getAuthor(id);
      total=quotedatabase.getQuotesCount();


      quote.setText(s1);
      author.setText(s2);
    }
    }
    //when this Activity starts
    @Override
 protected void onResume()
 {
  super.onResume();
  /*register the sensor listener to listen to the gyroscope sensor, use the
  callbacks defined in this class, and gather the sensor information as quick
  as possible*/
  sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
 }

  //When this Activity isn't visible anymore
 @Override
 protected void onStop()
 {
  //unregister the sensor listener
  sManager.unregisterListener(this);
  super.onStop();
 }

 @Override
 public void onAccuracyChanged(Sensor arg0, int arg1)
 {
  //Do nothing.
 }

 @Override
 public void onSensorChanged(SensorEvent event)
 { float x = event.values[0];
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
 else{float deltaX = Math.abs(mLastX - x);
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
 public void onBackPressed() {
  // TODO Auto-generated method stub
  sManager.unregisterListener(this);
  id=1;
  Intent backIntent = new Intent(getApplication(), MainActivity.class);
       finish();
       startActivity(backIntent);
  
 }

 
 }