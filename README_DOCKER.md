======== Despliegue Docker
Pasos:
1. Dentro del directorio "./docker" abrir una consola de Powershell
2. Construir las imagenes necesarias
    - docker network create --driver bridge nuevospa-net
3. Se debe iniciar el docker 
	- docker compose up -d
4. Verificar que la imagen este corriendo con el comando
	- docker ps
5. Abrir dentro de un browser
	- http://localhost:8080/swagger-ui.html

======== Prueba
Pasos:
1. Dentro del swagger se encuentran 5 servicios
	- /api/auth/login -> Servicio que permite generar el token para poder consumir los demás servicios
		- Se debe utilizar uno de los usuarios precargados que son:
			- username : davidsala
			- password: salamanca123
			- username: carolpineda
			- password: clave124
		
		Una vez enviados los datos, genera un token el cual tiene una vigencia de 60 minutos, dicho token se debe utilizar para autenticarse en los demás servicios rest ingresandolo en el candado
		
	- /api/gestionar-tares -> Servicio GET que permite listar todas las tareas
	- /api/gestionar-tares -> Servicio PUT que permite actualizar una tarea por nombre
		- El servicio recibe el nombre de la tarea y el nuevo estado, los estados precargados son Activo e Inactivo
		
	- /api/gestionar-tares -> Servicio POST que permite registrar una tarea
		- El servicio recibe el nombre de la tarea y la descripción, colocando por defecto el estado Activo
	
	- /api/gestionar-tares/{idTarea} -> Servicio DELETE que permite eliminar una tareas
		- El servicio recibe como parametro el id de la tarea
