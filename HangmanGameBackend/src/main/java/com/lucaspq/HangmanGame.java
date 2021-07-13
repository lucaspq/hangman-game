package com.lucaspq;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HangmanGame {

    private List<Letter> word;
    private List<Letter> letterAll;
    private List<String> words;
    private HangmanGameStatus status;
    private Long numMisses;
    public static final Long MAX_TRIES = 7L;

    // public static void main(String[] args) {
    //     HangmanGame hangmanGame = new HangmanGame();
    //     hangmanGame.tryLetter(new Letter('a'));
    // }

    public HangmanGame() {
        this.words = readWordsXML();
        this.letterAll = makeLetters("abcdefghijklmnopqrstuvwxyz");
        this.word = makeLetters(getRandomWord());
        this.status = HangmanGameStatus.ACTIVE;
        this.numMisses = 0L;
    }

    private List<String> readWordsXML() {
        File file = new File("src/main/resources/hangman.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        List<String> wordsXML = new ArrayList<String>();

        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("word");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                wordsXML.add(element.getTextContent());
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return wordsXML;
    }


    private String getRandomWord() {
        int index = ThreadLocalRandom.current().nextInt(this.words.size());
        return this.words.get(index);
    }
      
    private List<Letter> makeLetters(String alphabeth) {
        List<Letter> letters = new ArrayList<Letter>();

        for (int i = 0; i < alphabeth.length(); i++)
            letters.add(new Letter(alphabeth.charAt(i)));
        
        return letters;
    }
    
    private void revealWord() {
        for (Letter l : word)
            l.setChosen(true);
    }

    private void checkForEndOfGame() {
        Boolean win = true;

        for (Letter letter : word) {
            win = win && letter.getChosen();
        }

        if (win) {
            this.status = HangmanGameStatus.WON;
        } else if (this.numMisses == MAX_TRIES) {
            this.status = HangmanGameStatus.LOST;
            revealWord();
        }
    }

    private void incNumMisses(){
        this.numMisses++;
    }

    public void tryLetter(Letter guess) {
        Boolean found = false;

        guess.setChosen(true);
        for (Letter letter : word) {
            if (guess.equals(letter)) {
                letter.setChosen(true);
                found = true;
            }
        }

        if (!found) {
          this.incNumMisses();
        }

        this.checkForEndOfGame();
    }

    public List<Letter> getWord() {
        return word;
    }

    public void setWord(List<Letter> word) {
        this.word = word;
    }

    public List<Letter> getLetterAll() {
        return letterAll;
    }

    public void setLetterAll(List<Letter> letterAll) {
        this.letterAll = letterAll;
    }

    public HangmanGameStatus getStatus() {
        return status;
    }

    public void setStatus(HangmanGameStatus status) {
        this.status = status;
    }

    public Long getNumMisses() {
        return numMisses;
    }

    public void setNumMisses(Long numMisses) {
        this.numMisses = numMisses;
    }

}
