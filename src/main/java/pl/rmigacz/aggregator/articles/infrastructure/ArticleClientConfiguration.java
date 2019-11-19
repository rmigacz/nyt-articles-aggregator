package pl.rmigacz.aggregator.articles.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import pl.rmigacz.aggregator.articles.domain.ArticleClient;

@Configuration
class ArticleClientConfiguration {

  private Environment env;

  @Autowired
  ArticleClientConfiguration(Environment env) {
    this.env = env;
  }

  @Bean
  ArticleClient nytArticleClient() {
    final RestTemplate restTemplate = new RestTemplate();
    final ArticleClientResponseParser responseParser = new ArticleClientResponseParser();
    final String apiKey = env.getProperty("nyt.api.key");

    return new ArticleRestTemplateClient(restTemplate, responseParser, apiKey);
  }
}
