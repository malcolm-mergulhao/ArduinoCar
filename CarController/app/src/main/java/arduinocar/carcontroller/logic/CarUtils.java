package arduinocar.carcontroller.logic;

import java.util.HashMap;

interface Callback {
    void callBack(ExecResult execResult);
}

class ExecResult {
    public HashMap<String,Object> data;

    public ExecResult() {
        data = new HashMap<>();
    }
}