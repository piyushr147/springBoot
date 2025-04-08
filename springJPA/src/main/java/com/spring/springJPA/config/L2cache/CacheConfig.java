package com.spring.springJPA.config.L2cache;

import com.spring.springJPA.entity.Course;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager EhcacheManager() {

        CacheConfiguration<Integer, Course> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Integer.class,
                        Course.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .build();

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        javax.cache.configuration.Configuration<Integer, Course> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig);
        cacheManager.createCache("course", configuration);
        return cacheManager;
    }
//    @Bean
//    public CacheManager cacheManager(){
//        /* This method will work if and only if there is exactly one JCache implementation jar in the classpath.
//        If there are multiple providers in your classpath then use the fully qualified name, in our till now we are using
//        only ehcache */
//        CachingProvider cachingProvider = Caching.getCachingProvider();
//        CacheManager cacheManager = cachingProvider.getCacheManager();
//
//        MutableConfiguration<Integer, Course> mutableConfiguration =
//                new MutableConfiguration<Integer, Course>()
//                        .setTypes(Integer.class, Course.class)
//                        .setStoreByValue(false)
//                        .setStatisticsEnabled(true)
//                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
//
//        Cache<Integer, Course> cache = cacheManager.createCache("course", mutableConfiguration);
//        return cache.getCacheManager();
//    }
}
