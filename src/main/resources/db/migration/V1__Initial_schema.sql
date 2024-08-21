CREATE TABLE book(
                     id BIGSERIAL PRIMARY KEY NOT NULL,
                     author varchar(255) NOT NULL,
                     isbn varchar(255) UNIQUE NOT NULL,
                     price numeric(10,2) NOT NULL,
                     title varchar(255) NOT NULL,
                     created_date TIMESTAMP NOT NULL,
                     last_modified_date TIMESTAMP NOT NULL,
                     version INTEGER NOT NULL
);