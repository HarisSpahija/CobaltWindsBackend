CREATE TABLE Accounts
(
    ID INT PRIMARY KEY IDENTITY,
    UserName NVARCHAR(50) NOT NULL UNIQUE,
    Email NVARCHAR(255) NOT NULL UNIQUE,
    RoleId INT,
    FOREIGN KEY (RoleId) REFERENCES Roles(RoleId),
    Password NVARCHAR(255) NOT NULL,
    Role NVARCHAR(10) NOT NULL CHECK (Role IN ('Admin', 'User'))
);

CREATE TABLE AuthRoles
(
    RoleId INT PRIMARY KEY IDENTITY,
    RoleName VARCHAR(255) NOT NULL UNIQUE
);
INSERT INTO AuthRoles
(RoleName)
VALUES
    ('Admin'),
    ('Player'),
    ('User');