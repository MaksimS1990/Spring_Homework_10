CREATE TABLE IF NOT EXISTS Tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titleTask VARCHAR(500) NOT NULL,
    status VARCHAR(50) NOT NULL,
    dateTimeCreateTask TIMESTAMP NOT NULL
);