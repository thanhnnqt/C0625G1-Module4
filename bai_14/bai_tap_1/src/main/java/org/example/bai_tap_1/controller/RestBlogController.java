package org.example.bai_tap_1.controller;

import org.example.bai_tap_1.dto.BlogDto;
import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.service.IBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/api/blogs")
public class RestBlogController {
    private final IBlogService blogService;

    public RestBlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<Page<Blog>> getAll(Pageable pageable) {
        Page<Blog> blogList = blogService.findAll(pageable);
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(blog, HttpStatus.OK); // 200
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteById(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Blog> updateById(@PathVariable("id") int id,
                                           @RequestBody BlogDto blogDto) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        BeanUtils.copyProperties(blogDto, blog);
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
