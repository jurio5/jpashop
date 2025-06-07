package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 보통 임베디드 어노테이션은 엔티티 쪽에만 있어도 적용되지만, 명시적으로 해주는게 의도를 전할 수 있기에 좋은 설계
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
