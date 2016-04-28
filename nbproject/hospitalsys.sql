-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2016 at 12:22 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitalsys`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(125) DEFAULT NULL,
  `nationalID` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `nationalID`, `username`, `password`) VALUES
(1, 'default admin', 12345678, 'admin', '21232f297a57a5a743894a0e4a801fc3'),
(2, 'Mfuon', 4512114, 'mfuon', '7838e1976dce522c32b6139ab16017cf');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `Doctor_id` int(11) NOT NULL,
  `Patient_id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Receptionist_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cashier`
--

CREATE TABLE `cashier` (
  `id` int(11) NOT NULL,
  `name` varchar(125) DEFAULT NULL,
  `nationalID` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL,
  `description` text,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Doctor_id` int(11) NOT NULL,
  `Patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `nationalID` int(11) NOT NULL,
  `name` varchar(125) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `nationalID`, `name`, `username`, `password`) VALUES
(1, 784575, 'dr. lenny', 'doctor', '9a09b4dfda82e3e665e31092d1c3ec8d'),
(2, 4578124, 'Mfuon', 'doctor', 'f9f16d97c90d8c6f2cab37bb6d1f1992');

-- --------------------------------------------------------

--
-- Table structure for table `labtech`
--

CREATE TABLE `labtech` (
  `id` int(11) NOT NULL,
  `name` varchar(125) NOT NULL,
  `nationalID` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `labtech`
--

INSERT INTO `labtech` (`id`, `name`, `nationalID`, `username`, `password`) VALUES
(1, 'Robert', 4522136, 'labtech', 'f9664ea1803311b35f81d07d8c9e072d');

-- --------------------------------------------------------

--
-- Table structure for table `labtest`
--

CREATE TABLE `labtest` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Patient_id` int(11) NOT NULL,
  `LabTech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `labtest_has_test`
--

CREATE TABLE `labtest_has_test` (
  `LabTest_id` int(11) NOT NULL,
  `Test_id` int(11) NOT NULL,
  `description` text,
  `results` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `nationalID` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `blood_group` varchar(10) DEFAULT NULL,
  `health_history` text,
  `cellphone` int(11) DEFAULT NULL,
  `homephone` int(11) DEFAULT NULL,
  `postalcode` int(11) DEFAULT NULL,
  `po_box` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `street` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `patientbill`
--

CREATE TABLE `patientbill` (
  `id` int(11) NOT NULL,
  `labtestfee` double DEFAULT NULL,
  `doctorsfee` double DEFAULT NULL,
  `totalfee` double DEFAULT NULL,
  `paidstatus` tinyint(1) DEFAULT NULL,
  `Patient_id` int(11) NOT NULL,
  `Cashier_id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `patientrecord`
--

CREATE TABLE `patientrecord` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Diagnosis_id` int(11) DEFAULT NULL,
  `Prescription_id` int(11) DEFAULT NULL,
  `LabTest_id` int(11) DEFAULT NULL,
  `Appointment_id` int(11) DEFAULT NULL,
  `PatientBill_id` int(11) DEFAULT NULL,
  `Patient_id` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Doctor_id` int(11) NOT NULL,
  `description` text,
  `Patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `receptionist`
--

CREATE TABLE `receptionist` (
  `id` int(11) NOT NULL,
  `nationalID` int(11) NOT NULL,
  `name` varchar(125) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `receptionist`
--

INSERT INTO `receptionist` (`id`, `nationalID`, `name`, `username`, `password`) VALUES
(1, 45454784, 'Jane', 'recept', 'e75d4fcd5337aff0ede391007475db7f');

-- --------------------------------------------------------

--
-- Table structure for table `securityissue`
--

CREATE TABLE `securityissue` (
  `id` int(11) NOT NULL,
  `PatientRecord_id` int(11) NOT NULL,
  `description` text,
  `solvedstatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `charge` double DEFAULT NULL,
  `doctorsfee` int(11) DEFAULT '200'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `title`, `charge`, `doctorsfee`) VALUES
(1, 'blood Test', 100, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Appointment_Doctor1_idx` (`Doctor_id`),
  ADD KEY `fk_Appointment_Patient1_idx` (`Patient_id`),
  ADD KEY `fk_Appointment_Receptionist1_idx` (`Receptionist_id`);

--
-- Indexes for table `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Diagnosis_Doctor1_idx` (`Doctor_id`),
  ADD KEY `fk_Diagnosis_Patient1_idx` (`Patient_id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nationalID_UNIQUE` (`nationalID`);

--
-- Indexes for table `labtech`
--
ALTER TABLE `labtech`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nationalID_UNIQUE` (`nationalID`);

--
-- Indexes for table `labtest`
--
ALTER TABLE `labtest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_LabTest_Patient1_idx` (`Patient_id`),
  ADD KEY `fk_LabTest_LabTech1_idx` (`LabTech_id`);

--
-- Indexes for table `labtest_has_test`
--
ALTER TABLE `labtest_has_test`
  ADD PRIMARY KEY (`LabTest_id`,`Test_id`),
  ADD KEY `fk_LabTest_has_Test_Test1_idx` (`Test_id`),
  ADD KEY `fk_LabTest_has_Test_LabTest1_idx` (`LabTest_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patientbill`
--
ALTER TABLE `patientbill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_PatientBill_Patient1_idx` (`Patient_id`),
  ADD KEY `fk_PatientBill_Cashier1_idx` (`Cashier_id`);

--
-- Indexes for table `patientrecord`
--
ALTER TABLE `patientrecord`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_PatientRecord_Diagnosis1_idx` (`Diagnosis_id`),
  ADD KEY `fk_PatientRecord_Prescription1_idx` (`Prescription_id`),
  ADD KEY `fk_PatientRecord_LabTest1_idx` (`LabTest_id`),
  ADD KEY `fk_PatientRecord_Appointment1_idx` (`Appointment_id`),
  ADD KEY `fk_PatientRecord_PatientBill1_idx` (`PatientBill_id`),
  ADD KEY `fk_PatientRecord_Patient1_idx` (`Patient_id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Prescription_Doctor1_idx` (`Doctor_id`),
  ADD KEY `fk_Prescription_Patient1_idx` (`Patient_id`);

--
-- Indexes for table `receptionist`
--
ALTER TABLE `receptionist`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nationalID_UNIQUE` (`nationalID`);

--
-- Indexes for table `securityissue`
--
ALTER TABLE `securityissue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_SecurityIssue_PatientRecord1_idx` (`PatientRecord_id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cashier`
--
ALTER TABLE `cashier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `labtech`
--
ALTER TABLE `labtech`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `labtest`
--
ALTER TABLE `labtest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patientbill`
--
ALTER TABLE `patientbill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patientrecord`
--
ALTER TABLE `patientrecord`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `receptionist`
--
ALTER TABLE `receptionist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `securityissue`
--
ALTER TABLE `securityissue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_Appointment_Doctor1` FOREIGN KEY (`Doctor_id`) REFERENCES `doctor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Appointment_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Appointment_Receptionist1` FOREIGN KEY (`Receptionist_id`) REFERENCES `receptionist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD CONSTRAINT `fk_Diagnosis_Doctor1` FOREIGN KEY (`Doctor_id`) REFERENCES `doctor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Diagnosis_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `labtest`
--
ALTER TABLE `labtest`
  ADD CONSTRAINT `fk_LabTest_LabTech1` FOREIGN KEY (`LabTech_id`) REFERENCES `labtech` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_LabTest_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `labtest_has_test`
--
ALTER TABLE `labtest_has_test`
  ADD CONSTRAINT `fk_LabTest_has_Test_LabTest1` FOREIGN KEY (`LabTest_id`) REFERENCES `labtest` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_LabTest_has_Test_Test1` FOREIGN KEY (`Test_id`) REFERENCES `test` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patientbill`
--
ALTER TABLE `patientbill`
  ADD CONSTRAINT `fk_PatientBill_Cashier1` FOREIGN KEY (`Cashier_id`) REFERENCES `cashier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientBill_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patientrecord`
--
ALTER TABLE `patientrecord`
  ADD CONSTRAINT `fk_PatientRecord_Appointment1` FOREIGN KEY (`Appointment_id`) REFERENCES `appointment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientRecord_Diagnosis1` FOREIGN KEY (`Diagnosis_id`) REFERENCES `diagnosis` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientRecord_LabTest1` FOREIGN KEY (`LabTest_id`) REFERENCES `labtest` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientRecord_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientRecord_PatientBill1` FOREIGN KEY (`PatientBill_id`) REFERENCES `patientbill` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PatientRecord_Prescription1` FOREIGN KEY (`Prescription_id`) REFERENCES `prescription` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `fk_Prescription_Doctor1` FOREIGN KEY (`Doctor_id`) REFERENCES `doctor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Prescription_Patient1` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `securityissue`
--
ALTER TABLE `securityissue`
  ADD CONSTRAINT `fk_SecurityIssue_PatientRecord1` FOREIGN KEY (`PatientRecord_id`) REFERENCES `patientrecord` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
