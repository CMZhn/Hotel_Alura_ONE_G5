USE hotel_alura;

CREATE TABLE Datos_Personales (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(255),
Apellido VARCHAR(255),
Fecha_de_nacimiento DATE,
Id_Nacionalidad BIGINT UNSIGNED NOT NULL,
Telefono VARCHAR(50),
Activo BIT DEFAULT 1 NOT NULL,
 PRIMARY KEY (Id), 
 FOREIGN KEY(Id_Nacionalidad) REFERENCES Nacionalidades(Id)) ENGINE=InnoDB;
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Admin','Admin','2023-08-25',82,'+504 000000',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Juan Jose','Medina Gutierrez','1992-09-15',82,'+504 123456',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Juan Andes','Mejia Gutierrez','1995-09-20',82,'+504 654321',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Pedro Ormar','Nipon Moros','1985-10-7',82,'+504 142536',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Mario','Pinel Monterrozo','1980-03-10',82,'+504 253614',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Maria Jose','Peñalva Matamoros','1987-05-11',82,'+504 425361',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Rosa Juna','Gillen Martinez','2000-04-12',82,'+504 475869',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Ana Cristel','Mendelei Ortega','2001-06-11',82,'+504 576849',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Cristina Jolanda','Benites Pinel','1995-07-10',82,'+504 586794',1);
 
 INSERT INTO Datos_Personales (Nombre,Apellido,Fecha_de_nacimiento,Id_Nacionalidad,Telefono,Activo)
 VALUES('Lupe esmeralda','Picapiedras del Cid','1975-12-24',82,'+504 112233',1);
 
 SELECT * FROM Datos_Personales;