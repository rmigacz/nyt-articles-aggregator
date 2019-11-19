package pl.rmigacz.aggregator.articles.infrastructure.configuration;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static pl.rmigacz.aggregator.infrastructure.configuration.Profiles.REDIS_CACHE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Profile({REDIS_CACHE})
@Configuration
class ArticleClientRedisCacheConfiguration {

  private final Environment env;

  @Autowired
  public ArticleClientRedisCacheConfiguration(Environment env) {
    this.env = env;
  }

  @Bean
  RedisCacheManager articlesCacheManager() {
    return RedisCacheManager.builder(redisConnectionFactory())
        .cacheDefaults(redisCacheConfiguration())
        .transactionAware()
        .build();
  }

  @Bean
  LettuceConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
    redisConfiguration.setHostName(env.getProperty("spring.redis.host"));
    redisConfiguration.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
    redisConfiguration.setPort(parseInt(requireNonNull(env.getProperty("spring.redis.port"))));

    return new LettuceConnectionFactory(redisConfiguration);
  }

  @Bean
  RedisCacheConfiguration redisCacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues();
  }
}
