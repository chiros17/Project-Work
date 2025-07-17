# Project-Work BiblioTech

## Premessa

Il progetto BiblioTech nasce con l’obiettivo di facilitare e potenziare l’accesso ai servizi bibliotecari attraverso una soluzione digitale moderna ed efficiente. Non vuole sostituire il piacere dell’esperienza fisica in biblioteca, ma intende valorizzare l’integrazione tecnologica per agevolare l’operato del personale bibliotecario e migliorare l’esperienza dell’utente finale.

**BiblioTech** è un sistema applicativo per la gestione di una biblioteca digitale.

## Obiettivo e contesto

La committenza ha richiesto la realizzazione di un'applicazione web capace di gestire una biblioteca moderna in modo ottimizzato e accessibile da remoto. In risposta, abbiamo progettato un sistema distribuito in grado di:

- Centralizzare e digitalizzare la gestione di prestiti, recensioni e disponibilità dei libri.
- Rendere il sistema disponibile agli utenti 24/7 tramite interfaccia web.
- Separare in maniera chiara le responsabilità tra i diversi attori del sistema (utenti e bibliotecari).
- Scalare facilmente con il crescere del numero di funzionalità e di richieste.

## Architettura del sistema

Il sistema è stato progettato secondo un'architettura a microservizi, composta da tre macro-componenti:

- **Front-end:** interfaccia utente sviluppata in Angular.
- **Back-end:** serie di microservizi Spring Boot implementati in linguaggio Java responsabili della logica applicativa e dell'accesso ai dati.
- **Database:** gestione dei dati mediante H2, un database leggero e integrabile facilmente nel progetto.

Questa architettura consente una maggiore manutenibilità, scalabilità e isolamento dei problemi.

## Funzionalità principali

BiblioTech consente l’accesso tramite login per due tipologie di utenti: **studenti** e **bibliotecari**.

### Gli studenti possono:

- Consultare il catalogo dei libri disponibili  
- Inserire recensioni sui volumi  
- Prenotare libri attualmente non disponibili  
- Richiedere il prestito dei libri  

### I bibliotecari, con ruolo di amministratori, possono:

- Inserire nuovi libri nel sistema  
- Visualizzare l’elenco degli studenti registrati  
- Monitorare i prestiti effettuati  

---

## Database

### Tecnologia scelta

Per lo sviluppo è stato adottato **H2 Database**, una soluzione relazionale leggera che permette:

- Integrazione immediata come dipendenza Maven.
- Modalità in-memory o su file, utile per test locali o progetti demo.
- Nessun requisito di installazione o configurazione manuale.

### Console H2

Attraverso la console web di H2 è possibile eseguire interrogazioni dirette sulle tabelle. Le informazioni essenziali per la connessione sono:

- **Driver JDBC:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:file:${user.home}/db/nome_db`
- **Username:** `sa`
- **Password:** ` `

La console consente di visualizzare lo schema delle tabelle, il contenuto e l’esito delle query SQL in tempo reale.

---

## Back-end

### Linguaggi e strumenti

L’intero backend è stato sviluppato in **Java**, utilizzando **Spring Boot** come framework principale. Le ragioni principali di questa scelta sono:

- Capacità di gestione delle dipendenze.
- Integrazione nativa con librerie per il web, la persistenza e la sicurezza.
- Ecosistema maturo per lo sviluppo a microservizi.

Per la configurazione iniziale abbiamo utilizzato **Spring Initializr**, specificando:
- **Build Tool:** Maven
- **Packaging:** JAR
- **Versione Spring Boot:** 3.5.3

### Dipendenze principali

Ogni microservizio presenta un file `pom.xml` personalizzato con le seguenti dipendenze essenziali:

- **spring-boot-starter-web:** per lo sviluppo di controller REST e gestione HTTP.
- **spring-boot-devtools:** per hot-reload durante lo sviluppo.
- **lombok:** per la generazione automatica di costruttori, getter/setter, riducendo il codice boilerplate.
- **spring-boot-starter-data-jpa:** per la persistenza dei dati tramite JPA.
- **jakarta.validation:** per la validazione dei campi lato backend.
- **h2:** per il database integrato.
- **eureka-client:** per il discovery dinamico dei microservizi.

---

### Microservizi

Abbiamo suddiviso le funzionalità del sistema in più microservizi indipendenti. Ciascun microservizio è responsabile di un dominio specifico (es. utenti, libri, prestiti, recensioni, prenotazioni) e comunica con gli altri tramite API REST.

Questa suddivisione consente di:

- Separare le responsabilità funzionali.
- Ridurre l'impatto delle modifiche.
- Facilitare il debugging e il deploy.

---

### Struttura a livelli

Ogni microservizio è strutturato in **layer applicativi**, ciascuno con una responsabilità specifica:

#### Data Layer

Modella i dati tramite classi Java annotate con `@Entity`, definiamo getter, setter e costruttori con le annotazioni fornite da **lombok** `@Getter`, `@Setter`, `@NoArgsConstructor`, `@RequiredArgsConstructor`. Ogni attributo rappresenta una colonna della tabella nel database. Nello stesso layer implementiamo la repository in maniera dinamica grazie a `JpaRepository`. Quest'ultima ha lo scopo di fornire metodi che implementano le operazioni CRUD e possibilità di definire query personalizzate.

#### Business Layer

Gestisce la logica applicativa. Vengono modellati i **DTO (Data Transfer Object)** delle relative entity per aggiungere uno strato di sicurezza, nascondere le informazioni sensibili e per trasformare i dati tra modelli interni e output verso il client. Implementiamo il service, annotato con `@Service`, che riceve richieste dal controller e interagisce con il repository per eseguire le operazioni, inoltre si occupa della conversione da `model` a `DTO` per garantire sicurezza e chiarezza nella comunicazione.

#### Presentation Layer

Espone gli endpoint REST. Le classi sono annotate con `@RestController` e `@RequestMapping`. Le annotazioni come `@GetMapping`, `@PostMapping`, ecc... definiscono le operazioni disponibili via HTTP.  
È presente anche `@CrossOrigin` per consentire le richieste dal frontend (localhost:4200).

---

### Discovery Server

In un contesto a microservizi, è fondamentale permettere a ciascun servizio di localizzare dinamicamente gli altri. Per farlo, è stato implementato un microservizio separato chiamato `discovery-server`.

- L’annotazione `@EnableEurekaServer` abilita la funzionalità di service registry.
- Gli altri microservizi si registrano automaticamente al server tramite Eureka Client.
- Il `ServletInitializer.java` configura l'applicazione per l'esecuzione su un server esterno.

Il discovery server rappresenta un **registro centrale** che consente la comunicazione affidabile tra microservizi.

---

### API Gateway

Per evitare che il client debba conoscere ogni singolo microservizio e relativo indirizzo, abbiamo introdotto un **API Gateway**, ovvero un unico punto d’accesso centralizzato per tutte le richieste esterne.

Configurazione principale:

- Porta di avvio: `8080`
- Collegamento al discovery server: `eureka.client.service-url.defaultZone`
- Le rotte vengono definite con `id`, `uri` e `predicates` (es. `Path=/api/v1/utenti/**`)

Questo approccio semplifica la gestione dei flussi in ingresso e centralizza eventuali policy di sicurezza, logging e throttling.

---

## HTTP e comunicazione client-server

Le interazioni tra frontend (Angular) e backend (Spring Boot) avvengono tramite **HTTP**, il protocollo standard di comunicazione sul Web.

Una richiesta HTTP consiste in:

- Un **metodo** (GET, POST, PUT, DELETE) che specifica l’azione da compiere.
- Un **endpoint** che rappresenta la risorsa da contattare.
- Un **payload** opzionale contenente dati (per es. nel caso di POST o PUT).

Le risposte includono uno **status code** (es. 200 OK, 404 Not Found) e, se previsto, un corpo contenente i dati richiesti o messaggi di errore.

---

## Le API RESTful nel progetto

L’intero sistema backend espone **API RESTful**, progettate per garantire una comunicazione chiara e strutturata tra client e server.

### Caratteristiche delle API RESTful

- **Stateless:** ogni richiesta è indipendente dalle precedenti.
- **Risorse:** le entità (libri, utenti, recensioni, ecc.) sono trattate come risorse con URI univoci.
- **Verbi HTTP coerenti:**  
  - `GET` per ottenere dati  
  - `POST` per creare una risorsa  
  - `PUT` per aggiornare  
  - `DELETE` per rimuovere  
- **Formati standardizzati:** le richieste e le risposte avvengono in formato JSON.
- **Chiarezza degli endpoint:** es. `/api/v1/libri`, `/api/v1/utenti/{id}`

Ogni controller definisce gli endpoint necessari a interagire con la risorsa di competenza. Questo approccio rende l’applicazione facilmente integrabile con altri sistemi e favorisce una separazione netta tra logica di business e presentazione.

---

## Autori e ruoli

- **Alessio Chirone** – Back-end  
- **Jacopo Filippi** – Front-end  
- **Bruno Integlia** – Back-end

