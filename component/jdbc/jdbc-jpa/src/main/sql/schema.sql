CREATE DATABASE bs_jpa
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

use bs_jpa;

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) NOT NULL,
                           `role_key` varchar(255) NOT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
                                `user_id` int(11) NOT NULL,
                                `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_name` varchar(45) NOT NULL,
                           `password` varchar(45) NOT NULL,
                           `email` varchar(45) DEFAULT NULL,
                           `phone_number` int(11) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;