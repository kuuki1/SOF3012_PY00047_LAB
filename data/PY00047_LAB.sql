CREATE DATABASE PY00047_LAB
GO
USE PY00047_LAB
GO
CREATE TABLE Users(
    Id NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    Admin BIT NOT NULL,
    PRIMARY KEY(Id)
)
GO

CREATE TABLE Videos (
    Id NVARCHAR(20) NOT NULL,
    Title NVARCHAR(100) NOT NULL,
    Code NVARCHAR(50) NOT NULL,
    Views INT NOT NULL DEFAULT 0,
    Description NVARCHAR(255),
    Active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (Id)
);
GO

CREATE TABLE Favorites (
    Id INT IDENTITY(1,1) NOT NULL,
    VideoID NVARCHAR(20) NOT NULL,
    UserID NVARCHAR(20) NOT NULL,
    LikeDate DATE NOT NULL DEFAULT GETDATE(),
    PRIMARY KEY (Id),
    FOREIGN KEY (VideoID) REFERENCES Videos(Id),
    FOREIGN KEY (UserID) REFERENCES Users(Id)
);
GO


INSERT INTO Users (Id, Password, Fullname, Email, Admin) 
VALUES 
    (N'U001', N'123', N'Nguyen Van A', N'vana@example.com', 1),
    (N'U002', N'456', N'Tran Thi B', N'thib@example.com', 0),
    (N'U003', N'789', N'Le Van C', N'veanc@example.com', 1),
    (N'U004', N'321', N'Pham Thi D', N'thid@example.com', 0);
GO

INSERT INTO Videos (Id, Title, Code, Views, Description, Active)
VALUES 
    (N'V001', N'Video A', N'CODEA', 150, N'Description of Video A', 1),
    (N'V002', N'Video B', N'CODEB', 320, N'Description of Video B', 1),
    (N'V003', N'Video C', N'CODEC', 215, N'Description of Video C', 1),
    (N'V004', N'Video D', N'CODED', 500, N'Description of Video D', 1),
    (N'V005', N'Video E', N'CODEE', 50, N'Description of Video E', 0),
    (N'V006', N'Video F', N'CODEF', 600, N'Description of Video F', 1),
    (N'V007', N'Video G', N'CODEG', 700, N'Description of Video G', 0),
    (N'V008', N'Video H', N'CODEH', 120, N'Description of Video H', 1),
    (N'V009', N'Video I', N'CODEI', 250, N'Description of Video I', 1),
    (N'V010', N'Video J', N'CODEJ', 400, N'Description of Video J', 0),
    (N'V011', N'Video K', N'CODEK', 130, N'Description of Video K', 1),
    (N'V012', N'Video L', N'CODEL', 540, N'Description of Video L', 1),
    (N'V013', N'Video M', N'CODEM', 670, N'Description of Video M', 0),
    (N'V014', N'Video N', N'CODEN', 180, N'Description of Video N', 1),
    (N'V015', N'Video O', N'CODEO', 300, N'Description of Video O', 1),
    (N'V016', N'Video P', N'CODEP', 460, N'Description of Video P', 0),
    (N'V017', N'Video Q', N'CODEQ', 390, N'Description of Video Q', 1),
    (N'V018', N'Video R', N'CODER', 220, N'Description of Video R', 1),
    (N'V019', N'Video S', N'CODES', 520, N'Description of Video S', 0),
    (N'V020', N'Video T', N'CODET', 630, N'Description of Video T', 1);
GO

-- Insert sample data into Favorites table
INSERT INTO Favorites (VideoID, UserID, LikeDate)
VALUES 
    (N'V001', N'U001', GETDATE()),
    (N'V002', N'U001', DATEADD(day, -1, GETDATE())),
    (N'V003', N'U002', DATEADD(day, -2, GETDATE())),
    (N'V004', N'U002', DATEADD(day, -3, GETDATE())),
    (N'V005', N'U003', DATEADD(day, -4, GETDATE())),
    (N'V006', N'U003', DATEADD(day, -5, GETDATE())),
    (N'V007', N'U004', DATEADD(day, -6, GETDATE())),
    (N'V008', N'U004', DATEADD(day, -7, GETDATE())),
    (N'V009', N'U001', DATEADD(day, -8, GETDATE())),
    (N'V010', N'U002', DATEADD(day, -9, GETDATE())),
    (N'V011', N'U003', DATEADD(day, -10, GETDATE())),
    (N'V012', N'U004', DATEADD(day, -11, GETDATE())),
    (N'V013', N'U001', DATEADD(day, -12, GETDATE())),
    (N'V014', N'U002', DATEADD(day, -13, GETDATE())),
    (N'V015', N'U003', DATEADD(day, -14, GETDATE())),
    (N'V016', N'U004', DATEADD(day, -15, GETDATE())),
    (N'V017', N'U001', DATEADD(day, -16, GETDATE())),
    (N'V018', N'U002', DATEADD(day, -17, GETDATE())),
    (N'V019', N'U003', DATEADD(day, -18, GETDATE())),
    (N'V020', N'U004', DATEADD(day, -19, GETDATE()));
GO

-- Query to verify data in Videos table
SELECT * FROM Favorites;
GO

-- Query to verify data in Favorites table
SELECT * FROM Favorites WHERE UserID like 'U002';
GO

select*from Users