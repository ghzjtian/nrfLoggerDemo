package com.example.tianzeng.nrfloggerdemo;

import android.content.ContentProviderClient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import no.nordicsemi.android.log.ILogSession;
import no.nordicsemi.android.log.LocalLogSession;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.log.LogContract.Log.Level;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    ILogSession mLogSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show information if nRF Logger is not installed
        if (!logProviderExists()) {
            Toast.makeText(this, "nRF Logger is not installed. Using local log.", Toast.LENGTH_SHORT).show();
        }

        final SimpleDateFormat sdf = new SimpleDateFormat();
         mLogSession = Logger.newSession(MainActivity.this, "key", "name");


        findViewById(R.id.addBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dateTime = sdf.format(new Date());


                int level = Level.INFO;
//                level = Level.VERBOSE;
//                level = Level.DEBUG;
//                level = Level.INFO;
//                level = Level.APPLICATION;
//                level = Level.WARNING;
//                level = Level.ERROR;

                Log.i(TAG,"Current Date is:" + dateTime);

                level = Level.VERBOSE;
                Logger.log(mLogSession, level, "VERBOSE,Current Date is:" + dateTime);

                level = Level.DEBUG;
                Logger.log(mLogSession, level, "DEBUG,Current Date is:" + dateTime);

                level = Level.INFO;
                Logger.log(mLogSession, level, "INFO,Current Date is:" + dateTime);

                level = Level.APPLICATION;
                Logger.log(mLogSession, level, "APPLICATION,Current Date is:" + dateTime);

                level = Level.WARNING;
                Logger.log(mLogSession, level, "WARNING,Current Date is:" + dateTime);

                level = Level.ERROR;
                Logger.log(mLogSession, level, "ERROR,Current Date is:" + dateTime);

            }
        });

    }


    private boolean logProviderExists() {
        // The method below requires API 16
        final ContentProviderClient unstableClient = getContentResolver().acquireUnstableContentProviderClient(LogContract.AUTHORITY);
        if (unstableClient == null)
            return false;

        unstableClient.release();
        return true;
    }


    @Override
    public void onDestroy() {
        try {
            // Let's delete the local log session when exit
            if (mLogSession != null) {
                LocalLogSession session = (LocalLogSession) mLogSession;
                session.delete();
            }
        } catch (ClassCastException e) {
            // do nothing, nRF Logger is installed
        }
        super.onDestroy();
    }
}
