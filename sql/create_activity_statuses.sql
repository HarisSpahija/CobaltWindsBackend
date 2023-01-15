CREATE TABLE ActivityStatuses
(
    StatusId INT PRIMARY KEY,
    StatusName VARCHAR(255)
);

INSERT INTO ActivityStatuses
    (StatusId, StatusName)
VALUES
    (1, 'Active'),
    (2, 'Inactive');