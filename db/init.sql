DROP DATABASE IF EXISTS `epam_project_db`;

CREATE DATABASE `epam_project_db` DEFAULT CHARACTER SET utf8;

USE `epam_project_db`;

CREATE TABLE `user` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `account` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `client` VARCHAR(255) NOT NULL,
    `balance` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `transfer` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `source_id` INTEGER,
    `destination_id` INTEGER,
    `amount` BIGINT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `operator_id` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

ALTER TABLE `transfer`
ADD CONSTRAINT `FK_transfer_RT_source_account`
FOREIGN KEY (`source_id`)
REFERENCES `account`(`id`)
ON UPDATE RESTRICT
ON DELETE RESTRICT;

ALTER TABLE `transfer`
ADD CONSTRAINT `FK_transfer_RT_destination_account`
FOREIGN KEY (`destination_id`)
REFERENCES `account`(`id`)
ON UPDATE RESTRICT
ON DELETE RESTRICT;

ALTER TABLE `transfer`
ADD CONSTRAINT `FK_transfer_RT_operator_user`
FOREIGN KEY (`operator_id`)
REFERENCES `user`(`id`)
ON UPDATE RESTRICT
ON DELETE RESTRICT;

INSERT INTO `user`
(`id`, `login`,   `password`, `role`)
VALUES
(1,    "cass1",   "12345",    0),
(2,    "cass2",   "12345",    0),
(3,    "manager", "12345",    1),
(4,    "admin",   "12345",    2);

INSERT INTO `account`
(`id`, `client`,   `balance`)
VALUES
(1,    "Иванов",   1100),
(2,    "Петров",   2200),
(3,    "Сидоров",  2200),
(4,    "Васильев", 4200);

INSERT INTO `transfer`
(`id`, `source_id`, `destination_id`, `amount`, `date`,                `operator_id`)
VALUES
(1,    4,           1,                100,      '2015-06-01 12:00:00', 1),
(2,    4,           2,                200,      '2015-06-01 12:05:00', 1),
(3,    3,           4,                500,      '2016-01-30 09:30:00', 3),
(4,    3,           NULL,             300,      '2016-02-15 20:55:00', 2),
(5,    NULL,        3,                100,      '2016-02-17 08:05:00', 2);