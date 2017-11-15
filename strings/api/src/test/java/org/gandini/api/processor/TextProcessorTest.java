package org.gandini.api.processor;

import org.gandini.api.domain.TextInputObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextProcessorTest {

    private String basicText = "In the beginning God created the heavens and the earth. Now the earth was " +
            "formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over " +
            "the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was " +
            "good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he " +
            "called \"night.\" And there was evening, and there was morning - the first day.";

    private String tenLines = "churros churros churros churros churros churros churros churros churros churros";

    private String justifyText = "aaaaa aaaa a aa aa aa a aa aaa a";

    @Test
    public void testBasicTextWithoutJustify() {
        int lineLength = 40;
        int expectedNumLines = 13;
        TextInputObject textInputObject = new TextInputObject(basicText, lineLength, false);
        String text = new TextProcessor(textInputObject).processText();
        for (String line : text.split(System.lineSeparator())) {
            Assert.assertTrue(line.length() <= lineLength);
        }
        Assert.assertEquals(text.split(System.lineSeparator()).length, expectedNumLines);
    }

    @Test
    public void testBasicTextJustify() {
        int lineLength = 10;
        int expectedNumLines = 3;
        TextInputObject textInputObject = new TextInputObject(justifyText, lineLength, true);
        String text = new TextProcessor(textInputObject).processText();
        for (String line : text.split(System.lineSeparator())) {
            Assert.assertTrue(line.length() <= lineLength);
        }
        Assert.assertEquals(text.split(System.lineSeparator()).length, expectedNumLines);
    }

    @Test
    public void testTenLinesTextMinimunlineLength() {
        int lineLength = 10;
        int expectedNumLines = 10;
        TextInputObject textInputObject = new TextInputObject(tenLines, lineLength, true);
        String text = new TextProcessor(textInputObject).processText();
        Assert.assertEquals(text.split(System.lineSeparator()).length, expectedNumLines);
    }
}
