package mjp.utils;

import com.google.gson.Gson;
import com.sun.source.tree.TryTree;
import mjp.controllers.GameController;
import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.models.structures.Structure;
import mjp.models.units.Unit;

import java.sql.*;
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
        createTables();
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
        deleteTables();
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
        }
    }

    public void deleteTables() {
        String[] tables = {"player", "kingdom", "structure", "unit"};
        String sql = "DELETE FROM ?";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String s : tables) {
                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("delete from data base failed");
        }
    }

    public void query() {
//        System.out.println("(exit button action listener)gameLogger>>save>>query>>complete sql code");
        insertPlayers();
        insertKingdoms();
        insertStructures();
        insertUnits();
    }

    public void insertPlayers() {
        String sql = "INSERT INTO player (json) VALUES (?)";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String s : players) {
                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("connecting to player table failed");
        }
    }

    public void insertKingdoms() {
        String sql = "INSERT INTO kingdom (json) VALUES (?)";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String k : kingdoms) {
                preparedStatement.setString(1, k);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("connecting to kingdom table failed");
        }
    }

    public void insertStructures() {
        String sql = "INSERT INTO structure (json) VALUES (?)";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String s : structures) {
                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("connecting to structure table failed");
        }
    }

    public void insertUnits() {
        String sql = "INSERT INTO unit (json) VALUES (?)";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String u : units) {
                preparedStatement.setString(1, u);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("connecting to unit table failed");
        }
    }

    public void removeArrays() {
        players = new ArrayList<>();
        kingdoms = new ArrayList<>();
        units = new ArrayList<>();
        structures = new ArrayList<>();
    }

    public void createTables() {  // SHOULD CHANGE
        String player = "CREATE TABLE IF NOT EXISTS player (" +
                "json text)";
        String kingdom = "CREATE TABLE IF NOT EXISTS kingdom (" +
                "json text)";
        String structure = "CREATE TABLE IF NOT EXISTS structure (" +
                "json text)";
        String unit = "CREATE TABLE IF NOT EXISTS unit (" +
                "json text)";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(player);
            statement.execute(kingdom);
            statement.execute(structure);
            statement.execute(unit);
            System.out.println("Connection successfully cached");
        } catch (SQLException e) {
            System.err.println("gameLogger>>staticBlock>>createTable>>SQl-" +
                    "exception>>connection to data base failed");
        }
    }
}
