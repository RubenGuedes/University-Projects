SELECT *
FROM acidente FULL OUTER JOIN participação
USING(numero_relatorio)
WHERE valor_danos != Null;