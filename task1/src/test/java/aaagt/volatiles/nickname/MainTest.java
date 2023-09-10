package aaagt.volatiles.nickname;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void isPolyndrome() {
        assertTrue(Main.isPolyndrome("ababa"));
        assertTrue(Main.isPolyndrome("abba"));
        assertFalse(Main.isPolyndrome("aabac"));
    }

    @Test
    void isOnlyOneLatter() {
        assertTrue(Main.isOnlyOneLatter("aaaaa"));
        assertFalse(Main.isOnlyOneLatter("aaaac"));
    }

    @Test
    void isAlphabetOrder() {
        assertTrue(Main.isAlphabetOrder("aabcc"));
        assertFalse(Main.isAlphabetOrder("aaccb"));
    }
}
