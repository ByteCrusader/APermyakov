package ru.apermyakov.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for build parallel search text in files.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.11.2017
 */
@ThreadSafe
public class ParallelSearch {

    /**
     * Field for list of files with correct extension.
     */
    @GuardedBy("this")
    private List<String> extsFiles = new LinkedList<>();

    /**
     * Field for target root.
     */
    @GuardedBy("this")
    private String root;

    /**
     * Field for target text.
     */
    @GuardedBy("this")
    private String text;

    /**
     * Field for target file's extensions.
     */
    @GuardedBy("this")
    private List<String> exts;

    /**
     * Design parallel search.
     *
     * @param root root
     * @param text text
     * @param exts extensions
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Private class for override File Visitor.
     *
     * @author apermyakov
     * @version 1.0
     * @since 20.11.2017
     */
    private class OunFileVisitor extends SimpleFileVisitor<Path> {

        /**
         * Method for override file visit method.
         *
         * @param path target path
         * @param attributes attributes
         * @return File Visitor continue
         * @throws IOException file not found
         */
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {

            for (String ext : exts) {
                if (path.toString().endsWith(ext)) {
                    extsFiles.add(path.toString());
                }
            }

            return FileVisitResult.CONTINUE;
        }
    }

    /**
     * Method for scan list of files by multi threads.
     *
     * @param result list of result
     */
    private void scan(final List<String> result) {
        for (String path : extsFiles) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (result) {
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(path));

                            String string;
                            while ((string = br.readLine()) != null) {
                                if (string.contains(text)) {
                                    result.add(path);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(extsFiles.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for search files with target extensions.
     *
     * @throws IllegalArgumentException illegal argument
     */
    private void search() throws IllegalArgumentException {
        try {
            Files.walkFileTree(Paths.get(this.root), new OunFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for build threads for search and scan.
     *
     * @return list of results
     */
    public synchronized List<String> result() {

        final List<String> result = new LinkedList<>();

        Thread search = new Thread(new Runnable() {
            @Override
            public void run() {
                search();
            }
        });

        Thread scan = new Thread(new Runnable() {
            @Override
            public void run() {
                scan(result);
            }
        });

        search.start();
        try {
            search.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scan.start();
        try {
            scan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        List<String> exts = new LinkedList<>();
        exts.add(".txt");
        exts.add(".docx");
        ParallelSearch searcher = new ParallelSearch("C:\\Projects", "I will test your program.", exts);
        System.out.println(searcher.result());
    }
}
