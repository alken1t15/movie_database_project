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

-- Вставляем данные в таблицу "movie"
INSERT INTO movie (name, year, genre, rating, count)
VALUES
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050);

-- Вставляем данные в таблицу "actor"
INSERT INTO actor (name, surname, movie_id)
VALUES
    ('Tim', 'Robbins', 1),
    ('Morgan', 'Freeman', 1),
    ('Marlon', 'Brando', 2),
    ('Al', 'Pacino', 2),
    ('Christian', 'Bale', 3),
    ('Heath', 'Ledger', 3),
    ('John', 'Travolta', 4),
    ('Uma', 'Thurman', 4),
    ('Tom', 'Hanks', 5),
    ('Robin', 'Wright', 5);

-- Вставляем данные в таблицу "director"
INSERT INTO director (name, surname, movie_id)
VALUES
    ('Frank', 'Darabont', 1),
    ('Francis Ford', 'Coppola', 2),
    ('Christopher', 'Nolan', 3),
    ('Quentin', 'Tarantino', 4),
    ('Robert', 'Zemeckis', 5);

INSERT INTO movie (name, year, genre, rating, count)
VALUES
    ('The Matrix', 1999, 'Sci-Fi', 8.7, 980),
    ('Inception', 2010, 'Thriller', 8.8, 1050),
    ('The Lord of the Rings: The Fellowship of the Ring', 2001, 'Fantasy', 8.8, 1100),
    ('Fight Club', 1999, 'Drama', 8.8, 1000),
    ('The Silence of the Lambs', 1991, 'Thriller', 8.6, 950);

-- Вставляем данные в таблицу "actor"
INSERT INTO actor (name, surname, movie_id)
VALUES
    ('Keanu', 'Reeves', 6),
    ('Carrie-Anne', 'Moss', 6),
    ('Leonardo', 'DiCaprio', 7),
    ('Ellen', 'Page', 7),
    ('Elijah', 'Wood', 8),
    ('Ian', 'McKellen', 8),
    ('Brad', 'Pitt', 9),
    ('Edward', 'Norton', 9),
    ('Jodie', 'Foster', 10),
    ('Anthony', 'Hopkins', 10);

-- Вставляем данные в таблицу "director"
INSERT INTO director (name, surname, movie_id)
VALUES
    ('Lana', 'Wachowski', 6),
    ('Lilly', 'Wachowski', 6),
    ('Christopher', 'Nolan', 7),
    ('Peter', 'Jackson', 8),
    ('David', 'Fincher', 9),
    ('Jonathan', 'Demme', 10);

-- Вставляем данные в таблицу "movie"
INSERT INTO movie (name, year, genre, rating, count)
VALUES
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120),
    ('The Shawshank Redemption', 1994, 'Drama', 9.3, 1000),
    ('The Godfather', 1972, 'Crime', 9.2, 950),
    ('The Dark Knight', 2008, 'Action', 9.0, 1200),
    ('Pulp Fiction', 1994, 'Crime', 8.9, 1100),
    ('Forrest Gump', 1994, 'Drama', 8.8, 1050),
    ('Schindler''s List', 1993, 'Biography', 8.9, 970),
    ('The Lord of the Rings: The Return of the King', 2003, 'Fantasy', 8.9, 1120);

-- Вставляем данные в таблицу "actor"
INSERT INTO actor (name, surname, movie_id)
VALUES
    ('Tom', 'Hardy', 11),
    ('Anne', 'Hathaway', 11),
    ('Liam', 'Neeson', 11),
    ('Brad', 'Pitt', 12),
    ('Edward', 'Norton', 12),
    ('Elijah', 'Wood', 13),
    ('Viggo', 'Mortensen', 13),
    ('Helena', 'Bonham Carter', 14),
    ('Edward', 'Norton', 14),
    ('Jodie', 'Foster', 15),
    ('Anthony', 'Hopkins', 15),
    ('Ralph', 'Fiennes', 16),
    ('Liam', 'Neeson', 16),
    ('Morgan', 'Freeman', 17),
    ('Jessica', 'Tandy', 17),
    ('Matt', 'Damon', 18),
    ('Ben', 'Affleck', 18),
    ('Robin', 'Williams', 19),
    ('Matt', 'Damon', 19),
    ('Russell', 'Crowe', 20),
    ('Joaquin', 'Phoenix', 20),
    ('Emma', 'Watson', 21),
    ('Rupert', 'Grint', 21),
    ('Daniel', 'Radcliffe', 21),
    ('Helena', 'Bonham Carter', 22),
    ('Johnny', 'Depp', 22),
    ('Leonardo', 'DiCaprio', 23),
    ('Joseph', 'Gordon-Levitt', 23),
    ('Tom', 'Hanks', 24),
    ('Michael', 'Clarke Duncan', 24),
    ('Tim', 'Robbins', 25),
    ('Morgan', 'Freeman', 25),
    ('Marlon', 'Brando', 26),
    ('Al', 'Pacino', 26),
    ('Christian', 'Bale', 27),
    ('Heath', 'Ledger', 27),
    ('John', 'Travolta', 28),
    ('Uma', 'Thurman', 28),
    ('Tom', 'Hanks', 29),
    ('Robin', 'Wright', 29),
    ('Keanu', 'Reeves', 30),
    ('Carrie-Anne', 'Moss', 30),
    ('Leonardo', 'DiCaprio', 31),
    ('Ellen', 'Page', 31),
    ('Elijah', 'Wood', 32),
    ('Ian', 'McKellen', 32),
    ('Brad', 'Pitt', 33),
    ('Edward', 'Norton', 33),
    ('Jodie', 'Foster', 34),
    ('Anthony', 'Hopkins', 34);

-- Вставляем данные в таблицу "director"
INSERT INTO director (name, surname, movie_id)
VALUES
    ('David', 'Fincher', 11),
    ('Christopher', 'Nolan', 12),
    ('Peter', 'Jackson', 13),
    ('David', 'Fincher', 14),
    ('Jonathan', 'Demme', 15),
    ('Steven', 'Spielberg', 16),
    ('Peter', 'Jackson', 17),
    ('Steven', 'Spielberg', 18),
    ('Gus', 'Van Sant', 19),
    ('Ridley', 'Scott', 20),
    ('Chris', 'Columbus', 21),
    ('Tim', 'Burton', 22),
    ('Martin', 'Scorsese', 23),
    ('Christopher', 'Nolan', 24),
    ('Frank', 'Darabont', 25),
    ('Francis Ford', 'Coppola', 26),
    ('Christopher', 'Nolan', 27),
    ('Quentin', 'Tarantino', 28),
    ('Robert', 'Zemeckis', 29),
    ('Lana', 'Wachowski', 30),
    ('Lilly', 'Wachowski', 30),
    ('Christopher', 'Nolan', 31),
    ('Peter', 'Jackson', 32),
    ('David', 'Fincher', 33),
    ('Jonathan', 'Demme', 34);