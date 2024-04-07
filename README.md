### Conturi deja existente

- **Admin**
    - Email: admin@gmail.com
    - Parolă: adminpass
- **Student**
    - Email: rares@gmail.com
    - Parolă: parola

### Actiuni/Interogari

1. **Creare client**
    - Utilizatorul poate crea un cont de client în aplicație.

2. **Logare și delogare client**
    - Clientul poate să se autentifice în contul său și să se delogheze când termină sesiunea.

3. **Afisare abonamente STB și Metrorex active**
    - Utilizatorul poate vedea lista abonamentelor active pentru STB și Metrorex.

4. **Plata abonamentelor expirate**
    - Clientul poate efectua plăți pentru reînnoirea abonamentelor care au expirat.

5. **Cumpararea de abonamente pentru transportul in comun**
    - Utilizatorul poate achiziționa noi abonamente pentru transportul în comun, cum ar fi cele pentru STB sau Metrorex.

6. **Adaugarea unui card**
    - Clientul poate adăuga un nou card în contul său pentru a efectua plăți online.

7. **Inlocuirea unui card**
    - Clientul poate înlocui un card existent cu un altul nou, în caz de pierdere sau expirare.

8. **Adaugarea de studenti**
    - Adminul poate adăuga noi studenți în sistemul de gestionare a conturilor.

9. **Adaugarea de universitati si facultati**
    - Adminul poate introduce în baza de date noi universități și facultăți disponibile.

10. **Vizualizarea tuturor studentilor si adminilor**
    - Adminul poate accesa lista completă a tuturor studenților și adminilor înregistrați în sistem.

11. **Vizualizarea unui student sau admin**
    - Adminul poate accesa detaliile complete ale unui student sau admin specific, pentru a verifica informațiile și istoricul contului.

### Obiecte si Clase din Proiect

1. **Clase de Model**
   - `User` reprezinta un user in sistem
     - `Student`: Subclasa a `User` pentru studenti.
     - `Admin`: Subclasa a `User` pentru administratorii sistemului.
   - `Universitate`: Reprezinta o universitate in sistem.
   - `Facultate`: Reprezinta o facultate in cadrul unei universitati.
   - `Abonament`: Clasa abstracta pentru diverse tipuri de abonamente.
      - `AbonamentSTB`: Subclasa a `Abonament` pentru abonamentele STB.
      - `AbonamentMetrorex`: Subclasa a `Abonament` pentru abonamentele Metrorex.
   - `Card`: Reprezinta un card de plata asociat unui utilizator.
      - `DebitCard`: Subclasa a `Card` pentru cardurile de debit.
      - `CreditCard`: Subclasa a `Card` pentru cardurile de credit.
   - `Plata`: Reprezinta o tranzactie de plata inregistrata in sistem.

2. **Pachete si Servicii**
   - `DAO`: Pachetul care contine clasele de acces la date.
      - `AbonamentSTBDAO`: Clasa care gestioneaza operatiile CRUD pentru abonamentele STB
      - `AbonamentMetrorexDAO`: Clasa care gestioneaza operatiile CRUD pentru abonamentele Metrorex
      - `UserDAO`: Clasa care gestioneaza operatiile CRUD pentru utilizatori.
      - `UniversityDAO`: Clasa care gestioneaza operatiile CRUD pentru universitati.
      - `FacultyDAO`: Clasa care gestioneaza operatiile CRUD pentru facultati.
      - `CardDAO`: Clasa care gestioneaza operatiile CRUD pentru carduri.
      - `PlataDAO`: Clasa care gestioneaza operatiile CRUD pentru plati.

   - `Service`: Pachetul care contine clasele de servicii.
      - `AdminService`: Clasa pentru gestionarea operatiunilor administratorilor. 
      - `AuthenticationService`: Clasa pentru autentificarea utilizatorilor.
      - `CardService`: Clasa pentru gestionarea cardurilor utilizatorilor.
      - `PassService`: Clasa pentru gestionarea abonamentelor.
      - `PayService`: Clasa pentru efectuarea platilor.
      - `UserService`: Clasa pentru operatiile legate de utilizatori.
      - `UniversityService`: Clasa pentru operatiile legate de universitati si facultati
      - `Menu`: Pachetul care contine clasele pentru meniul aplicatiei.
         - `UserMenu`: Clasa pentru meniul administratorului si a studentului