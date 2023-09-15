USE hotel_alura;

CREATE TABLE Reserva_Huesped (
Id_Reserva BIGINT UNSIGNED NOT NULL,
Id_Huesped BIGINT UNSIGNED NOT NULL,
FOREIGN KEY(Id_Reserva) REFERENCES Reservas(Id),
FOREIGN KEY(Id_Huesped) REFERENCES Huespedes(Id)) ENGINE=InnoDB;

INSERT INTO Reserva_Huesped (Id_Reserva,Id_Huesped)
VALUES(1,1);

INSERT INTO Reserva_Huesped (Id_Reserva,Id_Huesped)
VALUES(1,2);

INSERT INTO Reserva_Huesped (Id_Reserva,Id_Huesped)
VALUES(2,3);

SELECT * FROM Reserva_Huesped;