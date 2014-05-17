/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 */

import java.net.*;
import java.io.*;

public class SmtpServer extends Thread {
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

		while (listening) {
			new SmtpServer(serverTCPSocket.accept()).start();
		}
		serverTCPSocket.close();
	}

	public SmtpServer(Socket socket) {
		super("TCPMultiServerThread");
		clientTCPSocket = socket;
	}

	public void run() {

		try {
			PrintWriter cSocketOut = new PrintWriter(
					clientTCPSocket.getOutputStream(), true);
			BufferedReader cSocketIn = new BufferedReader(
					new InputStreamReader(clientTCPSocket.getInputStream()));

			String fromClient;
			cSocketOut.println("220 " + InetAddress.getLocalHost());

			int i = 0;
			while ((fromClient = cSocketIn.readLine()) != null) {
				
				if (fromClient.split("\\s+")[0].equals("HELO") && i == 0) {
					i++;
					System.out.println(fromClient);
					cSocketOut.println("250 <"
							+ InetAddress.getLocalHost()
							+ "> Hello <"
							+ clientTCPSocket.getRemoteSocketAddress()
									.toString() + ">");
				} else if (i == 0) {
					cSocketOut.println("503 5.5.2 Send HELO first");
					System.out.println(fromClient);
				}

				else if (fromClient.split(": ")[0].equals("MAIL FROM") && (i == 1)) {
					System.out.println(fromClient);
					cSocketOut.println("250 2.1.0 Sender OK");
					i++;
				} else if (i == 1) {
					cSocketOut.println("503 5.5.2 Need mail command");
					System.out.println(fromClient);
				}
				else if (fromClient.split(": ")[0].equals("RCPT TO") && i == 2) {
					System.out.println(fromClient);
					cSocketOut.println("“250 2.1.5 Recipient OK");
					i++;
				} else if (i == 2) {
					cSocketOut.println("503 5.5.2 Need rcpt command");
					System.out.println(fromClient);
				}

				else if (fromClient.equals("DATA") && i == 3) {
					System.out.println(fromClient);
					cSocketOut
							.println("“354 Start mail input; end with <CRLF>.<CRLF>");
					i++;
				} else if (i == 3) {
					cSocketOut.println("503 5.5.2 Need data command");
					System.out.println(fromClient);
				}
				
				if((i == 4) && fromClient.equals(".")){
					cSocketOut.println("250 Message received and to be delivered");
					i = 0;
				}
				else if(i == 4)
					System.out.println(fromClient);
				
				if (fromClient.equals("QUIT"))
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
