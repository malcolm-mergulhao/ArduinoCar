package arduinocar.carcontroller.logic;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by malcolm on 03/01/17.
 */

public class BluetoothSocketUtils {
    public static BluetoothSocket bluetoothSocket;

    private static void addBluetoothSocket(BluetoothSocket newSocket) {
        bluetoothSocket = newSocket;
    }

    public void startConnectionWorker() {
        Thread connectionThread = new Thread(new ConnectThread(new connectThreadCallback()));
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

    class connectThreadCallback implements Callback {

        @Override
        public void callBack(ExecResult execResult) {
            if(execResult.data.containsKey("error")) {
                System.out.println("Something went wrong when connection to bluetooth");
                System.out.println(execResult.data.get("error"));
                System.out.println("Starting new thread");

                Thread connectionThread = new Thread(new ConnectThread(this));
                connectionThread.start();
            } else {
                if(execResult.data.containsKey("socket") && execResult.data.get("socket") instanceof BluetoothSocket) {
                    BluetoothSocket bluetoothSocket = (BluetoothSocket) execResult.data.get("socket");
                    if(bluetoothSocket.isConnected()) {
                        System.out.println("Connection to socket successful");
                        addBluetoothSocket((BluetoothSocket)execResult.data.get("socket"));
                    } else {
                        System.out.println("Socket was created but cannot be connected to");
                    }
                } else {
                    System.out.println("Something was wrong with the socket");
                }
            }
        }
    }
}

