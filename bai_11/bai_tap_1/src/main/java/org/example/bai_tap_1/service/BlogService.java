package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements IBlogService {

    private final IBlogRepository blogRepository;
    public BlogService(IBlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public void save(Blog blog) {
        this.blogRepository.save(blog);
    }

    @Override
    public Blog findById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> findAllByNameContaining(String keyword, Pageable pageable) {
        return blogRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public boolean delete(int id) {
        try{
            blogRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
