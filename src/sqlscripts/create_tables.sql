-- Tabela Useri
CREATE TABLE Useri (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       email_address VARCHAR(255) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);
CREATE TABLE Universitati (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              nume VARCHAR(255) NOT NULL,
                              adresa TEXT,
                              email VARCHAR(255)
);

-- Tabela Facultati
CREATE TABLE Facultati (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           nume VARCHAR(255) NOT NULL,
                           adresa TEXT,
                           email VARCHAR(255),
                           universitate_id INT NOT NULL,
                           FOREIGN KEY (universitate_id) REFERENCES Universitati(id)
);

-- Tabela Admini
CREATE TABLE Admini (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        adminRole VARCHAR(50) NOT NULL,
                        nume VARCHAR(255) NOT NULL,
                        FOREIGN KEY (id) REFERENCES Useri(id)
);
-- Tabela Carduri
CREATE TABLE Carduri (
                         id_card INT PRIMARY KEY AUTO_INCREMENT,
                         numar_card VARCHAR(255) NOT NULL,
                         typeofCard VARCHAR(255) NOT NULL,
                         data_expirare DATE NOT NULL,
                         detinator VARCHAR(255) NOT NULL,
                         cvv INT NOT NULL,
                         user_id INT,
                         FOREIGN KEY (user_id) REFERENCES Useri(id)
);

CREATE TABLE DebitCard (
                           id_debit_card INT PRIMARY KEY AUTO_INCREMENT,
                           id_card INT NOT NULL,
                           sold_limit DOUBLE NOT NULL,
                           ammount_used DOUBLE NOT NULL DEFAULT 0,
                           FOREIGN KEY (id_card) REFERENCES Carduri(id_card)
);

CREATE TABLE CreditCard (
                            id_credit_card INT PRIMARY KEY AUTO_INCREMENT,
                            id_card INT NOT NULL,
                            limita_credit DOUBLE NOT NULL,
                            datorie_curenta DOUBLE NOT NULL DEFAULT 0,
                            FOREIGN KEY (id_card) REFERENCES Carduri(id_card)
);

-- Tabela Studenti
CREATE TABLE Studenti (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          email VARCHAR(255) NOT NULL,
                          student_number INT NOT NULL,
                          nume VARCHAR(255) NOT NULL,
                          prenume VARCHAR(255) NOT NULL,
                          grupa INT NOT NULL,
                          id_universitate INT NOT NULL,
                          id_facultate INT NOT NULL,
                          id_card INT,
                          FOREIGN KEY (id) REFERENCES Useri(id),
                          FOREIGN KEY (id_universitate) REFERENCES Universitati(id),
                          FOREIGN KEY (id_facultate) REFERENCES Facultati(id),
                          FOREIGN KEY (id_card) REFERENCES Carduri(id_card)
);

-- Tabela Abonamente
CREATE TABLE Abonamente (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id INT NOT NULL,
                            plata_recurenta BOOLEAN,
                            universitate_id INT NOT NULL,
                            facultate_id INT NOT NULL,
                            data_inceput DATE NOT NULL,
                            expirat BOOLEAN,
                            tip_abonament VARCHAR(255) NOT NULL,
                            FOREIGN KEY (student_id) REFERENCES Useri(id),
                            FOREIGN KEY (universitate_id) REFERENCES Universitati(id),
                            FOREIGN KEY (facultate_id) REFERENCES Facultati(id)
);



-- Tabela Plati
CREATE TABLE Plati (
                       id_plata INT PRIMARY KEY AUTO_INCREMENT,
                       id_utilizator INT NOT NULL,
                       id_abonament INT NOT NULL,
                       tip_abonament VARCHAR(255) NOT NULL,
                       suma DOUBLE NOT NULL,
                       FOREIGN KEY (id_utilizator) REFERENCES Useri(id),
                       FOREIGN KEY (id_abonament) REFERENCES Abonamente(id)
);
ALTER TABLE DebitCard
    DROP FOREIGN KEY debitcard_ibfk_1,
    ADD CONSTRAINT debitcard_ibfk_2
        FOREIGN KEY (id_card)
            REFERENCES Carduri(id_card)
            ON DELETE CASCADE;

ALTER TABLE CreditCard
    DROP FOREIGN KEY creditcard_ibfk_1,
    ADD CONSTRAINT creditcard_ibfk_2
        FOREIGN KEY (id_card)
            REFERENCES Carduri(id_card)
            ON DELETE CASCADE;

ALTER TABLE Carduri
    ADD CONSTRAINT carduri_ibfk_2
        FOREIGN KEY (user_id)
            REFERENCES Studenti(id)
            ON DELETE CASCADE;

ALTER TABLE Abonamente
    ADD CONSTRAINT abonamente_ibfk_4
        FOREIGN KEY (student_id)
            REFERENCES Useri(id)
            ON DELETE CASCADE;

ALTER TABLE Abonamente
    DROP FOREIGN KEY abonamente_ibfk_2,
    ADD CONSTRAINT abonamente_ibfk_5
        FOREIGN KEY (universitate_id)
            REFERENCES Universitati(id)
            ON DELETE CASCADE;

ALTER TABLE Facultati
    DROP FOREIGN KEY facultati_ibfk_1,
    ADD CONSTRAINT facultati_ibfk_2
        FOREIGN KEY (universitate_id)
            REFERENCES Universitati(id)
            ON DELETE CASCADE;

ALTER TABLE Studenti
    DROP FOREIGN KEY studenti_ibfk_3,
    ADD CONSTRAINT studenti_ibfk_5
        FOREIGN KEY (id_facultate)
            REFERENCES Facultati(id)
            ON DELETE CASCADE;

ALTER TABLE Studenti
    DROP FOREIGN KEY studenti_ibfk_1,
    ADD CONSTRAINT studenti_ibfk_6
        FOREIGN KEY (id)
            REFERENCES Useri(id)
            ON DELETE CASCADE;

ALTER TABLE Studenti
    DROP FOREIGN KEY studenti_ibfk_4,
    ADD CONSTRAINT studenti_ibfk_7
        FOREIGN KEY (id_card)
            REFERENCES Carduri(id_card)
            ON DELETE SET NULL;

ALTER TABLE Plati
    DROP FOREIGN KEY plati_ibfk_2,
    ADD CONSTRAINT plati_ibfk_3
        FOREIGN KEY (id_abonament)
            REFERENCES Abonamente(id)
            ON DELETE CASCADE;

ALTER TABLE Abonamente
    DROP FOREIGN KEY abonamente_ibfk_1;

ALTER TABLE Abonamente
    ADD CONSTRAINT abonamente_ibfk_1
        FOREIGN KEY (student_id) REFERENCES useri(id)
            ON DELETE CASCADE;