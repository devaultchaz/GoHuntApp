package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Add_Hunter extends ActionBarActivity {

    //GUI components
    //TextView mHunterIDView;
    EditText mNameTextView;
    EditText mWeaponTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hunter);



        //mHunterIDView = (TextView)findViewById(R.id.hunterIdValue);
        mNameTextView = (EditText)findViewById(R.id.editTextName);
        mWeaponTextView = (EditText)findViewById(R.id.editTextWeapon);


    }


    public void onAddClick(View view){
        //create DB handler
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        //get user name
        String myName = mNameTextView.getText().toString();

        //get user weapon
        String myWeapon = mWeaponTextView.getText().toString();

        //make new hunter
        Hunter myHunter = new Hunter(myName, myWeapon);

        //add hunter to database
        dbHandler.addHunter(myHunter);

        //clear input fields
        mNameTextView.setText("");
        mWeaponTextView.setText("");


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
