CREATE SEQUENCE IF NOT EXISTS category_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.category (
    id BIGINT DEFAULT nextval('category_id_seq'::regclass) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

