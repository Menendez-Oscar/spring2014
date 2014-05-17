

/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this HttpServer
 * 
 */
import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HttpServer extends Thread {

    private Socket clientTCPSocket;

    public HttpServer(Socket socket) {
        super("TCPMultiServerThread");
        clientTCPSocket = socket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverTCPSocket = null;
        boolean listening = true;

        try {
            serverTCPSocket = new ServerSocket(5678);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5678.");
            System.exit(-1);
        }

        while (listening) {
            new HttpServer(serverTCPSocket.accept()).start();
        }

        serverTCPSocket.close();
    }

    public void run() {

        try {
            String status = "";
            String httpResponse = "";
            PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
            BufferedReader cSocketIn = new BufferedReader(
                    new InputStreamReader(
                            clientTCPSocket.getInputStream()));

            String fromClient, toClient;
            String[] request = new String[3];//line by line request.
            int line = 0;
            while ((fromClient = cSocketIn.readLine()) != null) {

                if (fromClient.equals("Bye") || fromClient.isEmpty()) {
                    break;
                } else {
                    request[line] = fromClient;
                }
                line++;
            }
            String[] requestLine = request[0].split(" ");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //get current date time with Date()
            Date date = new Date();
            if (!requestLine[0].equalsIgnoreCase("get")) {
                status = " 400 Bad Request ";
                httpResponse = requestLine[2] + status + " \r\n"
                        + "Date: " + date + "\r\n"
                        + "Server: Colorado\r\n\r\n";
                cSocketOut.println(httpResponse);
                System.out.println(httpResponse);
            } else {
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(requestLine[1].substring(1)));
                    status = " 200 OK";
                    httpResponse = requestLine[2] + status + "\r\n"
                            + "Date: " + date + "\r\n"
                            + "Server: Colorado\r\n\r\n";

                    //TODO read file and send response, also do the same for all the other requests.
                    String nextLine = null;
                    while ((nextLine = bf.readLine()) != null) {
                        httpResponse = httpResponse + "\r\n" + nextLine;
                    }
                    httpResponse = httpResponse + "\r\n\r\n\r\n\r\n";
                    cSocketOut.println(httpResponse);
                    System.out.println(httpResponse);
                    bf.close();
                } catch (FileNotFoundException e) {
                    status = " 404 Not Found";
                    httpResponse = requestLine[2] + status + "\r\n"
                            + "Date: " + date + "\r\n"
                            + "Server: Colorado\r\n\r\n";

                    cSocketOut.println(httpResponse);
                    System.out.println(httpResponse);
                }
            cSocketOut.close();
            cSocketIn.close();
            clientTCPSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
