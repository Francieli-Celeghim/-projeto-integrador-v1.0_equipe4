*** Settings ***

Library             SeleniumLibrary
Resource            car.resource
Test Setup          Abrir o site Ctd Cars
Test Teardown       Close Browser


*** Test Cases ***

Validação do cadastro
    Clicar em Criar conta
    Preencher os campos do cadastro
    Validação da confirmação que o cadastro foi realizado com sucesso


Validação do Login e da Reserva
    Clicar em Criar Login
    Preencher E-mail
    Preencher Senha
    Clicar em Entrar
    Clicar em “Conheça nossa Frota”
    Validação Caoa Chery iCar
    Clicar em "Mais Detalhes"
    Clicar em "Reserve Agora"
    Validar as datas e reservar
    Validar "Muito obrigado!"
    Validar "Sua reserva foi feita com sucesso."
    Clicar em "Voltar"
    