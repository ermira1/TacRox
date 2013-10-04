package com.ermira.raisa.tacrox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

//This class displays a layout with buttons where the user can click the button that matches his mood

public class MainActivity extends Activity {
DrawBalloon myBalloon;
int b1;
int b2;
int b3;
int b4;
int b5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//This method calls MoveBalloon activity and passes some images and audio ids to this activity
	public void showBalloon(View view){
		b1 = R.drawable.balloon;
		b2=R.drawable.sky;
	    b3=R.drawable.quote;
	    b4=R.drawable.buble2;
	    b5=R.raw.happy;
	    Intent intent = new Intent(getApplicationContext(), MoveBalloon.class);
	    intent.putExtra("b1", b1);
	    intent.putExtra("b2", b2);
	    intent.putExtra("b3", b3);
	    intent.putExtra("b4", b4);
		intent.putExtra("b5", b5);
		finish();
		startActivity(intent);
		
	}
	
	
	//This method calls MoveBalloon activity and passes some images and audio ids to this activity
	public void showStar(View view){
		b1 = R.drawable.star;
		b2=R.drawable.bmoon;
	    b3=R.drawable.moonwrite;
	    b4=R.drawable.buble1;
        b5=R.raw.sad;
        Intent intent = new Intent(getApplicationContext(), MoveBalloon.class);
        intent.putExtra("b1", b1);
        intent.putExtra("b2", b2);
        intent.putExtra("b3", b3);
        intent.putExtra("b4", b4);
        intent.putExtra("b5", b5);
        finish();
        startActivity(intent);
	}
	
	
	//This method calls MoveShip activity and passes some images id to this activity
	public void showShip(View view){
		b1 = R.drawable.ship;
		b2=R.drawable.sea3;
	    b3=R.drawable.frame4;
	    b4=R.drawable.say3;
	    Intent intent = new Intent(getApplicationContext(), MoveShip.class);
	    intent.putExtra("b1", b1);
	    intent.putExtra("b2", b2);
	    intent.putExtra("b3", b3);
	    intent.putExtra("b4", b4);
	    finish();
	    startActivity(intent);
	}
	
	
	//This method calls MoveTear activity and passes some images id to this activity
	public void showTear(View view){
		b1 = R.drawable.tear;
		b2=R.drawable.face3;
	    b3=R.drawable.frame3;
	    b4=R.drawable.buble3;
	    Intent intent = new Intent(getApplicationContext(), MoveTear.class);
	    intent.putExtra("b1", b1);
	    intent.putExtra("b2", b2);
	    intent.putExtra("b3", b3);
	    intent.putExtra("b4", b4);
	    finish();
	    startActivity(intent);
	}
	
	
	//This method calls MoveCloud activity and passes some images id to this activity
	public void showCloud(View view){
		b1 = R.drawable.cloud2;
		b2=R.drawable.back1;
	    b3=R.drawable.cloudframe;
	    b4=R.drawable.cloudbuble;
	    Intent intent = new Intent(getApplicationContext(), MoveCloud.class);
	    intent.putExtra("b1", b1);
		intent.putExtra("b2", b2);
		intent.putExtra("b3", b3);
		intent.putExtra("b4", b4);
		finish();
		startActivity(intent);
	}
	
	
	//This method calls Quote activity
	public void showQuoteList(View view){
		Intent intent = new Intent(getApplicationContext(), Quote.class);
        finish();
		startActivity(intent);
	}
	
	//This method calls Accelerometer activity
	public void showStress(View view){
		Intent objIntent = new Intent(getApplication(), Accelerometer.class);
		finish();
		startActivity(objIntent);
		
	}
	
	
	//This method finishes this activity and starts StartingApp activity when back button is pressed
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent objIntent = new Intent(getApplication(), StartingApp.class);
		finish();
		startActivity(objIntent);
		  
	}
	
}
