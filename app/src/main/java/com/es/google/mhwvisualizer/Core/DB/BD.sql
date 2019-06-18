DROP DATABASE IF EXISTS GEAR;
CREATE DATABASE IF NOT EXISTS GEAR DEFAULT CHARACTER SET utf8;
USE GEAR;

CREATE TABLE IF NOT EXISTS `WEAPON`(
  `ID` INT NOT NULL,
  `rareza` INT NOT NULL,
  `danho` INT NOT NULL,
  `defensa` INT NOT NULL,
  `elemento` varchar(9) NOT NULL,
  `elemento_cantidad` INT NOT NULL,
  `elemento_oculto` BOOLEAN NOT NULL,
  `afinidad` INT NOT NULL,
  `sello_ancianos` varchar(5) NOT NULL,
	/*
		huecosJoyas (tabla aparte)
		mejoras (tabla aparte)
	*/
  PRIMARY KEY (`ID`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BOW`(
	/* 
		Es posible que los arcos no tengan otra cosa aparte de revestimientos
		asi que de momento usar esta tabla como de union de revestimientos 
	*/
  `ID_arma_base` INT NOT NULL,
  `revestimiento` varchar(13) NOT NULL,
  PRIMARY KEY (`ID_arma_base` , `revestimiento`),
  CONSTRAINT `fk_arma_base_BOW`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `CHARGEBLADE`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_CHARGEBLADE`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `DUALBLADES`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  `elemento_secundario` varchar(9),
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_DUALBLADES`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GREATSWORD`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_GREATSWORD`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GUNLANCE`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  `proyectil_tipo` varchar(6) NOT NULL,
  `proyectil_lvl` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_GUNLANCE`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `HAMMER`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_HAMMER`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `HEAVYBOWGUN`(
  `ID_arma_base` INT NOT NULL,
  `desvio` varchar(5) NOT NULL,
  `municion_especial` varchar(14) NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_HEAVYBOWGUN`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `HUNTINGHORN`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_HUNTINGHORN`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `HUNTINGHORNNOTES`(
  `ID_HH` INT NOT NULL,
  `nota` varchar(8) NOT NULL,
  PRIMARY KEY (`ID_HH` , `nota`),
  CONSTRAINT `fk_arma_base_HUNTINGHORNNOTES`
    FOREIGN KEY (`ID_HH`)
    REFERENCES `HUNTINGHORN` (`ID_arma_base`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `INSECTGLAIVE`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  `bonus_insecto` varchar(10) NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_INSECTGLAIVE`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `LANCE`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_LANCE`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `LIGHTBOWGUN`(
  `ID_arma_base` INT NOT NULL,
  `desvio` varchar(5) NOT NULL,
  /*
	TODO: TIPO DE MUNICION Y COSAS
  */
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_LIGHTBOWGUN`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `LONGSWORD`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_LONGSWORD`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `SWITCHAXE`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_SWITCHAXE`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `SWORDANDSHIELD`(
  `ID_arma_base` INT NOT NULL,
  `afilado_rojo` INT NOT NULL,
  `afilado_naranja` INT NOT NULL,
  `afilado_amarillo` INT NOT NULL,
  `afilado_verde` INT NOT NULL,
  `afilado_azul` INT NOT NULL,
  `afilado_blanco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base`),
  CONSTRAINT `fk_arma_base_SWORDANDSHIELD`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `KINECTINSECT`(
  `ID` INT NOT NULL,
  `tipo` varchar(8) NOT NULL,
  `efecto` varchar(8) NOT NULL,
  `stat_elemento` INT NOT NULL,
  `stat_poder` INT NOT NULL,
  `stat_velocidad` INT NOT NULL,
  `stat_curacion` INT NOT NULL,
  PRIMARY KEY (`ID`)
)
ENGINE = InnoDB;
  
CREATE TABLE IF NOT EXISTS `MEJORAS` (
  `ID_arma_base` INT NOT NULL,
  `ID_arma_mejora` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base` , `ID_arma_mejora`),
  CONSTRAINT `fk_arma_base_MEJORAS`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_arma_mejora`
    FOREIGN KEY (`ID_arma_mejora`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;
  
CREATE TABLE IF NOT EXISTS `HUECOS` (
  `ID_arma_base` INT NOT NULL,
  `hueco` INT NOT NULL,
  PRIMARY KEY (`ID_arma_base` , `hueco`),
  CONSTRAINT `fk_arma_base_HUECOS`
    FOREIGN KEY (`ID_arma_base`)
    REFERENCES `WEAPON` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;