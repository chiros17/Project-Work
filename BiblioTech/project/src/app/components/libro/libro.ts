import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from "@angular/router";
import { LibroModel, LibroForm } from '../../models/libro.model';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-libro',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './libro.html',
  styleUrl: './libro.css'
})
export class Libro implements OnInit {
  

  libri: LibroModel[] = [];
  selectedLibro: LibroModel | null = null; // Per la modifica o visualizzazione dettagli
  newLibro: LibroForm = {  // Modello per il form di creazione
    uuid: '',
    titolo: '',
    autore: '',
    quantita: 0,
    prezzo: 0
  };

  // Variabile per gestire la visibilità del form di modifica
  showEditForm: boolean = false;

  constructor(private libroService: LibroService) {}

  ngOnInit(): void {
    this.loadLibri(); // Carica i libri all'avvio del componente
  }

  addLibro(): void {
    console.log(this.newLibro);
    this.libroService.createLibro(this.newLibro).subscribe({
      next: (libroAggiunto) => {
        console.log('Libro aggiunto con successo:', libroAggiunto);
        this.libri.push(libroAggiunto); // Aggiungi il nuovo libro all'array locale
        this.resetNewLibroForm(); // Pulisci il form
        // this.loadLibri(); // Puoi anche ricaricare tutti i libri per assicurarti la consistenza
      },
      error: (err) => console.error('Errore durante l\'aggiunta del libro:', err, this.newLibro)
    });
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
        this.showEditForm = true; // Mostra il form di modifica
        console.log('Libro selezionato:', this.selectedLibro);
      },
      error: (err) => console.error(`Errore durante il caricamento del libro ${uuid}:`, err)
    });
  }

  // UPDATE (Aggiorna un libro esistente)
  updateLibro(): void {
    if (this.selectedLibro && this.selectedLibro.id) {
      // Nota: Potresti voler mappare selectedLibro a LibroForm se le interfacce sono diverse
      const libroToUpdate: LibroForm = {
        uuid: this.selectedLibro.uuid,
        titolo: this.selectedLibro.titolo,
        autore: this.selectedLibro.autore,
        quantita: this.selectedLibro.quantita,
        prezzo: this.selectedLibro.prezzo
      };

      this.libroService.updateLibro(this.selectedLibro.uuid, libroToUpdate).subscribe({
        next: (libroAggiornato) => {
          console.log('Libro aggiornato con successo:', libroAggiornato);
          // Trova il libro nell'array e aggiornalo
          const index = this.libri.findIndex(l => l.id === libroAggiornato.id);
          if (index !== -1) {
            this.libri[index] = libroAggiornato;
          }
          this.selectedLibro = null; // Pulisci la selezione
          this.showEditForm = false; // Nascondi il form
          // this.loadLibri(); // Puoi anche ricaricare tutti i libri
        },
        error: (err) => console.error('Errore durante l\'aggiornamento del libro:', err)
      });
    }
  }

  // DELETE (Elimina un libro)
  deleteLibro(uuid: string): void {
    if (confirm('Sei sicuro di voler eliminare questo libro?')) {
      this.libroService.deleteLibro(uuid).subscribe({
        next: () => {
          console.log('Libro eliminato con successo:', uuid);
          this.libri = this.libri.filter(libro => libro.uuid !== uuid); // Rimuovi dall'array locale
        },
        error: (err) => console.error(`Errore durante l'eliminazione del libro ${uuid}:`, err)
      });
    }
  }


  editLibro(libro: LibroModel): void {
    this.selectedLibro = { ...libro }; // Crea una copia per evitare modifiche dirette all'oggetto nell'array
    this.showEditForm = true;
  }

  // Annulla la modifica e nasconde il form
  cancelEdit(): void {
    this.selectedLibro = null;
    this.showEditForm = false;
  }

  // Resetta il form per l'aggiunta di un nuovo libro
  resetNewLibroForm(): void {
    this.newLibro = {
      uuid: '',
      titolo: '',
      autore: '',
      quantita: 0,
      prezzo: 0
    };
  }

}
