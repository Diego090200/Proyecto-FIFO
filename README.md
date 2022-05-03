![fastell](https://user-images.githubusercontent.com/38775456/166434438-e758dd8f-b741-43af-9584-5e91bdc6bc44.png)

## Bienvenido al repositorio realizado por Fastell 😎😎


### Proyecto Final Sistemas Operativos 2022 🤯

#### Simulación de remplazo de paginas por medio de algoritmo FIFO

------------

#### Conocimientos Básicos para comprender el programa

#### ¿Cómo funciona el algoritmo FIFO para el remplazo de páginas?
Este algoritmo es utilizado cuando sucede un fallo de página, en este momento el sistema operativo debe quitar una de las páginas que se encuentran dentro de la memoria, se aplica este algoritmo para escoger que página se debe reemplazar, el sistema tiene una lista de las páginas que se encuentran en memoria en donde la ultima llegada se encuentra en el fondo y la mas antigua se encuentra al principio al momento de producirse el fallo el algoritmo reemplaza la pagina más antigua para ingresar la nueva página. A continuación se agrega un link que ejemplifica de forma gráfica el algoritmo FIFO. [Click aqui](https://www.youtube.com/watch?v=5Y2bT_gz2Yk:// "Click aqui")

------------

### Requerimientos

Se requiere minimo NetBeans IDE 11 para poder utilizar el programa.

Se requiere que mínimo tenga mínimo JDK 15. 

### Funcionamiento del programa
Después de tener los requerimientos necesarios puede proceder a probar la aplicación.

Al momento de correr el programa se genera un número aleatorio, primeramente se verifica si la lista de la memoria está vacía si es así se produce el primer fallo y se ingresa el número en el tope de la lista. 

A los 3 segundos se produce otra pulsación que genera un nuevo número aleatorio y el algoritmo nuevamente comprueba si la lista está vacía, sino esta procede a comparar el número nuevo con los números de la lista, si existe dentro la lista se produce un acierto y no se ingresa a la lista pero sino se encuentra procede a revisar el tamaño de la lista sea menor a 4 ya que ese es el límite de la memoria, de ser menor procede a ingresar el nuevo número de página dentro de la lista pero si por el contrario la lista se encuentra llena se procede a remplazar el número de página nuevo por el número de página más antiguo en la lista.

Este proceso se repite de forma indefinida, el sistema cuenta con 4 tablas una que muestra el estádo de la memoria en cada pulsación, otra muestra la lista de la memoria, en la siguiente tabla se indica cada acierto o fallo que se produzca en cada pulsación y para finalizar una tabla que muestra el número que se ingresa a memoria por cada pulsación en caso de ser un fallo, si se produce un acierto se muestra una "X".

### Documentación

Se muestra un documento indicando el funcionamiento del programa.

#### Colaboradores
Bryan Bac 😎 [Perfil de GitHub](https://github.com/BryanBac "Perfil de GitHub")

Laura Mejia 🤗 [Perfil de GitHub](https://github.com/Laura34 "Perfil de GitHub")

Kathya González 🤭 [Perfil de GitHub](https://github.com/KathyaGonzalez "Perfil de Github")

Diego Hernández 😜 [Perfil de GitHub](https://github.com/Diego090200 "Perfil de GitHub")
