package com.tienda.service;

import com.tienda.domain.Producto; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
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
    
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
    public List<Producto> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
}
 