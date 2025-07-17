import { Component, OnInit } from '@angular/core';
import { User, UserForm, VediUtente } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-gestione-utenti',
  imports: [CommonModule, FormsModule],
  templateUrl: './gestione-utenti.html',
  styleUrl: './gestione-utenti.css'
})
export class GestioneUtenti{
  utenti: VediUtente[] = [];
  errorMessage: string | null = null;

  

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.loadUtenti();
  }

  loadUtenti(): void {
    this.authService.getAllUtenti().subscribe({
      next: (data) => {
        this.utenti = data;
        this.errorMessage = null; // Resetta l'errore in caso di successo
      },
      error: (error) => {
        console.error('Si è verificato un errore durante il caricamento degli utenti:', error);
        this.errorMessage = 'Impossibile caricare gli utenti. Riprova più tardi.';
      }
    });
  }

}
