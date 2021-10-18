package test;

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
        assertEquals("Steph Curry", stephCurry.getName());
        assertEquals(myTeam, stephCurry.getFantasyTeam());
    }

    @Test
    public void testAddStats() {
        stephCurry.setFieldGoalPct(48.2);
        stephCurry.setFreeThrowPct(91.6);
        stephCurry.setAssists(5.8);
        stephCurry.setPoints(32.0);
        stephCurry.setRebounds(5.5);
        assertEquals(5.8, stephCurry.getAssists());
        assertEquals(32.0, stephCurry.getPoints());
        assertEquals(48.2, stephCurry.getFieldGoalPct());
        assertEquals(91.6, stephCurry.getFreeThrowPct());
        assertEquals(5.5, stephCurry.getRebounds());

    }

}
