package com.study.blog.service;

import com.study.blog.entity.Blog;
import com.study.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void write(Blog blog) {
        blogRepository.save(blog);
    }

    public Page<Blog> blogList(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> blogSearchList(String searchKeyword, Pageable pageable) {

        return blogRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public Blog blogView(Integer id) {

        return blogRepository.findById(id).get();
    }

    public void blogDelete(Integer id) {

        blogRepository.deleteById(id);
    }
}

