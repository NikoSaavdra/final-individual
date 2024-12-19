package es.santander.ascender.individual.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.individual.model.Producto;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/productos")
@CrossOrigin(origins = "http://localhost:1234")

public class ProductoController {

    private Map<Long, Producto> productos = new HashMap<>();

    public ProductoController() {
        // Añadir productos (herramientas)
        productos.put(1L, new Producto(1, "Martillo", "Martillo de acero con mango de madera", 15.0f, 20));
        productos.put(2L, new Producto(2, "Destornillador", "Destornillador de cruz con mango ergonómico", 8.5f, 50));
        productos.put(3L, new Producto(3, "Alicates", "Alicates de alta resistencia para trabajos pesados", 12.0f, 30));
        productos.put(4L, new Producto(4, "Sierra", "Sierra de mano con hoja de acero inoxidable", 25.0f, 15));
    }


    @GetMapping("/{id}")
    public HttpEntity<Producto> get(@PathVariable("id") long id) {
        if (!productos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(productos.get(id));
        }
    }

    @GetMapping
    public HttpEntity<Collection<Producto>> get() {
        return ResponseEntity.ok().body(productos.values());
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        long cuenta = productos.values().size();
        
        long maxId = 0;
        if (cuenta != 0) {
            maxId = productos.values().stream()
                                .map(p -> p.getId())
                                .mapToLong(id -> id)
                                .max()
                                .orElse(0);
        }
        producto.setId(maxId + 1);

        productos.put(producto.getId(), producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody Producto productoActualizado) {
        Producto productoExistente = productos.get(id);
        
        if (productoExistente == null) {
            return ResponseEntity.notFound().build();
        }
    
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCantidad(productoActualizado.getCantidad());
        
        return ResponseEntity.ok(productoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Producto productoExistente = productos.get(id);

        if (productoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        productos.remove(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/compra")
    public ResponseEntity<Map<String, Object>> comprarProducto(@PathVariable Long id) {
    Producto producto = productos.get(id);

    if (producto == null) {
        return ResponseEntity.notFound().build();
    }

    if (producto.getCantidad() <= 0) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "No se puede comprar, no hay stock disponible.");
        return ResponseEntity.badRequest().body(response);
    }

    producto.setCantidad(producto.getCantidad() - 1);

    Map<String, Object> response = new HashMap<>();
    response.put("message", "Compra realizada con éxito.");
    response.put("product", producto); 

    return ResponseEntity.ok(response);
}



    public Map<Long, Producto> getProductos() {
        return productos;
    }

    public void setProductos(Map<Long, Producto> productos) {
        this.productos = productos;
    }    
}
