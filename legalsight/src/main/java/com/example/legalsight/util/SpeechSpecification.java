package com.example.legalsight.util;

import com.example.legalsight.dto.SearchCriteriaDto;
import com.example.legalsight.enums.SearchOperation;
import com.example.legalsight.model.Speech;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpeechSpecification implements Specification<Speech> {

    private SearchCriteriaDto criteria;

    public SpeechSpecification(final SearchCriteriaDto criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteriaDto getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Speech> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equals(SearchOperation.EQUALITY)) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
