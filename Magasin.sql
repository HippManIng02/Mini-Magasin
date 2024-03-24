-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : dim. 14 mai 2023 à 15:29
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `Magasin`
--

-- --------------------------------------------------------

--
-- Structure de la table `Adresse`
--

CREATE TABLE `Adresse` (
  `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `rue` varchar(255) NOT NULL,
  `departement` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Adresse`
--

INSERT INTO `Adresse` (`id`, `code`, `numero`, `rue`, `departement`) VALUES
(1, 95100, 118, 'Moulin Sarrazin', 'Argenteuil'),
(2, 14, 74014, 'Rue Bezout', 'Paris'),
(3, 45100, 125, 'rue jean marie', 'Pontoise'),
(4, 78100, 10, 'Avenue de l\'europe', 'velizy'),
(5, 95100, 254, 'Jean Moulain', 'Satrouville'),
(6, 74103, 19, 'Alesia2', 'Paris'),
(7, 93210, 12, ' rue d\'alambert', 'Rosny sous bois'),
(8, 74102, 55, 'rue d\'acier', 'Paris'),
(9, 94000, 15, 'rue defense', 'Persant '),
(10, 75422, 2, 'rue du centre', 'Paris');

-- --------------------------------------------------------

--
-- Structure de la table `Fonction`
--

CREATE TABLE `Fonction` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Fonction`
--

INSERT INTO `Fonction` (`id`, `libelle`) VALUES
(1, 'Caissier(e)'),
(2, 'Resp Rayon'),
(3, 'Responsable');

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE `Personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `numero_badge` int(11) DEFAULT NULL,
  `numero_fidelite` int(11) DEFAULT NULL,
  `code_postal` int(11) DEFAULT NULL,
  `superieur` int(11) DEFAULT NULL,
  `fonction` int(11) DEFAULT NULL,
  `point_vente` int(11) DEFAULT NULL,
  `adresse` int(11) DEFAULT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Personne`
--

INSERT INTO `Personne` (`id`, `nom`, `prenom`, `email`, `numero_badge`, `numero_fidelite`, `code_postal`, `superieur`, `fonction`, `point_vente`, `adresse`, `role`) VALUES
(3, 'John', 'doe', 'john@doe.com', 111, NULL, NULL, 0, 1, 2, 9, 1),
(4, 'Lemaitre', 'jean', 'jean@gmail.com', 78, NULL, NULL, 0, 1, 2, 10, 1),
(5, 'Toto', 'tata', 'toto@tata.com', NULL, 28, 1458, NULL, NULL, NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Point_vente`
--

CREATE TABLE `Point_vente` (
  `numero` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Point_vente`
--

INSERT INTO `Point_vente` (`numero`, `nom`, `adresse`) VALUES
(2, 'test', 1),
(3, 'test 2', 2);

-- --------------------------------------------------------

--
-- Structure de la table `Prix`
--

CREATE TABLE `Prix` (
  `id` int(11) NOT NULL,
  `montant` double NOT NULL,
  `date_application` date NOT NULL,
  `date_fin_application` date DEFAULT NULL,
  `produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Prix`
--

INSERT INTO `Prix` (`id`, `montant`, `date_application`, `date_fin_application`, `produit`) VALUES
(1, 500, '2023-05-12', NULL, 1),
(2, 1458, '2023-05-13', NULL, 2),
(3, 12.8, '2023-05-13', NULL, 3),
(4, 5484, '2023-05-13', NULL, 4),
(5, 21478, '2023-05-13', NULL, 5),
(6, 5879, '2023-05-13', NULL, 6),
(7, 6484, '2023-05-13', NULL, 7),
(8, 125, '2023-05-13', NULL, 8);

-- --------------------------------------------------------

--
-- Structure de la table `Produit`
--

CREATE TABLE `Produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `pays` varchar(50) NOT NULL,
  `tva` float NOT NULL,
  `seuil` int(11) NOT NULL,
  `est_pesable` tinyint(1) NOT NULL,
  `unite_poids` float NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Produit`
--

INSERT INTO `Produit` (`id`, `nom`, `description`, `pays`, `tva`, `seuil`, `est_pesable`, `unite_poids`, `type`) VALUES
(1, 'PC', 'PC', 'Togo', 15, 5, 0, 0, 4),
(2, 'Bonbon', 'Bonbon', 'France', 5.8, 4, 0, 0, 1),
(3, 'Radio', 'Radio', 'Suisse', 7.1, 4, 0, 4, 4),
(4, 'Riz', 'Riz', 'Espagne', 5.1, 2, 0, 0, 1),
(5, 'Tomate', 'Tomates', 'Chine', 5, 45, 1, 1.2, 1),
(6, 'War Game', 'Game', 'Japon', 6, 25, 0, 0, 5),
(7, 'Coton', 'coton', ' USA', 14, 6, 1, 1.3, 5),
(8, 'Orangina', 'Orangina', 'USA', 3, 14, 0, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Produit_Point_vente`
--

CREATE TABLE `Produit_Point_vente` (
  `id` int(11) NOT NULL,
  `point_vente` int(11) NOT NULL,
  `produit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `est_actif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Produit_Point_vente`
--

INSERT INTO `Produit_Point_vente` (`id`, `point_vente`, `produit`, `quantite`, `est_actif`) VALUES
(2, 2, 2, 18, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Type_produit`
--

CREATE TABLE `Type_produit` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Type_produit`
--

INSERT INTO `Type_produit` (`id`, `libelle`, `description`) VALUES
(1, 'Alimentaire', 'Ensemble de produit consommable par l\'homme avec ou sans traitement'),
(2, 'Médecines', 'Ensemble de produit utilisable par l\'homme sous prescription'),
(4, 'Appareil électronique', 'Équipement technologique'),
(5, 'Autres', 'Tous les autres produits');

-- --------------------------------------------------------

--
-- Structure de la table `Vente`
--

CREATE TABLE `Vente` (
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `sous_total` double NOT NULL,
  `personnel` int(11) NOT NULL,
  `produit_point_vente` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Vente`
--

INSERT INTO `Vente` (`id`, `quantite`, `sous_total`, `personnel`, `produit_point_vente`, `client`, `date`) VALUES
(1, 4, 6170.256011992693, 3, 2, 5, '2023-05-14 00:00:00'),
(2, 6, 9255.38401798904, 3, 2, 5, '2023-05-14 00:00:00'),
(3, 4, 6170.256011992693, 3, 2, 5, '2023-05-14 00:00:00'),
(4, 4, 6170.256011992693, 3, 2, 5, '2023-05-14 00:00:00'),
(5, 1, 1542.5640029981732, 3, 2, 5, '2023-05-14 00:00:00'),
(6, 2, 3085.1280059963465, 3, 2, 5, '2023-05-14 00:00:00');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Adresse`
--
ALTER TABLE `Adresse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Fonction`
--
ALTER TABLE `Fonction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Personne`
--
ALTER TABLE `Personne`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_badge` (`numero_badge`),
  ADD UNIQUE KEY `numero_fidelite` (`numero_fidelite`),
  ADD KEY `fonction_personnel` (`fonction`),
  ADD KEY `point_vente_personnel` (`point_vente`),
  ADD KEY `adresse_personnel` (`adresse`);

--
-- Index pour la table `Point_vente`
--
ALTER TABLE `Point_vente`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `point_vente_adresse` (`adresse`);

--
-- Index pour la table `Prix`
--
ALTER TABLE `Prix`
  ADD PRIMARY KEY (`id`),
  ADD KEY `produit_prix` (`produit`);

--
-- Index pour la table `Produit`
--
ALTER TABLE `Produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `foreign_key` (`type`);

--
-- Index pour la table `Produit_Point_vente`
--
ALTER TABLE `Produit_Point_vente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `produit` (`produit`),
  ADD KEY `point_vente_p` (`point_vente`);

--
-- Index pour la table `Type_produit`
--
ALTER TABLE `Type_produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Vente`
--
ALTER TABLE `Vente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `personnel_vente` (`personnel`),
  ADD KEY `client_vente` (`client`),
  ADD KEY `produit_vente` (`produit_point_vente`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Adresse`
--
ALTER TABLE `Adresse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `Fonction`
--
ALTER TABLE `Fonction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `Personne`
--
ALTER TABLE `Personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `Point_vente`
--
ALTER TABLE `Point_vente`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `Prix`
--
ALTER TABLE `Prix`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `Produit`
--
ALTER TABLE `Produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `Produit_Point_vente`
--
ALTER TABLE `Produit_Point_vente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `Type_produit`
--
ALTER TABLE `Type_produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `Vente`
--
ALTER TABLE `Vente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Personne`
--
ALTER TABLE `Personne`
  ADD CONSTRAINT `adresse_personnel` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`id`),
  ADD CONSTRAINT `fonction_personnel` FOREIGN KEY (`fonction`) REFERENCES `Fonction` (`id`),
  ADD CONSTRAINT `point_vente_personnel` FOREIGN KEY (`point_vente`) REFERENCES `Point_vente` (`numero`);

--
-- Contraintes pour la table `Point_vente`
--
ALTER TABLE `Point_vente`
  ADD CONSTRAINT `point_vente_adresse` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`id`);

--
-- Contraintes pour la table `Prix`
--
ALTER TABLE `Prix`
  ADD CONSTRAINT `produit_prix` FOREIGN KEY (`produit`) REFERENCES `Produit` (`id`);

--
-- Contraintes pour la table `Produit`
--
ALTER TABLE `Produit`
  ADD CONSTRAINT `foreign_key` FOREIGN KEY (`type`) REFERENCES `Type_produit` (`id`);

--
-- Contraintes pour la table `Produit_Point_vente`
--
ALTER TABLE `Produit_Point_vente`
  ADD CONSTRAINT `point_vente_fk` FOREIGN KEY (`point_vente`) REFERENCES `Point_vente` (`numero`),
  ADD CONSTRAINT `point_vente_p` FOREIGN KEY (`point_vente`) REFERENCES `Point_vente` (`numero`),
  ADD CONSTRAINT `produit` FOREIGN KEY (`produit`) REFERENCES `Produit` (`id`);

--
-- Contraintes pour la table `Vente`
--
ALTER TABLE `Vente`
  ADD CONSTRAINT `client_vente` FOREIGN KEY (`client`) REFERENCES `Personne` (`id`),
  ADD CONSTRAINT `personnel_vente` FOREIGN KEY (`personnel`) REFERENCES `Personne` (`id`),
  ADD CONSTRAINT `produit_vente` FOREIGN KEY (`produit_point_vente`) REFERENCES `Produit_Point_vente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
