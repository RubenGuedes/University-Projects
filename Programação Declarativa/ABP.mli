(* Arvore binária de tipo genérico *)
type 'a abp =
  | Folha
  | No of 'a abp * 'a * 'a abp
;;
(* Function insert *)
let insert valor folha = function 
;;
(* Create an abp *)
let rec abp = function l ->
  let empty = Folha in
                  match l with
                    | []     -> empty
                    | x :: l -> insert x (abp l)
;;
(* Lookup function *)
let rec 'a lookup 'a abp 'a = function 
| Folha -> 
| No (a, b, p) -> 
;;  
(* Function remove *)
let remove abp = function  
| 
|
;;