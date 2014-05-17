/*
 * Client App upon TCP
 *
 * Weiying Zhu
 *
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SmtpClient {
	public static void main(String[] args) throws IOException {

		Socket tcpSocket = null;
		PrintWriter socketOut = null;
		BufferedReader socketIn = null;
		Scanner userIn = new Scanner(System.in);

		System.out.println("Enter Hostname or ip: ");
		String hostname = userIn.next();
		try {
			tcpSocket = new Socket(hostname, 4567);
			socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(
					tcpSocket.getInputStream()));
			System.out.println(socketIn.readLine());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + args[0]);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "
					+ args[0]);
			System.exit(1);
		}

		BufferedReader sysIn = new BufferedReader(new InputStreamReader(
				System.in));
		String fromServer;
		String fromUser;
		boolean done = false;
		while (!done) {

			System.out.println("Input Sender's email address:");
			String senderEmail = userIn.next();

			System.out.println("Input receiver’s email address:");
			String rcptEmail = userIn.next();

			System.out.println("Input subject:");
			String subject = userIn.next();

			System.out.println("Input email content:");
			String body = "";
			while(!(fromUser = userIn.next()).equals(".")){
				body = body + fromUser + "\n";
			}
			long startTime = System.currentTimeMillis();
		//	while (true) {
				String[] senderDomain = senderEmail.split("@");

				socketOut.println("HELO " + senderDomain[1]);// Mail From
				if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				} else {
					System.out.println("Server replies nothing!");
				}

				socketOut.println("MAIL FROM: <" + senderEmail + ">");// Mail
																		// From
				if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				} else {
					System.out.println("Server replies nothing!");
				}

				socketOut.println("RCPT TO: <" + rcptEmail + ">");// recpt From
				if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				} else {
					System.out.println("Server replies nothing!");
				}

				socketOut.println("DATA");// sending data command to server
				if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				} else {
					System.out.println("Server replies nothing!");
				}

				socketOut.println("To: " + rcptEmail + "\r\n" + "From: "
						+ senderEmail + "\r\n" + "subject: " + subject
						+ "\r\n\r\n" + body + "\r\n.");// sending the whole
														// message to server.

				if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				} else {
					System.out.println("Server replies nothing!");
				}
				System.out.println("Do you wish to continue(yes/no)? ");
				String decision = userIn.next();
				if (decision.equalsIgnoreCase("no")) {
					System.out.println((System.currentTimeMillis() - startTime) + " ms");
					socketOut.println("QUIT");
					socketOut.close();
					socketIn.close();
					sysIn.close();
					tcpSocket.close();
					userIn.close();
					done = true;
					break;
				} else {
					System.out.println((System.currentTimeMillis() - startTime) + "ms");
				}
			}
		}
	}
//}
