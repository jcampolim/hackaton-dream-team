# Desafio Hackathon 2024 Makenzie
## História
Eu enquanto Colaborador H Hiperstream, necessito de um sistema que com base no modelo de dados sugerido seja capaz de devolver um desenho de diagrama que mostre o Fluxo da informação com base em duas pastas diferentes. 
O Diagrama não precisa necessariamente seguir o do exemplo, 
### Modelo de Dados

| Campo | Descrição |
| --- | --- |
| ID | Identificador do cadastro |
| Nome | Nome da Aplicação |
| PastaOrigem | Pasta onde a aplicação espera a chegada de um arquivo |
| PasteDestino | Pasta onde a aplicação gera seu resulta após o processamento |
| PastaBackup | Local onde a aplicação copia o arquivo de origem  assim que processar |
#### Exemplo de dados:

| ID | Nome | PastaOrigem | PastaDestino | PastaBackup |
| --- | --- | --- | --- | --- |
| 1 | Aplicacao1 | C:\EntradaA\ | C:\Entrada2\ | C:\Guarda\ |
| 2 | Aplicacao2 | C:\Entrada2\ | C:\Aplicacao2Dest\ | C:\Voa |
| 3 | Aplicacao3 | C:\Voa |  | C:\Guarda\ |
| 4 | Aplicacao4 | C:\Entrada2\ |  | C:\Guarda\ |
| 5 | Aplicacao5 | C:\monitorada\3k\ | C:\monitorada\ds\ | C:\monitorada\Gd\ |
| 6 | Aplicacao6 | C:\Aplicacao2Dest\ |  |  |

## Nossa solução
Para resolver o desafio, o grupo desenvolveu um programa em Java que lê um arquivo CSV e transforma cada linha da base de dados em um nó. Cada nó possui seus atributos como ID, nome, pasta origem, pasta destino e pasta backup e, a partir disso, um grafo é criado tendo as conexões entre os nós geradas pelos relacionamentos entre as pastas de origem e de backup. Por fim, usamos a biblioteca ----- para gerar uma representação visual do grafo criado, representando a organização dos dados.

## Classes
### Main
Representa a classe principal do projeto. É onde ocorre a leitura do arquivo CSV e a criação do grafo a partir da base de dados disponibilizada no arquivo lido.
### Node
Representa um nó da estrutura do grafo. Contém os atributos ID, nome, origem, destino e backup, juntamente com métodos para adicionar conexões a outros nós.
