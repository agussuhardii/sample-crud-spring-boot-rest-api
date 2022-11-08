create table user_
(
    id         char(36) primary key not null,
    full_name  varchar(200)         not null,
    hobie      varchar(100)         not null,
    created_at bigint               null,
    updated_at bigint               null
);
