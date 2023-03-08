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

-- 테스트쿼리 테스트쿼리 테스트쿼리 테스트쿼리 테스트쿼리 테스트쿼리
insert into member ( mId , mPw , mName , mPhone , mEmail ) values ( 'qwert' , 'qwert' ,  'qwert' , '01012345678' , 'qwert@naver.com' );
insert into member ( mId , mPw , mName , mPhone , mEmail ) values ( 'asdfg' , 'asdfg' ,  'asdfg' , '01023456000' , 'asdfg@naver.com' );
insert into coinlist ( cName , cPrice , cAmount ) value ('비트코인' , 1000 , 100 ); -- 관리자가 코인 등록했다 가정
insert into coinmarketp ( ciprice , cmprice , cmremaining , cno ) values ( 1500 , 1500 , 100 , 1);

	-- 일반사용자 코인 구매 쿼리 ( 2번 유저가 1번 코인 1500원에 20개 구매 )
insert into cointradelist ( ctprice , ctvolume , Buystate , cno , mno ) values ( 1500 , 20 , 'b' , 1 , 2) ;
update coinmarketp p , cointradelist t set p.cmremaining = (p.cmremaining - 20) where p.cmno = 1;

	-- 일반사용자 코인 구매 쿼리 ( 3번 유저가 1번코인 1300원에 50개 구매 ) 
insert into cointradelist ( ctprice , ctvolume , Buystate , cno , mno ) values ( 1300 , 50 , 'b' , 1 , 3) ;
update coinmarketp p , cointradelist t set p.cmremaining = (p.cmremaining - 50) where p.cmno = 1;

	-- 일반사용자 코인 판매 쿼리 ( 3번 유저가 1번코인 1400원에 40개 판매 )
insert into cointradelist ( ctprice , ctvolume , Buystate, Sellstate , cno , mno ) values ( 1400 , 40 , 'b' , 's' , 1 , 3) ;
update coinmarketp p , cointradelist t set p.cmremaining = (p.cmremaining + 40) where p.cmno = 1;

insert into cointradelist ( ctprice , ctvolume , Buystate, Sellstate , cno , mno ) values ( 1700 , 50 , 'b' , 's' , 1 , 3) ;
update coinmarketp p , cointradelist t set p.cmremaining = (p.cmremaining + 50) where p.cmno = 1;


select (t.ctprice*t.ctvolume) + (p.ciprice*p.cmremaining) as result
from cointradelist t , coinlist c , coinmarketp p 
where c.cno = t.cno and c.cno = 1 and sellstate = 's' ;


-- ( CIprice( 초기단가) * CMRemaining( 남은코인갯수) ) + ( ctprice(최근판매단가)  * ctvolume(구매수량) )

-- 최근판매단가 구하는 쿼리
select ctprice from cointradelist where cno = 1 and sellstate is not null order by ctdate desc limit 1 ;

-- 구매수량 구하는 쿼리
select 
((select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null)
 - (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null))
from (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) buycoin , 
(select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null) sellcoin;

-- 최근거래단가 * 구매된수량
select
	(select ctprice from cointradelist where sellstate is not null order by ctdate desc limit 1)
    *
    (select 
	((select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null)
	 - (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null))
	from (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) buycoin , 
	(select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null) sellcoin)
from dual;

-- (( CIprice( 초기단가) * CMRemaining( 남은코인갯수) ) + ( ctprice(최근판매단가)  * ctvolume(구매수량) )) / 전체갯수 최종쿼리
select ((p.ciprice*p.cmremaining) +
	(select
		(select ctprice from cointradelist where cno = 1 and sellstate is not null order by ctdate desc limit 1)
		*
		(select 
		((select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null)
		 - (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null))
		from (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) buycoin , 
		(select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null) sellcoin)
	from dual)) / c.cAmount as result
from coinmarketp p , coinlist c where p.cno = c.cno and p.cno = 1 ;

select * from coinmarketp;
select * from cointradelist ;

-- 현단가로 업데이트 쿼리
update coinmarketp p,
	(select ((p.ciprice*p.cmremaining) +
		(select
			(select ctprice from cointradelist where cno = 1 and sellstate is not null order by ctdate desc limit 1)
			*
			(select 
			((select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null)
			 - (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null))
			from (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) buycoin , 
			(select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null) sellcoin)
		from dual)) / c.cAmount as result
	from coinmarketp p , coinlist c where p.cno = c.cno and p.cno = 1) r
set p.cmprice = r.result where p.cno = 1 ;

update coinmarketp p,
(select ((p.ciprice*p.cmremaining) +
(select (select ctprice from cointradelist where sellstate is not null order by ctdate desc limit 1) *
(select ((select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) - (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null))
from (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is null) buycoin , (select sum(ctvolume) from cointradelist where cno = 1 and sellstate is not null) sellcoin)
from dual)) / c.cAmount as result from coinmarketp p , coinlist c where p.cno = c.cno and p.cno = 1) r
set p.cmprice = r.result where p.cno = 1 ;



select c.cname , p.cmprice , (sum(t.ctprice)/sum(t.ctvolume)) , sum(ctvolume) 
from coinlist c , coinmarketp p , cointradelist t 
where c.cno = p.cno and p.cno = t.cno and t.mno = 3
group by t.ctprice , t.ctvolume , c.cname , p.cmprice ;





select * from coinlist ;
select * from coinmarketp;
select * from cointradelist ;