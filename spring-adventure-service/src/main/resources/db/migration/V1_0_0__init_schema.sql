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