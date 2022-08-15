package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class FootballTeamTests {

    private Footballer footballer;
    private Footballer footballer1;
    private FootballTeam footballTeam;
    private List<Footballer> footballers;

    @Before
    public void setUp() {
        footballTeam = new FootballTeam("Peperudite", 10);
        this.footballer = new Footballer("Georgio");
        this.footballer1 = new Footballer("Sando");
        footballers = new ArrayList<>();
    }

    @Test
    public void test_GetVacantPositionsShouldReturnSpots() {
        assertEquals(footballTeam.getVacantPositions(), 10);
        footballTeam = new FootballTeam("Peperudite", 9);
        Assert.assertEquals(footballTeam.getVacantPositions(), 9);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameShouldThrowForEmptyName() {
        FootballTeam footballTeam = new FootballTeam("", 10);
    }

    @Test()
    public void test_SetNameShouldReturnName() {
        Assert.assertEquals("Peperudite", footballTeam.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetVacantShouldThrowForLessThan0Spots() {
        FootballTeam footballTeam = new FootballTeam("", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFootballerShouldThrowForLessThan0Spots() {
        footballTeam = new FootballTeam("Peperudite", 0);
        footballTeam.addFootballer(footballer);
    }

    @Test
    public void test_AddFootballerShouldAddSuccess() {
        Assert.assertEquals(footballTeam.getCount(), 0);
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(footballTeam.getCount(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveFootballerShouldThrowNameNull() {
        footballTeam.removeFootballer("");
    }

    @Test
    public void test_RemoveFootballerShouldRemoveSuccess() {
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(footballTeam.getCount(), 1);
        footballTeam.removeFootballer("Georgio");
        Assert.assertEquals(footballTeam.getCount(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FootballerForSaleShouldThrowForNameNull() {
        footballTeam.footballerForSale("");
    }

    @Test
    public void test_FootballerForSaleShouldSetActiveToFalse() {
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        Assert.assertTrue(footballer1.isActive());
        footballTeam.footballerForSale("Sando");
        Assert.assertFalse(footballer1.isActive());
    }

    @Test
    public void test_GetStatisticsShouldReturnNames() {
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballers.add(footballer);
        footballers.add(footballer1);
        String names = this.footballers.stream().map(Footballer::getName).collect(Collectors.joining(", "));
        String actualOutput = String.format("The footballer %s is in the team %s.", names, this.footballTeam.getName());
        Assert.assertEquals(actualOutput, footballTeam.getStatistics());
    }
}
