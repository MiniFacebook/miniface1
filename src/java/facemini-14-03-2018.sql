-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2018 at 06:54 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `facemini`
--

-- --------------------------------------------------------

--
-- Table structure for table `aime`
--

CREATE TABLE `aime` (
  `ID` bigint(20) NOT NULL,
  `DATEAIME` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `COMMENTAIRE_ID` bigint(20) DEFAULT NULL,
  `LIKER_LOGIN` varchar(255) DEFAULT NULL,
  `PUBLICATION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `blocage`
--

CREATE TABLE `blocage` (
  `ID` bigint(20) NOT NULL,
  `DATEBLOCAGE` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `PRETEXTE` varchar(255) DEFAULT NULL,
  `BLOQUE_LOGIN` varchar(255) DEFAULT NULL,
  `BLOQUEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `ID` bigint(20) NOT NULL,
  `CODE` bigint(20) DEFAULT NULL,
  `DATECOMMENTAIRE` date DEFAULT NULL,
  `DATEMODIFICATION` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `RETURNBACK` tinyint(1) DEFAULT '0',
  `TEXTE` varchar(255) DEFAULT NULL,
  `PUBLICATION_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contenu`
--

CREATE TABLE `contenu` (
  `ID` bigint(20) NOT NULL,
  `CHEMIN` varchar(255) DEFAULT NULL,
  `EXTENSION` varchar(255) DEFAULT NULL,
  `COMMENTAIRE_ID` bigint(20) DEFAULT NULL,
  `MESSAGE_ID` bigint(20) DEFAULT NULL,
  `PUBLICATION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `droitacces`
--

CREATE TABLE `droitacces` (
  `ID` bigint(20) NOT NULL,
  `TYPE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `emploi`
--

CREATE TABLE `emploi` (
  `ID` bigint(20) NOT NULL,
  `ENTREPRISE` varchar(255) DEFAULT NULL,
  `LIEU` varchar(255) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `emploiitem`
--

CREATE TABLE `emploiitem` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `EMPLOI_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `etablissement`
--

CREATE TABLE `etablissement` (
  `ID` bigint(20) NOT NULL,
  `DATEETABLISSEMENT` date DEFAULT NULL,
  `FORMATION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `NIVEAU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `etablissementitem`
--

CREATE TABLE `etablissementitem` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `ETABLISSEMENT_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `groupe`
--

CREATE TABLE `groupe` (
  `ID` bigint(20) NOT NULL,
  `CODE` bigint(20) DEFAULT NULL,
  `DATECREATION` date DEFAULT NULL,
  `DATEMODIFICATION` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `ETAT` bigint(20) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `RETURNBACK` tinyint(1) DEFAULT '0',
  `TYPE` varchar(255) DEFAULT NULL,
  `ADMIN_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `groupeadmin`
--

CREATE TABLE `groupeadmin` (
  `ID` bigint(20) NOT NULL,
  `DATEADMIN` date DEFAULT NULL,
  `ADMIN_LOGIN` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `groupeitem`
--

CREATE TABLE `groupeitem` (
  `ID` bigint(20) NOT NULL,
  `DATEINTEGRATION` date DEFAULT NULL,
  `DEMANDEUR_LOGIN` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

CREATE TABLE `invitation` (
  `ID` bigint(20) NOT NULL,
  `BLOQUER` tinyint(1) DEFAULT '0',
  `AMISPROCHE` tinyint(1) DEFAULT '0',
  `DATEACCEPTATION` date DEFAULT NULL,
  `DATEENVOI` date DEFAULT NULL,
  `REJETER` tinyint(1) DEFAULT '0',
  `RETIRER` tinyint(1) DEFAULT '0',
  `TYPE` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL,
  `RECEPTEUR_LOGIN` varchar(255) DEFAULT NULL,
  `EMETTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lieu`
--

CREATE TABLE `lieu` (
  `ID` bigint(20) NOT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `VILLE` varchar(255) DEFAULT NULL,
  `VILLEORIGINE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL,
  `DATEENVOI` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `RETURNBACK` tinyint(1) DEFAULT '0',
  `TEXTE` varchar(255) DEFAULT NULL,
  `EMETTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `messageitem`
--

CREATE TABLE `messageitem` (
  `ID` bigint(20) NOT NULL,
  `DATELECTURE` date DEFAULT NULL,
  `MESSAGE_ID` bigint(20) DEFAULT NULL,
  `RECEPTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `ID` bigint(20) NOT NULL,
  `DATENOTIFICATION` date DEFAULT NULL,
  `IDELEMENT` varchar(255) DEFAULT NULL,
  `TEXTE` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `USERACTION_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notificationitem`
--

CREATE TABLE `notificationitem` (
  `ID` bigint(20) NOT NULL,
  `VU` tinyint(1) DEFAULT '0',
  `CONCERNE_LOGIN` varchar(255) DEFAULT NULL,
  `NOTIFICATION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

CREATE TABLE `photo` (
  `ID` bigint(20) NOT NULL,
  `BACKGROUND` tinyint(1) DEFAULT '0',
  `CHEMIN` varchar(255) DEFAULT NULL,
  `PROFIL` tinyint(1) DEFAULT '0',
  `TYPE` varchar(255) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `publication`
--

CREATE TABLE `publication` (
  `ID` bigint(20) NOT NULL,
  `CODE` bigint(20) DEFAULT NULL,
  `DATEMODIFICATION` date DEFAULT NULL,
  `DATEPUBLICATION` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `DROIT` int(11) DEFAULT NULL,
  `RETURNBACK` tinyint(1) DEFAULT '0',
  `TEXTE` varchar(255) DEFAULT NULL,
  `EMETTEUR_LOGIN` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL,
  `RECEPTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Table structure for table `signalpublication`
--

CREATE TABLE `signalpublication` (
  `ID` bigint(20) NOT NULL,
  `CAUSE` varchar(255) DEFAULT NULL,
  `DATESIGNAL` date DEFAULT NULL,
  `DATESUPRESSION` date DEFAULT NULL,
  `PUBLICATIONSIGNALE_ID` bigint(20) DEFAULT NULL,
  `USERACTION_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `signaluser`
--

CREATE TABLE `signaluser` (
  `ID` bigint(20) NOT NULL,
  `CAUSE` varchar(255) DEFAULT NULL,
  `DATESIGNAL` date DEFAULT NULL,
  `DATESUPRESSION` date DEFAULT NULL,
  `USERACTION_LOGIN` varchar(255) DEFAULT NULL,
  `USERSIGNALE_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `LOGIN` varchar(255) NOT NULL,
  `ACTIVE` tinyint(1) DEFAULT '0',
  `ADMINATRATEUR` tinyint(1) DEFAULT '0',
  `CODE` bigint(20) DEFAULT NULL,
  `DATEMODIFICATION` date DEFAULT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `DROIT` tinyint(1) DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `SEXE` varchar(255) DEFAULT NULL,
  `STOP` tinyint(1) DEFAULT '0',
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `TIMER` double DEFAULT NULL,
  `LIEU_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE `videos` (
  `ID` bigint(20) NOT NULL,
  `EXTENSION` varchar(255) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aime`
--
ALTER TABLE `aime`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_AIME_COMMENTAIRE_ID` (`COMMENTAIRE_ID`),
  ADD KEY `FK_AIME_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_AIME_LIKER_LOGIN` (`LIKER_LOGIN`);

--
-- Indexes for table `blocage`
--
ALTER TABLE `blocage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_BLOCAGE_BLOQUEUR_LOGIN` (`BLOQUEUR_LOGIN`),
  ADD KEY `FK_BLOCAGE_BLOQUE_LOGIN` (`BLOQUE_LOGIN`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_COMMENTAIRE_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_COMMENTAIRE_USER_LOGIN` (`USER_LOGIN`);

--
-- Indexes for table `contenu`
--
ALTER TABLE `contenu`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONTENU_MESSAGE_ID` (`MESSAGE_ID`),
  ADD KEY `FK_CONTENU_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_CONTENU_COMMENTAIRE_ID` (`COMMENTAIRE_ID`);

--
-- Indexes for table `droitacces`
--
ALTER TABLE `droitacces`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `emploi`
--
ALTER TABLE `emploi`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `emploiitem`
--
ALTER TABLE `emploiitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EMPLOIITEM_EMPLOI_ID` (`EMPLOI_ID`),
  ADD KEY `FK_EMPLOIITEM_USER_LOGIN` (`USER_LOGIN`);

--
-- Indexes for table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `etablissementitem`
--
ALTER TABLE `etablissementitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ETABLISSEMENTITEM_ETABLISSEMENT_ID` (`ETABLISSEMENT_ID`),
  ADD KEY `FK_ETABLISSEMENTITEM_USER_LOGIN` (`USER_LOGIN`);

--
-- Indexes for table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPE_ADMIN_LOGIN` (`ADMIN_LOGIN`);

--
-- Indexes for table `groupeadmin`
--
ALTER TABLE `groupeadmin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPEADMIN_ADMIN_LOGIN` (`ADMIN_LOGIN`),
  ADD KEY `FK_GROUPEADMIN_GROUPE_ID` (`GROUPE_ID`);

--
-- Indexes for table `groupeitem`
--
ALTER TABLE `groupeitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPEITEM_DEMANDEUR_LOGIN` (`DEMANDEUR_LOGIN`),
  ADD KEY `FK_GROUPEITEM_GROUPE_ID` (`GROUPE_ID`);

--
-- Indexes for table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_INVITATION_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`),
  ADD KEY `FK_INVITATION_GROUPE_ID` (`GROUPE_ID`),
  ADD KEY `FK_INVITATION_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`);

--
-- Indexes for table `lieu`
--
ALTER TABLE `lieu`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MESSAGE_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`);

--
-- Indexes for table `messageitem`
--
ALTER TABLE `messageitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MESSAGEITEM_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`),
  ADD KEY `FK_MESSAGEITEM_MESSAGE_ID` (`MESSAGE_ID`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTIFICATION_USERACTION_LOGIN` (`USERACTION_LOGIN`);

--
-- Indexes for table `notificationitem`
--
ALTER TABLE `notificationitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTIFICATIONITEM_NOTIFICATION_ID` (`NOTIFICATION_ID`),
  ADD KEY `FK_NOTIFICATIONITEM_CONCERNE_LOGIN` (`CONCERNE_LOGIN`);

--
-- Indexes for table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PHOTO_USER_LOGIN` (`USER_LOGIN`);

--
-- Indexes for table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PUBLICATION_GROUPE_ID` (`GROUPE_ID`),
  ADD KEY `FK_PUBLICATION_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`),
  ADD KEY `FK_PUBLICATION_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indexes for table `signalpublication`
--
ALTER TABLE `signalpublication`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SIGNALPUBLICATION_USERACTION_LOGIN` (`USERACTION_LOGIN`),
  ADD KEY `FK_SIGNALPUBLICATION_PUBLICATIONSIGNALE_ID` (`PUBLICATIONSIGNALE_ID`);

--
-- Indexes for table `signaluser`
--
ALTER TABLE `signaluser`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SIGNALUSER_USERSIGNALE_LOGIN` (`USERSIGNALE_LOGIN`),
  ADD KEY `FK_SIGNALUSER_USERACTION_LOGIN` (`USERACTION_LOGIN`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`LOGIN`),
  ADD KEY `FK_USER_LIEU_ID` (`LIEU_ID`);

--
-- Indexes for table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VIDEOS_USER_LOGIN` (`USER_LOGIN`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aime`
--
ALTER TABLE `aime`
  ADD CONSTRAINT `FK_AIME_COMMENTAIRE_ID` FOREIGN KEY (`COMMENTAIRE_ID`) REFERENCES `commentaire` (`ID`),
  ADD CONSTRAINT `FK_AIME_LIKER_LOGIN` FOREIGN KEY (`LIKER_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_AIME_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`);

--
-- Constraints for table `blocage`
--
ALTER TABLE `blocage`
  ADD CONSTRAINT `FK_BLOCAGE_BLOQUEUR_LOGIN` FOREIGN KEY (`BLOQUEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_BLOCAGE_BLOQUE_LOGIN` FOREIGN KEY (`BLOQUE_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_COMMENTAIRE_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`),
  ADD CONSTRAINT `FK_COMMENTAIRE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `contenu`
--
ALTER TABLE `contenu`
  ADD CONSTRAINT `FK_CONTENU_COMMENTAIRE_ID` FOREIGN KEY (`COMMENTAIRE_ID`) REFERENCES `commentaire` (`ID`),
  ADD CONSTRAINT `FK_CONTENU_MESSAGE_ID` FOREIGN KEY (`MESSAGE_ID`) REFERENCES `message` (`ID`),
  ADD CONSTRAINT `FK_CONTENU_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`);

--
-- Constraints for table `emploiitem`
--
ALTER TABLE `emploiitem`
  ADD CONSTRAINT `FK_EMPLOIITEM_EMPLOI_ID` FOREIGN KEY (`EMPLOI_ID`) REFERENCES `emploi` (`ID`),
  ADD CONSTRAINT `FK_EMPLOIITEM_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `etablissementitem`
--
ALTER TABLE `etablissementitem`
  ADD CONSTRAINT `FK_ETABLISSEMENTITEM_ETABLISSEMENT_ID` FOREIGN KEY (`ETABLISSEMENT_ID`) REFERENCES `etablissement` (`ID`),
  ADD CONSTRAINT `FK_ETABLISSEMENTITEM_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `FK_GROUPE_ADMIN_LOGIN` FOREIGN KEY (`ADMIN_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `groupeadmin`
--
ALTER TABLE `groupeadmin`
  ADD CONSTRAINT `FK_GROUPEADMIN_ADMIN_LOGIN` FOREIGN KEY (`ADMIN_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_GROUPEADMIN_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`);

--
-- Constraints for table `groupeitem`
--
ALTER TABLE `groupeitem`
  ADD CONSTRAINT `FK_GROUPEITEM_DEMANDEUR_LOGIN` FOREIGN KEY (`DEMANDEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_GROUPEITEM_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`);

--
-- Constraints for table `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `FK_INVITATION_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_INVITATION_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`),
  ADD CONSTRAINT `FK_INVITATION_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_MESSAGE_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `messageitem`
--
ALTER TABLE `messageitem`
  ADD CONSTRAINT `FK_MESSAGEITEM_MESSAGE_ID` FOREIGN KEY (`MESSAGE_ID`) REFERENCES `message` (`ID`),
  ADD CONSTRAINT `FK_MESSAGEITEM_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_NOTIFICATION_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `notificationitem`
--
ALTER TABLE `notificationitem`
  ADD CONSTRAINT `FK_NOTIFICATIONITEM_CONCERNE_LOGIN` FOREIGN KEY (`CONCERNE_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_NOTIFICATIONITEM_NOTIFICATION_ID` FOREIGN KEY (`NOTIFICATION_ID`) REFERENCES `notification` (`ID`);

--
-- Constraints for table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `FK_PHOTO_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `FK_PUBLICATION_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_PUBLICATION_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`),
  ADD CONSTRAINT `FK_PUBLICATION_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `signalpublication`
--
ALTER TABLE `signalpublication`
  ADD CONSTRAINT `FK_SIGNALPUBLICATION_PUBLICATIONSIGNALE_ID` FOREIGN KEY (`PUBLICATIONSIGNALE_ID`) REFERENCES `publication` (`ID`),
  ADD CONSTRAINT `FK_SIGNALPUBLICATION_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `signaluser`
--
ALTER TABLE `signaluser`
  ADD CONSTRAINT `FK_SIGNALUSER_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_SIGNALUSER_USERSIGNALE_LOGIN` FOREIGN KEY (`USERSIGNALE_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_LIEU_ID` FOREIGN KEY (`LIEU_ID`) REFERENCES `lieu` (`ID`);

--
-- Constraints for table `videos`
--
ALTER TABLE `videos`
  ADD CONSTRAINT `FK_VIDEOS_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
