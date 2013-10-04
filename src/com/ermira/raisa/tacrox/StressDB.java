package com.ermira.raisa.tacrox;


import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//This class is the second database of app
public class StressDB extends SQLiteOpenHelper {
	Context context;
 
	public StressDB(Context stressContext){
 
		super(stressContext, "stressDB", null, 1);
		this.context=stressContext;
 }

  @Override
 //Create database
 public void onCreate(SQLiteDatabase database) {
	 String s;
		try {
			Toast.makeText(context, "1", 2000).show();
			InputStream in = context.getResources().openRawResource(R.raw.sqlstress);
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
   
  
 
//Upgrades database
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
	  String query = "DROP TABLE IF EXISTS quotelist";
	  database.execSQL(query);
	  onCreate(database);
  }

  //returns the quote with the given id
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
  
//Returns the author of the quote with the given id
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

//Returns the number of rows in this database
public int getQuotesCount() {
    String countQuery = "SELECT  * FROM  quotelist" ;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);

    // return count
    return cursor.getCount();
}
}