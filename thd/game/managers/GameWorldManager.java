package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {
    private final List<GameObject> activatableGameObjects;

    protected GameWorldManager(GameView gameView) {
        super(gameView);

        activatableGameObjects = new LinkedList<>();
        highscore = new Highscore(gameView, this);
        currentScore = new CurrentScore(gameView, this);
        icon = new Icon(gameView, this);
        statusBlockImages = new StatusBlockImages(gameView, this);
        time = new Time(gameView, this);
        pitfallHarry = new PitfallHarry(gameView, this);
        overlay = new Overlay(gameView, this);
    }

    private void spawnGameObjects() {
        spawnGameObject(highscore);
        spawnGameObject(currentScore);
        spawnGameObject(icon);
        spawnGameObject(statusBlockImages);
        spawnGameObject(time);
        spawnGameObject(overlay);
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = level.world.split("\\R");
        for (int line = 0; line < lines.length; line++) {
            for (int column = 0; column < lines[line].length(); column++) {
                double x = (column - level.worldOffsetColumns) * 10;
                double y = (line - level.worldOffsetLines) * 10;
                char character = lines[line].charAt(column);
                if (character == 'B') {
                    BackgroundTrees backgroundTrees = new BackgroundTrees(gameView, this, false);
                    backgroundTrees.getPosition().updateCoordinates(x, y);
                    spawnGameObject(backgroundTrees);
                } else if (character == 'P') {
                    pitfallHarry.getPosition().updateCoordinates(x, y);
                    spawnGameObject(pitfallHarry);
                } else if (character == 'O') {
                    FloorUpperPart floorUpperPart = new FloorUpperPart(gameView, this);
                    floorUpperPart.getPosition().updateCoordinates(x, y);
                    spawnGameObject(floorUpperPart);
                } else if (character == 'U') {
                    FloorLowerPart floorLowerPart = new FloorLowerPart(gameView, this);
                    floorLowerPart.getPosition().updateCoordinates(x, y);
                    spawnGameObject(floorLowerPart);
                } else if (character == 'R') {
                    Rock rockLeft = new Rock(gameView, this, true);
                    rockLeft.getPosition().updateCoordinates(x, y);
                    spawnGameObject(rockLeft);
                    pitfallHarry.addGameObjectForPathDecision(rockLeft);
                } else if (character == 'C') {
                    Cave cave = new Cave(gameView, this);
                    cave.getPosition().updateCoordinates(x, y);
                    spawnGameObject(cave);
                } else if (character == 'L') {
                    Ladder ladder = new Ladder(gameView, this);
                    ladder.getPosition().updateCoordinates(x, y);
                    spawnGameObject(ladder);
                    pitfallHarry.addGameObjectForPathDecision(ladder);
                } else if (character == 'H') {
                    Hole hole = new Hole(gameView, this);
                    hole.getPosition().updateCoordinates(x, y);
                    spawnGameObject(hole);
                } else if (character == 'I') {
                    Rock rockRight = new Rock(gameView, this, false);
                    rockRight.getPosition().updateCoordinates(x, y);
                    spawnGameObject(rockRight);
                    pitfallHarry.addGameObjectForPathDecision(rockRight);
                } else if (character == 'S') {
                    Scorpion scorpion = new Scorpion(gameView, this, y);
                    scorpion.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(scorpion);
                } else if (character == 'T') {
                    BackgroundTrees backgroundSmilingTrees = new BackgroundTrees(gameView, this, true);
                    backgroundSmilingTrees.getPosition().updateCoordinates(x, y);
                    spawnGameObject(backgroundSmilingTrees);
                } else if (character == 'N') {
                    Cave caveWithBrownBricks = new Cave(gameView, this);
                    caveWithBrownBricks.setBrownBricks(true);
                    caveWithBrownBricks.setWithWater(false);
                    caveWithBrownBricks.getPosition().updateCoordinates(x, y);
                    spawnGameObject(caveWithBrownBricks);
                } else if (character == 'A') {
                    Bat bat = new Bat(gameView, this, y);
                    bat.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(bat);
                } else if (character == 'W') {
                    Cave caveWithBrownBricksAndWater = new Cave(gameView, this);
                    caveWithBrownBricksAndWater.setBrownBricks(true);
                    caveWithBrownBricksAndWater.setWithWater(true);
                    caveWithBrownBricksAndWater.getPosition().updateCoordinates(x, y);
                    spawnGameObject(caveWithBrownBricksAndWater);
                } else if (character == 'G') {
                    CashBag cashBag = new CashBag(gameView, this);
                    cashBag.getPosition().updateCoordinates(x, y);
                    spawnGameObject(cashBag);
                } else if (character == 'v') {
                    LavaLake lavaLakeSmall = new LavaLake(gameView, this, false);
                    lavaLakeSmall.getPosition().updateCoordinates(x, y);
                    spawnGameObject(lavaLakeSmall);
                } else if (character == 'l') {
                    Liana liana = new Liana(gameView, this, false);
                    liana.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(liana);
                } else if (character == 'o') {
                    Liana liana = new Liana(gameView, this, true);
                    liana.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(liana);
                } else if (character == 'w') {
                    WoodenLog woodenLog = new WoodenLog(gameView, this, y);
                    woodenLog.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(woodenLog);
                } else if (character == 'Y') {
                    HayBall hayBall = new HayBall(gameView, this);
                    hayBall.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(hayBall);
                } else if (character == 's') {
                    startSign = new StartSign(gameView, this);
                    startSign.getPosition().updateCoordinates(x, y);
                    spawnGameObject(startSign);
                    pitfallHarry.addGameObjectForPathDecision(startSign);
                } else if (character == 'p') {
                    Apple apple = new Apple(gameView, this, pitfallHarry);
                    apple.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(apple);
                } else if (character == 'y') {
                    BackgroundVolcano backgroundVolcanoGrey = new BackgroundVolcano(gameView, this);
                    backgroundVolcanoGrey.setGrey(true);
                    backgroundVolcanoGrey.getPosition().updateCoordinates(x, y);
                    spawnGameObject(backgroundVolcanoGrey);
                } else if (character == 'j') {
                    FireBall fireBall = new FireBall(gameView, this, true);
                    fireBall.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(fireBall);
                } else if (character == 'f') {
                    FireBall fireBallFalling = new FireBall(gameView, this, false);
                    fireBallFalling.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(fireBallFalling);
                } else if (character == 'V') {
                    LavaLake lavaLakeBig = new LavaLake(gameView, this, true);
                    lavaLakeBig.getPosition().updateCoordinates(x, y);
                    spawnGameObject(lavaLakeBig);
                } else if (character == 'E') {
                    Cave caveWithIce = new Cave(gameView, this);
                    caveWithIce.getPosition().updateCoordinates(x, y);
                    caveWithIce.setWithIce(true);
                    spawnGameObject(caveWithIce);
                } else if (character == 'e') {
                    LevelEnd levelEnd = new LevelEnd(gameView, this);
                    levelEnd.getPosition().updateCoordinates(x, y);
                    spawnGameObject(levelEnd);
                } else if (character == 'g') {
                    Frog frog = new Frog(gameView, this);
                    frog.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(frog);
                } else if (character == 'a') {
                    WaterLake waterLake = new WaterLake(gameView, this);
                    waterLake.getPosition().updateCoordinates(x, y);
                    spawnGameObject(waterLake);
                } else if (character == 'i') {
                    Crocodile aggressiveCrocodile = new Crocodile(gameView, this, true);
                    aggressiveCrocodile.getPosition().updateCoordinates(x, y);
                    spawnGameObject(aggressiveCrocodile);
                } else if (character == 'c') {
                    Crocodile crocodile = new Crocodile(gameView, this, false);
                    crocodile.getPosition().updateCoordinates(x, y);
                    spawnGameObject(crocodile);
                } else if (character == 'k') {
                    Key key = new Key(gameView, this);
                    key.getPosition().updateCoordinates(x, y);
                    spawnGameObject(key);
                } else if (character == 'z') {
                    Ancorn ancorn = new Ancorn(gameView, this, pitfallHarry);
                    ancorn.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(ancorn);
                } else if (character == 'x') {
                    BackgroundVolcano backgroundVolcanoGreen = new BackgroundVolcano(gameView, this);
                    backgroundVolcanoGreen.setGreen(true);
                    backgroundVolcanoGreen.getPosition().updateCoordinates(x, y);
                    spawnGameObject(backgroundVolcanoGreen);
                } else if (character == 'b') {
                    Raven raven = new Raven(gameView, this, y);
                    raven.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(raven);
                } else if (character == 'd') {
                    Statue statue = new Statue(gameView, this, pitfallHarry);
                    statue.getPosition().updateCoordinates(x, y);
                    pitfallHarry.addGameObjectForPathDecision(statue);
                    spawnGameObject(statue);
                }
            }
        }
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }

    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof Scorpion scorpion) {
                if (scorpion.tryToActivate(pitfallHarry)) {
                    spawnGameObject(scorpion);
                    iterator.remove();
                }
            } else if (gameObject instanceof Bat bat) {
                if (bat.tryToActivate(pitfallHarry)) {
                    spawnGameObject(bat);
                    iterator.remove();
                }
            } else if (gameObject instanceof Liana liana) {
                if (liana.tryToActivate(pitfallHarry)) {
                    spawnGameObject(liana);
                    iterator.remove();
                }
            } else if (gameObject instanceof WoodenLog woodenLog) {
                if (woodenLog.tryToActivate(pitfallHarry)) {
                    spawnGameObject(woodenLog);
                    iterator.remove();
                }
            } else if (gameObject instanceof HayBall hayBall) {
                if (hayBall.tryToActivate(pitfallHarry)) {
                    spawnGameObject(hayBall);
                    iterator.remove();
                }
            } else if (gameObject instanceof Apple apple) {
                if (apple.tryToActivate(pitfallHarry)) {
                    spawnGameObject(apple);
                    iterator.remove();
                }
            } else if (gameObject instanceof FireBall fireBall) {
                if (fireBall.tryToActivate(pitfallHarry)) {
                    spawnGameObject(fireBall);
                    iterator.remove();
                }
            } else if (gameObject instanceof Frog frog) {
                if (frog.tryToActivate(pitfallHarry)) {
                    spawnGameObject(frog);
                    iterator.remove();
                }
            } else if (gameObject instanceof Raven raven) {
                if (raven.tryToActivate(pitfallHarry)) {
                    spawnGameObject(raven);
                    iterator.remove();
                }
            } else if (gameObject instanceof Ancorn ancorn) {
                if (ancorn.tryToActivate(pitfallHarry)) {
                    spawnGameObject(ancorn);
                    iterator.remove();
                }
            }
        }
    }

    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        clearListsForPathDecisionsInGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
    }

    private void clearListsForPathDecisionsInGameObjects() {
        pitfallHarry.clearGameObjectsForPathDecision();
    }
}
