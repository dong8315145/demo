package com.example.demo.repository;

import com.example.demo.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    @Transactional
    public void findOneTest(){
        ProductCategory productCategory=repository.getOne(1);
       // System.out.println("ID:"+productCategory.getCategoryId());
        System.out.println(productCategory.toString());
    //   System.out.println(productCategory.getCategoryName());
    }
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory=repository.getOne(1);
        productCategory.setCategoryType(2);
        ProductCategory result=repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void findByCategoryTypeInTest(){
        List<Integer> categoryTypelist= Arrays.asList(2,3,4);
        List<ProductCategory> list =repository.findByCategoryTypeIn(categoryTypelist);
        System.out.println("len"+list.size());
        Assert.assertNotEquals(0,list.size());
    }

}
