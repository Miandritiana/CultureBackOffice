CREATE TABLE useruser (
    iduser serial PRIMARY KEY,
    nomuser varchar(100),
    password varchar(100),
    idAdmin boolean default false
);
INSERT INTO useruser (nomuser, password, idAdmin) VALUES ('admin', '123', true);


CREATE TABLE terrain (
    idterrain serial PRIMARY KEY,
    description  varchar(100),
    geolocalisation  varchar(100)
);

CREATE TABLE terrainuser (
    idterrainuser serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    iduser integer references useruser(iduser)
);

CREATE TABLE phototerrain (
    idphoto serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    photo varchar(100)
);


CREATE TABLE parcelle (
    idp serial PRIMARY KEY,
    nomp varchar(100),
    taille integer --en m2
);
INSERT INTO parcelle (nomp, taille) VALUES
('Parcelle A', 500),
('Parcelle B', 700),
('Parcelle C', 1000);

CREATE TABLE demandeparcelle (
    iddp serial PRIMARY KEY,
    nom varchar(100),
    taille integer
);

CREATE TABLE categorieculture (
    idcatecult serial PRIMARY KEY,
    nomcatecult varchar(100),
    prix double precision --/m2
);
INSERT INTO categorieculture (nomcatecult, prix) VALUES ('Fleurs', 20000);
INSERT INTO categorieculture (nomcatecult, prix) VALUES ('Herbes aromatiques', 15000);


CREATE TABLE parcelleculture (
    idpc serial PRIMARY KEY,
    daty timestamp,
    idp integer references parcelle(idp),
    idcatecult integer references categorieculture(idcatecult),
    rendement integer --kg/m2
);
INSERT INTO parcelleculture (daty, idp, idcatecult, rendement) VALUES
('2024-01-23 12:00:00', 1, 3, 300),
('2024-02-24 14:30:00', 2, 2, 500),
('2024-03-25 10:45:00', 3, 2, 400);


CREATE TABLE terrainparcelle (
    idtp serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    idp integer references parcelle(idp)
);