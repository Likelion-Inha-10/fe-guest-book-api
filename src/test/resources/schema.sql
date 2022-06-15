DROP TABLE IF EXISTS article;

CREATE TABLE article
(
    id         int auto_increment primary key,
    title      varchar(255)   not null,
    body       varchar(65535) not null,
    owner_id   varchar(255)   not null,
    created_at timestamp      not null
);
