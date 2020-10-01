--Selecionar o niv, o numero de relatorio e o valor dos danos para as pessoas que têm modelo Toyota e com id_condutor = C1700 e C1706 SELECT niv,numero_relatorio, valor_danos
FROM carro LEFT OUTER JOIN participação
USING (niv)
WHERE modelo = 'Toyota' AND (id_condutor = 'C1700' OR id_condutor= 'C1706');