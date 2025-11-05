-- =========================================
-- SCHEMA CREATION FOR WATCHFRIENDS DATABASE
-- =========================================

-- ============================
-- USERS TABLE
-- ============================
CREATE TABLE IF NOT EXISTS public.users
(
    user_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    bio TEXT,
    preferences TEXT
);

ALTER TABLE public.users OWNER TO postgres;

-- ============================
-- FRIENDSHIPS TABLE
-- ============================
CREATE TABLE IF NOT EXISTS public.friendships
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    friend_id INTEGER NOT NULL,
    since DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (user_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_friend FOREIGN KEY (friend_id)
        REFERENCES public.users (user_id)
        ON DELETE CASCADE
);

ALTER TABLE public.friendships OWNER TO postgres;

-- ============================
-- MOVIES TABLE
-- ============================
CREATE TABLE IF NOT EXISTS public.movies
(
    movie_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER,
    genre TEXT
);

ALTER TABLE public.movies OWNER TO postgres;

-- ============================
-- SHOWS TABLE
-- ============================
CREATE TABLE IF NOT EXISTS public.shows
(
    show_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    seasons INTEGER,
    genre TEXT
);

ALTER TABLE public.shows OWNER TO postgres;