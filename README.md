# FairShareEats

## Sobre o Projeto

  A ideia deste desafio é resolver um problema comum no dia-a-dia de quem divide almoços/lanches com a equipe de trabalho. Vamos imaginar que você e mais um colega queiram dividir um lanche que estão pedindo pelo iFood/Uber Eats utilizando seu smartphone. Fica fácil descobrir quanto seu colega deverá lhe pagar quando não existe nenhum desconto ou valor de entrega, porém quando estes fatores entram em questão, simplesmente dividir o valor no meio pode não ser o mais justo.

Para ilustrar melhor esta situação, vamos supor que você pediu um hamburguer de R$40,00 e uma sobremesa de R$2,00, enquanto seu amigo pediu apenas um sanduíche de R$8,00. Logo, você pagará inicialmente um total de R$42,00 enquanto seu amigo pagará R$8,00. Porém, considere também que o pedido total teve um valor adicional de R$8,00 pela entrega e que houve um desconto de R$20,00 no total do pedido. Sendo assim, é justo que a entrega e o desconto sejam proporcionais ao valor dos itens que cada um comprou. Neste caso, temos o seguinte:

Hamburguer = 40,00
Sobremesa = 2,00
Sanduíche (amigo) = 8,00
Total = 50,00
Entrega = 8,00
Desconto = 20,00
Total a pagar = 38,00
Logo, dos R$38,00, o seu amigo deverá pagar R$6,08, enquanto você pagará R$31,92, que corresponde ao desconto e entrega proporcionais aos itens que foram solicitados por cada um.

Com o objetivo de facilitar esta conta, você deverá criar uma pequena aplicação que irá calcular o total que seus amigos deverão lhe pagar quando dividirem um almoço. Ao final, você deverá simplificar o pagamento gerando um link do Picpay (ou outra carteira de sua preferência), para enviar a cobrança a seus amigos. *Obs.: Podem existir inúmeras formas de facilitar esta cobrança, e o Picpay aqui mencionado é apenas uma delas, portanto deixe seu código adaptável para receber quaisquer outras formas de pagamento que poderão ser posteriormente integráveis à sua aplicação.

## Tecnologiaas Utilizadas
  
  Para o desenvolvimento desta API foi utilizada a linguagem JAVA versão 17, Spring Boot na versão 3.
  Starter Test com JUNIT para realização de testes, a IDE Eclipse.
  
## Preparação do ambiente
  
  Para utilizar o projeto deve-se ter instalado o [Java 17](https://www.oracle.com/java/technologies/downloads/#java17), como IDE Eclipse conforme o sistema operacional. Após isto, efetuar o download e importação do projeto na IDE.
  Com o projeto importado, executar o Build no Eclipse e logo depois executar os testes.
  Com a execução do Build a aplicação web estará disponível em [http://localhost:8080/index.html](http://localhost:8080/index.html). 
  
## Funcionalidades
  Para obter o valor que cada pessoa deve pagar, proporcionalmente, assim como um QR Code para o pagamento, preenche os dados dos pedidos de cada pessoa na tela e clicar em Calcular, obtendo-se o valor, a porcentagem e o QRCode.
  A API pode ser acessada via Postman ou outro aplicativo de teste de API 
  URL: [http://localhost:8080/api/v1/fairShareEats/calculateFairShareEatsByPerson](http://localhost:8080/api/v1/fairShareEats/calculateFairShareEatsByPerson)
Método: POST
Request body exemplo:
{
    "orderByPerson": [
        {
            "personName": "João",
            "items": [
                {
                    "name": "Hamburger",
                    "price": 40
                },
                {
                    "name": "Sobremesa",
                    "price": 2.00
                }
            ]
        },
        {
            "personName": "Pedro",
            "items": [
                {
                    "name": "Sanduíche",
                    "price": 8
                }
            ]
        }
    ],
    "shipping": 8,
    "discount": 20
}
  
