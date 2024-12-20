document.addEventListener("DOMContentLoaded", function () {
  const apiUrl = 'http://localhost:8080/productos'; // URL del servidor
  const tableBody = document.querySelector('#productosTable tbody');
  const createProductBtn = document.getElementById('createProductBtn');

  const createProductModal = document.getElementById('createProductModal');
  const closeCreateProductModal = createProductModal.querySelector('.close-btn');
  const cancelCreateBtn = document.getElementById('cancelCreateBtn');
  const createProductForm = document.getElementById('createProductForm');

  const buyProductModal = document.getElementById('buyProductModal');
  const closeBuyProductModal = buyProductModal.querySelector('.close-btn');
  const confirmBuyBtn = document.getElementById('confirmBuyBtn');
  const cancelBuyBtn = document.getElementById('cancelBuyBtn');
  const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
  // Elemento donde se mostrarán los detalles
  const productDetailDiv = document.getElementById('productDetail');
  const closeDetailBtn = document.getElementById('closeDetailBtn');

  // Elementos para el formulario de edición
  const editProductModal = document.getElementById('editProductModal');
  const cancelEditBtn = document.getElementById('cancelEditBtn');
  const editProductForm = document.getElementById('editProductForm');

  let selectedProduct = null; 

  function cargarProductos() {
    fetch(apiUrl)
      .then(response => response.json())
      .then(data => {
        tableBody.innerHTML = '';

        data.forEach(product => {
          const row = document.createElement('tr');

          const nameCell = document.createElement('td');
          nameCell.textContent = product.nombre;

          const quantityCell = document.createElement('td');
          quantityCell.textContent = product.cantidad;
          quantityCell.classList.add('cantidad');

          const priceCell = document.createElement('td');
          priceCell.textContent = product.precio;
          priceCell.classList.add('precio'); 

          const actionsCell = document.createElement('td');

          // Crear botón de visualizar
          const verButton = document.createElement('button');
          verButton.classList.add('ver-btn');
          verButton.textContent = 'Visualizar';

          // Crear botón de modificar
          const actButton = document.createElement('button');
          actButton.classList.add('act-btn');
          actButton.textContent = 'Modificar';

          // Crear botón de comprar
          const buyButton = document.createElement('button');
          buyButton.classList.add('buy-btn');
          buyButton.textContent = 'Comprar';

          // Crear botón de eliminar
          const borrarButton = document.createElement('button');
          borrarButton.classList.add('del-btn');
          borrarButton.textContent = 'Eliminar';

          // Desactivar el botón si la cantidad es 0
          if (product.cantidad === 0) {
            buyButton.disabled = true;
            buyButton.textContent = 'Sin stock';
          } else {
            buyButton.onclick = function () {
              selectedProduct = product;
              comprarProducto(product); // Mostrar el modal para comprar
            };
          }

          // Agregar eventos a los botones
          verButton.onclick = function () {
            mostrarProductoDetalle(product);  // Mostrar los detalles del producto
          };

          // Evento para modificar el producto
          actButton.onclick = function () {
            mostrarProducto(product);  // Mostrar el formulario de edición
          };

          // Evento para eliminar el producto
          borrarButton.onclick = function () {
            if (confirm('¿Estás seguro de que quieres eliminar este producto?')) {
              borrarProducto(product.id, row);
            }
          };

          // Agregar botones a la celda de acciones
          actionsCell.appendChild(verButton);
          actionsCell.appendChild(actButton);
          actionsCell.appendChild(buyButton);
          actionsCell.appendChild(borrarButton);
          row.appendChild(nameCell);
          row.appendChild(quantityCell);
          row.appendChild(priceCell);
          row.appendChild(actionsCell);
          tableBody.appendChild(row);
        });
      })
      .catch(error => {
        console.error('Error al cargar los productos:', error);
      });
  }

  // Mostrar el modal para crear un producto (formulario)
  createProductBtn.onclick = function () {
    createProductModal.style.display = 'block';
  };

  // Cerrar el modal de crear producto
  closeCreateProductModal.onclick = function () {
    createProductModal.style.display = 'none';
  };

  // Cancelar la creación del producto
  cancelCreateBtn.onclick = function () {
    createProductModal.style.display = 'none';
  };
  
  // Crear producto
  createProductForm.onsubmit = function (e) {
    e.preventDefault(); // Prevenir el envío del formulario

    const newProduct = {
      nombre: document.getElementById('createProductName').value,
      descripcion: document.getElementById('createProductDescription').value,
      cantidad: parseInt(document.getElementById('createProductQuantity').value),
      precio: parseFloat(document.getElementById('createProductPrice').value)
    };
    
    if (!productName || !productDescription || isNaN(productQuantity) || isNaN(productPrice)) {
      alert('Por favor, complete todos los campos correctamente.');
      return;
    }
    fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newProduct)
    })
      .then(response => response.json())
      .then(data => {
        createProductModal.style.display = 'none';
        cargarProductos(); // Recargar los productos
      })
      .catch(error => {
        console.error('Error al crear el producto:', error);
      });
  };

function mostrarProducto(product) {
  selectedProduct = product;

  document.getElementById('editProductName').value = product.nombre;
  document.getElementById('editProductDescription').value = product.descripcion;
  document.getElementById('editProductQuantity').value = product.cantidad;
  document.getElementById('editProductPrice').value = product.precio;

  editProductModal.style.display = 'block'; // Mostrar el modal de edición
}

// Cerrar el formulario de edición
cancelEditBtn.onclick = function () {
  editProductModal.style.display = 'none'; // Ocultar el formulario de edición
};

// Guardar la edición (enviar el formulario de modificación)
editProductForm.onsubmit = function (e) {
  e.preventDefault(); // Prevenir el comportamiento por defecto del formulario

  const updatedProduct = {
    nombre: document.getElementById('editProductName').value,
    descripcion: document.getElementById('editProductDescription').value,
    cantidad: parseInt(document.getElementById('editProductQuantity').value),
    precio: parseFloat(document.getElementById('editProductPrice').value)
  };

  // Enviar la solicitud PUT para actualizar el producto
  fetch(`${apiUrl}/${selectedProduct.id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(updatedProduct)
  })
    .then(response => response.json())
    .then(data => {
      showMessageModal('Producto actualizado con éxito');
      editProductModal.style.display = 'none'; // Cerrar el modal de edición
      cargarProductos(); // Recargar los productos después de la modificación
    })
    .catch(error => {
      console.error('Error al actualizar el producto:', error);
      ('Error al actualizar el producto');
    });
};

  // Mostrar el modal de compra para un producto seleccionado
  function comprarProducto(product) {
    document.getElementById('productName').textContent = product.nombre;
    document.getElementById('productDescripcion').textContent = product.descripcion;
    document.getElementById('productDescripcion').textContent = product.descripcion;
    document.getElementById('productPrice').textContent = `Precio: ${product.precio}`+ ' €';
    buyProductModal.style.display = 'block';
  }

  // Función para mostrar los detalles del producto
  function mostrarProductoDetalle(product) {
    document.getElementById('detailProductName').textContent = product.nombre;
    document.getElementById('detailProductDescription').textContent = product.descripcion;
    document.getElementById('detailProductQuantity').textContent = product.cantidad;
    document.getElementById('detailProductPrice').textContent = product.precio + ' €';

    productDetailDiv.style.display = 'block'; // Mostrar el contenedor de detalles
  }

  // Cerrar el detalle del producto
  closeDetailBtn.onclick = function () {
    productDetailDiv.style.display = 'none'; // Ocultar el contenedor de detalles
  };

  // Confirmar compra
confirmBuyBtn.onclick = function () {

  // Enviar la solicitud de compra
  fetch(`http://localhost:8080/productos/${selectedProduct.id}/compra`, {
    method: 'POST',
  })
    .then(response => response.json())
    .then(data => {
      // Mostrar el mensaje de éxito con el modal
      showMessageModal('Compra Exitosa', data.message);
      buyProductModal.style.display = 'none'; // Cerrar el modal de compra
      cargarProductos(); // Recargar productos después de la compra
    })
    .catch(error => {
      console.error('Error al realizar la compra:', error);
      showMessageModal('Error', 'Hubo un problema al realizar la compra.');
    });
};

  // Cancelar compra
  cancelBuyBtn.onclick = function () {
    buyProductModal.style.display = 'none';
  };

  function borrarProducto(productId, row) {
    fetch(`${apiUrl}/${productId}`, {
      method: 'DELETE',
    })
      .then(response => {
        if (response.status === 204) {
          row.remove(); // Eliminar la fila de la tabla inmediatamente
          showMessageModal('Éxito', 'Producto eliminado con éxito');
          cargarProductos(); // Recargar los productos para reflejar los cambios
        } else {
          // Si no es 204, entonces mostramos un mensaje de error y lo registramos en la consola
          return response.json().then(data => {
            console.error('Error al eliminar el producto:', data);
            showMessageModal('Error', 'No se pudo eliminar el producto: ' + (data.message || 'Error desconocido'));
          });
        }
      })
      .catch(error => {
        console.error('Error al eliminar el producto:', error);
        showMessageModal('Error', 'Error al eliminar el producto');
      });
  }
  // Mostrar el modal con un mensaje
  function showMessageModal(title, message) {
    const modal = document.getElementById('messageModal');
    const titleElement = document.getElementById('modalMessageTitle');
    const messageElement = document.getElementById('modalMessageText');
    const closeBtn = document.getElementById('messageModalClose');
    const btn = document.getElementById('modalMessageBtn');

    titleElement.textContent = title;
    messageElement.textContent = message;

    modal.style.display = 'flex'; // Mostrar el modal

    // Función para cerrar el modal
    closeBtn.onclick = function () {
      modal.style.display = 'none';
    };

    btn.onclick = function () {
      modal.style.display = 'none';
    };
  }

  cargarProductos();
});
