package chaz.org.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chaz on 3/11/2015.
 */
public class HunterRepo {
    private DBHelper dbHelper;

    public HunterRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Hunter hunter) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Hunter.KEY_weapon,hunter.weapon);
        values.put(Hunter.KEY_name, hunter.name);

        // Inserting Row
        long student_Id = db.insert(Hunter.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Hunter.TABLE, Hunter.KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(Hunter hunter) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Hunter.KEY_weapon, hunter.weapon);
        values.put(Hunter.KEY_name, hunter.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Hunter.TABLE, values, Hunter.KEY_ID + "= ?", new String[] { String.valueOf(hunter.hunter_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Hunter.KEY_ID + "," +
                Hunter.KEY_name + "," +
                Hunter.KEY_weapon + "," +
                " FROM " + Hunter.TABLE;

        //Hunter hunter = new Huntert();
        ArrayList<HashMap<String, String>> hunterList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hunter = new HashMap<String, String>();
                hunter.put("id", cursor.getString(cursor.getColumnIndex(Hunter.KEY_ID)));
                hunter.put("name", cursor.getString(cursor.getColumnIndex(Hunter.KEY_name)));
                hunterList.add(hunter);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return hunterList;

    }

    public Hunter getHunterById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Hunter.KEY_ID + "," +
                Hunter.KEY_name + "," +
                Hunter.KEY_weapon + "," +
                " FROM " + Hunter.TABLE
                + " WHERE " +
                Hunter.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Hunter hunter = new Hunter();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                hunter.hunter_ID =cursor.getInt(cursor.getColumnIndex(Hunter.KEY_ID));
                hunter.name =cursor.getString(cursor.getColumnIndex(Hunter.KEY_name));
                hunter.weapon  =cursor.getString(cursor.getColumnIndex(Hunter.KEY_weapon));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return hunter;
    }

}

