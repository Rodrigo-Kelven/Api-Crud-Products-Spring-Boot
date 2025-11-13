#!/bin/bash

# Solicita o token para o usuário
echo "Por favor, insira o seu SONAR_TOKEN:"
read SONAR_TOKEN

# Exporta o token como variável de ambiente
export SONAR_TOKEN=$SONAR_TOKEN

# Executa o comando mvn sonar:sonar
mvn sonar:sonar

