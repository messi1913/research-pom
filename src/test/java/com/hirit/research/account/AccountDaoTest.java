package com.hirit.research.account;



import com.hirit.research.account.vo.AccountDTO;
import com.hirit.research.entity.Account;
import com.hirit.research.entity.QAccount;
import com.hirit.research.repository.AccountRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountDaoTest {
    QAccount ACCOUNT = QAccount.account;

    @Autowired
    JPAQueryFactory query;
    @Autowired
    AccountRepository repository;


    @BeforeEach
    public void init() {
        Account test1 = Account.builder()
            .email("test@test.com")
            .userName("test1")
            .age(19)
            .build();

        repository.save(test1);

    }

    @Test
    public void DTO_리턴하는_테스트() {
        String email = "messi1913@gmail.com";
        AccountDTO accountDTO = query.select(Projections.bean(AccountDTO.class,
            ACCOUNT.email,
            ACCOUNT.userName))
            .from(ACCOUNT)
            .where(ACCOUNT.email.eq(email))
            .fetchOne();

        assertThat(accountDTO.getEmail()).isEqualTo(email);
    }

    @Test
    public void 생성자_DTO_리턴하는_테스트() {
        String email = "messi1913@gmail.com";
        AccountDTO accountDTO = query.select(Projections.constructor(AccountDTO.class,
            ACCOUNT.email,
            ACCOUNT.userName))
            .from(ACCOUNT)
            .where(ACCOUNT.email.eq(email))
            .fetchOne();

        assertThat(accountDTO.getEmail()).isNotEqualTo(email);
    }

    @Test
    public void QueryDSL_일반쿼리() {
        QAccount ACCOUNT = QAccount.account;
        String email = "messi1913@gmail.com";
        query.selectFrom(ACCOUNT).fetch().forEach(System.out::println);
    }

    @Test
    public void 성인만_가져오기() {
        List<Account> fetch =
            query.selectFrom(ACCOUNT)
            .where(ACCOUNT.age.gt(18))
            .fetch();

        assertThat(fetch.size()).isGreaterThan(0);
    }

}