package gestioneLibri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class JSONReader {
	
public static final String JSON_FILE="libri.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
    	ArrayList<Libro> libri;
        
        InputStream input = new FileInputStream(JSON_FILE); //serve a leggere i byte da un file
        
        JsonReader jsonReader = Json.createReader(input); //crea un JSON reader da uno stream di byte
        
        JsonObject jsonObject = jsonReader.readObject(); //legge un oggetto json
        
        jsonReader.close(); //chiude il reader
        
        JsonObject innerJsonObject = jsonObject.getJsonObject("libreria"); //restituisce il valore dell'oggetto json "libreria"
        
        JsonArray jsonArray = innerJsonObject.getJsonArray("libri"); //restituisce il valore dell'array "libreria"
        
        
        libri = new ArrayList<Libro>(); //array di tipo Libro
        
        int index = 0; 
        
        for (JsonValue element : jsonArray) {
            Libro libro = new Libro(); //istanzio un oggetto di tipo Libro
            
            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());
            
            libri.add(libro);    
        }
        
        for (Libro libro : libri) {
            System.out.println(libro);
        }
       
    }

}
