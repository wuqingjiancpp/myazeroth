package com.accendl.web.controller;

import com.accendl.web.dto.Comment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("comment")
@Slf4j
public class CommentController {

    private final DateFormatter dateFormatter;

    public CommentController(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @GetMapping("listPage")
    public String listPage(){

//        int i = 1/0;
        return "/comment/list";
    }

    @GetMapping("addPage")
    public String addPage(){
        return "/comment/add";
    }

    @PostMapping(value="/update", params={"addRow"})
    public String addRow(final Comment comment, final BindingResult bindingResult) throws Exception {
        return "/comment/list";
    }

    @PostMapping(value = "/update", params = {"batchRemoveRow"})
    public String batchRemoveRow(@RequestParam String rowIds, final BindingResult bindingResult){
        return "/comment/list";
    }

    @PostMapping(value="/update", params={"removeRow"})
    public String removeRow(final Comment comment, final BindingResult bindingResult,
                            @RequestParam(value = "removeRow", required = false) Integer rowId) throws Exception {
        return "/comment/list";
    }

    @PostMapping(value = "/update", params = {"batchCheckRow"})
    public String batchCheckRow(@RequestParam String rowIds, final BindingResult bindingResult){
        return "/comment/list";
    }

    @PostMapping(value="/update", params={"checkRow"})
    public String checkRow(final Comment comment, final BindingResult bindingResult,
                            @RequestParam(value = "checkRow", required = false) Integer rowId) throws Exception {
        return "/comment/list";
    }

    @PostMapping(value="/update", params={"reply"})
    public String reply(final Comment comment, final BindingResult bindingResult,
                           @RequestParam(value = "reply", required = false) Integer rowId) throws Exception {
        return "/comment/list";
    }

    @GetMapping(value = "/search")
    public String search(final Comment comment, final BindingResult bindingResult) throws Exception{
        return "/comment/list";
    }

    @ModelAttribute
    public List<Comment> list(@RequestParam int pageSize, @RequestParam int page) throws Exception {
        List<Comment> commentList = new ArrayList<>();
        for (int i=0;i<21;i++){
            Comment comment = new Comment();
            comment.setId(i);
            comment.setPid(0);
            comment.setCreater("dev");
            comment.setCreateTime(new Date());
            comment.setContent("content"+i*i);
            commentList.add(comment);
        }
        List<Comment> childComment = new ArrayList<>();
        for (int i=0;i<10;i++){
            Comment comment = new Comment();
            comment.setId(i+21);
            comment.setPid(3);
            comment.setContent("回复content"+i);
            comment.setCreateTime(new Date());
            comment.setCreater("dev");
            childComment.add(comment);
        }
        commentList.get(3).setChildComment(childComment.toString());
        return commentList;
    }
}
