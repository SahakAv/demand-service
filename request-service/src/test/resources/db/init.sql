create table users
(
    id bigserial not null
        constraint user_id_pk
            primary key,
    username varchar(250)
        constraint username_unq
            unique,
    password varchar(250),
    type varchar(250)
);


create table servicetype
(
    id bigserial not null
        constraint servicetype_id_pk
            primary key,
    servicename varchar(250)
        constraint servicename_unq
            unique
);



create table servicerequest
(
    id bigserial not null
        constraint servicerequest_id_pk
            primary key,
    servicetype varchar(250),
    address varchar(250),
    date date,
    owner varchar(250),
    status varchar(250),
    assigned varchar(250)
);



create table serviceprovider
(
    id bigserial not null
        constraint item_id_pk
            primary key,
    name varchar(250),
    servicetype_id bigint not null
        constraint service_name_id_fk
            references servicetype,
    owner varchar(250)
        constraint owner_username_fk
            references users (username)
);


