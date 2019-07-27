create table user
(
	id int auto_increment primary key,
	account_id varchar(100) null,
	avatar_url varchar(100),
	name varchar(50) null,
	token char(36) null,
	gmt_create bigint null,
	gmt_modified bigint null
);

