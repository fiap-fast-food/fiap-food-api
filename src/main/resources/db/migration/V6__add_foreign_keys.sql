-- Adiciona a constraint de chave estrangeira na tabela order
ALTER TABLE public."order" ADD CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES public.customer(id);
ALTER TABLE public."order" ADD CONSTRAINT fk_order_payment FOREIGN KEY (payment_id) REFERENCES public.payment(id);
