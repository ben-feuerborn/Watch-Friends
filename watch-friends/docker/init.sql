-- Create the movies table inside watchfriendsdb
CREATE TABLE IF NOT EXISTS movies (
    id SERIAL PRIMARY KEY,
    tmdb_id BIGINT,
    title VARCHAR(255),
    overview TEXT,
    poster_path VARCHAR(255),
    release_date VARCAHR(50)
);
