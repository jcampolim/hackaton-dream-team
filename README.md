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

## Explicando o código (Class Main)

### main
Inicia a execução do programa, cria uma lista de árvores e adiciona uma nova árvore à essa lista. Depois, chama o método 'readFile' que recebe a lista de árvores e o arquivo csv como parâmetros.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/e54552fb-6d70-4bae-8fd2-0920872f0485)

### readFile
É o método responsável por ler o arquivo CSV pelo caminho 'filePath' e processar os seus dados

#### Processamento do CSV
O arquivo CSV é lido através de um 'FileReader', um 'Scanner' percorre cada conteúdo do arquivo, dois mapas são criados para armazenar os nós de backup e destino, uma lista auxiliar 'auxList' é criada para armazenar temporariamente os nós lidos do arquivo, a variável 'initialNode' é inicializada como nula e o cabeçalho do arquivo é ignorado.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/cd607d2e-533e-408f-898d-d5ba3f44b221)

#### Processamento das linhas
Loop para processar cada linha do arquivo. Cada linha é lida, processada e os dados são armazenados em objetos Node.
Os nós são adicionados às listas de backup e destino correspondentes nos mapas.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/92947d5c-7654-4a1d-9815-29670be37b28)

#### Conexão entre os nós
Percorre os nós na lista auxiliar e conecta os nós de backup e destino aos seus respectivos nós pais, conforme especificado nos mapas.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/8e429a77-e46e-4a3e-a828-ebb36afc7f21)

#### Impressão dos nós
Após conectar os nós, imprime os detalhes de cada nó, incluindo seu ID, nome e nós de origem, destino e backup associados.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/040be8c2-88f4-4ed7-b88e-55fecf241cf9)


#### Mensagem de erro
Se o arquivo não for encontrado, apresenta uma mensagem de erro representando a exceção 'FileNotFoundException'.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/bb9867c5-60ea-416e-96fa-4f155242c565)



