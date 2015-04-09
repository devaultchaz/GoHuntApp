package chaz.org.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Audio extends ActionBarActivity {

    private static Button mRecordButton;
    private static Button mStopRecordButton;
    private static Button mPlayButton;
    private static Button mStopPlayButton;
    private static MediaRecorder mMediaRecorder;
    private static MediaPlayer mMediaPlayer;
    private static String mAudioFilePath;
    private static String mError;
    private static TextView mTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mRecordButton = (Button)findViewById(R.id.record);
        mStopRecordButton = (Button)findViewById(R.id.stopRecord);
        mPlayButton = (Button)findViewById(R.id.play);
        mStopPlayButton = (Button)findViewById(R.id.stopPlay);
        mTextView = (TextView)findViewById(R.id.textView19);

        //set path
        mAudioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myAudio.3gp";

        //show path in textview
        mTextView.setText("file "+mAudioFilePath+"\nstate: "+Environment.getExternalStorageState());


    }

    public void onRecordClick(View view) {
        //record
        try {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mMediaRecorder.setOutputFile(mAudioFilePath);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


            mMediaRecorder.prepare();




        }catch(Exception e){
            e.printStackTrace();
        }
        //start recording
        mMediaRecorder.start();

    }

    public void onStopRecordClick(View view) {

            //stop recording
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;

    }
        public void onPlayClick(View view) {
            //play recording
            try {
                mMediaPlayer  = new MediaPlayer();
                mMediaPlayer.setDataSource(mAudioFilePath);
                mMediaPlayer.prepare();



            }catch(Exception e){
                e.printStackTrace();
            }
            //start playing the recording
            mMediaPlayer.start();
        }

    public void onStopPlayClick(View view) {

            //stop playing the recording
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;

    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio, menu);
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

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}

