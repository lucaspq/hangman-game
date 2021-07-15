import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Letter } from '../models/letter';

@Injectable({
  providedIn: 'root'
})
export class HangmanService {

  REST_API: string = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({ 
      'Access-Control-Allow-Origin':'*',
    })
  };

  constructor(
    private http: HttpClient
  ) { }
  
  // Create a new Hangman Game
  newHangmanGame(): Observable<any> {
    let API_URL = `${this.REST_API}/newHangmanGame`;
    return this.http.get(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Try a letter for Hangman Game
  tryLetter(c: string): Observable<any> {
    let API_URL = `${this.REST_API}/tryLetter/${c}`;
    return this.http.get(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Return status of Hangman Game
  status(): Observable<any> {
    let API_URL = `${this.REST_API}/status`;
    return this.http.get(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Return status of Hangman Game
  getLetterAll(): Observable<any> {
    let API_URL = `${this.REST_API}/getLetterAll`;
    return this.http.get<Letter[]>(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Return status of Hangman Game
  getWord(): Observable<any> {
    let API_URL = `${this.REST_API}/getWord`;
    return this.http.get<Letter[]>(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }

  guessesRemaining(): Observable<any> {
    let API_URL = `${this.REST_API}/guessesRemaining`;
    return this.http.get(API_URL)
      .pipe(
        catchError(this.handleError)
      );
  }


  // Error 
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Handle client error
      errorMessage = error.error.message;
    } else {
      // Handle server error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
  
}
