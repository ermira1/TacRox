package com.ermira.raisa.tacrox;

/**This class is the page of quotes*/

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import com.ermira.raisa.tacrox.Database;
import com.ermira.raisa.tacrox.AddQuote;
import com.ermira.raisa.tacrox.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.ListView;


public class Quote extends ListActivity {

 Intent intent;
 TextView quoteId;

 Database quotedatabase = new Database(this);

 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

  setContentView(R.layout.quote_main);//define that the interface used is quote_main
  
  //Store data from database in an array list
  
  ArrayList<HashMap<String, String>> quoteList =  quotedatabase.getAllItemsRed();

  //Check if there are quotes to display
  if(quoteList.size()!=0) {
   
   ListView listView = getListView();

   listView.setOnItemClickListener(new OnItemClickListener() {
    
    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
    	
     
        
     quoteId = (TextView) view.findViewById(R.id.quoteId);
     
     String itemIdValue = quoteId.getText().toString(); 
     
     Intent  theIndent = new Intent(getApplication(),ShowQuote.class);
     
        
     theIndent.putExtra("quoteId", itemIdValue); 
     
     finish();
     
     startActivity(theIndent); 
     
         }
   }); 
   
   // Here we use ListAdapter as a bridge between ListView and the data of ListView
   
   ListAdapter adapter = new SimpleAdapter( Quote.this,quoteList, R.layout.quote_entry, new String[] { "quoteId","textQuote"}, new int[] {R.id.quoteId, R.id.textQuote});

   setListAdapter(adapter);
  }
   
  }
  
 
 
 //When we press button that calls showAddItem activity then AddNewItems is called 
  
 public void showAddItem(View view) {
  Intent theIntent = new Intent(getApplicationContext(), AddQuote.class);
  finish();
  startActivity(theIntent);
 }
 public void slideQuote(View view){
	  Intent theIntent = new Intent(getApplicationContext(), SlideQuote.class);
	  finish();
	  startActivity(theIntent);

	  
 }

@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
    Intent backIntent = new Intent(getApplication(), MainActivity.class);
    finish();
    startActivity(backIntent);
}
}
 
 
 
 
