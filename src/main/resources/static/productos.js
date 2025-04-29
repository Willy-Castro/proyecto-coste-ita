//Controla el carrito
let carrito = [];	//guarda los productos que el cliente agrega

function agregarAlCarrito(button) {//al usar el boton de agregar se activa esta funcion
    const productoDiv = button.parentElement;//encuentra el div del producto
    const nombre = productoDiv.getAttribute('data-nombre');
    const precio = parseFloat(productoDiv.getAttribute('data-precio'));//encuentra el nombre y precio
    const cantidad = parseInt(productoDiv.querySelector('input').value);//lee la cantidad de productos

    const productoExistente = carrito.find(p => p.nombre === nombre);//funcion de revisar si el producto ya estaba en el pedido
    if (productoExistente) {
        productoExistente.cantidad += cantidad;//si es producto repetido lo suma
    } else {
        carrito.push({ nombre, precio, cantidad });
    }//si no, lo agrega como nuevo

    actualizarCarrito();//actualiza lo que se ve en el pedido
}

function actualizarCarrito() {//Funcion de limpiar y escribir lo que hay en el carrito
    const carritoDiv = document.getElementById('carrito');
    carritoDiv.innerHTML = '';//borra todo lo que habia antes en el carrito
    let total = 0;//inicia de 0 lo que se pagara
    carrito.forEach(p => {
        carritoDiv.innerHTML += `<p>${p.cantidad} x ${p.nombre} = S/${(p.precio * p.cantidad).toFixed(2)}</p>`;
        total += p.precio * p.cantidad;//Escribe en el carrito que productos y el subtotal.
    });
    total *= 1.18; // multiplica el total por el igv
    document.getElementById('total').innerText = `Total (con IGV): S/${total.toFixed(2)}`;//muestra el total final en el pedido
}

function pagarNormal() {
    sessionStorage.setItem('carrito', JSON.stringify(carrito));//guarda el pedido con sus datos
    window.location.href = '/pago';//te manda a la ventana pago
}

function cancelarPedido() {//Funcion de borra todo lo que hay en el carrito
    carrito = []; //vacia
    actualizarCarrito(); //actualiza
}
