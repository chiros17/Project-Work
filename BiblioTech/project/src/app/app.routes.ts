import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { BibliotecarioGuard } from './guards/bibliotecario.guard';
import { Libro } from './components/libro/libro';
import { MieiPrestiti } from './components/miei-prestiti/miei-prestiti';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent, 
    canActivate: [AuthGuard, BibliotecarioGuard]
  },
  {
        path:'libro',
        component: Libro
  },
  {
        path:'miei-prestiti',
        component: MieiPrestiti
  },
  { path: '**', redirectTo: '' }
];