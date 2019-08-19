# avaliacao_softplan
Softplan - Avaliação Técnica Unic

Os projeto foram criados como projetos gradle. Dessa forma, é possível especificar dependências de bibliotecas, convenções de estruturas de pastas, versão de compatibilidade de compilação, dentre outros benefícios do uso de gradle associado ao maven. 

Com relação às estruturas do projeto, foi pensado em estabelecer pacotes de entidades, casos de uso e controladores, seguindo os princípios da Clean Architecture, para haver clara separação entre estas partes.

################  Sobre exercício 1   ####################

A classe GeradorObservacao e seus métodos estão especificados de forma que não seguem alguns princípios que são boas recomendações de programação.

Por exemplo, o método geraObservacao declara uma variável texto, que não é utilizada, fugindo do princípio YAGNI (You ain't gonna need it). Além de ser uma variável desnecessária, em implementações de lógicas mais complexas, isto poderia confundir quem está analisando o código.

Além disso, no método retornaCodigos, quando repete a chamada à cod.toString() mais de uma vez, a prática do DRY (Don't repeat yourself) não é seguida. Seria possível criar uma variável local e referenciá-la, ao invés de chamar o mesmo método mais de uma vez.

Devido às questões mencionadas, foi feita uma reformulação na implementação do serviço de gerar observação de notas fiscais, a fim de tornar adaptável para o outro formato solicitado pelo cliente. 

O projeto foi criado como um projeto gradle. Dessa forma, é possível especificar dependências de bibliotecas, convenções de estruturas de pastas, versão de compatibilidade de compilação, dentre outros benefícios do uso de gradle associado ao maven. 

Com relação à estrutura do projeto, foi pensado em estabelecer pacotes de entidades e casos de uso, seguindo os princípios da Clean Architecture, de haver clara separação entre estas partes.

Foi criada a classe de entidade NotaFiscal, que permite modelar, além do identificador, como já existia na primeira versão do serviço, o valor da nota fiscal, que também será exibido no segundo formato.

O serviço de gerar observação fica representado no método geraObservacao da classe GeradorObservacao: este método segue um padrão de método template, pois é possível extrair um padrão de semelhança na exibição entre os dois formatos. A subclasse GeradorObservacaoSimples monta as partes que compõem a observação no formato original, e a subclasse GeradorObservacaoValoresComTotal monta as partes que exibem a observação no formato original.

Caso um destes serviços de geração de observação seja modificado para um formato que não seja possível extrair um padrão de semelhança, ainda assim é possível sobrescrever o método geraObservacao, fazendo com que o comportamento de exibição esteja conforme ao que se deseja.

Foram criadas classes de testes utilizando o framework JUnit para testar os serviços de geração de observação tanto no formato original, quanto no formato novo. Isto possibilitou a validação do resultado desejado e permitiu a detecção de erros na implementação de forma mais ágil e eficiente, por ter diminuído a necessidade de uso de depuração em tempo de execução.

################  Sobre exercício 2   ####################

Foram criadas as classes que representam as entidades no projeto: Item, Insumo, Composicao e Obra.

A entidade Item é modelada como uma super classe: ela possui os atributos codigo, descricao, unidadeMedida e valorUnitario. Estas são propriedades comuns aos insumos e composições, por isso a decisão de que Insumo e Composicao sejam subclasses de Item.

Foi criada a enum TipoItem, que tem a lógica para criar objetos de um dos tipos (Insumo ou Composicao).

Os objetos do tipo Composicao diferem dos objetos do tipo Insumo porque são compostos por outros itens. A classe Composicao contem um mapa que representa a quantidade de cada item na composicao.

A classe Obra contém as composições de toda a obra. O serviço de visualização pode manipular esse objeto para exibir as informações das composições da obra.

A classe ExtratorObra contém o serviço de extração das informações da obra que estão em arquivo. Para a montagem do objeto Obra, ExtratorObra utiliza uma classe ConstrutorObra, que dirige como serão construídos os objetos de insumo e de composição. ConstrutorObra possui o controle de quais composições e itens já foram criados, e só os instancia se ainda não tiverem sido processados.

Para a extração das informações, foi utilizada a biblioteca Gson para manipular objetos JSON. A classe ItemJSON possui um modelo para os objetos JSON sendo extraídos do arquivo. 

Foram criadas classes ServicoImpressaoComposicao e ServicoImpressaoObra com a implementação da impressão do servico de impressão de composição e obra, respectivamente. ServicoImpressaoObra é composta por ServicoImpressaoComposicao para imprimir cada uma das composições.

As classes de serviço de impressão são uma tentativa de desacoplar os objetos que representam entidades e lógica do sistema de suas visualizações.

Foram criadas classes de testes utilizando o framework JUnit para testar os serviços de impressão de composição e da obra. Testando as partes, foi possível refinar a implementação até chegar ao resultado esperado para o caso de teste em arquivo.

OBS: Foi criada a enum UnidadeMedida para representar unidades de medida. Dessa forma, caso a descrição de uma unidade seja alterada, isso não impacta na implementação da lógica de construção da obra.

OBS: Foi criada a enum FormatoDecimal para representar formatos de exibição de números decimais. A ideia é que, caso haja no futuro a criação de outros serviços de impressão para outra linguagem, a exibição possa ser inserida.

