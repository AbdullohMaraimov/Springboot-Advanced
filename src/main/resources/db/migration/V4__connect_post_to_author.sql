alter table posts
add constraint fk_author_id
foreign key (authorId)
references authors(id);