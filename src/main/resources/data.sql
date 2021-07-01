DROP TABLE IF EXISTS greeting;

CREATE TABLE greeting
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    greeting VARCHAR(250) NOT NULL
);

INSERT INTO greeting (greeting)
VALUES ('Hello'),
       ('Bonza'),
       ('Jolly Good');
