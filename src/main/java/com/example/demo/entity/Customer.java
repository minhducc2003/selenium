package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^0\\d{9,11}$",
            message = "Số điện thoại phải bắt đầu bằng 0 và có độ dài 10-12 ký tự"
    )
    private String phoneNumber;

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(min = 3, max = 50, message = "Tên khách hàng phải từ 3-50 ký tự")
    @Pattern(
            regexp = "^[\\p{L} ]+$",
            message = "Tên khách hàng chỉ chứa chữ cái, khoảng trắng và dấu"
    )
    private String customerName;

    @NotNull(message = "Nhóm khách hàng không được để trống")
    @Enumerated(EnumType.STRING)
    private CustomerGroup customerGroup;

    public enum CustomerGroup {
        MT("Modern Trade"),
        HO("Head Office"),
        KT("Key Trade");

        private final String description;

        CustomerGroup(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Getters and setters
}