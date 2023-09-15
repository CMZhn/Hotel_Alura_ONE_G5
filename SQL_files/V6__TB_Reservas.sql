USE hotel_alura;

CREATE TABLE Reservas (
Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
Id_Usuario BIGINT UNSIGNED NOT NULL,
Id_Habitacion BIGINT UNSIGNED NOT NULL,
Fecha_entrada DATE NOT NULL,
Fecha_salida DATE NOT NULL,
Valor_Total DECIMAL(20, 2),
Forma_de_pago VARCHAR(25) NOT NULL,
 PRIMARY KEY (Id),
 FOREIGN KEY(Id_Usuario) REFERENCES Usuarios(Id),
 FOREIGN KEY(Id_Habitacion) REFERENCES Habitaciones(Id)) ENGINE=InnoDB;
 
INSERT INTO Reservas (Id_Usuario,Id_Habitacion,Fecha_entrada,Fecha_salida,Valor_Total,Forma_de_pago)
VALUES(1,1,'2023-09-10','2023-09-13',0,'Tarjeta de Crédito');

SELECT * FROM Reservas;