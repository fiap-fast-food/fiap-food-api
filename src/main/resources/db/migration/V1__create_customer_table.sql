-- Cria a sequência para auto_increment no ID da tabela customer
CREATE SEQUENCE IF NOT EXISTS customer_id_seq START 1;

-- Cria a tabela customer com a coluna de chave primária auto_increment
CREATE TABLE IF NOT EXISTS public.customer (
    id BIGINT DEFAULT nextval('customer_id_seq'::regclass) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL
);