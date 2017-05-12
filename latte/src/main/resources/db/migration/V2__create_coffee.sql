CREATE TABLE coffee (
  id        CHAR(36)     NOT NULL PRIMARY KEY,
  friend_id CHAR(36)     NOT NULL,
  date_time TIMESTAMP(6) NOT NULL,

  FOREIGN KEY (friend_id) REFERENCES friend (id)
    ON DELETE CASCADE
);
