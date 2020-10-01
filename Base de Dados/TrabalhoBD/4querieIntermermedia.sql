SELECT endereço, nome, valor_danos
FROM pessoa RIGHT OUTER JOIN participação
USING (id_condutor)
WHERE (nome LIKE 'S%' OR nome LIKE 'H%' OR nome LIKE 'A%') AND VALOR_DANOS> 100;