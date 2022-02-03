# UserVaultApp
Applicazione in Spring dove si possono registrare e loggare utenti e per ogni utente li viene creato e associato
un vault o inventario, e su questo vault li possono aggiungere e rimuovere items, ogni utente e associato pure a un role 
e dipendendo del rol questo utente puo fare o non fare alcune funzioni sull app o accedere a certe risorse dell app. tutto si lavora in
un database di MySQL e spring data JPA e lavora con Spring securiry e JWT per la securita dell applicazione.
Ricordate modificare il file application.properties con le vostro credenziali e il nome del vostro database e ricordate 
di creare un nuovo database, nel mio caso il dababase che ho create si chiama *appvault*.
Ricordate pure che e solo il back-end allora per provare tutto dovete usare Postman e tutti i path per fare funzionare l'app
le trovate nei file Controller del programma.
