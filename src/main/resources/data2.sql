
INSERT
INTO
  users
  (user_Name, user_Img)
VALUES
 ('Adventurer', 'https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg');


INSERT
INTO
  users
  (user_Name, user_Img)
VALUES
  ('Traveller', 'https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg');


INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://viajes.nationalgeographic.com.es/medio/2019/07/19/acantilados-de-moher-ser-yo-tiene-sus-privilegios_1b1cd0a5_1500x994.jpg', 'Spain', 'Asturias', 2);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://photos.altai-travel.com/1920x1040/photo-dun-pont-suspendu-au-pied-du-volcan-arenal-au-costa-rica-8257.jpg', 'Costa Rica', 'Wild nature', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://i.pinimg.com/originals/93/21/b2/9321b2e3f66b12777ff7e98a9860a597.jpg', 'Guitar', 'Nature', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://media.timeout.com/images/105856246/750/422/image.jpg', 'Slovenia', 'Predjama Castle', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://www.guiadealemania.com/wp-content/uploads/2009/08/selva-bach.jpg', 'Germany', 'Black Forest', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://sunridgefarms.com/wp-content/uploads/2018/03/woman-doing-yoga-on-mountain-small.jpg', 'Yoga', 'Yoga on mountain', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://thumb.mp-farm.com/75973771/preview.jpg', 'Norway', 'Landscape', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://motor.elpais.com/wp-content/uploads/2018/06/mercedes-vito-marco-polo.jpg', 'Van', 'Van adventure', 1);

INSERT
INTO
  comments
  (comment, moment_id, creator_id)
VALUES
  ('Very Beautiful', 1, 1);
INSERT
INTO
  comments
  (comment, moment_id, creator_id)
VALUES
  ('Amazing place', 1, 1);
INSERT
INTO
  comments
  (comment, moment_id, creator_id)
VALUES
  ('Music oh yeah!', 3, 1);
INSERT
INTO
  likes
  (lover_id, moment_id)
VALUES
  (1, 1);
INSERT
INTO
  likes
  (lover_id, moment_id)
VALUES
  (1, 2);
