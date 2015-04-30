package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class HunterScreen extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunter_screen);

        String extraFromAct1 = getIntent().getStringExtra("hunter");

        Toast.makeText(getApplicationContext(),
                extraFromAct1,
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
