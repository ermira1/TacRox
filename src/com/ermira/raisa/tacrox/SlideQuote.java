package com.ermira.raisa.tacrox;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.TextView;

//This class uses touch sensor to change the quote on the screen
public class SlideQuote extends Activity {
	
	private TextView quote;
	private TextView author;
	float xLast,xCurrent;
	int id;
	int total;
	float x;
	boolean first=true;
	String s1,s2;
	Database quotedatabase = new Database(this);
	int height;
	int width;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_quote);
        total=quotedatabase.getQuotesCount();
        //get the TextView from the layout file
        quote = (TextView) findViewById(R.id.textView3);
        author = (TextView) findViewById(R.id.textView4);
        
        id=1;
        
        s1= quotedatabase.getQuote(id);
        s2= quotedatabase.getAuthor(id);
        
        DisplayMetrics displaymetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels; 

        quote.setText(s1);
        author.setText(s2);
    
    }
    
    //when this Activity starts
    @Override
  protected void onResume(){
    	super.onResume();
    }
  
  @Override
  protected void onStop(){
	  super.onStop();
 }

 //slides the quotes on the screen if the user touches the screen on its left or on its right
 @Override
 public boolean onTouchEvent(MotionEvent event){ 
	 float eventX = event.getX();
	 int action = MotionEventCompat.getActionMasked(event);
	 switch(action) {
	 case (MotionEvent.ACTION_DOWN) :
		 if(eventX>0&&eventX<width/3){
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
		 	else{
		 		if(eventX>2*width/3&&eventX<width){
		 			if(id==1)
		 			{id=total;
		 			}
		 			else{
          
		 				id--;
		 			}
		 			s1= quotedatabase.getQuote(id);
		 			s2= quotedatabase.getAuthor(id);
		 			quote.setText(s1);
		 			author.setText(s2);
        	
		 		}
		 	}
		
	 return true;
	 case (MotionEvent.ACTION_MOVE) :
		 return true;
	 case (MotionEvent.ACTION_UP) :
		 return true;
	 case (MotionEvent.ACTION_CANCEL) :
	     return true;
	 case (MotionEvent.ACTION_OUTSIDE) :
		 return true;      
	 default : 
		 return super.onTouchEvent(event);
	 }      
	}
 
 
//Finishes this activity and calls Quote activity when back button is pressed
  @Override
  public void onBackPressed() {
   // TODO Auto-generated method stub
	  Intent backIntent = new Intent(getApplication(), Quote.class);
	  finish();
      startActivity(backIntent);
  
  }

 
 }