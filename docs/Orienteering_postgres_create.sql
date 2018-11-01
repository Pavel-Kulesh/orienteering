CREATE TABLE "customer" (
	"id" integer NOT NULL,
	"name" character varying NOT NULL,
	"surname" character varying NOT NULL,
	"phone" character varying,
	"city_id" integer NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"path" character varying NOT NULL UNIQUE,
	"file" character varying NOT NULL,
	"customer_id" integer NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT route_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "map" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"customer_id" integer NOT NULL,
	"path" character varying NOT NULL UNIQUE,
	"file" character varying NOT NULL,
	"latitude1" DECIMAL,
	"longitude1" DECIMAL,
	"latitude2" DECIMAL,
	"longitude2" DECIMAL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT map_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"email" character varying NOT NULL UNIQUE,
	"password" character varying NOT NULL,
	"role" character varying NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"customer_id" integer NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"country_id" integer NOT NULL,
	"type" character varying NOT NULL,
	"info" TEXT NOT NULL,
	"latitude" DECIMAL NOT NULL,
	"longitude" DECIMAL NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT event_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "customer_2_event" (
	"customer_id" serial NOT NULL,
	"event_id" serial NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "point" (
	"id" serial NOT NULL,
	"route_id" integer NOT NULL,
	"latitude" DECIMAL NOT NULL,
	"longitude" DECIMAL NOT NULL,
	"diff_time" integer,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT point_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "news" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"info" TEXT NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT news_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "city" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"country_id" integer NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "customer" ADD CONSTRAINT "customer_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");
ALTER TABLE "customer" ADD CONSTRAINT "customer_fk1" FOREIGN KEY ("city_id") REFERENCES "city"("id");

ALTER TABLE "route" ADD CONSTRAINT "route_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("id");

ALTER TABLE "map" ADD CONSTRAINT "map_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("id");


ALTER TABLE "event" ADD CONSTRAINT "event_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("id");
ALTER TABLE "event" ADD CONSTRAINT "event_fk1" FOREIGN KEY ("country_id") REFERENCES "country"("id");

ALTER TABLE "customer_2_event" ADD CONSTRAINT "customer_2_event_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("id");
ALTER TABLE "customer_2_event" ADD CONSTRAINT "customer_2_event_fk1" FOREIGN KEY ("event_id") REFERENCES "event"("id");

ALTER TABLE "point" ADD CONSTRAINT "point_fk0" FOREIGN KEY ("route_id") REFERENCES "route"("id");


ALTER TABLE "city" ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");


