CREATE TABLE USERS (
        USER_ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255) NOT NULL,                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
        EMAIL VARCHAR(255) NOT NULL,
        PASSWORD VARCHAR(64) NOT NULL,
        CPF VARCHAR(24) NOT NULL,
        CEP VARCHAR(24) NOT NULL,
        IS_ACTIVE BOOLEAN DEFAULT TRUE
);

CREATE TABLE PRODUTO (
       PRODUTO_ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
       NAME VARCHAR(255) NOT NULL,                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
       QNTD INTEGER NOT NULL,
       PRECO VARCHAR(64) NOT NULL
);

CREATE TABLE PEDIDOS_USER (
       ID INTEGER PRIMARY KEY AUTO_INCREMENT,
       PRODUTO_ID REFERENCES PRODUTO(PRODUTO_ID),
       USER_ID REFERENCES USERS(USER_ID),
       QUANTITY INTEGER NOT NULL,
       PRICE FLOAT NOT NULL
);
