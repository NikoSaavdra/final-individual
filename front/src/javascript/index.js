document.addEventListener("DOMContentLoaded", function() {
    const apiUrl = 'http://localhost:8080/productos'; // URL del servidor
    const productosTable = document.getElementById('productos').getElementsByTagName('tbody')[0];
    const createBtn = document.getElementById('createBtn');
    
    // Función para cargar los productos
    function cargarProductos() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                productosTable.innerHTML = '';
                data.forEach(producto => {
                    let row = productosTable.insertRow();
                    row.innerHTML = `
                        <td>${producto.id}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.cantidad}</td>
                        <td>${producto.precio}</td>
                        <td>
                            <button class="modify-btn" onclick="modificarProducto(${producto.id})">Modificar</button>
                            <button class="delete-btn" onclick="eliminarProducto(${producto.id})">Eliminar</button>
                        </td>
                    `;
                });
            })
            .catch(error => console.log('Error al cargar los productos:', error));
    }

    // Función para crear un nuevo producto
    createBtn.addEventListener('click', function() {
        const nombre = document.getElementById('nombre').value;
        const cantidad = document.getElementById('cantidad').value;
        const precio = document.getElementById('precio').value;
        
        const nuevoProducto = { nombre, cantidad, precio };

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoProducto)
        })
        .then(response => response.json())
        .then(data => {
            cargarProductos(); // Recargar la lista de productos
        })
        .catch(error => console.log('Error al crear producto:', error));
    });

    // Función para modificar un producto
    window.modificarProducto = function(id) {
        const nombre = prompt("Nuevo nombre del producto:");
        const cantidad = prompt("Nueva cantidad:");
        const precio = prompt("Nuevo precio:");

        const productoModificado = { nombre, cantidad, precio };

        fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productoModificado)
        })
        .then(response => response.json())
        .then(data => {
            cargarProductos(); // Recargar la lista de productos
        })
        .catch(error => console.log('Error al modificar producto:', error));
    };

    // Función para eliminar un producto
    window.eliminarProducto = function(id) {
        if (confirm('¿Estás seguro de eliminar este producto?')) {
            fetch(`${apiUrl}/${id}`, {
                method: 'DELETE'
            })
            .then(() => {
                cargarProductos(); // Recargar la lista de productos
            })
            .catch(error => console.log('Error al eliminar producto:', error));
        }
    };

    // Cargar los productos al inicio
    cargarProductos();
});
