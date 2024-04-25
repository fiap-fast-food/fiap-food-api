CREATE TABLE IF NOT EXISTS public.order_product (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_product_order FOREIGN KEY (order_id) REFERENCES public.order(id),
    CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES public.product(id)
);
