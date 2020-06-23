insert into course (id, name, created_date, last_updated_date) values (1001, 'JPA in 100 Steps', now(), now());
insert into course (id, name, created_date, last_updated_date) values (1002, 'Spring in 100 Steps', now(), now());
insert into course (id, name, created_date, last_updated_date) values (1003, 'Spring Boot in 100 Steps', now(), now());

insert into passport (id, number) values (3001, 'W123121');
insert into passport (id, number) values (3002, 'BE123122');
insert into passport (id, number) values (3003, 'S123123');

insert into student (id, passport_id, name) values (2001, 3001, 'Winston Pidor');
insert into student (id, passport_id, name) values (2002, 3002, 'Bernadette Pidor');
insert into student (id, passport_id, name) values (2003, 3003, 'Solenn Pidor');

insert into review (id, rating, description, course_id, student_id) values (4001, '5', 'Great!', 1001, 2001);
insert into review (id, rating, description, course_id, student_id) values (4002, '4', 'Love it!', 1001, 2002);
insert into review (id, rating, description, course_id, student_id) values (4003, '1', 'Hmmmm...', 1003, 2001);

insert into student_courses (student_id, course_id) values (2001, 1001);
insert into student_courses (student_id, course_id) values (2002, 1001);
insert into student_courses (student_id, course_id) values (2003, 1001);
insert into student_courses (student_id, course_id) values (2001, 1003);
