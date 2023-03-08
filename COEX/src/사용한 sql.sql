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
    mEmail varchar(20) unique,				-- 이메일
    mState boolean
);

drop table if exists coinlist;
create table coinlist(
	cNo int auto_increment primary key,		-- 코인 고유번호
    cName varchar(20) unique,				-- 코인 이름
    cPrice int ,							-- 코인 가격
    cAmount int								-- 코인 전체수량
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

drop table if exists account;
create table account(							
	aNo int auto_increment primary key,				-- account 고유번호
    adeposit int ,									-- 입금금액
    withdraw int ,									-- 출금금액
    mNo int,										-- 멤버 고유번호
    accNo int,										-- 코인 고유번호
    foreign key ( mNo ) references member ( mNo ) on delete cascade ,
    foreign key ( accNo ) references create_acc ( accNo )  on delete cascade
);





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
	TAmount int,
    Average int,
    Buystate char(1),
    Sellstate char(1) default null,
    CTDate datetime default now(),
    cNo int,
    mNo int,
    foreign key ( cNo ) references coinlist ( cNo ) on delete cascade,
    foreign key ( mNo ) references member ( mNo ) on delete cascade
);

drop table if exists personal_coinlist;
create table personal_coinlist(
	pcNo varchar(10) primary key,
    pcAmount int not null,
    pcSumPrice int ,
    mno int,
    cno int,
    foreign key ( mno ) references member ( mno ) on delete cascade,
    foreign key ( cno ) references coinlist ( cno ) on delete cascade
);

insert into member ( mId , mPw , mName , mPhone , mEmail , mstate ) values ( 'admin' , 'admin' ,  '관리자' , '00000000000' , 'admin@admin' , true);
insert into member ( mId , mPw , mName , mPhone , mEmail , mstate ) values ( 'qwert' , 'qwert' ,  'qwert' , '01112345600' , 'qwert@qwert' , true);
insert into member ( mId , mPw , mName , mPhone , mEmail , mstate ) values ( 'asdfg' , 'asdfg' ,  'asdfg' , '01112345200' , 'asdfg@asdfg' , true);

insert into coinlist ( cName , cPrice , cAmount ) values ( '비트코인' , 200000 , 10000 );
insert into coinlist ( cName , cPrice , cAmount ) values ( '리플' , 2000 , 100000 );
insert into coinlist ( cName , cPrice , cAmount ) values ( '유벤투스' , 5000 , 5000 );

insert into coinmarketP ( CIPrice , CMprice , CMRemaining , cNo ) values ( 200000 , 200000 , 10000 , 1 );
insert into coinmarketP ( CIPrice , CMprice , CMRemaining , cNo ) values ( 2000 , 2000 , 100000 , 2 );
insert into coinmarketP ( CIPrice , CMprice , CMRemaining , cNo ) values ( 5000 , 5000 , 5000 , 3 );
