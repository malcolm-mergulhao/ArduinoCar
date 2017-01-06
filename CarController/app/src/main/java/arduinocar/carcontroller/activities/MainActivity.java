package arduinocar.carcontroller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import arduinocar.carcontroller.R;
import arduinocar.carcontroller.logic.BluetoothSocketUtils;
import arduinocar.carcontroller.logic.ConnectThread;

//UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onUpClick(View v) {
		BluetoothSocketUtils.sendMessage("UP\n");
	}

	public void onDownClick(View v) {
		BluetoothSocketUtils.sendMessage("DOWN\n");
	}

	public void onLeftClick(View v) {
		BluetoothSocketUtils.sendMessage("LEFT\n");
	}

	public void onRightClick(View v) {
		BluetoothSocketUtils.sendMessage("RIGHT\n");
	}


}
