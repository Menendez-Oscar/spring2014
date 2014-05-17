
/*
 * homework 2
 * Oscar Menendez
 * used Client App upon UDP
 * by Weiying Zhu
 */

import java.io.*;
import java.net.*;
import java.util.*;	

public class Client {

    public static void main(String[] args) throws IOException {
        //data format
        Data df = new Data();
        ArrayList<Data> data = new ArrayList<Data>();
        data.add(new Data("00001", "New Inspiron 15"));
        data.add(new Data("00002", "New Inspiron 17"));
        data.add(new Data("00003", "New Inspiron 15R"));
        data.add(new Data("00004", "New Inspiron 15z Ultrabook"));
        data.add(new Data("00005", "XPS 14 Ultrabook"));
        data.add(new Data("00006", "New XPS 12 UltrabookXPS"));

        // creat a UDP socket
        DatagramSocket udpSocket = new DatagramSocket();

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        //ask the User to input the IP of the machine on which the Server Program runs.
        System.out.println("Enter IP address of the server program's machine");
        String ipAddress = sysIn.readLine();

        System.out.println("Item ID        " + "Item Description");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).clientToString());
        }

        System.out.println("Input Item ID");

        while ((fromUser = sysIn.readLine()) != null) {

            //display user input
            System.out.println("From Client: " + fromUser);

            // send request
            InetAddress address = InetAddress.getByName(ipAddress);
            byte[] buf = fromUser.getBytes();
            long startTime = System.currentTimeMillis();
            DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5678);
            udpSocket.send(udpPacket);

            // get response
            byte[] buf2 = new byte[256];
            DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
            udpSocket.receive(udpPacket2);
            long endTime = System.currentTimeMillis();

            long totalTime = endTime - startTime;
            // display response
            fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
            System.out.println(df.leftPad("Item ID", 15) + df.leftPad("Item Description", 30)
                    + df.leftPad("Unit Price", 15) + df.leftPad("Inventory", 15)
                    + "RTT of Query");
            System.out.println(fromServer + totalTime + " ms");

            if (fromUser.equals("Bye.")) {
                break;
            }
        }

        udpSocket.close();
    }
}
