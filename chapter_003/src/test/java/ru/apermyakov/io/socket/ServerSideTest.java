package ru.apermyakov.io.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test server to chat bot.
 *
 * @author apermyakov
 * @version 1.0
 * @version 26.12.2017
 */
public class ServerSideTest {

    /**
     * Field for system separator.
     */
    private static final String SEP = System.getProperty("line.separator");

    /**
     * Method for generic test.
     *
     * @param input input
     * @param output output
     * @throws IOException e
     */
    private void genericTest(String input, String output) throws IOException {
        try (Socket socket = mock(Socket.class);
             ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            ServerSide server = new ServerSide(socket);
            server.startServer();
            assertThat(out.toString().contains(output), is(true));
        }
    }

    /**
     * Test when talk with greet and bye questions.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithGreetAndBye() throws IOException {
        this.genericTest(String.format("hello %s bye", SEP), "Bye, have a good time (￣▽￣)/\r\n\r\n");
    }

    /**
     * Test when talk with greet and no questions.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithGreetAndNoQuestions() throws IOException {
        this.genericTest(String.format("hello %s no", SEP), "....φ(︶▽︶)φ....\r\n\r\n");
    }

    /**
     * Test when talk with greet, yes and bye.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithGreetYesAndBye() throws IOException {
        this.genericTest(String.format("hello %s yes %s bye", SEP, SEP), "Excellent! (o˘◡˘o)");
    }

    /**
     * Test when talk with greet, yes, question about UK and bye.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithGreetYesUKQuestionAndBye() throws IOException {
        this.genericTest(String.format("hello %s yes %s united kingdom %s bye", SEP, SEP, SEP), "London (*¯︶¯*)");
    }

    /**
     * Test when talk with greet, blablabla and bye.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithGreetBlaBlaAndBye() throws IOException {
        this.genericTest(String.format("hello %s blabla %s bye", SEP, SEP), "*speaks french* <(￣︶￣)>");
    }

    /**
     * Test when talk without greet, no and no.
     *
     * @throws IOException e
     */
    @Test
    public void whenTalkWithoutGreetNoAndNo() throws IOException {
        this.genericTest(String.format("ey %s no %s no", SEP, SEP), "(-_-) Zzzz");
    }
}