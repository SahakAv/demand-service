package com.disqo.requestservice;

import com.disqo.requestservice.model.domain.ServiceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class RequestServiceApplicationTests {

    @Container
    public static PostgreSQLContainer<DatabaseContainer> postgreSQLContainer = DatabaseContainer.getInstance();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;


    @DynamicPropertySource
    static void dynamicSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    @Sql(scripts = "/db/request.sql")
    public void testGetRequests() {
        final String rootUrl = getRootUrl(port);
        HttpHeaders headers = new HttpHeaders();
        headers.set("username", "user");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(rootUrl + "/request");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        final HttpEntity<ServiceRequest[]> actual = testRestTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ServiceRequest[].class);

        final ServiceRequest[] body = actual.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals(body.length, 2);
    }


    private String getRootUrl(Integer port) {
        return "http://localhost:" + port;
    }

}
