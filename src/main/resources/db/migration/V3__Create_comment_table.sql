CREATE TABLE comment(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id BIGINT NOT NULL ,
    type int NOT NULL ,
    commentator int NOT NULL ,
    gmt_create BIGINT NOT NULL ,
    gmt_modified BIGINT NOT NULL ,
    like_count int DEFAULT 0,
    comment VARCHAR(1024) not null
)