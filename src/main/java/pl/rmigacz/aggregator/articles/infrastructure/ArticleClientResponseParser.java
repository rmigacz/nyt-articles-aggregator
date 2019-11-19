package pl.rmigacz.aggregator.articles.infrastructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import pl.rmigacz.aggregator.articles.domain.dto.Article;

class ArticleClientResponseParser {

  private final ObjectMapper objectMapper;
  private final Logger logger = LoggerFactory.getLogger(getClass());


  ArticleClientResponseParser() {
    objectMapper = new ObjectMapper();
  }

  List<Article> parseSearchResponse(String responseBody) {
    try {
      final JsonNode rootNode = objectMapper.readTree(responseBody).path("response").path("docs");
      final ObjectReader objectReader = objectMapper.reader()
          .forType(new TypeReference<List<Article>>() {
          });
      return objectReader.readValue(rootNode);

    } catch (IOException e) {
      logger.error("Unable to parse articles: ", e);
    }
    return new ArrayList<>();
  }
}
