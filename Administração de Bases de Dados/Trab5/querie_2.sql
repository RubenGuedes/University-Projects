SELECT  "Ciclista"."Cart�o cidad�o do ciclista", 
        "Ciclista"."Nome do ciclista", 
        "Acidente"."Local_Acidente"
FROM "Acidente" INNER JOIN "Ciclista" 
ON "Ciclista"."Cart�o cidad�o do ciclista" = "Acidente"."Cart�o_Cidad�o_Ciclista"
WHERE "Ciclista"."Nome do ciclista" LIKE 'P%';