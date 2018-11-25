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

    public void writeCSV(ResultSet resultSet) throws IOException, SQLException {
        CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.EXCEL);
        printer.printRecords(resultSet);
    }

    public List<CSVRecord> readCSV(File file) throws IOException {
        CSVParser parser = CSVParser.parse(file, Charsets.toCharset("UTF-8"), CSVFormat.EXCEL);
        return parser.getRecords();
    }

    public void printRepositoryOfGames(GamesRepository list) {
        System.out.printf("Nr   Tytuł                                                        "
                + "Producent                                "
                + "Wydawca                                  "
                + "Rok             "
                + "Rating\n"
                + "-------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------\n");
        list.getGames().forEach(item -> System.out.printf("%-4d %-60s %-40s %-40s %-15d %-5d\n",
                item.getID(),
                item.getTitle(),
                item.getDevelper(),
                item.getPublisher(),
                item.getYear(),
                item.getRate())
        );

    }
}

