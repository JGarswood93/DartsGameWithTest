package com.barton;

public class Darts {
    public static Object Multiplier;

    /**
     * Here is enum which is a way to set mandatory variables
     * that can be used in the programme
     *
     *In this case DOUBLE and TRIPLE
     */
    public enum Multipliers {
        DOUBLE, TRIPLE
    }

    /**
     * data set with values that can be used in later
     * methods
     *
     * Mandatory values to allow the game to run correctly
     */
    private int dartsLeft = 3;
    private int turn = 1;

    private int score = 301;
    private int lastTurnScore = score;
    private boolean isFinished = false;

    public int score() {
        return score;
    }

    /**
     * This has the dart assigned to the data type
     * and  computes a score to allow the score to be put
     * into the memory
     */

    public void dart(int i) {
        computeScore(i, null);
    }

    /**
     * Switch statement using the data from the enum
     *
     * method for the dart giving the score an int value
     * giving the multipliers a variable so that it can be used in
     * the method
     *
     * The purpose of this method is to use the numbers to assign
     * to the fields which can then create a score from the methods
     * functionality
     */
    public void dart(int score, Multipliers multipliers) {
        int mult = 0;
        switch (multipliers) {
            case DOUBLE:
                mult = 2;
                break;

            case TRIPLE:
                mult = 3;
                break;
        }
        /**This bit is where the score is calculated
         */
        int dartScore = mult * score;

        computeScore(dartScore, multipliers);
    }

    /**
     * Taking the score and the dart score and minimising the two numbers
     * to comply with the scoring system in darts
     */
    private void computeScore(int dartScore, Multipliers m) {

        int newScore = score - dartScore;

        if (newScore == 0 && m.equals(Multipliers.DOUBLE)) {
            isFinished = true;
            return;//return the logic established
        }
        /**
         * If the score has had its three turns it goes back to
         * a new score ie the next turn.
         *
         * no darts are left then it becomes reset
         *
         * this goes through all the instances for darts to
         * be set to 3 darts
         */
        if ((newScore == 1 && dartsLeft != 1) || (newScore < 0)) {
            newScore = lastTurnScore;
            dartsLeft = 3;
            turn++;
        } else if (dartsLeft == 1) {
            lastTurnScore = newScore;
            dartsLeft = 3;
            turn++;
        } else {
            dartsLeft--;
        }
        score = newScore;
    }

    /**
     * These are the data types that need methods in order for them to operate
     * they return their variable.
     */
    public int getTurn() {
        return turn;
    }

    public int dartsLeft() {
        return dartsLeft;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
