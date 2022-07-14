
INSERT
INTO
  users
  (user_Name, user_Img)
VALUES
  ('Lau', 'https://i.pinimg.com/originals/a6/58/32/a65832155622ac173337874f02b218fb.png');

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://www.touristinjapan.com/wp-content/uploads/2019/05/ginkakuji-reginald-pentinio.jpg', 'Japan', 'City', 1);

INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://losviajesdenena.com/wp-content/uploads/2014/06/paisajes-de-islandia.jpg”', 'Island', 'Lorem ipsum...', 1);
INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://images4.bovpg.net/r/back/es/sale/58da3e400b9f0o.jpg', 'Irland', 'Lorem ipsum...', 1);
INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://mundoxdescubrir.com/wp-content/uploads/2019/07/suiza.jpg', 'Suiza', 'Lorem ipsum...', 1);
INSERT
INTO
  comments
  (comment, moment_id, creator_id)
VALUES
  ('very beautiful', 1, 1);