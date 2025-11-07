package org.example.product_manage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.product_manage.entity.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;
}

