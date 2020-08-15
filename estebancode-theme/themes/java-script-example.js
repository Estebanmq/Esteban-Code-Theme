// console.log('Hola mundo');
// Tipos de datos en JS
// String: cadena de caracteres. 'a' 'Hola' 'Hola mundo' "Hola Mundo"
// Boolean: true o false
// Null: nulo suele hacer referencia a que una variable esta vacia
// Number: 1212445 no hace falta ni comillas dobles ni simples. Si tenemos un String "123" no va a ser un numero puesto que es un tipo de dato STRING
// Undefined: es algo que no se encuentra definido, null si esta definida pero su valor es nulo
// Object: es un objeto, estructuras que permiten agrupar todos los anteriores tipos de datos

// Variables se pueden definir de 3 formas: var - let - const

// var miPrimeraBariable = 'lala' mejor no utilizarla
let miPrimerLet = 'Mi primera variable!'
//console.log(miPrimerLet);

// Esto es mutabilidad, tenemos unas variables y despues la cambiamos de valor o reasignar variables es mutabilidad
miPrimerLet = 'Esto ha cambiado'
//console.log(miPrimerLet);

// Boolean
let miBoolean = true
let miOtroBool = false

let miNumero = 0
let miNumero2 = 12
let miNumero3 = -2

//console.log(miNumero, miNumero2, miNumero3, miBoolean, miPrimerLet);

let undef
//console.log(undef);

let nulo = null
//console.log(nulo);

// Objeto vacio
const miPrimerObjeto = {}

// Objeto agrupaciones de datos que hacen sentido entre si
const miObjeto = {
    unNumero:12,
    unString:'Esta cadena de caracteres',
    unaCondicion: true,
}

//console.log(miObjeto);
//console.log(miObjeto.unNumero);
//console.log(miObjeto.unString);
//console.log(miObjeto.unaCondicion);

// Arreglo
const arrVacio = []
const arr = [1, 2, 'Hola', 'Mundo', miObjeto]

// console.log(arrVacio, arr);
// Agregar elementos al arreglo
arr.push(5)
//console.log(arr);

arrVacio.push(5)
arrVacio.push(3)
arrVacio.push(1)
arrVacio.push('Hola')
arrVacio.push(miPrimerLet)
//console.log(arrVacio);

const suma = 1 + 2
const restar = 1 - 2
const multiplicar = 2 * 3
const division = 9 / 3

//console.log(suma, restar, multiplicar, division);

const modulo = 10 % 3 // nos da el resto
//console.log(modulo)

let num = 5
// Const no nos deja cambiar el valor que contiene la variable
num++
num--
num += 5
num -= 5
num *= 5
num /= 2

//console.log(num);

// Operadores de comparacion

const resultado1 = 5 === 6
// Igualdad no estricta Strings pueden ser iguales a numeros si el valor es el mismo
const resultado2 = 5 == '5'
const resultado3 = 5 === '5'
const resultado4 = 5 === 5

//console.log(resultado2, resultado3);

const resultado5 = 5 < 6
const resultado6 = 5 < 5
const resultado7 = 5 > 6
const resultado8 = 5 > 4

//console.log(resultado5, resultado6, resultado7, resultado8);

const resultado9 = 5<=5
const resultado10 = 5<=6
const resultado11 = 5>=5
const resultado12 = 5 >= 6

//console.log(resultado11, resultado12); 

const resultado13 = 5 !== 6
const resultado14 = 5 != '5'
//console.log(resultado13, resultado14);

// Operadores logicos: or ||, and && y not !
const resultadoOr = false || false || 'Hola' || 'Mundo'// or pilla el primero que sea true la unica forma de que sea false es que ambos valores sean falsos
// String, numeros, objetos todos evaluan en true a excepcion del 0 qiue evalua como falso
//console.log(resultadoOr);

const resultadoAnd = true && false //Funciona similar a or pero solo busca el primer false
//console.log(resultadoAnd);

const resultadoNot = !true // va a tener el valor completamente distinto pasado como valor
//console.log(resultadoNot);


// Controles de flujo

// Control de flujo If
const edad = 5
if (edad > 5 && edad < 18) {
    console.log('El ninio puede jugar');
} else {
    console.log('El ninio no puede jugar :( ');
}

//Control de flujo while
let x = false
while (x) {
    console.log(x);
    x = false
}

// Control de flujo switch

let y = 3
switch (y) {
    case 1:
        console.log('Yo soy caso 1');    
        break;
    case 2:
        console.log('Chanchito felis');
        break;
    case 3:
        console.log('Chanchito triste');
        break;
    default:
        console.log('No hay chanchitos');
        break;
}

// Control de flujo for
// for (let i=0;i < 10;i++) {
//     console.log(i);
// }

// Acceder a arreglos
const numero = [1,2,3,4,5]
for (let i=0;i<numero.length;i++) {
    console.log(numero[i]);
}