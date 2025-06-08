package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    void init() {
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void testMember() {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // 동등성은 당연히 같음 (트랜잭션이 없어도 같다고 출력)
        assertThat(findMember).isEqualTo(member);

        // 동일성은 조금 더 엄격하게 검수를 하기에 객체 참조 값이 다르면 false 가 출력되겠지만 트랜잭션이 걸려있기에 영속성 컨텍스트 때문에 이 역시 true 로 출력
        assertThat(findMember).isSameAs(member); 
    }
}