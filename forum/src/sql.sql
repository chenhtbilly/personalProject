drop database if exists forum;

create database forum ;
use forum;


--bulid table 配置对了执行测试类/forum/test/TestHibernate.java可自动创建表
CREATE TABLE
    reply
    (
        id bigint NOT NULL AUTO_INCREMENT,
        content VARCHAR(255),
        ip VARCHAR(255),
        rtime DATETIME,
        theme_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT FK67612EAED9116FE FOREIGN KEY (theme_id) REFERENCES theme (id),
        INDEX FK67612EAED9116FE (theme_id)
    );
CREATE TABLE
    theme
    (
        id bigint NOT NULL AUTO_INCREMENT,
        title VARCHAR(255),
        content VARCHAR(255),
        ip VARCHAR(255),
        ptime DATETIME,
        lastptime DATETIME,
        PRIMARY KEY (id)
    );


select * from theme;

select theme0_.id as id1_0_, replies1_.id as id0_1_, theme0_.title as title1_0_, theme0_.content as 
content1_0_, theme0_.ip as ip1_0_, theme0_.ptime as ptime1_0_, theme0_.lastptime as lastptime1_0_, 
replies1_.content as content0_1_, replies1_.ip as ip0_1_, replies1_.rtime as rtime0_1_, replies1_.theme_id 
as theme5_0_1_, replies1_.theme_id as theme5_1_0__, replies1_.id as id0__ from theme theme0_ left outer join 
reply replies1_ on theme0_.id=replies1_.theme_id where theme0_.id=13 order by replies1_.rtime asc