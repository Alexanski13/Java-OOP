package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private Database database;
    private static final Integer[] NUMBERS = {5, 8, 21, 68, 81, 1, 2};

    @Before
    public void setUp() throws OperationNotSupportedException {
        Integer[] numbers = NUMBERS;
        database = new Database(numbers);
    }

    @Test
    public void testCreateDatabase() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();

        assertEquals(NUMBERS.length, dbElements.length);

//        for (int i = 0; i < numbers.length; i++) {
//            assertEquals(numbers[i], dbElements[i]);
//        }

        assertArrayEquals(NUMBERS, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithLessThan1Argument() throws OperationNotSupportedException {
        new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithLessThan16Argument() throws OperationNotSupportedException {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddWithNullArgumentShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddSuccess() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        database.add(12);
        Integer[] dbElements = database.getElements();
        int lastElementFromDB = dbElements[dbElements.length - 1];

        assertEquals(12, lastElementFromDB);
        assertEquals(initialSize + 1, dbElements.length);
    }

    @Test
    public void testRemoveSuccess() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        database.remove();
        Integer[] currentElements = database.getElements();

        assertEquals(initialSize - 1, currentElements.length);
        assertEquals(NUMBERS[NUMBERS.length - 2], currentElements[currentElements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveEmptyDB() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }

        database.remove();
    }

}