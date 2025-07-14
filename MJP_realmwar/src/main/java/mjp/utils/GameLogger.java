package mjp.utils;

import com.google.gson.Gson;
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
    private static final String PASSWORD = "1010";
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
            if (!players.contains(gson.toJson(p))) {
                this.players.add(gson.toJson(p));
            }
        }


        for (Kingdom k : Kingdom.getKingdoms()) {
            if (!kingdoms.contains(gson.toJson(k))) {
                this.kingdoms.add(gson.toJson(k));
            }
        }

        for (Structure s : Structure.getStructures()) {
            if (!structures.contains(gson.toJson(s))) {
                this.structures.add(gson.toJson(s));
            }
        }

        for (Unit u : Unit.getUnits()) {
            if (!units.contains(gson.toJson(u))) {
                this.units.add(gson.toJson(u));
            }
        }
    }

    public void deleteTables() {
        String[] tables = {"player", "kingdom", "structure", "unit"};

        for (String table : tables) {
            String sql = "DELETE FROM " + table;

            try (
                    Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
                    Statement statement = connection.createStatement()
            ) {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("Failed to delete from table: " + table);
            }
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

        for (String s : players) {

            try {
                Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.out.println("connecting to player table failed");
            }
        }
    }

    public void insertKingdoms() {
        String sql = "INSERT INTO kingdom (json) VALUES (?)";

        for (String k : kingdoms) {

            try {
                Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, k);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.out.println("connecting to kingdom table failed");
            }
        }
    }

    public void insertStructures() {
        String sql = "INSERT INTO structure (json) VALUES (?)";

        for (String s : structures) {

            try {
                Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.out.println("connecting to structure table failed");
            }
        }
    }

    public void insertUnits() {
        String sql = "INSERT INTO unit (json) VALUES (?)";

        for (String u : units) {

            try {
                Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, u);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.out.println("connecting to unit table failed");
            }
        }
    }

    public void removeArrays() {
        players = new ArrayList<>();
        kingdoms = new ArrayList<>();
        units = new ArrayList<>();
        structures = new ArrayList<>();
    }

    public void readFromDateBase() {
        readFromPlayer();
        readFromKingdom();
        readFromStructure();
        readFromUnit();
        gameController.fillAttributes();
    }

    public void readFromPlayer() {
        String sql = "SELECT json text FROM player";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                players.add(resultSet.getString("json text"));
            }
        } catch (SQLException e) {
            System.out.println("read from player failed");
        }

        for (String s : players) {
            Player.getPlayers().add(gson.fromJson(s, Player.class));
        }
    }

    public void readFromKingdom() {
        String sql = "SELECT json text FROM kingdom";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                kingdoms.add(resultSet.getString("json text"));
            }
        } catch (SQLException e) {
            System.out.println("read from kingdom failed");
        }

        for (String s : kingdoms) {
            Kingdom.getKingdoms().add(gson.fromJson(s, Kingdom.class));
        }
    }

    public void readFromStructure() {
        String sql = "SELECT json text FROM structure";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                structures.add(resultSet.getString("json text"));
            }
        } catch (SQLException e) {
            System.out.println("read from structure failed");
        }

        for (String s : structures) {
            Structure.getStructures().add(gson.fromJson(s, Structure.class));
        }
    }

    public void readFromUnit() {
        String sql = "SELECT json text FROM unit";

        try {
            Connection connection = DriverManager.getConnection(URL, OWNER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                units.add(resultSet.getString("json text"));
            }
        } catch (SQLException e) {
            System.out.println("read from unit failed");
        }

        for (String s : units) {
            Unit.getUnits().add(gson.fromJson(s, Unit.class));
        }
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
