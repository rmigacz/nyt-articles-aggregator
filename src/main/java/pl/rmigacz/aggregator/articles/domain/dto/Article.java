package pl.rmigacz.aggregator.articles.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article implements Serializable {

  @JsonProperty("_id")
  private String id;

  @JsonProperty("pub_date")
  private String publicationDate;

  @JsonProperty("news_desk")
  private String newsDesk;

  @JsonProperty("section_name")
  private String sectionName;

  @JsonProperty("subsection_name")
  private String subsectionName;

  private Headline headline;

  private String snippet;

  @JsonProperty("web_url")
  private String webUrl;


}
