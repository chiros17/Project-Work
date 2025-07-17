import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    if (this.authService.isBibliotecario() || this.authService.isStudente()) {
      //this.router.navigate(['/dashboard']);
      console.log('sto qui');
      return true;
    }
    console.log("non sono bibliotecario");
    //this.router.navigate(['/home']);
    return false;
  }
}