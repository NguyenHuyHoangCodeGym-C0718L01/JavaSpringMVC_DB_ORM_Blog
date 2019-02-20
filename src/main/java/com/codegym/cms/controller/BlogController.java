package com.codegym.cms.controller;

import com.codegym.cms.model.Author;
import com.codegym.cms.model.Blog;
import com.codegym.cms.model.BlogDB;
import com.codegym.cms.model.Tag;
import com.codegym.cms.service.AuthorServiceImpl;
import com.codegym.cms.service.Service;
import com.codegym.cms.service.BlogServiceImpl;
import com.codegym.cms.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    @Qualifier("blog service")
    private Service blogService;

    @Autowired
    @Qualifier("author service")
    private Service authorService;

    @Autowired
    @Qualifier("tag service")
    private Service tagService;

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        List<Blog> blogs = blogService.findAll();
        blog.setId(blogs.get(blogs.size()-1).getId()+1);
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog created successfully");
        return modelAndView;
    }

    @GetMapping("/blog")
    public ModelAndView listCustomers(){
        List<Blog> blogs = blogService.findAll();
        List<BlogDB> list = new ArrayList<>();
        for(int i = 0;i < blogs.size(); i++){
            Blog blog = blogs.get(i);
            int authId = blog.getAuthorId();
            Author author = (Author) authorService.findById(authId);
            int tagId = blog.getTagId();
            Tag tag = (Tag) tagService.findById(tagId);
            BlogDB blogDB = new BlogDB(blog.getId(), blog.getName(), author.getName(), tag.getName(), blog.getContent());
            list.add(blogDB);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", list);
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Blog blog = (Blog) blogService.findById(id);
        if(blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Blog blog = (Blog) blogService.findById(id);
        if(blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:blog";
    }
}
