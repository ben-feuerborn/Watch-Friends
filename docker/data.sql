-- =========================================
-- INITIAL DATA SEED FOR WATCHFRIENDS
-- =========================================

-- ============================
-- USERS
-- ============================
INSERT INTO public.users (name, email, bio, preferences)
VALUES
('Alice Johnson', 'alice@example.com', 'Movie lover and sci-fi enthusiast', 'Sci-Fi, Drama'),
('Bob Smith', 'bob@example.com', 'Casual viewer and documentary fan', 'Documentary, History'),
('Charlie Brown', 'charlie@example.com', 'Enjoys comedies and animation', 'Comedy, Animation');

-- ============================
-- FRIENDSHIPS
-- ============================
INSERT INTO public.friendships (user_id, friend_id, since)
VALUES
(1, 2, '2023-06-10'),
(1, 3, '2024-01-15'),
(2, 3, '2024-05-20');

-- ============================
-- MOVIES
-- ============================
INSERT INTO public.movies (title, year, genre)
VALUES
('Inception', 2010, 'Sci-Fi'),
('The Godfather', 1972, 'Crime'),
('Interstellar', 2014, 'Sci-Fi'),
('The Dark Knight', 2008, 'Action');

-- ============================
-- SHOWS
-- ============================
INSERT INTO public.shows (title, seasons, genre)
VALUES
('Breaking Bad', 5, 'Crime'),
('Stranger Things', 4, 'Sci-Fi'),
('The Office', 9, 'Comedy'),
('Game of Thrones', 8, 'Fantasy');
