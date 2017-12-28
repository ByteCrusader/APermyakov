package ru.apermyakov.io.socket.manager;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * Class for realize client actions.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.12.2017
 */
public class Client implements ClientInterface {

    /**
     * Field for client catalog.
     */
    private String clientCatalog;

    /**
     * Field for socket.
     */
    private Socket socket;

    /**
     * Field for file handler.
     */
    private FileHandler handler;

    /**
     * Method for send message to server.
     *
     * @param message message
     * @throws IOException e
     */
    private void sendMessageToServer(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
    }

    /**
     * Method for send correct message to client.
     *
     * @param message message
     * @return true
     */
    private boolean sendCorrectMessageToClient(String message) {
        System.out.format("File correct %s", message);
        return true;
    }

    /**
     * Method for send error message to client.
     *
     * @param message message
     * @return false
     */
    private boolean sendErrorMessageToClient(String message) {
        System.out.format("File %s exist in client catalog", message);
        return false;
    }

    /**
     * Method for work with files - upload or download.
     *
     * @param message message
     * @param file file
     * @param upOrDownLoad upload or download
     * @return success or not
     * @throws IOException e
     */
    private boolean workWithFiles(String message, File file, boolean upOrDownLoad) throws IOException {
        boolean result;
        if (upOrDownLoad ? file.exists() : file.createNewFile()) {
            this.sendMessageToServer(message);
            if (upOrDownLoad) {
                this.handler.convertFileToOutputStream(file, this.socket);
            } else {
                this.handler.convertFileToInputStream(file, this.socket);
            }
            result = sendCorrectMessageToClient(upOrDownLoad ? "upload" : "download");
        } else {
            result = sendErrorMessageToClient(upOrDownLoad ? "doesn't" : "already");
        }
        System.out.println();
        return result;
    }

    /**
     * Method for initial client object.
     *
     * @throws IOException e
     */
    @Override
    public void initialClient() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        int port = Integer.parseInt(properties.getProperty("port"));
        String ip = properties.getProperty("ip");
        this.socket = new Socket(InetAddress.getByName(ip), port);
        this.clientCatalog = properties.getProperty("client");
        this.handler = new FileHandler();
    }

    /**
     * Method for check message.
     *
     * @param message message
     * @return success or not
     * @throws IOException e
     */
    private boolean checkMessage(String message) throws IOException {
        final String upload = "upload";
        final String download = "download";
        boolean result = true;
        if (message.contains(upload) || message.contains(download)) {
            boolean upOrDownLoad = message.contains(upload);
            File file = this.handler.buildFileByMessage(message, upOrDownLoad ? upload : download, this.clientCatalog);
            result = workWithFiles(message, file, upOrDownLoad);
        } else {
            this.sendMessageToServer(message);
        }
        return result;
    }

    /**
     * Method for start initial activity.
     *
     * @throws IOException e
     */
    @Override
    public void startClient() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String message;
            String answer;
            do {
                message = reader.readLine();
                if (checkMessage(message)) {
                    while (!(answer = in.readLine()).isEmpty()) {
                        System.out.println(answer);
                    }
                    System.out.println("");
                }
            } while (!message.contains("exit"));
        } finally {
            if (this.socket != null) {
                this.socket.close();
            }
        }
    }

    /**
     * Main method for test client.
     *
     * @param args args
     * @throws IOException e
     */
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initialClient();
        client.startClient();
    }
}