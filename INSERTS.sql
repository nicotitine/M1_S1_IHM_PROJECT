create table magazines(
    id int NOT NULL,
    title varchar(255),
    description varchar(1023),
    imageurl varchar(255),
    publishdate date,
    type varchar(255),
    PRIMARY KEY (id)
);


alter table magazines add column mediaurl varchar(255);

INSERT INTO magazines VALUES(6, 'Video Test 2', 'The English File Third Edition Students Book provides a flexible package for students to use both inside and outside of the classroom, complete with iTutor which allows students to catch up on missed lessons or review material covered in class via laptop or mobile. \n\nThe course follows a four-skills syllabus with a clear focus on pronunciation, plus Grammar Bank exercises for practice and activation of grammar. "Real-world" In-The-Street interviews, "Practical English" motivating drama and documentary videos motivate and engage students with English language and culture.', 'https://i.ytimg.com/vi/juKd26qkNAw/hqdefault.jpg' , '01/01/2012', 'video', 'https://www.youtube-nocookie.com/embed/juKd26qkNAw');


create table exercices(
    id int not null,
    title varchar(255),
    description varchar(255),
    type varchar(255),
    answerId int not null
);

create table questions(
    id int not null,
    