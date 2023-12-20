USE donaart;

INSERT INTO `users` (id, active, email, first_name, last_name, password)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (2, 1, 'user@example.com', 'User', 'Userov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (3, 1, 'alex.dimova@gmail.com', 'Alex', 'Dimova', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');


INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);


INSERT INTO `categories` (`id`, `name`)
VALUES
    (1, 'Paintings'),
    (2, 'Drawings'),
    (3, 'Prints');

INSERT INTO `techniques` (`id`, `technique`, `category_id`, `name`)
VALUES
    (1, 'Original', 1, 'Acrylic'),
    (2, 'Original', 1, 'Oil'),
    (3, 'Original', 2, 'Pencil'),
    (4, 'Original', 2, 'Digital'),
    (5, 'Original', 3, 'Ink');

INSERT INTO `arts` (`id`, `description`, `style`, `image_url`, `dimensions`, `price`, `material`, `uuid`, `year`, `technique_id`, `seller_id`)
VALUES
    (1, 'field flowers', 'Still_life', 'https://en.m.wikipedia.org/wiki/File:Rachel_Ruysch_-_Still-Life_with_Flowers_-_Google_Art_Project.jpg', '88 x 73', 1289, 'Canvas', 'b72e6550-e365-43bf-aab2-b57cafc2db7c', 2010, 3, 1),
    (2, 'beautiful nature', 'Landscape', 'https://www.saatchiart.com/art/Painting-Galloping-horses-on-snow/2360481/11199657/view', '38 x 45', 2400, 'Canvas', 'b72e6550-e365-43bf-aab2-b57cafc2db74', 1989, 5, 2),
    (3, 'wild horses', 'Horses', 'https://upload.wikimedia.org/wikipedia/commons/b/bb/Horses_at_the_Porch.jpg', '53 x 49', 2225, 'Canvas', 'b72e6550-e365-43bf-aab2-b57cafc2db71', 1999, 3, 1),
    (4, 'my crazy mood', 'Abstract', 'https://upload.wikimedia.org/wikipedia/commons/a/a6/24x16_Abstract_Art.jpg', '20 x 55', 2490, 'Paper', 'b72e6550-e365-43bf-aab2-b57cafc2db72', 2001, 1, 2),
    (5, 'lovely flowers', 'Still_life', 'https://upload.wikimedia.org/wikipedia/commons/9/96/Ambrosius_Bosschaert_the_Elder_%28Dutch_-_Flower_Still_Life_-_Google_Art_Project.jpg', '67 x 73', 1130, 'Wood', 'b72e6550-e365-43bf-aab2-b57cafc2db73', 2015, 4, 2);
