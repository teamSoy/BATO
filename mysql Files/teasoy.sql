-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 15, 2017 at 11:24 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teasoy`
--
CREATE DATABASE IF NOT EXISTS `teasoy` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `teasoy`;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `account_id` int(10) NOT NULL,
  `account_type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_id`, `account_type`) VALUES
(1, 'User'),
(2, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
CREATE TABLE `drivers` (
  `driver_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `vehicle_id` int(10) NOT NULL,
  `drivers_lisence` varchar(13) NOT NULL,
  `plate_number` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `emergency_hotlines`
--

DROP TABLE IF EXISTS `emergency_hotlines`;
CREATE TABLE `emergency_hotlines` (
  `hotline_id` int(10) NOT NULL,
  `hotline_name` varchar(30) NOT NULL,
  `hotline_number` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `followers`
--

DROP TABLE IF EXISTS `followers`;
CREATE TABLE `followers` (
  `follower_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `user_id2` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
  `report_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `situation_id` int(10) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  `descriptions` text NOT NULL,
  `date_reported` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `report_picture1` mediumblob NOT NULL,
  `report_picture2` mediumblob,
  `report_picture3` mediumblob,
  `status` varchar(20) DEFAULT NULL,
  `commends` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `situation`
--

DROP TABLE IF EXISTS `situation`;
CREATE TABLE `situation` (
  `situation_id` int(5) NOT NULL,
  `situation_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `situation`
--

INSERT INTO `situation` (`situation_id`, `situation_name`) VALUES
(1, 'Collision'),
(2, 'Flood'),
(3, 'Fire'),
(4, 'Heavy Traffic'),
(5, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(10) NOT NULL,
  `account_id` int(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `birthday` date NOT NULL,
  `age` int(3) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `contact_no` varchar(11) NOT NULL,
  `profile_picture` mediumblob NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `account_id`, `first_name`, `last_name`, `gender`, `birthday`, `age`, `email_address`, `contact_no`, `profile_picture`, `username`, `password`) VALUES
(1, 1, 'Paul Vincent', 'Nonat', 'Male', '1996-03-03', 20, 'paulvincentnonat@gmail.com', '09953675020', 0x313233353233343132333132, 'paul028', 'nonat028');

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles` (
  `vehicle_id` int(10) NOT NULL,
  `vehicle_brand` varchar(20) NOT NULL,
  `vehicle_type` varchar(20) NOT NULL,
  `vehicle_model` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`driver_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indexes for table `emergency_hotlines`
--
ALTER TABLE `emergency_hotlines`
  ADD PRIMARY KEY (`hotline_id`);

--
-- Indexes for table `followers`
--
ALTER TABLE `followers`
  ADD PRIMARY KEY (`follower_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_id2` (`user_id2`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `situation_id` (`situation_id`);

--
-- Indexes for table `situation`
--
ALTER TABLE `situation`
  ADD PRIMARY KEY (`situation_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`vehicle_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `account_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `driver_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `followers`
--
ALTER TABLE `followers`
  MODIFY `follower_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `situation`
--
ALTER TABLE `situation`
  MODIFY `situation_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `drivers`
--
ALTER TABLE `drivers`
  ADD CONSTRAINT `drivers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `drivers_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`);

--
-- Constraints for table `followers`
--
ALTER TABLE `followers`
  ADD CONSTRAINT `followers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `followers_ibfk_2` FOREIGN KEY (`user_id2`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`situation_id`) REFERENCES `situation` (`situation_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
