create table if not exists users
(
    user_id bigint generated by default as identity primary key not null,
    name varchar (256) not null,
    email varchar(512) not null,
    constraint uq_user_email unique(email)
);

create table if not exists subscriptions
(
    user_id integer references users(user_id) on delete cascade,
    subscription_id integer references users(user_id)  on delete cascade,
    constraint pk_subscriptions primary key(user_id, subscription_id)
);

create table if not exists categories
(
    category_id bigint generated by default as identity primary key not null,
    name varchar(64) not null,
    constraint uq_category_name unique(name)
);

create table if not exists events
(
    event_id bigint generated by default as identity primary key not null,
    title varchar(120) not null,
    annotation varchar(2000) not null,
    description varchar(7000),
    category_id bigint references categories(category_id) on delete cascade,
    created_on timestamp,
    published_on timestamp,
    event_date timestamp not null,
    request_moderation boolean not null,
    confirmed_requests integer,
    initiator_id bigint references users(user_id) on delete cascade,
    paid boolean not null,
    participant_limit integer not null,
    state varchar(16),
    views integer,
    lat double precision,
    lon double precision
);

create table if not exists compilations
(
    compilation_id bigint generated by default as identity primary key not null,
    pinned boolean not null,
    title varchar(256) not null
);

create table if not exists events_compilation
(
    compilation_id integer references compilations(compilation_id) on delete cascade,
    event_id integer references events(event_id)  on delete cascade,
    constraint pk_compilation_event primary key(compilation_id, event_id)
);

create table if not exists participation_requests
(
    request_id bigint generated by default as identity primary key not null,
    created timestamp,
    requester_id integer references users(user_id) on delete cascade,
    event_id integer references events(event_id)  on delete cascade,
    status varchar(16),
    unique(requester_id, event_id)
);