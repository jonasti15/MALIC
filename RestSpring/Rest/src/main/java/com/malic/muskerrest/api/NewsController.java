package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.news.NewsDao;
import com.malic.muskerrest.entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/news")
public class NewsController {

    @Autowired
    private NewsDao newsDao;

    @GetMapping("/all")
    public ResponseEntity<List<News>> getAllNews(){
        return ResponseEntity.ok(newsDao.getActiveNews());
    }
}
