SELECT  "Acidente"."Longitude", 
        "Acidente"."Latitude", 
        "Ciclista"."Nome do ciclista"
FROM "Acidente" INNER JOIN "Ciclista" 
ON "Ciclista"."Cart�o cidad�o do ciclista" = "Acidente"."Cart�o_Cidad�o_Ciclista"
WHERE "Ciclista"."Idade do ciclista" <= 30 AND 
      "Ciclista"."Idade do ciclista" >= 15;