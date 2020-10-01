import Exame_Desportivo.*;

import java.util.HashMap;

public class Software {
    public static HashMap<Integer, Praticante_Desportivo>             hash_praticante     = new HashMap<>();
    public static HashMap<Integer, Antecedente_Familiares>            hash_familiar       = new HashMap<>();
    public static HashMap<Integer, Antecedente_Pessoais>              hash_pessoais       = new HashMap<>();
    public static HashMap<Integer, Antecedentes_Desportivos>          hash_desportivo     = new HashMap<>();
    public static HashMap<Integer, Dados_Pessoais>                    hash_dados_pessoais = new HashMap<>();
    public static HashMap<Integer, Declaracoes_Pessoais>              hash_declaracoes    = new HashMap<>();
    public static HashMap<Integer, Exame_Abdomen>                     hash_abdomen        = new HashMap<>();
    public static HashMap<Integer, Exame_Biometrico>                  hash_biometrico     = new HashMap<>();
    public static HashMap<Integer, Exame_CardioCirculRespi>           hash_cardio         = new HashMap<>();
    public static HashMap<Integer, Exame_Ectoscopico>                 hash_ectoscopico    = new HashMap<>();
    public static HashMap<Integer, Exame_Estomatologico>              hash_estomatologico = new HashMap<>();
    public static HashMap<Integer, Exame_Genito_Urinario>             hash_genito         = new HashMap<>();
    public static HashMap<Integer, Exame_Medico_Desportivo>           hash_exame_medico   = new HashMap<>();
    public static HashMap<Integer, Exame_Oftalmologico>               hash_oftalmologico  = new HashMap<>();
    public static HashMap<Integer, Exame_ORI>                         hash_ori            = new HashMap<>();
    public static HashMap<Integer, Exames_Complementares_Diagnostico> hash_complementar   = new HashMap<>();

    public  static void main(String[] args)
    {
        // pessoa_1
            Praticante_Desportivo               praticante1             = new Praticante_Desportivo( "Rafael", 123456, 983127627, 1);
            Antecedente_Familiares              ante_familiar1          = new Antecedente_Familiares(false, false, false, true, false, true, false, false, false, false);
            Antecedente_Pessoais                ante_pessoais1          = new Antecedente_Pessoais(true, false, false, false, false, false,false, false, false, false,false,false, false, false);
            Antecedentes_Desportivos            ante_despor1            = new Antecedentes_Desportivos(true, true, false, "");
            Dados_Pessoais                      dados_pessoais1         = new Dados_Pessoais( praticante1.getExame_n(), "10/02/2018", praticante1.getNome(), "01/09/1990", "rua da marquesa", "650", "Évora", praticante1.getTelemovel(), "indefinido", "juíz", "0",  "Manuela");
            Declaracoes_Pessoais                declaracoes1            = new Declaracoes_Pessoais( false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, "passou");
            Exame_Abdomen                       exame_abdomen1          = new Exame_Abdomen( false, "nada a declarar");
            Exame_Biometrico                    exame_biometrico1       = new Exame_Biometrico( 1.70f, 20.2f);
            Exame_CardioCirculRespi             exame_cardio1           = new Exame_CardioCirculRespi( true, true, true, true, 70.3f, 12.3f);
            Exame_Ectoscopico                   exame_ectoscopico1      = new Exame_Ectoscopico(false, false, false, false, false, false,false,false);
            Exame_Estomatologico                exame_estoma1           = new Exame_Estomatologico(true, false);
            Exame_Genito_Urinario               exame_genito1           = new Exame_Genito_Urinario(0 ,false, false);
            Exame_Medico_Desportivo             ex_desportivo1          = new Exame_Medico_Desportivo(praticante1.getExame_n(), dados_pessoais1.getData_exame(), "regular");
            Exame_Oftalmologico                 exame_oftalmol1         = new Exame_Oftalmologico( 0, 0, 0,0 );
            Exame_ORI                           exame_ori1              = new Exame_ORI(true, false);
            Exames_Complementares_Diagnostico   exames_complementares1  = new Exames_Complementares_Diagnostico(true, true, "nada a declarar");

        // pessoa_2
            Praticante_Desportivo               praticante2             = new Praticante_Desportivo( "Rita", 1452456, 983127627, 2);
            Antecedente_Familiares              ante_familiar2          = new Antecedente_Familiares(false, false, false, true, false, true, false, false, false, false);
            Antecedente_Pessoais                ante_pessoais2          = new Antecedente_Pessoais(true, false, false, false, false, false,false, false, false, false,false,false, false, false);
            Antecedentes_Desportivos            ante_despor2            = new Antecedentes_Desportivos(true, true, false, "");
            Dados_Pessoais                      dados_pessoais2         = new Dados_Pessoais( praticante2.getExame_n(), "10/02/2018", praticante2.getNome(), "01/09/1990", "rua da marquesa", "650", "Évora", praticante2.getTelemovel(), "indefinido", "juíz", "0",  "Manuela");
            Declaracoes_Pessoais                declaracoes2            = new Declaracoes_Pessoais( false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, "passou");
            Exame_Abdomen                       exame_abdomen2          = new Exame_Abdomen( false, "nada a declarar");
            Exame_Biometrico                    exame_biometrico2       = new Exame_Biometrico( 1.70f, 20.2f);
            Exame_CardioCirculRespi             exame_cardio2           = new Exame_CardioCirculRespi( true, true, true, true, 70.3f, 12.3f);
            Exame_Ectoscopico                   exame_ectoscopico2      = new Exame_Ectoscopico(false, false, false, false, false, false,false,false);
            Exame_Estomatologico                exame_estoma2           = new Exame_Estomatologico(true, false);
            Exame_Genito_Urinario               exame_genito2           = new Exame_Genito_Urinario(0 ,false, false);
            Exame_Medico_Desportivo             ex_desportivo2          = new Exame_Medico_Desportivo(praticante2.getExame_n(), dados_pessoais2.getData_exame(), "regular");
            Exame_Oftalmologico                 exame_oftalmol2         = new Exame_Oftalmologico( 0, 0, 0,0 );
            Exame_ORI                           exame_ori2              = new Exame_ORI(true, false);
            Exames_Complementares_Diagnostico   exames_complementares2  = new Exames_Complementares_Diagnostico(true, true, "nada a declarar");

        // pessoa_3
            Praticante_Desportivo               praticante3             = new Praticante_Desportivo( "Eduardo", 123216, 983127627, 3);
            Antecedente_Familiares              ante_familiar3          = new Antecedente_Familiares(false, false, false, true, false, true, false, false, false, false);
            Antecedente_Pessoais                ante_pessoais3          = new Antecedente_Pessoais(true, false, false, false, false, false,false, false, false, false,false,false, false, false);
            Antecedentes_Desportivos            ante_despor3            = new Antecedentes_Desportivos(true, true, false, "");
            Dados_Pessoais                      dados_pessoais3         = new Dados_Pessoais( praticante3.getExame_n(), "10/02/2018", praticante3.getNome(), "01/09/1990", "rua da marquesa", "650", "Évora", praticante3.getTelemovel(), "indefinido", "juíz", "0",  "Manuela");
            Declaracoes_Pessoais                declaracoes3            = new Declaracoes_Pessoais( false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, "passou");
            Exame_Abdomen                       exame_abdomen3          = new Exame_Abdomen( false, "nada a declarar");
            Exame_Biometrico                    exame_biometrico3       = new Exame_Biometrico( 1.70f, 20.2f);
            Exame_CardioCirculRespi             exame_cardio3           = new Exame_CardioCirculRespi( true, true, true, true, 70.3f, 12.3f);
            Exame_Ectoscopico                   exame_ectoscopico3      = new Exame_Ectoscopico(false, false, false, false, false, false,false,false);
            Exame_Estomatologico                exame_estoma3           = new Exame_Estomatologico(true, false);
            Exame_Genito_Urinario               exame_genito3           = new Exame_Genito_Urinario(0 ,false, false);
            Exame_Medico_Desportivo             ex_desportivo3          = new Exame_Medico_Desportivo(praticante3.getExame_n(), dados_pessoais3.getData_exame(), "regular");
            Exame_Oftalmologico                 exame_oftalmol3         = new Exame_Oftalmologico( 0, 0, 0,0 );
            Exame_ORI                           exame_ori3              = new Exame_ORI(true, false);
            Exames_Complementares_Diagnostico   exames_complementares3  = new Exames_Complementares_Diagnostico(true, true, "nada a declarar");

        // pessoa_4
            Praticante_Desportivo               praticante4             = new Praticante_Desportivo( "Joana", 123452, 983123627, 4);
            Antecedente_Familiares              ante_familiar4          = new Antecedente_Familiares(false, false, false, true, false, true, false, false, false, false);
            Antecedente_Pessoais                ante_pessoais4          = new Antecedente_Pessoais(true, false, false, false, false, false,false, false, false, false,false,false, false, false);
            Antecedentes_Desportivos            ante_despor4            = new Antecedentes_Desportivos(true, true, false, "");
            Dados_Pessoais                      dados_pessoais4         = new Dados_Pessoais( praticante4.getExame_n(), "10/02/2018", praticante4.getNome(), "01/09/1990", "rua da marquesa", "650", "Évora", praticante4.getTelemovel(), "indefinido", "juíz", "0",  "Manuela");
            Declaracoes_Pessoais                declaracoes4            = new Declaracoes_Pessoais( false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, "passou");
            Exame_Abdomen                       exame_abdomen4          = new Exame_Abdomen( false, "nada a declarar");
            Exame_Biometrico                    exame_biometrico4       = new Exame_Biometrico( 1.70f, 20.2f);
            Exame_CardioCirculRespi             exame_cardio4           = new Exame_CardioCirculRespi( true, true, true, true, 70.3f, 12.3f);
            Exame_Ectoscopico                   exame_ectoscopico4      = new Exame_Ectoscopico(false, false, false, false, false, false,false,false);
            Exame_Estomatologico                exame_estoma4           = new Exame_Estomatologico(true, false);
            Exame_Genito_Urinario               exame_genito4           = new Exame_Genito_Urinario(0 ,false, false);
            Exame_Medico_Desportivo             ex_desportivo4          = new Exame_Medico_Desportivo(praticante4.getExame_n(), dados_pessoais4.getData_exame(), "regular");
            Exame_Oftalmologico                 exame_oftalmol4         = new Exame_Oftalmologico( 0, 0, 0,0 );
            Exame_ORI                           exame_ori4              = new Exame_ORI(true, false);
            Exames_Complementares_Diagnostico   exames_complementares4  = new Exames_Complementares_Diagnostico(true, true, "nada a declarar");

        // Colocar os conteudos nas respetivas hashtables
        colocar_praticantes(        praticante1.getExame_n(), praticante1, praticante2.getExame_n(), praticante2, praticante3.getExame_n(), praticante3, praticante4.getExame_n(), praticante4);

        colocar_ante_familiar(      praticante1.getExame_n(), ante_familiar1, praticante2.getExame_n(), ante_familiar2, praticante3.getExame_n(), ante_familiar3, praticante4.getExame_n(), ante_familiar4);

        colocar_ante_pessoal(       praticante1.getExame_n(), ante_pessoais1, praticante2.getExame_n(), ante_pessoais2, praticante3.getExame_n(), ante_pessoais3, praticante4.getExame_n(), ante_pessoais4);

        colocar_ante_desportivo(    praticante1.getExame_n(), ante_despor1, praticante2.getExame_n(), ante_despor2, praticante3.getExame_n(), ante_despor3, praticante4.getExame_n(), ante_despor4);

        colocar_dados_pessoais(     praticante1.getExame_n(), dados_pessoais1, praticante2.getExame_n(), dados_pessoais2, praticante3.getExame_n(), dados_pessoais3, praticante4.getExame_n(), dados_pessoais4);
        
        colocar_declaracoes(        praticante1.getExame_n(), declaracoes1, praticante2.getExame_n(), declaracoes2, praticante3.getExame_n(), declaracoes3, praticante4.getExame_n(), declaracoes4);

        colocar_exame_abdomen(      praticante1.getExame_n(), exame_abdomen1, praticante2.getExame_n(), exame_abdomen2, praticante3.getExame_n(), exame_abdomen3, praticante4.getExame_n(), exame_abdomen4);

        colocar_exame_biometrico(   praticante1.getExame_n(), exame_biometrico1, praticante2.getExame_n(), exame_biometrico2, praticante3.getExame_n(), exame_biometrico3, praticante4.getExame_n(), exame_biometrico4 );
        
        colocar_cardio(                 praticante1.getExame_n(), exame_cardio1, praticante2.getExame_n(), exame_cardio2, praticante3.getExame_n(), exame_cardio3, praticante4.getExame_n(), exame_cardio4);

        colocar_ectoscopico(        praticante1.getExame_n(), exame_ectoscopico1, praticante2.getExame_n(), exame_ectoscopico2, praticante3.getExame_n(), exame_ectoscopico3, praticante4.getExame_n(), exame_ectoscopico4);

        colocar_estomatologico(     praticante1.getExame_n(), exame_estoma1, praticante2.getExame_n(), exame_estoma2, praticante3.getExame_n(), exame_estoma3, praticante4.getExame_n(), exame_estoma4 );

        colocar_exame_genito(       praticante1.getExame_n(), exame_genito1, praticante2.getExame_n(), exame_genito2, praticante3.getExame_n(), exame_genito3, praticante4.getExame_n(), exame_genito4 );

        colocar_exame_medico(       praticante1.getExame_n(), ex_desportivo1, praticante2.getExame_n(), ex_desportivo2, praticante3.getExame_n(), ex_desportivo3, praticante4.getExame_n(), ex_desportivo4 );
                        
        colocar_oftalmologico(      praticante1.getExame_n(), exame_oftalmol1, praticante2.getExame_n(), exame_oftalmol2, praticante3.getExame_n(), exame_oftalmol3, praticante4.getExame_n(), exame_oftalmol4 );

        colocar_ori(                praticante1.getExame_n(), exame_ori1, praticante2.getExame_n(), exame_ori2, praticante3.getExame_n(), exame_ori3, praticante4.getExame_n(), exame_ori4 );

        colocar_complementar(       praticante1.getExame_n(), exames_complementares1, praticante2.getExame_n(), exames_complementares2, praticante3.getExame_n(), exames_complementares3, praticante4.getExame_n(), exames_complementares4 );
                                    
    }
    //                            MÉTODOS
    public static void colocar_praticantes(int e1, Praticante_Desportivo p1,
                                           int e2, Praticante_Desportivo p2,
                                           int e3, Praticante_Desportivo p3,
                                           int e4, Praticante_Desportivo p4){
        hash_praticante.put(e1, p1);
        hash_praticante.put(e2, p2);
        hash_praticante.put(e3, p3);
        hash_praticante.put(e4, p4);
    }

    public static void colocar_ante_familiar(int e1, Antecedente_Familiares p1,
                                             int e2, Antecedente_Familiares p2,
                                             int e3, Antecedente_Familiares p3,
                                             int e4, Antecedente_Familiares p4){
        hash_familiar.put(e1, p1);
        hash_familiar.put(e2, p2);
        hash_familiar.put(e3, p3);
        hash_familiar.put(e4, p4);
    }

    public static void colocar_ante_pessoal( int e1, Antecedente_Pessoais p1,
                                             int e2, Antecedente_Pessoais p2,
                                             int e3, Antecedente_Pessoais p3,
                                             int e4, Antecedente_Pessoais p4){
        hash_pessoais.put(e1, p1);
        hash_pessoais.put(e2, p2);
        hash_pessoais.put(e3, p3);
        hash_pessoais.put(e4, p4);
    }

    public static void colocar_ante_desportivo( int e1, Antecedentes_Desportivos p1,
                                                int e2, Antecedentes_Desportivos p2,
                                                int e3, Antecedentes_Desportivos p3,
                                                int e4, Antecedentes_Desportivos p4){
        hash_desportivo.put(e1, p1);
        hash_desportivo.put(e2, p2);
        hash_desportivo.put(e3, p3);
        hash_desportivo.put(e4, p4);
    }

    public static void colocar_dados_pessoais( int e1, Dados_Pessoais p1,
                                                int e2, Dados_Pessoais p2,
                                                int e3, Dados_Pessoais p3,
                                                int e4, Dados_Pessoais p4){

        hash_dados_pessoais.put(e1, p1);
        hash_dados_pessoais.put(e2, p2);
        hash_dados_pessoais.put(e3, p3);
        hash_dados_pessoais.put(e4, p4);
    }

    public static void colocar_declaracoes( int e1, Declaracoes_Pessoais p1,
                                            int e2, Declaracoes_Pessoais p2,
                                            int e3, Declaracoes_Pessoais p3,
                                            int e4, Declaracoes_Pessoais p4){

        hash_declaracoes.put(e1, p1);
        hash_declaracoes.put(e2, p2);
        hash_declaracoes.put(e3, p3);
        hash_declaracoes.put(e4, p4);
    }

    public static void colocar_exame_abdomen( int e1, Exame_Abdomen p1,
                                            int e2, Exame_Abdomen p2,
                                            int e3, Exame_Abdomen p3,
                                            int e4, Exame_Abdomen p4){

        hash_abdomen.put(e1, p1);
        hash_abdomen.put(e2, p2);
        hash_abdomen.put(e3, p3);
        hash_abdomen.put(e4, p4);
    }

    public static void colocar_exame_biometrico(    int e1, Exame_Biometrico p1,
                                                    int e2, Exame_Biometrico p2,
                                                    int e3, Exame_Biometrico p3,
                                                    int e4, Exame_Biometrico p4){

        hash_biometrico.put(e1, p1);
        hash_biometrico.put(e2, p2);
        hash_biometrico.put(e3, p3);
        hash_biometrico.put(e4, p4);
    }

    public static void colocar_cardio(      int e1, Exame_CardioCirculRespi p1,
                                            int e2, Exame_CardioCirculRespi p2,
                                            int e3, Exame_CardioCirculRespi p3,
                                            int e4, Exame_CardioCirculRespi p4){

        hash_cardio.put(e1, p1);
        hash_cardio.put(e2, p2);
        hash_cardio.put(e3, p3);
        hash_cardio.put(e4, p4);
    }

    public static void colocar_ectoscopico(         int e1, Exame_Ectoscopico p1,
                                                    int e2, Exame_Ectoscopico p2,
                                                    int e3, Exame_Ectoscopico p3,
                                                    int e4, Exame_Ectoscopico p4){

        hash_ectoscopico.put(e1, p1);
        hash_ectoscopico.put(e2, p2);
        hash_ectoscopico.put(e3, p3);
        hash_ectoscopico.put(e4, p4);
    }

    public static void colocar_estomatologico(  int e1, Exame_Estomatologico p1,
                                                int e2, Exame_Estomatologico p2,
                                                int e3, Exame_Estomatologico p3,
                                                int e4, Exame_Estomatologico p4){

        hash_estomatologico.put(e1, p1);
        hash_estomatologico.put(e2, p2);
        hash_estomatologico.put(e3, p3);
        hash_estomatologico.put(e4, p4);
    }

    public static void colocar_exame_genito(    int e1, Exame_Genito_Urinario p1,
                                                int e2, Exame_Genito_Urinario p2,
                                                int e3, Exame_Genito_Urinario p3,
                                                int e4, Exame_Genito_Urinario p4){

        hash_genito.put(e1, p1);
        hash_genito.put(e2, p2);
        hash_genito.put(e3, p3);
        hash_genito.put(e4, p4);
    }

    public static void colocar_exame_medico(    int e1, Exame_Medico_Desportivo p1,
                                                int e2, Exame_Medico_Desportivo p2,
                                                int e3, Exame_Medico_Desportivo p3,
                                                int e4, Exame_Medico_Desportivo p4){

        hash_exame_medico.put(e1, p1);
        hash_exame_medico.put(e2, p2);
        hash_exame_medico.put(e3, p3);
        hash_exame_medico.put(e4, p4);
    }

    public static void colocar_oftalmologico(   int e1, Exame_Oftalmologico p1,
                                                int e2, Exame_Oftalmologico p2,
                                                int e3, Exame_Oftalmologico p3,
                                                int e4, Exame_Oftalmologico p4){

        hash_oftalmologico.put(e1, p1);
        hash_oftalmologico.put(e2, p2);
        hash_oftalmologico.put(e3, p3);
        hash_oftalmologico.put(e4, p4);
    }

    public static void colocar_ori(     int e1, Exame_ORI p1,
                                        int e2, Exame_ORI p2,
                                        int e3, Exame_ORI p3,
                                        int e4, Exame_ORI p4){

        hash_ori.put(e1, p1);
        hash_ori.put(e2, p2);
        hash_ori.put(e3, p3);
        hash_ori.put(e4, p4);
    }

    public static void colocar_complementar(    int e1, Exames_Complementares_Diagnostico p1,
                                                int e2, Exames_Complementares_Diagnostico p2,
                                                int e3, Exames_Complementares_Diagnostico p3,
                                                int e4, Exames_Complementares_Diagnostico p4){

        hash_complementar.put(e1, p1);
        hash_complementar.put(e2, p2);
        hash_complementar.put(e3, p3);
        hash_complementar.put(e4, p4);
    }
}
