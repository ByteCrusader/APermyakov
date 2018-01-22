package ru.apermyakov.io.socket.manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Class for realize server activities.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.12.2017
 */
public class Server implements ServerInterface {

    /**
     * Field for socket.
     */
    private Socket socket;

    /**
     * Field for root catalog.
     */
    private String rootCatalog;

    /**
     * Field for file handler.
     */
    private FileHandler handler;

    /**
     * Design server with socket for jtest.
     *
     * @param socket socket
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Default designer.
     */
    public Server() {}

    /**
     * Method for get root catalog.
     *
     * @return list of root's files
     */
    @Override
    public List<String> getRootCatalog() {
        File catalog = new File(this.rootCatalog);
        return Arrays.stream(catalog.listFiles()).filter(i -> !i.isHidden()).map(File::getPath).collect(Collectors.toList());
    }

    /**
     * Method for go to sub catalog.
     *
     * @param message message
     * @return answer
     */
    @Override
    public String goToSubCatalog(String message) {
        message = message.replaceAll("up ", "");
        this.rootCatalog = this.rootCatalog.concat(String.format("\\%s", message));
        return String.format("Correct up to %s", this.rootCatalog);
    }

    /**
     * Method for go to parent catalog.
     *
     * @param message message
     * @return answer
     */
    @Override
    public String goToParentCatalog(String message) {
        int lastIndex = this.rootCatalog.lastIndexOf('\\');
        this.rootCatalog = this.rootCatalog.substring(0, ++lastIndex);
        return String.format("Correct down to %s", this.rootCatalog);
    }

    /**
     * Method for work with files - upload and download.
     *
     * @param file file
     * @param upOrDownLoad upload or download
     * @return answer
     * @throws IOException e
     */
    private String workWithFiles(File file, boolean upOrDownLoad) throws IOException {
        String result;
        String firstMessage = upOrDownLoad ? "save" : "send";
        if (upOrDownLoad ? file.createNewFile() : file.exists()) {
            if (upOrDownLoad) {
                this.handler.convertFileToInputStream(file, this.socket);
            } else {
                this.handler.convertFileToOutputStream(file, this.socket);
            }
            result = String.format("Server correct %s file", firstMessage);
        } else {
            result = String.format("Server cannot %s file or this file %s exist", firstMessage, upOrDownLoad ? "already" : "doesn't");
        }
        return result;
    }

    /**
     * Method for download file.
     *
     * @param file file
     * @return answer
     * @throws IOException e
     */
    @Override
    public String downloadFile(File file) throws IOException {
        return this.workWithFiles(file, false);
    }

    /**
     * Method for upload file.
     *
     * @param file file
     * @return answer
     * @throws IOException e
     */
    @Override
    public String uploadFile(File file) throws IOException {
        return this.workWithFiles(file, true);
    }

    /**
     * Method for get action by message.
     *
     * @param message message
     * @return list of answers
     * @throws IOException e
     */
    private List<String> getActionByMessage(String message) throws IOException {
        List<String> result = new ArrayList<>();
        if (message.contains("get")) {
            result.addAll(this.getRootCatalog());
        } else if (message.contains("download")) {
            File file = this.handler.buildFileByMessage(message, "download", this.rootCatalog);
            result.add(this.downloadFile(file));
        } else if (message.contains("upload")) {
            File file = this.handler.buildFileByMessage(message, "upload", this.rootCatalog);
            result.add(this.uploadFile(file));
        } else if (message.contains("up")) {
            result.add(this.goToSubCatalog(message));
        } else if (message.contains("down")) {
            result.add(this.goToParentCatalog(message));
        }
        return result;
    }

    /**
     * Method for waiting action.
     *
     * @param socket socket
     * @throws IOException e
     */
    private void waitingAction(Socket socket) throws IOException {
        try (BufferedReader inReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String message;
            System.out.println("Server build, ready to work");
            do {
                message = inReader.readLine();
                System.out.println(String.format("Catch message: %s", message));
                for (String answer : getActionByMessage(message)) {
                    outWriter.println(answer);
                    System.out.println(String.format("Answer: %s", answer));
                }
                outWriter.println();
            } while (!"exit".equals(message));
        }
    }

    /**
     * Method for initial server.
     *
     * @throws IOException e
     */
    @Override
    public void initialServer() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        int port = Integer.parseInt(properties.getProperty("port"));
        this.rootCatalog = properties.getProperty("catalog");
        if (this.socket == null) {
            this.socket = new ServerSocket(port).accept();
        }
        this.handler = new FileHandler();
    }

    /**
     * Method for start server.
     *
     * @throws IOException e
     */
    @Override
    public void startServer() throws IOException {
        try {
            waitingAction(this.socket);
        } finally {
            if (this.socket != null) {
                this.socket.close();
            }
        }
    }

    /**
     * Main method for test activity.
     *
     * @param args args
     * @throws IOException e
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.initialServer();
        server.startServer();
    }
}
