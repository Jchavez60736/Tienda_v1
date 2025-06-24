package com.tienda.service;

import com.tienda.domain.Categoria; 
import java.util.List; 

public interface CategoriaService {
    //asigna el metodo para despues implementarlo en el serviceImpl (Read)
    public List<Categoria> getCategorias(boolean activos);
    //Metodo para obtener el ID de la categoria (si el ID esta vacio o no para saber si actualizar o añadir)
    public Categoria getCategoria(Categoria categoria);
    //Metodo para guardar
    public void save(Categoria categoria);
    //Metodo para eliminar
    public void delete(Categoria categoria);
}
 