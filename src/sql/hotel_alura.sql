#CREATE DATABASE hotel_alura;
USE hotel_alura;

CREATE TABLE RESERVAS(
                         ID_RES INT AUTO_INCREMENT PRIMARY KEY,
                         DATA_ENTRADA VARCHAR(10) NOT NULL,
                         DATA_SAIDA VARCHAR(10) NOT NULL,
                         VALOR DECIMAL(7,2) NOT NULL,
                         PAGAMENTO VARCHAR(50) NOT NULL
);

CREATE TABLE HOSPEDES(
                         ID_HOS INT auto_increment PRIMARY KEY,
                         NOME VARCHAR(100) NOT NULL,
                         SOBRENOME VARCHAR(100) NOT NULL,
                         DATA_NASC VARCHAR(10) NOT NULL,
                         NACIONALIDADE VARCHAR(100) NOT NULL,
                         TELEFONE VARCHAR(50),
                         ID_RESERVA INT
);

CREATE TABLE users (
                       ID_USER INT auto_increment PRIMARY KEY,
                       USUARIO VARCHAR(25) NOT NULL,
                       PASS VARCHAR(25) NOT NULL
);

ALTER TABLE HOSPEDES ADD FOREIGN KEY (ID_RESERVA) REFERENCES RESERVAS(ID_RES);

INSERT INTO users (USUARIO, PASS) VALUES('admin','admin');