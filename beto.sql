-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para proyectofinal
CREATE DATABASE IF NOT EXISTS `proyectofinal` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `proyectofinal`;

-- Volcando estructura para tabla proyectofinal.cias
CREATE TABLE IF NOT EXISTS `cias` (
  `cod_cia` bigint(20) NOT NULL,
  `abreviatura` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cod_cia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.empresa
CREATE TABLE IF NOT EXISTS `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abreviatura` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipo_empresa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.entidad
CREATE TABLE IF NOT EXISTS `entidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.item
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.motivo
CREATE TABLE IF NOT EXISTS `motivo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `servicio_id` bigint(20) NOT NULL,
  `tipo_tramite_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkob5khvf3q156w78ctpgom0tp` (`servicio_id`),
  KEY `FKhgorvfusrqdb94g3ttki1xt9r` (`tipo_tramite_id`),
  CONSTRAINT `FKhgorvfusrqdb94g3ttki1xt9r` FOREIGN KEY (`tipo_tramite_id`) REFERENCES `tipo_tramite` (`id`),
  CONSTRAINT `FKkob5khvf3q156w78ctpgom0tp` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.orden_de_trabajo
CREATE TABLE IF NOT EXISTS `orden_de_trabajo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sector` varchar(200) DEFAULT NULL,
  `responsable` varchar(200) DEFAULT NULL,
  `equipo` varchar(200) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `orden` varchar(200) DEFAULT NULL,
  `tarea` varchar(200) DEFAULT NULL,
  `tiempo` varchar(200) DEFAULT NULL,
  `herramienta` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `id_producto` int(11) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.rol_empresa_usuario
CREATE TABLE IF NOT EXISTS `rol_empresa_usuario` (
  `empresa_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`empresa_id`,`rol_id`,`username`),
  KEY `FKrs3ogs3x70iuv6gvbvlpbk14b` (`rol_id`),
  KEY `FKc58hrhm5prq308fytuwu9afvq` (`username`),
  CONSTRAINT `FK9q9fvof7ucpscbtpeljt8ut3y` FOREIGN KEY (`empresa_id`) REFERENCES `cias` (`cod_cia`),
  CONSTRAINT `FKc58hrhm5prq308fytuwu9afvq` FOREIGN KEY (`username`) REFERENCES `usuario_ad` (`username`),
  CONSTRAINT `FKrs3ogs3x70iuv6gvbvlpbk14b` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.rol_item
CREATE TABLE IF NOT EXISTS `rol_item` (
  `item_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`item_id`,`rol_id`),
  KEY `FK851757uyf7suqusfpup7ft1o0` (`rol_id`),
  CONSTRAINT `FK851757uyf7suqusfpup7ft1o0` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKrrekmn8qh816uaxeq5e1epunf` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.servicio
CREATE TABLE IF NOT EXISTS `servicio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `solucion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsepq0dlu62jvq3nb92g7kovv1` (`solucion_id`),
  CONSTRAINT `FKsepq0dlu62jvq3nb92g7kovv1` FOREIGN KEY (`solucion_id`) REFERENCES `solucion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.servicio_empresas
CREATE TABLE IF NOT EXISTS `servicio_empresas` (
  `servicio_id` bigint(20) NOT NULL,
  `empresas_id` bigint(20) NOT NULL,
  KEY `FK69g6s9vvikxlt9spyccwwifbl` (`empresas_id`),
  KEY `FK14u10qe7coyat44e356nfvpqi` (`servicio_id`),
  CONSTRAINT `FK14u10qe7coyat44e356nfvpqi` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FK69g6s9vvikxlt9spyccwwifbl` FOREIGN KEY (`empresas_id`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `cuit` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `proveedor` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  `cantidad_minima` int(11) DEFAULT NULL,
  `cantidad_maxima` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.tipo_tramite
CREATE TABLE IF NOT EXISTS `tipo_tramite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_userid_role` (`role`,`userid`),
  KEY `fk_user_idx` (`userid`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.usuario_ad
CREATE TABLE IF NOT EXISTS `usuario_ad` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre_apellido` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.usuario_empresas
CREATE TABLE IF NOT EXISTS `usuario_empresas` (
  `usuario_id` bigint(20) NOT NULL,
  `empresas_id` bigint(20) NOT NULL,
  `empresas_cod_cia` bigint(20) NOT NULL,
  KEY `FK1hy9gok3ted9y6rayfu0o6vyn` (`empresas_id`),
  KEY `FKit1u8ksbsv56yovrybpalrgn3` (`usuario_id`),
  KEY `FKajyv4riut6jmdnnlli20ddmfj` (`empresas_cod_cia`),
  CONSTRAINT `FK1hy9gok3ted9y6rayfu0o6vyn` FOREIGN KEY (`empresas_id`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FKajyv4riut6jmdnnlli20ddmfj` FOREIGN KEY (`empresas_cod_cia`) REFERENCES `cias` (`cod_cia`),
  CONSTRAINT `FKit1u8ksbsv56yovrybpalrgn3` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla proyectofinal.visita
CREATE TABLE IF NOT EXISTS `visita` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comentarios` varchar(1024) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
