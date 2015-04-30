package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;



public class Add_Tree_Stand extends ActionBarActivity {

    EditText mTNameTextView;
    EditText mTypeTextView;
    EditText mNotesTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tree__stand);


        mTNameTextView = (EditText)findViewById(R.id.editTextTName);
        mTypeTextView = (EditText)findViewById(R.id.editTextType);
        mNotesTextView = (EditText)findViewById(R.id.editTextNotes);


    }

    //add treestand
    public void onAddClick(View view){
        //create ts handler
        TSHandler tsHandler = new TSHandler(this, null, null, 1);

        //get user treestand name
        String myTName = mTNameTextView.getText().toString();

        //get user treestand type
        String myType = mTypeTextView.getText().toString();

        //get user treestand notes
        String myNotes = mNotesTextView.getText().toString();


        //make new hunter
        TreeStand myTreeStand = new TreeStand(myTName, myType, myNotes);

        //add treestand to database
        tsHandler.addTreeStand(myTreeStand);

        //clear input fields
        mTNameTextView.setText("");
        mTypeTextView.setText("");
        mNotesTextView.setText("");

    }




    // Nav button to go to home screen
    public void onClick(View view){
        Intent myIntent = new Intent(this, MainActivity.class);

        startActivity(myIntent);

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
        getMenuInflater().inflate(R.menu.menu_add__tree__stand, menu);
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
