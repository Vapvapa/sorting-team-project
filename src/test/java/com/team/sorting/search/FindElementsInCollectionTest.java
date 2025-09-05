package com.team.sorting.search;

import com.team.sorting.input.generator.AnimalGenerator;
import com.team.sorting.input.generator.BarrelGenerator;
import com.team.sorting.input.generator.HumanGenerator;
import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FindElementsInCollection}.
 */
class FindElementsInCollectionTest {

    /**
     * Captures output printed to {@link System#out} during tests.
     */
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    /**
     * Stores the original {@link System#out} to restore after tests.
     */
    private final PrintStream systemOut = System.out;

    /**
     * Generator for creating test {@link Animal} objects.
     */
    private final AnimalGenerator animalGenerator = new AnimalGenerator();

    /**
     * Generator for creating test {@link Barrel} objects.
     */
    private final BarrelGenerator barrelGenerator = new BarrelGenerator();

    /**
     * Generator for creating test {@link Human} objects.
     */
    private final HumanGenerator humanGenerator = new HumanGenerator();

    /**
     * Redirects {@link System#out} to capture printed output before each test.
     */
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    /**
     * Restores {@link System#out} after each test.
     */
    @AfterEach
    void restoreStreams() {
        System.setOut(systemOut);
    }

    /**
     * Tests that the method counts animals correctly when the search value exists.
     */
    @Test
    void testCountsAnimals() {
        List<Animal> animals = animalGenerator.generate(5);
        List<Barrel> barrels = barrelGenerator.generate(0);
        List<Human> humans = humanGenerator.generate(0);

        String search = animals.get(0).getSpecies().toString(); // guaranteed match

        FindElementsInCollection.countersOfElements(
                animals, search,
                barrels, "WOOD",                humans, "Smith"
        );

        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("Animals with " + search), "Should count animals with " + search);
    }

    /**
     * Tests that the method counts barrels correctly when the search value exists.
     */
    @Test
    void testCountsBarrels() {
        List<Animal> animals = animalGenerator.generate(0);
        List<Human> humans = humanGenerator.generate(0);

        List<Barrel> barrels = barrelGenerator.generate(5);
        String search = barrels.get(0).getMaterial().toString(); // guaranteed match

        FindElementsInCollection.countersOfElements(
                animals, "DOG",
                barrels, search,
                humans, "25"
        );

        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("Barrels with " + search), "Should count barrels with " + search);
    }

    /**
     * Tests that the method counts humans correctly when the search value exists.
     */
    @Test
    void testCountsHumans() {
        List<Animal> animals = animalGenerator.generate(0);
        List<Barrel> barrels = barrelGenerator.generate(0);

        List<Human> humans = humanGenerator.generate(5);
        String search = humans.get(0).getGender().toString(); // guaranteed match

        FindElementsInCollection.countersOfElements(
                animals, "CAT",
                barrels, "BEER",
                humans, search
        );

        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("Humans with " + search), "Should count humans with " + search);
    }

    /**
     * Tests that the method works correctly when all collections are empty.
     */
    @Test
    void testCountsEmptyCollections() {
        List<Animal> animals = animalGenerator.generate(0);
        List<Barrel> barrels = barrelGenerator.generate(0);
        List<Human> humans = humanGenerator.generate(0);

        FindElementsInCollection.countersOfElements(
                animals, "DOG",
                barrels, "WOOD",
                humans, "Smith"
        );

        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("Animals with DOG : 0"));
        assertTrue(output.contains("Barrels with WOOD : 0"));
        assertTrue(output.contains("Humans with Smith : 0"));
    }
}
