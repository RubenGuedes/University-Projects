CREATE TABLE "Clients" (
  "username" varchar UNIQUE PRIMARY KEY,
  "password" varchar NOT NULL
);

CREATE TABLE "Needs" (
  "product" varchar NOT NULL,
  "registry_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "username" varchar
);

CREATE TABLE "Available" (
  "product" varchar,
  "store" varchar,
  "quantity" int,
  PRIMARY KEY ("product", "store")
);

ALTER TABLE "Needs" ADD FOREIGN KEY ("username") REFERENCES "Clients" ("username");

