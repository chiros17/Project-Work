export interface LibroModel {
  id: number;
  uuid: string;
  titolo: string;
  autore: string;
  copertina: string;
  quantita: number;
  prezzo: number;
}

export interface LibroForm {
  id: number;
  titolo: string;
  autore: string;
  quantita: number;
  prezzo: number;
}