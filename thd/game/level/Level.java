package thd.game.level;

/**
 * The Level class represents the upper class of all Levels.
 */
public class Level {
    /**
     * The name of the Level.
     */
    public String name;
    /**
     * The number of the Level.
     */
    public int number;
    /**
     * The world String the Level is created by.
     */
    public String world;
    /**
     * Offset Lines in case of a bigger World than shown in game view.
     */
    public int worldOffsetLines;
    /**
     * Offset Columns in case of a bigger World than shown in game view.
     */
    public int worldOffsetColumns;

    /**
     * Describes the Difficulty of the Level.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;

    protected String sound;

    //Characters for World String
    //a = Water lake
    //A = Bat
    //b = Raven
    //B = Background with Trees
    //c = crocodile
    //C = Cave
    //d = Statue
    //D =
    //e = LevelEnd
    //E = Cave with ice
    //f = FireBall falling
    //F =
    //g = Frog
    //G = CashBag
    //h =
    //H = Hole
    //i = aggressive Crocodile
    //I = Rock right
    //j = FireBall jumping
    //J =
    //k = Key
    //K =
    //l = Liane
    //L = Ladder
    //m =
    //M =
    //n =
    //N = Cave with Brown Bricks
    //o = liana opposite
    //O = Floor Upper Part
    //p = AngryApple
    //P = PitfallHarry
    //q =
    //Q =
    //r =
    //R = Rock left
    //s = StartSign
    //S = Scorpion
    //t =
    //T = Background with Trees smiling
    //u =
    //U = Floor lower Part
    //v = LavaLake small
    //V = LavaLake big
    //w = WoodenLog
    //W = Cave with brown bricks and water
    //x = Background Volcano Green
    //X =
    //y = Background Volcano Grey
    //Y = HayBall
    //z = acorn
    Level() {
    }

    /**
     * Returns the background sound of the current level.
     *
     * @return sound.
     */
    public String getSound() {
        return sound;
    }
}
