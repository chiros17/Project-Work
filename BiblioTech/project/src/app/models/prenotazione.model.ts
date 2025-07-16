export interface Prenotazione {
  id: number;
  uuid: string;
  uuidUtente: string;
  uuidLibro: string;
  dataPrenotazione: string;
  
  // Campi calcolati per UI
  nomeUtente?: string;
  titoloLibro?: string;
}

export interface PrenotazioneForm {
  uuidUtente: string;
  uuidLibro: string;
}