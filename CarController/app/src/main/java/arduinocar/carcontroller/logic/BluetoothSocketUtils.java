package arduinocar.carcontroller.logic;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by malcolm on 03/01/17.
 */

public class BluetoothSocketUtils {
    public static BluetoothSocket bluetoothSocket;

    protected static void addBluetoothSocket(BluetoothSocket newSocket) {
        bluetoothSocket = newSocket;
    }

    public static void startConnectionWorker() {
        Thread connectionThread = new Thread(new ConnectThread());
        connectionThread.start();
    }

    public static void sendMessage(String message) {
        try {
            OutputStream out = bluetoothSocket.getOutputStream();
            out.write(message.getBytes());
        } catch (IOException e) {
            System.out.println("something weird happened when writing to bluetooth socket");
            e.printStackTrace();
        }
    }
}
