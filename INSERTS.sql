create table magazines(
    id int NOT NULL,
    title varchar(255),
    description varchar(1023),
    imageurl varchar(255),
    publishdate date,
    type varchar(255),
    mediaurl type varchar(255),
    browsingurl varchar(255),
    PRIMARY KEY (id)
);

insert into magazines values (
    1,
    'Learn English in 30 Minutes - ALL the English Basics You Need',
    'With this video compilation you will be able to get started with the English language in only 30 minutes!\n\nYou have decided to start learning English, so lets build up your vocabulary! In this video, you will learn some of the most important words and phrases in the English language. If you want to start learning English, this video is made for you. Our host expresses herself in simple English, with English subtitles. This video will challenge your listening comprehension skills and help you progress in your English study. \n\nLet us help you through this 30-minute English basics compilation! This is the fastest, easiest way to pick up basic English!',
    'https://i.ytimg.com/vi/juKd26qkNAw/hqdefault.jpg',
    '03/17/2017',
    'video',
    'https://www.youtube-nocookie.com/embed/juKd26qkNAw',
    'https://www.youtube.com/watch?v=juKd26qkNAw'
);

create table exercices(
    id int not null,
    title varchar(255),
    description varchar(255),
    type varchar(255),
    answerId int not null
);

create table questions(
    id int not null,
    