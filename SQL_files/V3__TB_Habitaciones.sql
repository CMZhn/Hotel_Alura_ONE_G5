USE hotel_alura;

CREATE TABLE Habitaciones (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Capacidad INT UNSIGNED DEFAULT 1 NOT NULL,
Calificacion INT UNSIGNED DEFAULT 1 NOT NULL,
Descripcion VARCHAR(255) NOT NULL,
Valor_fijo DECIMAL(20, 2) NOT NULL,
Valor_variable DECIMAL(20, 2) NOT NULL,
Activo BIT DEFAULT 1 NOT NULL,
 PRIMARY KEY (Id)) ENGINE=InnoDB;
 
 INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(2,5,'Habitacion de lujo, vista al mar, baño con jacuzzi, Cama matrimonial con colchon de caucho natural',
       500,200,1);
       
INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(2,4,'Habitacion de lujo, sin vista al mar, baño con jacuzzi, Cama matrimonial terapéutica',
       500,100,1);

INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(2,3,'Habitacion premium, sin vista al mar, con baño, Cama matrimonial',
       200,50,1);
       
INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(1,2,'Habitacion normal, con baño, Cama unipersonal',
       70,50,1);
       
INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(1,2,'Habitacion normal, con baño, Cama unipersonal',
       70,50,1);

INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(4,5,'Habitacion de lujo, vista al mar, baño con jacuzzi, dos Camas matrimoniales con colchon de caucho natural',
       600,250,1);
       
INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(4,4,'Habitacion de lujo, sin vista al mar, baño sin jacuzzi, dos Camas matrimoniales terapéuticas',
       550,125,1);

INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(4,3,'Habitacion premium, sin vista al mar, con baño, 2 dos Camas matrimoniales',
       250,75,1);
       
INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(2,2,'Habitacion normal, con baño, dos Camas unipersonales',
       75,60,1);

INSERT INTO Habitaciones (Capacidad,Calificacion,Descripcion,Valor_fijo,Valor_variable,Activo)
 VALUES(2,2,'Habitacion normal, con baño, dos Camas unipersonales',
       75,60,1);

SELECT * FROM Habitaciones;