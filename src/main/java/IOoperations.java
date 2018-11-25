import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.Charsets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IOoperations {

    public void writeCSV(ResultSet resultSet, String filename) throws IOException, SQLException {
        CSVPrinter printer = new CSVPrinter(new FileWriter(filename), CSVFormat.EXCEL.withDelimiter(';'));
        printer.printRecords(resultSet);
    }

    public void writeSTDOUT(ResultSet resultSet) throws IOException, SQLException {
        CSVPrinter printer = new CSVPrinter(System.out, CSVFormat.EXCEL);
        printer.printRecords(resultSet);
    }

    public List<CSVRecord> readCSV(File file) throws IOException {
        CSVParser parser = CSVParser.parse(file, Charsets.toCharset("UTF-8"), CSVFormat.EXCEL);
        return parser.getRecords();
    }

    private String multiplyChar(char c, int i) {
        String out = "";
        for (int j = 0; j < i; j++) {
            out += c;
        }
        return out;
    }

    public void printRepositoryOfGames(GamesRepository list) {
        System.out.printf("ID   Tytuł" + multiplyChar(' ', 56)
                + "Producent" + multiplyChar(' ', 32)
                + "Wydawca" + multiplyChar(' ', 34)
                + "Rok" + multiplyChar(' ', 13)
                + "Rating\n" + multiplyChar('-', 170) + "\n");
        list.getGames().forEach(item -> System.out.printf("%-4d %-60s %-40s %-40s %-15d %-5d\n",
                item.getID(), item.getTitle(), item.getDeveloper(),
                item.getPublisher(), item.getYear(), item.getRate())
        );

    }

    public void readCSVwriteToRepo(GamesRepository initGames, IOoperations io, String file) {

        File initCSV = new File(file);
        List<CSVRecord> initListOfGames;
        try {
            initListOfGames = io.readCSV(initCSV);
            initListOfGames.forEach(item -> {
                String[] gameData = item.get(0).split(";");
//                System.out.printf(gameData[0] + " " +gameData[5] + "\n");
                if (!"\uFEFF\uFEFFID".equals(gameData[0])) {
                    initGames.add(
                            new Game(
                                    Integer.parseInt(gameData[0]), //ID
                                    Integer.parseInt(gameData[1]), //year
                                    Integer.parseInt(gameData[5]), //rate
                                    gameData[2], //title
                                    gameData[3], //developer
                                    gameData[4]  //publisher
                            )
                    );
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

