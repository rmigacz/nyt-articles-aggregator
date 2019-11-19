package pl.rmigacz.aggregator.articles.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ArticleConfiguration {

  @Bean
  ArticleFacade articleFacade(ArticleClient articleClient) {
    return new ArticleFacade(articleClient);
  }

}
