package com.forseek;

import android.Manifest;
import android.app.Activity;;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.location);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationUpdates(location);
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
        });

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationUpdates(location);

    }
    public void locationUpdates(Location location){
        if (location!=null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("你的位置信息:\n");
            stringBuilder.append("经度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("\n维度: ");
            stringBuilder.append(location.getLatitude());
            stringBuilder.append("\n精确度:");
            stringBuilder.append(location.getAccuracy());
            stringBuilder.append("\n高度: ");
            stringBuilder.append(location.getAltitude());
            stringBuilder.append("\n方向： ");
            stringBuilder.append(location.getBearing());
            stringBuilder.append("\n速度: ");
            stringBuilder.append(location.getSpeed());
            stringBuilder.append("\n时间: ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH mm ss");
            stringBuilder.append(dateFormat.format(new Date(location.getTime())));
            text.setText(stringBuilder.toString());
        }else {
            text.setText("没有获取到GPS信息");
        }
    }
}
