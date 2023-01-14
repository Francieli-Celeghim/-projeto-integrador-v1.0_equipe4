*** Variables ***

${SITE}                     http://localhost:3000/
${NAVEGADOR}                Chrome 
${CLICAR_CRIAR_CONTA}       css=.header-rightLink:nth-child(2)
${PREENCHER_NOME}           id=nome
${NOME}                     tati657
${PREENCHER_SOBRENOME}      id=sobrenome
${SOBRENOME}                Silva
${PREENCHER_EMAIL}          id=email
${EMAIL}                    tati657@gmail.com
${PREENCHER_SENHA}          id=senha
${SENHA}                    1234567
${BOTAO_REGISTRAR}          xpath=//button[contains(.,'Registrar')]
${TITULO_SUCESSO}            xpath=//h2[contains(.,'Sucesso!')] 
${TEXTO_SUCESSO}             Sucesso!
${CLICAR_LOGIN}                     css=.header-rightLink:nth-child(3)
${PREENCHER_EMAIL_LOGIN}            id=email
${EMAIL_LOGIN}                      francieliceleghim2013@gmail.com
${PREENCHER_SENHA_LOGIN}            id=password
${SENHA_LOGIN}                      1234567
${CLICAR_ENTRAR_LOGIN}              css=button
${CLICAR_CONHEÇA_NOSSA_FROTA}       css=.header-rightLink > span
${TITULO_PRODUTO}                   xpath=(//div[@id='page-container']/ul/li/div/h3[2])[2]
${TEXTO_TITULO_PRODUTO}             Caoa Chery iCar
${CLICAR_MAIS_DETALHES}             xpath=(//div[@id='page-container']/ul/li/button)[2]
${CLICAR_RESERVE_AGORA}             xpath=//button[contains(.,'Reserve Agora')]
${ESCOLHER_DATA_INICIO}             xpath=//*[@id="root"]/div[2]/div[2]/div[2]/div[2]/div[3]/div[1]/div[3]/button[27]
${ESCOLHER_DATA_FIM}                xpath=//*[@id="root"]/div[2]/div[2]/div[2]/div[2]/div[3]/div[1]/div[3]/button[28]
${CLICAR_RESERVAR}                  css=.button-reserva
${TITULO_MUITO_OBRIGADO}            xpath=//div[@id='confirmeReserva-card']/h3
${TEXTO_TITULO_MUITO_OBRIGADO}      Muito obrigado!
${TITULO_RESERVA_SUCESSO}           xpath=//div[@id='confirmeReserva-card']/h5
${TEXTO_TITULO_RESERVA_SUCESSO}     Sua reserva foi feita com sucesso.
${CLICAR_VOLTAR}                    css=.confirmeReservaBtn



*** Keywords ***

Abrir o site Ctd Cars
    Open Browser                    ${SITE}        ${NAVEGADOR}
    Maximize Browser Window
    Set Selenium Speed              0.1

Clicar em Criar conta
    Wait and Click          ${CLICAR_CRIAR_CONTA}

Preencher os campos do cadastro
    Wait and Input Text               ${PREENCHER_NOME}                         ${NOME}
    Wait and Input Text               ${PREENCHER_SOBRENOME}                    ${SOBRENOME}
    Wait and Input Text               ${PREENCHER_EMAIL}                        ${EMAIL}
    Wait and Input Text               ${PREENCHER_SENHA}                        ${SENHA}
    Wait and Click                    ${BOTAO_REGISTRAR}                       

Validação da confirmação que o cadastro foi realizado com sucesso
    Sleep  5
    Wait Until Element is Visible      ${TITULO_SUCESSO}
    Element Should Contain             ${TITULO_SUCESSO}             ${TEXTO_SUCESSO}

Clicar em Criar Login                
    Wait and Click                  ${CLICAR_LOGIN}

Preencher E-mail
    Wait and Input Text              ${PREENCHER_EMAIL_LOGIN}             ${EMAIL_LOGIN}

Preencher Senha
    Wait and Input Text               ${PREENCHER_SENHA_LOGIN}             ${SENHA_LOGIN}

Clicar em Entrar
    Wait and Click                    ${CLICAR_ENTRAR_LOGIN}

Clicar em “Conheça nossa Frota”
    Wait and Click                    ${CLICAR_CONHEÇA_NOSSA_FROTA}

Validação Caoa Chery iCar
    Wait Until Element is Visible      ${TITULO_PRODUTO}
    Element Should Contain             ${TITULO_PRODUTO}                    ${TEXTO_TITULO_PRODUTO}

Clicar em "Mais Detalhes"
    Wait and Click                     ${CLICAR_MAIS_DETALHES}

Clicar em "Reserve Agora"
    Wait and Click                     ${CLICAR_RESERVE_AGORA}

Validar as datas e reservar
    Execute JavaScript               window.scroll(0,477)
    Wait and Click                   ${ESCOLHER_DATA_INICIO}
    Wait and Click                   ${ESCOLHER_DATA_FIM}
    Wait and Click                   ${CLICAR_RESERVAR}

Validar "Muito obrigado!"
    Wait Until Element is Visible      ${TITULO_MUITO_OBRIGADO}
    Element Should Contain             ${TITULO_MUITO_OBRIGADO}             ${TEXTO_TITULO_MUITO_OBRIGADO}

Validar "Sua reserva foi feita com sucesso."
    Wait Until Element is Visible       ${TITULO_RESERVA_SUCESSO}
    Element Should Contain              ${TITULO_RESERVA_SUCESSO}           ${TEXTO_TITULO_RESERVA_SUCESSO}

Clicar em "Voltar"
    Wait and Click                      ${CLICAR_VOLTAR}


Wait and Click
    [Arguments]  ${ELEMENTO}
    Wait Until Element is Visible         ${ELEMENTO}
    Wait Until Element is Enabled         ${ELEMENTO}
    Scroll Element Into View              ${ELEMENTO}
    Click Element                         ${ELEMENTO}

Wait and Input Text
    [Arguments]  ${ELEMENTO}              ${TEXTO}
    Wait Until Element is Visible         ${ELEMENTO}
    Wait Until Element is Enabled         ${ELEMENTO}
    Scroll Element Into View              ${ELEMENTO}
    Input Text                            ${ELEMENTO}        ${TEXTO}

Wait and Select From List
    [Arguments]                       ${ELEMENTO}     ${LABEL}
    Wait Until Element is Visible     ${ELEMENTO}
    Wait Until Element is Enabled     ${ELEMENTO}
    Scroll Element Into View          ${ELEMENTO}
    Select From List By Label         ${ELEMENTO}     ${LABEL}