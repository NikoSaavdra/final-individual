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


  let selectedProduct = null; // Producto seleccionado para comprar

  
  function loadProducts() {
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

          const priceCell = document.createElement('td');
          priceCell.textContent = product.precio;

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
              showBuyModal(product); // Mostrar el modal para comprar
            };
          }

          // Agregar eventos a los botones
          verButton.onclick = function() {
            showProductDetails(product);  // Mostrar los detalles del producto
          };

          // Evento para modificar el producto
          actButton.onclick = function() {
            showEditProductForm(product);  // Mostrar el formulario de edición
          };

          // Evento para eliminar el producto
          borrarButton.onclick = function() {
            if (confirm('¿Estás seguro de que quieres eliminar este producto?')) {
              deleteProduct(product.id, row);
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

  // Crear producto (enviar el formulario)
  createProductForm.onsubmit = function (e) {
    e.preventDefault(); // Prevenir el envío del formulario

    const newProduct = {
      nombre: document.getElementById('createProductName').value,
      descripcion: document.getElementById('createProductDescription').value,
      cantidad: parseInt(document.getElementById('createProductQuantity').value),
      precio: parseFloat(document.getElementById('createProductPrice').value)
    };

    fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newProduct)
    })
      .then(response => response.json())
      .then(data => {
        createProductModal.style.display = 'none';
        loadProducts(); // Recargar los productos
      })
      .catch(error => {
        console.error('Error al crear el producto:', error);
      });
  };

  // Mostrar el formulario de edición con los datos del producto
  function showEditProductForm(product) {
    selectedProduct = product;
    
    document.getElementById('editProductName').value = product.nombre;
    document.getElementById('editProductDescription').value = product.descripcion;
    document.getElementById('editProductQuantity').value = product.cantidad;
    document.getElementById('editProductPrice').value = product.precio;

    editProductModal.style.display = 'block'; // Mostrar el modal de edición
  }

  // Cerrar el formulario de edición
  cancelEditBtn.onclick = function() {
    editProductModal.style.display = 'none'; // Ocultar el formulario de edición
  };

  // Mostrar el modal de compra para un producto seleccionado
  function showBuyModal(product) {
    document.getElementById('productName').textContent = product.nombre;
    document.getElementById('productDescripcion').textContent = product.descripcion;
    document.getElementById('productDescripcion').textContent = product.descripcion;
    document.getElementById('productPrice').textContent = `Precio: $${product.precio}`;
    buyProductModal.style.display = 'block';
  }

  // Función para mostrar los detalles del producto
  function showProductDetails(product) {
    document.getElementById('detailProductName').textContent = product.nombre;
    document.getElementById('detailProductDescription').textContent = product.descripcion;
    document.getElementById('detailProductQuantity').textContent = product.cantidad;
    document.getElementById('detailProductPrice').textContent = product.precio;

    productDetailDiv.style.display = 'block'; // Mostrar el contenedor de detalles
  }

  // Cerrar el detalle del producto
  closeDetailBtn.onclick = function() {
    productDetailDiv.style.display = 'none'; // Ocultar el contenedor de detalles
  };

  // Confirmar compra
  confirmBuyBtn.onclick = function () {
    const quantity = parseInt(document.getElementById('productQuantity').value);
    if (quantity <= 0 || quantity > selectedProduct.cantidad) {
      alert('Cantidad no válida.');
      return;
    }

    fetch(`http://localhost:8080/productos/${selectedProduct.id}/compra`, {
      method: 'POST',
    })
      .then(response => response.json())
      .then(data => {
        alert(data.message);
        buyProductModal.style.display = 'none';
        loadProducts(); // Recargar productos después de la compra
      })
      .catch(error => {
        console.error('Error al realizar la compra:', error);
      });
  };

  // Cancelar compra
  cancelBuyBtn.onclick = function () {
    buyProductModal.style.display = 'none';
  };

   // Función para eliminar un producto
   function deleteProduct(productId, row) {
    fetch(`${apiUrl}/${productId}`, {
      method: 'DELETE',
    })
    .then(response => response.json())
    
    .then(data => {
      if (data.success) {
        // Si la eliminación fue exitosa, eliminar la fila de la tabla
        row.remove();
        loadProducts(); // Recargar los productos
        alert('Producto eliminado con éxito');
      } else {
        alert('No se pudo eliminar el producto');
      }
    })
    .catch(error => {
      console.error('Error al eliminar el producto:', error);
      alert('Error al eliminar el producto');
    });
  }

  // Cargar productos al iniciar
  loadProducts();
});
