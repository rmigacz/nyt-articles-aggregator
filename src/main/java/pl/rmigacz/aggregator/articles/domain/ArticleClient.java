package pl.rmigacz.aggregator.articles.domain;

import java.util.List;

import pl.rmigacz.aggregator.articles.domain.dto.Article;
import pl.rmigacz.aggregator.articles.domain.dto.ArticleSearchQuery;

public interface ArticleClient {

  List<Article> findArticles(ArticleSearchQuery articleSearchQuery);
}
