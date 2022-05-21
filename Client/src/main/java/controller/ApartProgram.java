package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ApartProgram {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MainController mainController;

    public ApartProgram(ObjectInputStream in, ObjectOutputStream out) {
        this.in=in;
        this.out=out;
    }

    public void run() {

        mainController = new MainController(in, out);
        mainController.run();

    }
}
