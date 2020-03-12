-- phpMyAdmin SQL Dump
-- version 4.9.4deb1
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:3306
-- Létrehozás ideje: 2020. Már 12. 08:13
-- Kiszolgáló verziója: 8.0.19-0ubuntu0.19.10.3
-- PHP verzió: 7.3.11-0ubuntu0.19.10.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `hoe`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ability`
--

CREATE TABLE `ability` (
  `id` bigint NOT NULL,
  `brain` tinyint DEFAULT NULL,
  `description` longtext COLLATE utf8_hungarian_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `power` tinyint DEFAULT NULL,
  `skill` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `ability`
--

INSERT INTO `ability` (`id`, `brain`, `description`, `name`, `power`, `skill`) VALUES
(1, 100, 'Valahogy mindig megússza', 'Találékonyság', 10, 30),
(2, 10, 'Értelmetlen erőszak', 'Vérszomj', 100, 20),
(3, 80, 'Egyedül nem csinál semmit', 'csapat szellem', 50, 70);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `empire`
--

CREATE TABLE `empire` (
  `id` bigint NOT NULL,
  `description` longtext COLLATE utf8_hungarian_ci,
  `name` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `userid` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hero`
--

CREATE TABLE `hero` (
  `id` bigint NOT NULL,
  `description` longtext COLLATE utf8_hungarian_ci,
  `name` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `userid` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `hero`
--

INSERT INTO `hero` (`id`, `description`, `name`, `userid`) VALUES
(1, 'Első Hős.', 'OE-NIK', 'e299d013-7cda-4b01-ae24-17dee05fa399'),
(8, 'IT', '4D doft', 'e299d013-7cda-4b01-ae24-17dee05fa399'),
(9, '111', '11', 'e299d013-7cda-4b01-ae24-17dee05fa399');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hybrid`
--

CREATE TABLE `hybrid` (
  `id` bigint NOT NULL,
  `percent` tinyint DEFAULT NULL,
  `speciesid` bigint DEFAULT NULL,
  `hero_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `hybrid`
--

INSERT INTO `hybrid` (`id`, `percent`, `speciesid`, `hero_id`) VALUES
(1, 10, 1, 1),
(2, 90, 2, 1),
(9, 100, 3, 8),
(10, 1, 3, 9);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `securityguard`
--

CREATE TABLE `securityguard` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `starttime` date DEFAULT NULL,
  `stoptime` date DEFAULT NULL,
  `empire_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `species`
--

CREATE TABLE `species` (
  `id` bigint NOT NULL,
  `description` longtext COLLATE utf8_hungarian_ci,
  `name` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `species`
--

INSERT INTO `species` (`id`, `description`, `name`) VALUES
(1, 'Kicsi vicces lény', 'Manó'),
(2, 'Nagy büdös erőszakos lény', 'Ork'),
(3, 'Azt hiszi ő a főnök.....', 'Ember');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `species_endowments`
--

CREATE TABLE `species_endowments` (
  `species_id` bigint NOT NULL,
  `endowments_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `species_endowments`
--

INSERT INTO `species_endowments` (`species_id`, `endowments_id`) VALUES
(1, 1),
(2, 2),
(3, 3);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `ability`
--
ALTER TABLE `ability`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `empire`
--
ALTER TABLE `empire`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `hero`
--
ALTER TABLE `hero`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `hybrid`
--
ALTER TABLE `hybrid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3dr7ku4stt50jdcuysaxp7li2` (`hero_id`);

--
-- A tábla indexei `securityguard`
--
ALTER TABLE `securityguard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1s4g1qkcsg759tmptfjsqxfuf` (`empire_id`);

--
-- A tábla indexei `species`
--
ALTER TABLE `species`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `species_endowments`
--
ALTER TABLE `species_endowments`
  ADD KEY `FK4jmeg109v1wi7s88na8hq6gl7` (`endowments_id`),
  ADD KEY `FKqh58nkrqe66cok5qohpmw7lgn` (`species_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `ability`
--
ALTER TABLE `ability`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `empire`
--
ALTER TABLE `empire`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `hero`
--
ALTER TABLE `hero`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT a táblához `hybrid`
--
ALTER TABLE `hybrid`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `securityguard`
--
ALTER TABLE `securityguard`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `species`
--
ALTER TABLE `species`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `hybrid`
--
ALTER TABLE `hybrid`
  ADD CONSTRAINT `FK3dr7ku4stt50jdcuysaxp7li2` FOREIGN KEY (`hero_id`) REFERENCES `hero` (`id`);

--
-- Megkötések a táblához `securityguard`
--
ALTER TABLE `securityguard`
  ADD CONSTRAINT `FK1s4g1qkcsg759tmptfjsqxfuf` FOREIGN KEY (`empire_id`) REFERENCES `empire` (`id`);

--
-- Megkötések a táblához `species_endowments`
--
ALTER TABLE `species_endowments`
  ADD CONSTRAINT `FK4jmeg109v1wi7s88na8hq6gl7` FOREIGN KEY (`endowments_id`) REFERENCES `ability` (`id`),
  ADD CONSTRAINT `FKqh58nkrqe66cok5qohpmw7lgn` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
