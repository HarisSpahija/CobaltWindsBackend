CREATE TABLE Teams
(
    ID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(50) NOT NULL UNIQUE,
    CreatedDate DATETIME NOT NULL,
    DisbandedDate DATETIME NULL,
    Description NVARCHAR(MAX) NOT NULL,
    Image NVARCHAR(MAX) NOT NULL,
    ActivityStatusId INT,
    CaptainID INT NOT NULL,
    FOREIGN KEY (ActivityStatusId) REFERENCES ActivityStatuses(StatusId),
    FOREIGN KEY (CaptainID) REFERENCES Players(ID)
);

CREATE TABLE TeamPlayers
(
    TeamId INT,
    PlayerId INT,
    PRIMARY KEY (TeamId, PlayerId),
    FOREIGN KEY (TeamId) REFERENCES Teams(TeamId),
    FOREIGN KEY (PlayerId) REFERENCES Players(ProfileId)
);

CREATE TABLE TeamCaptains
(
    TeamId INT,
    CaptainId INT,
    PRIMARY KEY (TeamId),
    FOREIGN KEY (TeamId) REFERENCES Teams(TeamId),
    FOREIGN KEY (CaptainId) REFERENCES Players(ProfileId)
);