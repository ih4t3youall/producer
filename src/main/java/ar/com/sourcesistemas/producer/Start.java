package ar.com.sourcesistemas.producer;


import ar.com.sourcesistemas.producer.sockets.SocketClientImpl;
import ar.com.sourcesistemas.producer.utils.FileResourcesUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Start {

    private MessageSender messageSender;
    private long id = 0;
    private Gson gson = new Gson();
    private long startTime;
    private long endTime;

    public static void main(String [] args){
        if( args.length == 0){
            System.out.println("No arguments provided");
            System.exit(1);
        }
        new Start(args);
    }

    public void sendKickIn(List<Integer> ports){
        for(Integer port : ports) {
            new SocketClientImpl(port);
        }
    }

    public Start(String [] args){

        List<Integer> ports = new ArrayList<>();
        for (String arg : args){
            ports.add(Integer.valueOf(arg));
        }
        sendKickIn(ports);

        try {
            messageSender = new MessageSender();
            List<String> numeros = new FileResourcesUtils("numeros.txt").getLines();
            startTime = System.currentTimeMillis();

            System.out.println("entergin the loop");
            for (String numero : numeros) {
                Data data = new Data(id, numero, String.valueOf(System.nanoTime()));
                messageSender.sendTextMessage(gson.toJson(data));
                id++;
                System.out.println(data.id);
            }
            System.out.println("sending end session message.");
            messageSender.sendTextMessage(gson.toJson(new Data(-1l,"123",String.valueOf(System.nanoTime()))));

            endTime = System.currentTimeMillis();
            System.out.println("final time: "+ (endTime - startTime));
            messageSender.closeConnection();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
