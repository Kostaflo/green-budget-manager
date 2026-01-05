package io.github.codeblooded.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.codeblooded.model.GreenBudgetTag;

public class GreenBudgetTagTest {

    private GreenBudgetTag greenBudgetTag;

    @BeforeEach
    void setUp() {
        greenBudgetTag = new GreenBudgetTag(
                1,
                "Πρόγραμμα Περιβάλλοντος",
                "Υπουργείο Περιβάλλοντος",
                100000.0,
                "Θ",
                "Θ",
                "Α",
                "Θ",
                "ΜΠ",
                "Α",
                "Θ"
        );
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, greenBudgetTag.getProgramId());
        assertEquals("Πρόγραμμα Περιβάλλοντος", greenBudgetTag.getProgramName());
        assertEquals("Υπουργείο Περιβάλλοντος", greenBudgetTag.getOwningEntityName());
        assertEquals(100000.0, greenBudgetTag.getPoso());
        assertEquals("Θ", greenBudgetTag.getGreenTag());

        assertEquals("Θ", greenBudgetTag.getTagKlhmatikhMeiwsh());
        assertEquals("Α", greenBudgetTag.getTagKlhmatikhProsarmogh());
        assertEquals("Θ", greenBudgetTag.getTagYdatina());
        assertEquals("ΜΠ", greenBudgetTag.getTagKyklikhOikonomia());
        assertEquals("Α", greenBudgetTag.getTagRypanshElegxos());
        assertEquals("Θ", greenBudgetTag.getTagBiopoikilothtaProstasla());
    }

    @Test
    void testSetPoso() {
        greenBudgetTag.setPoso(150000.0);
        assertEquals(150000.0, greenBudgetTag.getPoso());
    }

    @Test
    void testSetGreenTag() {
        greenBudgetTag.setGreenTag("Α");
        assertEquals("Α", greenBudgetTag.getGreenTag());
    }
}
