CREATE DATABASE green_budget_2025;

USE green_budget_2025;

CREATE TABLE budget (
    id INT PRIMARY KEY AUTO_INCREMENT,
    foreas VARCHAR(70) NOT NULL,
    poso INT NOT NULL CHECK (poso>0),
    website VARCHAR (200),
    pososto DECIMAL (6,2)
            AS (poso/25000000)
);
CREATE TABLE Users (
    user_id INT PRIMARY KEY,
    full_name NVARCHAR(150) NOT NULL,
    mail NVARCHAR(150) UNIQUE NOT NULL,
    role NVARCHAR(50) NOT NULL,  -- admin, analyst, ministry_use
);
CREATE TABLE Programs (
       program_id INT PRIMARY KEY,
       budgetid INT NOT NULL,
       program NVARCHAR(255) NOT NULL,
       verifiedamount DECIMAL(18,2) NOT NULL
);
INSERT INTO budget (id, foreas, poso, website) VALUES
    (1001, "ΠΡΟΕΔΡΙΑ ΤΗΣ ΔΗΜΟΚΡΑΤΙΑΣ", 4638000),
    (1003, "ΒΟΥΛΗ ΤΩΝ ΕΛΛΗΝΩΝ", 171950000);

SELECT * FROM budget;
