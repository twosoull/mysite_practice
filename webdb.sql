CREATE TABLE rboard(
    no number ,
    user_no number not Null,
    title varchar2(500),
    content varchar2(4000),
    hit number ,
    reg_date date, 
    group_no number,
    order_no number,
    depth number,
    CONSTRAINT rboard_PK primary key(no),
    CONSTRAINT rboard_FK FOREIGN KEY(user_no)
    references users(no)
);

CREATE SEQUENCE seq_Rboard_no
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE seq_Rboard_group_no
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE seq_Rboard_group_no
INCREMENT BY 1
START WITH 2;

DROP TABLE RBOARD;
DROP SEQUENCE seq_rboard_no;
DROP SEQUENCE seq_rboard_group_no;
DROP SEQUENCE seq_rboard_order_no;

insert into rboard
values(seq_rboard_no.nextval,1,'안녕하세요','반가워요',0,sysdate,seq_Rboard_group_no.nextval,1,0);

insert into rboard
values(seq_rboard_no.nextval,1,'반갑습니다','반가워요',0,sysdate,seq_Rboard_group_no.nextval,1,0);

insert into rboard
values(seq_rboard_no.nextval,1,'회식합시다','회식ㄱ?',0,sysdate,seq_Rboard_group_no.nextval,1,0);

insert into rboard
values(seq_rboard_no.nextval,1,'봄입니다','한강가실분 ?ㅜ',0,sysdate,seq_Rboard_group_no.nextval,1,0);

select *
from rboard;

insert into rboard
values(seq_rboard_no.nextval,
       1,
       '어디서요?',
       '한강가실분 ?ㅜ',
       0,
       sysdate,
       3,
       3,
       1
       );

select r.no,
       r.user_no,
       r.title,
       r.hit,
       r.reg_date,
       u.name
from rboard r , users u
where r.user_no = u.no
order by group_no desc,order_no asc,depth asc;

update rboard
set title = '한강 치맥 ㄱ?'
where order_no = 3;

select r.no as no,
       r.user_no as user_no,
       r.title as title,
       r.hit as hit,
       r.reg_date as reg_date,
       u.name as name
from rboard r , users u
where r.user_no = u.no
order by group_no desc,order_no asc;

select no,
       user_no,
       title,
       hit,
       reg_date,
       name,
       rownum,
    group_no,
       order_no,
       depth
from (select r.no as no,
       r.user_no as user_no,
       r.title as title,
       r.hit as hit,
       r.reg_date as reg_date,
       u.name as name,
       r.group_no,
       r.order_no,
       r.depth
from rboard r , users u
where r.user_no = u.no
order by group_no asc,order_no desc)
order by rownum desc;

insert into rboard
values(seq_rboard_no.nextval,1,'봄입니다','한강가실분 ?ㅜ',0,sysdate,seq_Rboard_group_no.nextval,1,0);

/*seq_Rboard_group_no*/

update rboard
set order_no = order_no + 1
where no = 7;

update rboard
set depth = depth + 1
where no = 7;

select *
from rboard;

insert into rboard
values(seq_rboard_no.nextval,1,'어디서요?1','어디냐구영1',0,sysdate,3,3,1);


select no,
       user_no,
       title,
       hit,
       reg_date,
       name,
       rownum,
    group_no,
       order_no,
       depth
from (select r.no as no,
       r.user_no as user_no,
       r.title as title,
       r.hit as hit,
       r.reg_date as reg_date,
       u.name as name,
       r.group_no,
       r.order_no,
       r.depth
from rboard r , users u
where r.user_no = u.no
order by group_no asc,order_no desc)
order by rownum desc;


select r.no ,
       r.user_no,
       r.title ,
       r.hit ,
       r.reg_date ,
       r.content ,
       u.name ,
       r.group_no,
       r.order_no,
       r.depth
from rboard r , users u
where r.user_no = u.no
and r.no = 3
order by group_no desc,order_no asc;

select *
from rboard
where group_no = 1
order by group_no desc,order_no asc,depth asc;

select order_no,
       depth ,
       no
from rboard
where group_no = 1;

select max(order_no)as order_no
from rboard
where group_no = 1;

select max(depth)as depth
from rboard
where group_no = 1;


update rboard
set order_no = 3
where no= 29;

select r.no,
       r.user_no,
       r.title,
       r.hit,
       r.reg_date,
       u.name,
       r.group_no,
       r.order_no,
       r.depth
from rboard r , users u
where r.user_no = u.no
order by group_no desc,order_no asc;

select r.no,
     r.user_no,
     r.title,
     r.hit,
     r.reg_date,
     u.name
from rboard r , users u
where r.user_no = u.no
order by group_no desc,order_no asc;

update rboard
set title= null,
    user_no = 0    
where no = 8;

select *
from rboard;

update rboard
set hit= hit + 1  
where no = 8;

update rboard
set title = '   장범준'
where no = 8;
