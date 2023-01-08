delete from Book;
delete from Author;
delete from Book_Author;

insert into Book (id, name, released_year, edition, quantity, placed_at)
            values ('1', 'The Art Of Loving', 1995, 3, 20, '2023-01-08');

insert into Book (id, name, released_year, edition, quantity, placed_at)
            values ('2', 'The Science of StoryTelling', 2019, 1, 20, '2023-01-08');


insert into Author(name)
            values('Erich Fromm');

insert into Author(name)
            values('Will Storr');


insert into Book_Author(book_id, author_id)
            values('1', 1);

insert into Book_Author(book_id, author_id)
            values('2', 2);