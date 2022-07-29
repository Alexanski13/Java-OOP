package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        Person person1 = new Person(1, "Ivan");
        Person person2 = new Person(2, "Richard");
        database = new Database(person1, person2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameWithNullShouldThrow() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void testFindByUsernameSuccess() throws OperationNotSupportedException {
        Person person = database.findByUsername("Ivan");
        assertEquals("Ivan", person.getUsername());
        assertEquals(1, person.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameWithMissingName() throws OperationNotSupportedException {
        database.findByUsername("Andrei");
    }

    @Test
    public void testFindByIDSuccess() throws OperationNotSupportedException {
        Person person = database.findById(2);
        assertEquals(2, person.getId());
        assertEquals("Richard", person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIDWithMissingID() throws OperationNotSupportedException {
        database.findById(420);
    }

}