package com.forseek;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
import android.hardware.SensorEventListener;

public class PointerView extends View implements SensorEventListener {
    private Bitmap pointer = null;
    private float[] allValue;
    private SensorManager sensorManager;
    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pointer = BitmapFactory.decodeResource(super.getResources(), R.drawable.pointer);
       sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
       sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            float[] values = event.values;
            allValue = values;
            super.postInvalidate();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        if (allValue != null){
            float x = allValue[0];
            float y = allValue[1];
            canvas.save();
            canvas.restore();
            canvas.translate(super.getWidth()/2,super.getHeight()/2);
            if (y==0&&x>0){
                canvas.rotate(90);
            }else if (y==0 && x<0){
                canvas.rotate(270);
            }else {
                if (y>=0){
                    canvas.rotate((float) Math.tanh(x/y)*90);
                }else {
                    canvas.rotate(180+(float) Math.tanh(x/y)*90);
                }
            }
        }
        canvas.drawBitmap(this.pointer,-this.pointer.getWidth()/2,-this.pointer.getHeight()/2,p);
    }
}
