/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
        //Creo una lista para guardar los valores de cada pokemon(id, nombre, iamgen)
        ArrayList<Pokemon> lista = new ArrayList<Pokemon>();
        //Creo un for para mostrar solo los 9 primeros pokemones


        
            for (int i = 1; i <= 9 ; i++) {
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
        
    


        /*  lista.stream().map(poke -> {
            System.out.println(poke.getIdPokemon());
              return poke;
          }).map(poke -> {
              System.out.println(poke.getNombre());
              return poke;
          }).forEachOrdered(poke -> {
              System.out.println(poke.getUrlImagen());
          });*/
        // request.setAttribute("idPokemon", idPokemon);
        // request.setAttribute("namePokemon", namePokemon);
        // request.setAttribute("imagenPokemon", imagen);
        request.setAttribute("lista", lista);

        request.getRequestDispatcher("Pokemones.jsp").forward(request, response);
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
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
