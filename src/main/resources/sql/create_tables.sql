
\set AUTOCOMMIT off

DROP TABLE IF EXISTS eshop.product_label_link;
DROP TABLE IF EXISTS eshop.product;
DROP TABLE IF EXISTS eshop.product_label;
DROP TABLE IF EXISTS eshop.cart_product;
DROP TABLE IF EXISTS eshop.cart;

CREATE TABLE IF NOT EXISTS eshop.product
(
    id              INTEGER          PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name            VARCHAR(200)     NOT NULL UNIQUE,
    price           DOUBLE PRECISION NOT NULL,
    added_at        DATE             NOT NULL
);

CREATE TABLE IF NOT EXISTS eshop.product_label
(
    id         INTEGER PRIMARY KEY,
    label_text TEXT    NOT NULL
);

CREATE TABLE IF NOT EXISTS eshop.product_label_link
(
    product_id      INTEGER NOT NULL,
    label_link_id   INTEGER NOT NULL,
    PRIMARY KEY(product_id, label_link_id),

	CONSTRAINT product_id_fk
        FOREIGN KEY (product_id)
        REFERENCES eshop.product (id),

    CONSTRAINT label_link_id_fk
        FOREIGN KEY (label_link_id)
        REFERENCES eshop.product_label (id)
);

CREATE TABLE IF NOT EXISTS eshop.cart
(
    id          INTEGER          PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    checked_out BOOLEAN          NOT NULL DEFAULT FALSE,
    total_cost  DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS eshop.cart_product
(
    cart_id    INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity   INTEGER NOT NULL,

    CONSTRAINT cart_id_fk
            FOREIGN KEY (cart_id)
            REFERENCES eshop.cart (id),

    CONSTRAINT product_id_fk
        FOREIGN KEY (product_id)
        REFERENCES eshop.product (id)
);

COMMIT;