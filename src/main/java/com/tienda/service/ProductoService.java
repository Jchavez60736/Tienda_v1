package com.tienda.service;

import com.tienda.domain.Producto; 
import java.util.List; 

public interface ProductoService {
    //asigna el metodo para despues implementarlo en el serviceImpl (Read)
    public List<Producto> getProductos(boolean activos);
    //Metodo para obtener el ID de la producto (si el ID esta vacio o no para saber si actualizar o añadir)
    public Producto getProducto(Producto producto);
    //Metodo para guardar
    public void save(Producto producto);
    //Metodo para eliminar
    public void delete(Producto producto);
}
 