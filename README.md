# Parcial primer corte
---
<img width="770" height="243" alt="image" src="https://github.com/user-attachments/assets/4cefdb6d-feb2-455d-a550-275fcf281518" />

## Autor: Nicolás Prieto Vargas

### Descripción del proyecto:
Se debe construir un "Almacenamiento llave-Valor (key, value) distribuido". La solución consta de un servidor backend
que responde a solicitudes HTTP GET de la Facade, un servidor Facade que responde a solicitudes HTTP GET del cliente , y
un cliente Html+JS que envía los comandos y muestra las respuestas. La api permite almacenar tuplas llave-valor (k,v), ambas
de tipo string en el backend, y recuperar el valor dada una llave.

### Funcionamiento:
1. **GET /setkv?key={key}&value={value}** Debe crear o reemplazar el valor asociado a una llave.
      
  *Posibles respuestas:*  
  200 OK – reemplazado  o creado.  
  400 Bad Request – faltan key o value o no son string.  

  
2. **GET /getkv?key={key}** Obtiene el valor de una llave

   *Posibles respuestas:*
   200 OK
   404 NOT FOUND

### Arquitectura:
- La aplicación tendrá tres componentes distribuidos: Una fachada de servicios, un servicio de backend, y un cliente web (html +js).
- Los servicios de la fachada y del backend deben estar desplegados en máquinas virtuales java  diferentes.
- El cliente es un cliente web que usa html y js. Se descarga desde un servicio en la fachada (Puede entregar el cliente directamente desde un método no es necesario que lo lea desde el disco).
- La comunicación se hace usando http y las respuestas de los servicios son en formato JSON.
- Los llamados al servicio de fachada desde el cliente deben ser asíncronos usando el mínimo JS prosible. No actualice la página en cada llamado, solo el resultado.
- Los retornos deben estar  en formato JSON o TEXTO.
- El diseño de los servicios WEB debe tener en cuenta buenas prácticas de diseño OO.

### ¿Qué se hizo?
- Al ejecutar los archivos dados como ayuda, se hace la conexión entre el servidor HTTP y el ejemplo de conexión, para esto me di cuenta que la URL a la que estaba dirigido el ejemplo era:
  https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM, y el socket del servidor estaba conectado al puerto 36000, por ende, para que hubiera conexión se cambió de esa URL a
  localhost:36000, además se cambiaron algunas cosas de la información para hacerlo más personalizado dando como resultado:  
  <img width="316" height="162" alt="image" src="https://github.com/user-attachments/assets/07036514-56fe-45f2-8462-deb327f52a48" />  
- Por otro lado, en el index.html dejamos los formularios para las peticiones GET y SET, de la siguiente manera:
<img width="258" height="405" alt="image" src="https://github.com/user-attachments/assets/966e9d81-22fb-4078-b772-f7e9781837b5" />

Idealmente al momento de llenar el formulario SET, se ingresa la llave y el valor, y retorna un JSON parecido al siguiente:  

{ "key": "nico", "value": "123", "status": "created" }  

Y al poner una llave en el formulario de GET, obtenemos un JSON parecido al siguiente: 

{ "key": "mi_llave", "value": "mi_valor" } 

Pero en dado caso no encuentre la llave, se retorna un JSON así: 

{ "error": "key_not_found", "key": "mi_llave" } 

Luego de unas modificaciones se logró poner los formularios dentro del server, ya no están aislados en un inex.html aparte.
