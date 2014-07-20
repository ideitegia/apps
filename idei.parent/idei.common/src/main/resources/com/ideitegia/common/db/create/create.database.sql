# Database : idei_db
# Version: 1.0.0
# Created by: eagirrezabal
# Creation Date: 20/07/2014 15:25


SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `idei_db`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_spanish_ci';

USE `idei_db`;

#
# Structure for the `scope` table : 
#

CREATE TABLE `scope` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` varchar(16) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Ambito',
  PRIMARY KEY  (`id`),
  KEY `IDX_SCOPE_DOMAIN` (`domain`),
  CONSTRAINT `FK_SCOPE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Ambitos';

#
# Structure for the `domain` table : 
#

CREATE TABLE `domain` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `name` varchar(253) collate latin1_spanish_ci NOT NULL COMMENT 'Nombre del Dominio',
  `description` varchar(128) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Dominio',
  `parent` int(4) default NULL COMMENT 'Identificador del Dominio padre',
  `type` tinyint(2) NOT NULL default '0' COMMENT 'Tipo de Dominio',
  `scope` int(4) default NULL COMMENT 'Identificador del Ambito',
  `subDomainSuffix` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Sufijo de los Dominio Hijo',
  `enableHeredity` tinyint(1) NOT NULL default '0' COMMENT 'Indica si el Dominio tiene deshabilitado la herencia de registros o no',
  `domainManagement` tinyint(1) NOT NULL default '0' COMMENT 'Indica si el Dominio tiene capacidad de MultiDominio o no',
  `disableDomainManagement` tinyint(1) NOT NULL default '0' COMMENT 'Indica si el Dominio tiene deshabilitado el mantenimiento de Dominios o no',
  `maxDocumentSize` int(4) default NULL COMMENT 'Tamaño Maximo de los Documentos',
  `maxTotalDocumentSize` int(4) default NULL COMMENT 'Almacenamiento Documental Contratado',
  `maxDefinedUsers` int(4) default NULL COMMENT 'Numero Maximo de Usuarios',
  `active` tinyint(1) NOT NULL default '1' COMMENT 'Indica si el Dominio esta activo o no',
  `owner` varchar(256) collate latin1_spanish_ci NOT NULL COMMENT 'Emails del creador del Dominio',
  `creation_user` varchar(16) collate latin1_spanish_ci default NULL COMMENT 'Usuario de creacion',
  `creation_date` datetime default NULL COMMENT 'Fecha de creacion',
  `modification_user` varchar(16) collate latin1_spanish_ci default NULL COMMENT 'Usuario de modificacion',
  `modification_date` datetime default NULL COMMENT 'Fecha de modificacion',
  `expirationDate` date default NULL COMMENT 'Fecha de Expiracion del Dominio',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `IDX_UNQ_DOMAIN_NAME` (`name`),
  KEY `IDX_DOMAIN_PARENT` (`parent`),
  KEY `IDX_DOMAIN_SCOPE` (`scope`),
  CONSTRAINT `FK_DOMAIN_PARENT` FOREIGN KEY (`parent`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_DOMAIN_SCOPE` FOREIGN KEY (`scope`) REFERENCES `scope` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Dominios';

#
# Structure for the `academic_year` table : 
#

CREATE TABLE `academic_year` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Año Academico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `description` varchar(9) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Año Academico',
  PRIMARY KEY  (`id`),
  KEY `IDX_ACADEMIC_YEAR_DOMAIN` (`domain`),
  CONSTRAINT `FK_ACADEMIC_YEAR_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Año Academico';

#
# Structure for the `course_level` table : 
#

CREATE TABLE `course_level` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Nivel',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Nivel',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_LEVEL_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_LEVEL_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Niveles de Cursos';

#
# Structure for the `course_subject` table : 
#

CREATE TABLE `course_subject` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Materia',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion de la Materia',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_SUBJECT_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_SUBJECT_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Materias de Cursos';


#
# Structure for the `holiday` table : 
#

CREATE TABLE `holiday` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion de la Festividad',
  `holiday` int(4) default NULL COMMENT 'Identificador de Festividad',
  `editable` tinyint(1) default '0' COMMENT 'Indica si es editable o no',
  PRIMARY KEY  (`id`),
  KEY `IDX_HOLIDAY_HOLIDAY` (`holiday`),
  KEY `IDX_HOLIDAY_DOMAIN` (`domain`),
  CONSTRAINT `FK_HOLIDAY_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_HOLIDAY_HOLIDAY` FOREIGN KEY (`holiday`) REFERENCES `holiday` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Festividades';

#
# Structure for the `calendar` table : 
#

CREATE TABLE `calendar` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `holiday` int(4) default NULL COMMENT 'Identificador de Festivos',
  `anual_hours` double default '0' COMMENT 'Horas anuales del Calendario',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Calendario',
  `comments` text collate latin1_spanish_ci COMMENT 'Comentarios',
  `monday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `monday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `tuesday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `tuesday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `wednesday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `wednesday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `thursday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `thursday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `friday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `friday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `saturday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `saturday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `sunday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `sunday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `generic` tinyint(1) default '1' COMMENT 'Indica si es editable o no',
  `calendar` int(4) default NULL COMMENT 'Calendario del que se hereda',
  PRIMARY KEY  (`id`),
  KEY `IDX_CALENDAR_HOLIDAY` (`holiday`),
  KEY `IDX_CALENDAR_CALENDAR` (`calendar`),
  KEY `IDX_CALENDAR_DOMAIN` (`domain`),
  CONSTRAINT `FK_CALENDAR_CALENDAR` FOREIGN KEY (`calendar`) REFERENCES `calendar` (`id`),
  CONSTRAINT `FK_CALENDAR_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_CALENDAR_HOLIDAY` FOREIGN KEY (`holiday`) REFERENCES `holiday` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Calendarios Laborales';

#
# Structure for the `enterprise` table : 
#

CREATE TABLE `enterprise` (
  `registry` int(4) NOT NULL default '1' COMMENT 'Registro de la Empresa',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `scope` int(4) NOT NULL COMMENT 'Identificador del Ambito',
  `calendar` int(4) default NULL COMMENT 'Calendario',
  PRIMARY KEY  (`registry`),
  KEY `IDX_ENTERPRISE_SCOPE` (`scope`),
  KEY `IDX_ENTERPRISE_CALENDAR` (`calendar`),
  KEY `IDX_ENTERPRISE_DOMAIN` (`domain`),
  CONSTRAINT `FK_ENTERPRISE_CALENDAR` FOREIGN KEY (`calendar`) REFERENCES `calendar` (`id`),
  CONSTRAINT `FK_ENTERPRISE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_ENTERPRISE_REGISTRY` FOREIGN KEY (`registry`) REFERENCES `registry` (`id`),
  CONSTRAINT `FK_ENTERPRISE_SCOPE` FOREIGN KEY (`scope`) REFERENCES `scope` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Empresa';

#
# Structure for the `workplace` table : 
#

CREATE TABLE `workplace` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Centro de Trabajo',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `enterprise` int(4) NOT NULL default '1' COMMENT 'Empresa asociada al Centro de Trabajo',
  `description` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Centro de Trabajo',
  `address` int(4) NOT NULL COMMENT 'Identificador de la Direccion',
  `customer` int(4) default NULL COMMENT 'Identificador del Cliente',
  `scope` int(4) NOT NULL COMMENT 'Identificador del Ambito',
  `economicAgreement` tinyint(2) default '0' COMMENT 'Concierto Economico del Centro de Trabajo',
  `active` tinyint(1) default '1' COMMENT 'Indica si el Centro de Trabajo esta activo o no',
  PRIMARY KEY  (`id`),
  KEY `IDX_WORKPLACE_ENTERPRISE` (`enterprise`),
  KEY `IDX_WORKPLACE_RADDRESS` (`address`),
  KEY `IDX_WORKPLACE_SCOPE` (`scope`),
  KEY `IDX_WORKPLACE_DOMAIN` (`domain`),
  KEY `IDX_WORKPLACE_CUSTOMER` (`customer`),
  CONSTRAINT `FK_WORKPLACE_CUSTOMER` FOREIGN KEY (`customer`) REFERENCES `customer` (`registry`),
  CONSTRAINT `FK_WORKPLACE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_WORKPLACE_ENTERPRISE` FOREIGN KEY (`enterprise`) REFERENCES `enterprise` (`registry`),
  CONSTRAINT `FK_WORKPLACE_RADDRESS` FOREIGN KEY (`address`) REFERENCES `raddress` (`id`),
  CONSTRAINT `FK_WORKPLACE_SCOPE` FOREIGN KEY (`scope`) REFERENCES `scope` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Centros de Trabajo';

#
# Structure for the `course` table : 
#

CREATE TABLE `course` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Curso',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `code` varchar(5) collate latin1_spanish_ci default NULL COMMENT 'Alias del Curso',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Curso',
  `start_date` date NOT NULL COMMENT 'Fecha inicio del Curso',
  `end_date` date NOT NULL COMMENT 'Fecha fin del Curso',
  `academic_year` int(4) NOT NULL COMMENT 'Año Academico del Curso',
  `subject` int(4) NOT NULL COMMENT 'Materia del Curso',
  `level` int(4) NOT NULL COMMENT 'Nivel del Curso',
  `workplace` int(4) NOT NULL COMMENT 'Identificador del Centro de Trabajo',
  `alumn_limit` smallint(2) default NULL COMMENT 'Limite de Alumnos del Curso',
  `status` tinyint(2) default NULL COMMENT 'Estado del Curso',
  `comments` varchar(128) collate latin1_spanish_ci default NULL COMMENT 'Comentarios sobre el Curso',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_ACADEMIC_YEAR` (`academic_year`),
  KEY `IDX_COURSE_LEVEL` (`level`),
  KEY `IDX_COURSE_SUBJECT` (`subject`),
  KEY `IDX_COURSE_WORKPLACE` (`workplace`),
  KEY `IDX_COURSE_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_ACADEMIC_YEAR` FOREIGN KEY (`academic_year`) REFERENCES `academic_year` (`id`),
  CONSTRAINT `FK_COURSE_LEVEL` FOREIGN KEY (`level`) REFERENCES `course_level` (`id`),
  CONSTRAINT `FK_COURSE_SUBJECT` FOREIGN KEY (`subject`) REFERENCES `course_subject` (`id`),
  CONSTRAINT `FK_COURSE_WORKPLACE` FOREIGN KEY (`workplace`) REFERENCES `workplace` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Cursos';

#
# Structure for the `course_alumn` table : 
#

CREATE TABLE `course_alumn` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Identificador del Curso',
  `customer` int(4) NOT NULL COMMENT 'Identificador del Alumno',
  `status` tinyint(2) default NULL COMMENT 'Estado del alumno en el curso',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_ALUMN_COURSE` (`course`),
  KEY `IDX_COURSE_ALUMN_CUSTOMER` (`customer`),
  KEY `IDX_COURSE_ALUMN_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_ALUMN_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_ALUMN_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `FK_COURSE_ALUMN_CUSTOMER` FOREIGN KEY (`customer`) REFERENCES `customer` (`registry`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Alumnos por Curso';

#
# Structure for the `absence` table : 
#

CREATE TABLE `absence` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course_alumn` int(4) NOT NULL COMMENT 'Identificador del CursoAlumno',
  `absence_date` date default NULL COMMENT 'Fecha de la Ausencia',
  `comments` text collate latin1_spanish_ci COMMENT 'Comentarios de la Ausencia',
  `evaluation` tinyint(2) default NULL COMMENT 'Numero de Evaluacion en que se produjo la Ausencia',
  PRIMARY KEY  (`id`),
  KEY `IDX_ABSENCE_COURSE_ALUMN` (`course_alumn`),
  KEY `IDX_ABSENCE_DOMAIN` (`domain`),
  CONSTRAINT `FK_ABSENCE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_ABSENCE_COURSE_ALUMN` FOREIGN KEY (`course_alumn`) REFERENCES `course_alumn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Ausencias';

#
# Structure for the `academic_skill` table : 
#

CREATE TABLE `academic_skill` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Aptitud Academica',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `code` char(5) collate latin1_spanish_ci NOT NULL COMMENT 'Codigo de la Aptitud Academica',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion de la Aptitud Academica',
  PRIMARY KEY  (`id`),
  KEY `IDX_ACADEMIC_SKILL_DOMAIN` (`domain`),
  CONSTRAINT `FK_ACADEMIC_SKILL_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Aptitudes Academicas';

#
# Structure for the `department` table : 
#

CREATE TABLE `department` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `name` varchar(32) collate latin1_spanish_ci default NULL COMMENT 'Nombre del Departamento',
  PRIMARY KEY  (`id`),
  KEY `IDX_DEPARTMENT_DOMAIN` (`domain`),
  CONSTRAINT `FK_DEPARTMENT_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Departamentos';

#
# Structure for the `user` table : 
#

CREATE TABLE `user` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `name` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Nombre del Usuario',
  `login` varchar(16) collate latin1_spanish_ci NOT NULL COMMENT 'Login del Usuario',
  `enterprise` int(4) default NULL COMMENT 'Identificador de la Empresa',
  `registry` int(4) default NULL COMMENT 'Identificador del Registry',
  `active` tinyint(1) NOT NULL default '1' COMMENT 'Indica si el Usuario esta activo o no',
  `password` varchar(128) collate latin1_spanish_ci default NULL COMMENT 'Contraseña del Usuario',
  `passwordExpiration` date default NULL COMMENT 'Fecha de Expiracion de la Contraseña',
  `toolbar` tinyint(2) NOT NULL default '0' COMMENT 'Tipo de Barra de Herramientas del Usuario',
  `locale` varchar(8) collate latin1_spanish_ci default NULL COMMENT 'Locale del Usuario',
  `pageLimit` int(4) default NULL COMMENT 'Limite de filas en pantalla del Usuario',
  `linesPageLimit` int(4) default NULL COMMENT 'Limite de filas en pantalla en lineas del Usuario',
  `initAction` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Nombre la acicón de inicio del Usuario',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `IDX_UNQ_USER_DOMAIN_LOGIN` (`domain`,`login`),
  KEY `IDX_USER_ENTERPRISE` (`enterprise`),
  KEY `IDX_USER_REGISTRY` (`registry`),
  KEY `IDX_USER_DOMAIN` (`domain`),
  CONSTRAINT `FK_USER_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_USER_ENTERPRISE` FOREIGN KEY (`enterprise`) REFERENCES `enterprise` (`registry`),
  CONSTRAINT `FK_USER_REGISTRY` FOREIGN KEY (`registry`) REFERENCES `registry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Usuarios';


#
# Structure for the `session` table : 
#

CREATE TABLE `session` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `endDate` datetime default NULL COMMENT 'Fecha de finalizacion',
  `remote_address` varchar(15) collate latin1_spanish_ci NOT NULL default '' COMMENT 'IP remota',
  `remote_host` varchar(64) collate latin1_spanish_ci NOT NULL default '' COMMENT 'Equipo remoto',
  `session_id` varchar(128) collate latin1_spanish_ci NOT NULL default '' COMMENT 'Identificador web de la sesión',
  `startDate` datetime NOT NULL COMMENT 'Fecha de inicio',
  `application` int(4) NOT NULL COMMENT 'Identificador de la Aplicacion',
  `user_id` int(4) NOT NULL default '0' COMMENT 'Identificador del Usuario',
  PRIMARY KEY  (`id`),
  KEY `IDX_SESSION_APPLICATION` (`application`),
  KEY `IDX_SESSION_USER` (`user_id`),
  KEY `IDX_SESSION_DOMAIN` (`domain`),
  CONSTRAINT `FK_SESSION_APPLICATION` FOREIGN KEY (`application`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_SESSION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_SESSION_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Sesion web';

#
# Structure for the `alarm` table : 
#

CREATE TABLE `alarm` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Alarma',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` text collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion de la Alarma',
  `alarm_date` datetime NOT NULL COMMENT 'Fecha y hora de ejecucion de la Alarma',
  `status` tinyint(2) default NULL COMMENT 'Estado de la Alarma',
  `source` tinyint(2) NOT NULL COMMENT 'Origen de la Alarma',
  `source_id` int(4) default NULL COMMENT 'Identificador del origen de la Alarma',
  `user_id` int(4) default NULL COMMENT 'Identificador del Usuario asociado a la Alarma',
  `priority` tinyint(2) NOT NULL COMMENT 'Prioridad de la Alarma',
  PRIMARY KEY  (`id`),
  KEY `IDX_ALARM_USER` (`user_id`),
  KEY `IDX_ALARM_DOMAIN` (`domain`),
  CONSTRAINT `FK_ALARM_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_ALARM_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Alarmas';

#
# Structure for the `alumn_loan` table : 
#

CREATE TABLE `alumn_loan` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Prestamo',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `customer` int(4) NOT NULL COMMENT 'Alumno al que se le realizo el Prestamo',
  `material` varchar(16) collate latin1_spanish_ci NOT NULL COMMENT 'Material prestado',
  `loan_date` date NOT NULL COMMENT 'Fecha del Prestamo',
  `end_date` date default NULL COMMENT 'Fecha devolucion del material',
  `comments` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Observaciones',
  PRIMARY KEY  (`id`),
  KEY `IDX_ALUMN_LOAN_CUSTOMER` (`customer`),
  KEY `IDX_ALUMN_LOAN_DOMAIN` (`domain`),
  CONSTRAINT `FK_ALUMN_LOAN_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_ALUMN_LOAN_CUSTOMER` FOREIGN KEY (`customer`) REFERENCES `customer` (`registry`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Prestamos a Alumnos';


#
# Structure for the `asset` table : 
#

CREATE TABLE `asset` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Activo',
  `name` varchar(10) collate latin1_spanish_ci NOT NULL COMMENT 'Nombre corto del Activo',
  PRIMARY KEY  (`id`),
  KEY `IDX_ASSET_DOMAIN` (`domain`),
  CONSTRAINT `FK_ASSET_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Activos';

#
# Structure for the `asset_activity` table : 
#

CREATE TABLE `asset_activity` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `asset` int(4) NOT NULL COMMENT 'Identificador del Activo',
  `date` date NOT NULL COMMENT 'Fecha de la Actividad',
  `from_time` datetime NOT NULL COMMENT 'Hora de inicio de la Actividad',
  `to_time` datetime NOT NULL COMMENT 'Hora final de la Actividad',
  `who` varchar(20) collate latin1_spanish_ci default NULL COMMENT 'Quien solicita el Activo',
  `why` varchar(128) collate latin1_spanish_ci default NULL COMMENT 'Motivo de solicitud del Activo',
  `status` tinyint(2) NOT NULL default '0' COMMENT 'Estado de la Solicitud',
  PRIMARY KEY  (`id`),
  KEY `IDX_ASSET_ACTIVITY_ASSET` (`asset`),
  KEY `IDX_ASSET_ACTIVITY_DOMAIN` (`domain`),
  CONSTRAINT `FK_ASSET_ACTIVITY_ASSET` FOREIGN KEY (`asset`) REFERENCES `asset` (`id`),
  CONSTRAINT `FK_ASSET_ACTIVITY_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Actividades sobre el Activo';

#
# Structure for the `feature` table : 
#

CREATE TABLE `feature` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `name` varchar(32) collate latin1_spanish_ci default NULL COMMENT 'Nombre de la Caracteristica',
  PRIMARY KEY  (`id`),
  KEY `IDX_FEATURE_DOMAIN` (`domain`),
  CONSTRAINT `FK_FEATURE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Caracteristicas';

#
# Structure for the `asset_feature` table : 
#

CREATE TABLE `asset_feature` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `asset` int(4) NOT NULL COMMENT 'Identificador del Activo',
  `feature` int(4) NOT NULL COMMENT 'Identificador de la Caracteristica',
  PRIMARY KEY  (`id`),
  KEY `IDX_ASSET_FEATURE_ASSET` (`asset`),
  KEY `IDX_ASSET_FEATURE_FEATURE` (`feature`),
  KEY `IDX_ASSET_FEATURE_DOMAIN` (`domain`),
  CONSTRAINT `FK_ASSET_FEATURE_ASSET` FOREIGN KEY (`asset`) REFERENCES `asset` (`id`),
  CONSTRAINT `FK_ASSET_FEATURE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_ASSET_FEATURE_FEATURE` FOREIGN KEY (`feature`) REFERENCES `feature` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Caracteristicas por Activo';

#
# Structure for the `calendar_holiday` table : 
#

CREATE TABLE `calendar_holiday` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `calendar` int(4) NOT NULL default '0' COMMENT 'Identificador del Calendario',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Festivo',
  `date` date default NULL COMMENT 'Fecha del festivo',
  `day_type` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `hours` double default '0' COMMENT 'Numero de horas laborables',
  PRIMARY KEY  (`id`),
  KEY `IDX_CALENDAR_HOLIDAY_CALENDAR` (`calendar`),
  KEY `IDX_CALENDAR_HOLIDAY_DOMAIN` (`domain`),
  CONSTRAINT `FK_CALENDAR_HOLIDAY_CALENDAR` FOREIGN KEY (`calendar`) REFERENCES `calendar` (`id`),
  CONSTRAINT `FK_CALENDAR_HOLIDAY_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Festivos de Calendarios';

#
# Structure for the `calendar_period` table : 
#

CREATE TABLE `calendar_period` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `calendar` int(4) NOT NULL COMMENT 'Identificador del Calendario',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Periodo',
  `month` tinyint(2) default '0' COMMENT 'Mes del periodo',
  `start_day` tinyint(2) default '0' COMMENT 'Dia inicio del periodo',
  `end_day` tinyint(2) default '0' COMMENT 'Dia fin del periodo',
  `monday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `monday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `tuesday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `tuesday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `wednesday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `wednesday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `thursday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `thursday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `friday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `friday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `saturday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `saturday_hours` double default '0' COMMENT 'Numero de horas laborables',
  `sunday` tinyint(2) default '0' COMMENT 'Tipo de dia',
  `sunday_hours` double default '0' COMMENT 'Numero de horas laborables',
  PRIMARY KEY  (`id`),
  KEY `IDX_CALENDAR_PERIOD_CALENDAR` (`calendar`),
  KEY `IDX_CALENDAR_PERIOD_DOMAIN` (`domain`),
  CONSTRAINT `FK_CALENDAR_PERIOD_CALENDAR` FOREIGN KEY (`calendar`) REFERENCES `calendar` (`id`),
  CONSTRAINT `FK_CALENDAR_PERIOD_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Periodos de Calendarios';

#
# Structure for the `workgroup` table : 
#

CREATE TABLE `workgroup` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Grupo de Trabajo',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion del Grupo de Trabajo',
  `status` tinyint(2) default NULL COMMENT 'Estado del grupo de Trabajo',
  PRIMARY KEY  (`id`),
  KEY `IDX_WORKGROUP_DOMAIN` (`domain`),
  CONSTRAINT `FK_WORKGROUP_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Grupos de Trabajo';

#
# Structure for the `person` table : 
#

CREATE TABLE `person` (
  `registry` int(4) NOT NULL default '0' COMMENT 'Registro de la Persona',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `birth_date` date default NULL COMMENT 'Fecha de nacimiento de la Persona',
  `gender` tinyint(2) NOT NULL default '0' COMMENT 'Sexo de la Persona',
  `marital_status` tinyint(2) NOT NULL default '0' COMMENT 'Estado civil de la Persona',
  `social_security_num` varchar(32) collate latin1_spanish_ci default NULL COMMENT 'Numero de Seguridad Social de la Persona',
  `name` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Nombre',
  `first_surname` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Primer Apellido ',
  `second_surname` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Segundo Apellido',
  PRIMARY KEY  (`registry`),
  KEY `IDX_PERSON_DOMAIN` (`domain`),
  CONSTRAINT `FK_PERSON_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_PERSON_REGISTRY` FOREIGN KEY (`registry`) REFERENCES `registry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Personas';

#
# Structure for the `company` table : 
#

CREATE TABLE `company` (
  `registry` int(4) NOT NULL default '1' COMMENT 'Registro de la Compañia',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `active` tinyint(1) default '0' COMMENT 'Indica si la Compañia es activa o inactiva',
  `surcharge` tinyint(1) default '0' COMMENT 'Indica si la Compañia tiene de recargo de equivalencia',
  `withholding` tinyint(1) default '0' COMMENT 'Indica si la Compañia aplica retencion de impuestos',
  `vat_accrual_payment` tinyint(1) default '0' COMMENT 'Indica si la Compañia esta acogida al Regimen Especial de Criterio de Caja',
  `e_invoice` tinyint(1) default '0' COMMENT 'Indica si la Compañia desea emitir Facturas electronicas',
  PRIMARY KEY  (`registry`),
  UNIQUE KEY `IDX_UNQ_COMPANY_DOMAIN` (`domain`),
  KEY `IDX_COMPANY_DOMAIN` (`domain`),
  CONSTRAINT `FK_COMPANY_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COMPANY_REGISTRY` FOREIGN KEY (`registry`) REFERENCES `registry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Datos Corporativos';

#
# Structure for the `course_academicskill` table : 
#

CREATE TABLE `course_academicskill` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador Unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Curso',
  `academic_skill` int(4) NOT NULL COMMENT 'Aptitud Academica',
  `weight` int(4) NOT NULL default '1' COMMENT 'Peso de la Aptitud para calcular la Nota media',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_ACADEMIC_SKILL_COURSE` (`course`),
  KEY `IDX_COURSE_ACADEMIC_SKILL_ACADEMIC_SKILL` (`academic_skill`),
  KEY `IDX_COURSE_ACADEMIC_SKILL_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_ACADEMIC_SKILL_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_ACADEMIC_SKILL_ACADEMIC_SKILL` FOREIGN KEY (`academic_skill`) REFERENCES `academic_skill` (`id`),
  CONSTRAINT `FK_COURSE_ACADEMIC_SKILL_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Aptitudes Academicas por Curso';

#
# Structure for the `quality_skill` table : 
#

CREATE TABLE `quality_skill` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Aptitud Calidad',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `code` char(5) collate latin1_spanish_ci NOT NULL COMMENT 'Codigo de la Aptitud Calidad',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion de la Aptitud Calidad',
  PRIMARY KEY  (`id`),
  KEY `IDX_QUALITY_SKILL_DOMAIN` (`domain`),
  CONSTRAINT `FK_QUALITY_SKILL_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Aptitudes Calidad';

#
# Structure for the `course_evaluation` table : 
#

CREATE TABLE `course_evaluation` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Identificador de Curso',
  `quality_skill` int(4) NOT NULL COMMENT 'Identificador de Aptitudes Calidad',
  `evaluation` double(15,3) default '0.000' COMMENT 'Evaluaciones',
  `quantity` int(4) default '0' COMMENT 'Cantidad',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_EVALUATION_COURSE` (`course`),
  KEY `IDX_COURSE_EVALUATION_QUALITY_SKILL` (`quality_skill`),
  KEY `IDX_COURSE_EVALUATION_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_EVALUATION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_EVALUATION_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `FK_COURSE_EVALUATION_QUALITY_SKILL` FOREIGN KEY (`quality_skill`) REFERENCES `quality_skill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Evaluaciones por Curso';

#
# Structure for the `course_instructor` table : 
#

CREATE TABLE `course_instructor` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Identificador del Curso',
  `task_holder` int(4) NOT NULL COMMENT 'Identificador del Profesor',
  `type` tinyint(2) default NULL COMMENT 'Tipo de Profesor',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_INSTRUCTOR_COURSE` (`course`),
  KEY `IDX_COURSE_INSTRUCTOR_DOMAIN` (`domain`),
  KEY `IDX_COURSE_INSTRUCTOR_TASK_HOLDER` (`task_holder`),
  CONSTRAINT `FK_COURSE_INSTRUCTOR_TASK_HOLDER` FOREIGN KEY (`task_holder`) REFERENCES `task_holder` (`registry`),
  CONSTRAINT `FK_COURSE_INSTRUCTOR_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `FK_COURSE_INSTRUCTOR_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Profesores por Curso';

#
# Structure for the `course_observation` table : 
#

CREATE TABLE `course_observation` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Identificador de Curso',
  `observation` varchar(64) character set latin1 collate latin1_spanish_ci default NULL COMMENT 'Observaciones',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_OBSERVATION_COURSE` (`course`),
  KEY `IDX_COURSE_OBSERVATION_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_OBSERVATION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_OBSERVATION_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Observaciones por Curso';

#
# Structure for the `course_schedule` table : 
#

CREATE TABLE `course_schedule` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico del Horario',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `course` int(4) NOT NULL COMMENT 'Identificador del Curso',
  `day_of_week` tinyint(2) NOT NULL COMMENT 'Dia de la semana',
  `start_time` time NOT NULL COMMENT 'Hora de comienzo',
  `end_time` time NOT NULL COMMENT 'Hora de fin',
  PRIMARY KEY  (`id`),
  KEY `IDX_COURSE_SCHEDULE_COURSE` (`course`),
  KEY `IDX_COURSE_SCHEDULE_DOMAIN` (`domain`),
  CONSTRAINT `FK_COURSE_SCHEDULE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_COURSE_SCHEDULE_COURSE` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Horarios de Cursos';

#
# Structure for the `db_version` table : 
#

CREATE TABLE `db_version` (
  `version_number` varchar(10) collate latin1_spanish_ci NOT NULL COMMENT 'Numero de Version de la Base de Datos',
  PRIMARY KEY  (`version_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Version de la Base de Datos';

#
# Structure for the `evaluation_observation` table : 
#

CREATE TABLE `evaluation_observation` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `alumn` int(4) NOT NULL COMMENT 'Identificador de Alumno',
  `evaluation` tinyint(2) NOT NULL COMMENT 'Numero de Evaluacion',
  `comments` text character set latin1 collate latin1_spanish_ci COMMENT 'Comentarios',
  PRIMARY KEY  (`id`),
  KEY `IDX_EVALUATION_OBSERVATION_ALUMN` (`alumn`),
  KEY `IDX_EVALUATION_OBSERVATION_DOMAIN` (`domain`),
  CONSTRAINT `FK_EVALUATION_OBSERVATION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_EVALUATION_OBSERVATION_ALUMN` FOREIGN KEY (`alumn`) REFERENCES `course_alumn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Observaciones por Evaluacion';

#
# Structure for the `holiday_detail` table : 
#

CREATE TABLE `holiday_detail` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `holiday` int(4) NOT NULL COMMENT 'Identificador de Festividad',
  `date` date NOT NULL COMMENT 'Fecha Festiva',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion de Festividad',
  PRIMARY KEY  (`id`),
  KEY `IDX_HOLIDAY_DETAIL_HOLIDAY` (`holiday`),
  KEY `IDX_HOLIDAY_DETAIL_DOMAIN` (`domain`),
  CONSTRAINT `FK_HOLIDAY_DETAIL_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_HOLIDAY_DETAIL_HOLIDAY` FOREIGN KEY (`holiday`) REFERENCES `holiday` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Detalle de Festividades';

#
# Structure for the `loan` table : 
#

CREATE TABLE `loan` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador Unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion del Prestamo',
  `loan_date` date NOT NULL COMMENT 'Fecha de Concesion del Prestamo',
  `term` varchar(16) collate latin1_spanish_ci default NULL COMMENT 'Plazo',
  `interest` varchar(16) collate latin1_spanish_ci default NULL COMMENT 'Interes',
  `review` varchar(16) collate latin1_spanish_ci default NULL COMMENT 'Revision',
  `amount` double(15,3) default '0.000' COMMENT 'Importe',
  `expenses` double(15,3) default '0.000' COMMENT 'Gastos asociados al Prestamo',
  `rbank` int(4) NOT NULL COMMENT 'Banco por el que se paga el Prestamo',
  `security_level` tinyint(2) default '0' COMMENT 'Nivel de Seguridad',
  `fee_amount` double(15,3) default '0.000' COMMENT 'Importe de la cuota',
  `recurrence` int(4) default '0' COMMENT 'Periodicidad',
  `pay_day` int(4) default '1' COMMENT 'Dia de Pago',
  `status` tinyint(2) default '0' COMMENT 'Estado',
  `account` int(4) default NULL COMMENT 'Identificador de la Cuenta Contable',
  PRIMARY KEY  (`id`),
  KEY `IDX_LOAN_RBANK` (`rbank`),
  KEY `IDX_LOAN_DOMAIN` (`domain`),
  KEY `IDX_LOAN_ACCOUNT` (`account`),
  CONSTRAINT `FK_LOAN_ACCOUNT` FOREIGN KEY (`account`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_LOAN_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_LOAN_RBANK` FOREIGN KEY (`rbank`) REFERENCES `rbank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Prestamos';

#
# Structure for the `mail_account` table : 
#

CREATE TABLE `mail_account` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `name` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Nombre de la Cuenta de Correo',
  `email` varchar(256) collate latin1_spanish_ci NOT NULL COMMENT 'Cuenta de correo',
  `replyto_mail` varchar(256) collate latin1_spanish_ci default NULL COMMENT 'Email de Respuesta',
  `incoming_host` varchar(256) collate latin1_spanish_ci default NULL COMMENT 'Host del correo entrante',
  `protocol` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Protocolo utilizado (IMAP)',
  `incoming_port` int(4) default NULL COMMENT 'Puerto del correo entrante',
  `incoming_security` tinyint(2) NOT NULL default '0' COMMENT 'Seguridad de conexión del correo entrante',
  `outgoing_verification` tinyint(1) default '1' COMMENT 'Indica si hay autentificacion en el correo saliente',
  `outgoing_host` varchar(256) collate latin1_spanish_ci default NULL COMMENT 'Host del servidor de correo saliente',
  `outgoing_port` int(4) default NULL COMMENT 'Puerto del servidor de correo saliente',
  `outgoing_security` tinyint(2) NOT NULL default '0' COMMENT 'Seguridad de conexión del correo saliente',
  `mail_username` varchar(256) collate latin1_spanish_ci default NULL COMMENT 'Nombre del usuario',
  `password` varchar(128) collate latin1_spanish_ci default NULL COMMENT 'Clave del usuario',
  `default_account` tinyint(1) default '0' COMMENT 'Indica si es la cuenta de correo por defecto',
  `draft_folder` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Ruta de Borrador',
  `sent_folder` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Ruta de Enviados',
  `trash_folder` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Ruta de Papelera',
  `spam_folder` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Ruta de Spam',
  `display_name` varchar(256) collate latin1_spanish_ci default NULL COMMENT 'Mostrar como',
  `signature` int(4) default NULL COMMENT 'Identificador de la Firma',
  `user_id` int(4) default NULL COMMENT 'Identificador del Usuario',
  PRIMARY KEY  (`id`),
  KEY `IDX_MAIL_ACCOUNT_SIGNATURE` (`signature`),
  KEY `IDX_MAIL_ACCOUNT_DOMAIN` (`domain`),
  KEY `IDX_MAIL_ACCOUNT_USER` (`user_id`),
  CONSTRAINT `FK_MAIL_ACCOUNT_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_MAIL_ACCOUNT_SIGNATURE` FOREIGN KEY (`signature`) REFERENCES `signature` (`id`),
  CONSTRAINT `FK_MAIL_ACCOUNT_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Cuentas de Correo Electronico';

#
# Structure for the `mark` table : 
#

CREATE TABLE `mark` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `subject` int(4) NOT NULL COMMENT 'Identificador de Asignatura',
  `alumn` int(4) NOT NULL COMMENT 'Identificador de Alumno',
  `evaluation` tinyint(2) NOT NULL COMMENT 'Numero de evaluacion',
  `mark` double(15,3) default '0.000' COMMENT 'Nota',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `IDX_MARK_SUBJECT_ALUMN_EVALUATION` (`subject`,`alumn`,`evaluation`),
  KEY `IDX_MARK_ALUMN` (`alumn`),
  KEY `IDX_MARK_SUBJECT` (`subject`),
  KEY `IDX_MARK_DOMAIN` (`domain`),
  CONSTRAINT `FK_MARK_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_MARK_ALUMN` FOREIGN KEY (`alumn`) REFERENCES `course_alumn` (`id`),
  CONSTRAINT `FK_MARK_SUBJECT` FOREIGN KEY (`subject`) REFERENCES `course_academicskill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Notas de Alumnos';

#
# Structure for the `note` table : 
#

CREATE TABLE `note` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Nota',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `subject` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion corta de la Nota',
  `date` datetime NOT NULL COMMENT 'Fecha de la Nota',
  `owner` int(4) default NULL COMMENT 'Destinatario de la Nota',
  `note` text collate latin1_spanish_ci COMMENT 'Texto de la Nota',
  PRIMARY KEY  (`id`),
  KEY `IDX_NOTE_USER` (`owner`),
  KEY `IDX_NOTE_DOMAIN` (`domain`),
  CONSTRAINT `FK_NOTE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_NOTE_USER` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Notas';

#
# Structure for the `observation` table : 
#

CREATE TABLE `observation` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Observacion',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `description` varchar(256) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion de la Observacion',
  PRIMARY KEY  (`id`),
  KEY `IDX_OBSERVATION_DOMAIN` (`domain`),
  CONSTRAINT `FK_OBSERVATION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Observaciones';

#
# Structure for the `qualification` table : 
#

CREATE TABLE `qualification` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Calificacion',
  `domain` int(4) NOT NULL default '1' COMMENT 'Identificador del Dominio',
  `code` char(5) collate latin1_spanish_ci NOT NULL COMMENT 'Codigo de la Calificacion',
  `description` varchar(64) collate latin1_spanish_ci default NULL COMMENT 'Descripcion de la Calificacion',
  `min_value` double(15,3) NOT NULL default '0.000' COMMENT 'Limite inferior de la Calificacion',
  `max_value` double(15,3) NOT NULL default '0.000' COMMENT 'Limite superior de la Calificacion',
  PRIMARY KEY  (`id`),
  KEY `IDX_QUALIFICATION_DOMAIN` (`domain`),
  CONSTRAINT `FK_QUALIFICATION_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Calificaciones';

#
# Structure for the `rnote` table : 
#

CREATE TABLE `rnote` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico de la Nota de la Persona o Empresa',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `registry` int(4) NOT NULL default '0' COMMENT 'Identificador del Registro de la Persona o Empresa',
  `description` varchar(64) collate latin1_spanish_ci NOT NULL COMMENT 'Descripcion de la Nota',
  `note_date` date default NULL COMMENT 'Fecha de la Nota',
  `comments` text collate latin1_spanish_ci COMMENT 'Comentarios de la Nota',
  `note_type` tinyint(2) default NULL,
  `security_level` tinyint(2) default '0' COMMENT 'Nivel de seguridad de la Nota',
  PRIMARY KEY  (`id`),
  KEY `IDX_RNOTE_REGISTRY` (`registry`),
  KEY `IDX_RNOTE_DOMAIN` (`domain`),
  CONSTRAINT `FK_RNOTE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_RNOTE_REGISTRY` FOREIGN KEY (`registry`) REFERENCES `registry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Notas de Personas o Empresas';

#
# Structure for the `user_scope` table : 
#

CREATE TABLE `user_scope` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `user_id` int(4) NOT NULL COMMENT 'Identificador del Usuario',
  `scope` int(4) NOT NULL COMMENT 'Identificador del Ambito',
  PRIMARY KEY  (`id`),
  KEY `IDX_USER_SCOPE_SCOPE` (`scope`),
  KEY `IDX_USER_SCOPE_USER` (`user_id`),
  KEY `IDX_USER_SCOPE_DOMAIN` (`domain`),
  CONSTRAINT `FK_USER_SCOPE_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_USER_SCOPE_SCOPE` FOREIGN KEY (`scope`) REFERENCES `scope` (`id`),
  CONSTRAINT `FK_USER_SCOPE_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Ambitos de Usuario';

#
# Structure for the `user_workgroup` table : 
#

CREATE TABLE `user_workgroup` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `user_id` int(4) NOT NULL COMMENT 'Identificador del Usuario',
  `workgroup` int(4) NOT NULL COMMENT 'Identificador del Grupo de Trabajo',
  PRIMARY KEY  (`id`),
  KEY `IDX_USER_WORKGROUP_USER` (`user_id`),
  KEY `IDX_USER_WORKGROUP_WORKGROUP` (`workgroup`),
  KEY `IDX_USER_WORKGROUP_DOMAIN` (`domain`),
  CONSTRAINT `FK_USER_WORKGROUP_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_USER_WORKGROUP_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_USER_WORKGROUP_WORKGROUP` FOREIGN KEY (`workgroup`) REFERENCES `workgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Relacion entre Usuarios y Grupos de Trabajo';

#
# Structure for the `workplace_department` table : 
#

CREATE TABLE `workplace_department` (
  `id` int(4) NOT NULL auto_increment COMMENT 'Identificador unico',
  `domain` int(4) NOT NULL COMMENT 'Identificador del Dominio',
  `workplace` int(4) NOT NULL COMMENT 'Identificador del Centro de Trabajo',
  `department` int(4) NOT NULL COMMENT 'Identificador del Departamento',
  `catalogue` int(4) default NULL COMMENT 'Identificador del Catalogo',
  `active` tinyint(1) default '1' COMMENT 'Indica si el Departamento esta activo o no',
  PRIMARY KEY  (`id`),
  KEY `IDX_WORKPLACE_DEPARTMENT_DOMAIN` (`domain`),
  KEY `IDX_WORKPLACE_DEPARTMENT_WORKPLACE` (`workplace`),
  KEY `IDX_WORKPLACE_DEPARTMENT_DEPARTMENT` (`department`),
  KEY `IDX_WORKPLACE_DEPARTMENT_CATALOGUE` (`catalogue`),
  CONSTRAINT `FK_WORKPLACE_DEPARTMENT_CATALOGUE` FOREIGN KEY (`catalogue`) REFERENCES `catalogue` (`id`),
  CONSTRAINT `FK_WORKPLACE_DEPARTMENT_DEPARTMENT` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_WORKPLACE_DEPARTMENT_DOMAIN` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`),
  CONSTRAINT `FK_WORKPLACE_DEPARTMENT_WORKPLACE` FOREIGN KEY (`workplace`) REFERENCES `workplace` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Departamentos del Centro de Trabajo';


INSERT INTO `db_version` (`version_number`) VALUES ('1.0.0');

COMMIT;


SET FOREIGN_KEY_CHECKS=1;
