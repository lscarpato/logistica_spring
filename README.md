## Sistema de busca de melhor rota logística:

:truck:

### Arquitetura:

* REST Services
* JPA (backed by Hibernate) 
* Spring core (to wire everything) 4.0
* Spring data 1.5.2
* Spring MVC  4.0
* Hibernate 4.3.1
* Junit 4.8.1
* Mockito 1.9.5
* Derby 10.4.2
 
São disponibilizadas no projeto as seguintes Url's:
 
#### Para gravar uma malha logística, enviar a requisição via POST:
 
Parmetros do header: Content-Type	application/json

* http://localhost:8080/logistica-api/malhas

Corpo da request no formato json, conforme exemplo abaixo:

```{"nome":"rota 1", "pontoOrigem":"A","pontoDestino":"B", "distancia":10 }```

#### Para buscar uma malha logística:
 
* http://localhost:8080/logistica-api/malhas/{id}

#### Para deletar uma malha logística, enviar a requisição como DELETE: 
 
* http://localhost:8080/logistica-api/malhas/{id}
  
#### Para buscar a melhor rota logística:
 
* http://localhost:8080/logistica-api/trajetos?origem={origem}&destino={destino}&autonomia={autonomia}&valorLitro={valorLitro}
