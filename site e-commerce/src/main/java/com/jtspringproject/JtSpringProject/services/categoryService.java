package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.model.Category;
import com.jtspringproject.JtSpringProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class categoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public boolean deleteCategory(int id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Category updateCategory(int id, String name) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(name);  // Met à jour le nom
            return categoryRepository.save(category);
        }
        return null;
    }

    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
