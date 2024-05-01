use gb;
drop table t_user;
create table t_user(
	userid varchar(300) primary key,
    userpw varchar(300) not null,
    username varchar(300) not null,
    usergender char(3) not null,
    zipcode varchar(300) not null,
    addr varchar(1000),
    addrdetail varchar(1000) not null,
    addretc varchar(300),
    userhobby varchar(300)
);

drop table t_board;
create table t_board(
	boardnum bigint primary key auto_increment,
    boardtitle varchar(300) not null,
    boardcontents text not null,
    regdate datetime default now(),
    updatedate datetime default now(),
    readcount bigint default 0,
    userid varchar(300)
);

drop table t_file;
create table t_file(
	systemname varchar(2000),
    orgname varchar(2000),
    boardnum bigint
);

drop table t_reply;
create table t_reply(
	replynum bigint primary key auto_increment,
    replycontents varchar(2000),
    regdate datetime default now(),
    updatedate datetime default now(),
    boardnum bigint,
    userid varchar(300)
);