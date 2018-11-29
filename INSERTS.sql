create table magazines(
    id int NOT NULL,
    title varchar(255),
    description varchar(1023),
    imageurl varchar(255),
    publishdate date,
    type varchar(255),
    PRIMARY KEY (id)
);


INSERT INTO magazines VALUES(1, 'Test n°1', 'Test n°1. Courte description', 'https://st2.depositphotos.com/3910205/9614/i/950/depositphotos_96141134-stock-photo-flower-rose-closeup-isolated-on.jpg', '10/10/1996', 'book')