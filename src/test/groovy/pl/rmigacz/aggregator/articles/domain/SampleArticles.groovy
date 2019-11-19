package pl.rmigacz.aggregator.articles.domain

import groovy.transform.CompileStatic
import pl.rmigacz.aggregator.articles.domain.dto.Article
import pl.rmigacz.aggregator.articles.domain.dto.Headline

import java.time.LocalDateTime

@CompileStatic
trait SampleArticles {

    final Article donaldTrumpResponse = Article.builder()
            .headline(Headline.builder().main("Democrats Are Determined to Spotlight Trump’s Misdeeds, but Remain Divided on How").build())
            .snippet("House Democrats, returning from a weeklong recess, see an opening to focus public attention on presidential misconduct, but they are split over impeachment.")
            .id("5cf3def349f0eacbf1f9306a")
            .publicationDate(LocalDateTime.now().toString())
            .newsDesk("Washington")
            .sectionName("U.S.")
            .subsectionName("Politics")
            .webUrl("https://www.nytimes.com/2019/06/02/us/politics/democrats-impeachment-mueller.html")
            .build()

}