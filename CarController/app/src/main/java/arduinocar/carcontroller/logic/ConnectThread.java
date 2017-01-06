package arduinocar.carcontroller.logic;

/**
 * Created by malcolm on 02/01/17.
 */

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.telecom.Call;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ConnectThread implements Runnable {
    private final String ARDUINO_CAR_NAME = "ARDUINOCAR";
    private UUID socketConnectionUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Callback callback;

    public ConnectThread(Callback callback) {
        this.callback = callback;
    }

    public void run() {
        ExecResult execResult = new ExecResult();

        BluetoothDevice bluetoothDevice = getBluetoothDevice(ARDUINO_CAR_NAME, execResult);
        if(bluetoothDevice != null) {
            try {
                BluetoothSocket bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(socketConnectionUUID);
                bluetoothSocket.connect();
                execResult.data.put("socket", bluetoothSocket);
            } catch (IOException e) {
                execResult.data.put("error", "Something went wrong when creating socket");
                e.printStackTrace();
            }
        } else {
            if(!execResult.data.containsKey("error")) {
                execResult.data.put("error", "No bluetooth device with correct name could be found");
            }
        }

        callback.callBack(execResult);
    }

    private BluetoothDevice getBluetoothDevice(String deviceName, ExecResult execResult) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice bluetoothDevice = null;

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() == 0) {
            execResult.data.put("error", "Cound not find any bluetooth devices (is bluetooth turned on?)");
        } else {
            for (BluetoothDevice device : pairedDevices) {
                if(device.getName().equals(deviceName)) {
                    bluetoothDevice = device;
                    break;
                }
            }
        }

        return bluetoothDevice;
    }

}
