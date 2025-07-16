import { Component, inject, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserLogin } from '../../models/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="login-container">
      <div class="login-card">
        <h2>Accesso BiblioTech</h2>
        
        <form (ngSubmit)="onLogin()" #loginForm="ngForm">
          <div class="form-group">
            <label for="email">Email</label>
            <input
              type="text"
              id="username"
              name="username"
              [(ngModel)]="credentials.username"
              required
              #username="ngModel"
              class="form-input"
            />
            <div *ngIf="username.invalid && username.touched" class="error-message">
              Username richiesta
            </div>
          </div>
          
          <div class="form-group">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              [(ngModel)]="credentials.password"
              required
              #password="ngModel"
              class="form-input"
            />
            <div *ngIf="password.invalid && password.touched" class="error-message">
              Password richiesta
            </div>
          </div>
          
          <button
            type="submit"
            [disabled]="loginForm.invalid || loading"
            class="btn btn-primary"
          >
            {{ loading ? 'Accesso in corso...' : 'Accedi' }}
          </button>
        </form>
        
        <div class="demo-accounts">
          <p>Account demo disponibili:</p>
          <button (click)="useDemo('admin')" class="demo-btn">Bibliotecario</button>
          <button (click)="useDemo('student')" class="demo-btn">Studente</button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .login-container {
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: var(--color-panna);
    }
    
    .login-card {
      background: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
      width: 100%;
      max-width: 400px;
    }
    
    .login-card h2 {
      text-align: center;
      color: var(--color-mattone);
      margin-bottom: 2rem;
    }
    
    .form-group {
      margin-bottom: 1.5rem;
    }
    
    .form-group label {
      display: block;
      margin-bottom: 0.5rem;
      color: #333;
      font-weight: 500;
    }
    
    .form-input {
      width: 100%;
      padding: 0.75rem;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 1rem;
      transition: border-color 0.3s ease;
    }
    
    .form-input:focus {
      outline: none;
      border-color: var(--color-mattone);
    }
    
    .error-message {
      color: #dc3545;
      font-size: 0.875rem;
      margin-top: 0.25rem;
    }
    
    .demo-accounts {
      margin-top: 2rem;
      text-align: center;
      padding-top: 2rem;
      border-top: 1px solid #eee;
    }
    
    .demo-accounts p {
      margin-bottom: 1rem;
      color: #666;
    }
    
    .demo-btn {
      background-color: #f8f9fa;
      border: 1px solid #ddd;
      padding: 0.5rem 1rem;
      margin: 0 0.5rem;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    
    .demo-btn:hover {
      background-color: #e9ecef;
    }
  `]
})
export class LoginComponent {
  credentials: UserLogin = {
    username: '',
    password: ''
  };
  
  loading = false;

  constructor(
    
    private router: Router
  ) {}

  private authService = inject(AuthService);

  onLogin(): void {
    this.loading = true;
    console.log('dentro onLogin');
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.loading = false;
        console.log('prima di premere il tasto');
        console.log(response.user);
        if (response.user) {
          this.router.navigate(['/dashboard']);
          console.log('tasto premuto');
        }
      },
      error: (error) => {
        this.loading = false;
        console.error('Errore login:', error);
      }
    });
  }

  useDemo(type: 'admin' | 'student'): void {
    this.credentials = {
      username: type === 'admin' ? 'admin' : '',
      password: 'password123'
    };
  }
}