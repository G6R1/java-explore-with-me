DROP TABLE IF EXISTS users, subscriptions, categories, events, compilations, events_compilation, participation_requests;

CREATE TABLE IF NOT EXISTS users
(
    user_id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY
    NOT
    NULL,
    name
    VARCHAR
(
    256
) NOT NULL,
    email VARCHAR
(
    512
) NOT NULL,
    CONSTRAINT uq_user_email UNIQUE
(
    email
)
    );

CREATE TABLE IF NOT EXISTS subscriptions
(
    user_id INTEGER REFERENCES users
(
    user_id
) ON DELETE CASCADE,
    subscription_id INTEGER REFERENCES users
(
    user_id
)
  ON DELETE CASCADE,
    CONSTRAINT pk_subscriptions PRIMARY KEY
(
    user_id,
    subscription_id
)
    );

CREATE TABLE IF NOT EXISTS categories
(
    category_id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY
    NOT
    NULL,
    name
    VARCHAR
(
    64
) NOT NULL,
    CONSTRAINT uq_category_name UNIQUE
(
    name
)
    );

CREATE TABLE IF NOT EXISTS events
(
    event_id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY
    NOT
    NULL,
    title
    VARCHAR
(
    120
) NOT NULL,
    annotation VARCHAR
(
    2000
) NOT NULL,
    description VARCHAR
(
    7000
),
    category_id BIGINT REFERENCES categories
(
    category_id
) ON DELETE CASCADE,
    created_on timestamp,
    published_on timestamp,
    event_date timestamp NOT NULL,
    request_moderation BOOLEAN NOT NULL,
    confirmed_requests INTEGER,
    initiator_id BIGINT REFERENCES users
(
    user_id
)
  ON DELETE CASCADE,
    paid BOOLEAN NOT NULL,
    participant_limit INTEGER NOT NULL,
    state VARCHAR
(
    16
),
    views INTEGER,
    lat double precision,
    lon double precision
    );

CREATE TABLE IF NOT EXISTS compilations
(
    compilation_id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY
    NOT
    NULL,
    pinned
    BOOLEAN
    NOT
    NULL,
    title
    VARCHAR
(
    256
) NOT NULL
    );

CREATE TABLE IF NOT EXISTS events_compilation
(
    compilation_id INTEGER REFERENCES compilations
(
    compilation_id
) ON DELETE CASCADE,
    event_id INTEGER REFERENCES events
(
    event_id
)
  ON DELETE CASCADE,
    CONSTRAINT pk_compilation_event PRIMARY KEY
(
    compilation_id,
    event_id
)
    );

CREATE TABLE IF NOT EXISTS participation_requests
(
    request_id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY
    NOT
    NULL,
    created
    timestamp,
    requester_id
    INTEGER
    REFERENCES
    users
(
    user_id
) ON DELETE CASCADE,
    event_id INTEGER REFERENCES events
(
    event_id
)
  ON DELETE CASCADE,
    status VARCHAR
(
    16
),
    UNIQUE
(
    requester_id,
    event_id
)
    );