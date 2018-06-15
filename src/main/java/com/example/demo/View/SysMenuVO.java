package com.example.demo.View;

import lombok.Data;

@Data
public class SysMenuVO {

    private String id ;

    private String text;

    private String url;

    private SysMenuVO[] childrens;

}
