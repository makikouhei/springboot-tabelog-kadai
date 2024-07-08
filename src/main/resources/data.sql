-- restaurantsテーブル
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 1, 'レストランA', 'restaurant1.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '毎週火曜日',1);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 2, 'レストランB', 'restaurant2.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '毎週水曜日',13);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 3, 'レストランC', 'restaurant3.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',1);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 4, 'レストランD', 'restaurant4.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知件渋谷区神南'     , '030-1234-5678', '毎週火曜日',6);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 5, 'レストランE', 'restaurant5.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '毎週水曜日',4);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 6, 'レストランF', 'restaurant6.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',5);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 7, 'レストランG', 'restaurant7.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '毎週火曜日',17);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 8, 'レストランH', 'restaurant8.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県区梅田'  , '060-2345-6789', '毎週水曜日',4);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 9, 'レストランI', 'restaurant9.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',14);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (10, 'レストランJ', 'restaurant10.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '毎週火曜日',16);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (11, 'レストランK', 'restaurant11.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '毎週水曜日',8);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (12, 'レストランL', 'restaurant12.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',12);

-- categoriesテーブル
INSERT IGNORE INTO categorys (id, name) VALUES (1, '居酒屋');
INSERT IGNORE INTO categorys (id, name) VALUES (2, '和食');
INSERT IGNORE INTO categorys (id, name) VALUES (3, '洋食');
INSERT IGNORE INTO categorys (id, name) VALUES (4, 'ラーメン');
INSERT IGNORE INTO categorys (id, name) VALUES (5, '海鮮');
INSERT IGNORE INTO categorys (id, name) VALUES (6, '寿司');
INSERT IGNORE INTO categorys (id, name) VALUES (7, '焼肉');
INSERT IGNORE INTO categorys (id, name) VALUES (8, 'パスタ');
INSERT IGNORE INTO categorys (id, name) VALUES (9, '鍋料理');
INSERT IGNORE INTO categorys (id, name) VALUES (10, '鉄板焼き');
INSERT IGNORE INTO categorys (id, name) VALUES (11, 'うどん');
INSERT IGNORE INTO categorys (id, name) VALUES (12, 'そば');
INSERT IGNORE INTO categorys (id, name) VALUES (13, 'カレー');
INSERT IGNORE INTO categorys (id, name) VALUES (14, 'パン');
INSERT IGNORE INTO categorys (id, name) VALUES (15, 'ハンバーガー');
INSERT IGNORE INTO categorys (id, name) VALUES (16, 'カフェ');
INSERT IGNORE INTO categorys (id, name) VALUES (17, 'スイーツ');
INSERT IGNORE INTO categorys (id, name) VALUES (18, '名古屋名物');

-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_PREMIUM');

--usersテーブル
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '侍 幸美', 'サムライ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '侍 雅', 'サムライ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '侍 正保', 'サムライ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '侍 真由美', 'サムライ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '侍 安民', 'サムライ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '侍 章緒', 'サムライ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '侍 祐子', 'サムライ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '侍 秋美', 'サムライ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '侍 信平', 'サムライ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', 'password', 1, false);
