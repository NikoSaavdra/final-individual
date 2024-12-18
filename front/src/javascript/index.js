document.addEventListener("DOMContentLoaded", function () {
  const apiUrl = 'http://localhost:8080/productos'; // URL del servidor
  const tableBody = document.querySelector('#productosTable tbody');
  const createProductBtn = document.getElementById('createProductBtn');

  const createProductModal = document.getElementById('createProductModal');
  const closeCreateProductModal = createProductModal.querySelector('.close-btn');
  const confirmCreateBtn = document.getElementById('confirmCreateBtn');
  const cancelCreateBtn = document.getElementById('cancelCreateBtn');

  let selectedProduct = null; // Producto seleccionado para comprar

  // Cargar productos
  function loadProducts() {
    fetch(apiUrl)
      .then(response => response.json())
      .then(data => {
        console.log(data);  // Verifica la respuesta aquí
        tableBody.innerHTML = ''; // Limpiar la tabla antes de agregar nuevos datos

        data.forEach(product => {
          const row = document.createElement('tr');

          const nameCell = document.createElement('td');
          nameCell.textContent = product.nombre;

          const quantityCell = document.createElement('td');
          quantityCell.textContent = product.cantidad;

          const priceCell = document.createElement('td');
          priceCell.textContent = product.precio;

          const actionsCell = document.createElement('td');

          const buyButton = document.createElement('button');
          buyButton.classList.add('buy-btn');
          buyButton.textContent = 'Comprar';

          const borrarButton = document.createElement('button');
          borrarButton.classList.add('delete-btn');
          borrarButton.textContent = 'Borrar';

          // Desactivar el botón si la cantidad es 0
          if (product.cantidad === 0) {
            buyButton.disabled = true;
            buyButton.textContent = 'Sin stock';  // Cambiar el texto si no hay stock
          } else {
            buyButton.onclick = function () {
              showBuyModal(product); // Mostrar el modal con los datos del producto
            };
          }

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

  // Mostrar el modal para crear un producto
  createProductBtn.onclick = function () {
    createProductModal.style.display = 'block';
  };

  // Cerrar el modal
  closeCreateProductModal.onclick = function () {
    createProductModal.style.display = 'none';
  };

  // Cancelar creación del producto
  cancelCreateBtn.onclick = function () {
    createProductModal.style.display = 'none'; // Cerrar el modal si se cancela
  };

  // Confirmar la creación del producto
  confirmCreateBtn.onclick = function () {
    const newProduct = {
      nombre: document.getElementById('createProductName').value,
      descripcion: document.getElementById('createProductDescription').value,
      cantidad: parseInt(document.getElementById('createProductQuantity').value),
      precio: parseFloat(document.getElementById('createProductPrice').value)
    };

    // Hacer una solicitud POST para crear el producto
    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newProduct)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Producto creado:', data);
        createProductModal.style.display = 'none'; // Cerrar el modal después de la creación
        loadProducts(); // Recargar los productos y actualizar la tabla
      })
      .catch(error => {
        console.error('Error al crear el producto:', error);
      });
  };

  // Cargar productos al iniciar
  loadProducts();
});
