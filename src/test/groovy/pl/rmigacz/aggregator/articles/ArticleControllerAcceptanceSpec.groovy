package pl.rmigacz.aggregator.articles

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import pl.rmigacz.aggregator.articles.domain.ArticleFacade
import pl.rmigacz.aggregator.base.IntegrationSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ArticleControllerAcceptanceSpec extends IntegrationSpec {

    @Autowired
    private ArticleFacade articleFacade

    def "positive finding articles scenario"() {
        when: "I perform GET to /article"
        final String jsonSearchParameter = "{\n" +
                " \"query\": \"Donald Trump\", \n" +
                " \"sort\": \"newest\"\n" +
                " }"
        ResultActions getArticles = mockMvc.perform(get("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSearchParameter))

        then: "Articles are returned"
        getArticles.andExpect(status().isOk())
    }


}