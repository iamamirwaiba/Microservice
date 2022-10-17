CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table data(
    id int constraint data_id primary key generated by default as identity ,
    data TEXT not null,
    type varchar(25),
    created_at DATE,
    updated_at DATE
);

create table chat(
    id int constraint chat_id primary key generated by default as identity ,
    sender_id int not null,
    receiver_id int not null ,
    data_id int not null,
    chat_status varchar(20) not null,
    constraint fk_data_id foreign key (data_id) references data(id)
);

create table participant(
    id int constraint participant_id primary key generated by default as identity ,
    user_id int not null
);

create table group_text(
    id int constraint group_text_id primary key generated by default as identity ,
    name text not null ,
    participant_id int not null ,
    data_id int not null,
    created_at DATE,
    updated_at DATE,
    group_image TEXT,
    constraint fk_participant_id foreign key (participant_id) references participant(id),
    constraint fk_data_id foreign key (data_id) references data(id)
);

create table reply(
    id int constraint reply_id primary key generated by default as identity ,
    which_data_id int not null,
    what_data_id int not null,
    constraint fk_which_data_id foreign key (which_data_id) references data(id),
    constraint fk_what_data_id foreign key (what_data_id) references data(id)
);

create table chat_room(
    id int constraint chat_room_id primary key generated by default as identity ,
    chat_id int not null ,
    sender_id int not null ,
    receiver_id int not null
);