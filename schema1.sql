-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Student` (
  `idStudent` INT NOT NULL AUTO_INCREMENT,
  `Studentemail` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idStudent`),
  UNIQUE INDEX `Studentemail_UNIQUE` (`Studentemail` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Counselor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Counselor` (
  `idCounselor` INT NOT NULL AUTO_INCREMENT,
  `Counseloremail` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`idCounselor`),
  UNIQUE INDEX `Couseloremail_UNIQUE` (`Counseloremail` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Date` (
  `idDate` INT NOT NULL AUTO_INCREMENT,
  `DateValue` DATE NULL,
  `Counselor_id` INT NOT NULL,
  PRIMARY KEY (`idDate`),
  INDEX `fk_Date_Counselor1_idx` (`Counselor_id` ASC),
  CONSTRAINT `fk_Date_Counselor1`
    FOREIGN KEY (`Counselor_id`)
    REFERENCES `mydb`.`Counselor` (`idCounselor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Slot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Slot` (
  `idSlot` INT NOT NULL AUTO_INCREMENT,
  `SlotValue` VARCHAR(45) NULL,
  `Date_id` INT NOT NULL,
  `Status` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`idSlot`),
  INDEX `fk_Slot_Date1_idx` (`Date_id` ASC),
  CONSTRAINT `fk_Slot_Date1`
    FOREIGN KEY (`Date_id`)
    REFERENCES `mydb`.`Date` (`idDate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Student_Slot_Request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Student_Slot_Request` (
  `Student_id` INT NOT NULL,
  `Slot_id` INT NOT NULL,
  `idRequest` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idRequest`),
  INDEX `fk_Student_has_Slot_Slot1_idx` (`Slot_id` ASC),
  INDEX `fk_Student_has_Slot_Student1_idx` (`Student_id` ASC),
  CONSTRAINT `fk_Student_has_Slot_Student1`
    FOREIGN KEY (`Student_id`)
    REFERENCES `mydb`.`Student` (`idStudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Slot_Slot1`
    FOREIGN KEY (`Slot_id`)
    REFERENCES `mydb`.`Slot` (`idSlot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`StudentBookingHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`StudentBookingHistory` (
  `Student_id` INT NOT NULL,
  `Slot_id` INT NOT NULL,
  `idHistory` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_Student_has_Slot_Slot2_idx` (`Slot_id` ASC),
  INDEX `fk_Student_has_Slot_Student2_idx` (`Student_id` ASC),
  PRIMARY KEY (`idHistory`),
  CONSTRAINT `fk_Student_has_Slot_Student2`
    FOREIGN KEY (`Student_id`)
    REFERENCES `mydb`.`Student` (`idStudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Slot_Slot2`
    FOREIGN KEY (`Slot_id`)
    REFERENCES `mydb`.`Slot` (`idSlot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
