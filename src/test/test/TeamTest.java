package test;

import model.League;
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
       assertEquals(myTeam.getTeamName(), "My team");
    }

    @Test
    public void testGetPlayer() {
        myTeam.addPlayer(curry);
        myTeam.addPlayer(luka);
        assertEquals(myTeam.getPlayer(0), curry);
    }

    @Test
    public void testGetPlayerNames() {
        myTeam.addPlayer(curry);
        myTeam.addPlayer(luka);
        assertEquals(myTeam.getPlayerNames().size(), 2);
    }

    @Test
    public void testChangeTeamName() {
        assertEquals(myTeam.getTeamName(), "My team");
        myTeam.changeTeamName("The best team");
        assertEquals(myTeam.getTeamName(), "The best team");
    }

    @Test
    public void testAddPlayerTeamNotFull() {
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
        assertFalse(myTeam.addPlayer(p14));
    }

    @Test
    public void testAddPlayerAlreadyInTeam() {
        myTeam.addPlayer(curry);
        assertFalse(myTeam.addPlayer(curry));
    }

}
