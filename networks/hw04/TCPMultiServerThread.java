/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 */ 

import java.net.*;
import java.io.*;

public class TCPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;
	

public static void main(String[] args) throws IOException {
        ServerSocket serverTCPSocket = null;
        boolean listening = true;

        try {
            serverTCPSocket = new ServerSocket(4567);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4567.");
            System.exit(-1);
        }

        while (listening){
	    		new TCPMultiServerThread(serverTCPSocket.accept()).start();
		  }
			
        serverTCPSocket.close();
    }

    public TCPMultiServerThread(Socket socket) {
		super("TCPMultiServerThread");
		clientTCPSocket = socket;
    }

    public void run() {

		try {
	 	   PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
	  		BufferedReader cSocketIn = new BufferedReader(
				    new InputStreamReader(
				    clientTCPSocket.getInputStream()));

	      String fromClient, toClient;
			  
	 	   while ((fromClient = cSocketIn.readLine()) != null) {
				
				toClient = fromClient.toUpperCase();
				cSocketOut.println(toClient);
				
				if (fromClient.equals("Bye"))
				    break;
	 	   }
			
		   cSocketOut.close();
		   cSocketIn.close();
		   clientTCPSocket.close();

		} catch (IOException e) {
		    e.printStackTrace();
		}
    }
}
