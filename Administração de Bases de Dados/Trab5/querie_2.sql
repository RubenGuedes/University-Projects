SELECT  "Ciclista"."Cartão cidadão do ciclista", 
        "Ciclista"."Nome do ciclista", 
        "Acidente"."Local_Acidente"
FROM "Acidente" INNER JOIN "Ciclista" 
ON "Ciclista"."Cartão cidadão do ciclista" = "Acidente"."Cartão_Cidadão_Ciclista"
WHERE "Ciclista"."Nome do ciclista" LIKE 'P%';