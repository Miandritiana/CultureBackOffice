CREATE TABLE useruser (
    iduser serial PRIMARY KEY,
    nomuser varchar(100),
    password varchar(100)
);

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

CREATE TABLE categorieculture (
    idcatecult serial PRIMARY KEY,
    nomcatecult varchar(100),
    prix double precision --/m2
);

CREATE TABLE parcelleculture (
    idpc serial PRIMARY KEY,
    idp integer references parcelle(idp),
    idcatecult integer references categorieculture(idcatecult),
    rendement integer --kg/m2
);


CREATE TABLE terrainparcelle (
    idtp serial PRIMARY KEY,
    idterrain integer references terrain(idterrain),
    idp integer references parcelle(idp)
);