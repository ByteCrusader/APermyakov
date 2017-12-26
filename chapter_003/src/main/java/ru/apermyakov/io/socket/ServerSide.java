package ru.apermyakov.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class for modulate server activity.
 *
 * @author apermyakov
 * @version 1.0
 * @since 26.12.2017
 */
public class ServerSide {

    /**
     * Field for socket.
     */
    private final Socket socket;

    /**
     * Field for last message.
     */
    private String lastMessage;

    /**
     * Field for greed.
     */
    private boolean greet = false;

    /**
     * Design server.
     *
     * @param socket socket
     */
    public ServerSide(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method for create current data.
     *
     * @return data
     */
    private String createDate() {
        SimpleDateFormat format =  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return format.format(Calendar.getInstance().getTime());
    }

    /**
     * Method for build answer by message.
     *
     * @param message message
     * @return answer
     */
    private List<String> getAnswer(String message) {
        final String hi = "hello";
        final String yes = "yes";
        final String no = "no";
        List<String> answer = new ArrayList<>();
        if (message.toLowerCase().contains(hi)) {
            lastMessage = hi;
            answer.add("Greetings, traveler of the internet! ＼(￣▽￣)／");
            answer.add("My name is Omniscient Oracle! <(￣︶￣)>");
            answer.add("Do you want to ask me a question? (￣▽￣)");
            greet = true;
        } else if (greet && message.toLowerCase().contains(yes) && hi.equals(lastMessage)) {
            lastMessage = yes;
            answer.add("Excellent! (o˘◡˘o)");
            answer.add("But now come to mind only the capital of Europe " +
                    "and the current time (ಠ_ಠ)");
        } else if (greet && message.toLowerCase().contains(no) && hi.equals(lastMessage)) {
            answer.add("Oh   σ(￣、￣〃)");
            answer.add("Well then I went on to comb my beard, bye, wanderer ....φ(︶▽︶)φ....");
        } else if (greet && message.toLowerCase().contains("netherlands") && yes.equals(lastMessage)) {
            answer.add("The capital of the Netherlands - Amsterdam (・ω・)ﾉ");
        } else if (greet && message.toLowerCase().contains("greece") && yes.equals(lastMessage)) {
            answer.add("The capital of the Greece - Athens (˘⌣˘)");
        } else if (greet && message.toLowerCase().contains("germany") && yes.equals(lastMessage)) {
            answer.add("The capital of the Germany - Berlin (っ˘ω˘ς)");
        } else if (greet && message.toLowerCase().contains("united kingdom") && yes.equals(lastMessage)) {
            answer.add("The capital of the United Kingdom - London (*¯︶¯*)");
        } else if (greet && message.toLowerCase().contains("france") && yes.equals(lastMessage)) {
            answer.add("The capital of the France - Paris (ᵔ.ᵔ)");
        } else if (greet && message.toLowerCase().contains("time") && yes.equals(lastMessage)) {
            answer.add(String.format("The current time %s ( ^▽^)", createDate()));
        } else if (message.toLowerCase().contains(no) && !greet) {
            lastMessage = hi;
            answer.add("Oh, well, then (ಠ_ಠ)");
            answer.add("Greetings, traveler of the internet! ＼(￣▽￣)／");
            answer.add("My name is Omniscient Oracle! <(￣︶￣)>");
            answer.add("Do you want to ask me a question? (￣▽￣)");
            greet = true;
        } else if (message.toLowerCase().contains(yes) && !greet) {
            lastMessage = yes;
            answer.add("Hmmm... (⇀_⇀)");
            answer.add("(↼_↼)");
            answer.add("(⇀_⇀)");
            answer.add("I don't know you.. I can tell you only about the capital of Europe " +
                    "and the current time ノ( ゜-゜ノ)");
            greet = true;
        } else if (!greet) {
            answer.add("(-_-) Zzzz");
            answer.add("(・_-)");
            answer.add("(⊙_⊙)");
            answer.add("I'm sorry, I fell asleep, where are we?");
            answer.add("Have we already said hello? (*^.^*)");
        } else if (message.toLowerCase().contains("bye")) {
            answer.add("Bye, have a good time (￣▽￣)/");
        } else {
            answer.add("(・_・;)");
            answer.add("(•ิ_•ิ)?");
            answer.add("Um, I can not understand what you're saying ( ﾟｏ⌒)");
            answer.add("Maybe you speak French? (￣▽￣)ノ");
            answer.add("*speaks french* <(￣︶￣)>");
        }
        return answer;
    }

    /**
     * Method for start server.
     *
     * @throws IOException e
     */
    public void startServer() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        String message;
        boolean goodbye = false;
        System.out.println("Server build, ready to work");
        do {
            message = in.readLine();
            System.out.println(String.format("Catch message: %s", message));
            for (String answer : getAnswer(message)) {
                out.println(answer);
                System.out.println(String.format("Answer: %s", answer));
                if (answer.toLowerCase().contains("bye")) {
                    goodbye = true;
                }
            }
            out.println();
        } while (!goodbye);
    }

    /**
     * Main method for test app.
     *
     * @param args args
     */
    public static void main(String[] args) {
        try (final Socket socket = new ServerSocket(5354).accept()) {
            ServerSide server = new ServerSide(socket);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
