package api;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import servlet.Pokemon.Pokemon;

public class PokemonApi {

    int idPokemon;
    String namePokemon;
    String imagen;
    ArrayList<Pokemon> lista = new ArrayList<Pokemon>();

    public ArrayList<Pokemon> pokemon(int numero) throws IOException {
        //Creo un for para mostrar solo los 9 primeros pokemones
        for (int i = 1; i <= numero; i++) {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            CloseableHttpResponse respuesta = client.execute(new HttpGet("https://pokeapi.co/api/v2/pokemon/" + i));
            //Guardo el cuerpo del json en un string
            String bodyAsString = EntityUtils.toString(respuesta.getEntity());
            //System.out.println(bodyAsString); 

            //Creo un JSONObject para guardar el archivo json
            JSONObject pokemon = new JSONObject(bodyAsString);
            //Obtengo los valores mediante el nombre del json osea sus filas
            idPokemon = pokemon.getInt("id");
            namePokemon = pokemon.getString("name");
            //Asi obtengo el objeto Sprite de el pokeapi
            JSONObject spritePokemon = pokemon.getJSONObject("sprites");
            imagen = spritePokemon.getString("front_shiny");
            lista.add(new Pokemon(idPokemon, namePokemon, imagen));

        }//fin de for        
        return lista;
    }
}
