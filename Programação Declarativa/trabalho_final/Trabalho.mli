(**Lista com os setores de cores*)
let lc  = ["red"; "blue"; "orange"; "gray"; "yellow"; "gray"];;

(**********************************************
                  FUNCTIONS
 **********************************************)
(* Function to see the length of a list *)
let rec len_list = function 
  | [] -> 0  
  | _ :: x  -> 1 + len_list x
;;

(* Generate a random number taking in count the length of a list *)
let random_number  list = Random.int (len_list list)
;;

(* Find a element of the list given a certain position *)
let rec find_N_Element pos = function
  | [] -> "null"
  | head :: tail -> if pos = 0 
                      then head
                    else find_N_Element (pos-1) tail
;;

(* Function that gives a random color*)
let random_color list = (find_N_Element (random_number list) list)
;;

(* See if some content is in the list*)
let rec member value = function
  | [] -> false
  | head :: tail -> if value = head then true else member value tail
;;

(* Function exponential *)
let rec power value = function
  | 0 -> 1
  | 1 -> value
  | n -> let b = power value (n/2) in b * b * (if n mod 2 = 0 then 1 else value)
;;

(* Build a line of colors *)
let rec sector_color list value =
  if value = 0 
    then []
  else (random_color list) :: sector_color list (value - 1)
;;  

(* TODO *)
let rec restriction_2 = 0;;
let rec restriction_3 = 0;;
let rec minimize      = 0;;
(* 
            Function paint 
let rec paint coroas setores lista_cor =
  if setores = 0 
    then []
  else sector_color lista_cor coroas :: paint coroas (setores - 1) lista_cor
;;
*)

let rec paint coroas setores lista_cor =
  let list = [];
  if setores = 0 
    then []
  else if setores = 1
    then [(sector_color lista_cor coroas)]
  else 
    let pattern = (sector_color lista_cor coroas) 
    if !(member pattern list) 
      then pattern :: paint coroas (setores - 1) lista_cor
;;