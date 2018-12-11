create table magazines(
    id int NOT NULL,
    title varchar(255),
    description varchar(1023),
    imageurl varchar(255),
    publishdate date,
    type varchar(255),
    mediaurl varchar(255),
    browsingurl varchar(255),
    PRIMARY KEY (id)
);

create table exercices(
    id int not null,
    title varchar(255),
    description varchar(1023),
    questions varchar(2047),
    answers varchar(2047),
    duration varchar(255),
    imageurl varchar(255),
    type varchar(255),
    primary key (id)
);

create table times(
    time varchar(255) not null,
    example varchar(255),
    explenation varchar(255),
    primary key (time)
);

insert into times values ('Present simple', 'I play football every week.', 'Action qui arrive régulièrement');
insert into times values ('Present continuous', 'I am playing footbal now', 'Acion qui arrive en ce moment');
insert into times values ('Past simple', 'I played football yesterday', 'Action passée et datée (hier ou avant hier)');
insert into times values ('Past continuous', 'I was playing football the whole evening', 'Action qui avait lieu hier, on insiste sur la durée');
insert into times values ('Present perfect', 'I have just played football', 'Action qui vient de se produire, avec conséquence sur le présent');
insert into times values ('Present perfect continuous', 'I have been playing football for 2 hours', 'Action qui a commencé dans le passé et continue dans le présent');
insert into times values ('Past perfect', 'I have played football when Susan came', 'Les deux actions sont liées, l une finissant quand l autre commence');
insert into times values ('Past perfect continuous', 'I had been playing football when Susan came', 'Même chose ici en insistant sur la durée de la première action');
insert into times values ('Future (will)', 'I will play football next week', 'C est une prédiction, il se peut que vous fassiez autre chose');
insert into times values ('Future (going to)', 'I am going to play football this afternoon', 'C est un projet');
insert into times values ('Future continuous', 'I will be playing football next Sunday', 'Action future et habituelle du dimanche');
insert into times values ('Future perfect', 'I will have played football by tomarrow', 'Action accomplie d ici demain');
insert into times values ('Conditional simple', 'I would play football', 'Action probable');
insert into times values ('Conditional continuous', 'I would be playing football', 'Action probable, en insistant sur la durée');
insert into times values ('Conditional perfect', 'I would have played football', 'Action future avec notion de probabilité');
insert into times values ('Conditional perfect continuous', 'I would have been playing football', 'Action future avec notion de probabilité en insistant sur la durée');

insert into exercices values (
    1,
    'Test Exercice',
    'In this exercice, you will see a good display ! Cool isnt it ?',
    'question n°1, quesion n°2, question n°3',
    'question n°1, question n°3',
    '3min',
    'https://i2.wp.com/pedagoblog.fr/wp-content/uploads/2017/11/qcm-1.jpg?fit=1876%2C1038',
    'qcm'
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

insert into magazines values (
    4,
    'Audio Test',
    'Audio test description',
    'http://www.bbc.co.uk/staticarchive/3f3c6fbcecc7b29271f4bcd51da8268301fcfc36.jpg',
    '01/01/2010',
    'audio',
    'http://http-ws.bbc.co.uk.edgesuite.net/mp3/learningenglish/2014/09/140924_vwitn_inmates_bank_audio_140924_vwitn_inmates_bank_audio_au_bb.mp3',
    'http://www.bbc.co.uk/worldservice/learningenglish/081222_download.shtml'
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
    