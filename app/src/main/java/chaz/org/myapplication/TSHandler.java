package chaz.org.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chaz on 4/30/2015.
 */
public class TSHandler extends SQLiteOpenHelper {
    //declares database constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database2.db";

    //declare table constants
    private static final String TABLE_TREESTANDS = "Treestands";
    private static final String COLUMN_TREESTANDID = "treestand_id";
    private static final String COLUMN_TREESTANDNAME = "treestand_name";
    private static final String COLUMN_TREESTANDTYPE = "treestand_type";
    private static final String COLUMN_TREESTANDNOTES = "treestand_notes";


    //constructor
    public TSHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //construct SQL command
        String CREATE_TREESTAND_TABLE = "CREATE TABLE " +
                TABLE_TREESTANDS + "(" +
                COLUMN_TREESTANDID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TREESTANDNAME + " TEXT, " +
                COLUMN_TREESTANDTYPE + " TEXT, " +
                COLUMN_TREESTANDNOTES + " TEXT)";


        //execute SQL command
        db.execSQL(CREATE_TREESTAND_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if it exists
        db.execSQL("DROP TABLE IT EXISTS" + TABLE_TREESTANDS);

        //recreate table
        onCreate(db);
    }

    public TreeStand findTreeStand(String treestandName){
        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_TREESTANDS +
                " WHERE " + COLUMN_TREESTANDNAME + " = \"" + treestandName +
                "\"";

        //open DB
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty hunter object
        TreeStand myTreeStand = new TreeStand();

        if(myCursor.moveToFirst()){
            myTreeStand.setTreeStandID(myCursor.getInt(0));
            myTreeStand.setTreeStandName(myCursor.getString(1));
            myTreeStand.setTreeStandType(myCursor.getString(2));
            myTreeStand.setTreeStandNotes(myCursor.getString(3));
            myCursor.close();
        }
        else
            myTreeStand = null;

        //close DB
        db.close();
        return myTreeStand;
    }


    public TreeStand [] displayTreestands(int number){
        //construct SQL string
        String sql_query = "SELECT * FROM " + TABLE_TREESTANDS;

        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myTCursor = db.rawQuery(sql_query, null);
        TreeStand [] myTreestands;

        if(myTCursor != null){
            int count = myTCursor.getCount();
            int length = count;
            if(length > number)
                length = number;

            //create array of hunters
            myTreestands = new TreeStand[length];

            int index = count - length;

            myTCursor.moveToFirst();
            myTCursor.move(index);

            for(int i = 0; i < length; i++){
                myTreestands[i] = new TreeStand();
                myTreestands[i].setTreeStandID(myTCursor.getInt(0));
                myTreestands[i].setTreeStandName(myTCursor.getString(1));
                myTreestands[i].setTreeStandType(myTCursor.getString(2));
                myTreestands[i].setTreeStandNotes(myTCursor.getString(3));
                myTCursor.moveToNext();
            }
            myTCursor.close();

        }
        else{
            myTreestands = new TreeStand[1];
            myTreestands[0] = new TreeStand();
            myTreestands[0].setTreeStandID(0);
            myTreestands[0].setTreeStandName("empty");
            myTreestands[0].setTreeStandType("empty");
            myTreestands[0].setTreeStandNotes("empty");
        }
        //close db
        db.close();
        return myTreestands;

    }




    //add hunter to hunters table
    public void addTreeStand(TreeStand treestand) {
        //prepare values for new entry
        ContentValues values = new ContentValues();
        values.put(COLUMN_TREESTANDNAME, treestand.getTreeStandName());
        values.put(COLUMN_TREESTANDTYPE, treestand.getTreeStandType());
        values.put(COLUMN_TREESTANDNOTES, treestand.getTreeStandNotes());

        //open DB
        SQLiteDatabase db = getWritableDatabase();

        //insert into DB
        db.insert(TABLE_TREESTANDS, null, values);

        //close DB
        db.close();


    }
}
