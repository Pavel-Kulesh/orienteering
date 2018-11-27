--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.4

-- Started on 2018-11-27 18:52:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 213 (class 1259 OID 31614)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id integer NOT NULL,
    name character varying NOT NULL,
    country_id integer NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 31612)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_id_seq OWNER TO postgres;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 212
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- TOC entry 215 (class 1259 OID 31625)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 31623)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 214
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- TOC entry 196 (class 1259 OID 31521)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    phone character varying,
    city_id integer NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 31583)
-- Name: customer_2_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_2_event (
    customer_id integer NOT NULL,
    event_id integer NOT NULL
);


ALTER TABLE public.customer_2_event OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 31579)
-- Name: customer_2_event_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_2_event_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_2_event_customer_id_seq OWNER TO postgres;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 205
-- Name: customer_2_event_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_2_event_customer_id_seq OWNED BY public.customer_2_event.customer_id;


--
-- TOC entry 206 (class 1259 OID 31581)
-- Name: customer_2_event_event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_2_event_event_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_2_event_event_id_seq OWNER TO postgres;

--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 206
-- Name: customer_2_event_event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_2_event_event_id_seq OWNED BY public.customer_2_event.event_id;


--
-- TOC entry 204 (class 1259 OID 31570)
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id integer NOT NULL,
    name character varying NOT NULL,
    customer_id integer NOT NULL,
    date timestamp with time zone NOT NULL,
    country_id integer NOT NULL,
    type character varying NOT NULL,
    info text NOT NULL,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.event OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 31568)
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_id_seq OWNER TO postgres;

--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 203
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- TOC entry 200 (class 1259 OID 31544)
-- Name: map; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.map (
    id integer NOT NULL,
    name character varying NOT NULL,
    customer_id integer NOT NULL,
    path character varying NOT NULL,
    file character varying NOT NULL,
    latitude1 numeric,
    longitude1 numeric,
    latitude2 numeric,
    longitude2 numeric,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.map OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 31636)
-- Name: map_2_route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.map_2_route (
    map_id integer NOT NULL,
    route_id integer NOT NULL
);


ALTER TABLE public.map_2_route OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 31542)
-- Name: map_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.map_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.map_id_seq OWNER TO postgres;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 199
-- Name: map_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.map_id_seq OWNED BY public.map.id;


--
-- TOC entry 211 (class 1259 OID 31601)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id integer NOT NULL,
    name character varying NOT NULL,
    info text NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.news OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 31599)
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO postgres;

--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 210
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- TOC entry 209 (class 1259 OID 31590)
-- Name: point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.point (
    id integer NOT NULL,
    route_id integer NOT NULL,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    diff_time integer,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.point OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 31588)
-- Name: point_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.point_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.point_id_seq OWNER TO postgres;

--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 208
-- Name: point_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.point_id_seq OWNED BY public.point.id;


--
-- TOC entry 198 (class 1259 OID 31531)
-- Name: route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.route (
    id integer NOT NULL,
    name character varying NOT NULL,
    path character varying NOT NULL,
    file character varying NOT NULL,
    customer_id integer NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.route OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 31529)
-- Name: route_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.route_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.route_id_seq OWNER TO postgres;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 197
-- Name: route_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.route_id_seq OWNED BY public.route.id;


--
-- TOC entry 202 (class 1259 OID 31557)
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id integer NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    role character varying NOT NULL,
    created timestamp with time zone NOT NULL,
    updated timestamp with time zone NOT NULL
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 31555)
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO postgres;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- TOC entry 2745 (class 2604 OID 31617)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- TOC entry 2746 (class 2604 OID 31628)
-- Name: country id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 31586)
-- Name: customer_2_event customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_2_event ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_2_event_customer_id_seq'::regclass);


--
-- TOC entry 2742 (class 2604 OID 31587)
-- Name: customer_2_event event_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_2_event ALTER COLUMN event_id SET DEFAULT nextval('public.customer_2_event_event_id_seq'::regclass);


--
-- TOC entry 2740 (class 2604 OID 31573)
-- Name: event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- TOC entry 2738 (class 2604 OID 31547)
-- Name: map id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map ALTER COLUMN id SET DEFAULT nextval('public.map_id_seq'::regclass);


--
-- TOC entry 2744 (class 2604 OID 31604)
-- Name: news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- TOC entry 2743 (class 2604 OID 31593)
-- Name: point id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point ALTER COLUMN id SET DEFAULT nextval('public.point_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 31534)
-- Name: route id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route ALTER COLUMN id SET DEFAULT nextval('public.route_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 31560)
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- TOC entry 2925 (class 0 OID 31614)
-- Dependencies: 213
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.city (id, name, country_id, created, updated) VALUES (1, 'Grodno', 1, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (2, 'Minsk', 1, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (3, 'Vilnus', 2, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (4, 'Kaunas', 2, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (5, 'Paris', 3, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (6, 'Lion', 3, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (7, 'Berlin', 4, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (8, 'Borna', 4, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (9, 'Dohna', 4, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.city (id, name, country_id, created, updated) VALUES (10, 'Bremen', 4, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');


--
-- TOC entry 2927 (class 0 OID 31625)
-- Dependencies: 215
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.country (id, name, created, updated) VALUES (1, 'Belarus', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.country (id, name, created, updated) VALUES (2, 'Latvia', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.country (id, name, created, updated) VALUES (3, 'France', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.country (id, name, created, updated) VALUES (4, 'Germany', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');


--
-- TOC entry 2908 (class 0 OID 31521)
-- Dependencies: 196
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer (id, name, surname, phone, city_id, created, updated) VALUES (1, 'Pavel', 'K', '111-111-111', 1, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.customer (id, name, surname, phone, city_id, created, updated) VALUES (2, 'Vadim', 'D', '111-111-111', 5, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.customer (id, name, surname, phone, city_id, created, updated) VALUES (3, 'Maria', 'M', '111-111-111', 5, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.customer (id, name, surname, phone, city_id, created, updated) VALUES (6, 'test', 'admin', '', 5, '2018-11-15 19:04:09.377+03', '2018-11-15 19:04:09.377+03');


--
-- TOC entry 2919 (class 0 OID 31583)
-- Dependencies: 207
-- Data for Name: customer_2_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer_2_event (customer_id, event_id) VALUES (1, 2);
INSERT INTO public.customer_2_event (customer_id, event_id) VALUES (1, 1);
INSERT INTO public.customer_2_event (customer_id, event_id) VALUES (2, 2);
INSERT INTO public.customer_2_event (customer_id, event_id) VALUES (3, 1);
INSERT INTO public.customer_2_event (customer_id, event_id) VALUES (6, 2);


--
-- TOC entry 2916 (class 0 OID 31570)
-- Dependencies: 204
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.event (id, name, customer_id, date, country_id, type, info, latitude, longitude, created, updated) VALUES (1, 'Running Day', 1, '2017-10-25 00:00:00+03', 1, 'ROGANE', 'sleeping day after', 24.1, 2.33, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.event (id, name, customer_id, date, country_id, type, info, latitude, longitude, created, updated) VALUES (2, 'sleeping Day', 3, '2017-10-25 00:00:00+03', 3, 'ROGANE', 'some info', 24.1, 2.33, '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');


--
-- TOC entry 2912 (class 0 OID 31544)
-- Dependencies: 200
-- Data for Name: map; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.map (id, name, customer_id, path, file, latitude1, longitude1, latitude2, longitude2, created, updated) VALUES (5, '1', 6, '1', '1', 1, 1, 1, 1, '2018-11-20 21:07:23.912+03', '2018-11-20 21:07:23.912+03');
INSERT INTO public.map (id, name, customer_id, path, file, latitude1, longitude1, latitude2, longitude2, created, updated) VALUES (6, '12', 6, '12', '12', 12, 12, 12, 12, '2018-11-22 19:39:25.791+03', '2018-11-22 19:39:25.791+03');
INSERT INTO public.map (id, name, customer_id, path, file, latitude1, longitude1, latitude2, longitude2, created, updated) VALUES (7, 'vert', 6, 'qw', 'PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxncHggY3JlYXRvcj0iR2FybWluIENvbm5lY3QiIHZlcnNpb249IjEuMSINCiAgeHNpOnNjaGVtYUxvY2F0aW9uPSJodHRwOi8vd3d3LnRvcG9ncmFmaXguY29tL0dQWC8xLzEgaHR0cDovL3d3dy50b3BvZ3JhZml4LmNvbS9HUFgvMTEueHNkIg0KICB4bWxuczpuczM9Imh0dHA6Ly93d3cuZ2FybWluLmNvbS94bWxzY2hlbWFzL1RyYWNrUG9pbnRFeHRlbnNpb24vdjEiDQogIHhtbG5zPSJodHRwOi8vd3d3LnRvcG9ncmFmaXguY29tL0dQWC8xLzEiDQogIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zOm5zMj0iaHR0cDovL3d3dy5nYXJtaW4uY29tL3htbHNjaGVtYXMvR3B4RXh0ZW5zaW9ucy92MyI+DQogIDxtZXRhZGF0YT4NCiAgICA8bGluayBocmVmPSJjb25uZWN0Lmdhcm1pbi5jb20iPg0KICAgICAgPHRleHQ+R2FybWluIENvbm5lY3Q8L3RleHQ+DQogICAgPC9saW5rPg0KICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDY6NDQuMDAwWjwvdGltZT4NCiAgPC9tZXRhZGF0YT4NCiAgPHRyaz4NCiAgICA8bmFtZT5Icm9kenllbnNraSByYXlvbiDQkdC10LM8L25hbWU+DQogICAgPHR5cGU+cnVubmluZzwvdHlwZT4NCiAgICA8dHJrc2VnPg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjk2MTUzNzk3MjA5MjYyODQ3OTAwMzkwNjI1IiBsb249IjI0LjAxNTAyNjQ4MzY4NDc3ODIxMzUwMDk3NjU2MjUiPg0KICAgICAgICA8ZWxlPjEyNS44MDAwMDMwNTE3NTc4MTI1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDY6NDQuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgDQogICAgICANCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NTY0NDE3NzQ5NjQzMzI1ODA1NjY0MDYyNSIgbG9uPSIyNC4wMTQzODY0NDE1NTg1OTk0NzIwNDU4OTg0Mzc1Ij4NCiAgICAgICAgPGVsZT4xMjg8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowNzoxMy4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjk1NDcyNTk5OTM4NTExODQ4NDQ5NzA3MDMxMjUiIGxvbj0iMjQuMDE0MzE0MjczMzcyMjkyNTE4NjE1NzIyNjU2MjUiPg0KICAgICAgICA8ZWxlPjEyOC4xOTk5OTY5NDgyNDIxODc1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDc6MTkuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NTMwNTI5NzE1MTIwNzkyMzg4OTE2MDE1NjI1IiBsb249IjI0LjAxNDIwOTU4MzQwMTY3OTk5MjY3NTc4MTI1Ij4NCiAgICAgICAgPGVsZT4xMjguMTk5OTk2OTQ4MjQyMTg3NTwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA3OjI1LjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTUxMjEwNjI5MTk0OTc0ODk5MjkxOTkyMTg3NSIgbG9uPSIyNC4wMTQxMTExNzk4NTg0NDYxMjEyMTU4MjAzMTI1Ij4NCiAgICAgICAgPGVsZT4xMjguMTk5OTk2OTQ4MjQyMTg3NTwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA3OjMyLjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTQ5Nzk3NDQwMzIwMjUzMzcyMTkyMzgyODEyNSIgbG9uPSIyNC4wMTQxNjAzODE2MzAwNjMwNTY5NDU4MDA3ODEyNSI+DQogICAgICAgIDxlbGU+MTI4PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDc6MzcuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDgxNzk3MzMwMDgxNDYyODYwMTA3NDIxODc1IiBsb249IjI0LjAxNDI2Mjk3NjEyNDg4MjY5ODA1OTA4MjAzMTI1Ij4NCiAgICAgICAgPGVsZT4xMjcuODAwMDAzMDUxNzU3ODEyNTwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA3OjQzLjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTQ3MDk3NjI5MzA4NzAwNTYxNTIzNDM3NSIgbG9uPSIyNC4wMTQzODc4NjY0ODIxMzg2MzM3MjgwMjczNDM3NSI+DQogICAgICAgIDxlbGU+MTI3LjQwMDAwMTUyNTg3ODkwNjI1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDc6NDguMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDY4MDY3NzcyNjg2NDgxNDc1ODMwMDc4MTI1IiBsb249IjI0LjAxNDcyNjE2MDA5NDE0MTk2MDE0NDA0Mjk2ODc1Ij4NCiAgICAgICAgPGVsZT4xMjYuNTk5OTk4NDc0MTIxMDkzNzU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowNzo1NC4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjk0Njc2NjU0NDEzMzQyNDc1ODkxMTEzMjgxMjUiIGxvbj0iMjQuMDE1MDg1MDczMTg3OTQ3MjczMjU0Mzk0NTMxMjUiPg0KICAgICAgICA8ZWxlPjEyNi4xOTk5OTY5NDgyNDIxODc1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDg6MDAuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDY2MTY1MDgwNjY2NTQyMDUzMjIyNjU2MjUiIGxvbj0iMjQuMDE1NDcxOTgxODM4MzQ1NTI3NjQ4OTI1NzgxMjUiPg0KICAgICAgICA8ZWxlPjEyNjwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA4OjA3LjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTQ2NTQzNTg1NTA5MDYxODEzMzU0NDkyMTg3NSIgbG9uPSIyNC4wMTU2Nzk2ODUzOTg5MzYyNzE2Njc0ODA0Njg3NSI+DQogICAgICAgIDxlbGU+MTI1LjgwMDAwMzA1MTc1NzgxMjU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowODoxMS4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjk0NjMxMDU2ODYwMDg5MzAyMDYyOTg4MjgxMjUiIGxvbj0iMjQuMDE1OTM5MjcyOTQwMTU4ODQzOTk0MTQwNjI1Ij4NCiAgICAgICAgPGVsZT4xMjUuNDAwMDAxNTI1ODc4OTA2MjU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowODoxNi4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjk0NjEwMjY5NzQwMjIzODg0NTgyNTE5NTMxMjUiIGxvbj0iMjQuMDE2MTg1MzY1NjE3Mjc1MjM4MDM3MTA5Mzc1Ij4NCiAgICAgICAgPGVsZT4xMjUuMTk5OTk2OTQ4MjQyMTg3NTwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA4OjIxLjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTQ1OTU4NTI4NjY3Njg4MzY5NzUwOTc2NTYyNSIgbG9uPSIyNC4wMTYzMTY0NTg1ODI4NzgxMTI3OTI5Njg3NSI+DQogICAgICAgIDxlbGU+MTI1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDg6MjQuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDUwNzMzOTk2OTI3NzM4MTg5Njk3MjY1NjI1IiBsb249IjI0LjAxNjM2MTU1MzIyMTk0MDk5NDI2MjY5NTMxMjUiPg0KICAgICAgICA8ZWxlPjEyNC44MDAwMDMwNTE3NTc4MTI1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDg6MjguMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDI5NzQ1NzExMzg2MjAzNzY1ODY5MTQwNjI1IiBsb249IjI0LjAxNjI0Njg4ODc4NjU1NDMzNjU0Nzg1MTU2MjUiPg0KICAgICAgICA8ZWxlPjEyNC44MDAwMDMwNTE3NTc4MTI1PC9lbGU+DQogICAgICAgIDx0aW1lPjIwMTgtMTAtMjZUMTU6MDg6MzYuMDAwWjwvdGltZT4NCiAgICAgICAgPGV4dGVuc2lvbnM+DQogICAgICAgICAgPG5zMzpUcmFja1BvaW50RXh0ZW5zaW9uLz4NCiAgICAgICAgPC9leHRlbnNpb25zPg0KICAgICAgPC90cmtwdD4NCiAgICAgIDx0cmtwdCBsYXQ9IjUzLjY5NDEyMDE3OTg2MTc4Mzk4MTMyMzI0MjE4NzUiIGxvbj0iMjQuMDE2MTY4NDM0MTcyODY4NzI4NjM3Njk1MzEyNSI+DQogICAgICAgIDxlbGU+MTI1LjE5OTk5Njk0ODI0MjE4NzU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowODo0MC4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjkzOTE1OTk2NzAwNTI1MjgzODEzNDc2NTYyNSIgbG9uPSIyNC4wMTYwOTA5ODUzODc1NjM3MDU0NDQzMzU5Mzc1Ij4NCiAgICAgICAgPGVsZT4xMjUuNTk5OTk4NDc0MTIxMDkzNzU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowODo0OC4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjkzNzAzMjYzOTk4MDMxNjE2MjEwOTM3NSIgbG9uPSIyNC4wMTYwNDI0NTQxNjgyMDA0OTI4NTg4ODY3MTg3NSI+DQogICAgICAgIDxlbGU+MTI1LjgwMDAwMzA1MTc1NzgxMjU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTowODo1Ni4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgICAgPHRya3B0IGxhdD0iNTMuNjkzNTM0Mjg0ODMwMDkzMzgzNzg5MDYyNSIgbG9uPSIyNC4wMTU5ODU1NDEwNDU2NjU3NDA5NjY3OTY4NzUiPg0KICAgICAgICA8ZWxlPjEyNjwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjA5OjAyLjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICANCiAgICAgDQogICAgICA8dHJrcHQgbGF0PSI1My42OTYyMDMzMzQyNTcwMDY2NDUyMDI2MzY3MTg3NSIgbG9uPSIyNC4wMTUxOTcxMzkyMzMzNTA3NTM3ODQxNzk2ODc1Ij4NCiAgICAgICAgPGVsZT4xMjUuMTk5OTk2OTQ4MjQyMTg3NTwvZWxlPg0KICAgICAgICA8dGltZT4yMDE4LTEwLTI2VDE1OjQyOjA2LjAwMFo8L3RpbWU+DQogICAgICAgIDxleHRlbnNpb25zPg0KICAgICAgICAgIDxuczM6VHJhY2tQb2ludEV4dGVuc2lvbi8+DQogICAgICAgIDwvZXh0ZW5zaW9ucz4NCiAgICAgIDwvdHJrcHQ+DQogICAgICA8dHJrcHQgbGF0PSI1My42OTYyMTMzOTI1NDA4MTI0OTIzNzA2MDU0Njg3NSIgbG9uPSIyNC4wMTUyMjk2NjEwMTc2NTYzMjYyOTM5NDUzMTI1Ij4NCiAgICAgICAgPGVsZT4xMjU8L2VsZT4NCiAgICAgICAgPHRpbWU+MjAxOC0xMC0yNlQxNTo0MjowNy4wMDBaPC90aW1lPg0KICAgICAgICA8ZXh0ZW5zaW9ucz4NCiAgICAgICAgICA8bnMzOlRyYWNrUG9pbnRFeHRlbnNpb24vPg0KICAgICAgICA8L2V4dGVuc2lvbnM+DQogICAgICA8L3Rya3B0Pg0KICAgIDwvdHJrc2VnPg0KICA8L3Ryaz4NCjwvZ3B4Pg0K', 53.683742, 23.997510, 53.697090, 24.028666, '2018-11-22 21:20:36.334+03', '2018-11-22 21:20:36.334+03');


--
-- TOC entry 2928 (class 0 OID 31636)
-- Dependencies: 216
-- Data for Name: map_2_route; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2923 (class 0 OID 31601)
-- Dependencies: 211
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (id, name, info, created, updated) VALUES (1, '1', '1', '2018-11-20 21:23:07.279+03', '2018-11-20 21:23:07.279+03');


--
-- TOC entry 2921 (class 0 OID 31590)
-- Dependencies: 209
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2910 (class 0 OID 31531)
-- Dependencies: 198
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2914 (class 0 OID 31557)
-- Dependencies: 202
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (1, '1@list.ru', 'qwewrwr', 'USER', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (2, '2@list.ru', 'qwewrwr', 'USER', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (3, '3@list.ru', 'qwewrwr', 'ORGANIZER', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (4, '4@list.ru', 'qwewrwr', 'USER', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (5, '5@list.ru', 'qwewrwr', 'USER', '2017-10-25 00:00:00+03', '2017-10-25 00:00:00+03');
INSERT INTO public.user_account (id, email, password, role, created, updated) VALUES (6, 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'ADMIN', '2018-11-15 19:04:09.358+03', '2018-11-15 19:04:09.358+03');


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 212
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_id_seq', 10, true);


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 214
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 4, true);


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 205
-- Name: customer_2_event_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_2_event_customer_id_seq', 1, false);


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 206
-- Name: customer_2_event_event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_2_event_event_id_seq', 1, false);


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 203
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 2, true);


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 199
-- Name: map_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.map_id_seq', 7, true);


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 210
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_id_seq', 1, true);


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 208
-- Name: point_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.point_id_seq', 1, false);


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 197
-- Name: route_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.route_id_seq', 1, false);


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 6, true);


--
-- TOC entry 2770 (class 2606 OID 31622)
-- Name: city city_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- TOC entry 2772 (class 2606 OID 31635)
-- Name: country country_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_name_key UNIQUE (name);


--
-- TOC entry 2774 (class 2606 OID 31633)
-- Name: country country_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 31528)
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (id);


--
-- TOC entry 2762 (class 2606 OID 31578)
-- Name: event event_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pk PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 31554)
-- Name: map map_path_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map
    ADD CONSTRAINT map_path_key UNIQUE (path);


--
-- TOC entry 2756 (class 2606 OID 31552)
-- Name: map map_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map
    ADD CONSTRAINT map_pk PRIMARY KEY (id);


--
-- TOC entry 2766 (class 2606 OID 31611)
-- Name: news news_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_name_key UNIQUE (name);


--
-- TOC entry 2768 (class 2606 OID 31609)
-- Name: news news_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pk PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 31598)
-- Name: point point_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point
    ADD CONSTRAINT point_pk PRIMARY KEY (id);


--
-- TOC entry 2750 (class 2606 OID 31541)
-- Name: route route_path_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_path_key UNIQUE (path);


--
-- TOC entry 2752 (class 2606 OID 31539)
-- Name: route route_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pk PRIMARY KEY (id);


--
-- TOC entry 2758 (class 2606 OID 31567)
-- Name: user_account user_account_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_email_key UNIQUE (email);


--
-- TOC entry 2760 (class 2606 OID 31565)
-- Name: user_account user_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 31684)
-- Name: city city_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_fk0 FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- TOC entry 2781 (class 2606 OID 31669)
-- Name: customer_2_event customer_2_event_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_2_event
    ADD CONSTRAINT customer_2_event_fk0 FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 2782 (class 2606 OID 31674)
-- Name: customer_2_event customer_2_event_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_2_event
    ADD CONSTRAINT customer_2_event_fk1 FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- TOC entry 2775 (class 2606 OID 31639)
-- Name: customer customer_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_fk0 FOREIGN KEY (id) REFERENCES public.user_account(id);


--
-- TOC entry 2776 (class 2606 OID 31644)
-- Name: customer customer_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_fk1 FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- TOC entry 2779 (class 2606 OID 31659)
-- Name: event event_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk0 FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 2780 (class 2606 OID 31664)
-- Name: event event_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk1 FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- TOC entry 2785 (class 2606 OID 31689)
-- Name: map_2_route map_2_route_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map_2_route
    ADD CONSTRAINT map_2_route_fk0 FOREIGN KEY (map_id) REFERENCES public.map(id);


--
-- TOC entry 2786 (class 2606 OID 31694)
-- Name: map_2_route map_2_route_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map_2_route
    ADD CONSTRAINT map_2_route_fk1 FOREIGN KEY (route_id) REFERENCES public.route(id);


--
-- TOC entry 2778 (class 2606 OID 31654)
-- Name: map map_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.map
    ADD CONSTRAINT map_fk0 FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 2783 (class 2606 OID 31679)
-- Name: point point_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point
    ADD CONSTRAINT point_fk0 FOREIGN KEY (route_id) REFERENCES public.route(id);


--
-- TOC entry 2777 (class 2606 OID 31649)
-- Name: route route_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_fk0 FOREIGN KEY (customer_id) REFERENCES public.customer(id);


-- Completed on 2018-11-27 18:52:32

--
-- PostgreSQL database dump complete
--

