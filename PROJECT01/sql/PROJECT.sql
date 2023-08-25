CREATE TABLE `ConcertInfo` (
	`concert_id`	number	NOT NULL,
	`title`	varchar2(100)	NOT NULL,
	`genre`	varchar2(50)	NOT NULL,
	`running_Time`	number	NOT NULL,
	`concert_Date`	date	NOT NULL,
	`location`	varchar2(500)	NULL
);

CREATE TABLE `Users` (
	`id`	int	NOT NULL,
	`user_id`	varchar2(20)	NOT NULL,
	`email`	varchar2(50)	NOT NULL,
	`password`	varchar2(20)	NOT NULL	COMMENT 최소 8자리,
	`phoneNumber`	varchar2(11)	NOT NULL	COMMENT -없이 11자리,
	`birthDay`	varchar2(8)	NOT NULL	COMMENT 숫자 8자리,
	`gender`	varcahr2(1)	NOT NULL
);

--- untitle 로 되어있는 거 삭제되었길래 다시 생성함
CREATE TABLE PAYMENT (
      payment_id	number(20)	NOT NULL,
      user_id	varchar2(20)	NOT NULL,
      payment_option	varchar2(20)	NOT NULL,
      total_price	number(15)	NOT NULL,
      payment_date	varchar2(40)	NOT NULL,
      reservation_id	number(20)	NOT NULL
);



CREATE TABLE `Point` (
	`poi_id`	int	NOT NULL,
	`user_id`	varchar2(20)	NOT NULL,
	`point`	int	NOT NULL	DEFAULT 0,
	`issue_date`	varchar(8)	NOT NULL	COMMENT 발급 일자 yyyymmdd,
	`expiry_date`	varchar(8)	NOT NULL	COMMENT 만료 일자 yyyymmdd,
	`memo`	varchar2(100)	NULL	COMMENT 메모
);

CREATE TABLE `Untitled2` (
	`Field`	VARCHAR(255)	NULL,
	`concert_id`	number	NOT NULL,
	`hall_id`	number(2)	NOT NULL
);

--김하은 생성함-------------------------------
CREATE TABLE RESERVATION (
	book_id	number(10)	NOT NULL,
	user_id	varchar2(20)	NOT NULL,
	concert_id	number(2)	NOT NULL,
    hall_id number(2)	NOT NULL,
	count	number(10)	NOT NULL,
	seat	varchar2(20)	NOT NULL,
	totalPrice	number(30)	NOT NULL,
    paymentMethod	varchar2(255)	NOT NULL,
	createDate varchar2(200)	NOT NULL,
	status	varchar2(255)	NOT NULL
);
---------------------------------------------------

CREATE TABLE `hall` (
	`hall_id`	number(2)	NOT NULL,
	`city`	varchar2(20)	NOT NULL,
	`name`	varchar2(40)	NOT NULL,
	`hallName`	varchar2(40)	NULL
);

ALTER TABLE `Concert_Info` ADD CONSTRAINT `PK_CONCERT_INFO` PRIMARY KEY (
	`concert_id`
);

ALTER TABLE `Users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);

--김하은 생성함-------------------------------
ALTER TABLE RESERVATION ADD CONSTRAINT PK_RESERVATION PRIMARY KEY (
	book_id,
	user_id,
	concert_id,
	hall_id
);
---------------------------------------------------

commit;


