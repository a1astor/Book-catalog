CREATE TABLE IF NOT EXISTS public.authors
(
    id bigint NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    second_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_date date NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.books
(
    isbn bigint NOT NULL,
    book_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    book_year bigint NOT NULL,
    published date NOT NULL,
    publisher character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_date date NOT NULL,
    CONSTRAINT books_pkey1 PRIMARY KEY (isbn)
)

CREATE TABLE IF NOT EXISTS public.reviews
(
    id bigint NOT NULL,
    commenter character varying(255) COLLATE pg_catalog."default" NOT NULL,
    review_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    review_comment character varying(255) COLLATE pg_catalog."default",
    rating bigint,
    create_date date NOT NULL,
    book_isbn bigint,
    CONSTRAINT reviews_pkey PRIMARY KEY (id)
)