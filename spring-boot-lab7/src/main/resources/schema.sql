CREATE TABLE IF NOT EXISTS ACADEMIC_GROUPS (
    id      INT NOT NULL AUTO_INCREMENT,
    name    VARCHAR(20) NOT NULL,
    faculty VARCHAR(20),
    course  INT,
    curator VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS STUDENTS (
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(15),
    surname  VARCHAR(25),
    faculty  VARCHAR(20),
    age      INT,
    group_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (group_id) REFERENCES ACADEMIC_GROUPS (id)
);
