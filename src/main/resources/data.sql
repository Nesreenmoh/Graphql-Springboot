insert into author(id,name,email) values(30L,'Robin Sharma','robin@gmail.com');
insert into author(id,name,email) values(11L,'Wyne Dyer','john@gmail.com');
insert into author(id,name,email) values(22L,'Gay Shetty','gay@gmail.com');

insert into post(id,category,description,title,author_id) values(11L,'Self Development','You must see!','5 Am club',30L);
insert into post(id, category,description,title,author_id) values(22L,'Self Development','You must see!','You will see it when you believe it',11L);
insert into post(id, category,description,title,author_id) values(33L,'Self Development','You must see!','Think Like the Monk',22L);
insert into post(id, category,description,title,author_id) values(44L,'Self Development','You must see!','Now I see Clearly',11L);

insert into comment(id,text,post_id,author_id) values(3L,'First Comment',33L,22L);