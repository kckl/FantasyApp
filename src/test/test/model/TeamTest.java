package test.model;

import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TeamTest {
    private Team myTeam;
    Player curry = new Player("Steph Curry", myTeam);
    Player luka = new Player("Luka Doncic", myTeam);

    @BeforeEach
    public void setUp() {
        myTeam = new Team("My team");

    }

    @Test
    public void testConstructor() {
        assertEquals("My team", myTeam.getTeamName());
    }

    @Test
    public void testGetPlayer() {
        myTeam.addPlayer(curry);
        myTeam.addPlayer(luka);
        assertEquals(curry, myTeam.getPlayer(0));
    }

    @Test
    public void testGetPlayerNames() {
        myTeam.addPlayer(curry);
        myTeam.addPlayer(luka);
        assertEquals(2, myTeam.getPlayerNames().size());
    }

    @Test
    public void testChangeTeamName() {
        assertEquals("My team", myTeam.getTeamName());
        myTeam.changeTeamName("The best team");
        assertEquals("The best team", myTeam.getTeamName());
    }

    @Test
    public void testAddPlayerAlreadyInTeam() {
        assertTrue(myTeam.addPlayer(curry));
        assertFalse(myTeam.addPlayer(curry));
        assertEquals(1, myTeam.getPlayerNames().size());
    }

    @Test
    public void testAddPlayerTeamNotFull() {
        assertFalse(myTeam.isFull());
        assertTrue(myTeam.addPlayer(curry));

    }

    @Test
    public void testAddPlayerTeamFull() {
        Player p1 = new Player("Steph Curry", myTeam);
        Player p2 = new Player("Steph Curry", myTeam);
        Player p3 = new Player("Steph Curry", myTeam);
        Player p4 = new Player("Steph Curry", myTeam);
        Player p5 = new Player("Steph Curry", myTeam);
        Player p6 = new Player("Steph Curry", myTeam);
        Player p7 = new Player("Steph Curry", myTeam);
        Player p8 = new Player("Steph Curry", myTeam);
        Player p9 = new Player("Steph Curry", myTeam);
        Player p10 = new Player("Steph Curry", myTeam);
        Player p11 = new Player("Steph Curry", myTeam);
        Player p12 = new Player("Steph Curry", myTeam);
        Player p13 = new Player("Steph Curry", myTeam);
        Player p14 = new Player("Steph Curry", myTeam);
        myTeam.addPlayer(p1);
        myTeam.addPlayer(p2);
        myTeam.addPlayer(p3);
        myTeam.addPlayer(p4);
        myTeam.addPlayer(p5);
        myTeam.addPlayer(p6);
        myTeam.addPlayer(p7);
        myTeam.addPlayer(p8);
        myTeam.addPlayer(p9);
        myTeam.addPlayer(p10);
        myTeam.addPlayer(p11);
        myTeam.addPlayer(p12);
        myTeam.addPlayer(p13);
        assertTrue(myTeam.isFull());
        assertFalse(myTeam.addPlayer(p14));

    }

}
