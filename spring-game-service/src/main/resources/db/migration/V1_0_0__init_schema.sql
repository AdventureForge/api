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

create table if not exists adventure
(
    adventure_id  serial primary key,
    uuid          uuid,
    title         varchar(255),
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists appendix
(
    appendix_id   serial primary key,
    uuid          uuid,
    title         varchar(255),
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255)
);


create table if not exists adventure_appendix
(
    adventure_id int not null,
    appendix_id  int not null,
    primary key (adventure_id, appendix_id),
    foreign key (adventure_id) references adventure (adventure_id),
    foreign key (appendix_id) references appendix (appendix_id)
);

create table if not exists campaign
(
    campaign_id   serial primary key,
    uuid          uuid,
    title         varchar(255),
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists campaign_adventure
(
    campaign_id  integer not null,
    adventure_id integer not null,
    primary key (campaign_id, adventure_id),
    foreign key (campaign_id) references campaign (campaign_id),
    foreign key (adventure_id) references adventure (adventure_id)
);

create table if not exists npc
(
    npc_id        serial primary key,
    uuid          uuid,
    name          varchar(255),
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists npc_adventure
(
    npc_id       integer not null,
    adventure_id integer not null,
    primary key (npc_id, adventure_id),
    foreign key (npc_id) references npc (npc_id),
    foreign key (adventure_id) references adventure (adventure_id)
);

create table if not exists scene
(
    scene_id      serial primary key,
    uuid          uuid,
    title         varchar(255),
    adventure_id  integer not null,
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255),
    foreign key (adventure_id) references adventure (adventure_id)
);

create table if not exists npc_scene
(
    npc_id   integer not null,
    scene_id integer not null,
    primary key (npc_id, scene_id),
    foreign key (npc_id) references npc (npc_id),
    foreign key (scene_id) references scene (scene_id)
);

create table if not exists place
(
    place_id      serial primary key,
    uuid          uuid,
    name          varchar(255),
    date_created  timestamp(0),
    last_modified timestamp(0),
    user_created  varchar(255),
    user_modified varchar(255)
);

create table if not exists place_adventure
(
    place_id     integer not null,
    adventure_id integer not null,
    primary key (place_id, adventure_id),
    foreign key (place_id) references place (place_id),
    foreign key (adventure_id) references adventure (adventure_id)
);

create table if not exists place_scene
(
    place_id integer not null,
    scene_id integer not null,
    primary key (place_id, scene_id),
    foreign key (place_id) references place (place_id),
    foreign key (scene_id) references scene (scene_id)
);

create table if not exists scene_scene
(
    scene_a_id integer not null,
    scene_b_id integer not null,
    primary key (scene_a_id, scene_b_id),
    foreign key (scene_a_id) references scene (scene_id),
    foreign key (scene_b_id) references scene (scene_id)
);