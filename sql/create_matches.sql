CREATE TABLE Matches
(
    MatchId INT PRIMARY KEY IDENTITY,
    TeamOne INT NOT NULL,
    TeamTwo INT NOT NULL,
    Winner INT NOT NULL,
    Format VARCHAR(15) NOT NULL CHECK (Format IN ('Best of 1', 'Best of 2', 'Best of 3', 'Best of 5')),
    Date DATETIME NOT NULL,
    CompetitionId INT NOT NULL,
    FOREIGN KEY (TeamOne) REFERENCES Teams(TeamId),
    FOREIGN KEY (TeamTwo) REFERENCES Teams(TeamId),
    FOREIGN KEY (Winner) REFERENCES Teams(TeamId),
    FOREIGN KEY (CompetitionId) REFERENCES Competitions(CompetitionId)
);

CREATE TABLE Games
(
    GameId INT PRIMARY KEY IDENTITY,
    RiotGameId VARCHAR(255),
    MatchId INT NOT NULL,
    Winner INT NOT NULL,
    BlueSide INT NOT NULL,
    RedSide INT NOT NULL,
    FOREIGN KEY (MatchId) REFERENCES Matches(MatchId),
    FOREIGN KEY (Winner) REFERENCES Teams(TeamId),
    FOREIGN KEY (BlueSide) REFERENCES Teams(TeamId),
    FOREIGN KEY (RedSide) REFERENCES Teams(TeamId)
);

CREATE TABLE GamePlayers
(
    GameId INT NOT NULL,
    PlayerId INT NOT NULL,
    TeamId INT NOT NULL,
    PRIMARY KEY (GameId, PlayerId),
    FOREIGN KEY (GameId) REFERENCES Games(GameId),
    FOREIGN KEY (PlayerId) REFERENCES Players(PlayerId),
    FOREIGN KEY (TeamId) REFERENCES Teams(TeamId)
);