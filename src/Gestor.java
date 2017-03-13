import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.CreateDB;

import java.io.IOException;

/**
 * Created by 20464654j on 06/03/17.
 */
public class Gestor {

    /* Inicialitzem i tanquem la sessio corresponent a cada pregunta perque si intentem obrir dues sessions a la mateixa
    * base de dades, aunque siguin diferents fitxers, ens diu que la base de dades ja esta oberta en un altre proces
    * Exception: org.basex.core.BaseXException: Database 'input' is currently opened by another process.
    * */

    private ClientSession sessionFb;
    private ClientSession sessionMn;

    public Gestor(){

    }

    // path del fitxer factbook.xml
    private String pathFb = "/home/20464654j/Documents/Factbook.xml";
    //path del fitxer mondial.xml
    private String pathMn = "/home/20464654j/Documents/mondial.xml";

    /**Centralitzem l'inicialitzacio de la sessio del factbook per a evitar codi repetit
     *
     */
    private void initializeFactbook(){

        // creacio del client session
        try {
            sessionFb = new ClientSession("localhost", 1984, "admin", "admin");

            // creem la base de dades
            sessionFb.execute(new CreateDB("input", pathFb));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**Centralitzem el close de la sessio del factbook per a evitar codi repetit
     *
     */
    private void closeFactbook(){

        try {
            sessionFb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**Centralitzem l'inicialitzacio de la sessio del mondial per a evitar codi repetit
     *
     */
    private void initializeMondial(){

        // creacio del client session
        try {
            sessionMn = new ClientSession("localhost", 1984, "admin", "admin");

            // creem la base de dades
            sessionMn.execute(new CreateDB("input", pathMn));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Centralitzem el close de la sessio del factbook per a evitar codi repetit
     *
     */
    private void closeMondial() {
        try {
            sessionMn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** resposta a la pregunta 1 amb el query que extrau el resultat
     *
     */
    public void resp1() {

        // els comentaris de aquest metode expliquen el funcionament des del resp1 fins al resp7

        // inicialitzem la base de dades corresponent
        initializeFactbook();

        // creem la consulta
        try( ClientQuery query = sessionFb.query("//country/text()") ) {

            //imprimim la consulta
            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // finalment, tanquem la base de dades corresponent
            closeFactbook();
        }
    }

    /**resposta a la pregunta 2 amb el query que extrau el resultat
     *
     */
    public void resp2(){

        initializeFactbook();

        try( ClientQuery query = sessionFb.query("count( //country )") ) {

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFactbook();
        }
    }

    /** resposta a la pregunta 3 amb el query que extrau el resultat
     *
     */
    public void resp3(){

        initializeFactbook();

        try( ClientQuery query = sessionFb.query("//record[country=\"Germany\"]") ) {

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFactbook();
        }
    }

    /** resposta a la pregunta 4 amb el query que extrau el resultat
     *
     */
    public void resp4(){

        initializeMondial();

        try( ClientQuery query = sessionMn.query("//country[name=\"Uganda\"]/population[@year=\"2014\"]") ){

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeMondial();
        }
    }

    /**resposta a la pregunta 5 amb el query que extrau el resultat
     *
     */
    public void resp5(){

        initializeMondial();

        try( ClientQuery query = sessionMn.query("//country[name=\"Peru\"]/province/city/name/text()") ){

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeMondial();
        }
    }

    /**resposta a la pregunta 6 amb el query que extrau el resultat
     *
     */
    public void resp6(){

        initializeMondial();

        try( ClientQuery query = sessionMn.query("//country[name=\"China\"]/province/city[name=\"Shanghai\"]/population[@year=\"2010\"]") ){

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeMondial();
        }
    }

    /** resposta a la pregunta 7 amb el query que extrau el resultat
     *
     */
    public void resp7(){

        initializeMondial();

        try( ClientQuery query = sessionMn.query("//country[name=\"Cyprus\"]/@car_code") ){

            printQuery( query );

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeMondial();
        }

    }

    /** Metode per a imprimir el resultat de cada consulta
     *
     * @param q Query a executar i imprimir
     * @throws IOException
     */
    private void printQuery( ClientQuery q ) throws IOException {

        System.out.println( q.execute() );

    }
}