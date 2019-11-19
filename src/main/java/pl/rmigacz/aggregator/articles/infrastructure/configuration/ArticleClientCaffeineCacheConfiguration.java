package pl.rmigacz.aggregator.articles.infrastructure.configuration;

import static java.util.Objects.requireNonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.github.benmanes.caffeine.cache.CaffeineSpec;

import pl.rmigacz.aggregator.infrastructure.configuration.Profiles;

@Profile({Profiles.CAFFEINE_CACHE})
@Configuration
class ArticleClientCaffeineCacheConfiguration {

  private final Environment env;

  @Autowired
  public ArticleClientCaffeineCacheConfiguration(Environment env) {
    this.env = env;
  }

  @Bean
  CacheManager articlesCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("articlesCache");
    cacheManager.setAllowNullValues(false);
    cacheManager.setCaffeineSpec(
        CaffeineSpec.parse(requireNonNull(env.getProperty("spring.cache.caffeine.spec"))));
    return cacheManager;
  }
}
