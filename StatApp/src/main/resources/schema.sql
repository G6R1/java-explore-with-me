--DROP TABLE IF EXISTS endpoint_hits;

CREATE TABLE IF NOT EXISTS endpoint_hits
(
    hit_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    app       VARCHAR(200)                                        NOT NULL,
    uri       VARCHAR(100)                                        NOT NULL,
    ip        VARCHAR(20)                                         NOT NULL,
    timestamp timestamp                                           NOT NULL
);