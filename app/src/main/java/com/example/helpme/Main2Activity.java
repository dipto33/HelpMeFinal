package com.example.helpme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;


public class Main2Activity<inputBuffer> extends AppCompatActivity   {
    private static String SENT = "SMS_SENT", DELIVERED = "SMS_DELIVERED";


//location[

    LocationManager locationManager;
    Context mContext;
    Location location = null;

    //]
    private static final int RESULT_PICK_CONTACT = 1234;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int REQUEST_CALL = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    private final int REQ_CODE = 100;

    static final int READ_BLOCK_SIZE = 100;

    private static final int PERMISSION_REQUEST = 101;


    static final Integer CALL = 0x2;

    String msg = " bal ";
    String value = "no";
    //String str = "location will be found ";

    // final static String sSMSManagerIntentSENT = "package.DeliveryReport.SMS_SENT";


    Bundle savedInstanceState;

    private final static String simSlotName[] = {
            "extra_asus_dial_use_dualsim",
            "com.android.phone.extra.slot",
            "slot",
            "simslot",
            "sim_slot",
            "subscription",
            "Subscription",
            "phone",
            "com.android.phone.DialingMode",
            "simSlot",
            "slot_id",
            "simId",
            "simnum",
            "phone_type",
            "slotId",
            "slotIdx"
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    void calling() {
        String s = "";
        try {
            FileInputStream fileIn = openFileInput( "nummytextfile.txt" );
            InputStreamReader InputRead = new InputStreamReader( fileIn );

            char[] inputBuffer = new char[READ_BLOCK_SIZE];

            int charRead;

            while ((charRead = InputRead.read( inputBuffer )) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf( inputBuffer, 0, charRead );
                s += readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        String contactNumber = "" + s;
        makePhoneCall( contactNumber );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void calling1() {
        FileInputStream fin1 = null;

        {
            try {
                fin1 = openFileInput( "nummytextfile1.txt" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        int c = 0;
        String temp = "";
        while (true) {
            try {
                if (!((c = fin1.read()) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp = temp + Character.toString( (char) c );
        }

        try {
            fin1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String contactNumber = "" + temp;
        makePhoneCall( contactNumber );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void calling2() {
        FileInputStream fin2 = null;

        {
            try {
                fin2 = openFileInput( "mytextfile2.txt" );
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

        String contactNumber = "" + temp;
        makePhoneCall( contactNumber );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void test() {
        calling();
        Log.e( "LOB", "testing " );
    }

    /*FileOutputStream fileOutputStream1 = null;
    OutputStreamWriter outputWriter1 = null;
    File latlangfile = getBaseContext().getFileStreamPath( "latlang.txt" );
*/
    /*void locationfile()
    {
        try {
            fileOutputStream1 = openFileOutput("latlang.txt",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        outputWriter1=new OutputStreamWriter(fileOutputStream1);

        try {
            outputWriter1.write("location will find soon");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            outputWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        addNotification();


        //locationfile();


        // deleteFile("mytextfile.txt");


        //location[

       /* mContext = this;
        locationManager = (LocationManager) mContext.getSystemService( Context.LOCATION_SERVICE );
        if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                2000,
                10, locationListenerGPS );


        //]*/


        Log.e( "LOB", "testing 2" );
        //calling();


        Log.e( "LOB", " forwarding 3" );

        File file = getBaseContext().getFileStreamPath( "nummytextfile.txt" );
        File file1 = getBaseContext().getFileStreamPath( "nummytextfile1.txt" );
        File file2 = getBaseContext().getFileStreamPath( "mytextfile2.txt" );
        File filefile = getBaseContext().getFileStreamPath( "file.txt" );


        if (file.exists()) {

           /* String s = "";
            if(filefile.exists())
            {
                Log.e("LOB"," filefile ase ");
                Toast.makeText(MainActivity.this, " filefile ase ", Toast.LENGTH_LONG).show();
                try {
                    FileInputStream fileIn=openFileInput("file.txt");
                    InputStreamReader InputRead= new InputStreamReader(fileIn);

                    char[] inputBuffer= new char[READ_BLOCK_SIZE];

                    int charRead;


                    while ((charRead=InputRead.read(inputBuffer))>0) {
                        // char to string conversion
                        String readstring=String.copyValueOf(inputBuffer,0,charRead);
                        s +=readstring;
                    }
                    InputRead.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if(s=="ok")
            {
                Toast.makeText(MainActivity.this, " filefile kaj hosse ", Toast.LENGTH_LONG).show();

                Temp temp = new Temp();
                temp.calldebokina2();
                calling();
            }*/
            calling();

        }

        try {
            Thread.sleep( 5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (file1.exists()) {
            calling1();
        }

        try {
            Thread.sleep( 10000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (file1.exists()) {
            calling2();
        }
        Log.e( "LOB", " forwarding 4 " );


        // if (checkPermission())
        // {
        // Toast.makeText(MainActivity.this, " permission granted ", Toast.LENGTH_LONG).show();
        startService( new Intent( getApplicationContext(), LockService.class ) );
        // }


        //
    }

    //location[
    LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String str = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
            String msg = "New Latitude: " + latitude + "New Longitude: " + longitude + "nazmul from help me app";
            writeFile( "latlang.txt", str );
            Toast.makeText( mContext, msg, Toast.LENGTH_LONG ).show();

            Log.e( "LOB", " location dekhaise .............................. " );
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
   /* protected void onResume(){
        super.onResume();

    }
*/


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendSMS(String number, String text) {
        /*final PendingIntent localPendingIntent1 =
                PendingIntent.getBroadcast( mContext, 0, new Intent( this.SENT ), 0 );
        final PendingIntent localPendingIntent2 =
                PendingIntent.getBroadcast( mContext, 0, new Intent( this.DELIVERED ), 0 );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
        {
            SubscriptionManager subscriptionManager = SubscriptionManager.from(getApplicationContext());
            int subID = 0;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            File simfile = getBaseContext().getFileStreamPath( "simfile.txt" );
            int sim;
            if (simfile.exists()) {
                sim = Integer.parseInt( readFile( "simfile.txt" ) );
                //Toast.makeText( MainActivity.this,""+sim ,Toast.LENGTH_SHORT);
            } else {
                sim = 0;
            }
            List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            for (SubscriptionInfo subscriptionInfo : subscriptionInfoList) {
                if (subscriptionInfo.getSimSlotIndex() == sim) {
                    subID = subscriptionInfo.getSubscriptionId();
                    break;
                }
            }
            SmsManager smsManager = SmsManager.getSmsManagerForSubscriptionId(subID);
            smsManager.sendTextMessage(number, null, text, localPendingIntent1, localPendingIntent2);

        }
*/
        /*final PendingIntent localPendingIntent1 =
                PendingIntent.getBroadcast( mContext, 0, new Intent( this.SENT ), 0 );
        final PendingIntent localPendingIntent2 =
                PendingIntent.getBroadcast( mContext, 0, new Intent( this.DELIVERED ), 0 );
        SubscriptionManager subscriptionManager = ((Activity) mContext).getSystemService( SubscriptionManager.class );


        File simfile = getBaseContext().getFileStreamPath( "simfile.txt" );
        int sim;
        if (simfile.exists()) {
            sim = Integer.parseInt( readFile( "simfile.txt" ) );
            //Toast.makeText( MainActivity.this,""+sim ,Toast.LENGTH_SHORT);
        } else {
            sim = 0;
        }

        if (checkSelfPermission( Manifest.permission.SEND_SMS ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Log.e("LOB"," 000000 ");
            return;
        }
        SubscriptionInfo subscriptionInfo = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex( sim );
        if (Build.VERSION.SDK_INT >= 22) {

            if (checkSelfPermission( Manifest.permission.SEND_SMS ) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }

            SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, text, localPendingIntent1, localPendingIntent2);
        }
        SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, text, localPendingIntent1, localPendingIntent2);
*/
        boolean permission = checkAndRequestPermissions();
        Toast.makeText( Main2Activity.this,"ok "+permission,Toast.LENGTH_SHORT ).show();
        if(permission)
        {
            smssend(number,text);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makePhoneCall (String number){

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Main2Activity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Main2Activity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                Toast.makeText(Main2Activity.this, "Permition denaied", Toast.LENGTH_SHORT).show();
            } else if (ContextCompat.checkSelfPermission(Main2Activity.this,
                    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Main2Activity.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                Toast.makeText(Main2Activity.this, "sim select denied", Toast.LENGTH_SHORT).show();
            } else {
                String dial = number;

                File simfile = getBaseContext().getFileStreamPath( "simfile.txt" );
                int sim ;
                if(simfile.exists())
                {
                    sim= Integer.parseInt( readFile( "simfile.txt" ) );
                    Toast.makeText( Main2Activity.this,""+sim ,Toast.LENGTH_SHORT);
                }
                else
                {
                    sim=0;
                }

                File f = getBaseContext().getFileStreamPath( "latlang.txt" );
                String str = " file problem ";
                if(f.exists())
                {
                    str = readFile( "latlang.txt" );
                }
                else
                {
                    str = "location dekhaya pari nai ";
                }

                //22/9[
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial));

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("com.android.phone.force.slot", true);
                intent.putExtra("Cdma_Supp", true);
                //Add all slots here, according to device.. (different device require different key so put all together)
                for (String s : simSlotName)
                    intent.putExtra(s, sim); //0 or 1 according to sim.......
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", " (Parcelable)  getCallCapablePhoneAccounts()");


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
                boolean permissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
                sendSMS( number,"I am in Danger plese help me :"+str );


                startActivity( intent );
                /*File f = getBaseContext().getFileStreamPath( "latlang.txt" );
                if(f.exists())
                {
                    str = readFile( "latlang.txt" );
                }*/
                Toast.makeText(Main2Activity.this, msg +" extra ", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(Main2Activity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }

    }




    /* @Override
     protected void onStart() {
         startService(new Intent(getApplicationContext(), LockService.class));
         super.onStart();

     }

     @Override
     protected void onStop() {
         startService(new Intent(getApplicationContext(), LockService.class));
         super.onStop();

     }



     @Override
     protected void onPause() {
         startService(new Intent(getApplicationContext(), LockService.class));
         super.onPause();

     }

     @Override
     protected void onResume() {
         startService(new Intent(getApplicationContext(), LockService.class));
         super.onResume();

     }

     @Override
     protected void onRestart() {
         startService(new Intent(getApplicationContext(), LockService.class));
         super.onRestart();

     }
 */
    @Override
    protected void onDestroy () {

        Toast.makeText(getApplicationContext(),
                "App destryed",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("com.android.ServiceStopped");
        sendBroadcast(intent);

        startService(new Intent(getApplicationContext(), LockService.class));


        super.onDestroy();

    }

    private boolean checkPermission () {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);

        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission () {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);

    }
    @Override
    public void onRequestPermissionsResult ( int requestCode, String permissions[],
                                             int[] grantResults){
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean callAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (callAccepted) {
                        // startService(new Intent(getApplicationContext(), LockService.class));
                        Toast.makeText(Main2Activity.this, " permission ", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(Main2Activity.this, " permission no", Toast.LENGTH_LONG).show();


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CALL_PHONE)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }
    private void showMessageOKCancel (String message, DialogInterface.OnClickListener
            okListener){
        new AlertDialog.Builder(Main2Activity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void goSettings (View view){
        //Intent settings = new Intent(Main2Activity.this, Settings.class);
       // startActivity(settings);
        switch (view.getId()){
            case R.id.guidebtn :
                Intent intent2 =new Intent(this,Guide.class);
                startActivity(intent2);
                break;
            case R.id.button :
                Intent settings = new Intent(this, Settings.class);
                startActivity(settings);
                break;

        }
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
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.as)
                        .setContentTitle("Help Me")
                        .setContentText("Help Me App is running ...");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        Notification notification = new Notification(  );
        builder.setAutoCancel( false );
        Intent nintent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;
    }
    private boolean checkAndRequestPermissions() {

        int permissionSendSms = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionReceiveSms = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        int permissionReadPhoneState = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionSendSms != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1
            );
        }

        if (permissionReceiveSms != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1
            );
        }

        if (permissionReadPhoneState != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1
            );
        }

        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    void smssend(String number,String sms)
    {
        final PendingIntent localPendingIntent1 =
                PendingIntent.getBroadcast( getBaseContext(), 0, new Intent( this.SENT ), 0 );
        final PendingIntent localPendingIntent2 =
                PendingIntent.getBroadcast( getBaseContext(), 0, new Intent( this.DELIVERED ), 0 );
        SubscriptionManager subscriptionManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            subscriptionManager = ((Activity) this).getSystemService( SubscriptionManager.class );
            // Toast.makeText( MainActivity.this,"ok1",Toast.LENGTH_SHORT ).show();
        }
        // Toast.makeText( MainActivity.this,"ok2",Toast.LENGTH_SHORT ).show();




        File simfile = getBaseContext().getFileStreamPath( "simfile.txt" );
        int sim;
        if (simfile.exists()) {
            sim = Integer.parseInt( readFile( "simfile.txt" ) );
            //Toast.makeText( MainActivity.this,""+sim ,Toast.LENGTH_SHORT);
        } else {
            sim = 0;
        }





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission( Manifest.permission.SEND_SMS ) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                Log.e("LOB"," 000000 ");
                Toast.makeText( Main2Activity.this,"ok5",Toast.LENGTH_SHORT ).show();
            }
            Toast.makeText( Main2Activity.this,"ok3",Toast.LENGTH_SHORT ).show();
        }
        else
        {
            Toast.makeText( Main2Activity.this,"ok4",Toast.LENGTH_SHORT ).show();
        }
        SubscriptionInfo subscriptionInfo = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex( sim );
        if (Build.VERSION.SDK_INT >= 22) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission( Manifest.permission.SEND_SMS ) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            // Toast.makeText( MainActivity.this,"ok4",Toast.LENGTH_SHORT ).show();

            SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, sms, localPendingIntent1, localPendingIntent2);
        }
        SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, sms  , localPendingIntent1, localPendingIntent2);
        Toast.makeText( Main2Activity.this,"ok",Toast.LENGTH_SHORT ).show();

    }

}
