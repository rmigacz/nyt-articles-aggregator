package pl.rmigacz.aggregator.articles.infrastructure;

class ArticleApiParameters {

  static final String ARTICLE_SEARCH_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

  static final String API_KEY = "api-key";

  static final String QUERY = "q";
  static final String BEGIN_DATE = "begin_date";
  static final String END_DATE = "end_date";
  static final String SORT = "sort";
  static final String PAGE = "page";

  static final String DATE_FORMAT = "yyyyMMdd";

}
