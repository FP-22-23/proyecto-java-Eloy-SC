# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso 2021/22)
Autor: Eloy Sancho   uvus:elosanceb

## Estructura de las carpetas del proyecto

* **/src**: Directorio con el código fuente.
  * **fp.crimen**: Paquete que contiene los tipos del proyecto.
  * **fp.crimen.test**: Paquete que contiene las clases de test del proyecto.
  * **fp.utiles**:  Paquete que contiene las clases de utilidad. 
* **/data**: Contiene el dataset del proyecto.
  * **crime.csv**: Archivo csv que contiene datos sobre varios crímenes cometidos en una ciudad.

## Estructura del *dataset*

El dataset original Crime Classification dataset se puede encontrar en la URL [https://www.kaggle.com/datasets/abidaaslam/crime](https://www.kaggle.com/datasets/abidaaslam/crime). Se han utilizado las 9 columnas que había originalmente y cada fila recoge ciertos datos sobre un crimen cometido en una ciudad desconocida. A continuación se describen las 9 columnas del dataset:

* **Dates**: de tipo fecha y hora, indica la fecha y la hora a la que se cometió el delito.
* **Category**: de tipo cadena, indica la categoría del delito cometido.
* **Descript**: de tipo cadena, contiene una descripción muy breve del delito.
* **DayOfWeek**: de tipo cadena, indica el día de la semana. Puede tomar los valores: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday y Sunday.
* **PdDistrict**: de tipo cadena, indica el distrito en el que se cometió el delito.
* **Resolution**: de tipo cadena, indica la resolución del delito. Puede tomar los valores: NONE, "ARREST, BOOKED" y "ARREST, CITED".
* **Address**: de tipo cadena, indica la dirección del lugar.
* **X**: de tipo double, indica la latitud de la localización exacta.
* **Y**: de tipo double, indica la longitud de la localización exacta.

## Tipos implementados

Los tipos que se han implementado en el proyecto son los siguientes:

### Tipo Base - Crimen
Representa un delito concreto.
**Propiedades**:

- _fecha_, de tipo _LocalDateTime_, consultable. Indica la fecha y la hora del crimen.
- _categoria_, de tipo _String_, consultable y modificable. Indica el tipo de delito cometido.
- _descripcion_, de tipo _List\<String\>_, consultable y modificable. Lista con ciertas especificaciones que describen mejor el crimen.
- _diaSemana_, de tipo _DiaSemana_, consultable. Día de la semana en el que se cometió el crimen. Puede tomar los valores LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO y DOMINGO.
- _distrito_, de tipo _String_, consultable. Distrito de la ciudad desconocida en el que se cometió el crimen.
- _resolucion_, de tipo _Resolucion_, consultable y modificable. Resolución del delito. Puede tomar los valores SIN_RESOLUCION, ARRESTADO y ARRESTADO_CITADO.
- _direccion_, de tipo _String_, consultable. Dirección en la que se cometió el delito.
- _coordenadas_, de tipo _Coordenadas_, consultable. Localización exacta del crimen.
- _resuelto_, de tipo _Boolean_, consultable. Indica si el delito ha sido resuelto o no.
- _prioridad_, de tipo _Double_, consultable. La prioridad representa la importancia del delito. Es un número menor que 1 y cuanto menor sea dicho número más importante será el delito.

**Constructores**: 

- C1: Tiene un parámetro por cada propiedad básica del tipo. En el caso concreto del tipo auxiliar "Coordenadas", tiene un parámetro para la latitud y otro para la longitud.
- C2: Crea un objeto de tipo ```Crimen``` a partir de los siguientes parámetros: ```LocalDateTime fecha, String categoria, Resolucion resolucion```.

**Restricciones**:
 
- R1: La fecha no puede ser null y debe ser igual o anterior al día de hoy.
- R2: La categoría no puede ser null.
- R3: La resolución no puede ser null.

**Criterio de igualdad**: Dos crímenes son iguales si su categoría, su descripción, su fecha y su resolución son iguales.

**Criterio de ordenación**: Por prioridad del delito y por el nombre del distrito.

#### Tipos auxiliares

- DiaSemana, enumerado. Puede tomar los valores LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO y DOMINGO.
- Resolucion, enumerado. Puede tomar los valores SIN_RESOLUCION, ARRESTADO y ARRESTADO_CITADO.
- Coordenadas, record. Tiene dos atributos: latitud y longitud (ambos de tipo _Double_).

### Factoría - Factoría Crímenes
Clase de factoría para construir los objetos de tipo Crimenes.

- _Crimenes leeCrimenes(String fichero)_: Crea un objeto de tipo Crimenes a partir de la información recogida en el archivo csv, cuya ruta se da como parámetro.

### Tipo Contenedor - Factoría Crímenes
Clase contenedora de los objetos de tipo Crimen.

#### Propiedades
- _crimenes_, de tipo _List_<_Crimen_>, consultable. Lista de crímenes.

#### Constructores
- C1: Constructor por defecto. Crea un objeto de tipo crimenes sin ningún crimen almacenado.
- C2: Constructor con un parámetro de tipo Collection<Crimen>. Crea un objeto de tipo Crimenes con los crímenes incluidos en la colección dada como parámetro.
- C3: Constructor con un parámetro de tipo Stream<Crimen>. Crea un objeto de tipo Crimenes con los crímenes incluidos en el Stream dado.

**Criterio de igualdad:** Dos Crimenes son iguales si lo son sus propiedades crimenes.

#### Otras operaciones
- _Integer getNumeroCrimenes()_: Devuelve el número de crímenes que hay en el objeto.
- _void agregarCrimen(Crimen c)_: Agrega un crimen al objeto.
- _void agregarCrimenes(Collection_<_Crimen_> _crimenes)_: Agrega una colección de varios crímenes al contenedor.
- _void eliminarCrimen(Integer index)_: Elimina el crimen que ocupa la posición del indíce dado como parámetro.
- _Boolean existeCategoria(String cat)_: Devuelve "True" si existe algún crimen con la categoría dada como parámetro.
- _Integer sumaDistritos()_: Devuelve el número de distritos que aparecen en los crímenes que hay en el contenedor.
- _List_<_String_> _listaDirecciones(DiaSemana diaSemana)_: Devuelve una lista con las direcciones de los crímenes que hay en el objeto.
- _Map_<_String, List_<_String_>> _direccionesPorDistrito()_: Devuelve un Map en el que las claves son los distritos y los valores listas con las direcciones de los crimenes de sus respectivos distritos.
- _Map_<_String, Integer_> _contarDistritos()_: Devuelve un Map en el que las claves son los distritos que hay en el objeto y los valores la cantidad de crímenes que se han cometido en dichos distritos.
- _Boolean existeCategoriaStream(String cat)_: Devuelve "True" si existe algún crimen con la categoría dada como parámetros. Lo hace mediante el uso de un stream.
- _Integer sumaDistritosStream()_: Devuelve el número de distritos que aparecen en los crímenes que hay en el contenedor. Lo hace mediante el uso de un stream.
- _List_<_String_> _listaDireccionesStream(DiaSemana diaSemana)_: Devuelve una lista con las direcciones de los crímenes que hay en el objeto. Lo hace mediante el uso de un stream.
- _Crimen crimenMayorPrioridadEnDistrito(String distrito)_: Devuelve el crimen con mayor prioridad en el distrito dado como parámetro.
- _List_<_Crimen_> _crimenesPrioridadMayorOrdenadosPorDistrito(Integer prioridad)_: Devuelve una lista de crímenes que superan la prioridad dada como parámetro ordenada por orden alfabético de los distritos.
- _Map_<_String, Long_> _contarDistritosStream()_: Devuelve un Map en el que las claves son los distritos que hay en el objeto y los valores la cantidad de crímenes que se han cometido en dichos distritos. Lo hace mediante el uso de un stream.
- _Map_<_String, Integer_> _contarCrimenesSinResolverPorCategoria()_: Devuelve un Map en el que las claves son las categorías y los valores la cantidad de crímenes sin resolución de dichas categorías.
- _Map_<_Resolucion, Crimen_> _crimenesDeMayorPrioridadPorResoluciones()_: Devuelve un Map en el que las claves son las resoluciones y los valores los crímenes de mayor prioridad con dichas resoluciones.
- _SortedMap_<_DiaSemana, List_<_Crimen_>> _nCrimenesOrdenadosPorDiaDeSemana(Integer n)_: Devuelve un SortedMap en el que las claves son los días de la semana y los valores son listas con los n mayores crímenes de cada.
    - _List_<_Crimen_> _nCrimenesOrdenados(List_<_Crimen_> _listaCrimenes, Integer n)_: Función auxiliar para el método "nCrimenesOrdenadosPorDiaDeSemana". Devuelve una lista de los n crímenes mayores.
- _String categoriaMasRepetida()_: Calcula un Map con el número de veces que aparece cada categoría y devuelve una cadena con la clave de la categoría más repetida junto a su valor.
