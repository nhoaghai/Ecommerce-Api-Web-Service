

create table user(
                     user_id bigint auto_increment primary key,
                     userName varchar(100) unique check ( 6< userName < 100 ),
                     password varchar(255),
                     email varchar(255),
                     fullName varchar(100) not null,
                     status bit,
                     phone varchar(15) not null ,
                     address varchar(255) not null,
                     avatarImgUrl text,
                     createAt DATE,
                     updateAt date
);
create table role(
                     role_id bigint auto_increment primary key ,
                     roleName enum('ADMIN','USER')
);
create table category(
                         category_id bigint auto_increment primary key ,
                         categoryName varchar(100) not null ,
                         description text,
                         status bit
);
create table product(
                        product_id bigint auto_increment primary key ,
                        sku varchar(100) unique ,
                        productName varchar(100) not null unique ,
                        description text,
                        unitPrice DECIMAL(10,2),
                        stock int check ( stock >= 0 ),
                        imgUrl text,
                        category_id bigint,
                        createAt DATE,
                        updateAt date,
                        constraint fk_1 foreign key (category_id) references category(category_id)
);
create table `order`(
                        order_id bigint auto_increment primary key ,
                        serialNumber varchar(100) not null unique,
                        user_id bigint not null ,
                        totalPrice decimal(10,2),
                        status enum('WAITING','CONFIRM', 'DELIVERY', 'SUCCESS', 'CANCEL', 'DENIED'),
                        note varchar(100),
                        receiveName varchar(50),
                        receiveAddress varchar(255),
                        receivePhone varchar(15),
                        creatAt date,
                        updateAt date,
                        constraint fk_2 foreign key (user_id) references user(user_id)
);
create table orderDetail(
                            order_id bigint  ,
                            product_id bigint ,
                            name varchar(100),
                            unitPrice decimal(10,2),
                            orderQuantity int check ( orderQuantity > 0 ),
                            constraint pk_orderDetail primary key (order_id,product_id),
                            constraint fk_3 foreign key (order_id) references `order`(order_id),
                            constraint fk_4 foreign key (product_id) references product(product_id)
);
create table shoppingCart(
                             shopping_cart_id int auto_increment primary key ,
                             product_id bigint,
                             user_id bigint,
                             orderQuantity int check ( orderQuantity > 0 ),
                             constraint fk_5 foreign key (product_id) references product(product_id),
                             constraint fk_6 foreign key (user_id) references user(user_id)
);
create table address(
                        address_id bigint auto_increment primary key ,
                        user_id bigint,
                        fullAddress varchar(255),
                        phoneNumber varchar(15),
                        receiveName varchar(50),
                        constraint fk_7 foreign key (user_id) references user(user_id)
);
create table wishList(
                         wish_list_id bigint auto_increment primary key ,
                         user_id bigint,
                         product_id bigint,
                         constraint fk_8 foreign key (user_id) references user(user_id),
                         constraint fk_9 foreign key (product_id) references product(product_id)
);

