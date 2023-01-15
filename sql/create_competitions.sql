CREATE TABLE Competitions
(
    CompetitionId INT PRIMARY KEY IDENTITY,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    ActivityStatusId INT,
    Description VARCHAR(255),
    IconImage VARCHAR(255),
    BannerImage VARCHAR(255),
    RegistrationStartDate DATETIME NOT NULL,
    RegistrationEndDate DATETIME NOT NULL,
    FOREIGN KEY (ActivityStatusId) REFERENCES ActivityStatuses(StatusId)
);

CREATE TABLE Prizes
(
    PrizeId INT PRIMARY KEY IDENTITY,
    PrizeType VARCHAR(255) NOT NULL CHECK (PrizeType IN ('Money', 'Object')),
    PrizeValue INT,
    PrizeDescription VARCHAR(255),
    CompetitionId INT,
    FOREIGN KEY (CompetitionId) REFERENCES Competitions(CompetitionId)
);

CREATE TABLE CompetitionTeams
(
    CompetitionId INT,
    TeamId INT,
    PRIMARY KEY (CompetitionId, TeamId),
    FOREIGN KEY (CompetitionId) REFERENCES Competitions(CompetitionId),
    FOREIGN KEY (TeamId) REFERENCES Teams(TeamId)
);

CREATE TABLE TeamPrizes
(
    TeamId INT,
    PrizeId INT,
    PRIMARY KEY (TeamId, PrizeId),
    FOREIGN KEY (TeamId) REFERENCES Teams(TeamId),
    FOREIGN KEY (PrizeId) REFERENCES Prizes(PrizeId),
    UNIQUE (TeamId, PrizeId),
);

CREATE TABLE PlayerPrizes
(
    PlayerId INT,
    PrizeId INT,
    PRIMARY KEY (PlayerId, PrizeId),
    FOREIGN KEY (PlayerId) REFERENCES Players(ProfileId),
    FOREIGN KEY (PrizeId) REFERENCES Prizes(PrizeId),
);

CREATE UNIQUE INDEX UQ_TeamPrizes_TeamId_PrizeId
    ON TeamPrizes (TeamId, PrizeId);

CREATE UNIQUE INDEX UQ_PlayerPrizes_PlayerId_PrizeId
    ON PlayerPrizes (PlayerId, PrizeId);