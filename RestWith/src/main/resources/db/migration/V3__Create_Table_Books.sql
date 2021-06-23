CREATE TABLE `books` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(200),
  `launch_date` DATETIME NOT NULL,
  `price` DECIMAL(65,2) NOT NULL,
  `title`  VARCHAR(400),
   PRIMARY KEY (`id`)
)