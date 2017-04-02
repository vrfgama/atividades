CREATE SCHEMA IF NOT EXISTS sistemaPredial DEFAULT CHARACTER SET utf8;

USE sistemaPredial;

CREATE TABLE Empresa (
  cnpjEmpresa varchar(18) NOT NULL,
  razaoSocial VARCHAR(30) NOT NULL,
  horaFuncInicial TIME(0) NOT NULL,
  horaFuncFinal TIME(0) NOT NULL,
  PRIMARY KEY (cnpjEmpresa)
);


CREATE TABLE Usuario (
  idUsuario VARCHAR(4) NOT NULL,
  nome VARCHAR(30) NOT NULL,
  senha VARCHAR(4) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  perfil SMALLINT(1) NOT NULL,
  mudaTemp BOOLEAN NOT NULL,
  horaEntrada TIME(0) NOT NULL,
  horaSaida TIME(0) NOT NULL,
  Empresa_cnpjEmpresa varchar(18) NOT NULL,
  PRIMARY KEY (idUsuario),
    FOREIGN KEY (Empresa_cnpjEmpresa)
    REFERENCES Empresa (cnpjEmpresa)
);


CREATE TABLE Conjunto (
  numeroConj SMALLINT(1) NOT NULL,
  disponibilidade boolean NOT NULL,
  Empresa_cnpjEmpresa varchar(18),
  PRIMARY KEY (numeroConj),
    FOREIGN KEY (Empresa_cnpjEmpresa)
    REFERENCES Empresa (cnpjEmpresa)
);


CREATE TABLE CtrlTemperatura (
  tempMax SMALLINT(2) NOT NULL,
  Conjunto_numeroConj SMALLINT(1) NOT NULL,
    FOREIGN KEY (Conjunto_numeroConj)
    REFERENCES Conjunto (numeroConj)
);


insert into Conjunto values(1, true, null);
insert into Conjunto values(2, true, null);
insert into Conjunto values(3, true, null);
insert into Conjunto values(4, true, null);


insert into Empresa values('11.111.111/1111-11', 'Empresa SA', '05:00:00', '23:00:00');

 
select * from conjunto;
select * from ctrltemperatura;
select * from usuario;
select * from empresa;
