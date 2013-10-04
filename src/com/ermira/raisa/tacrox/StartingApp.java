package com.ermira.raisa.tacrox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
//This is the main activity of the app
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
	
	//This method opens a website using the device's browser
	public void openWeb(View view){
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://quotelicious.com/"));
	    startActivity(i);
	}
	
	//This method calls the activity that displays the quotes stored in local database
	public void showQuoteList(View view){
		Intent intent = new Intent(getApplicationContext(), Quote.class);
        finish();
		startActivity(intent);
		
	}
	
	//This method calls another activity that from where the user can choose his mood
	public void showMainActivity(View view){
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
		startActivity(intent);
	
	}
	
	//This method shows a popup message and closes the app if the user clicks YES.
	@Override
	  public void onBackPressed() {
	      new AlertDialog.Builder(this)
	          .setIcon(android.R.drawable.ic_dialog_alert)
	          .setTitle(getResources().getString(R.string.close))
	          .setMessage(getResources().getString(R.string.question))
	          .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener()
	      {
	          @Override
	          public void onClick(DialogInterface dialog, int which) {
	              finish();    
	          }
            })
	      .setNegativeButton(getResources().getString(R.string.no), null)
	      .show();
	  }
	 
}

