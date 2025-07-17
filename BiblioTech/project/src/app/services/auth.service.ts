import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { User, UserLogin, AuthResponse, VediUtente } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/api/v1/utenti/login';
  private apiUrl2 = 'http://localhost:8081/api/v1/utenti';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadCurrentUser();
  }

  private loadCurrentUser(): void {
    try {
      const userJson = localStorage.getItem('user');
      console.log('userJson:', userJson);
      
      if (userJson && userJson !== 'undefined' && userJson !== 'null') {
        const user: User = JSON.parse(userJson);
        this.currentUserSubject.next(user);
      } else {
        this.currentUserSubject.next(null);
      }
    } catch (error) {
      console.error('Errore parsing user data:', error);
      this.currentUserSubject.next(null);
      // Pulisci localStorage se i dati sono corrotti
      localStorage.removeItem('user');
      localStorage.removeItem('token');
    }
  }

  

  login(credentials: UserLogin): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.apiUrl, credentials).pipe(
      tap(response => {
        //const utenti: UserLogin = ;
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.currentUserSubject.next(response.user);
      }),
      catchError(error => {
        console.error('Errore login:', error);
        return throwError(() => error); // Usa throwError per un errore reattivo
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
  }

  isLoggedIn(): boolean {
    const user = this.getCurrentUser();
    const token = this.getToken();
    return !!(user && token);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isBibliotecario(): boolean {
    const user = this.getCurrentUser();
    return user?.ruolo === 'BIBLIOTECARIO';
  }

  isStudente(): boolean {
    const user = this.getCurrentUser();
    console.log("è studente", user?.ruolo);
    return user?.ruolo === 'STUDENTE';
  }

  getAllUtenti(): Observable<VediUtente[]> {
    // Potresti voler aggiungere un'intestazione di autorizzazione se l'API lo richiede
    // const headers = new HttpHeaders().set('Authorization', `Bearer ${this.getToken()}`);
    // return this.http.get<VediUtente[]>(this.apiUrl2, { headers: headers }).pipe(

    return this.http.get<VediUtente[]>(this.apiUrl2).pipe(
      tap(utenti => console.log('Utenti recuperati:', utenti)),
      catchError(error => {
        console.error('Errore durante il recupero degli utenti:', error);
        return throwError(() => error);
      })
    );
  }
}