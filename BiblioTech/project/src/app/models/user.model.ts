export interface User {
  id: number;
  uuid: string;
  nome: string;
  email: string;
  username: string;
  password: string;
  ruolo: 'BIBLIOTECARIO' | 'STUDENTE';
}

export interface UserLogin {
  username: string;
  password: string;
}

export interface AuthResponse {
  user: User;
  token: string;
}

export interface UserForm {
  nome: string;
  //email: string;
  username: string;
  password: string;
  ruolo: 'BIBLIOTECARIO' | 'STUDENTE';
}

export interface VediUtente {
  nome: string;
  username: string;
}