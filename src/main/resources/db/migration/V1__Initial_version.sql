CREATE TABLE code
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    version            BIGINT                NULL,
    `reference`        VARCHAR(255)          NULL,
    merchant_id        BIGINT                NULL,
    code_order_id      BIGINT                NULL,
    code               VARCHAR(255)          NULL,
    pin                VARCHAR(255)          NULL,
    denomination       INT                   NULL,
    provider_reference VARCHAR(255)          NULL,
    exported_at        datetime              NULL,
    created_at         datetime              NULL,
    created_by         VARCHAR(255)          NULL,
    updated_at         datetime              NULL,
    updated_by         VARCHAR(255)          NULL,
    CONSTRAINT pk_code PRIMARY KEY (id)
);

CREATE TABLE code_order
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NULL,
    `reference` VARCHAR(255)          NULL,
    created_at  datetime              NULL,
    created_by  VARCHAR(255)          NULL,
    updated_at  datetime              NULL,
    updated_by  VARCHAR(255)          NULL,
    CONSTRAINT pk_codeorder PRIMARY KEY (id)
);

CREATE TABLE denomination_config
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    version            BIGINT                NULL,
    merchant_id        BIGINT                NULL,
    denomination       INT                   NULL,
    denomination_min   INT                   NULL,
    denomination_max   INT                   NULL,
    buffer_count       INT                   NULL,
    created_at         datetime              NULL,
    created_by         VARCHAR(255)          NULL,
    updated_at         datetime              NULL,
    updated_by         VARCHAR(255)          NULL,
    merchant_config_id BIGINT                NULL,
    CONSTRAINT pk_denominationconfig PRIMARY KEY (id)
);

CREATE TABLE merchant
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NULL,
    external_id BIGINT                NULL,
    provider_id BIGINT                NULL,
    created_at  datetime              NULL,
    created_by  VARCHAR(255)          NULL,
    updated_at  datetime              NULL,
    updated_by  VARCHAR(255)          NULL,
    CONSTRAINT pk_merchant PRIMARY KEY (id)
);

CREATE TABLE merchant_config
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    version             BIGINT                NULL,
    merchant_id         BIGINT                NULL,
    currency            VARCHAR(255)          NULL,
    buffer_count        INT                   NULL,
    external_product_id VARCHAR(255)          NULL,
    created_at          datetime              NULL,
    created_by          VARCHAR(255)          NULL,
    updated_at          datetime              NULL,
    updated_by          VARCHAR(255)          NULL,
    CONSTRAINT pk_merchantconfig PRIMARY KEY (id)
);

CREATE TABLE provider
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    version        BIGINT                NULL,
    type           VARCHAR(255)          NULL,
    implementation VARCHAR(255)          NULL,
    billing_group  VARCHAR(255)          NULL,
    created_at     datetime              NULL,
    created_by     VARCHAR(255)          NULL,
    updated_at     datetime              NULL,
    updated_by     VARCHAR(255)          NULL,
    CONSTRAINT pk_provider PRIMARY KEY (id)
);

CREATE TABLE revokal
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    version    BIGINT                NULL,
    code_id    BIGINT                NULL,
    reason     VARCHAR(255)          NULL,
    created_at datetime              NULL,
    created_by VARCHAR(255)          NULL,
    updated_at datetime              NULL,
    updated_by VARCHAR(255)          NULL,
    CONSTRAINT pk_revokal PRIMARY KEY (id)
);

ALTER TABLE code
    ADD CONSTRAINT FK_CODE_ON_CODEORDER FOREIGN KEY (code_order_id) REFERENCES code_order (id);

ALTER TABLE code
    ADD CONSTRAINT FK_CODE_ON_MERCHANT FOREIGN KEY (merchant_id) REFERENCES merchant (id);

ALTER TABLE denomination_config
    ADD CONSTRAINT FK_DENOMINATIONCONFIG_ON_MERCHANT FOREIGN KEY (merchant_id) REFERENCES merchant (id);

ALTER TABLE denomination_config
    ADD CONSTRAINT FK_DENOMINATIONCONFIG_ON_MERCHANT_CONFIG FOREIGN KEY (merchant_config_id) REFERENCES merchant_config (id);

ALTER TABLE merchant_config
    ADD CONSTRAINT FK_MERCHANTCONFIG_ON_MERCHANT FOREIGN KEY (merchant_id) REFERENCES merchant (id);

ALTER TABLE merchant
    ADD CONSTRAINT FK_MERCHANT_ON_PROVIDER FOREIGN KEY (provider_id) REFERENCES provider (id);

ALTER TABLE revokal
    ADD CONSTRAINT FK_REVOKAL_ON_CODE FOREIGN KEY (code_id) REFERENCES code (id);