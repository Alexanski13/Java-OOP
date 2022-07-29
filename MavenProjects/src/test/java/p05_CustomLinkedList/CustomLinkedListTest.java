package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    CustomLinkedList<String> customLinkedList;

    @Before
    public void setUp() {
        customLinkedList = new CustomLinkedList<>();
    }

    @Test
    public void testAddSuccess() {
        customLinkedList.add("Pesho");
        assertEquals("Pesho", customLinkedList.get(0));
    }

    @Test
    public void testAddToNonEmptyListSuccess() {
        customLinkedList.add("Pesho");
        customLinkedList.add("Bogomil");
        assertEquals("Bogomil", customLinkedList.get(1));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowForBigIndex() {
        customLinkedList.get(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowForNegativeIndex() {
        customLinkedList.get(-100);
    }
}