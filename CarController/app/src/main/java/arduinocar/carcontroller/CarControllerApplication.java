package arduinocar.carcontroller;

import android.app.Application;

import arduinocar.carcontroller.logic.BluetoothSocketUtils;

/**
 * Created by malcolm on 04/01/17.
 */

public class CarControllerApplication extends Application {
    public CarControllerApplication() {
        new BluetoothSocketUtils().startConnectionWorker();
    }
}
