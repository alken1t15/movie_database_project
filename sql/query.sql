create database movie_project_runtime;

create table movie
(
    id     serial primary key not null,
    name   text               not null,
    year   integer            not null,
    genre  text               not null,
    rating numeric(4, 2)      not null,
    count  integer            not null
);

create table actor
(
    id       serial primary key not null,
    name     text               not null,
    surname  text               not null,
    movie_id integer references movie (id)
);

create table director
(
    id       serial not null primary key,
    name     text   not null,
    surname  text   not null,
    movie_id integer references movie (id)
);

create table users
(
    id       serial primary key,
    login    text not null,
    password text not null
);

create table roles
(
    id   serial primary key,
    name text not null
);

create table users_role
(
    id      serial primary key,
    user_id integer references users (id),
    role_id integer references roles (id)
);