USE hotel_alura;

CREATE TABLE Usuarios (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Usuario VARCHAR(30) NOT NULL UNIQUE,
Contraseña VARCHAR(100) NOT NULL,
Id_Tipo_Usuario BIGINT UNSIGNED NOT NULL,
Id_Datos_Personal BIGINT UNSIGNED NOT NULL UNIQUE,
Activo BIT DEFAULT 1 NOT NULL,
 PRIMARY KEY (Id),
 FOREIGN KEY(Id_Tipo_Usuario) REFERENCES Tipos_Usuarios(Id),
 FOREIGN KEY(Id_Datos_Personal) REFERENCES Datos_Personales(Id)) ENGINE=InnoDB;
 
INSERT INTO Usuarios (Usuario,Contraseña,Id_Tipo_Usuario,Id_Datos_Personal,Activo)
VALUES('Admin','Admin',1,1,1);

INSERT INTO Usuarios (Usuario,Contraseña,Id_Tipo_Usuario,Id_Datos_Personal,Activo)
VALUES('JMedina','JMedina',2,2,1);
 
 SELECT * FROM Usuarios;