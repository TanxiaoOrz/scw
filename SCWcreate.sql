CREATE SCHEMA `scw` ;

CREATE TABLE `scw`.`user` (
  `UserId` INT NOT NULL,
  `UserCode` VARCHAR(45) NULL,
  `UserName` VARCHAR(45) NULL,
  `UserPass` VARCHAR(45) NULL,
  `UserType` ENUM('teacher', 'student') NULL,
  PRIMARY KEY (`UserId`));

CREATE TABLE `scw`.`notification` (
  `NotId` INT NOT NULL,
  `Publisher` INT NULL,
  `Content` VARCHAR(1000) NULL,
  PRIMARY KEY (`NotId`));
  
CREATE TABLE `scw`.`not_to_user` (
  `Notification` INT NOT NULL,
  `User` INT NOT NULL,
  `Status` INT NULL,
  PRIMARY KEY (`Notification`, `User`));

CREATE TABLE `scw`.`team` (
  `TeamId` INT NOT NULL,
  `TeamStatus` INT NULL,
  `TeamMember1` INT NULL,
  `TeamMember2` INT NULL,
  `TeamMember3` INT NULL,
  `TeamMember4` INT NULL,
  `TeamMember5` INT NULL,
  `TeamMember6` INT NULL,
  `TeamMember7` INT NULL,
  PRIMARY KEY (`TeamId`));


CREATE TABLE `scw`.`chat` (
  `ChatId` INT NOT NULL,
  `ChatMessage` VARCHAR(1000) NULL,
  `Speaker` INT NULL,
  `BelongTeam` INT NULL,
  `ChatTime` DATETIME NULL,
  PRIMARY KEY (`ChatId`));
  
CREATE TABLE `scw`.`resource` (
  `ResourceId` INT NOT NULL,
  `ResourceName` VARCHAR(45) NULL,
  `Owner` INT NULL,
  `BelongTeam` INT NULL,
  `Route` VARCHAR(1000) NULL,
  PRIMARY KEY (`ResourceId`));
  
CREATE TABLE `scw`.`study_work` (
  `WorkId` INT NOT NULL,
  `Publisher` INT NULL,
  `Content` VARCHAR(1000) NULL,
  `ReleaseTime` DATETIME NULL,
  `EndTime` DATETIME NULL,
  `ResourceRoute` VARCHAR(1000) NULL,
  `Status` INT NULL,
  PRIMARY KEY (`WorkId`));
  
  CREATE TABLE `scw`.`team_work` (
  `TeamWorkId` INT NOT NULL,
  `WorkDescription` VARCHAR(1000) NULL,
  `BelongTeam` INT NULL,
  `BelongWork` INT NULL,
  `ProductionRoute` VARCHAR(1000) NULL,
  `Status` INT NULL,
  PRIMARY KEY (`TeamWorkId`));
  
CREATE TABLE `scw`.`single_work` (
  `SingleWorkId` INT NOT NULL,
  `WorkDescription` VARCHAR(1000) NULL,
  `BelongStudent` INT NULL,
  `BelongWork` INT NULL,
  `ProductionRoute` VARCHAR(1000) NULL,
  `Status` INT NULL,
  PRIMARY KEY (`SingleWorkId`));
  
  CREATE TABLE `scw`.`comment` (
  `BelongTeamWork` INT NOT NULL,
  `Description` VARCHAR(1000) NULL,
  `score` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`BelongTeamWork`));



  
