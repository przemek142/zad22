import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    private String printChoices() {
        return "Witaj!\n" +
                "Masz do wyboru:\n" +
                "0 - pokaż wszyskie gry\n" +
                "1 - pokaż 10 najnowszych gier\n" +
                "2 - pokaż gry z oceną wyższą niż ...\n" +
                "Wpisz wybór: ";
    }


    public String menu() {

        System.out.println(printChoices());
        String choice = scanner.nextLine();
        String output = "select * from ";

        while (choice == null || (!"0".equals(choice) && !"1".equals(choice) && !"2".equals(choice))) {
            System.out.println("Błędny wybór!\nSpróbuj ponownie\n" + printChoices());
            choice = scanner.nextLine();
        }

        switch (choice) {
            case "0":
                output += "games";
                break;
            case "1":
                output += "(SELECT * FROM games order by year DESC limit 10) as tab order by year;";
                break;
            case "2":
                System.out.println("Wpisz liczbę od 0 do 4: ");
                int reating = scanner.nextInt();
                output += "games where rate > " + reating;
                break;
        }

            return output + ";";
        }
    }
