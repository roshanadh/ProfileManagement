use profilems;

CREATE TABLE IF NOT EXISTS users(
        id int(11) NOT NULL AUTO_INCREMENT,
        username  varchar(50) NOT NULL UNIQUE,
        password char(88) NOT NULL,
        name varchar(50) NOT NULL,
        email varchar(50) NOT NULL,
        address varchar(50) NOT NULL,

        PRIMARY KEY(id),
        UNIQUE(username, email)
);

LOCK TABLES users WRITE;

INSERT INTO users (username, password, name, email, address) VALUES
("user1", "zlfYvJkER8fsNVVwQHVtsqn/fNq1ORHzx5lbxr81cs2oyU+lN4nlI6aA3pkhwGf2cX55Qm30Zxhfx6bb7EstVw==", "User 1", "user1@demo.com", "Manchester"),
("user2", "zlfYvJkER8fsNVVwQHVtsqn/fNq1ORHzx5lbxr81cs2oyU+lN4nlI6aA3pkhwGf2cX55Qm30Zxhfx6bb7EstVw==", "User 2", "user2@demo.com", "Ohio");

UNLOCK TABLES;
