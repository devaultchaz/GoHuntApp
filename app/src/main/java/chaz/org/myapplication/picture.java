package chaz.org.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;




public class picture extends ActionBarActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    Button btnCapture;
    //image view where the picture will show up on screen
    public static ImageView mImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        btnCapture = (Button) findViewById(R.id.button26);
        if (!hasCamera()) {
            btnCapture.setEnabled(false);
        }
    }
//check camera
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(getPackageManager().FEATURE_CAMERA_ANY)) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture, menu);
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
//set the button so when clicked will bring up camera and set the file
    public void captureClick(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mypicture.jpg");

        fileUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Capture Saved", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }
//sets the file value
    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "GoHuntApp");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("GoHuntApp", "Failed to create directory");
                return null;

            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath()+File.separator+"IMG_"+timeStamp+".jpg");

        }
        else{
            return null;
        }

        return mediaFile;



    }
//button to go to home screen
    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
//button to make the picture show up in the image view
    public void onShowClick(View view){

        mImageView = (ImageView) findViewById(R.id.imageView8);
        mImageView.setImageURI(fileUri);

    }

}