START TRANSACTION;

CREATE DATABASE IF NOT EXISTS `contratosdb`;

USE `contratosdb`;

CREATE TABLE `endereco_empresa` (
    `id_endereco` INT NOT NULL AUTO_INCREMENT,
    `logradouro` VARCHAR(255),
    `cep` VARCHAR(255),
    `numero` BIGINT,
    `bairro` VARCHAR(255),
    `complemento` VARCHAR(255),
    PRIMARY KEY (`id_endereco`)
);

CREATE TABLE `responsavel` (
    `id_responsavel` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255),
    `sobrenome` VARCHAR(255),
    `cpf` VARCHAR(255),
    `email` VARCHAR(255),
    `telefone` VARCHAR(255),
    PRIMARY KEY (`id_responsavel`)
);

CREATE TABLE `tel_responsavel` (
    `id_tel` INT NOT NULL AUTO_INCREMENT,
    `id_responsavel` INT NOT NULL,
    `numero` VARCHAR(255),
    PRIMARY KEY (`id_tel`),
    FOREIGN KEY (`id_responsavel`) REFERENCES `responsavel`(`id_responsavel`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `empresa` (
    `id_empresa` INT NOT NULL AUTO_INCREMENT,
    `id_endereco` INT,
    `id_responsavel` INT,
    `cnpj` VARCHAR(255),
    `razao_social` VARCHAR(255),
    `nome_fantasia` VARCHAR(255),
    `tipo` SMALLINT,
    PRIMARY KEY (`id_empresa`),
    FOREIGN KEY (`id_endereco`) REFERENCES `endereco_empresa`(`id_endereco`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (`id_responsavel`) REFERENCES `responsavel`(`id_responsavel`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `email_empresa` (
    `id_email` INT NOT NULL AUTO_INCREMENT,
    `id_empresa` INT NOT NULL,
    `email` VARCHAR(255),
    PRIMARY KEY (`id_email`),
    FOREIGN KEY (`id_empresa`) REFERENCES `empresa`(`id_empresa`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `tel_empresa` (
    `id_tel` INT NOT NULL AUTO_INCREMENT,
    `id_empresa` INT NOT NULL,
    `numero` VARCHAR(255),
    PRIMARY KEY (`id_tel`),
    FOREIGN KEY (`id_empresa`) REFERENCES `empresa`(`id_empresa`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `cargo` (
    `id_cargo` INT NOT NULL AUTO_INCREMENT,
    `descricao` VARCHAR(255),
    `ativo` TINYINT(1),
    PRIMARY KEY (`id_cargo`)
);

CREATE TABLE `colaborador` (
    `id_funcionario` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255),
    `ativo` TINYINT(1),
    `cpf` VARCHAR(255),
    `contato` VARCHAR(255),
    `id_cargo` INT,
    PRIMARY KEY (`id_funcionario`),
    FOREIGN KEY (`id_cargo`) REFERENCES `cargo`(`id_cargo`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `status` (
    `id_status` INT NOT NULL AUTO_INCREMENT,
    `descricao` VARCHAR(255),
    PRIMARY KEY (`id_status`)
);

CREATE TABLE `contrato` (
    `id_contrato` INT NOT NULL AUTO_INCREMENT,
    `id_cliente` INT NOT NULL,
    `id_funcionario` INT NOT NULL,
    `id_responsavel` INT NOT NULL,
    `id_status` INT,
    `valor` FLOAT,
    `descricao` TEXT,
    `tipo_contrato` TEXT,
    `anexo` BLOB,
    `prazo` DATETIME,
    `data_inicio` DATE,
    `data_final` DATE,
    PRIMARY KEY (`id_contrato`),
    FOREIGN KEY (`id_cliente`) REFERENCES `empresa`(`id_empresa`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (`id_funcionario`) REFERENCES `colaborador`(`id_funcionario`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (`id_status`) REFERENCES `status`(`id_status`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (`id_responsavel`) REFERENCES `responsavel`(`id_responsavel`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `entregavel` (
    `id_entregavel` INT NOT NULL AUTO_INCREMENT,
    `id_contrato` INT NOT NULL,
    `id_status` INT,
    `prazo` INT,
    `descricao` TEXT,
    `observacao` TEXT,
    `just_canc` TEXT,
    `data_canc` DATE,
    `data_final` DATE,
    `data_entrega` DATE,
    PRIMARY KEY (`id_entregavel`),
    FOREIGN KEY (`id_contrato`) REFERENCES `contrato`(`id_contrato`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (`id_status`) REFERENCES `status`(`id_status`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `aditivo` (
    `id_aditivo` INT NOT NULL AUTO_INCREMENT,
    `id_entregavel` INT NOT NULL,
    `tempo_adit` TIME,
    `data_inicio` DATE,
    `data_final` DATE,
    `anexo` BLOB,
    `descricao` TEXT,
    `tipo_contrato` TEXT,
    PRIMARY KEY (`id_aditivo`),
    FOREIGN KEY (`id_entregavel`) REFERENCES `entregavel`(`id_entregavel`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `repactuacao` (
    `id_repactuacao` INT NOT NULL AUTO_INCREMENT,
    `id_contrato` INT NOT NULL,
    `tempo_adit` TIME,
    `data_inicio` DATE,
    `data_final` DATE,
    `anexo` BLOB,
    `descricao` TEXT,
    `tipo_contrato` TEXT,
    PRIMARY KEY (`id_repactuacao`),
    FOREIGN KEY (`id_contrato`) REFERENCES `contrato`(`id_contrato`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `anexo` (
    `id_anexo` INT NOT NULL AUTO_INCREMENT,
    `id_contrato` INT NOT NULL,
    `anexo` BLOB,
    PRIMARY KEY (`id_anexo`),
    FOREIGN KEY (`id_contrato`) REFERENCES `contrato`(`id_contrato`)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

COMMIT;
