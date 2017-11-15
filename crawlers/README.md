## Intro
O projeto de crawler foi dividido em duas partes, API e Robo, as explicações detalhadas de como cada um funciona estão em seus respectivos Readmes.
- [API](https://github.com/fgandini/desafios/tree/master/crawlers/api) é uma api rest que recebe alguns parâmetros, faz as chamadas devidas no site(Reddit) e retorna um Json com o que achou.
- [ROBO](https://github.com/fgandini/desafios/tree/master/crawlers/robot) é o robo que se conecta com o Telegram, recebe as mensagens enviadas, faz as chamadas para API e as retorna para o app.

## O que você precisa?
- Gradle
- Java 8

## Compilar
Com o comando 'pack' do gradle ele criará uma pasta \pack em cada um dos subprojetos já com o fatjar e com seu arquivo de config (quando existente) 
```
$ gradle clean build pack
```