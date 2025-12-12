INSERT INTO auteurs (identifiant, prenom, nom, annee_naissance) VALUES
  (1, 'Victor', 'Hugo', 1802),
  (2, 'George', 'Orwell', 1903),
  (3, 'Margaret', 'Atwood', 1939);

INSERT INTO livres (identifiant, titre, code_isbn, annee_parution, categorie, auteur_id) VALUES
  (1, 'Les Misérables', '978-1234567890', 1862, 'ROMAN', 1);

ALTER TABLE auteurs ALTER COLUMN identifiant RESTART WITH 4;
