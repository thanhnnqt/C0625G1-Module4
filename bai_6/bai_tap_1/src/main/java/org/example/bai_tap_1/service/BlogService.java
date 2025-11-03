package org.example.bai_tap_1.service;

import jakarta.transaction.Transactional;
import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService{
    @Autowired
    public IBlogRepository blogRepository;

    public BlogService(IBlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }


    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Transactional
    @Override
    public boolean add(Blog blog) {
        return blogRepository.add(blog);
    }

    @Transactional
    @Override
    public boolean update(Blog blog) {
        return blogRepository.update(blog);
    }

    @Transactional
    @Override
    public void delete(int id) {
        blogRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Blog> search(String keyword) {
        return blogRepository.search(keyword);
    }

    @Transactional
    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id);
    }
}
