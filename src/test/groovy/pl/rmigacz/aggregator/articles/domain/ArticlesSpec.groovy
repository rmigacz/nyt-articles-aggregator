package pl.rmigacz.aggregator.articles.domain

import pl.rmigacz.aggregator.articles.domain.dto.Article
import pl.rmigacz.aggregator.articles.domain.dto.ArticleSearchQuery
import spock.lang.Specification

class ArticlesSpec extends Specification implements SampleArticles {

    private ArticleClient articleClientMock = Mock(ArticleClient.class)

    private ArticleFacade facade = new ArticleConfiguration().articleFacade(articleClientMock)

    def "should find articles"() {
        given: "There are articles about 'Donald Trump'"
        articleClientMock.findArticles(_) >> Collections.singletonList(donaldTrumpResponse)

        when: "I search articles using query 'Donald Trump' "
        List<Article> result = facade.findArticles(ArticleSearchQuery.builder()
                .query("Donald Trump").build()
        )

        then: "I receive an article about 'Donald Trump'"
        result.contains(donaldTrumpResponse)
    }

}