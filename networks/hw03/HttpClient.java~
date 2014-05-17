/*
 * HttpClient App upon TCP
 * Author: Oscar Menendez
 *
 */

import java.io.*;
import java.net.*;

public class HttpClient {

	public static void main(String[] args) throws IOException {
		Socket tcpSocket = null;
		PrintWriter socketOut = null;
		BufferedReader socketIn = null;
		long startTime = 0;
		long endTime = 0;
		BufferedReader sysIn = new BufferedReader(new InputStreamReader(
				System.in));
		PrintWriter fileWriter = null;
		// ask user for input
		System.out.println("Input DNS/IP number: ");
		String host = sysIn.readLine();

		try {
			startTime = System.currentTimeMillis();
			tcpSocket = new Socket(host, 5678); // establishing connection
			socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(
					tcpSocket.getInputStream()));
			endTime = System.currentTimeMillis();
			System.out.println("RTT for socket connection: "
					+ (endTime - startTime) + " ms");
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "
					+ host);
			System.exit(1);
		}

		while (true) {

			System.out.println("Input HTTP method type: ");
			String httpMethod = sysIn.readLine();

			System.out.println("Input file name: ");
			String htmfile = sysIn.readLine();

			System.out.println("Input HTTP version: ");
			String httpVersion = sysIn.readLine();

			System.out.println("Input user Agent: ");
			String userAgent = sysIn.readLine();

			socketOut.println(httpMethod + " /" + htmfile + " " + httpVersion
					+ "\r\n" + "Host: " + host + "\r\n" + "User-Agent: "
					+ userAgent + "\r\n\r\n");
			fileWriter = new PrintWriter(new File(htmfile));

			int emptyLines = 0;// counts number of empty lines.
			boolean done = false;
			String header = "";
			String line = "";
			while (emptyLines < 3) {
				line = socketIn.readLine();
				if (line.isEmpty()) {
					emptyLines++;
					done = true;
				} else {
					emptyLines = 0;
				}
				if (!done) {
					header = header + line;
					System.out.println(line);
				}
				else if (done && header.contains("200 OK")){//after 1 empty line write file
					//line = socketIn.readLine();					
					fileWriter.println(line);
					System.out.println(line);			
						
				}else {
					emptyLines = 3;				
				}
			}
			fileWriter.close();
			System.out.println("Do you wish to continue(yes/no)?");
			String user = sysIn.readLine();

			if (user.equalsIgnoreCase("yes")) {
				done = true;
			} else {
				socketOut.close();
				socketIn.close();
				sysIn.close();
				tcpSocket.close();
				System.exit(0);

			}
		}
	}
}
