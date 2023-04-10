# Projeto desafio-oneracao

Este projeto usa quarkus com maven com o objetivo de servir como prova de conceito para o uso das tecnologias Java , Kafka e banco de dados dynamodb

## Executando a aplicação local

Primeiramente, você precisa criar os arquivos de configuração para simular a aplicação com o banco de dados dynamodb localmente, siga os passos abaixo.

## Primeiro Passo

Crie a pasta no nível `src/scripts` , dentro da pasta scripts crie o arquivo `init-dynamodb.sh`, copie e cole o script abaixo.

#!/bin/bash

#!/bin/bash

# -- > Create DynamoDb Table
echo Criando  DynamoDb \'tb_riscos\' table ...
echo $(awslocal dynamodb create-table --cli-input-json '{"TableName":"tb_riscos", "KeySchema":[{"AttributeName":"id_cadastro","KeyType":"HASH"},{"AttributeName":"id_documento","KeyType":"RANGE"}], "AttributeDefinitions":[{"AttributeName":"id_cadastro","AttributeType":"S"},{"AttributeName":"id_documento","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}')


# --> List DynamoDb Tables
echo Listando tabelas ...
echo $(awslocal dynamodb list-tables)

# --> Inserindo registro
echo Criando registro na tabela
echo $(awslocal dynamodb put-item --table-name tb_riscos --item '{"id_cadastro": {"S": "1b42efe8-3a86-4ac2-9815-0a859223f35b"},"id_documento": {"S": "94278653000"},"id_documento": {"S": "94278653000"},"Nome": {"S": "Joao Aristos"},"Telefone": {"S": "912346546"},"Batizado": {"S": "true"},"Membro": {"S": "false"},"genero": {"S": "masculino"},"Endereco": {"S": "ruateste,123"},"bairro": {"S": "pq viana"},"cidade": {"S": "barueri"},"cep": {"S": "06439210"},"timestamp": {"S": "2022-11-07T23:16:42.307Z"}}')

echo Verificando se o registro foi criado
echo $(awslocal dynamodb scan --table-name tb_riscos)

## Segundo Passo

No nível da pasta `src` , crie o arquivo **.env** e copie e cole o script abaixo, esse script serve para setar as configurações do micro serviço no localhost

# Padrão de porta para a aplicação para ambiente de desenvolvimento local
quarkus.http.port=8080

# Configuração para uso do dynamodb
quarkus.dynamodb.endpoint-override=http://localhost:4566
quarkus.dynamodb.aws.region=sa-east-1
quarkus.dynamodb.aws.credentials.type=static
quarkus.dynamodb.aws.credentials.static-provider.access-key-id=test-key
quarkus.dynamodb.aws.credentials.static-provider.secret-access-key=test-secret


## Terceiro Passo
No terminal, dentro da IDE que esteja utilizando, certique de estar dentro da pasta `raíz do projeto `, ou seja fora da pasta `src`, após certificar execute os comandos abaixo.

1 - `docker-compose down` para certificar que não tem nenhum container do mesmo tipo rodando
2 - `docker-compose up -d` para certificar que não tem nenhum container do mesmo tipo rodando

> **_NOTE:_**  Para executar o container local, você precisa ter o docker desktop instalado na sua máquina.

## Quatro Passo

Precisamos configurar as variaveis de ambiente para simular a aws dentro do container e também validar se a tabela foi criada e contém massa conforme executado no segundo passo.

1 - Acesse o docker desktop e acesse o CLI.
    Dentro do CLI , digite `aws configure` e preencha cada item que aparecer conforme abaixo.
    AWS Access Key ID : Coloque o valor `test-key`
    AWS Secret Access Key : Coloque o valor `test-secret`
    Default region name : Coloque o valor `sa-east-1`
    Default output format : Coloque o valor `json`
> **_NOTE:_**  Perceba que são os mesmos valores pedidos para colocar no arquivo .env conforme segundo passo.

**Verificando se a tabela está criada**

1 - Digite o comando abaixo no CLI do container, para validar que a tabela foi criada 
	aws --endpoint-url=http://localhost:4566 --region=sa-east-1  dynamodb list-tables

2 - Verificando se o dado foi inserido na tabela
aws --endpoint-url=http://localhost:4566 --region=sa-east-1  dynamodb scan --table-name tb_cadastro

## Quinto Passo

Após o container estar rodando, agora você pode subir a aplicação localhost, executando o comando abaixo.

1 - `mvn clean install` para garantir que a aplicação instale as bibliotecas e execute os testes unitários
2 - `mvn quarkus:dev` com este comando a aplicação deve ficar disponível para chamada localhost

```URL localhost do micro serviço
localhost:8080/cadastro
```


```Informações Gerais
```

Link para acessar o contrato swagger openapi
http://localhost:7000/q/swagger-ui/
