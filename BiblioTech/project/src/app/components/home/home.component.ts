import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="home-container">
      <div class="hero-section">
        <h1 class="hero-title">Benvenuto in BiblioTech</h1>
        <p class="hero-subtitle">Sistema di Gestione Bibliotecario Moderno</p>
        <div class="hero-actions">
          <a routerLink="/login" class="btn btn-primary">Accedi</a>
        </div>
      </div>
      
      <div class="features-section">
        <div class="container">
          <h2>Funzionalità Principali</h2>
          <div class="features-grid">
            <div class="feature-card">
              <h3>Gestione Catalogo</h3>
              <p>Organizza e gestisci il catalogo libri con facilità</p>
            </div>
            <div class="feature-card">
              <h3>Sistema Prestiti</h3>
              <p>Traccia prestiti e restituzioni in tempo reale</p>
            </div>
            <div class="feature-card">
              <h3>Recensioni</h3>
              <p>Permetti agli utenti di recensire i libri</p>
            </div>
            <div class="feature-card">
              <h3>Prenotazioni</h3>
              <p>Sistema avanzato di prenotazione libri</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .home-container {
      min-height: 100vh;
    }
    
    .hero-section {
      background: linear-gradient(135deg, var(--color-mattone), var(--color-bordeaux));
      color: white;
      text-align: center;
      padding: 4rem 2rem;
    }
    
    .hero-title {
      font-size: 3rem;
      margin-bottom: 1rem;
      font-weight: 700;
    }
    
    .hero-subtitle {
      font-size: 1.25rem;
      margin-bottom: 2rem;
      opacity: 0.9;
    }
    
    .hero-actions {
      display: flex;
      justify-content: center;
      gap: 1rem;
    }
    
    .features-section {
      padding: 4rem 2rem;
      background-color: var(--color-panna);
    }
    
    .container {
      max-width: 1200px;
      margin: 0 auto;
    }
    
    .features-section h2 {
      text-align: center;
      margin-bottom: 3rem;
      color: var(--color-mattone);
    }
    
    .features-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 2rem;
    }
    
    .feature-card {
      background: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
      text-align: center;
    }
    
    .feature-card h3 {
      color: var(--color-mattone);
      margin-bottom: 1rem;
    }
    
    .feature-card p {
      color: #666;
      line-height: 1.6;
    }
  `]
})
export class HomeComponent {}