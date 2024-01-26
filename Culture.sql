CREATE TABLE useruser (
    iduser serial PRIMARY KEY,
    nomuser varchar(100),
    password varchar(100),
    idAdmin boolean default false
);
INSERT INTO useruser (nomuser, password, idAdmin) VALUES ('Admin', '123', true);
INSERT INTO useruser (nomuser, password) VALUES ('Rasoa', '123');


CREATE TABLE terrain (
    idterrain serial PRIMARY KEY,
    description  varchar(100),
    geolocalisation  varchar(100),
    status int --0 en attente ,1 validé
);
INSERT INTO terrain (description, geolocalisation, status) VALUES
('Terrain 1', 'Latitude: X, Longitude: Y', 1),
('Terrain 2', 'Latitude: A, Longitude: B', 0),
('Terrain 3', 'Latitude: C, Longitude: D', 0);

CREATE TABLE parcelle (
    idp serial PRIMARY KEY,
    nomp varchar(100),
    taille integer --en m2
);
INSERT INTO parcelle (nomp, taille) VALUES
('Parcelle A', 500),
('Parcelle B', 700),
('Parcelle C', 1000);


CREATE TABLE terrainparcelle (
    idtp serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    idp integer references parcelle(idp)
);
INSERT INTO terrainparcelle (idterrain, idp) VALUES
(1, 1),
(1, 2);

INSERT INTO terrainparcelle (idterrain, idp) VALUES
(2, 3);

CREATE TABLE terrainuser (
    idterrainuser serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    iduser integer references useruser(iduser)
);
INSERT INTO terrainuser (idterrain, iduser) VALUES
(1, 2);
INSERT INTO terrainuser (idterrain, iduser) VALUES
(2, 3);
--------View ListeTerrain 

CREATE VIEW viewDetailsTerrain AS
SELECT
    t.idterrain,
    t.description,
    t.geolocalisation,
    t.status, 
    p.idp,
    p.nomp,
    p.taille,
    tu.iduser,
    u.nomuser,
    pt.photo  -- Ajout de la colonne photo
FROM
    terrain t
JOIN terrainparcelle tp ON t.idterrain = tp.idterrain
JOIN parcelle p ON tp.idp = p.idp
LEFT JOIN terrainuser tu ON t.idterrain = tu.idterrain
JOIN useruser u ON u.iduser = tu.iduser
LEFT JOIN phototerrain pt ON t.idterrain = pt.idterrain;  -- Ajout de la jointure avec la table phototerrain


CREATE TABLE phototerrain (
    idphoto serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    photo varchar(100)
);
INSERT INTO phototerrain (idterrain,photo) values (2,'terrain1.jpg');
CREATE TABLE categorieculture (
    idcatecult serial PRIMARY KEY,
    nomcatecult varchar(100),
    prix double precision --/m2
);
INSERT INTO categorieculture (nomcatecult, prix) VALUES ('Katsaka', 20000);
INSERT INTO categorieculture (nomcatecult, prix) VALUES ('Vary', 15000);
INSERT INTO categorieculture (nomcatecult, prix) VALUES ('Ovy', 15000);


CREATE TABLE parcelleculture (
    idpc serial PRIMARY KEY,
    daty timestamp,
    idp integer references parcelle(idp),
    idcatecult integer references categorieculture(idcatecult),
    rendement integer --kg/m2
);
INSERT INTO parcelleculture (daty, idp, idcatecult, rendement) VALUES
('2024-01-23 12:00:00', 1, 1, 300),
('2024-02-24 14:30:00', 2, 2, 500),
('2024-03-25 10:45:00', 3, 2, 400);
INSERT INTO parcelleculture (daty, idp, idcatecult, rendement) VALUES
('2024-01-23 12:00:00', 1, 1, 300),
('2024-02-24 14:30:00', 2, 2, 700);

INSERT INTO parcelleculture (daty, idp, idcatecult, rendement) VALUES
('2024-01-23 12:00:00', 1, 3, 300),
('2024-02-24 14:30:00', 2, 3, 200);

--------Statistique
SELECT
    cc.nomcatecult AS categorie,
    SUM(pc.rendement) AS totalRendement
FROM
    parcelleculture pc
JOIN
    categorieculture cc ON pc.idcatecult = cc.idcatecult
GROUP BY
    cc.nomcatecult;

--------View ListeCulture 
CREATE VIEW viewListeCulture AS
SELECT
    t.idterrain,
    tp.idp AS idparcelle,
    tu.iduser,
    cc.idcatecult,
    cc.nomcatecult AS nomculture,
    t.description AS nomterrain,
    p.nomp,
    u.nomuser
FROM
    terrain t
JOIN
    terrainparcelle tp ON t.idterrain = tp.idterrain
JOIN
    parcelle p ON tp.idp = p.idp
JOIN
    terrainuser tu ON t.idterrain = tu.idterrain
JOIN
    useruser u ON tu.iduser = u.iduser
JOIN
    parcelleculture pc ON tp.idp = pc.idp
JOIN
    categorieculture cc ON pc.idcatecult = cc.idcatecult;

--------View Terrain A valider
CREATE VIEW viewTerrainAValider AS
SELECT
    t.idterrain,
    t.description,
    t.geolocalisation,
    t.status, 
    tu.iduser,
    u.nomuser,
    pt.photo  
FROM
    terrain t
LEFT JOIN terrainuser tu ON t.idterrain = tu.idterrain
JOIN useruser u ON u.iduser = tu.iduser
LEFT JOIN phototerrain pt ON t.idterrain = pt.idterrain
WHERE t.status=0;
