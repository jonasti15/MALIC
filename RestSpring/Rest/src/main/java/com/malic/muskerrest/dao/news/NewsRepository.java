package com.malic.muskerrest.dao.news;

import com.malic.muskerrest.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByInitDateIsLessThanEqualAndEndDateGreaterThanEqual(
            Date initDate, Date endDate);
}
