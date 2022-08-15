package petStore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PetStoreTests {

    private PetStore petStore;
    private Animal animal;


    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal("Dog", 30, 100.00);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimals_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_GetCount_ShouldReturn0_WhenEmpty_And1WhenSingleAnimalAdded() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_FindAllAnimalsWithMaxKilograms_ShouldReturn_EmptyList_WhenNoSuchAnimals() {
        petStore.addAnimal(animal);
        List<Animal> animalsList = petStore.findAllAnimalsWithMaxKilograms(30 + 10);
        assertTrue(animalsList.isEmpty());
    }


    @Test
    public void test_FindAllAnimalsWithMaxKilograms_ShouldReturn_OnlyThoseHeavier() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("test", 30 - 2, 100.00));
        List<Animal> animalsList = petStore.findAllAnimalsWithMaxKilograms(30 - 1);
        assertEquals(1, animalsList.size());
        assertEquals(animal.getSpecie(), animalsList.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_ShouldThrow() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_AddAnimal_ShouldIncreaseCount() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_getTheMostExpensiveAnimal_ShouldReturnNullWhenEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        assertNull(animal);
    }

    @Test
    public void test_getTheMostExpensiveAnimal_ShouldReturnMostExpensive() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("dog", 30, 100.00 - 20.00));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_FindAllAnimalBySpecie_ShouldReturnEmptyList() {
        List<Animal> animals = petStore.findAllAnimalBySpecie("Dog");
        assertTrue(animals.isEmpty());
    }

    @Test
    public void test_FindAllAnimalBySpecie_ShouldReturnOnlyRequiredSpecie() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Goat", 50, 300));
        List<Animal> animals = petStore.findAllAnimalBySpecie("Dog");
        assertEquals(1, animals.size());
        assertEquals("Dog", animals.get(0).getSpecie());
    }
}

