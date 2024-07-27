package com.ecommerce.services;

import com.ecommerce.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category findById(Integer id);
    Boolean update(Category category);
    Boolean delete(Integer id);
    List<Category> search(String keyword);
    Page<Category> getAll(Integer pageNo);
    Page<Category> search(String keyword,Integer pageNo);
}
