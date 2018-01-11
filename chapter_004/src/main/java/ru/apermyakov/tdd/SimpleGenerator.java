package ru.apermyakov.tdd;

import java.util.Map;

/**
 * Class for modulate
 */
public class SimpleGenerator implements Template {

    /**
     * Method for build key by values' key.
     *
     * @param valueKey value's key.
     * @return result key.
     */
    private String buildKey(String valueKey) {
        return String.format("${%s}", valueKey);
    }

    /**
     * Method for build string with replace key to value.
     *
     * @param key key.
     * @param value value.
     * @param template template.
     * @return result string.
     */
    private String replaceKeyToValue(String key, String value, String template) {
        return template.replaceAll(String.format("\\Q%s\\E", key), value);
    }

    /**
     * Method for generate string using template and meanings.
     *
     * @param template template.
     * @param meanings meanings.
     * @return result string.
     */
    @Override
    public String generate(String template, Map<String, String> meanings) {
        if (!meanings.isEmpty()) {
            for (Map.Entry<String, String> value : meanings.entrySet()) {
                String key = buildKey(value.getKey());
                if (template.contains(key)) {
                    template = replaceKeyToValue(key, value.getValue(), template);
                } else {
                    throw new TemplateException("Extra key");
                }
            }
        } else {
            throw new TemplateException("Empty meanings");
        }
        return template;
    }
}
