create table if not exists admin_users (
    id uuid primary key,
    username varchar(100) not null unique,
    password_hash varchar(255) not null,
    role varchar(50) not null,
    enabled boolean not null default true,
    created_at timestamp not null default current_timestamp
);
