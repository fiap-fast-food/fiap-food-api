CREATE SEQUENCE IF NOT EXISTS product_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.product (
    id BIGINT DEFAULT nextval('product_id_seq'::regclass) PRIMARY KEY,    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    category_id BIGINT,
    information VARCHAR(255) COLLATE pg_catalog."default",
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES public.category(id)
);
