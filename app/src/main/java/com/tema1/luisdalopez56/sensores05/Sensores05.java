package com.tema1.luisdalopez56.sensores05;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores05 extends AppCompatActivity implements SensorEventListener {

    private int contador;
    private double azimut = 0, vertical = 0, lateral = 0;
    private String orientacion = "posicion";
    private TextView tvAzimut, tvVertical, tvLateral, tvOrientacion, tvContador;

    SensorManager sensorManager;
    Sensor orienta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores05);

        tvAzimut = findViewById(R.id.tvAzimut);
        tvVertical = findViewById(R.id.tvVertical);
        tvLateral = findViewById(R.id.tvLateral);
        tvOrientacion = findViewById(R.id.tvOrientación);
        tvContador = findViewById(R.id.tvContador);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orienta = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, orienta, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        azimut = event.values[0];
        vertical = event.values[1];
        lateral = event.values[2];
        contador++;


        if (azimut < 22) orientacion = "NORTE";
        else if (azimut < 67) orientacion = "NORESTE";
        else if (azimut < 112) orientacion = "ESTE";
        else if (azimut < 157) orientacion = "SURESTE";
        else if (azimut <202) orientacion = "SUR";
        else if (azimut < 247) orientacion = "SUROESTE";
        else if (azimut < 292) orientacion = "OESTE";
        else if (azimut < 337) orientacion = "NOROESTE";
        else orientacion = "NORTE";

        if (vertical < -50 ) orientacion = "VERTICAL ARRIBA";
        if (vertical > 50) orientacion = "VERITCAL ABAJO";

        if (lateral > 50) orientacion = "LATERAL IZQUERDA";
        if (lateral < -50) orientacion = "LATERAL DERECHA";

        runOnUiThread(new CambiaTexto());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class CambiaTexto implements Runnable{
        @Override
        public void run() {
            tvAzimut.setText(""+azimut);
            tvVertical.setText(""+vertical);
            tvLateral.setText(""+lateral);
            tvOrientacion.setText(""+orientacion);
            tvContador.setText(""+contador);
        }

    }
}
