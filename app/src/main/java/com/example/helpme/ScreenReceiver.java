package com.example.helpme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.RequiresApi;

import static android.content.Context.ALARM_SERVICE;


public class ScreenReceiver extends BroadcastReceiver  {

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



    private static final String TAG = " new ";
    public static boolean wasScreenOn = true;
    long a ,b;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(final Context context, final Intent intent) {


        final Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        Log.e("LOB","onReceive");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            // vibe.vibrate(0b1111101000);


            a = System.currentTimeMillis();

            wasScreenOn = false;
            Log.e("LOB","wasScreenOn"+wasScreenOn);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here

            b=System.currentTimeMillis();
            wasScreenOn = true;

        }
        if((a-b<300 && a-b>10)||(b-a<300 && b-a>10))
        {
            /*Temp temp = new Temp();
            temp.calldebokina();*/

            vibe.vibrate(300);
            a=0;
            b=0;

            Intent in = new Intent( context, Main2Activity.class );
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    in, 0);
            // get the alarm manager, and scedule an alarm that calls the receiver
            AlarmManager mgr = (AlarmManager) context.getSystemService( ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis()+100 , pendingIntent);

            //doRestart(context);

            //
            Log.e("LOB"," 1111 ");
        }




    }
    public static void doRestart(Context c) {
        //[

        //]
        try {
            //check if the context is given
            if (c != null) {
                //fetch the packagemanager so we can get the default launch activity
                // (you can replace this intent with any other activity if you want
                PackageManager pm = c.getPackageManager();
                //check if we got the PackageManager
                if (pm != null) {
                    //create the intent with the default start activity for your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(
                            c.getPackageName()
                    );
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                        //create a pending intent so the application is restarted after System.exit(0) was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId, mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c.getSystemService( ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() , mPendingIntent);
                        //kill the application
                        //System.exit(0);
                    } else {
                        Log.e(TAG, "Was not able to restart application, mStartActivity null");
                    }
                } else {
                    Log.e(TAG, "Was not able to restart application, PM null");
                }
            } else {
                Log.e(TAG, "Was not able to restart application, Context null");
            }
        } catch (Exception ex) {
            Log.e(TAG, "Was not able to restart application");
        }
    }







}
