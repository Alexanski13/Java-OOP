package farmville;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private Animal chicken;
    private Animal tiger;
    private Farm farm;


    @Before
    public void setUp() {
        chicken = new Animal("Chicken", 10);
        tiger = new Animal("Tiger", 15);
        farm = new Farm("SoftUni", 15);
    }


    @Test
    public void test_ShouldCreateFarmSuccess() {
        Farm farm = new Farm("SoftUni", 15);
        assertEquals("SoftUni", farm.getName());
        assertEquals(15, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateShouldThrowBecauseNameIsWhitespace() {
        new Farm("   ", 15);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateShouldThrowBecauseNameIsEmpty() {
        new Farm("", 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateShouldThrowBecauseCapacityIsNegative() {
        new Farm("SoftUni", -15);
    }

    @Test
    public void test_AddShouldAddSuccess() {
        farm.add(tiger);
        assertEquals(1, farm.getCount());

        farm.add(chicken);
        assertEquals(2, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddShouldThrowBecauseOfNoCapacity() {
        Farm farm = new Farm("SoftUni", 1);
        farm.add(tiger);
        farm.add(chicken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddShouldThrowBecauseOfDuplicateAnimal() {
        farm.add(chicken);
        farm.add(chicken);
    }

    @Test
    public void test_RemoveShouldRemoveSuccess() {
        farm.add(chicken);
        assertTrue(farm.remove(chicken.getType()));
        assertEquals(0, farm.getCount());
    }

    @Test
    public void test_RemoveShouldNotRemove() {
        farm.add(chicken);
        assertFalse(farm.remove(tiger.getType()));
        assertEquals(1, farm.getCount());
    }
}
