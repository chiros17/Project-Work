export interface Prestito {
  id: number;
  uuid: string;
  uuidUtente: string;
  uuidLibro: string;
  dataRestituzione: string;
  isRestituito: boolean;
  
  // Campi calcolati per UI
  nomeUtente?: string;
  titoloLibro?: string;
}

export interface PrestitoForm {
  uuidUtente: string;
  uuidLibro: string;
  dataRestituzione: string;
}