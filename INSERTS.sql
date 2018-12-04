create table magazines(
    id int NOT NULL,
    title varchar(255),
    description varchar(1023),
    imageurl varchar(255),
    publishdate date,
    type varchar(255),
    PRIMARY KEY (id)
);


INSERT INTO magazines VALUES(8, 'Test n°2', 'Test n°2. Courte description kfhdsjkfh dskhfsd sdfdskl sdkfsdhk sdfuidsiusdfuid ezfz fuifgu fzfgdsgfdshfgsdf s fgsdh gdshfgdshdshfghsd fg uiaefguif gdsfgsdhfgsd fgz fauifg gdsh fsdhfghd fufgafga fdhsfdhf g aziuf zh fsdhf sdhf uifgzi fgdhsf gfds', 'https://st2.depositphotos.com/3910205/9614/i/950/depositphotos_96141134-stock-photo-flower-rose-closeup-isolated-on.jpg', '10/10/1996', 'book');


create table exercices(
    id int not null,
    title varchar(255),
    description varchar(255),
    type varchar(255),
    answerId int not null
);

create table questions(
    id int not null,
    