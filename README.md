<div align="center">
  <h1>Alura Forum Hub</h1>
  <I>Challenge Oracle ONE</I>
  
  <h4>Uma API Rest que controla o server-side de um forum da Alura.</h4>
</div>

## Índice 
1. [Requisitos](#requisitos)
2. [Instalação](#instalação)
3. [Autenticação](#autenticação)
4. [Endpoints](#endpoints)
5. [Exemplos de Uso](#exemplos-de-uso)
6. [Tratamento de Erros](#tratamento-de-erros)
 
## Requisitos 
- Java 21;
- Maven (caso vá executar com os comandos mvn).
  
## Instalação
> Será necessário criar o próprio servidor MySQL, crie variáveis de ambiente com os nomes "DB_URL", "DB_USERNAME", "DB_PASSWORD" e (opcionalmente) "API_SECRET".

Acesse os binários e instruções [aqui](https://github.com/CaioMtho/Challenge-ForumHub/releases/tag/challenge).

Alternativa:
Clone o repositório:

```bash
git clone https://github.com/CaioMtho/Challenge-ForumHub
```

Ou instale o zip e extraia.
Em seguida instale as dependências e execute
```bash
cd Challenge-ForumHub
```
```bash
mvn install
```
```bash
mvn spring-boot:run
```


## Autenticação
Use o formato Bearer Token, envie uma requisição post para o endpoint de usuários para registrar, 
e então envie outra requisição post para o endpoint de login (mais informações abaixo).

## Endpoints
Execute a aplicação e acesse pelo navegador as URLs: "/v3/api-docs" e "/swagger-ui.html", para uma documentação descritiva e testes.

## Exemplos de Uso
- POST para /auth
```bash
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgRm9ydW0gSHViIiwic3ViIjoiam9hb0BnbWFpbC5jb20iLCJleHAiOjE3MzUwNjA1ODl9.mkcFdQztlSfGBeAjhnR4wuaur5KztyTOdqprtJ_V0lI"
}
```
- GET para /topico
```bash
{
    "content": [
        {
            "titulo": "Problemas com Lombok",
            "mensagem": "Mais alguém está tendo problemas usando o lombok no Intellij?",
            "dataCriacao": "2024-12-21T12:25:11.554956",
            "status": "ABERTO",
            "autor": {
                "id": "471acad5-1b67-4bd9-96c6-e6bd3ea3ac14",
                "nome": "joazinho",
                "usuario": {
                    "id": "9019255d-2d1f-429b-b337-ad5fa0d7c27a",
                    "nome": "João Grilo",
                    "email": "joao@gmail.com",
                    "senha": "$2a$12$oXM1AuvEsKIqo4h4nZcAbuRAPigUxkhW3upGFgOYf1.xZT4sgr.Iq",
                    "password": "$2a$12$oXM1AuvEsKIqo4h4nZcAbuRAPigUxkhW3upGFgOYf1.xZT4sgr.Iq",
                    "enabled": true,
                    "accountNonLocked": true,
                    "authorities": [
                        {
                            "authority": "ROLE_USER"
                        }
                    ],
                    "username": "joao@gmail.com",
                    "accountNonExpired": true,
                    "credentialsNonExpired": true
                }
            },
            "curso": {
                "id": "81f05cca-b1c7-4526-be35-47b8b0c7f292",
                "nome": "Desenvolvimento Spring-Boot",
                "categoria": "Programação"
            }
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 1,
    "empty": false
}
```
- GET para /topico/{id} com ID não existente:
```bash
{
    "timestamp": "2024-12-24T12:25:18.6915205",
    "status": 404,
    "error": "Resource Not Found",
    "message": "Tópico não encontrado para o ID: 5f684025-6a48-401f-a2c9-05c2439743d5",
    "path": "uri=/topico/5f684025-6a48-401f-a2c9-05c2439743d5"
}
```
## Tratamento de Erros
- 400: Revise o corpo da requisição, caso haja um id na url, garanta que esteja no formato necessário (deve haver mais informações na resposta).
- 403: Verifique se o token foi gerado corretamente e está sendo enviado como bearer token. Caso persista, gere outro token.
- 404: Caso o objeto com a id especificada realmente exista, procure por erros de digitação.
- 500: Verifique a URL da requisição. 
