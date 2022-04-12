create table if not exists authors
(
    author_id     serial primary key,
    uuid          uuid,
    firstname     varchar(255),
    lastname      varchar(255),
    date_created  timestamp,
    last_modified timestamp,
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists publishers
(
    publisher_id  serial primary key,
    uuid          uuid,
    description   varchar(65535),
    logo          varchar(255),
    name          varchar(255),
    website_url   varchar(2048),
    date_created  timestamp,
    last_modified timestamp,
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists roleplayinggames
(
    roleplayinggame_id serial primary key,
    uuid               uuid,
    description        varchar(65535),
    picture_url        varchar(255),
    subtitle           varchar(255),
    title              varchar(255),
    website_url        varchar(2048),
    date_created       timestamp,
    last_modified      timestamp,
    user_created       varchar(255),
    user_modified      varchar(255)
);

create table if not exists editions
(
    edition_id         serial primary key,
    uuid               uuid,
    edition_number     int,
    edition_title      varchar(255),
    roleplayinggame_id int,
    date_created       timestamp,
    last_modified      timestamp,
    user_created       varchar(255),
    user_modified      varchar(255),
    foreign key (roleplayinggame_id) references roleplayinggames (roleplayinggame_id)
);

create table if not exists collections
(
    collection_id serial primary key,
    uuid          uuid,
    description   varchar(65535),
    title         varchar(255),
    edition_id    int,
    publisher_id  int not null,
    date_created  timestamp,
    last_modified timestamp,
    user_created  varchar(255),
    user_modified varchar(255),
    foreign key (edition_id) references editions (edition_id),
    foreign key (publisher_id) references publishers (publisher_id)
);

create table if not exists books
(
    book_id       serial primary key,
    uuid          uuid,
    book_category varchar(255),
    cover         varchar(255),
    description   varchar(65535),
    isbn          varchar(255),
    language      varchar(10),
    subtitle      varchar(512),
    title         varchar(255) not null,
    collection_id int          not null,
    date_created  timestamp,
    last_modified timestamp,
    user_created  varchar(255),
    user_modified varchar(255),
    foreign key (collection_id) references collections (collection_id)
);

create table if not exists author_book
(
    book_id   int not null,
    author_id int not null,
    primary key (book_id, author_id),
    foreign key (book_id) references books (book_id),
    foreign key (author_id) references authors (author_id)
);