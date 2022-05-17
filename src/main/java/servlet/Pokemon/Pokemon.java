/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet.Pokemon;


public class Pokemon {
    private int idPokemon;
    private String nombre;
    private String urlImagen;

    public Pokemon(int idPokemon, String nombre, String urlImagen) {
        this.idPokemon = idPokemon;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public Pokemon() {
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    
    
    
    
}
