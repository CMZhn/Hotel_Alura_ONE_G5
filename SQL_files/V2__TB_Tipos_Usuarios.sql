USE hotel_alura;
CREATE TABLE Tipos_Usuarios (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(20),
 PRIMARY KEY (Id)) ENGINE=InnoDB;
 
 INSERT INTO Tipos_Usuarios (Id,Nombre)VALUES(1,'Administrador');
 INSERT INTO Tipos_Usuarios (Id,Nombre)VALUES(2,'Recepcionista');
 
 SELECT * FROM Tipos_Usuarios;