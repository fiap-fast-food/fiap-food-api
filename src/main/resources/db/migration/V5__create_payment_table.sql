CREATE SEQUENCE IF NOT EXISTS payment_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.payment (
    id BIGINT DEFAULT nextval('payment_id_seq'::regclass) PRIMARY KEY,
    amount NUMERIC(19, 2) NOT NULL,
    status_payment VARCHAR(255) NOT NULL,
    order_id BIGINT,
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES public."order"(id)
);
CREATE INDEX IF NOT EXISTS idx_payment_order ON public.payment (order_id);