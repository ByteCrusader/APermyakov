package ru.apermyakov.io.socket.manager;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test file manager.
 *
 * @author apermyakov
 * @version 1.0
 * @since 27.12.2017
 */
public class ServerTest {

    /**
     * Method for modulate generic test.
     *
     * @param input input string
     * @param output output string
     * @throws IOException e
     */
    private void genericTest(String input, String output) throws IOException {
        try (Socket socket = mock(Socket.class);
             ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Server server = new Server(socket);
            server.initialServer();
            server.startServer();
            assertThat(out.toString().contains(output), is(true));
        }
    }

    /**
     * Test when exit then take separator.
     *
     * @throws IOException e
     */
    @Test
    public void whenExitThenTakeSeparator() throws IOException {
        this.genericTest("exit", "\r\n");
    }

    /**
     * Test when get then take list of catalog.
     *
     * @throws IOException e
     */
    @Test
    public void whenGetThenTakeListOfCatalog() throws IOException {
        this.genericTest("get\r\nexit", ".sqlite");
    }

    /**
     * Test when go to up catalog then take answer.
     *
     * @throws IOException e
     */
    @Test
    public void whenGoToUpCatalogThenTakeAnswer() throws IOException {
        this.genericTest("up chapter_009\r\nexit", "chapter_009");
    }

    /**
     * Test when go to up catalog and get catalog then take catalog.
     *
     * @throws IOException e
     */
    @Test
    public void whenGoToUpCatalogAndGetCatalogThenTakeCatalog() throws IOException {
        this.genericTest("up chapter_009\r\nget\r\nexit", "chapter_009.iml");
    }

    /**
     * Test when go down then take catalog.
     *
     * @throws IOException e
     */
    @Test
    public void whenGoDownThenTakeCatalog() throws IOException {
        this.genericTest("down\r\nexit", "Correct down to C:\\");
    }

    /**
     * test when go down and get then take catalog with root catalog.
     *
     * @throws IOException e
     */
    @Test
    public void whenGoDownAndGetThenTakeCatalogWithRootCatalog() throws IOException {
        this.genericTest("down\r\nget\r\nexit", "C:\\check");
    }

    /**
     * Test when upload then take correct answer.
     *
     * @throws IOException e
     */
    @Test
    public void whenUploadThenTakeCorrectAnswer() throws IOException {
        this.genericTest("upload hi.txt\r\nexit", "correct");
    }

    /**
     * Test when download then take correct answer.
     *
     * @throws IOException e
     */
    @Test
    public void whenDownloadThenTakeCorrectAnswer() throws IOException {
        this.genericTest("download hi.txt\r\nexit", "correct");
    }

    /**
     * Test when upload some file then save this file and response correct.
     *
     * @throws IOException e
     */
    @Test
    public void whenUploadSomeFileThenSaveThisFileAndResponseCorrect() throws IOException {
        try (Socket socket = mock(Socket.class);
             FileInputStream fileInputStream = new FileInputStream("C:\\client\\test.txt")) {
            when(socket.getInputStream()).thenReturn(fileInputStream);
            Server server = new Server(socket);
            server.initialServer();
            assertThat(server.uploadFile(new File("C:\\check\\test.txt")).contains("correct"), is(true));
        }
    }

    /**
     * Test when download some file that exist then response correct.
     *
     * @throws IOException e
     */
    @Test
    public void whenDownloadSomeFileThatExistThenResponseCorrect() throws IOException {
        try (Socket socket = mock(Socket.class);
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\client\\second.txt")) {
            when(socket.getOutputStream()).thenReturn(fileOutputStream);
            Server server = new Server(socket);
            server.initialServer();
            assertThat(server.downloadFile(new File("C:\\check\\first.txt")).contains("correct"), is(true));
        }
    }
}