package com.tienda.controller;

import com.tienda.domain.Producto; 
import com.tienda.domain.Categoria; 
import com.tienda.service.ProductoService; 
import com.tienda.service.CategoriaService; 
import com.tienda.service.impl.FirebaseStorageServiceImpl; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j //Permitir manejar solicitudes de HTTP
@RequestMapping("/producto") //Define la ruta en la aplicacion
public class ProductoController {
    
    @Autowired 
    private ProductoService productoService;
    
    @Autowired 
    private CategoriaService categoriaService;

    @GetMapping("/listado") 
    public String inicio(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos); //union del codigo con la vista
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("producto", new Producto());
        return "/producto/listado";
    }
    
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "producto", 
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}") // localhost:8080/producto/eliminar/17
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}") //{idProducto} = path variable
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }   
}