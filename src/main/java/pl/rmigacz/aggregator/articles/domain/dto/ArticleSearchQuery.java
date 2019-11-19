package pl.rmigacz.aggregator.articles.domain.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArticleSearchQuery {

  private String query;

  private Date beginDate;

  private Date endDate;

  private Sort sort = Sort.BEST;

  private Integer page = 0;

}
