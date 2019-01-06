INSERT INTO user (name) VALUES('Username One');
INSERT INTO user (name) VALUES('Username Two');
INSERT INTO user (name) VALUES('Username Three');

INSERT INTO ingredient (name) VALUES('Rijst');
INSERT INTO ingredient (name) VALUES('Sjalotten');
INSERT INTO ingredient (name) VALUES('Knoflook');
INSERT INTO ingredient (name) VALUES('Rode peper');
INSERT INTO ingredient (name) VALUES('Peper');
INSERT INTO ingredient (name) VALUES('Zout');
INSERT INTO ingredient (name) VALUES('Vanillesuiker');
INSERT INTO ingredient (name) VALUES('Ei');
INSERT INTO ingredient (name) VALUES('Bloem');
INSERT INTO ingredient (name) VALUES('Boter');
INSERT INTO ingredient (name) VALUES('Suiker');
INSERT INTO ingredient (name) VALUES('Boerenkool');
INSERT INTO ingredient (name) VALUES('Aardappel');
INSERT INTO ingredient (name) VALUES('Ui');
INSERT INTO ingredient (name) VALUES('Wortel');

INSERT INTO recipe (name, description, image_url, preparation_time, user_id, is_private)
VALUES ('Nasi Goreng',
	'Hak de sjalotten, knoflook, rode peper (met of zonder zaadjes) en laos grof. Maal fijn in een hakmolen of keukenmachine met de trassi en water. Je kunt dit uiteraard ook met de hand fijnhakken.',
	'https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg',
	20,
	1,
	0);

INSERT INTO recipe (name, description, image_url, preparation_time, user_id, is_private)
VALUES ('Kaiserschmarrn met abrikozencompote',
	'Splits de eieren en zet het eiwit apart in de koelkast. Meng de eidooiers met de bloem, water, vanillesuiker en zout in een kom tot een beslag. Eventueel kun je er nog wat rasp van een ½ citroen of sinaasappel aan toevoegen.',
	'https://www.francescakookt.nl/wp-content/uploads/kaiserschmarrn-met-abrikozencompote-1.jpg',
	10,
	1,
	0);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 1, 100);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 2, 10);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 3, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 4, 0.5);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 5, 3);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (1, 6, 2);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (2, 6, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (2, 7, 10);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (2, 8, 3);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (2, 9, 100);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount) VALUES (2, 10, 1);

