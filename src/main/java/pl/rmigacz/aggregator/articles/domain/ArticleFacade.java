package pl.rmigacz.aggregator.articles.domain;

import java.util.List;

import pl.rmigacz.aggregator.articles.domain.dto.Article;
import pl.rmigacz.aggregator.articles.domain.dto.ArticleSearchQuery;

public class ArticleFacade {

  private final ArticleClient articleClient;

  public ArticleFacade(ArticleClient articleClient) {
    this.articleClient = articleClient;
  }

  public List<Article> findArticles(ArticleSearchQuery articleSearchQuery) {
    return articleClient.findArticles(articleSearchQuery);
  }


}

