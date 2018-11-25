import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        IOoperations io = new IOoperations();
        GamesRepository initGames = new GamesRepository();

        //***********************************//
        //            DB INIT                 //
        //***********************************//

        File initCSV = new File("init.csv");
        List<CSVRecord> initListOfGames;
        try {
            initListOfGames = io.readCSV(initCSV);
            initListOfGames.forEach(item -> {
                if ("\uFEFFID".compareTo(item.get(0).split(";")[0]) != 0) {
                    initGames.add(
                            new Game(
                                    Integer.parseInt(item.get(0).split(";")[0]), //ID
                                    Integer.parseInt(item.get(0).split(";")[1]), //year
                                    Integer.parseInt(item.get(0).split(";")[5]), //rate
                                    String.valueOf(item.get(0).split(";")[2]), //title
                                    String.valueOf(item.get(0).split(";")[3]), //developer
                                    String.valueOf(item.get(0).split(";")[4])  //publisher
                            )
                    );
                }
            });
            io.printRepositoryOfGames(initGames);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }
}
