## API Reddit

### Rodar

Depois de ter rodado o comando para compilar (gradle clean build pack), navegue até a pasta api/build/pack, lá devem estar dois arquivos ("api-1.0-all.jar" e "config.yml"). 

Se sim, agora é só rodar o jar passando os seguintes parametros (server path/config), como no exemplo abaixo:
```
$ java -jar api-1.0-all.jar server config
```

A api rodará nas portas 10000(api) e 10001(admin), as portas podem ser alteradas pelo config.yml.


### Exemplo

Existe uma collection do postman para ajudar!

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/41c5d303ae6dca6421dd)

##### Chamada:

- **<code>GET</code> http://localhost:10000/crawler/reddit/hot?minimo-votos=5000&subreddits=funny;gifs**

##### Retorno:

```json
[
    {
        "subreddit": "funny",
        "threads": [
			{
                "title": "Deal with it..",
                "votes": 13148,
                "self_link": "https://v.redd.it/jkiou3dsk0yz",
                "subreddit": "funny",
                "comments": {
                    "link": "https://www.reddit.com/r/funny/comments/7cz8em/deal_with_it/",
                    "count": "80"
                }
            }, ...
```
