package com.jun.mwj.controller;

import com.jun.mwj.pojo.Book;
import com.jun.mwj.service.BookService;
import com.jun.mwj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> list()  throws Exception{
        return bookService.list();
    }

    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception{
        if(0 != cid){
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keyword){
        if("".equals(keyword)){
            return bookService.list();
        } else {
            return bookService.search(keyword);
        }
    }

    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/upload";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6)
                + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4));
        if(!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        try{
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
