
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `Gaming`
--
CREATE DATABASE IF NOT EXISTS `Gaming`;
USE `Gaming`;

-- --------------------------------------------------------

--
-- Struttura della tabella `gioco`
--

DROP TABLE IF EXISTS `gioco`;
CREATE TABLE `gioco` (
  `id` int(6) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `punti` int(3) NOT NULL DEFAULT '10' COMMENT 'punti xp dati dal gioco'
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Struttura della tabella `recensione`
--

DROP TABLE IF EXISTS `recensione`;

CREATE TABLE `recensione` (
  `id` int(6) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `testo` varchar(255) NOT NULL,
  `approvata` tinyint(1) NOT NULL DEFAULT '0',
  `id_gioco` int(6) NOT NULL REFERENCES gioco(id)
)ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Struttura della tabella `timeline`
--

DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `livello` int(11) NOT NULL,
  `data` date NOT NULL,
  `id_giocatore` int(11) NOT NULL REFERENCES utente(id) 
) ENGINE=InnoDB ;


-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente` (
  `id` int(6) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `tipo` varchar(10) NOT NULL DEFAULT 'giocatore'
) ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Struttura della tabella `utente_gioco`
--

DROP TABLE IF EXISTS `utente_gioco`;
CREATE TABLE `utente_gioco` (
  `id` int(6) NOT NULL REFERENCES utente(id) ,
  `puntixp` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB;

--
-- Trigger `utente_gioco`
--
DROP TRIGGER IF EXISTS `infoTimeline`;
DELIMITER $$
CREATE TRIGGER `infoTimeline` AFTER UPDATE ON `utente_gioco` FOR EACH ROW BEGIN
DECLARE vecchio_livello INT;
DECLARE nuovo_livello INT;
SET vecchio_livello = FLOOR(OLD.puntixp/100);
SET nuovo_livello = FLOOR(NEW.puntixp/100);
IF (vecchio_livello != nuovo_livello) THEN
   INSERT INTO timeline (livello, data, id_giocatore) VALUES (nuovo_livello, DATE(NOW()), OLD.id);
END IF;     
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `voto`
--

DROP TABLE IF EXISTS `voto`;
CREATE TABLE `voto` (
  `id_voto` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_gioco` int(6) NOT NULL REFERENCES gioco(id) ,
  `id_utente` int(6) NOT NULL REFERENCES utente(id) ,
  `voto` int(1) NOT NULL
) ENGINE=InnoDB;

--
-- Dump dei dati per la tabella `gioco`
--

INSERT INTO `gioco` (`id`, `nome`, `punti`) VALUES
(1, 'tressette', 30),
(2, 'scopone', 10),
(3, 'bridge', 10),
(4, 'sette e mezzo', 10),
(5, 'solitario', 3),
(6, 'ruba mazzo', 10),
(7, 'briscola', 50);


--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `nome`, `cognome`, `email`, `username`, `password`, `tipo`) VALUES
(1, 'Giovanni', 'Rossi', 'giovanni_rossi@gmail.com', 'giovanni', 'password', 'giocatore'),
(2, 'Franco', 'Bianchi', 'franco_bianchi@gmail.com', 'franco', 'password', 'giocatore'),
(3, 'Dino', 'Sauro', 'dino_sauro@gmail.com', 'dino', 'password', 'moderatore');

--
-- Dump dei dati per la tabella `utente_gioco`
--

INSERT INTO `utente_gioco` (`id`, `puntixp`) VALUES
(1, 50),
(2, 0),
(3, 70);

--
-- Dump dei dati per la tabella `voto`
--

INSERT INTO `voto` (`id_voto`, `id_gioco`, `id_utente`, `voto`) VALUES
(1, 3, 1, 3),
(2, 3, 2, 3),
(3, 1, 3, 2);

--
-- Dump dei dati per la tabella `recensione`
--

INSERT INTO `recensione` (`id`, `testo`, `approvata`, `id_gioco`) VALUES
(1, 'Recensione1', 1, 3),
(2, 'Recensione1', 1, 3),
(3, 'Recensione1', 1, 3),
(4, 'Recensione1', 1, 2),
(5, 'Recensione1', 1, 1),
(6, 'ProRecensione1va2', 0, 4),
(7, 'Recensione1', 0, 3),
(8, 'Recensione1', 0, 2),
(9, 'Recensione1', 0, 4),
(10, 'Recensione1', 0, 1);
