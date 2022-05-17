import controller.ApartProgram;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String address = "localhost";  //서버 주소
        int port = 3000;

        Socket socket = null;
        try {
            socket = new Socket(address, port);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            ApartProgram program = new ApartProgram(in, out);
            program.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

//
//        ApartProgram program = new ApartProgram();
//        program.run();
    }
}
