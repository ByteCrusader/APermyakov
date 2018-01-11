package ru.apermyakov.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple generator.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 11.01.2018.
 */
public class TemplateTest {

    /**
     * Test when text with different param then replace param to keys.
     */
    @Test
    public void whenTextWithDifferentParamThenReplaceParamToKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        Template generator = new SimpleGenerator();

        String result = generator.generate(template, keys);

        assertThat(result, is("I am a Petr, Who are you?"));
    }

    /**
     * Test when text with similar param then replace param to keys.
     */
    @Test
    public void whenTextWithSimilarParamThenReplaceParamToKeys() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = new HashMap<>();
        keys.put("sos", "Aaa");
        Template generator = new SimpleGenerator();

        String result = generator.generate(template, keys);

        assertThat(result, is("Help, Aaa, Aaa, Aaa"));
    }

    /**
     * Test when add extra key then take exception.
     */
    @Test(expected = TemplateException.class)
    public void whenAddExtraKeyThenTakeException() {
        String template = "Simple ${word}";
        Map<String, String> keys = new HashMap<>();
        keys.put("word", "example");
        keys.put("tyt", "Tam");
        Template generator = new SimpleGenerator();

        generator.generate(template, keys);
    }

    /**
     * Test when empty key then take exception.
     */
    @Test(expected = TemplateException.class)
    public void whenEmptyKeyThenTakeException() {
        Map<String, String> keys = new HashMap<>();
        Template generator = new SimpleGenerator();

        generator.generate("", keys);
    }
}