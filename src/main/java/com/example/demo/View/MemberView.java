package com.example.demo.View;

import com.example.demo.View.BaseView.PageView;
import lombok.Data;

@Data
public class MemberView extends PageView {

    private String name;

    private String create_time;

    private String update_time;

}
