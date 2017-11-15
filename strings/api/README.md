## API Manipulação de Strings


### Rodar

Depois de ter rodado o comando para compilar (gradle clean build pack), navegue até a pasta api/build/pack, lá devem estar dois arquivos ("api-1.0-all.jar" e "config.yml"). 

Se sim, agora é só rodar o jar passando os seguintes parametros (server path/config), como no exemplo abaixo:
```
$ java -jar api-1.0-all.jar server config
```

A api rodará nas portas 10555(api) e 10556(admin), as portas podem ser alteradas pelo config.yml.


### Exemplo

Existe uma collection do postman para ajudar!

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/cc876ada241ca972c68f)



##### Chamada:

A Api oferece dois tipos de retorno, um em *text/plain* e outro em *json*. O que determina o tipo de retorno é o header **Accept** na request, ex: 
- *Accept:text/plain* retorná um texto 
- *Accept:application/json* um json.

O Header **Content-Type:application/json** deve estar sempre presente.

**Parâmetros**
- **texto**: (String) texto que deseja formatar. Usar *'\n' ou '\r\n'* quando quiser uma quebra intencional de linha.
- **tamanho_linha**: (inteiro >= 1) quantos caracteres deseja por linha.
- **justificado**: (boleano) *true* para justificar forçado e *false* para não.
- **elasticidade**: (double default=0.8) usado para determinar se a ultima linha será justificada mesmo quando houverem poucas palavras com relação a linha anterior.

**<code>POST</code> http://localhost:10555/texto/formartar**

**Body** 
```json
{
	"texto" : "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.",
	"tamanho_linha" : 40,
	"justificado" : true,
	"elasticidade" : 0.8
}

```

##### Retorno:

```json
{
    "text": "In the beginning God created the heavens\r\nand   the   earth.  Now  the  earth  was\r\nformless  and  empty,  darkness was over\r\nthe  surface of the deep, and the Spirit\r\nof  God  was  hovering  over the waters.\r\n\r\nAnd  God said, 'Let there be light,' and\r\nthere  was light. God saw that the light\r\nwas  good,  and  he  separated the light\r\nfrom  the darkness. God called the light\r\n'day,'   and   the  darkness  he  called\r\n'night.'  And  there  was  evening,  and\r\nthere  was  morning  -  the  first  day.\r\n"
}
```
