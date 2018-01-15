package ru.apermyakov.references;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for modulate cache activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public abstract class SoftRefCache {

    /**
     * Field for cache.
     */
    private final Map<SoftReference<String>, SoftReference<String>> cache = new HashMap<>();

    /**
     * Field for key.
     */
    private String key;

    /**
     * Method for set value key.
     *
     * @param key key.
     */
    public void setValueKey(String key) {
        this.key = key;
    }

    /**
     * Method for upload value.
     *
     * @return text from file.
     * @throws IOException exception.
     */
    public String uploadValue() throws IOException {
        FileUploader uploader = new FileUploader();
        String result;
        SoftReference<String> keyReference = this.cache.get(new SoftReference<>(this.key));
        if (keyReference == null || keyReference.get() == null) {
            result = uploader.upload(this.key);
            this.cache.put(new SoftReference<>(this.key), new SoftReference<>(result));
        } else {
            result = keyReference.get();
        }
        return result;
    }
}
