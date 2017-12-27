package ru.apermyakov.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.Thread.sleep;

/**
 * Class for modulate client activity.
 *
 * @author apermyakov
 * @version 1.0
 * @since 26.12.2017
 */
public class Client {

    /**
     * Field for server port.
     */
    private int serverPort;

    /**
     * Field for server ip address.
     */
    private final String ipAddress;

    /**
     * Design client.
     *
     * @param serverPort port
     * @param ipAddress ip
     */
    public Client(int serverPort, String ipAddress) {
        this.serverPort = serverPort;
        this.ipAddress = ipAddress;
    }

    /**
     * Method for start client.
     */
    public void startClient() throws UnknownHostException {
        final String separator = System.getProperty("line.separator");
        InetAddress address = InetAddress.getByName(this.ipAddress);
        try (Socket socket = new Socket(address, this.serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {

            System.out.println(
                    String.format(
                            "You have approached a strange shadow.%s" +
                                    "It turned out to be a small gray-haired old man.%s" +
                                    "You apply to him:%s"
                            , separator
                            , separator
                            , separator
                    ));

            String message;
            String answer;

            boolean goodbye = false;
            while (!goodbye) {
                message = reader.readLine();
                out.println(message);

                while (!(answer = in.readLine()).isEmpty()) {
                    System.out.println(answer);
                    sleep(500);
                    if (answer.toLowerCase().contains("bye")) {
                        goodbye = true;
                    }
                }
                System.out.println("");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method for test app.
     *
     * @param args args
     */
    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client(5354, "127.0.0.1");
        client.startClient();
    }
}
