
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
  ('https://whitetravelsinternational.com/images/attractions/12-rock-cashel.jpg', 'Ireland', 'Rock of Cashel', 1);
INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://i.insider.com/5cd5e05293a15234b101b553?width=1136&format=jpeg', 'Slovenia', 'The Predjama Castle', 1);
INSERT
INTO
  moments
  (img_Url, title, description, creator_id)
VALUES
  ('https://p4.wallpaperbetter.com/wallpaper/483/238/498/german-villages-wallpaper-preview.jpg', 'Germany', 'Selva Negra', 1);
INSERT
INTO
  comments
  (comment, moment_id, creator_id)
VALUES
  ('Very beautiful', 2, 1);