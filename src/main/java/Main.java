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

//        io.printRepositoryOfGames(initGames);

        try {
            connection = sql.initDB();

            Statement statement = connection.createStatement();
            String query = "select * from games";
            ResultSet resultSet = statement.executeQuery(query);
            io.writeCSV(resultSet, "games.csv");

            sql.writeRepoToSQL(initGames, connection);

//            System.out.println(sql.singleInsertStatement(initGames.getGames().get(3)));

        }catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("SQL ERROR");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try{
            GamesRepository currentGameRepo = new GamesRepository();
            Menu menu = new Menu();
            String queryMenu = menu.menu();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryMenu);
            io.writeCSV(resultSet, "currentGames.csv");
            io.readCSVwriteToRepo(currentGameRepo,io,"currentGames.csv");
            io.printRepositoryOfGames(currentGameRepo);

        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("SQL ERROR");

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

