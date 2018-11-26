import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        GamesRepository initGames = new GamesRepository(); //For DB Initiation from CSV
        Connection connection = null;
        IOoperations io = new IOoperations();
        SQLoperations sql = new SQLoperations();

        io.readCSVwriteToRepo(initGames, io, "init.csv");
        try {
            connection = sql.initDB();
            sql.writeRepoToSQL(initGames, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            GamesRepository currentGameRepo = new GamesRepository();
            Menu menu = new Menu();
            String queryMenu = menu.menu();
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(queryMenu);
            io.writeCSV(resultSet2, "currentGames.csv");
            io.readCSVwriteToRepo(currentGameRepo, io, "currentGames.csv");
            io.printRepositoryOfGames(currentGameRepo);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

