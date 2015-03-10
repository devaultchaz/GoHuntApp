package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ListView mListView;

    ListView mHuntersList;

    ListView mTreeStandList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mHuntersList = (ListView) findViewById(R.id.listView2);

        mTreeStandList = (ListView) findViewById(R.id.listView3);


        String[] values2 = new String[]{"Jim Davis","Greg Mathews","Arnold Fuller", "Adam James", "Dan Katz", "Fred Jones"};

        String[] values3 = new String[]{"Kingdom","Acorn","Junkyard", "Big Time","Hilltop","Long-Range","Luck"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values3);

        mHuntersList.setAdapter(adapter2);

        mTreeStandList.setAdapter(adapter3);

        mHuntersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {


                //the item clicked
                String itemValue2 = (String)mHuntersList.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Position: " + position + "Value: " + itemValue2,
                        Toast.LENGTH_LONG).show();


            }


        });


    }






    // Nav button to go to add hunter screen
    public void onAddHClick(View view){
        Intent myAddHIntent = new Intent(this, Add_Hunter.class);

        startActivity(myAddHIntent);

    }


    // Nav button to go to add tree stand screen
    public void onAddTClick(View view){
        Intent myAddTIntent = new Intent(this, Add_Tree_Stand.class);

        startActivity(myAddTIntent);

    }




    //Nav button to go to map screen
    public void onMapClick(View view) {
        Intent mapIntent = new Intent(this, MapScreen.class);

        startActivity(mapIntent);
    }

    //Nav button to go to go hunt screen
    public void onGoClick(View view){
        Intent mapGoIntent = new Intent(this, GoHuntScreen.class);

        startActivity(mapGoIntent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
