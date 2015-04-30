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

                    findViewById(R.id.listViewHunters);

            //set the tree stand listview
            mTreeStandList = (ListView)

                    findViewById(R.id.listView3);

            //get hunters into list

            //create db handler
            DBHandler dbHandler = new DBHandler(this, null, null, 1);

            //get hunters to display from dbhandler
            Hunter [] myHunters = dbHandler.displayHunters(20);

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



            //set the hunter onclicklistener so when an item in the listview is clicked it will perform something
            mHuntersList.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                                {


                                                    @Override
                                                    public void onItemClick(final AdapterView<?> parent, final View view, final int position,
                                                                            final long id) {

                                                        String data = "Test";

                                                        //set the intent to go to the hunterscreen when a item is clicked
                                                        Intent intent = new Intent(MainActivity.this, HunterScreen.class);
                                                        intent.putExtra("hunter",data);
                                                        startActivity(intent);
                                                        //the item clicked




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
                                                          //String itemValue3 = (String) parent.getItemAtPosition(position);

                                                          Toast.makeText(getApplicationContext(),
                                                                  "Position: " + position,
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
        Intent mapIntent = new Intent(this, MapsActivity.class);

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
