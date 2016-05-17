package com.metflix.uaa;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository memberRepository;

    @Test
    public void testFindByEmail() {
        User user = User.builder()
                .email("foo@example.com")
                .username("foo").build();
        entityManager.persist(user);
        Optional<User> m = memberRepository.findByEmail("foo@example.com");
        assertThat(m.isPresent()).isTrue();
        assertThat(m.get()).isEqualTo(user);
    }


    @Test
    public void testFindByIds() {
        User user1 = User.builder()
                .email("foo@example.com")
                .username("foo").build();
        User user2 = User.builder()
                .email("bar@example.com")
                .username("bar").build();
        entityManager.persist(user1);
        entityManager.persist(user2);
        List<User> m = memberRepository.findByIds(Arrays.asList(user1.getUserId(), user2.getUserId()));
        Assertions.assertThat(m).hasSize(2);
        assertThat(m.get(0)).isEqualTo(user2);
        assertThat(m.get(1)).isEqualTo(user1);
    }

    @Configuration
    static class Conf {
        @Bean
        ObjectPostProcessor postProcessor() {
            return Mockito.mock(ObjectPostProcessor.class);
        }
    }
}

