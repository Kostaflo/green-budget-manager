package io.github.codeblooded.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.github.codeblooded.model.GreenBudgetTag;

class GreenBudgetTagTest {

    @Test
    void testProgramFieldsGettersAndSetters() {
        GreenBudgetTag tag = new GreenBudgetTag();

        tag.setProgramId(123);
        tag.setProgramName("Test Program");
        tag.setPoso(1000.50);
        tag.setPososto(25.5);
        tag.setMinistryId(10);
        tag.setRegionId(5);

        assertEquals(123, tag.getProgramId());
        assertEquals("Test Program", tag.getProgramName());
        assertEquals(1000.50, tag.getPoso());
        assertEquals(25.5, tag.getPososto());
        assertEquals(10, tag.getMinistryId());
        assertEquals(Integer.valueOf(5), tag.getRegionId());
    }

    @Test
    void testGreenAndSubTagsGettersAndSetters() {
        GreenBudgetTag tag = new GreenBudgetTag();

        tag.setGreenTag("GREEN");
        tag.setTagKlhmatikhMeivsh("Μείωση");
        tag.setTagKlhmatikhProsarmofh("Προσαρμογή");
        tag.setTagYdatina("Υδάτινα");
        tag.setTagKyklikhOikonomia("Κυκλική");
        tag.setTagRypanshElegxos("Ρύπανση");
        tag.setTagBiopoikilothtaProstasla("Βιοποικιλότητα");

        assertEquals("GREEN", tag.getGreenTag());
        assertEquals("Μείωση", tag.getTagKlhmatikhMeivsh());
        assertEquals("Προσαρμογή", tag.getTagKlhmatikhProsarmofh());
        assertEquals("Υδάτινα", tag.getTagYdatina());
        assertEquals("Κυκλική", tag.getTagKyklikhOikonomia());
        assertEquals("Ρύπανση", tag.getTagRypanshElegxos());
        assertEquals("Βιοποικιλότητα", tag.getTagBiopoikilothtaProstasla());
    }

    @Test
    void testToStringContainsKeyFields() {
        GreenBudgetTag tag = new GreenBudgetTag();
        tag.setProgramId(1);
        tag.setProgramName("Program A");
        tag.setPoso(200.0);
        tag.setGreenTag("GREEN");

        String result = tag.toString();

        assertTrue(result.contains("programId=1"));
        assertTrue(result.contains("Program A"));
        assertTrue(result.contains("poso=200.0"));
        assertTrue(result.contains("greenTag='GREEN'"));
    }
}
