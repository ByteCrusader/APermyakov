package ru.apermyakov.testtask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate
 */
public class TestTask implements TestTaskInterface {

    /**
     * Field for contain directory.
     */
    private String directory;

    /**
     * Field for list of results.
     */
    private List<String> results = new ArrayList<>();

    /**
     * Field for base filter.
     */
    private BaseFilter filter = new BaseFilter();

    /**
     * Method for initial search action.
     *
     * @param inputMessage input message
     * @throws IOException e
     */
    @Override
    public void find(String[] inputMessage) throws IOException {
        for (int index = 0; index < inputMessage.length; index++) {
            keyValidate(inputMessage[index], index + 1 < inputMessage.length ? inputMessage[index + 1] : "");
        }
    }

    /**
     * Method for save target directory.
     *
     * @param directory directory
     */
    @Override
    public void goToDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Method for save search data to filter.
     *
     * @param searchData search data
     */
    @Override
    public void collectSearchData(String searchData) {
        this.filter.setCheckedCond(searchData);
    }

    /**
     * Method for add file to result list.
     *
     * @param resultFile found file
     */
    private void addFileToResults(File resultFile) {
        this.results.add(resultFile.getAbsolutePath());
    }

    /**
     * Method for search files by filters inside directory and subdirectory.
     *
     * @param directory directory
     * @param filter filter
     */
    private void searchFilesByFilters(File directory, FileFilter filter) {
        File[] suitableFiles = directory.listFiles(filter);
        if (suitableFiles != null) {
            for (File file : suitableFiles) {
                if (file.isDirectory()) {
                    this.searchFilesByFilters(file, filter);
                } else {
                    this.addFileToResults(file);
                }
            }
        }
    }

    /**
     * Method for initial search by file's mask.
     */
    @Override
    public void searchByMask() {
        this.searchFilesByFilters(new File(this.directory), new FilterByMask(this.filter));
    }

    /**
     * Method for initial search by file's name.
     */
    @Override
    public void searchByName() {
        this.searchFilesByFilters(new File(this.directory), new FilterByName(this.filter));
    }

    /**
     * Method for initial search by regular expression.
     */
    @Override
    public void searchByRegx() {
        this.searchFilesByFilters(new File(this.directory), new FilterByRegx(this.filter));
    }

    /**
     * Method for write results to file.
     *
     * @param targetFile target file
     * @throws IOException e
     */
    @Override
    public void whiteToFile(String targetFile) throws IOException {
        try (RandomAccessFile fileStream = new RandomAccessFile(targetFile, "rw");
             PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile))
        ) {
            writer.write("");
            for (String foundFileName : this.results) {
                fileStream.writeBytes(String.format("%s%s", foundFileName, System.getProperty("line.separator")));
            }
        }
    }

    /**
     * Method for show help to user.
     *
     * @throws IOException e
     */
    @Override
    public void showHelp() throws IOException {
        File helpFile = new File(this.getClass().getClassLoader().getResource("Help.txt").getFile());
        try (RandomAccessFile helpFileStream = new RandomAccessFile(helpFile, "r")) {
            String fileLine;
            while ((fileLine = helpFileStream.readLine()) != null) {
                System.out.println(fileLine);
            }
        }
    }

    /**
     * Method for choose search action by key.
     *
     * @param key key
     * @throws IOException e
     */
    private void searchSwitcher(String key) throws IOException {
        if (key.contains("-m")) {
            this.searchByMask();
        } else if (key.contains("-f")) {
            this.searchByName();
        } else if (key.contains("-r")) {
            this.searchByRegx();
        } else {
            this.showHelp();
        }
    }

    /**
     * Method for key validate.
     *
     * @param key key
     * @param argument argument
     * @throws IOException e
     */
    @Override
    public void keyValidate(String key, String argument) throws IOException {
        if (key.contains("-")) {
            if (key.contains("-d")) {
                this.goToDirectory(argument);
            } else if (key.contains("-n")) {
                this.collectSearchData(argument);
            } else if (key.contains("-o")) {
                this.whiteToFile(argument);
            } else {
                searchSwitcher(key);
            }
        }
    }

    /**
     * Main method to work with jar.
     *
     * @param args args
     * @throws IOException e
     */
    public static void main(String[] args) throws IOException {
        TestTask task = new TestTask();
        task.find(args);
    }
}
