package com.example.demo.View;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCategoryVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

}
