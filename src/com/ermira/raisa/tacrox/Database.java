package com.ermira.raisa.tacrox;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
 
 public Database(Context quoteContext){
 
 super(quoteContext, "quoteDB", null, 1);
 
 }

 @Override
 //Create database
 public void onCreate(SQLiteDatabase database) {
   
  String query = "CREATE TABLE quotelist ( quoteId INTEGER PRIMARY KEY, textQuote TEXT ,textAuthor TEXT)";
  String query1 = "INSERT INTO quotelist  VALUES ( '1', 'RAI', 'uku')";
  String query2 = "INSERT INTO quotelist  VALUES ( '2', 'RAISA', 'muka')";

    
    database.execSQL(query);
    database.execSQL(query1);
    database.execSQL(query2);

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

//Delete quotes into the database

public void deleteItem(String id){
 
 SQLiteDatabase database = this.getWritableDatabase();
 
 String deleteQuery = "DELETE FROM quotelist WHERE quoteId='" + id + "'";
 
 database.execSQL(deleteQuery);
 database.close();

 
}

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
public int getQuotesCount() {
    String countQuery = "SELECT  * FROM  quotelist" ;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);

    // return count
    return cursor.getCount();
}
}