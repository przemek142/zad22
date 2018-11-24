import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IOoperations {

    public void writeCSV(ResultSet resultSet) throws IOException, SQLException {
        CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.MYSQL);
        printer.printRecords(resultSet);
    }

    public void printRecordsToConsole(ResultSet resultSet) throws SQLException, IOException {
        CSVPrinter printer = new CSVPrinter(System.out, CSVFormat.MYSQL);
        printer.printRecords(resultSet);
    }

}

