package ru.apermyakov.testtask;

import java.io.File;
import java.io.FileFilter;

/**
 * Class for modulate base filter of files.
 *
 * @author apermyakov
 * @version 1.0
 * @since 29.12.2017
 */
public class BaseFilter implements FileFilter {

    /**
     * Field for checked condition.
     */
    private String checkedCond;

    /**
     * Method for set checked condition.
     *
     * @param checkedCond condition
     */
    public void setCheckedCond(String checkedCond) {
        this.checkedCond = checkedCond;
    }

    /**
     * Method for get checked condition.
     *
     * @return condition
     */
    public String getCheckedCond() {
        return checkedCond;
    }

    /**
     * Method for get name by file's path.
     *
     * @param file file
     * @return file name
     */
    public String getNameByFile(File file) {
        String filePath = file.getPath();
        String fileName = "";
        int lastIndexOfSep = filePath.lastIndexOf('\\');
        if (lastIndexOfSep > 0 && lastIndexOfSep < filePath.length() - 1) {
            fileName = filePath.substring(lastIndexOfSep + 1).toLowerCase();
        }
        return fileName;
    }

    /**
     * Method for compare file name and condition.
     *
     * @param pathname file
     * @return compare or not
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || this.getNameByFile(pathname).equals(checkedCond);
    }
}
