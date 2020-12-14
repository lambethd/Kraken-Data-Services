package lambethd.kraken.application;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoSimpleTypes;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "lambethd.kraken.data.mongo.repository")
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.uri}")
    public String uri;

    @Override
    protected String getDatabaseName() {
        return "kraken";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString conn = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(conn)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.baeldung";
    }

    @Bean
    @Primary
    public MappingMongoConverter mongoConverter(
            @Autowired MongoMappingContext mongoMappingContext,
            @Autowired MongoDbFactory mainMongoFactory,
            @Autowired MongoCustomConversions conversions
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mainMongoFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        mongoConverter.setMapKeyDotReplacement("#");
        mongoConverter.afterPropertiesSet();
        mongoConverter.setCustomConversions(conversions);
        return mongoConverter;
    }


    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext context = new MongoMappingContext();
        context.setSimpleTypeHolder(new SimpleTypeHolder(new HashSet<>(Arrays.asList(
                LocalDateTime.class
        )), MongoSimpleTypes.HOLDER));
        return context;
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new MongoStringFromStringConverter());
        converterList.add(new MongoLocalDateTimeFromStringConverter());
        return new MongoCustomConversions(converterList);
    }

    private static final class MongoLocalDateTimeFromStringConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            return source == null ? null : LocalDateTime.parse(source);
        }
    }
    private static final class MongoStringFromStringConverter implements Converter<String, String> {
        @Override
        public String convert(String source) {
            return source;
        }
    }
}