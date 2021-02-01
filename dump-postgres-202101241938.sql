--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-01-24 19:38:31

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
-- TOC entry 3046 (class 0 OID 0)
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
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 201
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_id_seq OWNED BY public."group".id;


--
-- TOC entry 204 (class 1259 OID 16514)
-- Name: mark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark (
    id integer NOT NULL,
    group_id integer NOT NULL,
    student_id integer NOT NULL,
    theme smallint NOT NULL,
    value smallint
);


ALTER TABLE public.mark OWNER TO postgres;

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
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 203
-- Name: marks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marks_id_seq OWNED BY public.mark.id;


--
-- TOC entry 210 (class 1259 OID 16644)
-- Name: salary; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salary (
    teacher_id integer NOT NULL,
    value double precision,
    month smallint,
    id integer NOT NULL
);


ALTER TABLE public.salary OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16659)
-- Name: salary_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.salary_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.salary_id_seq OWNER TO postgres;

--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 211
-- Name: salary_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.salary_id_seq OWNED BY public.salary.id;


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
-- TOC entry 3050 (class 0 OID 0)
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
    password character varying(20) NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16615)
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group (
    user_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE public.user_group OWNER TO postgres;

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
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 2879 (class 2604 OID 16425)
-- Name: group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group" ALTER COLUMN id SET DEFAULT nextval('public.group_id_seq'::regclass);


--
-- TOC entry 2880 (class 2604 OID 16517)
-- Name: mark id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark ALTER COLUMN id SET DEFAULT nextval('public.marks_id_seq'::regclass);


--
-- TOC entry 2883 (class 2604 OID 16661)
-- Name: salary id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salary ALTER COLUMN id SET DEFAULT nextval('public.salary_id_seq'::regclass);


--
-- TOC entry 2881 (class 2604 OID 16535)
-- Name: type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type ALTER COLUMN id SET DEFAULT nextval('public.type_id_seq'::regclass);


--
-- TOC entry 2882 (class 2604 OID 16541)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 3031 (class 0 OID 16422)
-- Dependencies: 202
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."group" (id, group_name) FROM stdin;
1	Java Core
2	Java Enterprise
3	Android
\.


--
-- TOC entry 3033 (class 0 OID 16514)
-- Dependencies: 204
-- Data for Name: mark; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mark (id, group_id, student_id, theme, value) FROM stdin;
83	2	2	3	7
84	2	2	4	6
30	1	3	5	6
31	1	3	6	0
32	1	3	7	8
33	1	3	8	0
92	2	6	1	5
34	1	3	9	0
35	1	3	10	0
36	1	3	11	0
38	1	4	2	7
39	1	4	3	9
40	1	4	4	0
41	1	4	5	0
42	1	4	6	6
43	1	4	7	8
44	1	4	8	0
93	2	6	2	6
45	1	4	9	0
46	1	4	10	0
47	1	4	11	0
49	1	5	2	7
50	1	5	3	9
94	2	6	3	8
95	2	6	4	6
96	2	6	5	0
97	2	6	6	7
114	2	3	1	5
115	2	3	2	6
116	2	3	3	8
117	2	3	4	6
118	2	3	5	0
119	2	3	6	0
120	2	3	7	0
121	2	3	8	0
26	1	3	1	5
27	1	3	2	7
28	1	3	3	9
29	1	3	4	0
37	1	4	1	5
19	1	2	5	0
20	1	2	6	6
21	1	2	7	8
22	1	2	8	0
23	1	2	9	0
24	1	2	10	0
25	1	2	11	0
51	1	5	4	8
52	1	5	5	6
53	1	5	6	0
54	1	5	7	7
55	1	5	8	0
56	1	5	9	0
57	1	5	10	0
58	1	5	11	0
59	1	6	1	5
60	1	6	2	7
61	1	6	3	9
62	1	6	4	0
63	1	6	5	9
64	1	6	6	6
65	1	6	7	7
66	1	6	8	0
67	1	6	9	0
68	1	6	10	0
69	1	6	11	0
70	1	7	1	5
71	1	7	2	7
72	1	7	3	9
73	1	7	4	0
74	1	7	5	6
75	1	7	6	0
76	1	7	7	7
77	1	7	8	0
78	1	7	9	0
79	1	7	10	0
80	1	7	11	0
5	3	7	12	0
14	3	8	12	0
13	3	8	12	9
3	1	3	12	0
124	2	3	11	0
125	2	4	1	5
126	2	4	2	6
127	2	4	3	8
128	2	4	4	6
9	2	4	12	0
129	2	4	5	7
130	2	4	6	0
131	2	4	7	0
105	3	6	3	7
106	3	6	4	0
107	3	6	5	0
108	3	6	6	0
109	3	6	7	0
110	3	6	8	0
111	3	6	9	0
112	3	6	10	0
113	3	6	11	0
15	1	2	1	5
16	1	2	2	7
17	1	2	3	9
18	1	2	4	0
6	1	4	12	0
2	3	6	12	0
8	1	5	12	0
82	2	2	2	6
10	1	6	12	0
11	1	7	12	0
4	2	2	12	0
7	2	3	12	0
122	2	3	9	0
123	2	3	10	0
1	1	2	12	0
85	2	2	5	0
86	2	2	6	0
87	2	2	7	0
88	2	2	8	0
89	2	2	9	0
90	2	2	10	0
91	2	2	11	0
132	2	4	8	0
133	2	4	9	0
134	2	4	10	0
135	2	4	11	0
48	1	5	1	5
98	2	6	7	0
99	2	6	8	0
100	2	6	9	0
101	2	6	10	0
102	2	6	11	0
103	3	6	1	7
104	3	6	2	9
136	3	7	1	8
137	3	7	2	9
138	3	7	3	0
139	3	7	4	0
140	3	7	5	0
141	3	7	6	0
142	3	7	7	0
143	3	7	8	0
144	3	7	9	0
145	3	7	10	0
81	2	2	1	5
12	2	6	12	0
146	3	7	11	0
147	3	8	1	8
148	3	8	2	9
149	3	8	3	0
150	3	8	4	0
151	3	8	5	0
152	3	8	6	0
153	3	8	7	0
154	3	8	8	0
155	3	8	9	0
156	3	8	10	0
157	3	8	11	0
\.


--
-- TOC entry 3039 (class 0 OID 16644)
-- Dependencies: 210
-- Data for Name: salary; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salary (teacher_id, value, month, id) FROM stdin;
11	1600.11	1	17
11	1600.11	2	18
11	1600.11	3	19
11	1600.11	4	20
11	1800.11	5	21
11	1800.11	6	22
11	1800.11	7	23
11	1800.11	8	24
11	1800.11	9	26
11	1800.11	10	27
11	1800.11	11	28
11	1800.11	12	29
9	1600	2	30
9	1600	3	31
9	1600	4	32
9	1600	5	33
9	1600	6	34
9	1700	7	35
9	1700	8	36
9	1700	9	37
9	1700	10	38
9	1700	11	39
9	1700	12	40
9	1600	1	25
10	1800.5	9	9
10	1800.5	10	10
10	1800.5	11	11
10	1800.5	12	12
10	1500.5	1	1
10	1500.5	2	2
10	1500.5	3	3
10	1500.5	4	4
10	1600.5	5	5
10	1600.5	6	6
10	1600.5	7	7
10	1600.5	8	8
\.


--
-- TOC entry 3035 (class 0 OID 16532)
-- Dependencies: 206
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type (id, type_name) FROM stdin;
1	admin
2	teacher
3	student
\.


--
-- TOC entry 3037 (class 0 OID 16538)
-- Dependencies: 208
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, type, fio, age, login, password) FROM stdin;
2	3	Звягинцева Анастасия Александровна	20	zva	zva
3	3	Лапунька Иван Иванович	21	lap	lap
4	3	Парфенков Антон Антонович	21	parf	parf
5	3	Глод Денис Денисович	21	glod	glod
6	3	Костюкевич Александр Александрович	20	kost	kost
7	3	Корчевский Павел Павлович	20	kor	kor
8	3	Шашко Алина Андреевна	20	sha	sha
9	2	Власик Геннадий Геннадьевич	25	vlas	vlas
10	2	Калевич Вячеслав Вячеславович	35	kal	kal
11	2	Прудиков Владислав Владиславович	20	prud	prud
1	1	Судак Юрий Николаевич	38	sud	sud
\.


--
-- TOC entry 3038 (class 0 OID 16615)
-- Dependencies: 209
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group (user_id, group_id) FROM stdin;
2	1
2	2
3	1
3	2
4	1
4	2
5	1
6	1
6	2
6	3
7	1
7	3
8	3
9	1
10	2
11	3
\.


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 201
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_id_seq', 17, true);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 203
-- Name: marks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marks_id_seq', 157, true);


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 211
-- Name: salary_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.salary_id_seq', 72, true);


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 205
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_id_seq', 3, true);


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 25, true);


--
-- TOC entry 2885 (class 2606 OID 16433)
-- Name: group group_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group"
    ADD CONSTRAINT group_pk PRIMARY KEY (id);


--
-- TOC entry 2887 (class 2606 OID 16519)
-- Name: mark marks_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT marks_pk PRIMARY KEY (id);


--
-- TOC entry 2893 (class 2606 OID 16666)
-- Name: salary salary_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salary
    ADD CONSTRAINT salary_pk PRIMARY KEY (id);


--
-- TOC entry 2889 (class 2606 OID 16578)
-- Name: type type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type
    ADD CONSTRAINT type_pk PRIMARY KEY (id);


--
-- TOC entry 2891 (class 2606 OID 16576)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2898 (class 2606 OID 16623)
-- Name: user_group group_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT group_fk FOREIGN KEY (group_id) REFERENCES public."group"(id);


--
-- TOC entry 2895 (class 2606 OID 16599)
-- Name: mark mark_student; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT mark_student FOREIGN KEY (student_id) REFERENCES public."user"(id);


--
-- TOC entry 2894 (class 2606 OID 16525)
-- Name: mark marks_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT marks_fk2 FOREIGN KEY (group_id) REFERENCES public."group"(id);


--
-- TOC entry 2899 (class 2606 OID 16647)
-- Name: salary salary_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salary
    ADD CONSTRAINT salary_fk FOREIGN KEY (teacher_id) REFERENCES public."user"(id);


--
-- TOC entry 2896 (class 2606 OID 16579)
-- Name: user user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk FOREIGN KEY (type) REFERENCES public.type(id);


--
-- TOC entry 2897 (class 2606 OID 16618)
-- Name: user_group user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


-- Completed on 2021-01-24 19:38:31

--
-- PostgreSQL database dump complete
--

