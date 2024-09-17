# Atividade: Serialização

## Exercício 01: Salvando dados de uma Conta Bancária

Através dos mecanismos de serialização, implemente uma rotina para salvar uma determinada conta em arquivo.
Esse método deverá ser implementado dentro da própria classe conta.
O arquivo a ser salvo deverá ser nomeado com o número da agencia seguido do numero da conta que está sendo salva (`AGENCIA-CONTA.ser`).

## Exercício 02: Carregando dados de uma Conta Bancária

Utilizando os mesmos mecanismos de serialização, implemente uma rotina para carregar os dados de uma determinada conta.
Esse método deverá receber dois parâmetros de entrada, o número da agência e número da conta, que serão utilizados para localizar o arquivo a ser aberto.
Faça o devido tratamento caso o arquivo não exista.