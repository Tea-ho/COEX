
drop database if exists project;
create database project;

use project;

-- 멤버 테이블
drop table if exists member;
create table member (
	mNo int auto_increment primary key,  	-- 멤버 고유키
    mId varchar(20) unique,					-- 멤버 아이디 ( 계정 )
    mPw varchar(20),						-- 멤버 패스워드
    mName varchar(20) ,						-- 멤버 이름
    mPhone varchar(20) unique,				-- 멤버 휴대전화
    mEmail varchar(20) unique				-- 이메일
);

drop table if exists coinlist;
create table coinlist(
	cNo int auto_increment primary key,		-- 코인 고유번호
    cName varchar(20) unique,				-- 코인 이름
    cPrice int ,							-- 초기 코인 가격
    cAmount int								-- 코인 전체 수량
);

-- 구매 테이블
drop table if exists buy;
create table buy (
	bNo int auto_increment primary key,				-- 구매 고유번호
    bPrice int not null,							-- 구매 가격
    bAmount int not null,							-- 구매 수량
    bDate datetime default now(),					-- 구매날짜
    mNo int,										-- 구매한사람(멤버) 고유번호
    cNo int,
    foreign key ( mNo ) references member ( mNo ) on delete cascade,
    foreign key ( cNo ) references coinlist ( cNo ) on delete cascade
);

-- 판매 테이블
drop table if exists sell;
create table sell(
	sNo int auto_increment primary key,				-- 판매 고유번호
    sPrice int not null,							-- 판매 가격
    sAmount int not null,							-- 판매 수량
    sDate datetime default now(),					-- 판매날짜
    bNo int,										-- 구매
    cNo int,
    foreign key ( bNo ) references buy ( bNo ) on delete cascade ,
    foreign key ( cNo ) references coinlist ( cNo ) on delete cascade
);

drop table if exists account;
create table account(							
	aNo int auto_increment primary key,				-- account 고유번호
    aName int  ,									-- 계좌멤버 이름
    aAcount int  ,									-- 계좌번호
    aBalance int not null,							-- 잔고
    aAmount int not null,							-- 잔여갯수
    adeposit int ,									-- 입금금액
    withdraw int ,									-- 출금금액
    mNo int,										-- 멤버 고유번호
    cNo int,										-- 코인 고유번호
    foreign key ( mNo ) references member ( mNo ) on delete cascade ,
    foreign key ( cNo ) references coinlist ( cNo )  on delete cascade
);

drop table if exists create_acc;
create table create_acc(
	accNo int auto_increment primary key,
    accName varchar(20),
    accountNo varchar(20),					-- 계좌번호
    accBalance int	,						-- 계좌 잔고
    mNo int,
    foreign key ( mNo ) references member ( mNo ) on delete cascade
);

insert into member ( mId , mPw , mName , mPhone , mEmail ) values ( 'admin' , 'admin' ,  '관리자' , '00000000000' , 'admin@admin' );


drop table if exists coinmarketP ;
create table coinmarketP(
	CMNo int auto_increment primary key,
    CIPrice int not null,
    CMprice int not null,
    CMRemaining int not null,
    CMDate datetime default now(),
    cNo int,
    foreign key ( cNo ) references coinlist ( cNo ) on delete cascade
);

drop table if exists coinTradeList;
create table coinTradeList(
	CTNo int auto_increment primary key,
    CTPrice int not null,
    CTVolume int not null,
    CTDate datetime default now(),
    Buystate char(1),
    Sellstate char(1) default null,
    cNo int,
    mNo int,
    foreign key ( cNo ) references coinlist ( cNo ) on delete cascade,
    foreign key ( mNo ) references member ( mNo ) on delete cascade
);

drop table if exists personal_coinlist;
create table personal_coinlist(
	pcNo int auto_increment primary key,
    pcAmount int not null,
    pcSumPrice int not null,
    mno int,
    cno int,
    foreign key ( mno ) references member ( mno ) on delete cascade,
    foreign key ( cno ) references coinlist ( cno ) on delete cascade
);

select * from coinmarketp ;
select * from cointradelist ;
select * from personal_coinlist;

insert into cointradelist ( ctprice , ctvolume , buystate , cno , mno ) values ( ? , ? , ? , ? , ? ) ;
update coinmarketp p , cointradelist t set p.cmremaining = (p.cmremaining - ? ) where p.cmno = ? ;

insert into personal_coinlist ( pcno , pcAmount , pcsumprice , cno , mno ) values (  1, 10, 10000 , 1 , 3)
on duplicate key update pcamount = (select sum(ctvolume) from cointradelist where sellstate is null) , 
pcsumprice = (select sum(ctprice * ctvolume) from cointradelist where mno = 3 and cno = 1 and sellstate is null )/pcamount ;

-- 수익률
select ((p.cmprice - pn.pcsumprice)/pn.pcsumprice)*100 from coinmarketp p , personal_coinlist pn where p.cno = pn.cno and pn.cno = 1 ;

-- 수익금
select pn.pcsumprice * ((p.cmprice - pn.pcsumprice)/pn.pcsumprice) * pn.pcamount from coinmarketp p , personal_coinlist pn where p.cno = pn.cno and pn.cno = 1;

select c.cname , p.cmprice , (select ctprice from cointradelist where cno = 1 and sellstate is not null order by ctdate desc limit 1) as recent_trade,
pn.pcSumPrice , pn.pcAmount , (select ((p.cmprice - pn.pcsumprice)/pn.pcsumprice)*100 from coinmarketp p , personal_coinlist pn where p.cno = pn.cno and pn.cno = 1) as 수익률,
(select pn.pcsumprice * ((p.cmprice - pn.pcsumprice)/pn.pcsumprice) * pn.pcamount from coinmarketp p , personal_coinlist pn where p.cno = pn.cno and pn.cno = 1) as 수익금
from coinlist c , coinmarketp p , personal_coinlist pn 
where c.cno = p.cno and p.cno = pn.cno and pn.cno = 1 and pn.mno = 3;

select * from coinmarketp ;
select * from coinlist;
insert into coinlist ( cname , cprice , camount ) values ( '이더리움' , 2000 , 1000 ) ;
insert into coinmarketp ( ciprice , cmprice , cmremaining , cno ) value ( 2000 , 2000 , 1000 , 2 );

select * from cointradelist; 
select * from member ;
select c.cname , p.cmprice , (select ctprice from cointradelist where cno = 2 and sellstate is not null order by ctdate desc limit 1) 
from coinlist c , coinmarketp p 
where c.cno = p.cno and c.cno = 2;

select ctprice from cointradelist where cno = 1 and sellstate is not null order by ctdate desc limit 1;