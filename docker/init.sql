CREATE SCHEMA IF NOT EXISTS news_portal_schema;
CREATE TABLE news_db.news_portal_schema.categories(
Id INTEGER PRIMARY KEY ,
type VARCHAR(255) NOT NULL
);
INSERT INTO news_db.news_portal_schema.categories VALUES (1, 'SPORT');
INSERT INTO news_db.news_portal_schema.categories VALUES (2, 'ECONOMIC');
INSERT INTO news_db.news_portal_schema.categories VALUES (3, 'COMPUTER_SINCE');