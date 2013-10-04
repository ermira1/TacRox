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
	  String query1 = "INSERT INTO quotelist  VALUES ( '1', 'When life gives you a hundred reasons to cry, show life that you have a thousand reasons to smile.', 'Unknown author')";
	  String query2 = "INSERT INTO quotelist  VALUES ( '2', 'The time to relax is when you don`t have time for it.', 'Sydney J. Harris ')";
	  String query3 = "INSERT INTO quotelist  VALUES ( '3', 'Don`t cry because it`s over, smile because it happened.', 'Dr. Seuss')";
	  String query4 = "INSERT INTO quotelist  VALUES ( '4', 'Two things are infinite: the universe and human stupidity, and I`m not sure about the universe.', ' Albert Einstein')";
	  String query5 = "INSERT INTO quotelist  VALUES ( '5', 'I always like walking in the rain, so no one can see me crying.', 'Charly Chaplin ')";
	  String query6 = "INSERT INTO quotelist  VALUES ( '6', 'Ask yourself this question:Will this matter a year from now?', 'Richard Carlson')";
	  String query7 = "INSERT INTO quotelist  VALUES ( '7', 'Never tell your problems to anyone...20% don`t care and the other 80% are glad you have them.', 'Lou Holtz')";
	  
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