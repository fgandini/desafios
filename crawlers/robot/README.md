## Robo Telegram + Reddit

### Rodar

Depois de ter rodado o comando para compilar (gradle clean build pack), navegue até a pasta robot/build/pack, lá devem estar dois arquivos ("robot-1.0-all.jar" e "config.yml"). 

Se sim, agora é só rodar o jar passando o seguinte parametro (path/config), como no exemplo abaixo:
```
$ java -jar robot-1.0-all.jar config
```

### Exemplo

No telegram, procurar pelo robo **@gandini_bot**.

Uma vez conectado é só passar o comando **/nadaprafazer** + a lista de subreddits que deseja separados por ";", ex: **/nadaprafazer pics;cats**
