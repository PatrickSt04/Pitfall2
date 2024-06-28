package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.List;

class LevelManager extends GameWorldManager {
    private List<Level> levels;
    public static final int LIVES = 1;

    protected LevelManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        lives = LIVES;
        initializeGameObjects();
    }

    protected boolean hasNextLevel() {
        return level.number < levels.size();
    }

    protected void switchToNextLevel() {
        //When Harry dies the level restarts and not the entire game!
        if (hasNextLevel() && !(lives == 0)) {
            level = levels.get(level.number);
        } else if (!hasNextLevel() && lives == 0) {
            level = levels.get(levels.size() - 1);
        } else if (lives == 0) {
            level = levels.get(level.number - 1);
        } else {
            throw new NoMoreLevelsAvailableException("No more levels available");
        }
    }

    protected void initializeGame() {
        levels = List.of(new Level1(), new Level2(), new Level3(), new Level4(), new Level5());
        level = levels.get(0);
        lives = LIVES;
        points = 0;
    }

    private void initializeGameObjects() {
        currentScore.updatePoints(this.points);
        time.resetTime();
        //Die Methode soll in Zukunft dazu genutzt werden um Spielelemente an ein neues Level anzupassen, z.B.:
        //Anpassungen für das Level am Hintergrund machen
        //Die Lebensanzeige aktualisieren
        //Den Punktestand aus dem vorherigen Level übernehmen
        //Einen Coundown neu starten
    }
}
