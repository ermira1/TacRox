package com.ermira.raisa.tacrox;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StartingApp extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.starting_app);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openWeb(View view){
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://quotelicious.com/"));
	    startActivity(i);
	}
	public void showQuoteList(View view){
		Intent intent = new Intent(getApplicationContext(), Quote.class);

		finish();
		startActivity(intent);
		
		
	}
	public void showMainActivity(View view){
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);

		finish();
		startActivity(intent);
		
		
	}
}
