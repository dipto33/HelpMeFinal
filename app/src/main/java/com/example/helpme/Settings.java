package com.example.helpme;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Settings extends AppCompatActivity {
    public static final int ACTION_LOCATION_SOURCE_SETTINGS = 1;
    private static final int REQUEST_CALL = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    private final int REQ_CODE = 100;
    public  static final int RequestPermissionCode  = 1 ;

    String TAG = "ContactsActivityTAG";

    private static final int RESULT_PICK_CONTACT = 1234;
    private static final int RESULT_PICK_CONTACT1 = 12345;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = (Button) findViewById(R.id.btn1);
        Button button1 = (Button) findViewById(R.id.btn12);

        TextView tvv1 = (TextView) findViewById( R.id.textView1 );
        TextView tvv2 = (TextView) findViewById( R.id.textView2 );
        TextView tvv3 = (TextView) findViewById( R.id.textView3 );

        File file = getBaseContext().getFileStreamPath( "mytextfile.txt" );
        File file1 = getBaseContext().getFileStreamPath( "mytextfile1.txt" );
        File file2 = getBaseContext().getFileStreamPath( "mytextfile2.txt" );
        if(file.exists())
        {
            // Toast.makeText( Settings.this," xxxxxxx",Toast.LENGTH_SHORT ).show();
            tvv1.setText(readFile( "mytextfile.txt" ));
        }
        else
        {
            // Toast.makeText( Settings.this," yyyyy",Toast.LENGTH_SHORT ).show();
        }
        if(file1.exists())
        {
            tvv2.setText(readFile( "mytextfile.txt" ));
        }
        if(file2.exists())
        {
            tvv3.setText(readFile( "mytextfile.txt" ));
        }



        // Toast.makeText( Settings.this,"zzzzzz",Toast.LENGTH_SHORT ).show();


        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent cp = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(cp, RESULT_PICK_CONTACT);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent cp = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(cp, RESULT_PICK_CONTACT1);
            }
        });
    }

    public void showContact(View view) {
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phone, name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

            Log.d(TAG, "ContactPicked NAME: " + name);
            Log.d(TAG, "ContactPicked NUMBER: " + phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int i=0;
    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent ResultIntent) {

        super.onActivityResult(RequestCode, ResultCode, ResultIntent);
        switch (RequestCode) {
            case (RESULT_PICK_CONTACT):
                if (ResultCode == Activity.RESULT_OK) {

                    Uri uri = ResultIntent.getData();
                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int numberColoumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String num = cursor.getString(numberColoumnIndex);
                    int nameColoumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = cursor.getString(nameColoumnIndex);
                    Toast.makeText(Settings.this, "name = " + name, Toast.LENGTH_LONG).show();
                    //Toast.makeText(Settings.this, "Name=" + name, Toast.LENGTH_LONG).show();
                    // Toast.makeText(MainActivity.this, "Number=" + nam, Toast.LENGTH_LONG).show();

                    MainActivity mainActivity = new MainActivity();
                    // mainActivity.contactNumber = num;
                    TextView tv1 = (TextView) findViewById(R.id.textView1);
                    TextView tv2 = (TextView) findViewById(R.id.textView2);
                    TextView tv3 = (TextView) findViewById(R.id.textView3);
                    String s = name+"   "+num;


                    FileOutputStream fileOutputStream = null;
                    FileOutputStream numfileOutputStream = null;
                    try {
                        // deleteFile("mytextfile.txt");
                        fileOutputStream = openFileOutput("mytextfile.txt",MODE_PRIVATE);
                        numfileOutputStream = openFileOutput("nummytextfile.txt",MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileOutputStream);
                    OutputStreamWriter numoutputWriter=new OutputStreamWriter(numfileOutputStream);
                    try {
                        outputWriter.write(name);
                        numoutputWriter.write(num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputWriter.close();
                        numoutputWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tv1.setText(readFile( "mytextfile.txt" ));
                    cursor.close();
                }
                break;
            case (RESULT_PICK_CONTACT1):
                if (ResultCode == Activity.RESULT_OK) {

                    Uri uri = ResultIntent.getData();
                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int numberColoumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String num = cursor.getString(numberColoumnIndex);
                    int nameColoumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = cursor.getString(nameColoumnIndex);

                    Toast.makeText(Settings.this, "Name = " + name, Toast.LENGTH_LONG).show();
                    //Toast.makeText(Settings.this, "Name=" + name, Toast.LENGTH_LONG).show();
                    // Toast.makeText(MainActivity.this, "Number=" + nam, Toast.LENGTH_LONG).show();

                    MainActivity mainActivity = new MainActivity();
                    // mainActivity.contactNumber = num;
                    TextView tv1 = (TextView) findViewById(R.id.textView1);
                    TextView tv2 = (TextView) findViewById(R.id.textView2);
                    TextView tv3 = (TextView) findViewById(R.id.textView3);
                    String s = name+"   "+num;


                    FileOutputStream fileOutputStream1 = null;
                    FileOutputStream numfileOutputStream1 = null;
                    try {
                        // deleteFile("mytextfile1.txt");
                        fileOutputStream1 = openFileOutput("mytextfile1.txt",MODE_PRIVATE);
                        numfileOutputStream1 = openFileOutput("nummytextfile1.txt",MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputWriter1=new OutputStreamWriter(fileOutputStream1);
                    OutputStreamWriter numoutputWriter1=new OutputStreamWriter(numfileOutputStream1);

                    try {
                        outputWriter1.write(name);
                        numoutputWriter1.write(num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputWriter1.close();
                        numoutputWriter1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tv2.setText(readFile( "mytextfile1.txt" ));

                    cursor.close();


                }
                break;
        }
    }

    public void simSelect1(View view) {

        writeFile( "simfile.txt","0" );

        Button button1 =(Button) findViewById( R.id.sim1btid );
        Button button2 =(Button) findViewById( R.id.sim2btid );

        button1.setBackgroundColor( Color.parseColor( "#512888" ) );
        button2.setBackgroundColor( Color.parseColor( "#fafbf5" ) );

    }

    public void simSelect2(View view) {

        writeFile( "simfile.txt","1" );
        Button button1 =(Button) findViewById( R.id.sim1btid );
        Button button2 =(Button) findViewById( R.id.sim2btid );

        button2.setBackgroundColor( Color.parseColor( "#512888" ) );
        button1.setBackgroundColor( Color.parseColor( "#fafbf5" ) );

    }

    public void saving(View view) {
        EditText editText = (EditText) findViewById( R.id.etid );
        TextView tv3 = (TextView) findViewById( R.id.textView3 );

        FileOutputStream fileOutputStream2 = null;
        try {
            //deleteFile("mytextfile2.txt");
            fileOutputStream2 = openFileOutput("mytextfile2.txt",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputWriter2=new OutputStreamWriter(fileOutputStream2);
        try {
            outputWriter2.write(editText.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv3.setText(readFile( "mytextfile2.txt" ));
        editText.setText( "" );
    }

    public void golastpage(View view) {
        Intent settings = new Intent(Settings.this, LastActivity.class);
        startActivity(settings);
    }

    public void reset(View view) {
        File file = getBaseContext().getFileStreamPath( "mytextfile.txt" );
        File file1 = getBaseContext().getFileStreamPath( "mytextfile1.txt" );
        File file2 = getBaseContext().getFileStreamPath( "mytextfile2.txt" );

        File file31 = getBaseContext().getFileStreamPath( "nummytextfile.txt" );
        File file32 = getBaseContext().getFileStreamPath( "nummytextfile1.txt" );

        file.delete();
        file1.delete();
        file2.delete();

        file31.delete();
        file32.delete();

        Intent settings = new Intent(Settings.this, Settings.class);
        startActivity(settings);
    }
    String readFile(String fn)
    {
        FileInputStream fin2 = null;

        {
            try {
                fin2 = openFileInput( fn );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        int c = 0;
        String temp = "";
        while (true) {
            try {
                if (!((c = fin2.read()) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp = temp + Character.toString( (char) c );
        }

        try {
            fin2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    void writeFile(String filename,String s)
    {
        FileOutputStream fileOutputStream2 = null;
        try {
            //deleteFile("mytextfile2.txt");
            fileOutputStream2 = openFileOutput(filename,MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputWriter2=new OutputStreamWriter(fileOutputStream2);
        try {
            outputWriter2.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
