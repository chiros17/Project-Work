import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LibroModel, LibroForm } from '../../models/libro.model';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-studente.catalogo',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './studente.catalogo.html',
  styleUrl: './studente.catalogo.css'
})
export class StudenteCatalogo implements OnInit{

  libri: LibroModel[] = [];
    selectedLibro: LibroModel | null = null; // Per la modifica o visualizzazione dettagli
    newLibro: LibroForm = {  // Modello per il form di creazione
      uuid: '',
      titolo: '',
      autore: '',
      quantita: 0,
      prezzo: 0
    };

    constructor(private libroService: LibroService) {}
    
      ngOnInit(): void {
        this.loadLibri(); // Carica i libri all'avvio del componente
      }

      loadLibri(): void {
        this.libroService.getAllLibri().subscribe({
          next: (data) => {
            this.libri = data;
            console.log('Libri caricati:', this.libri);
          },
          error: (err) => console.error('Errore durante il caricamento dei libri:', err)
        });
      }

      // READ (Carica un libro specifico per ID - utile per i dettagli o la modifica)
      loadLibroById(uuid: string): void {
        this.libroService.getLibroById(uuid).subscribe({
          next: (data) => {
            this.selectedLibro = data;
            console.log('Libro selezionato:', this.selectedLibro);
          },
          error: (err) => console.error(`Errore durante il caricamento del libro ${uuid}:`, err)
        });
      }

}
