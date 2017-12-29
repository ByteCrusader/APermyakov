package ru.apermyakov.testtask;

import java.io.File;
import java.io.FileFilter;

/**
 * Class for modulate filter by file's mask.
 *
 * @author apermyakov
 * @version 1.0
 * @since 29.12.2017
 */
public class FilterByMask implements FileFilter {

    /**
     * Field for base filter.
     */
    private final BaseFilter filter;

    /**
     * Design filter by mask.
     *
     * @param filter base filter object
     */
    public FilterByMask(BaseFilter filter) {
        this.filter = filter;
    }

    /**
     * Method for compare file name and condition (mask).
     *
     * @param pathname file
     * @return compare or not
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || filter.getNameByFile(pathname).endsWith(this.filter.getCheckedCond().replace("*", ""));
    }
}
