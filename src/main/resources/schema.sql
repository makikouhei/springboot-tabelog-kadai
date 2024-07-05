-- restaurantsテーブル --
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
    category_id INT NOT NULL,    --カテゴリーID
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- レコード更新日時（自動更新）
);

--カテゴリーテーブル--
CREATE TABLE IF NOT EXISTS categories (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- カテゴリーid
     name VARCHAR(50) NOT NULL, --カテゴリー名
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, --カテゴリー作成日
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP --カテゴリー更新日
  );

-- rolesテーブル --
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- ユーザーテーブル --
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- ユーザーID
    name VARCHAR(50) NOT NULL, -- ユーザー名
    furigana VARCHAR(50) NOT NULL, -- フリガナ
    postal_code VARCHAR(50) NOT NULL, -- 郵便番号
    address VARCHAR(255) NOT NULL, -- 住所
    phone_number VARCHAR(50) NOT NULL, -- 電話番号
    email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス
   password VARCHAR(255) NOT NULL, -- パスワード
    role_id INT NOT NULL, -- 役割ID
    enabled BOOLEAN NOT NULL, -- アカウントの有効状態
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- レコード更新日時
    FOREIGN KEY (role_id) REFERENCES roles (id) -- role_id列に対する外部キー制約、rolesテーブルのid列を参照
);
-- 認証トークンテーブル --
CREATE TABLE IF NOT EXISTS verification_tokens (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     user_id INT NOT NULL UNIQUE,
     token VARCHAR(255) NOT NULL,        
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id) REFERENCES users (id) 
 );