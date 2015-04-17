package chaz.org.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SMSandEmail extends ActionBarActivity {

    //
    private EditText mPhoneNumber;
    private EditText mMessageBody;
    private EditText mEmailAdress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsand_email);
//define variable for the edittext fields
        mPhoneNumber = (EditText)findViewById(R.id.phoneNumber);

        mMessageBody = (EditText)findViewById(R.id.messageBody);

        mEmailAdress = (EditText)findViewById(R.id.emailAddress);


    }


    public void onSendSMSClick(View view){
//get the text in the edittext fields
        String number = mPhoneNumber.getText().toString();

        String message = mMessageBody.getText().toString();

        try {
//send message
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(number,null,message,null,null);

            Toast.makeText(getApplicationContext(),"SMS Sent!", Toast.LENGTH_LONG).show();


        }catch(Exception e){
//toast message that shows if the sms message fails to send
            Toast.makeText(getApplicationContext(),"SMS Failed!", Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }


    }

    public void onSMSIntentClick(View view){
//get text from edittext field
        String message = mMessageBody.getText().toString();

//toast the user if the message body is blank
        if (message.length()==0)
            Toast.makeText(getApplicationContext(), "Please Enter Message", Toast.LENGTH_SHORT).show();
//gather the values for sending
        else{
            try{
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);

                sendIntent.putExtra("message_body",message);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);

            }catch(Exception e){

                Toast.makeText(getApplicationContext(), "SMS Failed to send!", Toast.LENGTH_LONG).show();

                e.printStackTrace();

            }
        }




    }

    public void onSendEmailClick(View view){
//get values of edittext fields
        String emailAdress = mEmailAdress.getText().toString();

        String message = mMessageBody.getText().toString();
//send email if both the fields have something in them
        if (mMessageBody.length()!= 0 && mEmailAdress.length() != 0) {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAdress);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Email from my App!");

            try{
//clear the edittext fields
                startActivity(Intent.createChooser(emailIntent,"Send email..."));

                mEmailAdress.setText("");
                mMessageBody.setText("");

            }catch(Exception e){
//toast the user telling them the email failed to send
                Toast.makeText(getApplicationContext(), "Problem sending email", Toast.LENGTH_LONG).show();


            }

        }else
//toast the user telling them to fill in both the email address and message body fields
            Toast.makeText(getApplication(),"Please enter both email address and message",Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smsand_email, menu);
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
