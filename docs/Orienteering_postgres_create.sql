CREATE TABLE "user" (
	"id" integer NOT NULL,
	"name" character varying NOT NULL,
	"surname" character varying NOT NULL,
	"phone" character varying NOT NULL,
	"adress" integer NOT NULL,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"user_id" integer NOT NULL,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT route_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "map" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"user_id" integer NOT NULL,
	"image" character varying NOT NULL,
	"size" character varying NOT NULL,
	"coordinate_x1" double NOT NULL,
	"coordinate_y1" double NOT NULL,
	"coordinate_x2" double NOT NULL,
	"coordinate_y2" double NOT NULL,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT map_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"email" character varying NOT NULL UNIQUE,
	"password" character varying NOT NULL,
	"role_id" integer NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"user_id" integer NOT NULL,
	"date" DATE NOT NULL,
	"country_id" integer NOT NULL,
	"type_id" integer NOT NULL,
	"info" TEXT NOT NULL,
	"coordinate_x" double NOT NULL,
	"coordinate_y" double NOT NULL,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT event_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "item_event" (
	"user_id" serial NOT NULL,
	"event_id" serial NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "point_route" (
	"id" serial NOT NULL,
	"route_id" integer NOT NULL,
	"latitude" character varying NOT NULL,
	"longitude" character varying NOT NULL,
	"diff_time" integer NOT NULL,
	CONSTRAINT point_route_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "info_event" (
	"id" serial NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "city" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"country_id" character varying NOT NULL,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" DATE NOT NULL,
	"updated" DATE NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "type" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "role" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");
ALTER TABLE "user" ADD CONSTRAINT "user_fk1" FOREIGN KEY ("adress") REFERENCES "city"("id");

ALTER TABLE "route" ADD CONSTRAINT "route_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");

ALTER TABLE "map" ADD CONSTRAINT "map_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");

ALTER TABLE "user_account" ADD CONSTRAINT "user_account_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");

ALTER TABLE "event" ADD CONSTRAINT "event_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "event" ADD CONSTRAINT "event_fk1" FOREIGN KEY ("country_id") REFERENCES "country"("id");
ALTER TABLE "event" ADD CONSTRAINT "event_fk2" FOREIGN KEY ("type_id") REFERENCES "type"("id");

ALTER TABLE "item_event" ADD CONSTRAINT "item_event_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "item_event" ADD CONSTRAINT "item_event_fk1" FOREIGN KEY ("event_id") REFERENCES "event"("id");

ALTER TABLE "point_route" ADD CONSTRAINT "point_route_fk0" FOREIGN KEY ("route_id") REFERENCES "route"("id");


ALTER TABLE "city" ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");




