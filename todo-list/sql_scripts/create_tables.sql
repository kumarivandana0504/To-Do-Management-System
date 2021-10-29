CREATE DATABASE  IF NOT EXISTS `todo_management_directory`;
USE `todo_management_directory`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users` VALUES (1,'admin','admin');

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `userid` int(11) NOT NULL,
  `taskid` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Pending',
  PRIMARY KEY (`taskid`),
  CONSTRAINT `task_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `tasks` VALUES 
	(1,1,'Go for shopping','Pending'),
	(1,2,'Do workout','Pending'),
	(1,3,'Have Dinner at 9 PM','Pending');

