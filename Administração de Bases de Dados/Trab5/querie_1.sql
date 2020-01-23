SELECT  "Acidente"."Longitude", 
        "Acidente"."Latitude", 
        "Ciclista"."Nome do ciclista"
FROM "Acidente" INNER JOIN "Ciclista" 
ON "Ciclista"."Cartão cidadão do ciclista" = "Acidente"."Cartão_Cidadão_Ciclista"
WHERE "Ciclista"."Idade do ciclista" <= 30 AND 
      "Ciclista"."Idade do ciclista" >= 15;