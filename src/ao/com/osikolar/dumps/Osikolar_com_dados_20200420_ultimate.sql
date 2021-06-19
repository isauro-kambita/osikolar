CREATE DATABASE  IF NOT EXISTS `osikolar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `osikolar`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: osikolar
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.37-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblaluno`
--

DROP TABLE IF EXISTS `tblaluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblaluno` (
  `idAluno` int(11) NOT NULL AUTO_INCREMENT,
  `nomeAluno` varchar(45) NOT NULL,
  `generoAluno` enum('M','F') NOT NULL,
  `aniversarioAluno` date NOT NULL,
  `pai` varchar(45) DEFAULT NULL,
  `mae` varchar(45) NOT NULL,
  `naturalidade` varchar(25) DEFAULT NULL,
  `municipio` varchar(20) NOT NULL,
  `idProvincia` int(11) NOT NULL,
  `cedulaBi` varchar(15) NOT NULL,
  `dataArquivo` date NOT NULL,
  `estadoAluno` enum('ACTIVO','DESISTIDO','FALECIDO','TRANSFERIDO') NOT NULL,
  `anoLectivo` int(11) NOT NULL,
  `obsAluno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAluno`),
  KEY `fk_tblAluno_tblProvincia1_idx` (`idProvincia`),
  CONSTRAINT `fk_tblAluno_tblProvincia1` FOREIGN KEY (`idProvincia`) REFERENCES `tblprovincia` (`idProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaluno`
--

LOCK TABLES `tblaluno` WRITE;
/*!40000 ALTER TABLE `tblaluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblaluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblalunodesistido`
--

DROP TABLE IF EXISTS `tblalunodesistido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblalunodesistido` (
  `idDesistencia` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idTurno` int(11) NOT NULL,
  PRIMARY KEY (`idDesistencia`),
  KEY `fk_tblAlunoDesistido_tblTurno1_idx` (`idTurno`),
  KEY `fk_tblAlunoDesistido_tblTurma1_idx` (`idTurma`),
  KEY `fk_tblAlunoDesistido_tblCurso1_idx` (`idCurso`),
  KEY `fk_tblAlunoDesistido_tblClasse1_idx` (`idClasse`),
  KEY `fk_tblAlunoDesistido_tblAluno1_idx` (`idAluno`),
  KEY `fk_tblAlunoDesistido_tblSala1_idx` (`idSala`),
  CONSTRAINT `fk_tblAlunoDesistido_tblAluno1` FOREIGN KEY (`idAluno`) REFERENCES `tblaluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblAlunoDesistido_tblClasse1` FOREIGN KEY (`idClasse`) REFERENCES `tblclasse` (`idClasse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblAlunoDesistido_tblCurso1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblAlunoDesistido_tblSala1` FOREIGN KEY (`idSala`) REFERENCES `tblsala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblAlunoDesistido_tblTurma1` FOREIGN KEY (`idTurma`) REFERENCES `tblturma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblAlunoDesistido_tblTurno1` FOREIGN KEY (`idTurno`) REFERENCES `tblturno` (`idTurno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblalunodesistido`
--

LOCK TABLES `tblalunodesistido` WRITE;
/*!40000 ALTER TABLE `tblalunodesistido` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblalunodesistido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcertificado`
--

DROP TABLE IF EXISTS `tblcertificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcertificado` (
  `idCertificado` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  `idTurno` int(11) NOT NULL,
  `anoLectivo` year(4) NOT NULL,
  `dataEmissao` date NOT NULL,
  PRIMARY KEY (`idCertificado`),
  KEY `fk_tblCertificado_tblAluno1_idx` (`idAluno`),
  KEY `fk_tblCertificado_tblTurno1_idx` (`idTurno`),
  KEY `fk_tblCertificado_tblCurso1_idx` (`idCurso`),
  KEY `fk_tblCertificado_tblTurma1_idx` (`idTurma`),
  KEY `fk_tblCertificado_tblClasse1_idx` (`idClasse`),
  CONSTRAINT `fk_tblCertificado_tblAluno1` FOREIGN KEY (`idAluno`) REFERENCES `tblaluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblCertificado_tblClasse1` FOREIGN KEY (`idClasse`) REFERENCES `tblclasse` (`idClasse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblCertificado_tblCurso1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblCertificado_tblTurma1` FOREIGN KEY (`idTurma`) REFERENCES `tblturma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblCertificado_tblTurno1` FOREIGN KEY (`idTurno`) REFERENCES `tblturno` (`idTurno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcertificado`
--

LOCK TABLES `tblcertificado` WRITE;
/*!40000 ALTER TABLE `tblcertificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcertificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblclasse`
--

DROP TABLE IF EXISTS `tblclasse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblclasse` (
  `idClasse` int(11) NOT NULL AUTO_INCREMENT,
  `classe` varchar(5) NOT NULL,
  PRIMARY KEY (`idClasse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblclasse`
--

LOCK TABLES `tblclasse` WRITE;
/*!40000 ALTER TABLE `tblclasse` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblclasse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcurso`
--

DROP TABLE IF EXISTS `tblcurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcurso` (
  `idCurso` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(30) NOT NULL,
  PRIMARY KEY (`idCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcurso`
--

LOCK TABLES `tblcurso` WRITE;
/*!40000 ALTER TABLE `tblcurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldeclaracao`
--

DROP TABLE IF EXISTS `tbldeclaracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbldeclaracao` (
  `idCertificado` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NOT NULL,
  `anoLectivo` year(4) NOT NULL,
  `dataEmissao` date NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  `idTurno` int(11) NOT NULL,
  `obsDeclaracao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCertificado`),
  KEY `fk_tblDeclaracao_tblClasse1_idx` (`idClasse`),
  KEY `fk_tblDeclaracao_tblAluno1_idx` (`idAluno`),
  KEY `fk_tblDeclaracao_tblTurma1_idx` (`idTurma`),
  KEY `fk_tblDeclaracao_tblCurso1_idx` (`idCurso`),
  KEY `fk_tblDeclaracao_tblTurno1_idx` (`idTurno`),
  CONSTRAINT `fk_tblDeclaracao_tblAluno1` FOREIGN KEY (`idAluno`) REFERENCES `tblaluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblDeclaracao_tblClasse1` FOREIGN KEY (`idClasse`) REFERENCES `tblclasse` (`idClasse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblDeclaracao_tblCurso1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblDeclaracao_tblTurma1` FOREIGN KEY (`idTurma`) REFERENCES `tblturma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblDeclaracao_tblTurno1` FOREIGN KEY (`idTurno`) REFERENCES `tblturno` (`idTurno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldeclaracao`
--

LOCK TABLES `tbldeclaracao` WRITE;
/*!40000 ALTER TABLE `tbldeclaracao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbldeclaracao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfuncionario`
--

DROP TABLE IF EXISTS `tblfuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfuncionario` (
  `idFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(45) NOT NULL,
  `generoFuncionario` enum('M','F') NOT NULL,
  `funcao` varchar(30) NOT NULL,
  `grauFormacao` varchar(15) NOT NULL,
  `EstadoFormacao` varchar(15) NOT NULL,
  `especialidade` varchar(25) NOT NULL,
  `aniversarioFuncionario` date NOT NULL,
  `classeGrau` varchar(15) NOT NULL,
  `estado` enum('ACTIVO','DEVOLVIDO','FALECIDO','TRANSFERIDO') NOT NULL,
  `biFuncionario` varchar(15) NOT NULL,
  `obsFuncionario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfuncionario`
--

LOCK TABLES `tblfuncionario` WRITE;
/*!40000 ALTER TABLE `tblfuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblfuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblguiadevolucao`
--

DROP TABLE IF EXISTS `tblguiadevolucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblguiadevolucao` (
  `idGuia` int(11) NOT NULL AUTO_INCREMENT,
  `devolvidoPara` varchar(25) NOT NULL,
  `municipio` varchar(25) NOT NULL,
  `idProvincia` int(11) NOT NULL,
  `idFuncionario` int(11) NOT NULL,
  `obsGuiaDevolucao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGuia`),
  KEY `fk_tblGuiaDevolucao_tblProvincia1_idx` (`idProvincia`),
  KEY `fk_tblGuiaDevolucao_tblFuncionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_tblGuiaDevolucao_tblFuncionario1` FOREIGN KEY (`idFuncionario`) REFERENCES `tblfuncionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblGuiaDevolucao_tblProvincia1` FOREIGN KEY (`idProvincia`) REFERENCES `tblprovincia` (`idProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblguiadevolucao`
--

LOCK TABLES `tblguiadevolucao` WRITE;
/*!40000 ALTER TABLE `tblguiadevolucao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblguiadevolucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblguiaferia`
--

DROP TABLE IF EXISTS `tblguiaferia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblguiaferia` (
  `idGuia` int(11) NOT NULL AUTO_INCREMENT,
  `idFuncionario` int(11) NOT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataFim` int(11) DEFAULT NULL,
  `obsGuiaFeria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGuia`),
  KEY `fk_tblGuiaFeria_tblFuncionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_tblGuiaFeria_tblFuncionario1` FOREIGN KEY (`idFuncionario`) REFERENCES `tblfuncionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblguiaferia`
--

LOCK TABLES `tblguiaferia` WRITE;
/*!40000 ALTER TABLE `tblguiaferia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblguiaferia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblinscricao`
--

DROP TABLE IF EXISTS `tblinscricao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblinscricao` (
  `idInscricao` int(11) NOT NULL AUTO_INCREMENT,
  `dataInscricao` date NOT NULL,
  `modalidade` enum('CONFIRMADO','MATRICULADO') NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idTurno` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `obsInscricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idInscricao`),
  KEY `fk_tblInscricao_tblAluno1_idx` (`idAluno`),
  KEY `fk_tblInscricao_tblCurso1_idx` (`idCurso`),
  KEY `fk_tblInscricao_tblTurno1_idx` (`idTurno`),
  KEY `fk_tblInscricao_tblClasse1_idx` (`idClasse`),
  KEY `fk_tblInscricao_tblTurma1_idx` (`idTurma`),
  KEY `fk_tblInscricao_tblUsuario1_idx` (`idUsuario`),
  KEY `fk_tblInscricao_tblSala1_idx` (`idSala`),
  CONSTRAINT `fk_tblInscricao_tblAluno1` FOREIGN KEY (`idAluno`) REFERENCES `tblaluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblClasse1` FOREIGN KEY (`idClasse`) REFERENCES `tblclasse` (`idClasse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblCurso1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblSala1` FOREIGN KEY (`idSala`) REFERENCES `tblsala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblTurma1` FOREIGN KEY (`idTurma`) REFERENCES `tblturma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblTurno1` FOREIGN KEY (`idTurno`) REFERENCES `tblturno` (`idTurno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblInscricao_tblUsuario1` FOREIGN KEY (`idUsuario`) REFERENCES `tblusuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblinscricao`
--

LOCK TABLES `tblinscricao` WRITE;
/*!40000 ALTER TABLE `tblinscricao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblinscricao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblprovincia`
--

DROP TABLE IF EXISTS `tblprovincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblprovincia` (
  `idProvincia` int(11) NOT NULL AUTO_INCREMENT,
  `provincia` varchar(25) NOT NULL,
  PRIMARY KEY (`idProvincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblprovincia`
--

LOCK TABLES `tblprovincia` WRITE;
/*!40000 ALTER TABLE `tblprovincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblprovincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsala`
--

DROP TABLE IF EXISTS `tblsala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblsala` (
  `idSala` int(11) NOT NULL AUTO_INCREMENT,
  `sala` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsala`
--

LOCK TABLES `tblsala` WRITE;
/*!40000 ALTER TABLE `tblsala` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblsala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransaluno`
--

DROP TABLE IF EXISTS `tbltransaluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransaluno` (
  `idTransferencia` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NOT NULL,
  `paraEscola` varchar(45) DEFAULT NULL,
  `paraMunicipio` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `idProvincia` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idTurno` int(11) NOT NULL,
  `obsAlunoTransferido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTransferencia`),
  KEY `fk_tblTransAluno_tblProvincia1_idx` (`idProvincia`),
  KEY `fk_tblTransAluno_tblTurno1_idx` (`idTurno`),
  KEY `fk_tblTransAluno_tblAluno1_idx` (`idAluno`),
  KEY `fk_tblTransAluno_tblTurma1_idx` (`idTurma`),
  KEY `fk_tblTransAluno_tblCurso1_idx` (`idCurso`),
  KEY `fk_tblTransAluno_tblClasse1_idx` (`idClasse`),
  KEY `fk_tblTransAluno_tblSala1_idx` (`idSala`),
  CONSTRAINT `fk_tblTransAluno_tblAluno1` FOREIGN KEY (`idAluno`) REFERENCES `tblaluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblClasse1` FOREIGN KEY (`idClasse`) REFERENCES `tblclasse` (`idClasse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblCurso1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblProvincia1` FOREIGN KEY (`idProvincia`) REFERENCES `tblprovincia` (`idProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblSala1` FOREIGN KEY (`idSala`) REFERENCES `tblsala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblTurma1` FOREIGN KEY (`idTurma`) REFERENCES `tblturma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransAluno_tblTurno1` FOREIGN KEY (`idTurno`) REFERENCES `tblturno` (`idTurno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransaluno`
--

LOCK TABLES `tbltransaluno` WRITE;
/*!40000 ALTER TABLE `tbltransaluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransaluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransfuncionario`
--

DROP TABLE IF EXISTS `tbltransfuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransfuncionario` (
  `idTransfererir` int(11) NOT NULL AUTO_INCREMENT,
  `idFuncionario` int(11) NOT NULL,
  `para` varchar(25) NOT NULL,
  `municipio` varchar(25) NOT NULL,
  `idProvincia` int(11) NOT NULL,
  `dataTransferencia` date NOT NULL,
  `obsFuncionarioTransferido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTransfererir`),
  KEY `fk_tblTransFuncionario_tblProvincia1_idx` (`idProvincia`),
  KEY `fk_tblTransFuncionario_tblFuncionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_tblTransFuncionario_tblFuncionario1` FOREIGN KEY (`idFuncionario`) REFERENCES `tblfuncionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTransFuncionario_tblProvincia1` FOREIGN KEY (`idProvincia`) REFERENCES `tblprovincia` (`idProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransfuncionario`
--

LOCK TABLES `tbltransfuncionario` WRITE;
/*!40000 ALTER TABLE `tbltransfuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransfuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblturma`
--

DROP TABLE IF EXISTS `tblturma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblturma` (
  `idTurma` int(11) NOT NULL AUTO_INCREMENT,
  `turma` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`idTurma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblturma`
--

LOCK TABLES `tblturma` WRITE;
/*!40000 ALTER TABLE `tblturma` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblturma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblturno`
--

DROP TABLE IF EXISTS `tblturno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblturno` (
  `idTurno` int(11) NOT NULL AUTO_INCREMENT,
  `turno` varchar(10) NOT NULL,
  PRIMARY KEY (`idTurno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblturno`
--

LOCK TABLES `tblturno` WRITE;
/*!40000 ALTER TABLE `tblturno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblturno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusuario`
--

DROP TABLE IF EXISTS `tblusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblusuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(25) NOT NULL,
  `generoUsuario` enum('M','F') NOT NULL,
  `perfil` varchar(8) NOT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `idFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_tblUsuario_tblFuncionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_tblUsuario_tblFuncionario1` FOREIGN KEY (`idFuncionario`) REFERENCES `tblfuncionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusuario`
--

LOCK TABLES `tblusuario` WRITE;
/*!40000 ALTER TABLE `tblusuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblusuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-20 23:00:37
