package com.archmotion.service.specification;

import com.archmotion.entity.Diagram;
import com.archmotion.security.dto.CustomUserPrincipal;
import com.archmotion.security.util.SecurityUtil;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class DiagramSpecification {

    public static Specification<Diagram> belongsToUser() {
        CustomUserPrincipal principal = SecurityUtil.getCurrentUser();
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("user").get("uuid"),
                        principal.userUuid()
                );
    }

    public Specification<Diagram> allOf(String search) {
        List<Specification<Diagram>> diagramSpecifications = new ArrayList<>();
        if (Objects.nonNull(search) && !search.isEmpty()) {
            diagramSpecifications.add(
                    BaseSpecification.search(search, List.of("name"))
            );
        }

        return Specification.allOf(diagramSpecifications);
    }

}
