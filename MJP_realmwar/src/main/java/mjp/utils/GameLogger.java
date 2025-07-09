package mjp.utils;

import com.google.gson.Gson;
import mjp.controllers.GameController;
import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.models.structures.Structure;
import mjp.models.units.Unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//       DEMO       DEMO       DEMO       DEMO       DEMO       DEMO       DEMO
public class GameLogger {
    private static final String URL = "jdbc:postgresql://localhost:5432/realm_war"; // must check
    private static final String OWNER = "postgres";
    private static final String PASSWORD = "1234";
    GameController gameController;
    ArrayList<String> players;
    ArrayList<String> kingdoms;
    ArrayList<String> structures;
    ArrayList<String> units;
    Gson gson;

    {
        createTable();
    }

    public GameLogger(GameController gameController) {
        this.gameController = gameController;
        gson = new Gson();
        players = new ArrayList<>();
        kingdoms = new ArrayList<>();
        structures = new ArrayList<>();
        units = new ArrayList<>();
    }

    public void saveMatch() {
        readyObjectsToQuery();
        setArrays();
        query();

        //    ******************   after connecting to data base   ***********************
        removeArrays();
    }

    public void readyObjectsToQuery() {
        for (Player p : Player.getPlayers()) {
            p.readyToJson();
        }

        for (Kingdom k : Kingdom.getKingdoms()) {
            k.readyToJson();
        }

        for (Unit u : Unit.getUnits()) {
            u.readyToJson();
        }

        for (Structure s : Structure.getStructures()) {
            s.readyToJson();
        }
    }

    public void setArrays() {
        for (Player p : Player.getPlayers()) {
            this.players.add(gson.toJson(p));
        }

        for (Kingdom k : Kingdom.getKingdoms()) {
            this.kingdoms.add(gson.toJson(k));
        }

        for (Structure s : Structure.getStructures()) {
            this.structures.add(gson.toJson(s));
        }

        for (Unit u : Unit.getUnits()) {
            this.units.add(gson.toJson(u));
            System.out.println(gson.toJson(u));
        }
    }

    public void query() {
        System.out.println("(exit button action listener)gameLogger>>save>>query>>complete sql code");
    }

    public void removeArrays() {
        players = new ArrayList<>();
        kingdoms = new ArrayList<>();
        units = new ArrayList<>();
        structures = new ArrayList<>();
    }

    public void createTable() {  // SHOULD CHANGE
        String sql = "CREATE TABLE IF NOT EXISTS student (" +
                "id SERIAL UNIQUE, " +
                "name TEXT NUT NULL, " +
                "birthday TEXT, " +
                "score DECIMAL(3, 1), " +
                "city VARCHAR(20))";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Connection successfully cached");
        } catch (SQLException e) {
            System.err.println("gameLogger>>staticBlock>>createTable>>SQl-" +
                    "exception>>connection to data base failed");
        }
    }
}
