package monitoreo.modelos.Pregunta4;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class ConexionPatronSingleton extends Mongo{

    //DECLARACIONES ESTATICAS PARA LA BD
    static public ConexionPatronSingleton mongoClient = getInstance("localhost",27017);
    static public DB db = mongoClient.getDB("Monitoreo");
    private static ConexionPatronSingleton instance = null;
    public static String ip = "localhost"; public static int port = 27017;

    //DECLARACION DE NUESTRAS COLECCIONES A USAR
    static public DBCollection collection = db.getCollection("Parametros");

    //CONSTRUCTOR PRIVADO P
    private ConexionPatronSingleton(String ip, int port) {
        super(ip, port);                                 }

    //PÁTRON SINGLETON PARA BLOQUEAR LA CREACION DE NUEVAS INSTANCIAS DE CLASE CONEXION
    public static synchronized ConexionPatronSingleton getInstance(String ip, int port) {
        if (instance == null){
            instance =  new ConexionPatronSingleton(ip,port);
                              }
        return instance;
                                                                                         }
                                            }