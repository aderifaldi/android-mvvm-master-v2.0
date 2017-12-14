package com.ade.mvvm.model.api;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by RadyaLabs PC on 11/10/2017.
 */
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class ProductDetail extends BaseModel implements Serializable{

    private Product product;

    @NoArgsConstructor
    @Data
    public static class Product implements Serializable{

        private long price;
        private String id;
        private String desc;
        private List<String> images;
        private String name;

    }
}
