-- ==== AUTEURS ====
INSERT INTO auteurs (prenom, nom, annee_naissance) VALUES
('Victor', 'Hugo', 1802),
('George', 'Orwell', 1903),
('Margaret', 'Atwood', 1939),
('Albert', 'Camus', 1913),
('Jane', 'Austen', 1775),
('Jules', 'Verne', 1828),
('Simone', 'de Beauvoir', 1908);

-- ==== LIVRES ====
INSERT INTO livres (titre, annee_parution, code_isbn, categorie, auteur_id) VALUES
('Les Misérables', 1862, '9782070409189', 'ROMAN', 1),
('1984', 1949, '9780451524935', 'ROMAN', 2),
('La Servante écarlate', 1985, '9782070368226', 'ROMAN', 3),
('L’Étranger', 1942, '9782070360022', 'ROMAN', 4),
('Orgueil et Préjugés', 1813, '9782070367724', 'ROMAN', 5),
('Vingt mille lieues sous les mers', 1870, '9782253006329', 'ROMAN', 6),
('Le Deuxième Sexe', 1949, '9782070320576', 'ESSAI', 7),
('Le Dernier Jour d’un Condamné', 1829, '9782070402050', 'ROMAN', 1),
('La Peste', 1947, '9782070360429', 'ROMAN', 4),
('Les Voyages Extraordinaires', 1875, '9782070612368', 'AUTRE', 6);

ALTER TABLE auteurs ALTER COLUMN identifiant RESTART WITH 7;
