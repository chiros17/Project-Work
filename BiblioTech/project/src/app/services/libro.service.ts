import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, of, throwError } from 'rxjs';
import { LibroModel, LibroForm } from '../models/libro.model';

@Injectable({
  providedIn: 'root'
})
export class LibroService {
  private apiUrl = 'http://localhost:8082/api/v1/libro';

  constructor(private http: HttpClient) {}
  /*
  getAllLibri(): Observable<LibroModel[]> {
    // Dati mock per testing
    const mockLibri: Libro[] = [
      {
        id: 1,
        uuid: 'libro-uuid-1',
        titolo: 'Il Nome della Rosa',
        autore: 'Umberto Eco',
        editore: 'Bompiani',
        quantita: 5,
        prezzo: 15.99
      },
      {
        id: 2,
        uuid: 'libro-uuid-2',
        titolo: 'Cien Años de Soledad',
        autore: 'Gabriel García Márquez',
        editore: 'Mondadori',
        quantita: 3,
        prezzo: 12.50
      }
    ];

    return of(mockLibri);
  }
  */

  getAllLibri(): Observable<LibroModel[]> {
    return this.http.get<LibroModel[]>(this.apiUrl); // Rimuovi 'of(mockLibri)'
  }

  getLibroById(uuid: string): Observable<LibroModel> {
    return this.http.get<LibroModel>(`${this.apiUrl}/${uuid}`);
  }

  createLibro(libro: LibroForm): Observable<LibroModel> {
    return this.http.post<LibroModel>(`${this.apiUrl}`, libro).pipe(
      catchError(err => {console.error("Messaggio errore, errore service", err, libro);
        return throwError(() => new Error("Messaggio di errore casuale"))
      })
    );
  }

  updateLibro(libro: LibroForm): Observable<LibroModel> {
    return this.http.put<LibroModel>(`${this.apiUrl}`, libro).pipe(
      catchError(err => {console.error("Messaggio errore, errore modifica", err, libro);
        return throwError(() => new Error("Messaggio di errore casuale"))
      })
    );
  }

  deleteLibro(uuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${uuid}`).pipe(
      catchError(err => {console.error("Messaggio errore, errore eliminazione", err, uuid);
        return throwError(() => new Error("Messaggio di errore casuale"))
      })
    );
  }
}