package com.company;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private final boolean SHOULD_BE_DIFFERENT = true;

    private Logger logger = new Logger();
    private RandomGenerator generator = new RandomGenerator();
    private InputManager inputManager = new InputManager(logger);

    private int numberA;
    private int numberB;
    private int numberC;


    private static class Logger {

        void log(String text) {
            System.out.println(text);
        }

        void err(String text) {
            System.err.println(text);
        }
    }


    private static class RandomGenerator {

        int randomInt(int inclusiveOrigin, int exclusiveBound) {
            return ThreadLocalRandom.current().nextInt(inclusiveOrigin, exclusiveBound);
        }
    }

    private static class InputManager {

        private Logger logger;

        public InputManager(Logger logger) {
            this.logger = logger;
        }

        int readNumber() {
            try {
                logger.log("Please make a guess");
                Scanner in = new Scanner(System.in);
                return in.nextInt();
            } catch (Exception exc) {
                logger.err("Invalid Format!");
                return this.readNumber();
            }
        }
    }


    public void play() {
        logger.log("WELCOME TO THE SUPER GAME");
        this.initNumbers();

        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = this.playTurn();
        }
        logger.log("GG");
        logger.log("Secret numbers were "+ this.numberA + ", " + this.numberB + " and " + this.numberC);

    }


    // Not sure if numbers should be different so I added 'shouldBeDifferent' boolean
    private void initNumbers() {
        int numberMinimumBound = 1;
        int numberMaximumBound = 11;
        numberA = generator.randomInt(numberMinimumBound, numberMaximumBound);
        numberB = generator.randomInt(numberMinimumBound, numberMaximumBound);
        while (this.SHOULD_BE_DIFFERENT && numberB == numberA) {
            numberB = generator.randomInt(numberMinimumBound, numberMaximumBound);
        }
        numberC = generator.randomInt(numberMinimumBound, numberMaximumBound);
        while (this.SHOULD_BE_DIFFERENT && (numberC == numberA || numberC == numberB)) {
            numberC = generator.randomInt(numberMinimumBound, numberMaximumBound);
        }
    }


    private boolean playTurn() {
        int userNumber = inputManager.readNumber();
        int score = this.getScore(userNumber);

        logger.log("Score : " + score);
        return score == 3;
    }

    private int getScore(int userNumber) {
        int score = 0;
        if (userNumber % numberA == 0) { score ++; }
        if (userNumber % numberB == 0) { score ++; }
        if (userNumber % numberC == 0) { score ++; }
        return score;
    }
}
