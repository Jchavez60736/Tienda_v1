package com.tienda.domain;

import jakarta.persistence.*;   // <- esto es obligatorio con Spring Boot 3+
import lombok.Data;
import java.io.Serializable;
import com.tienda.domain.Categoria;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    Categoria categoria;
    
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
    public Producto() {
    }

    public Producto(String producto, boolean activo) {
        this.descripcion = producto;
        this.activo = activo;
    }

}
