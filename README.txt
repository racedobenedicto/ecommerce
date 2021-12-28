Este proyecto de Netbeans contiene una versión mejorada del proyecto ecommerce original. En esta podemos iniciar sesión y registrar los usuarios y
sus pedidos en la base de datos (la cual adjunto - ecommerce_v2) a modo de histórico de lo que han añadido en la cesta de la compra, lo hayan com-
prado después o no. De modo que, una vez iniciemos sesión, en la página de inicio, además de las cuatro categorías, también nos aparecen los produc-
tos que hemos visto y/o comprado en las últimas veces que hemos entrado a la aplicación, facilitando así la compra. Cuando no iniciamos sesión, la
aplicación funciona de la misma manera que en la versión original.

Respecto a la base de datos: Tenemos dos tablas nuevas, comanda y user.
	1. Comanda: en ella guardamos los pedidos de cada usuario por producto, de manera que se enviarían individualmente (estilo Amazon). Tenemos
la id del pedido, la del usuario que hace el pedido, la del producto en cuestión y la cantidad y, por último, un campo con la fecha y la hora en la
cual se realiza el pedido. En esta tabla vienen ya algunos pedidos guardados.
	2. User: guardamos el nombre del usuario y su contraseña ordenándolos por una id. De esta manera, podemos verificar que el usuario que ha
iniciado sesión estaba ya dado de alta en la plataforma o, en caso contrario, le damos la posibilidad de registrarse. Queda registrado en la tabla
el usuario con username = 'Raquel' y password = '1234'. Se pueden crear más usuarios si es necesario.

Archivos nuevos o con nuevas funcionalidades:
	1. Web Pages/view:
		- init.jsp: en la parte superior izquierda hay un botón "LOGIN" que te mueve a la pantalla login.jsp. Una vez hemos iniciado sesión,
nos aparece un botón de "LOGOUT" y un mensaje de bienvenida. Además, en la parte inferior de la pantalla nos aparece la tabla a modo de histórico
con los productos vistos y/o comprados recientemente viendo así la categoría del producto y su precio, teniendo, además la posibilidad de añadirlo
a la cesta de la compra. Una vez añadimos un producto a la cesta, 
		- login.jsp: en esta pantalla tenemos la posibilidad de iniciar sesión (primeros campos de Login) o de registrarnos (campos de Sign
In). En el primer caso, si el usuario no existe o está mal la contraseña da un error y en el segundo lo da si el usuario ya existe, por lo que es
duplicado. Una vez iniciamos sesión o nos registramos volvemos a la pantalla init.jsp donde podemos escoger la categoría que queremos ver. Además
de la tabla del histórico.
		- A partir de este momento, en la parte superior izquierda de cada pantalla nos indica el nombre del usuario que está con la sesión
iniciada y tenemos el botón de "LOGOUT" para cerrar sesión.
		- category.jsp: el enlace de "Proceed to checkout" te envía a la página checkout.jsp donde ya puedes iniciar la compra o cancelarla.
		- viewcart.jsp: la imagen para comprar (equivalente al enlace anterior) te envía a la misma pantalla.
		- checkout.jsp: en esta pantalla encontramos el enlace a paypal y la misma tabla que en viewcart.jsp pero esta vez sin la posibili-
dad de modificar las cantidades. En la parte inferior podemos ver un botón para cancelar, el cual nos redirigirá a la pantalla viewcart.jsp donde 
tendremos la posibilidad de volver a empezar ("Clear cart"), de seguir comprando ("Continue shopping") o modificar la cantidad de algún producto 
("update").
	2. Source Packages/entity: se han añadido las dos clases referentes a las dos nuevas tablas de la base de datos (Comanda.java y User.java).
	3. Source Packages/model: creación de dos modelos referentes a las dos nuevas tablas de la base de datos (UserModel.java y ComandaModel.java)
donde se hacen los accesos a base de datos para obtener un usuario, la lista de usuarios, insertar un usuario, comprobar si existe un usuario 
(devuelve el id del usuario si existe y lo encuentra o -1 en caso contrario) e insertar un pedido, en el caso del ComandaModel.
	4. Source Packages/util/ContextLisener.java: se han creado una instancia comandaModel y una userModel en ámbito aplicación.
	5. Source Packages/web/ControllerServlet.java: se han añadido los servlet-path correspondientes a las nuevas páginas jsp y añadido al 
constructor el ComandaModel y/o el UserModel, u alguno de los otros dos modelos, en caso necesario.
	6. Source Packages/web.action:
		- En todos los archivos Action de la versión inicial se ha añadido el usuario y, en algunos casos, algunos parámetros de interés.
		- loginAction.java: este archivo hace un forward al login.jsp.
		- checkloginAction.java: es el encargado de verificar que el usuario existe, en ese caso redirigirá la pantalla al init.do, en caso
de un mal inicio de sesión a partir del campo Login muestra un mensaje de error y se mantiene en la pantalla login.do. Además, recogemos los pedidos
hechos anteriormente de la base de datos y enviamos a init.jsp otros datos de interés.
		- signinAction.java: se encarga de recoger el usuario y contraseña del campo de registro de usuario (Sign In), comprueba si el usu-
ario ya existe en la base de datos (en este caso envía un error manteniéndose en la misma pantalla) o si es un nuevo usuario, el cual registra en 
la base de datos y hace en forward a init.do. Además, como en el caso anterior, recogemos los pedidos hechos anteriormente de la base de datos y en-
viamos a init.jsp otros datos de interés.
		- checkoutAction.java: en esta versión, esta acción te redirige a la pantalla checkout.jsp. Además, es el encargado de guardar los
datos del pedido en la base de datos.
		-historicAction.java: este es un archivo con una funcionalidad muy parecida a neworderAction.java, en el que, desde la página de 
al inicio podemos añadir productos al carrito sin desplazarnos a category.jsp. De esta manera, podemos añadir productos que necesitemos desde la 
página de init.jsp sin tener que buscarlos entre las diferentes categorías. Una vez añadimos un producto con este nuevo botón, nos aparece en la 
parte superior la cantidad de ítems que hemos añadido y un enlace con la posibilidad de ir a la cesta de la compra sin pasar por una categoría.

Adjunto el esquema del proyecto de netbeans y un esquema de clases.

