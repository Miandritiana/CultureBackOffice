--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categorieculture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorieculture (
    idcatecult integer NOT NULL,
    nomcatecult character varying,
    prix double precision
);


ALTER TABLE public.categorieculture OWNER TO postgres;

--
-- Name: parcelle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parcelle (
    idp integer NOT NULL,
    nomp character varying,
    taille integer
);


ALTER TABLE public.parcelle OWNER TO postgres;

--
-- Name: parcelleculture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parcelleculture (
    idpc integer NOT NULL,
    idp integer,
    idcatecult integer,
    rendement integer
);


ALTER TABLE public.parcelleculture OWNER TO postgres;

--
-- Name: phototerrain; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phototerrain (
    idterrain integer,
    photo character varying
);


ALTER TABLE public.phototerrain OWNER TO postgres;

--
-- Name: terrain; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.terrain (
    idterrain integer NOT NULL,
    description character varying,
    geolocalisation character varying
);


ALTER TABLE public.terrain OWNER TO postgres;

--
-- Name: terrainparcelle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.terrainparcelle (
    idtp integer NOT NULL,
    idterrain integer,
    idp integer
);


ALTER TABLE public.terrainparcelle OWNER TO postgres;

--
-- Name: terrainuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.terrainuser (
    idterrainuser integer NOT NULL,
    idterrain integer,
    iduser integer
);


ALTER TABLE public.terrainuser OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    iduser integer NOT NULL,
    nomuser character varying,
    password character varying
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: TABLE "user"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."user" IS 'celui qui contient les utilisateurs';


--
-- Data for Name: categorieculture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorieculture (idcatecult, nomcatecult, prix) FROM stdin;
\.


--
-- Data for Name: parcelle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parcelle (idp, nomp, taille) FROM stdin;
\.


--
-- Data for Name: parcelleculture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parcelleculture (idpc, idp, idcatecult, rendement) FROM stdin;
\.


--
-- Data for Name: phototerrain; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.phototerrain (idterrain, photo) FROM stdin;
\.


--
-- Data for Name: terrain; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.terrain (idterrain, description, geolocalisation) FROM stdin;
\.


--
-- Data for Name: terrainparcelle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.terrainparcelle (idtp, idterrain, idp) FROM stdin;
\.


--
-- Data for Name: terrainuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.terrainuser (idterrainuser, idterrain, iduser) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (iduser, nomuser, password) FROM stdin;
\.


--
-- Name: categorieculture pk_categorieculture; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorieculture
    ADD CONSTRAINT pk_categorieculture PRIMARY KEY (idcatecult);


--
-- Name: parcelle pk_parcelle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parcelle
    ADD CONSTRAINT pk_parcelle PRIMARY KEY (idp);


--
-- Name: parcelleculture pk_parcelleculture; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parcelleculture
    ADD CONSTRAINT pk_parcelleculture PRIMARY KEY (idpc);


--
-- Name: user pk_tbl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT pk_tbl PRIMARY KEY (iduser);


--
-- Name: terrain pk_terrain; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrain
    ADD CONSTRAINT pk_terrain PRIMARY KEY (idterrain);


--
-- Name: terrainparcelle pk_terrainparcelle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainparcelle
    ADD CONSTRAINT pk_terrainparcelle PRIMARY KEY (idtp);


--
-- Name: terrainuser pk_terrainuser; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainuser
    ADD CONSTRAINT pk_terrainuser PRIMARY KEY (idterrainuser);


--
-- Name: parcelleculture fk_parcelleculture_parcelle; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parcelleculture
    ADD CONSTRAINT fk_parcelleculture_parcelle FOREIGN KEY (idcatecult) REFERENCES public.categorieculture(idcatecult);


--
-- Name: parcelleculture fk_parcelleculture_parcelleculture; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parcelleculture
    ADD CONSTRAINT fk_parcelleculture_parcelleculture FOREIGN KEY (idp) REFERENCES public.parcelle(idp);


--
-- Name: phototerrain fk_phototerrain_terrain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phototerrain
    ADD CONSTRAINT fk_phototerrain_terrain FOREIGN KEY (idterrain) REFERENCES public.terrain(idterrain);


--
-- Name: terrainparcelle fk_terrainparcelle_parcelle; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainparcelle
    ADD CONSTRAINT fk_terrainparcelle_parcelle FOREIGN KEY (idp) REFERENCES public.parcelle(idp);


--
-- Name: terrainparcelle fk_terrainparcelle_terrain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainparcelle
    ADD CONSTRAINT fk_terrainparcelle_terrain FOREIGN KEY (idterrain) REFERENCES public.terrain(idterrain);


--
-- Name: terrainuser fk_terrainuser_terrain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainuser
    ADD CONSTRAINT fk_terrainuser_terrain FOREIGN KEY (idterrain) REFERENCES public.terrain(idterrain);


--
-- Name: terrainuser fk_terrainuser_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.terrainuser
    ADD CONSTRAINT fk_terrainuser_user FOREIGN KEY (iduser) REFERENCES public."user"(iduser);


--
-- PostgreSQL database dump complete
--

