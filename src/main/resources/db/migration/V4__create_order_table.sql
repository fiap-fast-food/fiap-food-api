CREATE SEQUENCE IF NOT EXISTS order_id_seq START 1;


CREATE TABLE IF NOT EXISTS public."order" (
    id BIGINT DEFAULT nextval('order_id_seq'::regclass) PRIMARY KEY,
    customer_id BIGINT,
    date_time_order TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    confirmation_code VARCHAR(255) NOT NULL,
    payment_id BIGINT
);