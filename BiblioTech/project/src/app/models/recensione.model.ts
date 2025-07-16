export interface Recensione {
  id: number;
  uuid: string;
  uuidUtente: string;
  uuidLibro: string;
  voto: number;
  commento: string;
  
  // Campi calcolati per UI
  nomeUtente?: string;
  titoloLibro?: string;
}

export interface RecensioneForm {
  uuidUtente: string;
  uuidLibro: string;
  voto: number;
  commento: string;
}