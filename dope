CREATE VIEW FlightReservation(AirlineID, FlightNo, ResrCount)
AS SELECT I.AirlineID, I.FlightNo, COUNT(DISTINCT I.ResrNo)
FROM Includes I
GROUP BY I.AirlineID, I.FlightNo
SELECT * FROM FlightReservation FR
WHERE NOT EXISTS (SELECT * FROM Reservation R, Includes I
WHERE R.ResrNo = I.ResrNo AND FR.AirlineID = I.AirlineID
AND FR.FlightNo = I.FlightNo AND R.AccountNo = 1008
)
ORDER BY FR.ResrCount DESC
