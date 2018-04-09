-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 09 avr. 2018 à 20:33
-- Version du serveur :  10.1.26-MariaDB
-- Version de PHP :  7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `facemini`
--

-- --------------------------------------------------------

--
-- Structure de la table `aime`
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
-- Structure de la table `blocage`
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
-- Structure de la table `commentaire`
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
-- Structure de la table `contenu`
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
-- Structure de la table `droitacces`
--

CREATE TABLE `droitacces` (
  `ID` bigint(20) NOT NULL,
  `TYPE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `emploi`
--

CREATE TABLE `emploi` (
  `ID` bigint(20) NOT NULL,
  `ENTREPRISE` varchar(255) DEFAULT NULL,
  `LIEU` varchar(255) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `emploiitem`
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
-- Structure de la table `etablissement`
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
-- Structure de la table `etablissementitem`
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
-- Structure de la table `groupe`
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
-- Structure de la table `groupeadmin`
--

CREATE TABLE `groupeadmin` (
  `ID` bigint(20) NOT NULL,
  `DATEADMIN` date DEFAULT NULL,
  `ADMIN_LOGIN` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupeitem`
--

CREATE TABLE `groupeitem` (
  `ID` bigint(20) NOT NULL,
  `DATEINTEGRATION` date DEFAULT NULL,
  `DEMANDEUR_LOGIN` varchar(255) DEFAULT NULL,
  `GROUPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `invitation`
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
  `VU` tinyint(1) DEFAULT '0',
  `GROUPE_ID` bigint(20) DEFAULT NULL,
  `RECEPTEUR_LOGIN` varchar(255) DEFAULT NULL,
  `EMETTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `ID` bigint(20) NOT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `VILLE` varchar(255) DEFAULT NULL,
  `VILLEORIGINE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `message`
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
-- Structure de la table `messageitem`
--

CREATE TABLE `messageitem` (
  `ID` bigint(20) NOT NULL,
  `DATELECTURE` date DEFAULT NULL,
  `VU` tinyint(1) DEFAULT '0',
  `MESSAGE_ID` bigint(20) DEFAULT NULL,
  `RECEPTEUR_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
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
-- Structure de la table `notificationitem`
--

CREATE TABLE `notificationitem` (
  `ID` bigint(20) NOT NULL,
  `VU` tinyint(1) DEFAULT '0',
  `CONCERNE_LOGIN` varchar(255) DEFAULT NULL,
  `NOTIFICATION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photo`
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
-- Structure de la table `publication`
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
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Structure de la table `signalpublication`
--

CREATE TABLE `signalpublication` (
  `ID` bigint(20) NOT NULL,
  `CAUSE` varchar(255) DEFAULT NULL,
  `DATESIGNAL` date DEFAULT NULL,
  `DATESUPRESSION` date DEFAULT NULL,
  `VU` tinyint(1) DEFAULT '0',
  `PUBLICATIONSIGNALE_ID` bigint(20) DEFAULT NULL,
  `USERACTION_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `signaluser`
--

CREATE TABLE `signaluser` (
  `ID` bigint(20) NOT NULL,
  `CAUSE` varchar(255) DEFAULT NULL,
  `DATESIGNAL` date DEFAULT NULL,
  `DATESUPRESSION` date DEFAULT NULL,
  `VU` tinyint(1) DEFAULT '0',
  `USERACTION_LOGIN` varchar(255) DEFAULT NULL,
  `USERSIGNALE_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `LOGIN` varchar(255) NOT NULL,
  `ACTIVE` tinyint(1) DEFAULT '0',
  `ADMINATRATEUR` tinyint(1) DEFAULT '0',
  `CODE` bigint(20) DEFAULT NULL,
  `DATECREATION` date DEFAULT NULL,
  `DATEMODIFICATION` date DEFAULT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `DROIT` tinyint(1) DEFAULT '0',
  `INTRO` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `SEXE` varchar(255) DEFAULT NULL,
  `STOP` tinyint(1) DEFAULT '0',
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `TIMER` double DEFAULT NULL,
  `LIEU_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`LOGIN`, `ACTIVE`, `ADMINATRATEUR`, `CODE`, `DATECREATION`, `DATEMODIFICATION`, `DATENAISSANCE`, `DATESUPPRESSION`, `DROIT`, `INTRO`, `NOM`, `PASSWORD`, `PRENOM`, `SEXE`, `STOP`, `TELEPHONE`, `TIMER`, `LIEU_ID`) VALUES
('hajar', 0, 0, NULL, '2018-04-09', NULL, '1996-04-24', NULL, 0, NULL, 'hajar', 'sir', 'hajar', 'femme', 0, NULL, NULL, NULL),
('safai', 0, 0, NULL, '2018-04-09', NULL, NULL, NULL, 0, NULL, 'safia', 'sir', 'safia', NULL, 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `videos`
--

CREATE TABLE `videos` (
  `ID` bigint(20) NOT NULL,
  `EXTENSION` varchar(255) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `aime`
--
ALTER TABLE `aime`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_AIME_COMMENTAIRE_ID` (`COMMENTAIRE_ID`),
  ADD KEY `FK_AIME_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_AIME_LIKER_LOGIN` (`LIKER_LOGIN`);

--
-- Index pour la table `blocage`
--
ALTER TABLE `blocage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_BLOCAGE_BLOQUEUR_LOGIN` (`BLOQUEUR_LOGIN`),
  ADD KEY `FK_BLOCAGE_BLOQUE_LOGIN` (`BLOQUE_LOGIN`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_COMMENTAIRE_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_COMMENTAIRE_USER_LOGIN` (`USER_LOGIN`);

--
-- Index pour la table `contenu`
--
ALTER TABLE `contenu`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONTENU_MESSAGE_ID` (`MESSAGE_ID`),
  ADD KEY `FK_CONTENU_PUBLICATION_ID` (`PUBLICATION_ID`),
  ADD KEY `FK_CONTENU_COMMENTAIRE_ID` (`COMMENTAIRE_ID`);

--
-- Index pour la table `droitacces`
--
ALTER TABLE `droitacces`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `emploi`
--
ALTER TABLE `emploi`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `emploiitem`
--
ALTER TABLE `emploiitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EMPLOIITEM_EMPLOI_ID` (`EMPLOI_ID`),
  ADD KEY `FK_EMPLOIITEM_USER_LOGIN` (`USER_LOGIN`);

--
-- Index pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `etablissementitem`
--
ALTER TABLE `etablissementitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ETABLISSEMENTITEM_ETABLISSEMENT_ID` (`ETABLISSEMENT_ID`),
  ADD KEY `FK_ETABLISSEMENTITEM_USER_LOGIN` (`USER_LOGIN`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPE_ADMIN_LOGIN` (`ADMIN_LOGIN`);

--
-- Index pour la table `groupeadmin`
--
ALTER TABLE `groupeadmin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPEADMIN_ADMIN_LOGIN` (`ADMIN_LOGIN`),
  ADD KEY `FK_GROUPEADMIN_GROUPE_ID` (`GROUPE_ID`);

--
-- Index pour la table `groupeitem`
--
ALTER TABLE `groupeitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_GROUPEITEM_DEMANDEUR_LOGIN` (`DEMANDEUR_LOGIN`),
  ADD KEY `FK_GROUPEITEM_GROUPE_ID` (`GROUPE_ID`);

--
-- Index pour la table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_INVITATION_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`),
  ADD KEY `FK_INVITATION_GROUPE_ID` (`GROUPE_ID`),
  ADD KEY `FK_INVITATION_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`);

--
-- Index pour la table `lieu`
--
ALTER TABLE `lieu`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MESSAGE_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`);

--
-- Index pour la table `messageitem`
--
ALTER TABLE `messageitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MESSAGEITEM_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`),
  ADD KEY `FK_MESSAGEITEM_MESSAGE_ID` (`MESSAGE_ID`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTIFICATION_USERACTION_LOGIN` (`USERACTION_LOGIN`);

--
-- Index pour la table `notificationitem`
--
ALTER TABLE `notificationitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTIFICATIONITEM_NOTIFICATION_ID` (`NOTIFICATION_ID`),
  ADD KEY `FK_NOTIFICATIONITEM_CONCERNE_LOGIN` (`CONCERNE_LOGIN`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PHOTO_USER_LOGIN` (`USER_LOGIN`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PUBLICATION_GROUPE_ID` (`GROUPE_ID`),
  ADD KEY `FK_PUBLICATION_EMETTEUR_LOGIN` (`EMETTEUR_LOGIN`),
  ADD KEY `FK_PUBLICATION_RECEPTEUR_LOGIN` (`RECEPTEUR_LOGIN`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `signalpublication`
--
ALTER TABLE `signalpublication`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SIGNALPUBLICATION_USERACTION_LOGIN` (`USERACTION_LOGIN`),
  ADD KEY `FK_SIGNALPUBLICATION_PUBLICATIONSIGNALE_ID` (`PUBLICATIONSIGNALE_ID`);

--
-- Index pour la table `signaluser`
--
ALTER TABLE `signaluser`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SIGNALUSER_USERSIGNALE_LOGIN` (`USERSIGNALE_LOGIN`),
  ADD KEY `FK_SIGNALUSER_USERACTION_LOGIN` (`USERACTION_LOGIN`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`LOGIN`),
  ADD KEY `FK_USER_LIEU_ID` (`LIEU_ID`);

--
-- Index pour la table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VIDEOS_USER_LOGIN` (`USER_LOGIN`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `aime`
--
ALTER TABLE `aime`
  ADD CONSTRAINT `FK_AIME_COMMENTAIRE_ID` FOREIGN KEY (`COMMENTAIRE_ID`) REFERENCES `commentaire` (`ID`),
  ADD CONSTRAINT `FK_AIME_LIKER_LOGIN` FOREIGN KEY (`LIKER_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_AIME_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`);

--
-- Contraintes pour la table `blocage`
--
ALTER TABLE `blocage`
  ADD CONSTRAINT `FK_BLOCAGE_BLOQUEUR_LOGIN` FOREIGN KEY (`BLOQUEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_BLOCAGE_BLOQUE_LOGIN` FOREIGN KEY (`BLOQUE_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_COMMENTAIRE_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`),
  ADD CONSTRAINT `FK_COMMENTAIRE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `contenu`
--
ALTER TABLE `contenu`
  ADD CONSTRAINT `FK_CONTENU_COMMENTAIRE_ID` FOREIGN KEY (`COMMENTAIRE_ID`) REFERENCES `commentaire` (`ID`),
  ADD CONSTRAINT `FK_CONTENU_MESSAGE_ID` FOREIGN KEY (`MESSAGE_ID`) REFERENCES `message` (`ID`),
  ADD CONSTRAINT `FK_CONTENU_PUBLICATION_ID` FOREIGN KEY (`PUBLICATION_ID`) REFERENCES `publication` (`ID`);

--
-- Contraintes pour la table `emploiitem`
--
ALTER TABLE `emploiitem`
  ADD CONSTRAINT `FK_EMPLOIITEM_EMPLOI_ID` FOREIGN KEY (`EMPLOI_ID`) REFERENCES `emploi` (`ID`),
  ADD CONSTRAINT `FK_EMPLOIITEM_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `etablissementitem`
--
ALTER TABLE `etablissementitem`
  ADD CONSTRAINT `FK_ETABLISSEMENTITEM_ETABLISSEMENT_ID` FOREIGN KEY (`ETABLISSEMENT_ID`) REFERENCES `etablissement` (`ID`),
  ADD CONSTRAINT `FK_ETABLISSEMENTITEM_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `FK_GROUPE_ADMIN_LOGIN` FOREIGN KEY (`ADMIN_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `groupeadmin`
--
ALTER TABLE `groupeadmin`
  ADD CONSTRAINT `FK_GROUPEADMIN_ADMIN_LOGIN` FOREIGN KEY (`ADMIN_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_GROUPEADMIN_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`);

--
-- Contraintes pour la table `groupeitem`
--
ALTER TABLE `groupeitem`
  ADD CONSTRAINT `FK_GROUPEITEM_DEMANDEUR_LOGIN` FOREIGN KEY (`DEMANDEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_GROUPEITEM_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`);

--
-- Contraintes pour la table `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `FK_INVITATION_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_INVITATION_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`),
  ADD CONSTRAINT `FK_INVITATION_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_MESSAGE_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `messageitem`
--
ALTER TABLE `messageitem`
  ADD CONSTRAINT `FK_MESSAGEITEM_MESSAGE_ID` FOREIGN KEY (`MESSAGE_ID`) REFERENCES `message` (`ID`),
  ADD CONSTRAINT `FK_MESSAGEITEM_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_NOTIFICATION_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `notificationitem`
--
ALTER TABLE `notificationitem`
  ADD CONSTRAINT `FK_NOTIFICATIONITEM_CONCERNE_LOGIN` FOREIGN KEY (`CONCERNE_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_NOTIFICATIONITEM_NOTIFICATION_ID` FOREIGN KEY (`NOTIFICATION_ID`) REFERENCES `notification` (`ID`);

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `FK_PHOTO_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `FK_PUBLICATION_EMETTEUR_LOGIN` FOREIGN KEY (`EMETTEUR_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_PUBLICATION_GROUPE_ID` FOREIGN KEY (`GROUPE_ID`) REFERENCES `groupe` (`ID`),
  ADD CONSTRAINT `FK_PUBLICATION_RECEPTEUR_LOGIN` FOREIGN KEY (`RECEPTEUR_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `signalpublication`
--
ALTER TABLE `signalpublication`
  ADD CONSTRAINT `FK_SIGNALPUBLICATION_PUBLICATIONSIGNALE_ID` FOREIGN KEY (`PUBLICATIONSIGNALE_ID`) REFERENCES `publication` (`ID`),
  ADD CONSTRAINT `FK_SIGNALPUBLICATION_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `signaluser`
--
ALTER TABLE `signaluser`
  ADD CONSTRAINT `FK_SIGNALUSER_USERACTION_LOGIN` FOREIGN KEY (`USERACTION_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_SIGNALUSER_USERSIGNALE_LOGIN` FOREIGN KEY (`USERSIGNALE_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_LIEU_ID` FOREIGN KEY (`LIEU_ID`) REFERENCES `lieu` (`ID`);

--
-- Contraintes pour la table `videos`
--
ALTER TABLE `videos`
  ADD CONSTRAINT `FK_VIDEOS_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
