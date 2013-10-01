package com.ermira.raisa.tacrox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
DrawBalloon myBalloon;
int b1;
int b2;
int b3;
int b4;
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
	
	public void showBalloon(View view){
		b1 = R.drawable.balloon;
		b2=R.drawable.sky;
	    b3=R.drawable.quote;
	    b4=R.drawable.buble2;
	Intent intent = new Intent(getApplicationContext(), MoveBalloon.class);
	intent.putExtra("b1", b1);
	intent.putExtra("b2", b2);
	intent.putExtra("b3", b3);
	intent.putExtra("b4", b4);

	finish();
	startActivity(intent);
	

	
	}
	public void showStar(View view){
		b1 = R.drawable.star;
		b2=R.drawable.bmoon;
	    b3=R.drawable.moonwrite;
	    b4=R.drawable.buble1;

	Intent intent = new Intent(getApplicationContext(), MoveBalloon.class);
	intent.putExtra("b1", b1);
	intent.putExtra("b2", b2);
	intent.putExtra("b3", b3);
	intent.putExtra("b4", b4);

	finish();
	startActivity(intent);
	

		
		
	}
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
	public void showQuoteList(View view){
		Intent intent = new Intent(getApplicationContext(), Quote.class);

		finish();
		startActivity(intent);
		
		
	}

}
