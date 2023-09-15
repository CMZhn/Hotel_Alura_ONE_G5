USE hotel_alura;

CREATE TABLE Huespedes (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Id_Datos_Personal BIGINT UNSIGNED NOT NULL UNIQUE,
Activo BIT DEFAULT 1 NOT NULL,
 PRIMARY KEY (Id),
 FOREIGN KEY(Id_Datos_Personal) REFERENCES Datos_Personales(Id)) ENGINE=InnoDB;
 
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(3,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(4,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(5,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(6,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(7,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(8,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(9,1);
INSERT INTO Huespedes (Id_Datos_Personal,Activo) VALUES(10,1);
 
 SELECT * FROM Huespedes;