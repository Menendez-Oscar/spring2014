
/*
 * homework 2
 * Oscar Menendez
 * used Server App upon UDP
 * by Weiying Zhu
 */ 
 
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
                
        ArrayList<Data> data = new ArrayList<Data>();
                data.add(new Data("00001", "New Inspiron 15", "$379.99", 157));
                data.add(new Data("00002", "New Inspiron 17", "$449.99", 128));
                data.add(new Data("00003", "New Inspiron 15R", "$549.99", 202));
                data.add(new Data("00004", "New Inspiron 15z Ultrabook", "$749.99", 315));
                data.add(new Data("00005", "XPS 14 Ultrabook", "$999.99", 261));
                data.add(new Data("00006", "New XPS 12 UltrabookXPS", "$1199.99", 178));               
        
	        DatagramSocket udpServerSocket = null;
        	BufferedReader in = null;
		DatagramPacket udpPacket = null, udpPacket2 = null;
		String fromClient = null, toClient = null;
        	boolean morePackets = true;

		byte[] buf = new byte[256];
	
		udpServerSocket = new DatagramSocket(5678);
		  	  
        while (morePackets) {
            try {

                // receive UDP packet from client
                udpPacket = new DatagramPacket(buf, buf.length);
                udpServerSocket.receive(udpPacket);

		fromClient = new String(udpPacket.getData(), 0, udpPacket.getLength());
							
		// get the response
                for(int i = 0; i < data.size(); i++){
                    if(fromClient.equalsIgnoreCase(data.get(i).getItemId())){
                        toClient = data.get(i).ServerToString();
                    }
                }
		
		// send the response to the client at "address" and "port"
                InetAddress address = udpPacket.getAddress();
                int port = udpPacket.getPort();
		
		byte[] buf2 = toClient.getBytes();
                udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
                udpServerSocket.send(udpPacket2);
					 
            } catch (IOException e) {
                e.printStackTrace();
       	        morePackets = false;
            }
        }
		  
        udpServerSocket.close();

    }
}
