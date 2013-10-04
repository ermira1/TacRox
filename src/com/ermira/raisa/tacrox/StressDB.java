package com.ermira.raisa.tacrox;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StressDB extends SQLiteOpenHelper {
 
 public StressDB(Context stressContext){
 
 super(stressContext, "stressDB", null, 1);
 
 }

 @Override
 //Create database
 public void onCreate(SQLiteDatabase database) {

	   
	  String query = "CREATE TABLE quotelist ( quoteId INTEGER PRIMARY KEY, textQuote TEXT ,textAuthor TEXT)";
	  String query1 = "INSERT INTO quotelist  VALUES ( '1', 'The greatest weapon against stress is our ability to choose one thought over another.', 'William James')";
	  String query2 = "INSERT INTO quotelist  VALUES ( '2', 'The time to relax is when you don`t have time for it.You must learn to let go. Release the stress. You were never in control anyway.', 'Steve Maraboli')";
	  String query3 = "INSERT INTO quotelist  VALUES ( '3', 'When things go wrong, don`t go with them.', 'Elvis Presley')";
	  String query4 = "INSERT INTO quotelist  VALUES ( '4', 'If you treat every situation as a life and death matter, you`ll die a lot of times.', 'Dean Smith')";
	  String query5 = "INSERT INTO quotelist  VALUES ( '5', 'Give your stress wings and let it fly away.', 'Terri Guillemets')";
	  String query6 = "INSERT INTO quotelist  VALUES ( '6', 'There`s never enough time to do all the nothing you want.', 'Bill Watterson')";
	  String query7 = "INSERT INTO quotelist  VALUES ( '7', 'Stress is an ignorant state.  It believes that everything is an emergency.', 'Natalie Goldberg')";
	  
	    database.execSQL(query);
	    database.execSQL(query1);
	    database.execSQL(query2);
	    database.execSQL(query3);
	    database.execSQL(query4);
	    database.execSQL(query5);
	    database.execSQL(query6);
	    database.execSQL(query7);
	    

	 }
   
  
 

 @Override
 public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
     
   String query = "DROP TABLE IF EXISTS quotelist";
   
   database.execSQL(query);
   onCreate(database);
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