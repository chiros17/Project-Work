import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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

  getLibroById(id: number): Observable<LibroModel> {
    return this.http.get<LibroModel>(`${this.apiUrl}/${id}`);
  }

  createLibro(libro: LibroForm): Observable<LibroModel> {
    return this.http.post<LibroModel>(`${this.apiUrl}/`, libro);
  }

  updateLibro(id: number, libro: LibroForm): Observable<LibroModel> {
    return this.http.put<LibroModel>(`${this.apiUrl}/${id}`, libro);
  }

  deleteLibro(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}