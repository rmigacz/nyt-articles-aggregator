package pl.rmigacz.aggregator.articles;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.rmigacz.aggregator.articles.domain.ArticleFacade;
import pl.rmigacz.aggregator.articles.domain.dto.Article;
import pl.rmigacz.aggregator.articles.domain.dto.ArticleSearchQuery;

@RestController
class ArticleController {

  private ArticleFacade articleFacade;

  public ArticleController(ArticleFacade articleFacade) {
    this.articleFacade = articleFacade;
  }

  @GetMapping("/article")
  List<Article> findArticles(@RequestBody ArticleSearchQuery articleSearchQuery) {
    return articleFacade.findArticles(articleSearchQuery);
  }
}
