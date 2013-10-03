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

public class AddQuote extends Activity {
 
 // The EditText objects

  EditText textQuote;
  EditText textAuthor;
    
  Database quotedatabase = new Database (this);//use the database that we have created

@Override
public void onCreate(Bundle savedInstanceState) {

 super.onCreate(savedInstanceState);

 setContentView(R.layout.add_quote);//define the interface that is used, add_quote.xml

 
 textQuote = (EditText) findViewById(R.id.textQuoteAdd);
 textAuthor = (EditText) findViewById(R.id.textAuthorAdd);
 
 }

/**This is the method that adds quotes*/

public void addNewItems(View view) {
 
 HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();
if(textQuote.getText().toString().length()<30)
{
	 new AlertDialog.Builder(this)
     .setIcon(android.R.drawable.ic_dialog_alert)
     .setTitle("Be careful!")
     .setMessage("Please write more than 30 characters")
     .setPositiveButton("OK", null)


 .show();
}
else{
	

 queryValuesMap.put("textQuote", textQuote.getText().toString());
 queryValuesMap.put("textAuthor",textAuthor.getText().toString());
 
 
 
 quotedatabase.insertItem(queryValuesMap);//add into database

 

 this.callQuote(view);
}
}
public void callQuote(View view) {
 Intent theIntent = new Intent(getApplicationContext(), Quote.class);
 finish();
 startActivity(theIntent);
} 

public void onBackPressed() {
    
    Intent backIntent = new Intent(getApplication(), Quote.class);
    finish();
    startActivity(backIntent);
      
      }
}