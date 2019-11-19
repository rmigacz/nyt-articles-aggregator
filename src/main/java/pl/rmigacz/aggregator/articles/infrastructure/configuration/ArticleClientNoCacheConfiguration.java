package pl.rmigacz.aggregator.articles.infrastructure.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pl.rmigacz.aggregator.infrastructure.configuration.Profiles;

@Profile(Profiles.WITHOUT_CACHE)
@Configuration
class ArticleClientNoCacheConfiguration {

  @Bean
  CacheManager articlesCacheManager() {
    return new NoOpCacheManager();
  }
}
