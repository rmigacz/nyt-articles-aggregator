package pl.rmigacz.aggregator.articles.infrastructure;

import static java.util.Collections.singletonList;
import static java.util.Objects.nonNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pl.rmigacz.aggregator.articles.domain.ArticleClient;
import pl.rmigacz.aggregator.articles.domain.dto.Article;
import pl.rmigacz.aggregator.articles.domain.dto.ArticleSearchQuery;

class ArticleRestTemplateClient implements ArticleClient {

  private final RestTemplate restTemplate;
  private final ArticleClientResponseParser responseParser;
  private final String apiKeyValue;

  private final Logger logger = LoggerFactory.getLogger(getClass());


  ArticleRestTemplateClient(RestTemplate restTemplate, ArticleClientResponseParser responseParser,
      String apiKeyValue) {
    this.restTemplate = restTemplate;
    this.responseParser = responseParser;
    this.apiKeyValue = apiKeyValue;
  }

  @Override
  @Cacheable(cacheManager = "articlesCacheManager", value = "articlesCache", key = "{#articleSearchQuery.query, #articleSearchQuery.page}", sync = true)
  public List<Article> findArticles(ArticleSearchQuery articleSearchQuery) {
    logger.info("Searching for articles with parameters: {}", articleSearchQuery);

    final HttpEntity<String> entity = buildArticleSearchHttpEntity();
    final String searchUrl = buildArticleSearchUrl(articleSearchQuery);

    final ResponseEntity<String> searchResponse = restTemplate
        .exchange(searchUrl, HttpMethod.GET, entity, String.class);
    final List<Article> foundArticles = responseParser
        .parseSearchResponse(searchResponse.getBody());

    logger.info("Found {} articles", foundArticles.size());

    return foundArticles;
  }

  private HttpEntity<String> buildArticleSearchHttpEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

    return new HttpEntity<>(headers);
  }

  private String buildArticleSearchUrl(ArticleSearchQuery articleSearchQuery) {
    final MultiValueMap<String, String> queryParams = buildQueryParams(articleSearchQuery);
    return UriComponentsBuilder.fromHttpUrl(ArticleApiParameters.ARTICLE_SEARCH_URL)
        .queryParams(queryParams)
        .toUriString();
  }

  private MultiValueMap<String, String> buildQueryParams(ArticleSearchQuery articleSearchQuery) {
    final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    final Format formatter = new SimpleDateFormat(ArticleApiParameters.DATE_FORMAT);

    queryParams.put(ArticleApiParameters.API_KEY, singletonList(apiKeyValue));

    if (nonNull(articleSearchQuery.getQuery())) {
      queryParams.put(ArticleApiParameters.QUERY, singletonList(articleSearchQuery.getQuery()));
    }
    if (nonNull(articleSearchQuery.getBeginDate())) {
      queryParams.put(ArticleApiParameters.BEGIN_DATE,
          singletonList(formatter.format(articleSearchQuery.getBeginDate())));
    }
    if (nonNull(articleSearchQuery.getEndDate())) {
      queryParams.put(ArticleApiParameters.END_DATE,
          singletonList(formatter.format(articleSearchQuery.getEndDate())));
    }

    queryParams.put(
        ArticleApiParameters.SORT, singletonList(articleSearchQuery.getSort().toString()));
    queryParams
        .put(ArticleApiParameters.PAGE, singletonList(articleSearchQuery.getPage().toString()));

    return queryParams;
  }

}
