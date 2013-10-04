package com.ermira.raisa.tacrox;

import java.util.HashMap;
import com.ermira.raisa.tacrox.Database;
import com.ermira.raisa.tacrox.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//This class adds quotes that the use inserts in EditText fields in the database
public class AddQuote extends Activity {
 
 // The EditText objects
  EditText textQuote;
  EditText textAuthor;
    
  Database quotedatabase = new Database (this);//use the database that we have created

   @Override
   public void onCreate(Bundle savedInstanceState) {

	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.add_quote);
	   textQuote = (EditText) findViewById(R.id.textQuoteAdd);
	   textAuthor = (EditText) findViewById(R.id.textAuthorAdd);
 
 }


   //The method that adds quotes
   public void addNewItems(View view) {
 
	   HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();
	   if(textQuote.getText().toString().length()<30){
		   
		   new AlertDialog.Builder(this)
		   .setIcon(android.R.drawable.ic_dialog_alert)
		   .setTitle(getResources().getString(R.string.poptitle))
		   .setMessage(getResources().getString(R.string.popmessage))
		   .setPositiveButton("OK", null)
		   .show();
       }
	   
	   else{
		   queryValuesMap.put("textQuote", textQuote.getText().toString());
		   if(textAuthor.getText().toString().length()==0){
		   queryValuesMap.put("textAuthor",getResources().getString(R.string.nauthor));
            }
		   else{
			   queryValuesMap.put("textAuthor",textAuthor.getText().toString());
		   }
 
		   quotedatabase.insertItem(queryValuesMap);//add into database
		   this.callQuote(view);
	   }
   }
   
   
   //This method finishes this activity and starts Quote activity
   public void callQuote(View view) {
	   Intent theIntent = new Intent(getApplicationContext(), Quote.class);
	   finish();
	   startActivity(theIntent);
   } 

 //This method finishes this activity and starts Quote activity when back button is pressed
   public void onBackPressed() {
	   Intent backIntent = new Intent(getApplication(), Quote.class);
	   finish();
	   startActivity(backIntent);
      
   }
}