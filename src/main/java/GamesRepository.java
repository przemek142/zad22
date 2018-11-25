import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data
class GamesRepository {
    private List<Game> games = new ArrayList<>();

    public void add(Game game) {
        if (game != null)
            games.add(game);
    }
}
