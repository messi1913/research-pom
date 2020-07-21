package com.hirit.research;


import com.hirit.research.entity.Account;
import com.hirit.research.entity.FoodStore;
import com.hirit.research.entity.QFoodStore;
import com.hirit.research.repository.AccountRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HiritRunner implements ApplicationRunner {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory query;

    // final 안하면 주입못함. ㅠㅠ
    //이건 스프링 부트 성공적으로 띄어지면 바로 실해함.
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // check QueryDSL
        List<FoodStore> fetch = query.selectFrom(QFoodStore.foodStore).fetch();
        log.info("QueryDSL Execute : {}", fetch.size());

        String email = "messi1913@gmail.com";
        String admin = "admin@gmail.com";
        Optional<Account> byUser = accountRepository.findByEmail(email);
        Optional<Account> byAdmin = accountRepository.findByEmail(admin);
        if (byUser.isPresent() && byAdmin.isPresent()) {
            log.debug("Already exist : {}", byUser.get().getEmail());
            return;
        }

        if(byUser.isEmpty()) {
            Account sangmessi = Account.builder()
                .email(email)
                .password(passwordEncoder.encode("12345"))
                .userName("sangmessi")
                .age(36)
                .build();

            accountRepository.save(sangmessi);
        }

        if(byUser.isEmpty()) {
            Account adminUser = Account.builder()
                .email(admin)
                .password(passwordEncoder.encode("12345"))
                .userName("admin_user")
                .age(26)
                .build();

            accountRepository.save(adminUser);
        }
        // JpaRepository 상속바등면 자동으로 save 있음.
    }
}
