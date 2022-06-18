SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS `file` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `file_id` varchar(255) DEFAULT NULL,
                      `file_name` varchar(255) DEFAULT NULL,
                      `files` longblob,
                      `create_time` datetime DEFAULT NULL,
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `operation_log` (
                               `id` varchar(255) DEFAULT NULL,
                               `create_time` timestamp NULL DEFAULT NULL,
                               `level` int(11) DEFAULT NULL,
                               `operation_unit` varchar(255) DEFAULT NULL,
                               `method` varchar(255) DEFAULT NULL,
                               `args` varchar(255) DEFAULT NULL,
                               `user_id` varchar(255) DEFAULT NULL,
                               `user_name` varchar(255) DEFAULT NULL,
                               `description` varchar(255) DEFAULT NULL,
                               `operation_type` varchar(255) DEFAULT NULL,
                               `run_time` bigint(20) DEFAULT NULL,
                               `return_value` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `school` (
                        `id` int(11) NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `school` varchar(255) DEFAULT NULL,
                        `zy` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `name_` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `sys_menu` (
                          `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
                          `menu_name` varchar(56) DEFAULT NULL COMMENT '菜单名称',
                          `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单url',
                          `parent_id` int(11) DEFAULT NULL COMMENT '父菜单id',
                          `level` int(2) DEFAULT '1' COMMENT '菜单级别（一级菜单，二级菜单，三级菜单）',
                          `menu_status` int(1) DEFAULT '0' COMMENT '菜单状态（1禁用,0启用）',
                          PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `name` varchar(255) DEFAULT NULL,
                      `age` int(11) DEFAULT NULL,
                      `sex` varchar(255) DEFAULT NULL,
                      `password` varchar(255) DEFAULT NULL,
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user_info` (
                           `id` int(11) NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `age` int(11) DEFAULT NULL,
                           `gender` varchar(255) DEFAULT NULL,
                           `address` varchar(255) DEFAULT NULL,
                           `tel` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `name_` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `my_insert`()
BEGIN
DECLARE n int DEFAULT 1;
loopname:LOOP
INSERT INTO user_info(id,name,age,gender,address,tel)VALUES(n,'shirs',n,2,'深圳',599073);
SET n=n+1;
IF n=1500000 THEN
LEAVE loopname;
END IF;
END LOOP loopname;
END
;;
DELIMITER ;*/
