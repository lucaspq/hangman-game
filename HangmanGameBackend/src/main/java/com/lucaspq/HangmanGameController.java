package com.lucaspq;

import com.lucaspq.error.HangmanGameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HangmanGameController {

    private HangmanGame hangmanGame = null;

    @GetMapping("/newHangmanGame")
    void newHangmanGame() {
        hangmanGame = new HangmanGame();
    }

    @GetMapping("/tryLetter/{c}")
    void tryLetter(@PathVariable Character c) {
        if (hangmanGame == null) {
            throw new HangmanGameNotFoundException();
        } else {
            hangmanGame.tryLetter(new Letter(c));
        }
    }

    @GetMapping("/status")
    HangmanGameStatus status() {
        if (hangmanGame == null) {
            throw new HangmanGameNotFoundException();
        } else {
            return hangmanGame.getStatus();
        }
    }

    @GetMapping("/getLetterAll")
    List<Letter> getLetterAll() {
        if (hangmanGame == null) {
            throw new HangmanGameNotFoundException();
        } else {
            return hangmanGame.getLetterAll();
        }
    }

    @GetMapping("/getWord")
    List<Letter> getWord() {
        if (hangmanGame == null) {
            throw new HangmanGameNotFoundException();
        } else {
            return hangmanGame.getWord();
        }
    }

    @GetMapping("/guessesRemaining")
    Long guessesRemaining() {
        if (hangmanGame == null) {
            throw new HangmanGameNotFoundException();
        } else {
            return HangmanGame.MAX_TRIES - hangmanGame.getNumMisses();
        }
    }
    

}
