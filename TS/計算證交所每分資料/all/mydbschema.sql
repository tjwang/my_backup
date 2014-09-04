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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test` /*!40100 DEFAULT CHARACTER SET latin1 */;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000_200908`
--

DROP TABLE IF EXISTS `ds000_200908`;
CREATE TABLE `ds000_200908` (
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `capital` bigint(20) default NULL,
  KEY `ipcds000new` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000attr`
--

DROP TABLE IF EXISTS `ds000attr`;
CREATE TABLE `ds000attr` (
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `type` char(10) default NULL,
  `category` char(30) default NULL,
  `CCODE` char(2) default NULL,
  `TCODE` char(1) default NULL,
  KEY `ds000attrnew` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `dcdate` char(8) default NULL,
  `dsdate` char(8) default NULL,
  `dc` float default NULL,
  `ds` float default NULL,
  `worth` float default NULL,
  KEY `ipcds000info` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000info200908`
--

DROP TABLE IF EXISTS `ds000info200908`;
CREATE TABLE `ds000info200908` (
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000info200909`
--

DROP TABLE IF EXISTS `ds000info200909`;
CREATE TABLE `ds000info200909` (
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
  `dcdate` char(8) default NULL,
  `dsdate` char(8) default NULL,
  `dc` float default NULL,
  `ds` float default NULL,
  `worth` float default NULL,
  KEY `ipcds000info` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000infoold`
--

DROP TABLE IF EXISTS `ds000infoold`;
CREATE TABLE `ds000infoold` (
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ds000old`
--

DROP TABLE IF EXISTS `ds000old`;
CREATE TABLE `ds000old` (
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `capital` bigint(20) default NULL,
  KEY `ipcds000new` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `mmap`
--

DROP TABLE IF EXISTS `mmap`;
CREATE TABLE `mmap` (
  `mnum1` char(5) default NULL,
  `mnum2` char(5) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo`
--

DROP TABLE IF EXISTS `pamountinfo`;
CREATE TABLE `pamountinfo` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `open` float default NULL,
  `close` float default NULL,
  `rate` float default NULL,
  `Avg` float default NULL,
  `Open_upAvg` float default NULL,
  `Open_downAvg` float default NULL,
  `Close_upAvg` float default NULL,
  `Close_downAvg` float default NULL,
  `Open_upAmt` bigint(20) default NULL,
  `Open_downAmt` bigint(20) default NULL,
  `Close_upAmt` bigint(20) default NULL,
  `Close_downAmt` bigint(20) default NULL,
  `hand_rate` float default NULL,
  KEY `ipsnuminfo` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo2`
--

DROP TABLE IF EXISTS `pamountinfo2`;
CREATE TABLE `pamountinfo2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Bamount` bigint(20) default NULL,
  `BViscosity` float default NULL,
  `Samount` bigint(20) default NULL,
  `SViscosity` float default NULL,
  `BVRV` float default NULL,
  `BVT` float default NULL,
  `RVT` float default NULL,
  `Total` bigint(20) default NULL,
  KEY `ipsnuminfo2` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo3`
--

DROP TABLE IF EXISTS `pamountinfo3`;
CREATE TABLE `pamountinfo3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Bamount` bigint(20) default NULL,
  `BViscosity` float default NULL,
  `Samount` bigint(20) default NULL,
  `SViscosity` float default NULL,
  `BVRV` float default NULL,
  `BVT` float default NULL,
  `RVT` float default NULL,
  `Total` bigint(20) default NULL,
  KEY `ipsnuminfo2` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo3_15m`
--

DROP TABLE IF EXISTS `pamountinfo3_15m`;
CREATE TABLE `pamountinfo3_15m` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `Bamount` bigint(20) default NULL,
  `BViscosity` float default NULL,
  `Samount` bigint(20) default NULL,
  `SViscosity` float default NULL,
  `BVRV` float default NULL,
  `BVT` float default NULL,
  `RVT` float default NULL,
  `Total` bigint(20) default NULL,
  KEY `ipsnuminfo3_15m` (`snum`,`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo4`
--

DROP TABLE IF EXISTS `pamountinfo4`;
CREATE TABLE `pamountinfo4` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rangeS` float default NULL,
  `timeS` char(8) default NULL,
  `rangeE` float default NULL,
  `timeE` char(8) default NULL,
  `amount` float default NULL,
  KEY `ipsnuminfo4` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo4a`
--

DROP TABLE IF EXISTS `pamountinfo4a`;
CREATE TABLE `pamountinfo4a` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `rangeS` float default NULL,
  `timeS` char(8) default NULL,
  `rangeE` float default NULL,
  `timeE` char(8) default NULL,
  `amount` float default NULL,
  `tamount` bigint(20) default NULL,
  KEY `ipsnuminfo4` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo5`
--

DROP TABLE IF EXISTS `pamountinfo5`;
CREATE TABLE `pamountinfo5` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `open` float default NULL,
  `close` float default NULL,
  `inAmount` bigint(20) default NULL,
  `inMoneyAmt` float default NULL,
  `outAmount` bigint(20) default NULL,
  `outMoneyAmt` float default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `diff` float default NULL,
  KEY `ipsnuminfo5` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountinfo6`
--

DROP TABLE IF EXISTS `pamountinfo6`;
CREATE TABLE `pamountinfo6` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `open` float default NULL,
  `close` float default NULL,
  `inAmount` bigint(20) default NULL,
  `inMoneyAmt` float default NULL,
  `outAmount` bigint(20) default NULL,
  `outMoneyAmt` float default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `diff` float default NULL,
  KEY `ipsnuminfo6` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountnew`
--

DROP TABLE IF EXISTS `pamountnew`;
CREATE TABLE `pamountnew` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountnew200909`
--

DROP TABLE IF EXISTS `pamountnew200909`;
CREATE TABLE `pamountnew200909` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountnew200910`
--

DROP TABLE IF EXISTS `pamountnew200910`;
CREATE TABLE `pamountnew200910` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountnew200911_12`
--

DROP TABLE IF EXISTS `pamountnew200911_12`;
CREATE TABLE `pamountnew200911_12` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamounts15m`
--

DROP TABLE IF EXISTS `pamounts15m`;
CREATE TABLE `pamounts15m` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr` float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamounts5m`
--

DROP TABLE IF EXISTS `pamounts5m`;
CREATE TABLE `pamounts5m` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr` float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamounttemp`
--

DROP TABLE IF EXISTS `pamounttemp`;
CREATE TABLE `pamounttemp` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmpnew` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamounttemp2`
--

DROP TABLE IF EXISTS `pamounttemp2`;
CREATE TABLE `pamounttemp2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumtmp2new` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pamountws5m`
--

DROP TABLE IF EXISTS `pamountws5m`;
CREATE TABLE `pamountws5m` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `sp` float default NULL,
  `ep` float default NULL,
  `ra` int(11) default NULL,
  `rarr` float default NULL,
  `rc` float default NULL,
  `ocprr` float default NULL,
  `hlprr` float default NULL,
  KEY `ipsnum` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pavgindex`
--

DROP TABLE IF EXISTS `pavgindex`;
CREATE TABLE `pavgindex` (
  `date` char(8) default NULL,
  `avgIndexP` float default NULL,
  `avgIndexC` float default NULL,
  `Amount` float default NULL,
  `Product` float default NULL,
  `Credits` float default NULL,
  KEY `ipavgIndex` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pavgindex_of`;
CREATE TABLE `pavgindex_of` (
  `date` char(8) default NULL,
  `avgIndexM` float default NULL,
  `avgIndexV` float default NULL,
  `avgIndexC` float default NULL,
  `AmountM` float default NULL,
  `AmountV` float default NULL,
  `AmountC` float default NULL,
  `CreditsM` float default NULL,
  `CreditsV` float default NULL,
  `CreditsC` float default NULL,
  KEY `ipavgIndex` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pavgindex_old`
--

DROP TABLE IF EXISTS `pavgindex_old`;
CREATE TABLE `pavgindex_old` (
  `date` char(8) default NULL,
  `avgIndex` float default NULL,
  `Amount` float default NULL,
  KEY `ipavgIndex` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1`
--

DROP TABLE IF EXISTS `pcp1`;
CREATE TABLE `pcp1` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200809`
--

DROP TABLE IF EXISTS `pcp1_200809`;
CREATE TABLE `pcp1_200809` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200810`
--

DROP TABLE IF EXISTS `pcp1_200810`;
CREATE TABLE `pcp1_200810` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200811`
--

DROP TABLE IF EXISTS `pcp1_200811`;
CREATE TABLE `pcp1_200811` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200812`
--

DROP TABLE IF EXISTS `pcp1_200812`;
CREATE TABLE `pcp1_200812` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200902`
--

DROP TABLE IF EXISTS `pcp1_200902`;
CREATE TABLE `pcp1_200902` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200903`
--

DROP TABLE IF EXISTS `pcp1_200903`;
CREATE TABLE `pcp1_200903` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200904`
--

DROP TABLE IF EXISTS `pcp1_200904`;
CREATE TABLE `pcp1_200904` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200905`
--

DROP TABLE IF EXISTS `pcp1_200905`;
CREATE TABLE `pcp1_200905` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200906`
--

DROP TABLE IF EXISTS `pcp1_200906`;
CREATE TABLE `pcp1_200906` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200907`
--

DROP TABLE IF EXISTS `pcp1_200907`;
CREATE TABLE `pcp1_200907` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200908`
--

DROP TABLE IF EXISTS `pcp1_200908`;
CREATE TABLE `pcp1_200908` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200909`
--

DROP TABLE IF EXISTS `pcp1_200909`;
CREATE TABLE `pcp1_200909` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200910`
--

DROP TABLE IF EXISTS `pcp1_200910`;
CREATE TABLE `pcp1_200910` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200911`
--

DROP TABLE IF EXISTS `pcp1_200911`;
CREATE TABLE `pcp1_200911` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_200912`
--

DROP TABLE IF EXISTS `pcp1_200912`;
CREATE TABLE `pcp1_200912` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_201001`
--

DROP TABLE IF EXISTS `pcp1_201001`;
CREATE TABLE `pcp1_201001` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_201002`
--

DROP TABLE IF EXISTS `pcp1_201002`;
CREATE TABLE `pcp1_201002` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp1_201003`
--

DROP TABLE IF EXISTS `pcp1_201003`;
CREATE TABLE `pcp1_201003` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2`
--

DROP TABLE IF EXISTS `pcp2`;
CREATE TABLE `pcp2` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200810`
--

DROP TABLE IF EXISTS `pcp2_200810`;
CREATE TABLE `pcp2_200810` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200811`
--

DROP TABLE IF EXISTS `pcp2_200811`;
CREATE TABLE `pcp2_200811` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200812`
--

DROP TABLE IF EXISTS `pcp2_200812`;
CREATE TABLE `pcp2_200812` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200902`
--

DROP TABLE IF EXISTS `pcp2_200902`;
CREATE TABLE `pcp2_200902` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200903`
--

DROP TABLE IF EXISTS `pcp2_200903`;
CREATE TABLE `pcp2_200903` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200904`
--

DROP TABLE IF EXISTS `pcp2_200904`;
CREATE TABLE `pcp2_200904` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200905`
--

DROP TABLE IF EXISTS `pcp2_200905`;
CREATE TABLE `pcp2_200905` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200906`
--

DROP TABLE IF EXISTS `pcp2_200906`;
CREATE TABLE `pcp2_200906` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200907`
--

DROP TABLE IF EXISTS `pcp2_200907`;
CREATE TABLE `pcp2_200907` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200908`
--

DROP TABLE IF EXISTS `pcp2_200908`;
CREATE TABLE `pcp2_200908` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200909`
--

DROP TABLE IF EXISTS `pcp2_200909`;
CREATE TABLE `pcp2_200909` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200910`
--

DROP TABLE IF EXISTS `pcp2_200910`;
CREATE TABLE `pcp2_200910` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200911`
--

DROP TABLE IF EXISTS `pcp2_200911`;
CREATE TABLE `pcp2_200911` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_200912`
--

DROP TABLE IF EXISTS `pcp2_200912`;
CREATE TABLE `pcp2_200912` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_201001`
--

DROP TABLE IF EXISTS `pcp2_201001`;
CREATE TABLE `pcp2_201001` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_201002`
--

DROP TABLE IF EXISTS `pcp2_201002`;
CREATE TABLE `pcp2_201002` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp2_201003`
--

DROP TABLE IF EXISTS `pcp2_201003`;
CREATE TABLE `pcp2_201003` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `ra` int(11) default NULL,
  `price2` int(11) default NULL,
  `rp2` float default NULL,
  `ra2` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3`
--

DROP TABLE IF EXISTS `pcp3`;
CREATE TABLE `pcp3` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200811`
--

DROP TABLE IF EXISTS `pcp3_200811`;
CREATE TABLE `pcp3_200811` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200812`
--

DROP TABLE IF EXISTS `pcp3_200812`;
CREATE TABLE `pcp3_200812` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200902`
--

DROP TABLE IF EXISTS `pcp3_200902`;
CREATE TABLE `pcp3_200902` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200903`
--

DROP TABLE IF EXISTS `pcp3_200903`;
CREATE TABLE `pcp3_200903` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200904`
--

DROP TABLE IF EXISTS `pcp3_200904`;
CREATE TABLE `pcp3_200904` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200905`
--

DROP TABLE IF EXISTS `pcp3_200905`;
CREATE TABLE `pcp3_200905` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200906`
--

DROP TABLE IF EXISTS `pcp3_200906`;
CREATE TABLE `pcp3_200906` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200907`
--

DROP TABLE IF EXISTS `pcp3_200907`;
CREATE TABLE `pcp3_200907` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200908`
--

DROP TABLE IF EXISTS `pcp3_200908`;
CREATE TABLE `pcp3_200908` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200909`
--

DROP TABLE IF EXISTS `pcp3_200909`;
CREATE TABLE `pcp3_200909` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200910`
--

DROP TABLE IF EXISTS `pcp3_200910`;
CREATE TABLE `pcp3_200910` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200911`
--

DROP TABLE IF EXISTS `pcp3_200911`;
CREATE TABLE `pcp3_200911` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_200912`
--

DROP TABLE IF EXISTS `pcp3_200912`;
CREATE TABLE `pcp3_200912` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_201001`
--

DROP TABLE IF EXISTS `pcp3_201001`;
CREATE TABLE `pcp3_201001` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_201002`
--

DROP TABLE IF EXISTS `pcp3_201002`;
CREATE TABLE `pcp3_201002` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcp3_201003`
--

DROP TABLE IF EXISTS `pcp3_201003`;
CREATE TABLE `pcp3_201003` (
  `date` char(8) default NULL,
  `t0001` float default NULL,
  `t9999` float default NULL,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`date`,`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pcpprice`
--

DROP TABLE IF EXISTS `pcpprice`;
CREATE TABLE `pcpprice` (
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `date` char(8) default NULL,
  `price` int(11) default NULL,
  `rp` float default NULL,
  `buy` float default NULL,
  `sell` float default NULL,
  `diff` float default NULL,
  `ra` int(11) default NULL,
  KEY `ipcp` (`cp_month`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `picmap`
--

DROP TABLE IF EXISTS `picmap`;
CREATE TABLE `picmap` (
  `type` char(4) default NULL,
  `name` char(60) default NULL,
  KEY `ictype` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pidxdifrp_offical`
--

DROP TABLE IF EXISTS `pidxdifrp_offical`;
CREATE TABLE `pidxdifrp_offical` (
  `date` char(8) default NULL,
  `time` int(8) default NULL,
  `BuyCnt` int(11) default NULL,
  `BuyAmt` int(11) default NULL,
  `SellCnt` int(11) default NULL,
  `SellAmt` int(11) default NULL,
  `RpCnt` int(11) default NULL,
  `RpAmt` int(11) default NULL,
  `RpMoney` int(11) default NULL,
  KEY `ipIdxRp_offical` (`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pidxinfo3_of`
--

DROP TABLE IF EXISTS `pidxinfo3_of`;
CREATE TABLE `pidxinfo3_of` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Bamount` bigint(20) default NULL,
  `BViscosity` float default NULL,
  `Samount` bigint(20) default NULL,
  `SViscosity` float default NULL,
  `BamountC` bigint(20) default NULL,
  `BViscosityC` float default NULL,
  `SamountC` bigint(20) default NULL,
  `SViscosityC` float default NULL,
  `BamountCv` bigint(20) default NULL,
  `BViscosityCv` float default NULL,
  `SamountCv` bigint(20) default NULL,
  `SViscosityCv` float default NULL,
  KEY `Idxinfo3` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pidxrp_offical`
--

DROP TABLE IF EXISTS `pidxrp_offical`;
CREATE TABLE `pidxrp_offical` (
  `date` char(8) default NULL,
  `time` int(8) default NULL,
  `BuyCnt` int(11) default NULL,
  `BuyAmt` int(11) default NULL,
  `SellCnt` int(11) default NULL,
  `SellAmt` int(11) default NULL,
  `RpCnt` int(11) default NULL,
  `RpAmt` int(11) default NULL,
  `RpMoney` int(11) default NULL,
  KEY `ipIdxRp_offical` (`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pindexs_offical`
--

DROP TABLE IF EXISTS `pindexs_offical`;
CREATE TABLE `pindexs_offical` (
  `date` char(8) default NULL,
  `time` int(8) default NULL,
  `Idx_A` float default NULL,
  `Idx_B` float default NULL,
  `Idx_C` float default NULL,
  `Idx_D` float default NULL,
  `Idx_E` float default NULL,
  `Idx_F` float default NULL,
  `Idx_G` float default NULL,
  `Idx_H` float default NULL,
  `Idx_I` float default NULL,
  `Idx_J` float default NULL,
  `Idx_K` float default NULL,
  `Idx_L` float default NULL,
  `Idx_M` float default NULL,
  `Idx_N` float default NULL,
  `Idx_O` float default NULL,
  `Idx_P` float default NULL,
  `Idx_Q` float default NULL,
  `Idx_R` float default NULL,
  `Idx_S` float default NULL,
  `Idx_T` float default NULL,
  `Idx_U` float default NULL,
  `Idx_V` float default NULL,
  `Idx_W` float default NULL,
  `Idx_X` float default NULL,
  `Idx_Y` float default NULL,
  `Idx_Z` float default NULL,
  `Idx_AA` float default NULL,
  `Idx_BB` float default NULL,
  `Idx_CC` float default NULL,
  `Idx_DD` float default NULL,
  `Idx_EE` float default NULL,
  `Idx_FF` float default NULL,
  `Idx_GG` float default NULL,
  `Idx_HH` float default NULL,
  KEY `ipIndexs_offical` (`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pinoutsum`
--

DROP TABLE IF EXISTS `pinoutsum`;
CREATE TABLE `pinoutsum` (
  `date` char(8) default NULL,
  `type` char(2) default NULL,
  `inAmount` float default NULL,
  `outAmount` float default NULL,
  `totalAmount` float default NULL,
  KEY `ipinoutdate` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pinoutsum2`
--

DROP TABLE IF EXISTS `pinoutsum2`;
CREATE TABLE `pinoutsum2` (
  `date` char(8) default NULL,
  `type` char(2) default NULL,
  `in` float default NULL,
  `out` float default NULL,
  `total` float default NULL,
  KEY `ipinoutdate` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pinoutsum_official`
--

DROP TABLE IF EXISTS `pinoutsum_official`;
CREATE TABLE `pinoutsum_official` (
  `date` char(8) default NULL,
  `type` char(2) default NULL,
  `inAmount` float default NULL,
  `outAmount` float default NULL,
  `totalAmount` float default NULL,
  KEY `ipinoutdate` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `plast`
--

DROP TABLE IF EXISTS `plast`;
CREATE TABLE `plast` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` char(10) default NULL,
  `psell` char(10) default NULL,
  `rp` char(10) default NULL,
  `diff` char(10) default NULL,
  `ra` char(10) default NULL,
  `total` char(10) default NULL,
  KEY `ipsnumlast` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `plast2`
--

DROP TABLE IF EXISTS `plast2`;
CREATE TABLE `plast2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` char(8) default NULL,
  `pbuy` float default NULL,
  `psell` float default NULL,
  `rp` float default NULL,
  `diff` float default NULL,
  `ra` bigint(20) default NULL,
  `total` bigint(20) default NULL,
  `pclose` float default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  KEY `ipsnumlast2` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pldayk_ofcl`
--

DROP TABLE IF EXISTS `pldayk_ofcl`;
CREATE TABLE `pldayk_ofcl` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `aIdx` float default NULL,
  `eIdx` float default NULL,
  `fIdx` float default NULL,
  `BuyCnt` int(11) default NULL,
  `BuyAmt` int(11) default NULL,
  `SellCnt` int(11) default NULL,
  `SellAmt` int(11) default NULL,
  `RpCnt` int(11) default NULL,
  `RpAmt` int(11) default NULL,
  `RpMoney` int(11) default NULL,
  KEY `ipldaykofcl` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pldaykv`
--

DROP TABLE IF EXISTS `pldaykv`;
CREATE TABLE `pldaykv` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `ipldaykV` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamount2`
--

DROP TABLE IF EXISTS `pmamount2`;
CREATE TABLE `pmamount2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum2` (`snum`,`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pmamount3`;
CREATE TABLE `pmamount3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum2` (`snum`,`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamount2009`
--

DROP TABLE IF EXISTS `pmamount2009`;
CREATE TABLE `pmamount2009` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum` (`snum`,`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountinfo`
--

DROP TABLE IF EXISTS `pmamountinfo`;
CREATE TABLE `pmamountinfo` (
  `mnum` char(4) default NULL,
  `date` char(8) default NULL,
  `inAmt` float default NULL,
  `outAmt` float default NULL,
  `totalAmt` float default NULL,
  KEY `ipmamountinfo` (`mnum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountinfo_week`
--

DROP TABLE IF EXISTS `pmamountinfo_week`;
CREATE TABLE `pmamountinfo_week` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `mnum` char(4) default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `Avg` float default NULL,
  `Rate` float default NULL,
  KEY `ipsnuminfo` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountinfo_week2`
--

DROP TABLE IF EXISTS `pmamountinfo_week2`;
CREATE TABLE `pmamountinfo_week2` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `mnum` char(4) default NULL,
  `BAmount` bigint(20) default NULL,
  `BMoneyAmt` float default NULL,
  `BAvg` float default NULL,
  `BRate` float default NULL,
  `SAmount` bigint(20) default NULL,
  `SMoneyAmt` float default NULL,
  `SAvg` float default NULL,
  `SRate` float default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `Avg` float default NULL,
  `Rate` float default NULL,
  KEY `ipsnuminfo22` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  KEY `ipmsnum` (`snum`,`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountmm`
--

DROP TABLE IF EXISTS `pmamountmm`;
CREATE TABLE `pmamountmm` (
  `mnum` char(4) default NULL,
  `date` char(8) default NULL,
  `inAmount` float default NULL,
  `outAmount` float default NULL,
  `sum` float default NULL,
  KEY `ipmsnum` (`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountw`
--

DROP TABLE IF EXISTS `pmamountw`;
CREATE TABLE `pmamountw` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `inAmount` int(11) default NULL,
  `outAmount` int(11) default NULL,
  `sum` int(11) default NULL,
  KEY `ipmsnum` (`snum`,`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmamountwm`
--

DROP TABLE IF EXISTS `pmamountwm`;
CREATE TABLE `pmamountwm` (
  `mnum` char(4) default NULL,
  `date` char(8) default NULL,
  `inAmount` float default NULL,
  `outAmount` float default NULL,
  `sum` float default NULL,
  KEY `ipmsnum` (`date`,`mnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmmamount`
--

DROP TABLE IF EXISTS `pmmamount`;
CREATE TABLE `pmmamount` (
  `date` char(8) default NULL,
  `mnum` char(4) default NULL,
  `money` float default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmname`
--

DROP TABLE IF EXISTS `pmname`;
CREATE TABLE `pmname` (
  `mnum` char(4) default NULL,
  `mname` char(20) default NULL,
  `type` char(1) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmname2`
--

DROP TABLE IF EXISTS `pmname2`;
CREATE TABLE `pmname2` (
  `mnum` char(4) default NULL,
  `type` char(1) default NULL,
  `mname` char(40) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmrate`
--

DROP TABLE IF EXISTS `pmrate`;
CREATE TABLE `pmrate` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(5) default NULL,
  `SumB` int(11) default NULL,
  `mnumS` char(5) default NULL,
  `SumS` int(11) default NULL,
  `ratio` float default NULL,
  KEY `ipmrate` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmrate5`
--

DROP TABLE IF EXISTS `pmrate5`;
CREATE TABLE `pmrate5` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(5) default NULL,
  `SumB` int(11) default NULL,
  `mnumS` char(5) default NULL,
  `SumS` int(11) default NULL,
  `ratio` float default NULL,
  KEY `ipmrate` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pmratem`
--

DROP TABLE IF EXISTS `pmratem`;
CREATE TABLE `pmratem` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mnumB` char(4) default NULL,
  `sumB` int(11) default NULL,
  `mnumS` char(4) default NULL,
  `sumS` int(11) default NULL,
  `rate` float default NULL,
  KEY `ipmsnum` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `psnumindis`
--

DROP TABLE IF EXISTS `psnumindis`;
CREATE TABLE `psnumindis` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Frate` float default NULL,
  `Brate` float default NULL,
  `Arate` float default NULL,
  `Orate` float default NULL,
  `ra` bigint(20) default NULL,
  KEY `ipsnumindis` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `psnuminoutmoney`
--

DROP TABLE IF EXISTS `psnuminoutmoney`;
CREATE TABLE `psnuminoutmoney` (
  `date` char(8) default NULL,
  `type` char(1) default NULL,
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `inM` float default NULL,
  `outM` float default NULL,
  `totalM` float default NULL,
  KEY `ipinoutmdate` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `psnuminoutsum`
--

DROP TABLE IF EXISTS `psnuminoutsum`;
CREATE TABLE `psnuminoutsum` (
  `date` char(8) default NULL,
  `type` char(1) default NULL,
  `snum` char(5) default NULL,
  `sname` char(10) default NULL,
  `in` bigint(20) default NULL,
  `out` bigint(20) default NULL,
  `total` bigint(20) default NULL,
  KEY `ipinoutdate` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `psnumoutdis`
--

DROP TABLE IF EXISTS `psnumoutdis`;
CREATE TABLE `psnumoutdis` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `Frate` float default NULL,
  `Brate` float default NULL,
  `Arate` float default NULL,
  `Orate` float default NULL,
  `ra` bigint(20) default NULL,
  KEY `ipsnumoutdis` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptest2minfo1`
--

DROP TABLE IF EXISTS `ptest2minfo1`;
CREATE TABLE `ptest2minfo1` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `mname` char(15) default NULL,
  `total` bigint(20) default NULL,
  `totalM` float default NULL,
  `avg` float default NULL,
  `type` char(1) default NULL,
  KEY `ipmsnum2` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptest2minfo2`
--

DROP TABLE IF EXISTS `ptest2minfo2`;
CREATE TABLE `ptest2minfo2` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `total` bigint(20) default NULL,
  `totalM` float default NULL,
  `avg` float default NULL,
  `type` char(1) default NULL,
  KEY `ipmsnum32` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptest2minfo3`
--

DROP TABLE IF EXISTS `ptest2minfo3`;
CREATE TABLE `ptest2minfo3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `total` bigint(20) default NULL,
  `bavg` float default NULL,
  `savg` float default NULL,
  KEY `ipmsnum4` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestinfo1`
--

DROP TABLE IF EXISTS `ptestinfo1`;
CREATE TABLE `ptestinfo1` (
  `snum` char(5) default NULL,
  `open` float default NULL,
  `close` float default NULL,
  `rclose` float default NULL,
  `rate` float default NULL,
  KEY `iptestinfo` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestinfo3`
--

DROP TABLE IF EXISTS `ptestinfo3`;
CREATE TABLE `ptestinfo3` (
  `snum` char(5) default NULL,
  `date` char(8) default NULL,
  `open` float default NULL,
  `close` float default NULL,
  `rate` float default NULL,
  `Avg` float default NULL,
  `Open_upAvg` float default NULL,
  `Open_downAvg` float default NULL,
  `Close_upAvg` float default NULL,
  `Close_downAvg` float default NULL,
  `Open_upAmt` bigint(20) default NULL,
  `Open_downAmt` bigint(20) default NULL,
  `Close_upAmt` bigint(20) default NULL,
  `Close_downAmt` bigint(20) default NULL,
  `hand_rate` float default NULL,
  KEY `ipsnuminfo` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestminfo1`
--

DROP TABLE IF EXISTS `ptestminfo1`;
CREATE TABLE `ptestminfo1` (
  `snum` char(5) default NULL,
  `mname` char(15) default NULL,
  `total` bigint(20) default NULL,
  `totalM` float default NULL,
  `avg` float default NULL,
  `type` char(1) default NULL,
  KEY `ipmsnum2` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestminfo2`
--

DROP TABLE IF EXISTS `ptestminfo2`;
CREATE TABLE `ptestminfo2` (
  `snum` char(5) default NULL,
  `mname` char(15) default NULL,
  `total` bigint(20) default NULL,
  `totalM` float default NULL,
  `avg` float default NULL,
  `type` char(1) default NULL,
  KEY `ipmsnum3` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestminfo3`
--

DROP TABLE IF EXISTS `ptestminfo3`;
CREATE TABLE `ptestminfo3` (
  `snum` char(5) default NULL,
  `total` bigint(20) default NULL,
  `bavg` float default NULL,
  `savg` float default NULL,
  KEY `ipmsnum4` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestminfo32`
--

DROP TABLE IF EXISTS `ptestminfo32`;
CREATE TABLE `ptestminfo32` (
  `snum` char(5) default NULL,
  `total` bigint(20) default NULL,
  `bavg` float default NULL,
  `savg` float default NULL,
  KEY `ipmsnum4` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ptestminfo33`
--

DROP TABLE IF EXISTS `ptestminfo33`;
CREATE TABLE `ptestminfo33` (
  `snum` char(5) default NULL,
  `total` bigint(20) default NULL,
  `bavg` float default NULL,
  `savg` float default NULL,
  KEY `ipmsnum4` (`snum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-04-20 16:24:44
