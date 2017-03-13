import java.util.Scanner;

/**
 * Created by 20464654j on 06/03/17.
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Gestor gestor = new Gestor();
        int i;

        do{
            i = -1;
            System.out.println("Tria una consulta a realizar;");
            System.out.println("1-Quins països hi ha en el fitxer factbook.xml?");
            System.out.println("2-Quants paisos hi ha?");
            System.out.println("3-Quina es la informacio sobre Alemanya?");
            System.out.println("4-Quanta gent viu a Uganda segons aquest fitxer?");
            System.out.println("5-Quines son les ciutats de Perú que recull aquest fitxer?");
            System.out.println("6-Quanta gent viu a Shangai?");
            System.out.println("7-Quin es el codi de matricula dels cotxes a Xipre");
            System.out.println("0-Sortir");

            //try/catch per a assegurar que es selecciona un int
            try{
                i = in.nextInt();
            }catch (Exception e ){
                System.out.println("Deus introduir un numero");
                in.next();
            }

        }while( !menuOptions( gestor, i ) );

        System.out.println("ByeBye!!");

    }

    /** Opcions del menu
     *
     * @param g gestor de la base de dades xml
     * @param opcio opcio elegida
     * @return boolean per a sortir del menu i acabar el programa o no
     */
    private static boolean menuOptions( Gestor g, int opcio ){

        switch ( opcio ){
            case 1 : g.resp1();
                break;
            case 2 : g.resp2();
                break;
            case 3 : g.resp3();
                break;
            case 4 : g.resp4();
                break;
            case 5 : g.resp5();
                break;
            case 6 : g.resp6();
                break;
            case 7 : g.resp7();
                break;
            case 0 : return true;
            default:
                System.out.println("Opcio incorrecta. Torna a elegir");
                break;
        }

        System.out.println();

        return false;
    }
}
