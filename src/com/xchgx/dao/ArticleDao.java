package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.Article;

@Repository
public class ArticleDao extends BaseDao<Article>{

	@SuppressWarnings("unchecked")
	public List<Article> list(){
		return find("from Article order by date desc, id desc");
	}
	
}
