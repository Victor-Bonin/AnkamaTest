package com.company;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private final boolean SHOULD_BE_DIFFERENT = true;

    private int numberA;
    private int numberB;
    private int numberC;


    public void play() {
        System.out.println("WELCOME TO THE SUPER GAME");
        this.initNumbers();

        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = this.playTurn();
        }
        System.out.println("GG");
        System.out.println("Secret numbers were "+ this.numberA + ", " + this.numberB + " and " + this.numberC);

    }


    // Not sure if numbers should be different so I added 'shouldBeDifferent' boolean
    private void initNumbers() {
        numberA = ThreadLocalRandom.current().nextInt(1, 11);
        numberB = ThreadLocalRandom.current().nextInt(1, 11);
        while (this.SHOULD_BE_DIFFERENT && numberB == numberA) {
            numberB = ThreadLocalRandom.current().nextInt(1, 11);
        }
        numberC = ThreadLocalRandom.current().nextInt(1, 11);
        while (this.SHOULD_BE_DIFFERENT && (numberC == numberA || numberC == numberB) ) {
            numberC = ThreadLocalRandom.current().nextInt(1, 11);
        }
    }


    private boolean playTurn() {
        int userNumber = this.getUserNumber();
        int score = this.getScore(userNumber);

        if (score == 3) {
            return true;
        }
        else {
            System.out.println("Score : " + score);
            return false;
        }
    }

    private int getScore(int userNumber) {
        int score = 0;
        if (userNumber % numberA == 0) { score ++; }
        if (userNumber % numberB == 0) { score ++; }
        if (userNumber % numberC == 0) { score ++; }
        return score;
    }

    private int getUserNumber() {
        try {
            System.out.println("Please make a guess");
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        }
        catch (Exception exc) {
            System.err.println("Invalid Format!");
            return this.getUserNumber();
        }
    }
}
