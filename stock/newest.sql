-- MySQL dump 10.10
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt

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
-- Current Database: `test`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test` /*!40100 DEFAULT CHARACTER SET big5 */;

USE `test`;

--
-- Table structure for table `ds000`
--

DROP TABLE IF EXISTS `ds000`;
CREATE TABLE `ds000` (
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `capital` bigint(20) default NULL,
  KEY `ipcds000new` (`snum`)
);

--
-- Table structure for table `ds000info`
--

DROP TABLE IF EXISTS `ds000info`;
CREATE TABLE `ds000info` (
  `snum` char(5) default NULL,
  `capital` bigint(20) default NULL,
  `rawProfit` float default NULL,
  `pureProfit` float default NULL,
  `unTaxtedProfit` float default NULL,
  `xxxProfit` float default NULL,
  `stockProfit` float default NULL,
  `eps1` float default NULL,
  `eps2` float default NULL,
  `eps3` float default NULL,
  `eps4` float default NULL,
  `totalEps` float default NULL,
  KEY `ipcds000info` (`snum`)
) ;


--
-- Table structure for table `mds000`
--

DROP TABLE IF EXISTS `mds000`;
CREATE TABLE `mds000` (
  `snum` char(5) default NULL
) ;

--
-- Table structure for table `pamount`
--

DROP TABLE IF EXISTS `pamount`;
CREATE TABLE `pamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `close` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `open` char(10) default NULL,
  `high` char(10) default NULL,
  `low` char(10) default NULL,
  `lclose` char(10) default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ;

--
-- Table structure for table `pamounttemp`
--

DROP TABLE IF EXISTS `pamounttemp`;
CREATE TABLE `pamounttemp` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ;

--
-- Table structure for table `pamounttemp2`
--

DROP TABLE IF EXISTS `pamounttemp2`;
CREATE TABLE `pamounttemp2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ;

--
-- Table structure for table `pamountweek`
--

DROP TABLE IF EXISTS `pamountweek`;
CREATE TABLE `pamountweek` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `ramount` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  KEY `ipaweek` (`snum`,`date`)
) ;

--
-- Table structure for table `pamountweek2`
--

DROP TABLE IF EXISTS `pamountweek2`;
CREATE TABLE `pamountweek2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `ramount` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  KEY `ipaweek` (`snum`,`date`)
) ;

--
-- Table structure for table `pcamount`
--

DROP TABLE IF EXISTS `pcamount`;
CREATE TABLE `pcamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` char(10) default NULL,
  `ra` int(11) default NULL,
  KEY `pCAmount` (`snum`,`date`)
) ;

--
-- Table structure for table `pcaverage`
--

DROP TABLE IF EXISTS `pcaverage`;
CREATE TABLE `pcaverage` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcadate` (`snum`,`date`)
) ;

--
-- Table structure for table `pdayk`
--

DROP TABLE IF EXISTS `pdayk`;
CREATE TABLE `pdayk` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `maxp` float default NULL,
  `minp` float default NULL,
  `bra` float default NULL,
  `bran` int(11) default NULL,
  `sra` float default NULL,
  `sran` int(11) default NULL,
  `ra` float default NULL,
  `ran` int(11) default NULL,
  `ranr` float default NULL,
  KEY `ipdayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pdeviation`
--

DROP TABLE IF EXISTS `pdeviation`;
CREATE TABLE `pdeviation` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `buy1` float default NULL,
  `buy3a` float default NULL,
  `buya` float default NULL,
  `buyd` float default NULL,
  `sell1` float default NULL,
  `sell3a` float default NULL,
  `sella` float default NULL,
  `selld` float default NULL,
  `rp` float default NULL,
  `prp` float default NULL,
  `upK` float default NULL,
  `downK` float default NULL,
  `K` float default NULL,
  `bra` float default NULL,
  `sra` float default NULL,
  `buym` int(11) default NULL,
  `sellm` int(11) default NULL,
  `ra` int(11) default NULL,
  KEY `ipdiva` (`snum`,`date`)
) ;

--
-- Table structure for table `pdeviationweek`
--

DROP TABLE IF EXISTS `pdeviationweek`;
CREATE TABLE `pdeviationweek` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `buy1` float default NULL,
  `buy5a` float default NULL,
  `buya` float default NULL,
  `buyd` float default NULL,
  `sell1` float default NULL,
  `sell5a` float default NULL,
  `sella` float default NULL,
  `selld` float default NULL,
  `buym` int(11) default NULL,
  `sellm` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `endp2` float default NULL,
  KEY `ipdivaW` (`snum`,`date`)
) ;

--
-- Table structure for table `pdislevel`
--

DROP TABLE IF EXISTS `pdislevel`;
CREATE TABLE `pdislevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pkddlevel`
--

DROP TABLE IF EXISTS `pkddlevel`;
CREATE TABLE `pkddlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdlevel`
--

DROP TABLE IF EXISTS `pkdlevel`;
CREATE TABLE `pkdlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdownlevel`
--

DROP TABLE IF EXISTS `pkdownlevel`;
CREATE TABLE `pkdownlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdslevel`
--

DROP TABLE IF EXISTS `pkdslevel`;
CREATE TABLE `pkdslevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkreallevel`
--

DROP TABLE IF EXISTS `pkreallevel`;
CREATE TABLE `pkreallevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkstartlevel`
--

DROP TABLE IF EXISTS `pkstartlevel`;
CREATE TABLE `pkstartlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkuplevel`
--

DROP TABLE IF EXISTS `pkuplevel`;
CREATE TABLE `pkuplevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `plast`
--

DROP TABLE IF EXISTS `plast`;
CREATE TABLE `plast` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipLast` (`snum`,`date`)
) ;

--
-- Table structure for table `pldayk`
--

DROP TABLE IF EXISTS `pldayk`;
CREATE TABLE `pldayk` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pldayk2`
--

DROP TABLE IF EXISTS `pldayk2`;
CREATE TABLE `pldayk2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `ipldayk2` (`snum`,`date`)
) ;


--
-- Table structure for table `pmamount`
--

DROP TABLE IF EXISTS `pmamount`;
CREATE TABLE `pmamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum` (`snum`,`date`,`mnum`)
) ;

DROP TABLE IF EXISTS `pMRate`;
CREATE TABLE `pMRate` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(4) default NULL,
  `sumB` int(11) default NULL,
  `mnumS` char(4) default NULL,
  `sumS` int(11) default NULL,
  `rate` float default NULL,
  KEY `ipmsnum` (`snum`,`date`)
) ;

DROP TABLE IF EXISTS `pMRate5`;
CREATE TABLE `pMRate5` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(4) default NULL,
  `sumB` int(11) default NULL,
  `mnumS` char(4) default NULL,
  `sumS` int(11) default NULL,
  `rate` float default NULL,
  KEY `ipmsnum` (`snum`,`date`)
) ;

DROP TABLE IF EXISTS `pMRateM`;
CREATE TABLE `pMRateM` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(4) default NULL,
  `sumB` int(11) default NULL,
  `mnumS` char(4) default NULL,
  `sumS` int(11) default NULL,
  `rate` float default NULL,
  KEY `ipmsnum` (`snum`,`date`)
) ;

 --
-- Table structure for table `pmamountm`
--

DROP TABLE IF EXISTS `pmamountm`;
CREATE TABLE `pmamountm` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmam` (`snum`,`date`),
  KEY `ipmsnumm` (`snum`,`date`,`mnum`)
) ;

--
-- Table structure for table `pmamountmm`
--

DROP TABLE IF EXISTS `pmamountmm`;
CREATE TABLE `pmamountmm` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `amount` int(11) default NULL,
  `money` float default NULL,
  KEY `ipmmsnumm` (`snum`,`date`,`mnum`)
) ;

--
-- Table structure for table `pmamountmmnow`
--

DROP TABLE IF EXISTS `pmamountmmnow`;
CREATE TABLE `pmamountmmnow` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `amount` int(11) default NULL,
  `money` float default NULL
) ;

--
-- Table structure for table `pmaxamount`
--

DROP TABLE IF EXISTS `pmaxamount`;
CREATE TABLE `pmaxamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` float default NULL,
  `max_ra` int(11) default NULL,
  `av5_ra` int(11) default NULL,
  `av_ra` int(11) default NULL,
  KEY `ipmax` (`snum`),
  KEY `ippower` (`snum`,`date`)
) ;

--
-- Table structure for table `pmcamount`
--

DROP TABLE IF EXISTS `pmcamount`;
CREATE TABLE `pmcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `diff` float default NULL,
  KEY `ipcmsnum` (`snum`),
  KEY `ipcmdate` (`date`)
) ;

--
-- Table structure for table `pmcamount1`
--

DROP TABLE IF EXISTS `pmcamount1`;
CREATE TABLE `pmcamount1` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL,
  KEY `ipmcamount1` (`snum`,`date`)
) ;


--
-- Table structure for table `pmcamount2`
--

DROP TABLE IF EXISTS `pmcamount2`;
CREATE TABLE `pmcamount2` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL
) ;


--
-- Table structure for table `pmcamount3`
--

DROP TABLE IF EXISTS `pmcamount3`;
CREATE TABLE `pmcamount3` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL
) ;

--
-- Table structure for table `pmcamount3r`
--

DROP TABLE IF EXISTS `pmcamount3r`;
CREATE TABLE `pmcamount3r` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL
) ;

--
-- Table structure for table `pmet3`
--

DROP TABLE IF EXISTS `pmet3`;
CREATE TABLE `pmet3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `tstart` char(8) default NULL,
  `tend` char(8) default NULL,
  `ptime` int(11) default NULL,
  `bran` int(11) default NULL,
  `sran` int(11) default NULL,
  `ran` int(11) default NULL,
  `buying` float default NULL,
  `selling` float default NULL,
  `level` int(11) default NULL,
  KEY `ipmet3` (`snum`,`date`)
) ;

--
-- Table structure for table `pmk`
--

DROP TABLE IF EXISTS `pmk`;
CREATE TABLE `pmk` (
  `date` char(8) default NULL,
  `snum` char(6) default NULL,
  `sname` char(20) default NULL,
  `mnum` char(6) default NULL,
  `mname` char(20) default NULL,
  `ra` int(11) default NULL,
  `rar` float default NULL,
  KEY `ipmk` (`date`,`snum`)
) ;

--
-- Table structure for table `pmmamount`
--

DROP TABLE IF EXISTS `pmmamount`;
CREATE TABLE `pmmamount` (
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `money` float default NULL
) ;

--
-- Table structure for table `pmname`
--

DROP TABLE IF EXISTS `pmname`;
CREATE TABLE `pmname` (
  `mnum` char(4) default NULL,
  `mname` char(20) default NULL,
  `type` char(1) default NULL
) ;

--
-- Table structure for table `pmoney`
--

DROP TABLE IF EXISTS `pmoney`;
CREATE TABLE `pmoney` (
  `date` char(8) default NULL,
  `type` char(1) default NULL,
  `buy` bigint(20) default NULL,
  `sell` bigint(20) default NULL,
  `diff` bigint(20) default NULL
) ;

--
-- Table structure for table `pmtcamount`
--

DROP TABLE IF EXISTS `pmtcamount`;
CREATE TABLE `pmtcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer1` char(20) default NULL,
  `mbuy1` float default NULL,
  `mbuyer2` char(20) default NULL,
  `mbuy2` float default NULL,
  `mbuyer3` char(20) default NULL,
  `mbuy3` float default NULL,
  `mseller1` char(20) default NULL,
  `msell1` float default NULL,
  `mseller2` char(20) default NULL,
  `msell2` float default NULL,
  `mseller3` char(20) default NULL,
  `msell3` float default NULL,
  `diff1` float default NULL,
  `diff2` float default NULL,
  `diff3` float default NULL
) ;

--
-- Table structure for table `ppower`
--

DROP TABLE IF EXISTS `ppower`;
CREATE TABLE `ppower` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `inSum` int(11) default NULL,
  `outSum` int(11) default NULL,
  `SumSum` int(11) default NULL,
  `power` float default NULL,
  KEY `ipppower` (`snum`,`date`)
) ;


--
-- Table structure for table `pstatistics`
--

DROP TABLE IF EXISTS `pstatistics`;
CREATE TABLE `pstatistics` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve1` int(11) default NULL,
  `prevDislevel1` int(11) default NULL,
  `prevPeriodlevel1` int(11) default NULL,
  `prevSlopelevel1` int(11) default NULL,
  `prevTSlopelevel1` int(11) default NULL,
  `prevCurve2` int(11) default NULL,
  `prevDislevel2` int(11) default NULL,
  `prevPeriodlevel2` int(11) default NULL,
  `prevSlopelevel2` int(11) default NULL,
  `Curve` int(11) default NULL,
  `Dislevel` int(11) default NULL,
  `Periodlevel` int(11) default NULL,
  `Slopelevel` int(11) default NULL,
  `TSlopelevel` int(11) default NULL,
  KEY `iptsldayk` (`snum`,`serial`)
) ;

--
-- Table structure for table `pstatisticskd`
--

DROP TABLE IF EXISTS `pstatisticskd`;
CREATE TABLE `pstatisticskd` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve` int(11) default NULL,
  `prevKDLevel` int(11) default NULL,
  `prevKDD` int(11) default NULL,
  `prevKDS` int(11) default NULL,
  `prevKDGcross` int(11) default NULL,
  `prevKDDcross` int(11) default NULL,
  `prevVlevel` int(11) default NULL,
  `prevFVlevel` int(11) default NULL,
  `prevLVlevel` int(11) default NULL,
  `Curve` int(11) default NULL,
  `KDLevel` int(11) default NULL,
  `KDD` int(11) default NULL,
  `KDS` int(11) default NULL,
  `KDGcross` int(11) default NULL,
  `KDDcross` int(11) default NULL,
  `Vlevel` int(11) default NULL,
  `FVlevel` int(11) default NULL,
  `LVlevel` int(11) default NULL,
  KEY `iptvstckd` (`snum`,`serial`)
) ;

--
-- Table structure for table `pstatisticsv`
--

DROP TABLE IF EXISTS `pstatisticsv`;
CREATE TABLE `pstatisticsv` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve` int(11) default NULL,
  `prevDislevel` int(11) default NULL,
  `prevPeriodlevel` int(11) default NULL,
  `prevSlopelevel` int(11) default NULL,
  `prevTSlopelevel` int(11) default NULL,
  `prevVlevel` int(11) default NULL,
  `prevFVlevel` int(11) default NULL,
  `prevLVlevel` int(11) default NULL,
  `Curve` int(11) default NULL,
  `Dislevel` int(11) default NULL,
  `Periodlevel` int(11) default NULL,
  `Slopelevel` int(11) default NULL,
  `TSlopelevel` int(11) default NULL,
  `Vlevel` int(11) default NULL,
  `FVlevel` int(11) default NULL,
  `LVlevel` int(11) default NULL,
  KEY `iptvstcdayk` (`snum`,`serial`)
) ;

--
-- Table structure for table `ptcamount`
--

DROP TABLE IF EXISTS `ptcamount`;
CREATE TABLE `ptcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `nowp` float default NULL,
  `mbuyer1` char(20) default NULL,
  `mbuy1` float default NULL,
  `mbuyavg1` float default NULL,
  `mbuyer2` char(20) default NULL,
  `mbuy2` float default NULL,
  `mbuyavg2` float default NULL,
  `mbuyer3` char(20) default NULL,
  `mbuy3` float default NULL,
  `mbuyavg3` float default NULL,
  `mseller1` char(20) default NULL,
  `msell1` float default NULL,
  `msellavg1` float default NULL,
  `mseller2` char(20) default NULL,
  `msell2` float default NULL,
  `msellavg2` float default NULL,
  `mseller3` char(20) default NULL,
  `msell3` float default NULL,
  `msellavg3` float default NULL,
  `diff` float default NULL
) ;


--
-- Table structure for table `ptimelevel`
--

DROP TABLE IF EXISTS `ptimelevel`;
CREATE TABLE `ptimelevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `ptslopelevel`
--

DROP TABLE IF EXISTS `ptslopelevel`;
CREATE TABLE `ptslopelevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptsldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pvoldlevel`
--

DROP TABLE IF EXISTS `pvoldlevel`;
CREATE TABLE `pvoldlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;

--
-- Table structure for table `pvoltlevel`
--

DROP TABLE IF EXISTS `pvoltlevel`;
CREATE TABLE `pvoltlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;

--
-- Table structure for table `pvolulevel`
--

DROP TABLE IF EXISTS `pvolulevel`;
CREATE TABLE `pvolulevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;


--
-- Current Database: `test`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test` /*!40100 DEFAULT CHARACTER SET big5 */;

USE `test`;

--
-- Table structure for table `ds000`
--

DROP TABLE IF EXISTS `ds000`;
CREATE TABLE `ds000` (
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `capital` bigint(20) default NULL,
  KEY `ipcds000new` (`snum`)
) ;

--
-- Table structure for table `ds000info`
--

DROP TABLE IF EXISTS `ds000info`;
CREATE TABLE `ds000info` (
  `snum` char(5) default NULL,
  `capital` bigint(20) default NULL,
  `rawProfit` float default NULL,
  `pureProfit` float default NULL,
  `unTaxtedProfit` float default NULL,
  `xxxProfit` float default NULL,
  `stockProfit` float default NULL,
  `eps1` float default NULL,
  `eps2` float default NULL,
  `eps3` float default NULL,
  `eps4` float default NULL,
  `totalEps` float default NULL,
  KEY `ipcds000info` (`snum`)
) ;


--
-- Table structure for table `mds000`
--

DROP TABLE IF EXISTS `mds000`;
CREATE TABLE `mds000` (
  `snum` char(5) default NULL
) ;

--
-- Table structure for table `pamount`
--

DROP TABLE IF EXISTS `pamount`;
CREATE TABLE `pamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ;


--
-- Table structure for table `pamounta`
--

DROP TABLE IF EXISTS `pamounta`;
CREATE TABLE `pamounta` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnum` (`snum`,`date`),
  KEY `iprp` (`rp`)
) ;

--
-- Table structure for table `pamounttemp`
--

DROP TABLE IF EXISTS `pamounttemp`;
CREATE TABLE `pamounttemp` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ;


--
-- Table structure for table `pamountweek`
--

DROP TABLE IF EXISTS `pamountweek`;
CREATE TABLE `pamountweek` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `ramount` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  KEY `ipaweek` (`snum`,`date`)
) ;

--
-- Table structure for table `pamountweek2`
--

DROP TABLE IF EXISTS `pamountweek2`;
CREATE TABLE `pamountweek2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `ramount` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  KEY `ipaweek` (`snum`,`date`)
) ;

--
-- Table structure for table `pcamount`
--

DROP TABLE IF EXISTS `pcamount`;
CREATE TABLE `pcamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` char(10) default NULL,
  `ra` int(11) default NULL,
  KEY `pCAmount` (`snum`,`date`)
) ;

--
-- Table structure for table `pcaverage`
--

DROP TABLE IF EXISTS `pcaverage`;
CREATE TABLE `pcaverage` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcadate` (`snum`,`date`)
) ;

--
-- Table structure for table `pdayk`
--

DROP TABLE IF EXISTS `pdayk`;
CREATE TABLE `pdayk` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `maxp` float default NULL,
  `minp` float default NULL,
  `bra` float default NULL,
  `bran` int(11) default NULL,
  `sra` float default NULL,
  `sran` int(11) default NULL,
  `ra` float default NULL,
  `ran` int(11) default NULL,
  `ranr` float default NULL,
  KEY `ipdayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pdeviation`
--

DROP TABLE IF EXISTS `pdeviation`;
CREATE TABLE `pdeviation` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `buy1` float default NULL,
  `buy3a` float default NULL,
  `buya` float default NULL,
  `buyd` float default NULL,
  `sell1` float default NULL,
  `sell3a` float default NULL,
  `sella` float default NULL,
  `selld` float default NULL,
  `rp` float default NULL,
  `prp` float default NULL,
  `upK` float default NULL,
  `downK` float default NULL,
  `K` float default NULL,
  `bra` float default NULL,
  `sra` float default NULL,
  `buym` int(11) default NULL,
  `sellm` int(11) default NULL,
  `ra` int(11) default NULL,
  KEY `ipdiva` (`snum`,`date`)
) ;

--
-- Table structure for table `pdeviationweek`
--

DROP TABLE IF EXISTS `pdeviationweek`;
CREATE TABLE `pdeviationweek` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `buy1` float default NULL,
  `buy5a` float default NULL,
  `buya` float default NULL,
  `buyd` float default NULL,
  `sell1` float default NULL,
  `sell5a` float default NULL,
  `sella` float default NULL,
  `selld` float default NULL,
  `buym` int(11) default NULL,
  `sellm` int(11) default NULL,
  `tamount` int(11) default NULL,
  `mamount` float default NULL,
  `average` float default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `endp2` float default NULL,
  KEY `ipdivaW` (`snum`,`date`)
) ;

--
-- Table structure for table `pdislevel`
--

DROP TABLE IF EXISTS `pdislevel`;
CREATE TABLE `pdislevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pkddlevel`
--

DROP TABLE IF EXISTS `pkddlevel`;
CREATE TABLE `pkddlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdlevel`
--

DROP TABLE IF EXISTS `pkdlevel`;
CREATE TABLE `pkdlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdownlevel`
--

DROP TABLE IF EXISTS `pkdownlevel`;
CREATE TABLE `pkdownlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkdslevel`
--

DROP TABLE IF EXISTS `pkdslevel`;
CREATE TABLE `pkdslevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkreallevel`
--

DROP TABLE IF EXISTS `pkreallevel`;
CREATE TABLE `pkreallevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkstartlevel`
--

DROP TABLE IF EXISTS `pkstartlevel`;
CREATE TABLE `pkstartlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `pkuplevel`
--

DROP TABLE IF EXISTS `pkuplevel`;
CREATE TABLE `pkuplevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL
) ;

--
-- Table structure for table `plast`
--

DROP TABLE IF EXISTS `plast`;
CREATE TABLE `plast` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `abuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `asell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipLast` (`snum`,`date`)
) ;

--
-- Table structure for table `pldayk`
--

DROP TABLE IF EXISTS `pldayk`;
CREATE TABLE `pldayk` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pldayk2`
--

DROP TABLE IF EXISTS `pldayk2`;
CREATE TABLE `pldayk2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `ipldayk2` (`snum`,`date`)
) ;

--
-- Table structure for table `pmaamount`
--

DROP TABLE IF EXISTS `pmaamount`;
CREATE TABLE `pmaamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mnum` char(4) default NULL,
  `mname` char(20) default NULL,
  `amount` int(11) default NULL,
  `amountP` float default NULL,
  `avgp` float default NULL,
  `nowp` float default NULL,
  KEY `ipmadate` (`date`,`snum`,`mnum`)
) ;

--
-- Table structure for table `pmamount`
--

DROP TABLE IF EXISTS `pmamount`;
CREATE TABLE `pmamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum` (`snum`,`date`,`mnum`)
) ;

--
-- Table structure for table `pmamountm`
--

DROP TABLE IF EXISTS `pmamountm`;
CREATE TABLE `pmamountm` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmam` (`snum`,`date`),
  KEY `ipmsnumm` (`snum`,`date`,`mnum`)
) ;

--
-- Table structure for table `pmamountmm`
--

DROP TABLE IF EXISTS `pmamountmm`;
CREATE TABLE `pmamountmm` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `amount` int(11) default NULL,
  `money` float default NULL,
  KEY `ipmmsnumm` (`snum`,`date`,`mnum`)
) ;

--
-- Table structure for table `pmamountmmnow`
--

DROP TABLE IF EXISTS `pmamountmmnow`;
CREATE TABLE `pmamountmmnow` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `amount` int(11) default NULL,
  `money` float default NULL
) ;

--
-- Table structure for table `pmaxamount`
--

DROP TABLE IF EXISTS `pmaxamount`;
CREATE TABLE `pmaxamount` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rp` float default NULL,
  `max_ra` int(11) default NULL,
  `av5_ra` int(11) default NULL,
  `av_ra` int(11) default NULL,
  KEY `ipmax` (`snum`),
  KEY `ippower` (`snum`,`date`)
) ;

--
-- Table structure for table `pmcamount`
--

DROP TABLE IF EXISTS `pmcamount`;
CREATE TABLE `pmcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `diff` float default NULL,
  KEY `ipcmsnum` (`snum`),
  KEY `ipcmdate` (`date`)
) ;

--
-- Table structure for table `pmcamount1`
--

DROP TABLE IF EXISTS `pmcamount1`;
CREATE TABLE `pmcamount1` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL,
  KEY `ipmcamount1` (`snum`,`date`)
) ;


DROP TABLE IF EXISTS `pmcamount2`;
CREATE TABLE `pmcamount2` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL
) ;

--
-- Table structure for table `pmcamount3`
--

DROP TABLE IF EXISTS `pmcamount3`;
CREATE TABLE `pmcamount3` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer` char(20) default NULL,
  `mbuy` float default NULL,
  `mbuyavg` float default NULL,
  `mseller` char(20) default NULL,
  `msell` float default NULL,
  `msellavg` float default NULL,
  `nowp` float default NULL,
  `diff` float default NULL,
  `pdiff` float default NULL
) ;

--
-- Table structure for table `pmet3`
--

DROP TABLE IF EXISTS `pmet3`;
CREATE TABLE `pmet3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `startp` float default NULL,
  `endp` float default NULL,
  `tstart` char(8) default NULL,
  `tend` char(8) default NULL,
  `ptime` int(11) default NULL,
  `bran` int(11) default NULL,
  `sran` int(11) default NULL,
  `ran` int(11) default NULL,
  `buying` float default NULL,
  `selling` float default NULL,
  `level` int(11) default NULL,
  KEY `ipmet3` (`snum`,`date`)
) ;

--
-- Table structure for table `pmk`
--

DROP TABLE IF EXISTS `pmk`;
CREATE TABLE `pmk` (
  `date` char(8) default NULL,
  `snum` char(6) default NULL,
  `sname` char(20) default NULL,
  `mnum` char(6) default NULL,
  `mname` char(20) default NULL,
  `ra` int(11) default NULL,
  `rar` float default NULL,
  KEY `ipmk` (`date`,`snum`)
) ;

--
-- Table structure for table `pmmamount`
--

DROP TABLE IF EXISTS `pmmamount`;
CREATE TABLE `pmmamount` (
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `money` float default NULL
) ;

--
-- Table structure for table `pmname`
--

DROP TABLE IF EXISTS `pmname`;
CREATE TABLE `pmname` (
  `mnum` char(4) default NULL,
  `mname` char(20) default NULL,
  `type` char(1) default NULL
) ;

--
-- Table structure for table `pmoney`
--

DROP TABLE IF EXISTS `pmoney`;
CREATE TABLE `pmoney` (
  `date` char(8) default NULL,
  `type` char(1) default NULL,
  `buy` bigint(20) default NULL,
  `sell` bigint(20) default NULL,
  `diff` bigint(20) default NULL
) ;

--
-- Table structure for table `pmtcamount`
--

DROP TABLE IF EXISTS `pmtcamount`;
CREATE TABLE `pmtcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `mbuyer1` char(20) default NULL,
  `mbuy1` float default NULL,
  `mbuyer2` char(20) default NULL,
  `mbuy2` float default NULL,
  `mbuyer3` char(20) default NULL,
  `mbuy3` float default NULL,
  `mseller1` char(20) default NULL,
  `msell1` float default NULL,
  `mseller2` char(20) default NULL,
  `msell2` float default NULL,
  `mseller3` char(20) default NULL,
  `msell3` float default NULL,
  `diff1` float default NULL,
  `diff2` float default NULL,
  `diff3` float default NULL
) ;

--
-- Table structure for table `ppower`
--

DROP TABLE IF EXISTS `ppower`;
CREATE TABLE `ppower` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `inSum` int(11) default NULL,
  `outSum` int(11) default NULL,
  `SumSum` int(11) default NULL,
  `power` float default NULL,
  KEY `ipppower` (`snum`,`date`)
) ;


--
-- Table structure for table `pslopelevel`
--

DROP TABLE IF EXISTS `pslopelevel`;
CREATE TABLE `pslopelevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pssuper2`
--

DROP TABLE IF EXISTS `pssuper2`;
CREATE TABLE `pssuper2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Buy` float default NULL,
  `Sell` float default NULL,
  `Total` float default NULL,
  `BM` float default NULL,
  `SM` float default NULL,
  `TM` float default NULL,
  `BuyingM` float default NULL,
  `SellingM` float default NULL,
  KEY `ipssuper2` (`snum`,`date`)
) ;

--
-- Table structure for table `pstatistics`
--

DROP TABLE IF EXISTS `pstatistics`;
CREATE TABLE `pstatistics` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve1` int(11) default NULL,
  `prevDislevel1` int(11) default NULL,
  `prevPeriodlevel1` int(11) default NULL,
  `prevSlopelevel1` int(11) default NULL,
  `prevTSlopelevel1` int(11) default NULL,
  `prevCurve2` int(11) default NULL,
  `prevDislevel2` int(11) default NULL,
  `prevPeriodlevel2` int(11) default NULL,
  `prevSlopelevel2` int(11) default NULL,
  `Curve` int(11) default NULL,
  `Dislevel` int(11) default NULL,
  `Periodlevel` int(11) default NULL,
  `Slopelevel` int(11) default NULL,
  `TSlopelevel` int(11) default NULL,
  KEY `iptsldayk` (`snum`,`serial`)
) ;

--
-- Table structure for table `pstatisticskd`
--

DROP TABLE IF EXISTS `pstatisticskd`;
CREATE TABLE `pstatisticskd` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve` int(11) default NULL,
  `prevKDLevel` int(11) default NULL,
  `prevKDD` int(11) default NULL,
  `prevKDS` int(11) default NULL,
  `prevKDGcross` int(11) default NULL,
  `prevKDDcross` int(11) default NULL,
  `prevVlevel` int(11) default NULL,
  `prevFVlevel` int(11) default NULL,
  `prevLVlevel` int(11) default NULL,
  `Curve` int(11) default NULL,
  `KDLevel` int(11) default NULL,
  `KDD` int(11) default NULL,
  `KDS` int(11) default NULL,
  `KDGcross` int(11) default NULL,
  `KDDcross` int(11) default NULL,
  `Vlevel` int(11) default NULL,
  `FVlevel` int(11) default NULL,
  `LVlevel` int(11) default NULL,
  KEY `iptvstckd` (`snum`,`serial`)
) ;

--
-- Table structure for table `pstatisticsv`
--

DROP TABLE IF EXISTS `pstatisticsv`;
CREATE TABLE `pstatisticsv` (
  `snum` char(5) default NULL,
  `serial` int(11) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `prevCurve` int(11) default NULL,
  `prevDislevel` int(11) default NULL,
  `prevPeriodlevel` int(11) default NULL,
  `prevSlopelevel` int(11) default NULL,
  `prevTSlopelevel` int(11) default NULL,
  `prevVlevel` int(11) default NULL,
  `prevFVlevel` int(11) default NULL,
  `prevLVlevel` int(11) default NULL,
  `Curve` int(11) default NULL,
  `Dislevel` int(11) default NULL,
  `Periodlevel` int(11) default NULL,
  `Slopelevel` int(11) default NULL,
  `TSlopelevel` int(11) default NULL,
  `Vlevel` int(11) default NULL,
  `FVlevel` int(11) default NULL,
  `LVlevel` int(11) default NULL,
  KEY `iptvstcdayk` (`snum`,`serial`)
) ;

--
-- Table structure for table `psuper`
--

DROP TABLE IF EXISTS `psuper`;
CREATE TABLE `psuper` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `buyM` float default NULL,
  `sellM` float default NULL,
  `BSM` float default NULL,
  `BSRM` float default NULL,
  `totalM` float default NULL,
  `totalRM` float default NULL,
  `buyingM` float default NULL,
  `sellingM` float default NULL,
  `BSingM` float default NULL,
  `BSingRM` float default NULL,
  `BSDM` float default NULL,
  `TotalDM` float default NULL,
  `BSingDM` float default NULL,
  `Rp` float default NULL,
  `MW` float default NULL,
  KEY `ipsuper` (`snum`,`date`)
) ;


--
-- Table structure for table `ptcamount`
--

DROP TABLE IF EXISTS `ptcamount`;
CREATE TABLE `ptcamount` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `sname` char(15) default NULL,
  `nowp` float default NULL,
  `mbuyer1` char(20) default NULL,
  `mbuy1` float default NULL,
  `mbuyavg1` float default NULL,
  `mbuyer2` char(20) default NULL,
  `mbuy2` float default NULL,
  `mbuyavg2` float default NULL,
  `mbuyer3` char(20) default NULL,
  `mbuy3` float default NULL,
  `mbuyavg3` float default NULL,
  `mseller1` char(20) default NULL,
  `msell1` float default NULL,
  `msellavg1` float default NULL,
  `mseller2` char(20) default NULL,
  `msell2` float default NULL,
  `msellavg2` float default NULL,
  `mseller3` char(20) default NULL,
  `msell3` float default NULL,
  `msellavg3` float default NULL,
  `diff` float default NULL
) ;

--
-- Table structure for table `ptimelevel`
--

DROP TABLE IF EXISTS `ptimelevel`;
CREATE TABLE `ptimelevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `ipldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `ptslopelevel`
--

DROP TABLE IF EXISTS `ptslopelevel`;
CREATE TABLE `ptslopelevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptsldayk` (`snum`,`date`)
) ;

--
-- Table structure for table `pvoldlevel`
--

DROP TABLE IF EXISTS `pvoldlevel`;
CREATE TABLE `pvoldlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;

--
-- Table structure for table `pvoltlevel`
--

DROP TABLE IF EXISTS `pvoltlevel`;
CREATE TABLE `pvoltlevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;

--
-- Table structure for table `pvolulevel`
--

DROP TABLE IF EXISTS `pvolulevel`;
CREATE TABLE `pvolulevel` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `samples` int(11) default NULL,
  `measure` float default NULL,
  `_error` float default NULL,
  `level` float default NULL,
  KEY `iptvolt` (`snum`,`date`)
) ;

DROP TABLE IF EXISTS `pamountS5M`;
CREATE TABLE `pamountS5M` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr`float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ;

DROP TABLE IF EXISTS `pamountWS5M`;
CREATE TABLE `pamountWS5M` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr`float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ;


DROP TABLE IF EXISTS `pamountS15M`;
CREATE TABLE `pamountS15M` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr`float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-04-09 17:04:48
