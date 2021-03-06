package com.ermira.raisa.tacrox;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ermira.raisa.tacrox.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//This is the main database of the app
public class Database extends SQLiteOpenHelper {
 Locale loc;
 String lang;
 Context context;
 
   public Database(Context quoteContext){
 
    	super(quoteContext, "quoteDB", null, 1);
    	this.context=quoteContext;
 
    }

  @Override
  //Create database
  public void onCreate(SQLiteDatabase database) {
		String s;
		try {
			Toast.makeText(context, "1", 2000).show();
			InputStream in = context.getResources().openRawResource(R.raw.sqldb);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(in, null);
			NodeList statements = doc.getElementsByTagName("statement");
			for (int i=0; i<statements.getLength(); i++) {
				s = statements.item(i).getChildNodes().item(0).getNodeValue();
				database.execSQL(s);
			}
		} catch (Throwable t) {
			Toast.makeText(context, t.toString(), 50000).show();
		}
	}

   
 

 @Override
 public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
     
   String query = "DROP TABLE IF EXISTS quotelist";
   
   database.execSQL(query);
   onCreate(database);
  }
 
 //Insert quotes into database 
  public void insertItem(HashMap<String, String> queryValues){
  
	SQLiteDatabase database = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put("textQuote", queryValues.get("textQuote"));
  	values.put("textAuthor", queryValues.get("textAuthor"));
  	database.insert("quotelist", null, values);
  	database.close();
  
  }

//Update the quotes into the database

  public int updateItem(HashMap<String, String> queryValues) {
	  SQLiteDatabase database = this.getWritableDatabase();
	  ContentValues values = new ContentValues();
	  values.put("textQuote", queryValues.get("textQuote"));
	  values.put("textAuthor", queryValues.get("textAuthor"));
	  return database.update("quotelist", values, "quoteId" + " = ?", new String[] {queryValues.get("quoteId") });

  }


   
   //Gets all datas in the database
  public ArrayList<HashMap<String, String>> getAllItems(){
 
	  ArrayList<HashMap<String, String>> itemArrayList = new ArrayList<HashMap<String, String>>();
	  String selectQuery = "SELECT * FROM quotelist";
	  SQLiteDatabase database = this.getWritableDatabase();
	  Cursor cursor = database.rawQuery(selectQuery, null);
	  if(cursor.moveToFirst()){
		  do{
			  HashMap<String, String> itemMap = new HashMap<String, String>();
			  itemMap.put("quoteId", cursor.getString(0));
			  itemMap.put("textQuote", cursor.getString(1));
			  itemMap.put("textAuthor", cursor.getString(2));
			  itemArrayList.add(itemMap);
   
		  } while(cursor.moveToNext());
  
	  }
	  database.close();
	  return itemArrayList;
 
  }
  
  
  //Get all quotes in the database but reduced(only first 30 characters)
  public ArrayList<HashMap<String, String>> getAllItemsRed(){
	 
	  ArrayList<HashMap<String, String>> itemArrayList = new ArrayList<HashMap<String, String>>();
	  String selectQuery = "SELECT * FROM quotelist";
	  SQLiteDatabase database = this.getWritableDatabase();
	  Cursor cursor = database.rawQuery(selectQuery, null);
	  if(cursor.moveToFirst()){
		  do{
			  HashMap<String, String> itemMap = new HashMap<String, String>();
			  itemMap.put("quoteId", cursor.getString(0));
			  if(cursor.getString(1).length()>=30){
				  itemMap.put("textQuote", cursor.getString(1).substring(0, 30)+" ...");}
			  else{
				  itemMap.put("textQuote", cursor.getString(1));}
			  itemArrayList.add(itemMap);
	   
		  } while(cursor.moveToNext());
	  
	  }
	  database.close();
	  return itemArrayList;
	  }
  
  
  //Returns the quote and author with a given id in database
  public HashMap<String, String> getItemInfo(String id){
 
	  HashMap<String, String> itemMap = new HashMap<String, String>();
	  SQLiteDatabase database = this.getReadableDatabase();
	  String selectQuery = "SELECT * FROM quotelist WHERE quoteId='" + id + "'";
	  Cursor cursor = database.rawQuery(selectQuery, null);
	  if(cursor.moveToFirst()){
		  do{
			  itemMap.put("quoteId", cursor.getString(0));
			  itemMap.put("textQuote", cursor.getString(1));
			  itemMap.put("textAuthor", cursor.getString(2));
      
		  } while(cursor.moveToNext());
  
	  }
 
	  return itemMap;
  }

  
  //Returns the quote with a given id
  public String getQuote(int id) {
   
	  SQLiteDatabase db = this.getReadableDatabase();
	  Cursor cursor = db.query("quotelist", new String[] { "quoteId",
            "textQuote", "textAuthor" }, "quoteId" + "=?",
            new String[] { String.valueOf(id) }, null, null, null, null);
	  if (cursor != null)
        cursor.moveToFirst();
	  
	  String s1=cursor.getString(1);
	  return s1;

}

  
//Returns the author with a given id
 public String getAuthor(int id) {
    
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.query("quotelist", new String[] { "quoteId",
            "textQuote", "textAuthor" }, "quoteId" + "=?",
            new String[] { String.valueOf(id) }, null, null, null, null);
    if (cursor != null)
        cursor.moveToFirst();
        String s1=cursor.getString(2);
        return s1;
}

  //Returns the number of rows in the database
  public int getQuotesCount() {
	  
	   String countQuery = "SELECT  * FROM  quotelist" ;
	   SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery(countQuery, null);
    	// return count
    	return cursor.getCount();
   }
}