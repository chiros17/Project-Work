export interface LibroModel {
  id: number;
  uuid: string;
  titolo: string;
  autore: string;
  quantita: number;
  prezzo: number;
}

export interface LibroForm {
  uuid?: string;
  titolo: string;
  autore: string;
  quantita: number;
  prezzo: number;
}