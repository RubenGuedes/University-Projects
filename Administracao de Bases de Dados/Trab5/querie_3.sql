SELECT  "Acidente"."Longitude", 
        "Acidente"."Latitude", 
        Cartao_cidadao_Ciclista, Nome, Idade
FROM Acidente OUTER JOIN Ciclista
GROUP BY faixa_etaria
HAVING local_acidente LIKE 'Intersecção';