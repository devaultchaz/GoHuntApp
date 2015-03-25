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
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "database.db";

    //declare table constants
    private static final String TABLE_HUNTERS = "Hunters";
    private static final String COLUMN_HUNTERID = "hunter_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_WEAPON = "weapon";

    //constructor
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //construct SQL command
        String CREATE_HUNTERS_TABLE = "CREATE TABLE " +
                TABLE_HUNTERS + "(" +
                COLUMN_HUNTERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_WEAPON + " TEXT)";

        //execute SQL command
        db.execSQL(CREATE_HUNTERS_TABLE);

    }

    public Hunter getHunters(){
        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_HUNTERS;

        //open DB
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query and store data
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty student object
        Hunter myHunter = new Hunter();

        if(myCursor.moveToFirst()){
            myHunter.setID(myCursor.getInt(0));
            myHunter.setName(myCursor.getString(1));
            myHunter.setWeapon(myCursor.getString(2));
            myCursor.close();

        }
        else
            myHunter = null;

        //close db
        db.close();
        return myHunter;


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drop table if it exists
        db.execSQL("DROP TABLE IT EXISTS" + TABLE_HUNTERS);

        //recreate table
        onCreate(db);

    }

    //add hunter to hunters table
    public void addHunter(Hunter hunter){
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
