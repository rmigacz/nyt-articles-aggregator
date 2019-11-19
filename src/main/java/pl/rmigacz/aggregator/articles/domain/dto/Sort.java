package pl.rmigacz.aggregator.articles.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sort {

  BEST("best"),
  NEWEST("newest"),
  OLDEST("oldest");

  private String name;

  Sort(String name) {
    this.name = name;
  }

  @JsonCreator
  public static Sort create(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }

    for (Sort v : values()) {
      if (name.equals(v.name)) {
        return v;
      }
    }

    throw new IllegalArgumentException();
  }

  @Override
  public String toString() {
    return name;
  }
}