package p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testSort() {
        int[] numbers = {9, -5, 6, 1, 15, 420};
        Bubble.sort(numbers);
        int[] sortedNumbers = {-5, 1, 6, 9, 15, 420};
        assertArrayEquals(sortedNumbers, numbers);
    }

}