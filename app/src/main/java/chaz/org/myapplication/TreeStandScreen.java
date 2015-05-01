package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class TreeStandScreen extends ActionBarActivity {

    TextView mTreeStandName;

    TextView mTreeStandType;

    TextView mTreeStandNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_stand_screen);

        String extraFromAct1 = getIntent().getStringExtra("treestand");

        //Toast.makeText(getApplicationContext(),
                //extraFromAct1,
               // Toast.LENGTH_LONG).show();

        //set the name textview
        mTreeStandName = (TextView)findViewById(R.id.textViewTreeStandName);

        //set the type textview
        mTreeStandType = (TextView)findViewById(R.id.textViewTreeStandType);

        //set the notes textview
        mTreeStandNotes = (TextView)findViewById(R.id.textViewTreeStandNotes);

        //create ts handler
        TSHandler tsHandler = new TSHandler(this, null, null, 1);

        //get hunter name
        //StringTokenizer tokens = new StringTokenizer(extraFromAct1, ",");
        //String first = tokens.nextToken();// this will contain hunters name
        //String second = tokens.nextToken();// this will contain hunters weapon

        //check for treestand
        TreeStand myTreeStand = tsHandler.findTreeStand(extraFromAct1);

        if(myTreeStand != null){
            //set name and weapon
            mTreeStandName.setText(String.valueOf(myTreeStand.getTreeStandName()));
            mTreeStandType.setText(String.valueOf(myTreeStand.getTreeStandType()));
            mTreeStandNotes.setText(String.valueOf(myTreeStand.getTreeStandNotes()));

        }
        else
            Toast.makeText(getApplicationContext(),
                    "No Match Found",
                    Toast.LENGTH_LONG).show();



    }

    //delete treestand
    public void onDeleteClick(View view){
        //create db handler
        TSHandler tsHandler = new TSHandler(this, null, null, 1);

        //get treestand name to delete
        String myTreeStandName = mTreeStandName.getText().toString();

        //delete record
        boolean myResult = tsHandler.deleteTreeStand(myTreeStandName);

        //if deleted successfully
        if(myResult){
            mTreeStandName.setText("");
            mTreeStandType.setText("");
            mTreeStandNotes.setText("");
        }
        else
            Toast.makeText(getApplicationContext(),
                    "No Match Found",
                    Toast.LENGTH_LONG).show();

    }




    // Nav button to go to home screen
    public void onClick(View view){
        Intent homeIntent = new Intent(this, MainActivity.class);

        startActivity(homeIntent);

    }


    // Nav button to go to go hunt screen
    public void onGoClick(View view){
        Intent myGoIntent = new Intent(this, GoHuntScreen.class);

        startActivity(myGoIntent);

    }

    //Nav button to go to map screen
    public void onMapClick(View view) {
        Intent mapIntent = new Intent(this, MapScreen.class);

        startActivity(mapIntent);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tree_stand_screen, menu);
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
