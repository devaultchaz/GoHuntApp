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


    ListView mHuntersList;

    ListView mTreeStandList;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //btnGetAll = (Button) findViewById(R.id.btnGetAll);
            //btnGetAll.setOnClickListener(this);


            //set the hunter listview
            mHuntersList = (ListView)

                    findViewById(R.id.listView2);

            //set the tree stand listview
            mTreeStandList = (ListView)

                    findViewById(R.id.listView3);

            //create the string that will hold the values of the array for hunters


            String[] values2 = new String[]{"Jim Davis", "Greg Mathews", "Arnold Fuller", "Adam James", "Dan Katz", "Fred Jones"};

            //create the string that will hold the values of the array for tree stands
            String[] values4 = new String[]{"Kingdom", "Acorn", "Junkyard", "Big Time", "Hilltop", "Long-Range", "Luck"};

            //create the array that holds the hunter values that will be placed in the hunter listview
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values2);

            //create the array that holds the tree stand values that will be placed in the tree stand listview
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values3);

            //set the hunter adapter
            mHuntersList.setAdapter(adapter2);

            //set the tree stand adapter
            mTreeStandList.setAdapter(adapter3);

            //set the hunter onclicklistener so when an item in the listview is clicked it will perform something
            mHuntersList.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                                {


                                                    @Override
                                                    public void onItemClick(final AdapterView<?> parent, final View view, final int position,
                                                                            final long id) {

                                                        //set the intent to go to the hunterscreen when a item is clicked
                                                        Intent intent = new Intent(MainActivity.this, HunterScreen.class);
                                                        startActivity(intent);
                                                        //the item clicked
                                                        String itemValue2 = (String) mHuntersList.getItemAtPosition(position);

                                                        Toast.makeText(getApplicationContext(),
                                                                "Position: " + position + "Value: " + itemValue2,
                                                                Toast.LENGTH_LONG).show();


                                                    }


                                                }

            );


            //set the tree stand onclicklistneer so when an item in the listview is click it will perform something
            mTreeStandList.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                                  {

                                                      @Override
                                                      public void onItemClick(final AdapterView<?> parent, final View view, final int position,
                                                                              final long id) {

                                                          //set the intent to go to the treestandscreen when an item is clicked
                                                          Intent intent = new Intent(MainActivity.this, TreeStandScreen.class);
                                                          startActivity(intent);

                                                          //the item clicked
                                                          String itemValue3 = (String) mTreeStandList.getItemAtPosition(position);

                                                          Toast.makeText(getApplicationContext(),
                                                                  "Position: " + position + "Value: " + itemValue3,
                                                                  Toast.LENGTH_LONG).show();


                                                      }


                                                  }

            );
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
