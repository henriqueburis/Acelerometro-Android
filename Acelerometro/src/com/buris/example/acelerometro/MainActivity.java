package com.buris.example.acelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity implements SensorEventListener {

	private TextView textViewX;
	private TextView textViewY;
	private TextView textViewZ;
	private TextView textViewDetail;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewX = (TextView) findViewById(R.id.text_view_x);
		textViewY = (TextView) findViewById(R.id.text_view_y);
		textViewZ = (TextView) findViewById(R.id.text_view_z);
		textViewDetail = (TextView) findViewById(R.id.text_view_detail);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		Log.i("Log.i: ", "Iniciando");

	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		Float x = arg0.values[0];
		Float y = arg0.values[1];
		Float z = arg0.values[2];

		Log.i("Log.i: ", x.toString());

		/*
		 * Os valores ocilam de -10 a 10. Quanto maior o valor de X mais ele ta
		 * caindo para a esquerda - Positivo Esqueda Quanto menor o valor de X
		 * mais ele ta caindo para a direita - Negativo Direita Se o valor de X
		 * for 0 ent�o o celular ta em p� - Nem Direita Nem Esquerda Se o valor
		 * de Y for 0 ent�o o cel ta "deitado" Se o valor de Y for negativo
		 * ent�o ta de cabe�a pra baixo, ent�o quanto menor y mais ele ta
		 * inclinando pra ir pra baixo Se o valor de Z for 0 ent�o o dispositivo
		 * esta reto na horizontal. Quanto maioro o valor de Z Mais ele esta
		 * inclinado para frente Quanto menor o valor de Z Mais ele esta
		 * inclinado para traz.
		 */
		textViewX.setText("Posi��o X: " + x.intValue() + " Float: " + x);
		textViewY.setText("Posi��o Y: " + y.intValue() + " Float: " + y);
		textViewZ.setText("Posi��o Z: " + z.intValue() + " Float: " + z);

		if (y < 0) { // O dispositivo esta de cabe�a pra baixo
			if (x > 0)
				textViewDetail
						.setText("Virando para ESQUERDA ficando INVERTIDO");
			if (x < 0)
				textViewDetail
						.setText("Virando para DIREITA ficando INVERTIDO");
		} else {
			if (x > 0)
				textViewDetail.setText("Virando para ESQUERDA ");
			if (x < 0)
				textViewDetail.setText("Virando para DIREITA ");
		}

	}

}
