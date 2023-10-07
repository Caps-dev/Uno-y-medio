# Juego Uno y medio
<img src="https://github.com/programacion1-ucr/TP1-persona-5/assets/128566646/e635c488-1431-4621-a46b-84f32f3413af" width="150"  alt="Imagen Uno y Medio Game">





## Miembros del Equipo
- [Allan Picado](https://github.com/Caps-dev)
- [Emir Rojas](https://github.com/arayarojasemir)

## Introducción
El proyecto "Uno y Medio" es una adaptación del clásico juego de cartas UNO, que presenta reglas personalizadas que agregan emoción y desafío a la jugabilidad. Este proyecto fue desarrollado como parte del curso de Programación 1 en nuestra universidad, y sirve como ejercicio de programación en Java para aplicar los conceptos aprendidos en clase.

## Reglas
Este juego Uno y medio incorpora las siguientes reglas personalizadas:
1. El menú del juego ofrece tres opciones, y los jugadores deben ingresar el número correspondiente para hacer su selección.
2. Al inicio del juego, a cada jugador se le reparten 5 cartas.
3. Para iniciar el juego, se utiliza la ultima carta del mazo.
4. Los jugadores deben jugar cartas válidas (cartas del mismo color o número) durante su turno.
5. Las cartas especiales se pueden jugar en cualquier momento (Comer 2, Comer 3,buscar en pila y Cancelar).
6. Si un jugador utiliza una carta "Comer 2", el siguiente jugador debe robar dos cartas del mazo a menos que tenga una carta "Comer 2", "Cancelar" o "Buscar en pila". Si se juega una carta "Comer 2", el jugador siguiente debe tomar 2 cartas a menos que tenga una carta "Cancelar" o "Comer 2", y así sucesivamente. Las cartas "Comer" de mayor valor se pueden jugar sobre las de menor valor, pero no al revés. Por ejemplo, si un jugador juega una "Comer 3", el siguiente jugador no puede jugar una "Comer 2".
7. Si un jugador no tiene cartas válidas para jugar, se le asignan automáticamente cartas hasta que tenga una válida.
8. El juego termina cuando un jugador se queda sin cartas.
9. La carta "Buscar en pila" permite a un jugador buscar entre las cartas que se han jugado y elegir una para jugar. El uso de esta carta no cuenta como parte del turno del jugador, por lo que el jugador puede jugar la carta especial o buscar una carta y luego jugar la carta que encontró en el montón. Se debe tomar en cuenta que se debe de jugar con la ultima carta con color y numero. En caso de ser una carta "Comer" podrá jugar una carta del color seleccionado por el jugador anterior
10. La carta "Cancelar" puede cancelar cualquier secuencia de cartas "Comer" o, si no hay secuencia, saltar el turno del siguiente jugador. Si hay en juego una carta 'buscar en pila' se tendra la opcion de tirar la carta cancelar para que el jugador no la pueda utilizar

## Diagrama de Clases
Hemos incluido un diagrama de clases que representa la lógica del juego y los procesos de toma de decisiones.

Diagrama propuesto al inicio del proyecto:
- [Diagrama de clases Inicial](https://drive.google.com/file/d/1FmIGmljjBBGCz_99Hji-OI_dvqq-8GF_/view?usp=drive_link)

Diagrama al final del proyecto:
- [Diagrama de clases Final](https://drive.google.com/file/d/1h24bE74UdMpWEdHauA-qF_4xCFvswLWG/view?usp=drive_link)

## Diagramas de Flujo
Hemos incluido diagramas de flujo de los métodos más importantes que representan la lógica del juego y los procesos de toma de decisiones.

- [Main](https://drive.google.com/file/d/1HWm2ehOBiA7AVAOxbqkRsFFz_pOOEhI8/view?usp=drive_link)
- [Jugar](https://drive.google.com/file/d/1bxOCE6OigEe7igBMFGdB4HXN-Z1lYRvv/view?usp=drive_link)
- [JugarComputadora](https://drive.google.com/file/d/1_ww9PY4MA7LP2VKQPy4bWTml3yUddB6P/view?usp=drive_link)
- [EsCartaValida](https://drive.google.com/file/d/1GdAncnDBEW1U2OaZfgeIMKg02X8SOSr9/view?usp=drive_link)
