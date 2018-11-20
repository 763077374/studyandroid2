package com.forseek;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView) findViewById(R.id.provider);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providersNames = locationManager.getAllProviders();
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<String> iterator = providersNames.iterator(); ((Iterator) iterator).hasNext() ; ) {
            stringBuilder.append(iterator.next()+"\n");
        }
        textView.setText(stringBuilder.toString());


    }
}
