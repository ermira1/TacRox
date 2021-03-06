package com.ermira.raisa.tacrox;

import java.util.HashMap;
import com.ermira.raisa.tacrox.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//This class edits the quotes
public class EditQuote extends Activity{
 
   EditText textQuote;
   EditText textAuthor;
   
   Database quotedatabase = new Database(this);
   
   public void onCreate(Bundle savedInstanceState){
    
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.edit_quote);
	   textQuote = (EditText) findViewById(R.id.textQuote);
	   textAuthor = (EditText) findViewById(R.id.textAuthor);
	   Intent theIntent = getIntent();
	   String quoteId = theIntent.getStringExtra("quoteId");
	   HashMap<String, String> itemList = quotedatabase.getItemInfo(quoteId);
	   if(itemList.size() != 0){
		   textQuote.setText(itemList.get("textQuote"));
		   textAuthor.setText(itemList.get("textAuthor"));
          
	   }
   }
   
   
   //This method is used to edit quote list
   public void saveItem(View view){
	   HashMap<String, String> queryValuesMap = new HashMap<String, String>();
	   textQuote = (EditText) findViewById(R.id.textQuote);
	   textAuthor = (EditText) findViewById(R.id.textAuthor);
	   Intent theIntent = getIntent();
	   String quoteId = theIntent.getStringExtra("quoteId");
	   if(textQuote.getText().toString().length()<30){
		   new AlertDialog.Builder(this)
		   .setIcon(android.R.drawable.ic_dialog_alert)
		   .setTitle(getResources().getString(R.string.poptitle))
		   .setMessage(getResources().getString(R.string.popmessage))
		   .setPositiveButton("OK", null)
		   .show();
	   }
    	else{
    
    		queryValuesMap.put("quoteId", quoteId);
    		queryValuesMap.put("textQuote", textQuote.getText().toString());
    		queryValuesMap.put("textAuthor", textAuthor.getText().toString());
    		quotedatabase.updateItem(queryValuesMap);
    		this.callQuote(view);
    	}
    
   	}

   //This method finishes this activity and calls Qoute activity
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