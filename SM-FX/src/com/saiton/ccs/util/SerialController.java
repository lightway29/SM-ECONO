/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.util;

import com.saiton.ccs.scale.ScaleController;
import com.saiton.ccs.scale.ScaleRegisterController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public class SerialController implements SerialPortEventListener {

    SerialPort serialPort;
    /**
     * The port we're normally going to use.
     */
//    private static final String PORT_NAMES[] = {
//        //     "/dev/cu.usbserial", // Mac OS X
//        "/dev/tty.usbmodem1421", // Mac OS X
//    //        "/dev/ttyUSB0", // Linux
//    //        "COM5", // Windows
//    };

//    String port = "/dev/cu.usbmodem1421";
    //private BufferedReader input;
    private InputStream input;
//     private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
//    private static final int DATA_RATE = 1200;

    public void initialize(String port, int DATA_RATE) {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.
                    nextElement();
//        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(port)) {
                System.out.println("Initialized");
                portId = currPortId;
                break;
            }
//        }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
//            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
//                    | SerialPort.FLOWCONTROL_RTSCTS_OUT);
//            serialPort.setRTS(false);

            // open the streams
//            input = new BufferedReader(new InputStreamReader(serialPort.
//                    getInputStream()));
            input = serialPort.
                    getInputStream();
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {

                String inputLine = null;

                byte[] readBuffer = new byte[40];
                StringBuilder sb = new StringBuilder();
                try {
                    while (input.available() > 0) {
                        int numBytes = input.read(readBuffer);
                    }

//                    ScaleController.currentReading = new String(readBuffer);

                    
                    System.out.print("CurrentReading Value = "+ScaleController.currentReading);
                    System.out.print("Original Value = "
                            + new String(readBuffer,"ASCII"));
                    ScaleController.currentReading = new String(readBuffer, "ASCII");
                    //close();
                    

                } catch (IOException e) {
                    close();
                    e.printStackTrace();
                }
                //readBuffer = null;
                
                System.out.println("Reading from serial monitor - "
                        + ScaleController.currentReading);
                close();

            } catch (Exception e) {
                close();
//                System.out.println("Input Issue detected.");
//                e.printStackTrace();
            }
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Main Method">
    //public static void main(String[] args) throws Exception {
//    SerialController main = new SerialController();
//    main.initialize();
//    Thread t=new Thread() {
//        public void run() {
//            //the following line will keep this app alive for 1000    seconds,
//            //waiting for events to occur and responding to them    (printing incoming messages to console).
//            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
//        }
//    };
//    t.start();
//    System.out.println("Started");
//}
//</editor-fold>

}
