/*
 * Client App upon TCP
 *
 * Weiying Zhu
 *
 */ 

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
        long startTime = 0; 
        long endTime = 0; 
        Scanner userIn = new Scanner(System.in);

        //ask user for input
        System.out.println("Input DNS/IP number: ");
        String ip = userIn.nextLine();

        System.out.println(""); 

        try {
            startTime = System.currentTimeMillis();
            tcpSocket = new Socket(args[0], 4567); //establishing connection
            endTime = System.currentTimeMillis();
            System.out.println("RTT for socket connection: " + 
                (endTime - startTime);

            socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + args[0]);
            System.exit(1);
        }

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromUser = sysIn.readLine()) != null) {
		      System.out.println("Client: " + fromUser);
                socketOut.println(fromUser);
				
				if ((fromServer = socketIn.readLine()) != null)
				{
					System.out.println("Server: " + fromServer);
				}
				else {
                System.out.println("Server replies nothing!");
                break;
				}
		    
			   if (fromUser.equals("Bye."))
					break;
         
        }

        socketOut.close();
        socketIn.close();
        sysIn.close();
        tcpSocket.close();
    }
}
