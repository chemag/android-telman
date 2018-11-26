package com.example.telman;


import android.Manifest;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Process;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import java.util.Vector;
import java.util.Collections;


// https://www.javatpoint.com/android-telephony-manager-tutorial

public class MainActivity extends AppCompatActivity {
    TextView textView1;  

    protected String getTelmanString() {
        // https://stackoverflow.com/a/22719891
        TelephonyManager tm =
            (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);  

        // check permissions
        // https://developer.android.com/training/permissions/requesting
        if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        // look inside
        String IMEINumber = tm.getDeviceId();  
        String subscriberID = tm.getDeviceId();  
        String SIMSerialNumber = tm.getSimSerialNumber();  
        String networkCountryISO = tm.getNetworkCountryIso();  
        String SIMCountryISO = tm.getSimCountryIso();  
        String softwareVersion = tm.getDeviceSoftwareVersion();  
        String voiceMailNumber = tm.getVoiceMailNumber();  
          
        // get the phone type  
        String strphoneType = "";  

        int phoneType = tm.getPhoneType();  
  
        switch (phoneType)   
        {  
                case (TelephonyManager.PHONE_TYPE_CDMA):  
                           strphoneType = "CDMA";  
                               break;  
                case (TelephonyManager.PHONE_TYPE_GSM):   
                           strphoneType = "GSM";                
                               break;  
                case (TelephonyManager.PHONE_TYPE_NONE):  
                            strphoneType = "NONE";                
                                break;  
         }  
          
        // get information if phone is in roaming  
        boolean isRoaming = tm.isNetworkRoaming();  
          
        String info = "Phone Details:\n";  
        info += "\n IMEI Number:" + IMEINumber;  
        info += "\n SubscriberID:" + subscriberID;  
        info += "\n Sim Serial Number:" + SIMSerialNumber;  
        info += "\n Network Country ISO:" + networkCountryISO;  
        info += "\n SIM Country ISO:" + SIMCountryISO;  
        info += "\n Software Version:" + softwareVersion;  
        info += "\n Voice Mail Number:" + voiceMailNumber;  
        info += "\n Phone Network Type:" + strphoneType;  
        info += "\n In Roaming? :" + isRoaming;  

        return info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telman);
        String str = getTelmanString();
        // print the string
        TextView telmanList = findViewById(R.id.telman);
        telmanList.setMovementMethod(new ScrollingMovementMethod());
        telmanList.setText(str);
    }
}
