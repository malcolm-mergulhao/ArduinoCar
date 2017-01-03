package arduinocar.carcontroller.logic;

/**
 * Created by malcolm on 02/01/17.
 */

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ConnectThread implements Runnable {
    private String arduinoCarAddress = null;
    private final String ARDUINO_CAR_NAME = "ARDUINOCAR";
    private UUID socketConnectionUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public void run() {
        BluetoothDevice bluetoothDevice = getBluetoothDevice(ARDUINO_CAR_NAME);
        if(bluetoothDevice != null) {
            try {
                BluetoothSocket bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(socketConnectionUUID);
                bluetoothSocket.connect();
                BluetoothSocketUtils.addBluetoothSocket(bluetoothSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No bluetooth device with correct name could be found");
        }

    }

    private BluetoothDevice getBluetoothDevice(String deviceName) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice bluetoothDevice = null;

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() == 0) {
            System.out.println("Cound not find any bluetooth devices (is bluetooth turned on?)");
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
