package com.study.blog.controller;

import com.study.blog.entity.Blog;
import com.study.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/write")
    public String blogWriteForm() {

        return "blogWrite";
    }

    @PostMapping("/blog/write-do")
    public String blogWriteDo(Blog blog, Model model) {
        blogService.write(blog);

        model.addAttribute("message", "글 작성이 완료되었습니다");
        model.addAttribute("searchUrl", "/blog/list");
        return "message";
    }

    @GetMapping("/blog/list")
    public String blogList(Model model,
                           @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                           String searchKeyword) {

        Page<Blog> list = null;
        if (searchKeyword == null) {
            list = blogService.blogList(pageable);
        } else {
            list = blogService.blogSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4,1);
        int endPage = Math.min(nowPage+5,list.getTotalPages());
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "blogList";
    }

    @GetMapping("/blog/view") // localhost:8080/blog/view?id=1
    public String blogView(Model model, Integer id) {

        model.addAttribute("blog", blogService.blogView(id)); // "blog"라는 이름으로 넘겨주고, 그 값으로 오른쪽을 준다.
        return "blogView";
    }

    @PostMapping ("/blog/delete")
    public String blogDelete(Integer id) {
        blogService.blogDelete(id);
        return "redirect:/blog/list";
    }

    @GetMapping("/blog/modify/{id}")
    public String blogModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("blog", blogService.blogView(id));
        return "blogModify";
    }

    @PostMapping("blog/update/{id}")
    public String blogUpdate(@PathVariable("id") Integer id, Blog blog) {
        Blog blogTemp = blogService.blogView(id);
        blogTemp.setTitle(blog.getTitle());
        blogTemp.setContent(blog.getContent());

        blogService.write(blogTemp);

        return "redirect:/blog/list";
    }
}
