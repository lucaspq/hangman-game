import { Component, OnInit } from '@angular/core';
import * as _ from 'underscore';
import { Letter } from '../models/letter';
import { HangmanService } from '../services/hangman.service';

@Component({
  selector: 'app-hangman',
  templateUrl: './hangman.component.html',
  styleUrls: ['./hangman.component.css']
})
export class HangmanComponent implements OnInit {

  // missesAllowed = 6;
  // secretWord: string;
  private _guessesRemaining: number;
  private _status: string;
  letter = { c: '', chosen: false};
  private _letters: Letter[] = [];
  private _word: Letter[] = [];

  constructor(
    private hangmanService: HangmanService
  ) { }

  ngOnInit(): void {
    this.newHangmanGame();
  }

  newHangmanGame(): void {
    this.hangmanService.newHangmanGame().subscribe( () => {
      console.log("A new Hangman Game was created!");
      this.update();
    });
  }
  
  try(guess) {
    this.hangmanService.tryLetter(guess).subscribe( () => {
      console.log("call hangmanService.tryLetter");
      this.update();
    });
    
  }

  update() {
    this.hangmanService.getLetterAll().subscribe( (response) => {
      this._letters = response;
      console.log("call hangmanService.getLetterAll()");
    });

    this.hangmanService.getWord().subscribe( (response) => {
      this._word = response;
      console.log("call hangmanService.getWord()");
    });

    this.hangmanService.status().subscribe( (response) => {
      this._status = response;
      console.log("call hangmanService.status()");
    });
    
    this.hangmanService.guessesRemaining().subscribe( (response) => {
      this._guessesRemaining = response;
      console.log("call hangmanService.guessesRemaining()");
    });
  }

  get letters() {
    return this._letters;
  }

  get secretWord() {
    return this._word;
  }

  get guessesRemaining() {
    return this._guessesRemaining;
  }

  get status() {
    return this._status;
  }
  
}
