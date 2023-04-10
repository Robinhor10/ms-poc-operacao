#!/bin/bash

# -- > Create DynamoDb Table
echo \#\#\#\# Criando  DynamoDb \'tb_riscos\' table ... \#\#\#\#
echo $(awslocal dynamodb create-table --cli-input-json '{"TableName":"tb_riscos", "KeySchema":[{"AttributeName":"id_cadastro","KeyType":"HASH"},{"AttributeName":"id_documento","KeyType":"RANGE"}], "AttributeDefinitions":[{"AttributeName":"id_cadastro","AttributeType":"S"},{"AttributeName":"id_documento","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}')


# --> List DynamoDb Tables
echo \#\#\#\# Listando tabelas ... \#\#\#\#
echo $(awslocal dynamodb list-tables)

# --> Inserindo registro
echo \#\#\#\# Criando registro na tabela \#\#\#\#
echo $(awslocal dynamodb put-item --table-name tb_riscos --item '{"id_cadastro": {"S": "1b42efe8-3a86-4ac2-9815-0a859223f35b"},"id_documento": {"S": "94278653000"},"id_documento": {"S": "94278653000"},"Nome": {"S": "Joao Aristos"},"Telefone": {"S": "912346546"},"Batizado": {"S": "true"},"Membro": {"S": "false"},"genero": {"S": "masculino"},"Endereco": {"S": "ruateste,123"},"bairro": {"S": "pq viana"},"cidade": {"S": "barueri"},"cep": {"S": "06439210"},"timestamp": {"S": "2022-11-07T23:16:42.307Z"}}')

echo \#\#\#\# Verificando se o registro foi criado \#\#\#\#
echo $(awslocal dynamodb scan --table-name tb_riscos)