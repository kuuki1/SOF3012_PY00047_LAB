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
INSERT INTO Users (Id, Password, Fullname, Email, Admin) 
VALUES 
    (N'U001', N'123', N'Nguyen Van A', N'vana@example.com', 1),
    (N'U002', N'456', N'Tran Thi B', N'thib@example.com', 0),
    (N'U003', N'789', N'Le Van C', N'veanc@example.com', 1),
    (N'U004', N'321', N'Pham Thi D', N'thid@example.com', 0);
GO

select*from Users

GRANT INSERT ON OBJECT::dbo.Users TO phamtin;
