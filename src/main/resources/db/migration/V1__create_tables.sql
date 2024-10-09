-- Таблица "Товар"
CREATE TABLE IF NOT EXISTS product (
                                       id SERIAL PRIMARY KEY,
                                       description TEXT NOT NULL,
                                       cost NUMERIC(10,2) NOT NULL,
                                       quantity INTEGER NOT NULL
);
COMMENT ON TABLE product IS 'Таблица, содержащая информацию о товарах';

-- Таблица "Покупатель"
CREATE TABLE IF NOT EXISTS customer (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(50) NOT NULL,
                                        surname VARCHAR(50) NOT NULL
);
COMMENT ON TABLE customer IS 'Таблица, содержащая информацию о покупателях';

-- Таблица "Заказ"
CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      product_id INTEGER NOT NULL,
                                      customer_id INTEGER NOT NULL,
                                      order_date DATE NOT NULL,
                                      quantity INTEGER NOT NULL,
                                      FOREIGN KEY (product_id) REFERENCES product(id),
                                      FOREIGN KEY (customer_id) REFERENCES customer(id)
);
COMMENT ON TABLE orders IS 'Таблица, содержащая информацию о заказах';