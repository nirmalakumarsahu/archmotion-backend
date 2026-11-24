package com.archmotion.service.specification;

import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@UtilityClass
public class BaseSpecification {

    public <T> Specification<T> search(String search, List<String> fields) {
        String pattern = "%" + search.trim().toLowerCase() + "%";
        return (root, query, cb) -> {
            List<Predicate> predicates = fields.stream()
                    .map(field ->
                            cb.like(cb.lower(root.get(field)), pattern)
                    ).toList();

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

}
