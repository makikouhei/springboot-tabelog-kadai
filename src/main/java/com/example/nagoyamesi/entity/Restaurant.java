package com.example.nagoyamesi.entity;

import java.sql.Timestamp;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id; // 店舗のID

    @Column(name = "name")
    private String name; // 店舗の名前

    @Column(name = "image_name")
    private String imageName; // 画像のURL

    @Column(name = "description")
    private String description; // 店舗説明

    @Column(name = "opening_time")
    private LocalTime openingTime;  // 営業開始時間
    
    @Column(name = "closing_time")
    private LocalTime closingTime;  // 営業終了時間

    @Column(name = "lowest_price")
    private Integer lowestPrice;  // 最低価格帯

    @Column(name = "highest_price")
    private Integer highestPrice;  // 最高価格帯

    @Column(name = "postal_code")
    private String postalCode; // 郵便番号

    @Column(name = "address")
    private String address; // 住所

    @Column(name = "phone_number")
    private String phoneNumber; // 電話番号

    @Column(name = "regular_holiday")
    private String regularHoliday; // 定休日

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt; // レコード作成日時

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt; // レコード更新日時

}
