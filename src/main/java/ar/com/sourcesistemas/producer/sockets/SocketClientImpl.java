package ar.com.sourcesistemas.producer.sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientImpl {

    public SocketClientImpl(int port){
        Socket socket = null;
        ObjectOutputStream buffer = null;

        try {
            socket = new Socket("127.0.0.1",port);
            buffer = new ObjectOutputStream(socket.getOutputStream());
            buffer.writeObject("Start");
        } catch (IOException e) {
           System.exit(1) ;
        }

        System.out.println("message sent closign socket");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("socket closed");


    }
}
