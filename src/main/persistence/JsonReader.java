package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.League;
import model.Player;
import model.Team;
import org.json.*;

// Represents a reader that reads league from JSON data stored in file
// This class references code from this repo
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int size = jsonObject.getInt("size");
        League league = new League(name, size);
        addTeams(league, jsonObject);
        return league;
    }

    // MODIFIES: league
    // EFFECTS: parses teams from JSON object and adds them to league
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTeam(league, nextThingy);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses team from JSON object and adds it to league
    private void addTeam(League league, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
        league.registerTeam(team);
        addPlayers(team, jsonObject);
    }

    // MODIFIES: league
    // EFFECTS: parses teams from JSON object and adds them to league
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("roster");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addPlayer(team, nextThingy);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses team from JSON object and adds it to league
    private void addPlayer(Team team, JSONObject jsonObject) {
        String playerName = jsonObject.getString("playerName");
        Player player = new Player(playerName, team);
        team.addPlayer(player);
    }
}
