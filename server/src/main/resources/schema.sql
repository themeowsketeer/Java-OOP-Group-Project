create table if not exists Book (
    id varchar(256) primary key,
    name varchar(256) not null,
    released_date timestamp not null,
    edition bigint not null,
    quantity bigint not null,
    placed_at timestamp not null,
);

create table if not exists Author (
    id varchar(256) primary key,
    first_names varchar(256) not null,
    last_name varchar(256) not null,
);

create table if not exists Book_Author (
    book_id varchar(256),
    author_id varchar(256),
    primary key(book_id, author_id),
    foreign key (book_id) references Book(id),
    foreign key (author_id) references Author(id),
);