package com.forseek.demo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends Activity implements SensorEventListener {
    EditText textGRAVITY,textLIGHT;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textGRAVITY = (EditText) findViewById(R.id.textGRAVITY);
        textLIGHT = (EditText) findViewById(R.id.textLIGHT);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        int sensorType = event.sensor.getType();
        switch (sensorType){
            case Sensor.TYPE_GRAVITY:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("X轴横向重力值： ");
                stringBuilder.append(values[0]);
                stringBuilder.append("\nY轴横向重力值： ");
                stringBuilder.append(values[1]);
                stringBuilder.append("\nZ轴横向重力值： ");
                stringBuilder.append(values[2]);
                textGRAVITY.setText(stringBuilder.toString());
                break;
            case Sensor.TYPE_LIGHT:
                stringBuilder = new StringBuilder();
                stringBuilder.append("光的强度值:");
                stringBuilder.append(values[0]);
                textLIGHT.setText(stringBuilder.toString());
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }
    @Override
    protected void onStop(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }
}
