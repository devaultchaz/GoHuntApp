package chaz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;


public class Add_Hunter extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hunter);

/*
        Button btnSave;

        EditText editTextName;
        EditText editTextWeapon;

private int _Hunter_Id=0;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hunter);

        btnSave = (Button) findViewById(R.id.btnSave);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextWeapon = (EditText) findViewById(R.id.editTextWeapon);

        btnSave.setOnClickListener(this);

        _Hunter_Id =0;
        Intent intent = getIntent();
        _Hunter_Id =intent.getIntExtra("hunter_Id", 0);
        HunterRepo repo = new HunterRepo(this);
        Hunter hunter = new Hunter();
        hunter = repo.getHunterById(_Hunter_Id);


        editTextName.setText(hunter.name);
        editTextWeapon.setText(hunter.weapon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hunter_screen, menu);
        return true;
    }

    public void onAddClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            HunterRepo repo = new HunterRepo(this);
            Hunter hunter = new Hunter();
            hunter.weapon=editTextWeapon.getText().toString();
            hunter.name=editTextName.getText().toString();
            hunter.hunter_ID=_Hunter_Id;

            if (_Hunter_Id==0){
                _Hunter_Id = repo.insert(hunter);

                Toast.makeText(this, "New Hunter Insert", Toast.LENGTH_SHORT).show();
            }else{

                repo.update(hunter);
                Toast.makeText(this,"Hunter Record updated",Toast.LENGTH_SHORT).show();
            }
        }
*/

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
