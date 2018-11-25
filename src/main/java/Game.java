import lombok.Data;

public @Data class  Game {
    private int ID,year,rate;
    private String title,develper, publisher;

    public Game(int ID, int year, int rate, String title, String develper, String publisher) {
        this.ID = ID;
        this.year = year;
        this.rate = rate;
        this.title = title;
        this.develper = develper;
        this.publisher = publisher;
    }



}
