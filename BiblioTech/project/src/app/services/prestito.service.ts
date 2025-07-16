import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Prestito, PrestitoForm } from '../models/prestito.model';

@Injectable({
  providedIn: 'root'
})
export class PrestitoService {
  private apiUrl = 'http://localhost:8083/api/v1/prestiti';

  constructor(private http: HttpClient) {}

  getAllPrestiti(): Observable<Prestito[]> {
    // Dati mock per testing
    const mockPrestiti: Prestito[] = [
      {
        id: 1,
        uuid: 'prestito-uuid-1',
        uuidUtente: 'user-uuid-123',
        uuidLibro: 'libro-uuid-1',
        dataRestituzione: '2024-02-15',
        isRestituito: false,
        nomeUtente: 'Mario Rossi',
        titoloLibro: 'Il Nome della Rosa'
      }
    ];

    return of(mockPrestiti);
  }

  getPrestitoById(id: number): Observable<Prestito> {
    return this.http.get<Prestito>(`${this.apiUrl}/${id}`);
  }

  createPrestito(prestito: PrestitoForm): Observable<Prestito> {
    return this.http.post<Prestito>(`${this.apiUrl}/`, prestito);
  }

  updatePrestito(id: number, prestito: Partial<Prestito>): Observable<Prestito> {
    return this.http.put<Prestito>(`${this.apiUrl}/${id}`, prestito);
  }

  deletePrestito(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}