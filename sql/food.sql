create table myazeroth_business.food_facility
(
    id                        int auto_increment,
    location_id               int             not null,
    applicant                 varchar(512)    null,
    facility_type             varchar(64)     null,
    cnn                       varchar(64)     null,
    location_description      varchar(2048)   null,
    address                   varchar(128)    null,
    block_lot                 varchar(32)     null,
    block                     varchar(32)     null,
    lot                       varchar(32)     null,
    permit                    varchar(64)     null,
    status                    varchar(32)     null,
    food_items                varchar(512)   null,
    X                         decimal(13, 6)  null,
    Y                         decimal(13, 6)  null,
    latitude                  decimal(16, 14) null,
    longitude                 decimal(17, 14) null,
    schedule                  varchar(2083)   null,
    dayshours                 varchar(128)    null,
    noi_sent                  int             null,
    approved                  varchar(32)        null,
    received                  varchar(32)            null,
    prior_permit              int             null,
    expiration_date           varchar(32)        null,
    location                  varchar(64)     null,
    fire_prevention_districts int             null,
    police_districts          int             null,
    supervisor_districts      int             null,
    zip_code                  varchar(6)      null,
    neighborhoods_old         int             null,
    constraint PK_id
        primary key (id)
);

create index index_food_items
    on myazeroth_business.food_facility (food_items);

create table myazeroth_business.map_location
(
    id        int             not null
        primary key,
    mercatorX decimal(18, 10) null,
    mercatorY decimal(18, 10) null
);
