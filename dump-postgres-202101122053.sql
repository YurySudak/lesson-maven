--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-01-12 20:53:54

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16422)
-- Name: group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."group" (
    id integer NOT NULL,
    group_name character varying(100) NOT NULL
);


ALTER TABLE public."group" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16420)
-- Name: group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_id_seq OWNER TO postgres;

--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 201
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_id_seq OWNED BY public."group".id;


--
-- TOC entry 204 (class 1259 OID 16514)
-- Name: marks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marks (
    id integer NOT NULL,
    group_id integer NOT NULL,
    student_id integer NOT NULL,
    mark_of_theme_1 smallint,
    mark_of_theme_2 smallint,
    mark_of_theme_3 smallint,
    mark_of_theme_4 smallint,
    mark_of_theme_5 smallint,
    mark_of_theme_6 smallint,
    mark_of_theme_7 smallint,
    mark_of_theme_8 smallint,
    mark_of_theme_9 smallint,
    mark_of_theme_10 smallint,
    mark_of_theme_11 smallint,
    mark_of_theme_12 smallint
);


ALTER TABLE public.marks OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16512)
-- Name: marks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marks_id_seq OWNER TO postgres;

--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 203
-- Name: marks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marks_id_seq OWNED BY public.marks.id;


--
-- TOC entry 206 (class 1259 OID 16532)
-- Name: type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type (
    id integer NOT NULL,
    type_name character varying(20) NOT NULL
);


ALTER TABLE public.type OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16530)
-- Name: type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_id_seq OWNER TO postgres;

--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 205
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_id_seq OWNED BY public.type.id;


--
-- TOC entry 208 (class 1259 OID 16538)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    type smallint NOT NULL,
    fio character varying(50) NOT NULL,
    age smallint NOT NULL,
    login character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    group1_id integer,
    group2_id integer,
    group3_id integer
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16536)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 2869 (class 2604 OID 16425)
-- Name: group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group" ALTER COLUMN id SET DEFAULT nextval('public.group_id_seq'::regclass);


--
-- TOC entry 2870 (class 2604 OID 16517)
-- Name: marks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marks ALTER COLUMN id SET DEFAULT nextval('public.marks_id_seq'::regclass);


--
-- TOC entry 2871 (class 2604 OID 16535)
-- Name: type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type ALTER COLUMN id SET DEFAULT nextval('public.type_id_seq'::regclass);


--
-- TOC entry 2872 (class 2604 OID 16541)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 3019 (class 0 OID 16422)
-- Dependencies: 202
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."group" (id, group_name) FROM stdin;
1	Java Core
2	Java Enterprise
3	Android
\.


--
-- TOC entry 3021 (class 0 OID 16514)
-- Dependencies: 204
-- Data for Name: marks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marks (id, group_id, student_id, mark_of_theme_1, mark_of_theme_2, mark_of_theme_3, mark_of_theme_4, mark_of_theme_5, mark_of_theme_6, mark_of_theme_7, mark_of_theme_8, mark_of_theme_9, mark_of_theme_10, mark_of_theme_11, mark_of_theme_12) FROM stdin;
1	1	2	9	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
3	1	3	7	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
6	1	4	8	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
8	1	5	9	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
10	1	6	6	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
11	1	7	7	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
4	2	2	7	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
7	2	3	8	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
9	2	4	5	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
12	2	6	8	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
2	3	6	8	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
5	3	7	6	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
13	3	8	9	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3023 (class 0 OID 16532)
-- Dependencies: 206
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type (id, type_name) FROM stdin;
1	admin
2	teacher
3	student
\.


--
-- TOC entry 3025 (class 0 OID 16538)
-- Dependencies: 208
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, type, fio, age, login, password, group1_id, group2_id, group3_id) FROM stdin;
2	3	Звягинцева Анастасия Александровна	20	zva	zva	1	2	\N
3	3	Лапунька Иван Иванович	21	lap	lap	1	2	\N
4	3	Парфенков Антон Антонович	21	parf	parf	1	2	\N
5	3	Глод Денис Денисович	21	glod	glod	1	\N	\N
6	3	Костюкевич Александр Александрович	20	kost	kost	1	2	3
7	3	Корчевский Павел Павлович	20	kor	kor	1	3	\N
8	3	Шашко Алина Андреевна	20	sha	sha	3	\N	\N
9	2	Власик Геннадий Геннадьевич	25	vlas	vlas	1	\N	\N
10	2	Калевич Вячеслав Вячеславович	35	kal	kal	2	\N	\N
11	2	Прудиков Владислав Владиславович	20	prud	prud	3	\N	\N
1	1	Судак Юрий Николаевич	38	sud	sud	\N	\N	\N
\.


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 201
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_id_seq', 3, true);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 203
-- Name: marks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marks_id_seq', 13, true);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 205
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_id_seq', 3, true);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 11, true);


--
-- TOC entry 2874 (class 2606 OID 16433)
-- Name: group group_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group"
    ADD CONSTRAINT group_pk PRIMARY KEY (id);


--
-- TOC entry 2876 (class 2606 OID 16519)
-- Name: marks marks_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT marks_pk PRIMARY KEY (id);


--
-- TOC entry 2878 (class 2606 OID 16578)
-- Name: type type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type
    ADD CONSTRAINT type_pk PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 16576)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2886 (class 2606 OID 16558)
-- Name: user fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk FOREIGN KEY (group1_id) REFERENCES public."group"(id);


--
-- TOC entry 2882 (class 2606 OID 16599)
-- Name: marks mark_student; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT mark_student FOREIGN KEY (student_id) REFERENCES public."user"(id);


--
-- TOC entry 2883 (class 2606 OID 16604)
-- Name: marks marks_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT marks_fk FOREIGN KEY (student_id) REFERENCES public."user"(id);


--
-- TOC entry 2881 (class 2606 OID 16525)
-- Name: marks marks_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT marks_fk2 FOREIGN KEY (group_id) REFERENCES public."group"(id);


--
-- TOC entry 2884 (class 2606 OID 16547)
-- Name: user student_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT student_fk2 FOREIGN KEY (group2_id) REFERENCES public."group"(id);


--
-- TOC entry 2885 (class 2606 OID 16552)
-- Name: user student_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT student_fk3 FOREIGN KEY (group3_id) REFERENCES public."group"(id);


--
-- TOC entry 2887 (class 2606 OID 16579)
-- Name: user user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk FOREIGN KEY (type) REFERENCES public.type(id);


-- Completed on 2021-01-12 20:53:54

--
-- PostgreSQL database dump complete
--

