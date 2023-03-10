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
- _categoria_, de tipo _String_, consultable. Indica el tipo de delito cometido.
- _descripcion_, de tipo _List\<String\>_, consultable. Lista con ciertas especificaciones que describen mejor el crimen.
- _diaSemana_, de tipo _DiaSemana_, consultable. Día de la semana en el que se cometió el crimen. Puede tomar los valores LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO y DOMINGO.
- _distrito_, de tipo _String_, consultable. Distrito de la ciudad desconocida en el que se cometió el crimen.
- _resolucion_, de tipo _Resolucion_, consultable. Resolución del delito. Puede tomar los valores SIN_RESOLUCION, ARRESTADO y ARRESTADO_CITADO.
- _direccion_, de tipo _String_, consultable. Dirección en la que se cometió el delito.
- _latitud_, de tipo _Double_, consultable. Latitud de la localización exacta del crimen.
- _longitud_, de tipo _Double_, consultable. Longitud de la localización exacta del crimen.


**Constructores**: 

- C1: Tiene un parámetro por cada propiedad básica del tipo.
- C2: Crea un objeto de tipo ```Crimen``` a partir de los siguientes parámetros: ```LocalDateTime fecha, String categoria, Resolucion resolucion```.

**Restricciones**:
 
- R1: La fecha no puede ser null y debe ser igual o anterior al día de hoy.
- R2: La categoría no puede ser null.
- R3: La resolución no puede ser null.
***Criterio de igualdad**: Dos crímenes son iguales si su categoría, su descripción, su fecha, su latitud, su longitud y su resolución son iguales.

**Criterio de ordenación**: Por prioridad del delito y .

#### Tipos auxiliares

- DiaSemana, enumerado. Puede tomar los valores LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO y DOMINGO.
- Resolucion, enumerado. Puede tomar los valores SIN_RESOLUCION, ARRESTADO y ARRESTADO_CITADO.

