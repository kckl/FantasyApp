package ui;

import model.League;
import model.Player;
import model.Team;

import java.util.List;
import java.util.Scanner;

public class FantasyApp {
    private static final String REGISTER_COMMAND = "register";
    private static final String DRAFT_COMMAND = "draft";
    private static final String VIEW_COMMAND = "view";
    private static final String SETTINGS_COMMAND = "settings";
    private static final String QUIT_APP_COMMAND = "quit";

    private League league;
    private boolean continueApp;
    private Scanner input;
    private Team team;

    // EFFECTS: runs the fantasy sports application
    public FantasyApp() {
        runFantasyApp();
    }

    // This method references code from this repo
    // Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp.git]

    // MODIFIES: this
    // EFFECTS: parses user input
    private void runFantasyApp() {
        continueApp = true;
        String command = "";

        initialize();

        while (continueApp) {
            printHomeScreen();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                closeApplication();
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nHave fun!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a league
    private void initialize() {
        league = new League("NBA2k21League", 12);
        input = new Scanner(System.in);
    }

    //EFFECTS: prints start up instructions to launch application
    private void printHomeScreen() {
        System.out.println("Enter '" + REGISTER_COMMAND + "' to register a new team.");
        System.out.println("Enter '" + DRAFT_COMMAND + "' to add players to an existing team.");
        System.out.println("Enter '" + VIEW_COMMAND + "' to view all teams.");
        System.out.println("Enter '" + SETTINGS_COMMAND + "' to for league settings.");
        System.out.println("Enter '" + QUIT_APP_COMMAND + "' to quit the application.");

    }

    // REQUIRES: string is not empty
    // EFFECTS: processes user command and prints information depending on the input

    private void processCommand(String command) {
        switch (command) {
            case REGISTER_COMMAND:
                registerTeamOption();
                break;
            case DRAFT_COMMAND:
                selectTeamMenu();
                break;
            case VIEW_COMMAND:
                viewLeague();
                break;
            case SETTINGS_COMMAND:
                printLeagueSettings(league);
                break;
            case QUIT_APP_COMMAND:
                closeApplication();
                break;
            default:
                System.out.println("\nThe command is not valid. Please try again.");
                break;
        }
    }

    // REQUIRES: input to be a non-empty string
    // MODIFIES: this
    // EFFECTS: adds a new team to the league unless league is full
    private void registerTeamOption() {
        System.out.println("Please input a team name. (No spaces)");
        String teamName = "";
        teamName = input.next();

        Team newTeam = new Team(teamName);

        if (league.registerTeam(newTeam)) {
            System.out.println(newTeam.getTeamName()
                    + " has been successfully registered to " + league.getLeagueName() + ".");
        } else {
            System.out.println(newTeam.getTeamName() + " cannot be registered because the league is full.");
        }

        registerAnotherTeamOption();
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to add more teams to their league
    private void registerAnotherTeamOption() {
        System.out.println("\nWould you like to add another team? (Y/N)");
        String command;
        input = new Scanner(System.in);
        command = input.next();
        command = command.toLowerCase();

        switch (command) {
            case "y":
                registerTeamOption();
                break;
            case "n":
                System.out.println("\nReturning to home screen.\n");
                break;
            default:
                System.out.println("\nThe command is not valid. Please try again.");
                break;
        }
    }

    // REQUIRES: input to be at an index with team
    // EFFECTS: selects a team from the league
    private void selectTeamMenu() {
        System.out.println("\nWhich team would you like to select? (input index)");
        System.out.println("Registered Teams: " + league.getTeamNames());

        int teamPositionInList;
        Team selectedTeam;
        input = new Scanner(System.in);

        teamPositionInList = input.nextInt();
        selectedTeam = league.getTeam(teamPositionInList);
        System.out.println("Selected team: " + selectedTeam.getTeamName());
        addPlayerOption(selectedTeam);
    }

    // EFFECTS: add players to a team
    private void addPlayerOption(Team selectedTeam) {
        String player = null;
        input = new Scanner(System.in);

        System.out.println("What is the name of the player you would like to add to "
                + selectedTeam.getTeamName() + "? (Eg. S.Curry)");
        player = input.next();

        Player newPlayer = new Player(player, selectedTeam);
        selectedTeam.addPlayer(newPlayer);
        System.out.println(player + " has been added to " + selectedTeam.getTeamName() + ".");

        addPlayerStatsOption(newPlayer);

        addAnotherPlayerOption();
    }

    // MODIFIES: this
    // EFFECTS: ask user to input player's stats for each category
    private void addPlayerStatsOption(Player p) {
        input = new Scanner(System.in);

        System.out.println("\nPlease input the following stats for " + p.getName() + ":");

        System.out.println("Field goal Percentage?");
        double fieldGoalPct = input.nextDouble();

        System.out.println("Free throw percentage?");
        double freeThrowPct = input.nextDouble();

        System.out.println("Total points?");
        double points = input.nextDouble();

        System.out.println("Total rebounds?");
        double rebounds = input.nextDouble();

        System.out.println("Total assists?");
        double assists = input.nextDouble();

        p.setFieldGoalPct(fieldGoalPct);
        p.setFreeThrowPct(freeThrowPct);
        p.setPoints(points);
        p.setRebounds(rebounds);
        p.setAssists(assists);


        System.out.println("Stats for " + p.getName() + " has been successfully added:");
        System.out.println("FG% - " + p.getFieldGoalPct());
        System.out.println("FT% - " + p.getFreeThrowPct());
        System.out.println("PTS - " + p.getPoints());
        System.out.println("REB - " + p.getAssists());
        System.out.println("AST - " + p.getRebounds());
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to add more players to their team
    private void addAnotherPlayerOption() {
        System.out.println("\nWould you like to add another player? (Y/N)");
        String command;
        input = new Scanner(System.in);
        command = input.next();
        command = command.toLowerCase();

        switch (command) {
            case "y":
                selectTeamMenu();
                break;
            case "n":
                System.out.println("\nReturning to home screen.\n");
                break;
            default:
                System.out.println("\nThe command is not valid. Please try again.");
                break;
        }
    }

    // todo: complete this
    // EFFECTS: returns a list of team names for all the teams registered in league
    private List<String> viewLeague() {
        System.out.println("Here are all the registered teams:");
        return league.getTeamNames();
    }

//    // EFFECTS: selects a player from a team
//    public void selectPlayerMenu() {
//        System.out.println("Which player would you like to select? (input index)");
//        System.out.println("Roster: " + team.getPlayerNames());
//
//        int playerPositionInList;
//        Player selectedPlayer;
//        input = new Scanner(System.in);
//
//        playerPositionInList = input.nextInt();
//        selectedPlayer = team.getPlayer(playerPositionInList);
//        System.out.println("Selected player: " + selectedPlayer.getName());
//    }

    // EFFECTS: prints existing league settings
    private void printLeagueSettings(League league) {
        System.out.println("\nHere are your league settings:\n");
        System.out.println("League Name: " + league.getLeagueName());
        System.out.println("Maximum Teams: " + league.getLeagueSize());

        System.out.println("\nWould you like to update the league settings? (Y/N)");
        String command;
        input = new Scanner(System.in);
        command = input.next();
        command = command.toLowerCase();

        switch (command) {
            case "y":
                changeLeagueSettings(league);
                break;
            case "n":
                System.out.println("\nReturning to home screen.\n");
                break;
            default:
                System.out.println("\nThe command is not valid. Please try again.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes league name and/or size
    private void changeLeagueSettings(League league) {
        System.out.println("What would you like to rename the league to? (No Spaces)");
        String newName;
        input = new Scanner(System.in);
        newName = input.next();

        league.changeLeagueName(newName);

        System.out.println("What would you like to change the league size to?");
        int newSize;
        input = new Scanner(System.in);
        newSize = input.nextInt();

        league.changeLeagueSize(newSize);

        printLeagueSettings(league);
    }

    // EFFECTS: quits the application
    private void closeApplication() {
        continueApp = false;
        System.out.println("The NBA Fantasy Helper has been closed.");
    }

}
