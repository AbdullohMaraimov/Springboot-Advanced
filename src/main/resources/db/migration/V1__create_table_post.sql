create table posts (
    id serial primary key,
    title varchar(100) not null,
    description varchar(300),
    authorId int
)