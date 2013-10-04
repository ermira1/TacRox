package com.ermira.raisa.tacrox;

import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//This class shows the quote and the author in textviews
public class ShowQuote extends Activity {
	TextView textQuote;
	TextView textAuthor;
	String quoteId;
	Database quotedatabase = new Database(this);
	   
	   public void onCreate(Bundle savedInstanceState){
	    
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.show_quote);
	    textQuote = (TextView) findViewById(R.id.textView1);
	    textAuthor = (TextView) findViewById(R.id.textView2);
	    Intent theIntent = getIntent();
	    quoteId = theIntent.getStringExtra("quoteId");
	    HashMap<String, String> itemList = quotedatabase.getItemInfo(quoteId);
	    if(itemList.size() != 0){
	    	textQuote.setText(itemList.get("textQuote"));
	    	textAuthor.setText(itemList.get("textAuthor"));
	          
	    }
	   }
	   
	   //This method is used to edit quote list
	   
	   public void editItems(View view){
	    
		   Intent  theIntent = new Intent(getApplication(),EditQuote.class);
		   theIntent.putExtra("quoteId", quoteId); 
		   finish();
		   startActivity(theIntent); 
	}
	   
	   
	   //This method finishes this activity and calls Quote activity
	   public void callQuote(View view){
	    
		   Intent objIntent = new Intent(getApplication(), Quote.class);
		   finish();
		   startActivity(objIntent);
	    
	   }
	   
	   
	   //This method is used to exit activity when user press the back button   
	    public void onBackPressed() {
	           
	    	Intent backIntent = new Intent(getApplication(), Quote.class);
	    	finish();
	    	startActivity(backIntent);
	        
	    }
	   
 }

