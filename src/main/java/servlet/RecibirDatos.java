package servlet;

import api.PokemonApi;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import servlet.Pokemon.Pokemon;

/**
 *
 * @author andro
 */
public class RecibirDatos extends HttpServlet {

    int idPokemon;
    String namePokemon;
    String imagen;
    ArrayList<Pokemon> lista = new ArrayList<Pokemon>();
    static int  numero = 9;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("accion");
        if (action.equals("sumar")) {
            System.out.println("se presiono el boton");
            lista.clear();
            //Creo un for para mostrar solo los 9 primeros pokemones
            //1 - 9 / 10 - 18 
            numero += 9;
            for (int i = numero - 8; i <= numero; i++) {
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

            request.setAttribute("lista", lista);
            System.out.println(numero);
            request.getRequestDispatcher("Pokemones.jsp").forward(request, response);
        }else if(action.equals("restar")){ //1 - 9 / 10 - 18 / 19 - 27
                                               //                   -9
                                            //                 10 <- -8  <- 18
            lista.clear();
            numero -= 9;
            System.out.println(numero);
            for (int i = (numero -8); i <= numero ; i++) {
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

            request.setAttribute("lista", lista);
            request.getRequestDispatcher("Pokemones.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String action = request.getParameter("siguiente");
        if (action.equalsIgnoreCase("Enviar")) {
            PokemonApi pokemon = new PokemonApi();
            ArrayList<Pokemon> listita = pokemon.pokemon(9);
            request.setAttribute("lista", listita);

            request.getRequestDispatcher("Pokemones.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
