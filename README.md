# Testes Automatizados com Rest Assured

## 📌 Descrição

Este projeto contém testes automatizados de API utilizando **Rest Assured** para validar funcionalidades do servidor de testes **Serverest**. Os testes verificam requisições HTTP e suas respectivas respostas, garantindo a confiabilidade das APIs.

## 🚀 Tecnologias Utilizadas

- **Java** - Linguagem utilizada para os testes  
- **Rest Assured** - Biblioteca para testes de API  
- **JUnit** - Framework de testes unitários  
- **Maven** - Gerenciador de dependências  
- **GitHub Actions** - Para execução dos testes em CI/CD  
- **ExtentReports** - Geração de relatórios detalhados  

## 📂 Estrutura do Projeto

```
/testesapirestserverestassured
│── src/test/java/
│   ├── tests/        # Casos de Teste
│── pom.xml           # Gerenciador de dependências Maven
│── README.md         # Documentação do projeto
│── reports/          # Relatórios gerados pelo ExtentReports
```

## 🛠️ Pré-requisitos

Antes de rodar os testes, certifique-se de ter instalado:

- **Java** (versão 11 ou superior)  
- **Maven**  

Para instalar as dependências, execute:

```sh
mvn clean install
```

## ▶️ Como Executar os Testes

Executar os testes via Maven:

```sh
mvn test
```

Executar testes especificando a classe:

```sh
mvn -Dtest=NomeDaClasseDeTeste test
```

## 💊 Relatório de Testes

Os relatórios de execução dos testes são gerados automaticamente na pasta `reports/`.

### 📣 Importância do Relatório

Os relatórios fornecem uma visão clara dos testes executados, incluindo:

- Testes aprovados e falhos  
- Tempo de execução de cada teste  
- Logs detalhados  

Isso facilita a identificação de falhas e ajuda na melhoria da qualidade do software.

## 🔗 Como Acessar o Relatório ExtentReports

Após a execução dos testes, o relatório pode ser acessado abrindo o arquivo gerado dentro da pasta `reports/`.

## 🛠️ Configuração no GitHub Actions

O projeto possui um workflow configurado para executar os testes automaticamente no GitHub Actions. O workflow está localizado em:

```
.github/workflows/github_actions_api.yml
```

## 📝 Licença

Este projeto está sob a licença MIT. Sinta-se livre para utilizá-lo e contribuir!

