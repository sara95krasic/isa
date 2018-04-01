insert into theater(name, theater_type, address, description) values ('Arena Cineplex', 'MOVIE_THEATER', 'Bulevar Mihajla Pupina 3, Novi Sad 21000', 'Dobar bioskop!');
insert into theater(name, theater_type, address, description) values ('BIG TC Bioskop', 'MOVIE_THEATER', 'Sentandrejski put 11, BIG CEE Novi Sad, Novi Sad 21000', 'okej bioskop...');
insert into theater(name, theater_type, address, description) values ('BSKP', 'MOVIE_THEATER', '170e Ustanicka, 11050, Beograd (Zvezdara), Grad Beograd', 'sanfsnf90nsdifnasdnfasd js f jsajjf asdjf sj fja fsadn fsdj fasjdfja sdjfjasd f k nan ajk njkans jdknfaj n k nan ajk njkans jdknfaj n ');
insert into theater(name, theater_type, address, description) values ('SNP', 'PLAY_THEATER', 'Pozorisni trg 1, Novi Sad 21000', 'Srpsko narodno pozoriste je poyoriste u novom sadu itd sanfsnf90nsdifnasdnfasd js f jsajjf asdjf sj fja fsadn fsdjsanfsnf90nsdifnasdnfasd js f jsajjf asdjf sj fja fsadn fsdj');
insert into theater(name, theater_type, address, description) values ('Pozoriste Mladih', 'PLAY_THEATER', 'Ignjata Pavlasa 4, Novi Sad 21000', 'Pozoriste mladih lorem ipsum masdfmamsdfnansdfjkasnddkjfnasdnf n kjansdjk nan ajk njkans jdknfaj n an anskdn fkansdjk fnakn k nan ajk njkans jdknfaj n k nan ajk njkans jdknfaj n ');

insert into review(date, rating, theater_id) values ('2012-08-21', 4, 1);

insert into projection (title, runtime, genre, director, description) values ('Ready Player One', 140, 'Akcija, Fantastika', 'Pera Peric', 'When the creator of a virtual reality world called the OASIS dies, he releases a video in which he challenges all OASIS users to find his Easter Egg, which will give the finder his fortune.');
insert into projection (title, runtime, genre, director, description) values ('Tomb Raider', 120, 'Akcija', 'Djoka Djokic', 'Lara Croft, the fiercely independent daughter of a missing adventurer, must push herself beyond her limits when she finds herself on the island where her father disappeared.');
insert into projection (title, runtime, genre, director, description) values ('Pčelica Maja 2: medene igre 3D', 120, 'Deciji', 'Milan Milanko', 'Pčelica Maja se pojavila pre više od stotinu godina i ubrzo postala jedna od najčitanijih knjiga tog vremena, omiljena i kod odraslih i kod dece. Nakon prvog filma koji je oduševio publiku svih generacija, stiže nam nastavak medene priče.');
insert into projection (title, runtime, genre, director, description) values ('Marija Magdalena', 130, 'Drama', 'Ivana Ivanovic', 'Film MARIJA MAGDALENA na autentičan način prikazuje snažnu i inspirativnu ljudsku priču o jednoj od najmisterioznijih, neshvaćenih duhovnih ličnosti u istoriji čovečanstva.');
insert into projection (title, runtime, genre, director, description) values ('Bitka za Pacifik - Pobuna', 120, 'Akcija, Sci-Fi', 'Djoka Djokic', 'Globalni sukob između čudovišta za masovno uništenje koja nisu sa planete Zemlje i super mašina koje su stvorene da ih unište, a kojima upravljaju ljudi, biće samo uvod u napad na čovečanstvo epskih razmera, u filmu BITKA ZA PACIFIK – POBUNA.');
insert into projection (title, runtime, genre, director, description) values ('Red Sparrow', 120, 'Drama', 'Lala Lalic', 'Priča je adaptacija špijunskog romana Džejsona Metjusa, smeštena u modernoj Rusiji, koja prati operativca Dominiku Jegorovu (Dženifer Lorens).');
insert into projection (title, runtime, genre, director, description) values ('Labudovo Jezero', 160, 'Balet', 'P. I. Cajkovski', 'P. I. Čajkovski, koreografija: Vladimir Logunov, dirigent: Mikica Jevtić');
insert into projection (title, runtime, genre, director, description) values ('Rodljupci', 60, 'Komedija', 'Jovan Sterija Popovic', 'Jovan Sterija Popović, režija: Radoje Čupić');
insert into projection (title, runtime, genre, director, description) values ('Madam Baterflaj', 150, 'Opera', 'Đakomo Pučini', 'Dirigent: Đanluka Marčano');

insert into projection_rating (projection_id, rating) values (1, 4);
insert into projection_rating (projection_id, rating) values (1, 3);
insert into projection_rating (projection_id, rating) values (1, 3);
insert into projection_rating (projection_id, rating) values (1, 3);
insert into projection_rating (projection_id, rating) values (1, 4);
insert into projection_rating (projection_id, rating) values (1, 5);
insert into projection_rating (projection_id, rating) values (2, 4);
insert into projection_rating (projection_id, rating) values (2, 3);
insert into projection_rating (projection_id, rating) values (2, 3);
insert into projection_rating (projection_id, rating) values (2, 3);
insert into projection_rating (projection_id, rating) values (3, 4);
insert into projection_rating (projection_id, rating) values (5, 4);
insert into projection_rating (projection_id, rating) values (5, 4);
insert into projection_rating (projection_id, rating) values (6, 3);
insert into projection_rating (projection_id, rating) values (7, 2);
insert into projection_rating (projection_id, rating) values (7, 5);
insert into projection_rating (projection_id, rating) values (7, 2);
insert into projection_rating (projection_id, rating) values (7, 1);

insert into actor values ('Jennifer Lawrence');
insert into actor values ('Joel Edgerton');
insert into actor values ('Matthias Schoenaerts');
insert into actor values ('Charlotte Rampling');
insert into actor values ('Mary-Louise Parker');
insert into actor values ('Ciarán Hinds');
insert into actor values ('Joely Richardson');
insert into actor values ('Jeremy Irons');
insert into actor values ('Thekla Reuten');
insert into actor values ('Douglas Hodge');
insert into actor values ('Sasha Frolova');
insert into actor values ('Sakina Jaffrey');
insert into actor values ('Sergei Polunin');
insert into actor values ('Sebastian Hülk');

insert into projection_actors (projection_id, actors_name) values (1, 'Ciarán Hinds');
insert into projection_actors (projection_id, actors_name) values (1, 'Jeremy Irons');
insert into projection_actors (projection_id, actors_name) values (1, 'Thekla Reuten');
insert into projection_actors (projection_id, actors_name) values (2, 'Ciarán Hinds');
insert into projection_actors (projection_id, actors_name) values (2, 'Thekla Reuten');
insert into projection_actors (projection_id, actors_name) values (3, 'Sergei Polunin');
insert into projection_actors (projection_id, actors_name) values (4, 'Joely Richardson');
insert into projection_actors (projection_id, actors_name) values (4, 'Charlotte Rampling');
insert into projection_actors (projection_id, actors_name) values (5, 'Jennifer Lawrence');
insert into projection_actors (projection_id, actors_name) values (5, 'Jeremy Irons');
insert into projection_actors (projection_id, actors_name) values (6, 'Sergei Polunin');
insert into projection_actors (projection_id, actors_name) values (6, 'Sebastian Hülk');
insert into projection_actors (projection_id, actors_name) values (7, 'Mary-Louise Parker');
insert into projection_actors (projection_id, actors_name) values (7, 'Jeremy Irons');
insert into projection_actors (projection_id, actors_name) values (7, 'Thekla Reuten');
insert into projection_actors (projection_id, actors_name) values (7, 'Joely Richardson');

insert into hall (theater_id, label) values (1, '1');
insert into hall (theater_id, label) values (1, '2');
insert into hall (theater_id, label) values (1, '3');
insert into hall (theater_id, label) values (1, '4A');
insert into hall (theater_id, label) values (1, '4B');
insert into hall (theater_id, label) values (1, '5');
insert into hall (theater_id, label) values (2, '1');
insert into hall (theater_id, label) values (2, '2');
insert into hall (theater_id, label) values (2, '3');
insert into hall (theater_id, label) values (3, '1');
insert into hall (theater_id, label) values (3, '2');
insert into hall (theater_id, label) values (3, '3');
insert into hall (theater_id, label) values (4, 'Kamerna scena 1');
insert into hall (theater_id, label) values (4, 'Kamerna scena 2');
insert into hall (theater_id, label) values (4, 'Pera Dobrinović');
insert into hall (theater_id, label) values (5, 'Kamerna scena');
insert into hall (theater_id, label) values (5, 'Jovan Đorđević');

insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (1, 1, '1', '2017-05-01', '14:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (1, 1, '2', '2017-05-01', '16:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (1, 1, '2', '2017-05-01', '18:00', 350, 20);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (2, 1, '2', '2017-05-01', '15:00', 400, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (2, 1, '2', '2017-05-02', '15:00', 400, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (2, 1, '4A', '2017-05-02', '17:00', 400, 20);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (3, 1, '4A', '2017-05-01', '14:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (4, 1, '4B', '2017-05-03', '14:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (1, 2, '1', '2017-05-01', '18:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (1, 2, '2', '2017-05-01', '20:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (2, 2, '1', '2017-05-02', '20:00', 300, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (2, 2, '1', '2017-05-02', '22:00', 450, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (5, 2, '1', '2017-05-01', '14:00', 300, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (5, 2, '3', '2017-05-01', '20:00', 350, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (5, 2, '3', '2017-05-01', '22:00', 350, 30);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (4, 3, '3', '2017-05-03', '14:00', 200, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (4, 3, '1', '2017-05-03', '16:00', 200, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (4, 3, '1', '2017-05-03', '19:00', 250, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (4, 3, '2', '2017-05-04', '21:00', 250, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (5, 3, '2', '2017-05-10', '18:00', 250, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (6, 4, 'Pera Dobrinović', '2017-05-02', '14:00', 750, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (6, 4, 'Pera Dobrinović', '2017-05-03', '14:00', 750, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 4, 'Kamerna scena 1', '2017-05-01', '14:00', 750, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 4, 'Kamerna scena 1', '2017-05-01', '16:00', 650, 20);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 4, 'Kamerna scena 1', '2017-05-03', '14:00', 550, 10);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 4, 'Kamerna scena 2', '2017-05-04', '14:00', 750, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 4, 'Pera Dobrinović', '2017-05-05', '14:00', 700, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (8, 4, 'Kamerna scena 1', '2017-05-02', '14:00', 700, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (8, 4, 'Kamerna scena 1', '2017-05-03', '14:00', 700, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (8, 4, 'Kamerna scena 1', '2017-05-04', '14:00', 900, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (8, 4, 'Pera Dobrinović', '2017-05-05', '14:00', 500, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (6, 5, 'Jovan Đorđević', '2017-05-01', '14:00', 650, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (6, 5, 'Kamerna scena', '2017-05-04', '14:00', 650, 40);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 5, 'Kamerna scena', '2017-05-04', '14:00', 650, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (7, 5, 'Kamerna scena', '2017-05-06', '14:00', 850, null);
insert into projection_date (projection_id, hall_theater_id, hall_label, date, time, price, discount) values (8, 5, 'Kamerna scena', '2017-05-06', '14:00', 850, null);

