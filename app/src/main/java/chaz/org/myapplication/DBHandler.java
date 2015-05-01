package chaz.org.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chaz on 3/24/2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    //declares database constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    //declare table constants
    private static final String TABLE_HUNTERS = "Hunters";
    private static final String COLUMN_HUNTERID = "hunter_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_WEAPON = "weapon";

    //constructor
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //construct SQL command
        String CREATE_HUNTERS_TABLE = "CREATE TABLE " +
                TABLE_HUNTERS + "(" +
                COLUMN_HUNTERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_WEAPON + " TEXT)";

        //execute SQL command
        db.execSQL(CREATE_HUNTERS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if it exists
        db.execSQL("DROP TABLE IT EXISTS" + TABLE_HUNTERS);

        //recreate table
        onCreate(db);

    }

    public Hunter findHunter(String hunterName){
        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_HUNTERS +
                " WHERE " + COLUMN_NAME + " = \"" + hunterName +
                "\"";

        //open DB
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty hunter object
        Hunter myHunter = new Hunter();

        if(myCursor.moveToFirst()){
            myHunter.setID(myCursor.getInt(0));
            myHunter.setName(myCursor.getString(1));
            myHunter.setWeapon(myCursor.getString(2));
            myCursor.close();
        }
        else
            myHunter = null;

        //close DB
        db.close();
        return myHunter;
    }

    public boolean deleteHunter(String hunterNameDelete){
        //set default return value
        boolean result = false;

        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_HUNTERS +
                " WHERE " + COLUMN_NAME + " = \"" +
                hunterNameDelete + "\"";

        //open DB
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty hunter object
        Hunter myHunterDelete = new Hunter();

        if (myCursor.moveToFirst()){
            //get id
            //myHunterDelete.setID(myCursor.getInt(0));

            //delete entry with hunterid
            db.delete(TABLE_HUNTERS, COLUMN_NAME + " = ?",
                    new String[]{hunterNameDelete});

            //close cursor
            myCursor.close();

            //set return result to true
            result = true;


        }

        //close db
        db.close();
        return result;
    }


    public Hunter[]  displayHunters(int number){
        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_HUNTERS;

        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myCursor = db.rawQuery(sql_query, null);
        Hunter [] myHunters;

        if(myCursor != null){
            int count = myCursor.getCount();
            int length = count;
            if(length > number)
                length = number;

            //create array of hunters
            myHunters = new Hunter[length];

            int index = count - length;

            myCursor.moveToFirst();
            myCursor.move(index);

            for(int i = 0; i < length; i++){
                myHunters[i] = new Hunter();
                myHunters[i].setID(myCursor.getInt(0));
                myHunters[i].setName(myCursor.getString(1));
                myHunters[i].setWeapon(myCursor.getString(2));
                myCursor.moveToNext();
            }
            myCursor.close();

        }
        else{
            myHunters = new Hunter[1];
            myHunters[0] = new Hunter();
            myHunters[0].setID(0);
            myHunters[0].setName("empty");
            myHunters[0].setWeapon("empty");
        }
        //close db
        db.close();
        return myHunters;



    }


   /* @Override
    public String toString() {
        return mName;
    }
*/

    //add hunter to hunters table
    public void addHunter(Hunter hunter) {
        //prepare values for new entry
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, hunter.getName());
        values.put(COLUMN_WEAPON, hunter.getWeapon());

        //open DB
        SQLiteDatabase db = getWritableDatabase();

        //insert into DB
        db.insert(TABLE_HUNTERS, null, values);

        //close DB
        db.close();


    }


}
