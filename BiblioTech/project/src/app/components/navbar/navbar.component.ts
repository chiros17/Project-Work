import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <nav class="navbar">
      <div class="navbar-container">
        <div class="navbar-brand">
          <a routerLink="/" class="brand-link">
            <span class="brand-text">BiblioTech</span>
          </a>
        </div>
        
        <div class="navbar-nav">
          <div *ngIf="!currentUser" class="nav-links">
            <a routerLink="/login" class="nav-link">Login</a>
          </div>
          
          <div *ngIf="currentUser" class="nav-links">
            <span class="user-info">Benvenuto, {{ currentUser.nome }}</span>
            <a routerLink="/dashboard" class="nav-link">Dashboard</a>
            
            <div *ngIf="isBibliotecario()" class="admin-links">
              <a routerLink="/admin/utenti" class="nav-link">Gestione Utenti</a>
              <a routerLink="/admin/libri" class="nav-link">Gestione Libri</a>
              <a routerLink="/admin/prestiti" class="nav-link">Gestione Prestiti</a>
            </div>
            
            <button (click)="logout()" class="logout-btn">Logout</button>
          </div>
        </div>
      </div>
    </nav>
  `,
  styles: [`
    .navbar {
      background-color: var(--color-mattone);
      color: white;
      padding: 1rem 0;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    
    .navbar-container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 0 1rem;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    
    .brand-link {
      text-decoration: none;
      color: white;
    }
    
    .brand-text {
      font-size: 1.5rem;
      font-weight: bold;
    }
    
    .nav-links {
      display: flex;
      align-items: center;
      gap: 1rem;
    }
    
    .nav-link {
      color: white;
      text-decoration: none;
      padding: 0.5rem 1rem;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }
    
    .nav-link:hover {
      background-color: rgba(255,255,255,0.1);
    }
    
    .user-info {
      font-size: 0.9rem;
      opacity: 0.9;
    }
    
    .admin-links {
      display: flex;
      gap: 0.5rem;
    }
    
    .logout-btn {
      background-color: var(--color-bordeaux);
      color: white;
      border: none;
      padding: 0.5rem 1rem;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    
    .logout-btn:hover {
      background-color: var(--color-bordeaux-dark);
    }
  `]
})
export class NavbarComponent implements OnInit {
  currentUser: User | null = null;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  isBibliotecario(): boolean {
    return this.authService.isBibliotecario();
  }
}