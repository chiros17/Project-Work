import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { Libro } from './components/libro/libro';
import { MieiPrestiti } from './components/miei-prestiti/miei-prestiti';
import { StudenteCatalogo } from './components/studente.catalogo/studente.catalogo';
import { GestioneUtenti } from './components/gestione-utenti/gestione-utenti';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent, 
    canActivate: [AuthGuard, RoleGuard]
  },
  { 
    path: 'studente_catalogo', 
    component: StudenteCatalogo,
  },
  { 
    path: 'gestione_utenti', 
    component: GestioneUtenti,
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