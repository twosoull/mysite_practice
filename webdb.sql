/*리스트*/
select rownum,
	   no,
	   title,
       hit,
       reg_date,
       name
from(SELECT b.no,
            b.title,
            b.hit,
	        b.reg_date,
       		b.user_no,
	      	u.name
      FROM board b  , USERS u
      where b.user_no = u.no
      order by b.no asc)
order by rownum desc;

/*read*/
SELECT b.title,
       b.hit,
       b.reg_date,
       b.user_no,
       b.content,
       u.name
FROM board b  , USERS u
where b.user_no = u.no
and b.no=8;

select *
from board;

/*board modifyForm2*/
SELECT b.no as no,
       b.title as title,
       b.hit as hit,
       b.reg_date as reg_date,
       b.content as content,
       u.name as name
FROM board b  , USERS u
where b.user_no = u.no
and b.no= 8;

update board
set title = '나를 사랑을채워줘영',
    content = '사랑의빴떄리까다됏나봐영'
where no = 6;
            
            
insert into board
values(SEQ_BOARD_NO.nextval,'0123','콘텐트',0,sysdate,0);
