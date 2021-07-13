package com.lucaspq.error;

public class HangmanGameNotFoundException extends RuntimeException {

    public HangmanGameNotFoundException() {
        super("Hangman Game not found");
    }

}
