import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../note';
import { AuthenticationService } from './authentication.service';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class NotesService {

  private notesUrl: string;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.notesUrl = 'http://localhost:3000/api/v1/notes';
  }

  getNotes(): Observable<Array<Note>> {
    return this.http.get<Array<Note>>(this.notesUrl, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    });
  }

  addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(this.notesUrl, note, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    });
  }

}
