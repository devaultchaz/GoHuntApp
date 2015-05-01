package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class HunterScreen extends ActionBarActivity {

    TextView mHunterName;

    TextView mHunterWeapon;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunter_screen);

        String extraFromAct1 = getIntent().getStringExtra("hunter");

        //Toast.makeText(getApplicationContext(),
                //extraFromAct1,
                //Toast.LENGTH_LONG).show();

        //set the hunter textview
        mHunterName = (TextView)findViewById(R.id.textViewHuntersName);

        //set the hunter textview
        mHunterWeapon = (TextView)findViewById(R.id.textViewWeapon);

        //create db handler
        DBHandler dbHandler = new DBHandler(this, null, null, 1);



        //get hunter name
        //StringTokenizer tokens = new StringTokenizer(extraFromAct1, ",");
        //String first = tokens.nextToken();// this will contain hunters name
        //String second = tokens.nextToken();// this will contain hunters weapon

        //check for hunter
        Hunter myHunter = dbHandler.findHunter(extraFromAct1);

        if(myHunter != null){
            //set name and weapon
            mHunterName.setText(String.valueOf(myHunter.getName()));
            mHunterWeapon.setText(String.valueOf(myHunter.getWeapon()));

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

    //delete hunter
    public void onDeleteClick(View view){
        //create db handler
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        //get hunters name to delete
        String myHunterName = mHunterName.getText().toString();

        //delete record
        boolean myResult = dbHandler.deleteHunter(myHunterName);

        //if deleted successfully
        if(myResult){
            mHunterName.setText("");
            mHunterWeapon.setText("");
        }
        else
            Toast.makeText(getApplicationContext(),
                    "No Match Found",
                    Toast.LENGTH_LONG).show();

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
        getMenuInflater().inflate(R.menu.menu_hunter_screen, menu);
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
