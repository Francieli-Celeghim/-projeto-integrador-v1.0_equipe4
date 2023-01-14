# Criação da tabela de produtos
CREATE TABLE products(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    descricao VARCHAR(255),
    PRIMARY KEY (id)
);

# Criação da tabela de imagens
CREATE TABLE images(
	id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100),
    url VARCHAR(255),
    PRIMARY KEY (id)
);

# Criação da tabela de categorias
CREATE TABLE categories(
	id INT NOT NULL AUTO_INCREMENT,
    qualificacao VARCHAR(50),
    descricao VARCHAR(255),
    id_imagem INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_imagem) REFERENCES images(id)
);

# Criação da tabela de categorias x produtos
CREATE TABLE categories_products(
	id_produto INT,
    id_categoria INT,
    FOREIGN KEY (id_produto) REFERENCES products(id),
    FOREIGN KEY (id_categoria) REFERENCES categories(id)
);

# Criação da tabela de imagens x produtos
CREATE TABLE images_products(
	id_produto INT,
    id_imagem INT,
    FOREIGN KEY (id_produto) REFERENCES products(id),
    FOREIGN KEY (id_imagem) REFERENCES images(id)
);

# Criação da tabela de características
CREATE TABLE features(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    id_imagem INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_imagem) REFERENCES images(id)
);

# Criação da tabela de características x produtos
CREATE TABLE features_products(
	id_produto INT,
    id_caracteristica INT,
    FOREIGN KEY (id_produto) REFERENCES products(id),
    FOREIGN KEY (id_caracteristica) REFERENCES features(id)
);

# Criação da tabela de cidades
CREATE TABLE cities(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    estado VARCHAR(100),
    PRIMARY KEY (id)
);

# Criação da tabela de funções
CREATE TABLE roles(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    PRIMARY KEY (id)
);

# Criação da tabela de usuários
CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    sobrenome VARCHAR(100),
    email VARCHAR(255),
    senha VARCHAR(50),
    id_funcao INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_funcao) REFERENCES roles(id)
);

# Criação da tabela de reservas
CREATE TABLE bookings(
	id INT NOT NULL AUTO_INCREMENT,
    inicio_reserva DATETIME,
    fim_reserva DATETIME,
    id_cidade INT,
    id_produto INT,
    id_usuario INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cidade) REFERENCES cities(id),
    FOREIGN KEY (id_produto) REFERENCES products(id),
    FOREIGN KEY (id_usuario) REFERENCES users(id)
);

# SEEDING

# Produtos
INSERT INTO products (nome, descricao)
VALUES ();

# Imagens
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Econômico", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg/1024px-2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Compacto", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/2016_Smart_Fortwo_Passion_Automatic_1.0_Front.jpg/1024px-2016_Smart_Fortwo_Passion_Automatic_1.0_Front.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Confort", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Citroen_C4_%282020%29_IMG_4202.jpg/1024px-Citroen_C4_%282020%29_IMG_4202.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Luxo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Mercedes-Benz_S_500_%28W_222%29_%E2%80%93_Heckansicht%2C_6._April_2014%2C_Neuss.jpg/1024px-Mercedes-Benz_S_500_%28W_222%29_%E2%80%93_Heckansicht%2C_6._April_2014%2C_Neuss.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Esportivo", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Porsche_911_No_1000000%2C_70_Years_Porsche_Sports_Car%2C_Berlin_%281X7A3888%29.jpg/1024px-Porsche_911_No_1000000%2C_70_Years_Porsche_Sports_Car%2C_Berlin_%281X7A3888%29.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Hatch", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/2018_Honda_Jazz_%28GK5_MY18%29_VTi-S_hatchback_%282018-08-06%29_01.jpg/1024px-2018_Honda_Jazz_%28GK5_MY18%29_VTi-S_hatchback_%282018-08-06%29_01.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Sedan", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/2013-2016_Toyota_Corolla_%28ZRE172R%29_SX_sedan_%282018-09-17%29_01.jpg/1024px-2013-2016_Toyota_Corolla_%28ZRE172R%29_SX_sedan_%282018-09-17%29_01.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Pickup", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Fiat_Strada_Adventure_1.3_JTD_Multijet_Lumberjack_%28IV%29_%E2%80%93_Heckansicht%2C_5._Oktober_2013%2C_M%C3%BCnster.jpg/1024px-Fiat_Strada_Adventure_1.3_JTD_Multijet_Lumberjack_%28IV%29_%E2%80%93_Heckansicht%2C_5._Oktober_2013%2C_M%C3%BCnster.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Elétrico", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/2019_Tesla_Model_3_Performance_AWD_Front.jpg/1024px-2019_Tesla_Model_3_Performance_AWD_Front.jpg");
INSERT INTO images (titulo, url)
VALUES ("Imagem Categoria Minivan", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/2021_Toyota_Sienna_XLE_Hybrid%2C_front_12.21.21.jpg/1024px-2021_Toyota_Sienna_XLE_Hybrid%2C_front_12.21.21.jpg");

# Categorias
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Econômico", "Carros com baixo consumo", 1);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Compacto", "Carros com tamanho compacto", 2);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Confort", "Carros com amplo espaço interno", 3);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Luxo", "Carros de luxo", 4);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Esportivo", "Carros esportivos", 5);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Hatch", "Carros com formato hatch", 6);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Sedan", "Carros com formato sedan", 7);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Pickup", "Carros com carroceria", 8);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Elétrico", "Carros movidos a energia elétrica", 9);
INSERT INTO categories (qualificacao, descricao, id_imagem)
VALUES ("Minivan", "Carros a partir de 6 lugares", 10);

# CategoriasProdutos
INSERT INTO categories_products (id_produto, id_categoria)
VALUES ();

# ImagensProdutos
INSERT INTO images_products (id_produto, id_imagem)
VALUES ();

# Caracteristicas
INSERT INTO features (nome, id_imagem)
VALUES ();

# CaracteristicasProdutos
INSERT INTO features_products (id_produto, id_caracteristica)
VALUES ();

# Cidades
INSERT INTO cities (nome, estado)
VALUES ();

# Funcoes
INSERT INTO roless (nome)
VALUES ();

# Usuarios
INSERT INTO users (nome, sobrenome, email, senha, id_funcao)
VALUES ();
