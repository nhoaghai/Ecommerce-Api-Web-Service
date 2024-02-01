use `e_commerce_pj5`;

insert into address(address_id, fullAddress, phoneNumber, receiveName, user_id)
VALUES (1, 'Số 161 Thạch Bàn, Long Biên, Long Biên, Hà Nội', '0358532562', 'Đào Hoàng Hải', 1),
       (2, '171 Xã Đàn, Đống Đa, Hà Nội', '0998378267', 'Hồ Hùng', 2),
       (3, '37a P.Cảm Hội, Đống Mác, Hai Bà Trưng, Hà Nội', '0966180799', 'Doris Floral', 1),
       (4, 'No.1C P. Đặng Thái Thân, St, Hoàn Kiếm, Hà Nội', '0983131511', 'HaveU', 2),
       (5, '46 Trần Hưng Đạo, Hàng Bài, Hoàn Kiếm, Hà Nội', '0969465045', 'The Cricket Project', 2);

insert into category(category_id, categoryName, description, status)
VALUES (1, 'Coffee', 'Coffee', true),
       (2, 'gift', 'gift shop', true),
       (3, 'Food', 'Food', true);

insert into product(createAt, description, imgUrl, productName, sku, stock, unitPrice, updateAt, category_id)
VALUES ('2024-01-30 21:09:01.000000', 'Coffee shop',
        'https://lh5.googleusercontent.com/p/AF1QipPujvpP1-54qLZ4yPxufgrvmVL86zhOGrkBjzM3=w203-h270-k-no',
        'DEST coffee', UUID(), '100', '35000', '2024-01-30 21:09:01.000000', 1),
       ('2024-01-30 21:09:01.000000', 'Cocktail bar',
        'https://lh5.googleusercontent.com/p/AF1QipMGpuezZ0-tUg_9AG43KB5p1q3weHZ-t1JYOlQN=w408-h272-k-no', 'monochrome',
        UUID(), '100', '350000', '2024-01-30 21:09:01.000000', 1),
       ('2024-01-30 21:09:01.000000', 'Souvenir store',
        'https://lh5.googleusercontent.com/p/AF1QipPpNCyqwgtvrmRgNs8LyE3mMF27CD0MQ8VtNdxl=w408-h544-k-no',
        'Tiệm cốc nhỏ Yliacups', UUID(), '100', '30000', '2024-01-30 21:09:01.000000', 2),
       ('2024-01-30 21:09:01.000000', 'Store',
        'https://lh5.googleusercontent.com/p/AF1QipO1n-kkZxSrBVqTSzyrVF06Nnnnb9g4lgbmLNag=w408-h544-k-no',
        'Phúc Lợi Stamps', UUID(), '100', '50000', '2024-01-30 21:09:01.000000', 2),
       ('2024-01-30 21:09:01.000000', 'Japanese restaurant',
        'https://lh5.googleusercontent.com/p/AF1QipNLHSkNZUXn3dNXDcmWWJgUDn-wCcT9fD5LuXab=w203-h270-k-no',
        'Mì Nhật Neko Ramen', UUID(), '100', '65000', '2024-01-30 21:09:01.000000', 3),
       ('2024-01-30 21:09:01.000000', 'Restaurant',
        'https://lh5.googleusercontent.com/p/AF1QipO7R2yBOFttKzpNbtHTKUiuWsmf69vhHSA1mknt=w408-h283-k-no',
        'Bún Bò Nam Bộ Hải Sẹo', UUID(), '100', '50000', '2024-01-30 21:09:01.000000', 3);

insert into role(role_id, roleName) value (1, 'ADMIN'), (2, 'USER');

insert into shoppingcart(orderQuantity, product_id, user_id)
VALUES (10, 1, 1),
       (5, 2, 1),
       (3, 3, 1),
       (4, 4, 2),
       (5, 5, 2);

insert into orders(createAt, note, receiveAddress, receiveName, receivePhone, serialNumber, status, totalPrice,
                   updateAt, user_id)
VALUES ('2024-01-30 21:09:01.000000', 'note', 'Tổ 1 Long Biên, Hà Nội', 'Đào Hải', '0358532562', UUID(), 'WAITING',
        '210000', '2024-01-30 21:09:01.000000', 1),
       ('2024-01-30 21:09:01.000000', 'note', '3 P. Trần Phú, Hàng Bông, Hoàn Kiếm, Hà Nội', 'Thành Long', '0928588268',
        UUID(), 'CONFIRM', '300000', '2024-01-30 21:09:01.000000', 1),
       ('2024-01-30 21:09:01.000000', 'note', '7 Đ. Văn Cao, Thuỵ Khuê, Tây Hồ, Hà Nội', 'Mạnh Đạt', '0334835555',
        UUID(),
        'SUCCESS', '99000', '2024-01-30 21:09:01.000000', 2),
       ('2024-01-30 21:09:01.000000', 'note', '15 P. Cao Thắng, Đồng Xuân, Hoàn Kiếm, Hà Nội', 'Nguyễn Minh',
        '0983131511', UUID(), 'DELIVERY', '99000', '2024-01-30 21:09:01.000000', 2),
       ('2024-01-30 21:09:01.000000', 'note', '25 P. Hàng Vải, Phố cổ Hà Nội, Hoàn Kiếm, Hà Nội, Vietnam', 'Đức Anh',
        '0378802382', UUID(), 'SUCCESS', '99000', '2024-01-30 21:09:01.000000', 2);

insert into orderdetail(name, orderQuantity, unitPrice, order_order_id, product_product_id)
VALUES ('Cappuccino', '3', 21000, 1, 1),
       ('Americano', '2', 25000, 2, 1),
       ('Latte', '1', 25000, 1, 2),
       ('Ramen', '1', 38000, 3, 2),
       ('Udon', '3', 22000, 4, 2);

insert into wishlist(product_id, user_id)
value (1, 1),
    (2, 1),
     (3, 1),
     (4, 1),
     (5, 2),
     (6, 2),
     (7, 2),
     (8, 2)