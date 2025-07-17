import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LibroService } from '../../services/libro.service';
import { PrestitoService } from '../../services/prestito.service';
import { User } from '../../models/user.model';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="dashboard-container">
      <div class="dashboard-header">
        <h1>Dashboard</h1>
        <p>Benvenuto, {{ currentUser?.nome }}</p>
      </div>
      
      <div class="stats-grid">
        <div class="stat-card">
          <h3>Libri Totali</h3>
          <div class="stat-number">{{ totalLibri }}</div>
        </div>
        <div class="stat-card">
          <h3>Prestiti Attivi</h3>
          <div class="stat-number">{{ prestitiAttivi }}</div>
        </div>
        <div class="stat-card">
          <h3>Il Tuo Ruolo</h3>
          <div class="stat-text">{{ currentUser?.ruolo }}</div>
        </div>
      </div>
      
      <div class="actions-grid">
        <div  *ngIf="isBibliotecario()" class="action-card">
          <h3>Catalogo Libri</h3>
          <p>Esplora il catalogo completo dei libri disponibili</p>
          <a routerLink="/libro" class="btn btn-primary">Visualizza Catalogo</a>
        </div>
        
        <div *ngIf="isBibliotecario()" class="action-card">
          <h3>Gestione Utenti</h3>
          <p>Gestisci gli utenti del sistema</p>
          <a routerLink="/gestione_utenti" class="btn btn-primary">Gestisci Utenti</a>
        </div>
        
        <div *ngIf="isStudente()" class="action-card">
          <h3>Catalogo Libri</h3>
          <p>Sfoglia il catalogo</p>
          <a routerLink="/studente_catalogo" class="btn btn-primary">Catalogo</a>
        </div>
        
        <div  *ngIf="isStudente()" class="action-card">
          <h3>I Miei Prestiti</h3>
          <p>Visualizza i tuoi prestiti attivi e la cronologia</p>
          <a routerLink="/miei-prestiti" class="btn btn-primary">Visualizza Prestiti</a>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .dashboard-container {
      padding: 2rem;
      max-width: 1200px;
      margin: 0 auto;
    }
    
    .dashboard-header {
      margin-bottom: 2rem;
    }
    
    .dashboard-header h1 {
      color: var(--color-mattone);
      margin-bottom: 0.5rem;
    }
    
    .dashboard-header p {
      color: #666;
      font-size: 1.1rem;
    }
    
    .stats-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 1.5rem;
      margin-bottom: 3rem;
    }
    
    .stat-card {
      background: white;
      padding: 1.5rem;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      text-align: center;
    }
    
    .stat-card h3 {
      color: var(--color-mattone);
      margin-bottom: 1rem;
      font-size: 1rem;
    }
    
    .stat-number {
      font-size: 2rem;
      font-weight: bold;
      color: var(--color-bordeaux);
    }
    
    .stat-text {
      font-size: 1.2rem;
      font-weight: 500;
      color: var(--color-mattone);
    }
    
    .actions-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
      gap: 2rem;
    }
    
    .action-card {
      background: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    
    .action-card h3 {
      color: var(--color-mattone);
      margin-bottom: 1rem;
    }
    
    .action-card p {
      color: #666;
      margin-bottom: 1.5rem;
      line-height: 1.6;
    }
  `]
})
export class DashboardComponent implements OnInit {
  currentUser: User | null = null;
  totalLibri = 0;
  prestitiAttivi = 0;

  constructor(
    private authService: AuthService,
    private libroService: LibroService,
    private prestitoService: PrestitoService
  ) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
    });
    
    this.loadStats();
  }

  private loadStats(): void {
    forkJoin({
      libri: this.libroService.getAllLibri(),
      prestiti: this.prestitoService.getAllPrestiti()
    }).subscribe({
      next: ({ libri, prestiti }) => {
        this.totalLibri = libri.length;
        this.prestitiAttivi = prestiti.filter(p => !p.isRestituito).length;
      },
      error: (error) => console.error('Errore caricamento stats:', error)
    });
  }

  isBibliotecario(): boolean {
    return this.authService.isBibliotecario();
  }
  isStudente(): boolean {
    return this.authService.isStudente();
  }
}