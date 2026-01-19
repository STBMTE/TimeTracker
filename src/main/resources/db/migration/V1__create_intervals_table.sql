CREATE EXTENSION IF NOT EXISTS btree_gist;

DROP TABLE IF EXISTS intervals CASCADE;

CREATE TABLE intervals (
    id SERIAL PRIMARY KEY,
    start_sec INT NOT NULL CHECK (start_sec >= 0 AND start_sec <= 86400),
    end_sec INT NOT NULL CHECK (end_sec >= 0 AND end_sec <= 86400 AND end_sec > start_sec),
    type VARCHAR(10) NOT NULL CHECK (type IN ('WORK', 'BREAK')),
    EXCLUDE USING gist (
        int4range(start_sec, end_sec, '[]') WITH &&
    )
);