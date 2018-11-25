import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLoperations {

    public Connection initDB() throws SQLException {
        String createGameTable = "CREATE TABLE if not exists `zad22`.`games` (\n" +
                "  `ID` INT NOT NULL,\n" +
                "  `title` VARCHAR(100) NOT NULL,\n" +
                "  `developer` VARCHAR(100) NOT NULL,\n" +
                "  `publisher` VARCHAR(100) NOT NULL,\n" +
                "  `year` INT NOT NULL,\n" +
                "  `rate` INT NOT NULL,\n" +
                "  PRIMARY KEY (`ID`),\n" +
                "  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE);\n";
        String url = "jdbc:mysql://192.168.56.128:3306/?characterEncoding=utf8";
        String ur2 = "jdbc:mysql://192.168.56.128:3306/zad22?characterEncoding=utf8";
        String username = "root";
        String password = "toor";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        statement.execute("CREATE SCHEMA IF NOT EXISTS zad22");
        connection = DriverManager.getConnection(ur2, username, password);
        statement.execute(createGameTable);
        return connection;
    }

    public String singleInsertStatement(Game game) {
        return String.format("INSERT INTO `zad22`.`games` (`ID`, `title`, `developer`, `publisher`, `year`, `rate`) VALUES ('%d', '%s', '%s', '%s', '%d', '%d');",
                game.getID(), game.getTitle(), game.getDeveloper(), game.getPublisher(), game.getYear(), game.getRate());
    }

    public void writeRepoToSQL(GamesRepository gamesRepository, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        gamesRepository.getGames().forEach(item -> {
                    try {
                        statement.addBatch(singleInsertStatement(item));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

        statement.executeBatch();
        connection.commit();

    }
}
