--Select matricula
--From autocarro
--Where nMAXpass <= (Select nMAXpass
--                   From carreira
--                   Where cod = 10)

-- 4.2
Select percurso
From carreira natural join paragem
Where nome = "Rossio"

-- 4.3
--Select nome
--From Motorista inner join autocarros
--Where 

-- 4.4
--Select nome
--From Administrativo 
--Where cod = (Select cod
--            From carreira
--            Where carreira.cod = 10)

     