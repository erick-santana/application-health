# Application Heath Monitoring

Sistema que automatiza o monitoramento de aplicações. 

Ela envia um email de alerta em caso de queda da aplicação e fica aguardando alguma alteração no estado para que seja enviado um novo email de que a aplicação se encontra ativa.

Implementa o Design Pattern Observer para realizar o monitoramento, o sistema aguarda receber uma mensagem da aplicação monitorada com o seu nome no corpo.
