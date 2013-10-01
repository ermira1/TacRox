package com.ermira.raisa.tacrox;

import java.util.HashMap;
import com.ermira.raisa.tacrox.MainActivity;
import com.ermira.raisa.tacrox.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


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
    
    queryValuesMap.put("quoteId", quoteId);
    queryValuesMap.put("textQuote", textQuote.getText().toString());
    queryValuesMap.put("textAuthor", textAuthor.getText().toString());
        
    quotedatabase.updateItem(queryValuesMap);
    this.callQuote(view);
    
   }

   
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