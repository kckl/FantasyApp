package test;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private Player stephCurry;
    private Team myTeam;

    @BeforeEach
    public void setUp() {
        myTeam = new Team("My Team");
        stephCurry = new Player("Steph Curry", myTeam);
    }

    @Test
    public void testConstructor() {
        assertEquals(stephCurry.getName(), "Steph Curry");
        assertEquals(stephCurry.getFantasyTeam(), myTeam);
    }

    @Test
    public void testAddStats() {
        stephCurry.setFieldGoalPct(48.2);
        stephCurry.setFreeThrowPct(91.6);
        stephCurry.setAssists(5.8);
        stephCurry.setPoints(32.0);
        stephCurry.setRebounds(5.5);
        assertEquals(stephCurry.getAssists(), 5.8);
        assertEquals(stephCurry.getPoints(), 32.0);
        assertEquals(stephCurry.getFieldGoalPct(), 48.2);

    }

}
