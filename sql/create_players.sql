CREATE TABLE Players
(
    ID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(50) NOT NULL,
    Age INT NOT NULL,
    Description NVARCHAR(MAX) NOT NULL,
    PrimaryRole NVARCHAR(10) NOT NULL CHECK (PrimaryRole IN ('Fill', 'Top', 'Jungle', 'Mid', 'ADC', 'Support')),
    SecondaryRole NVARCHAR(10) NULL CHECK (SecondaryRole IN ('Top', 'Jungle', 'Mid', 'ADC', 'Support')),
    ActivityStatus NVARCHAR(10) NOT NULL CHECK (ActivityStatus IN ('Active', 'Inactive')),
    Image NVARCHAR(MAX) NOT NULL,
    FreeAgent BIT NOT NULL,
    AccountID INT NOT NULL,
    FOREIGN KEY (AccountID) REFERENCES Accounts(ID)
);