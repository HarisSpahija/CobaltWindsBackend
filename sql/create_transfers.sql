CREATE TABLE TransferHistory
(
    transferID INT PRIMARY KEY,
    PlayerID INT,
    TeamID INT,
    InitiatorID INT,
    date DATETIME,
    StatusId INT,
    FOREIGN KEY (PlayerID) REFERENCES PlayerProfile(ID),
    FOREIGN KEY (TeamId) REFERENCES Team(TeamID),
    FOREIGN KEY (InitiatorID) REFERENCES PlayerProfile(ID),
    FOREIGN KEY (StatusId) REFERENCES StatusLookup(StatusId)
);

CREATE TABLE TransferStatuses
(
    StatusId INT PRIMARY KEY,
    StatusName VARCHAR(20)
);
INSERT INTO TransferStatuses
(StatusId, StatusName)
VALUES
    (1, 'Added'),
    (2, 'Removed');