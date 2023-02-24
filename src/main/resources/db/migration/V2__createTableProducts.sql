CREATE TABLE if not exists products (
                          id INT NOT NULL AUTO_INCREMENT,
                          cat_id int not null ,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          quantity DECIMAL(10,2) NOT NULL,
                          image varchar(255) not null ,
                          PRIMARY KEY (id),
                          CONSTRAINT fk_product_category
                              FOREIGN KEY (cat_id)
                                  REFERENCES categories(id)
                                  ON DELETE CASCADE
);