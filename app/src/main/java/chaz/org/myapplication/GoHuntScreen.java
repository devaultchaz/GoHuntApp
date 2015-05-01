package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;


public class GoHuntScreen extends ActionBarActivity {

    ListView mHuntersList;

    ListView mTreeStandList;

    Array mHuntArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_hunt_screen);

        //set the hunter listview
        mHuntersList = (ListView)

                findViewById(R.id.listViewGoHunters);

        //set the tree stand listview
        mTreeStandList = (ListView)

                findViewById(R.id.listViewGoTreeStands);

        //get hunters into list

        //create db handler
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        //get hunters to display from dbhandler
        Hunter [] myHunters = dbHandler.displayHunters(20);

        //ArrayList arrayList = dbHandler.displayHunters(20);


        //List<Array> wordList = Arrays.asList();
        //Collections.shuffle(myHunters);


        //set adapter to listview
        ArrayAdapter<Hunter> adapter = new ArrayAdapter<Hunter>(this, android.R.layout.simple_list_item_1, myHunters);

        mHuntersList.setAdapter(adapter);

        //create ts handler
        TSHandler tsHandler = new TSHandler(this, null, null, 1);

        //get treestands to display from tshandler
        TreeStand [] myTreestands = tsHandler.displayTreestands(20);

        //set adapter to listview
        ArrayAdapter<TreeStand> adapterT = new ArrayAdapter<TreeStand>(this, android.R.layout.simple_list_item_1, myTreestands);

        mTreeStandList.setAdapter(adapterT);









    }


    //performs go hunt feature
    public void onGoHuntClick(View view){
        Intent goHuntIntent = new Intent(this, GoHuntScreen.class);
        //Collections.shuffle(myHunters);
        startActivity(goHuntIntent);

    }


    //Nav button to go to homescreen
    public void onClick(View view){
        Intent homeIntent = new Intent(this, MainActivity.class);

        startActivity(homeIntent);

    }


    //Nav button to go to map screen
    public void onClickMap(View view){
        Intent mapIntent = new Intent(this, MapScreen.class);

        startActivity(mapIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_go_hunt_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
