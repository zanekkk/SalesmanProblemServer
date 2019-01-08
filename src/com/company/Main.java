package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Main{

    public static void main(String[] args) throws IOException {

        ArrayList<Fitness> fitnessList = new ArrayList<>();
        Lock lock = new Lock();
        ServerSocket ss = new ServerSocket(4000);
        Tour[] tours ;

        while (true)
        {

            try
            {
                Socket s = ss.accept();

                System.out.println("A new client is connected : " + s);


                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());

                tours = (Tour[]) is.readObject();

                for (int i = 0; i < tours.length ; i++) {

                    fitnessList.add(new Fitness(tours[i], lock));
                    fitnessList.get(i).start();  // Start download in another thread

                }


                while (lock.getRunningThreadsNumber() > 0) {
                    synchronized (lock) {
                        lock.wait();
                    }

                }


                for(int i = 0; i < tours.length ; i++){
                    if(tours[i].getFitness() == 0){
                        tours[i] = fitnessList.get(i).getTour();
                    }
                }


                os.writeObject(tours);
                fitnessList.clear();

            }
            catch (Exception e){

                e.printStackTrace();
            }
        }

    }
}
