CREATE TABLE IF NOT EXISTS restaurants (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 店舗の一意のID
    name VARCHAR(50) NOT NULL, -- 店舗名
    image_name VARCHAR(255), -- 画像のファイル名
    description VARCHAR(255) NOT NULL, -- 店舗の説明
    opening_time TIME NOT NULL, -- 営業開始時間
    closing_time TIME NOT NULL, -- 営業終了時間
    lowest_price INT NOT NULL, -- 最低価格帯
    highest_price INT NOT NULL, -- 最高価格帯
    postal_code VARCHAR(50) NOT NULL, -- 郵便番号
    address VARCHAR(255) NOT NULL, -- 住所
    phone_number VARCHAR(50) NOT NULL, -- 電話番号
    regular_holiday VARCHAR(100),       -- 定休日
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- レコード更新日時（自動更新）
);
