package com.Edu.specification;


import com.Edu.specification.key.JoinEnum;
import com.Edu.specification.key.PropertyEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntitySpecification<T> implements Specification<T> {

    //private static final long serialVersionUID = 795559827320722608L;
    protected List<SearchCriteria> listCriteria = new ArrayList<SearchCriteria>();
    protected List<JoinEnum> listDefaultJoin = new ArrayList<JoinEnum>();
    protected Map<JoinEnum, List<JoinEnum>> listMapJoin = new HashMap<JoinEnum, List<JoinEnum>>();

    public void addCriteria(SearchCriteria criteria) {
        listCriteria.add(criteria);
    }

    public void addCriteria(PropertyEnum property, Comparable<? extends Object> value,
                            SearchOperation searchOperation) {
        addCriteria(null, property, value, searchOperation);
    }

    public void addCriteria(JoinEnum join, PropertyEnum property, Comparable<? extends Object> value,
                            SearchOperation searchOperation) {
        if (value != null) {
            addCriteria(new SearchCriteria(join, property, value, searchOperation));
        }
    }

    public void addJoin(JoinEnum join) {
        listDefaultJoin.add(join);
    }

    public void addJoin(JoinEnum parent, JoinEnum join) {
        if (listMapJoin.containsKey(parent) == false) {
            listMapJoin.put(parent, new ArrayList<JoinEnum>());
        }
        listMapJoin.get(parent).add(join);
    }

    public Specification<T> generateSpecification() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        toPredicate(root, query, criteriaBuilder)
                )
        );
    }

    /**
     * Returns specification object for getting the object with the maximal id
     *
     * @return
     */
    public Specification<T> getSpecificationMaxId() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), getSubQueryForMaxId(criteriaBuilder, query, root))
        );
    }

    //@SuppressWarnings("unchecked")
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        //create a new predicate list
        List<Predicate> listPredicate = new ArrayList<>();
        Map<JoinEnum, Join<T, ?>> mapJoin = new HashMap<JoinEnum, Join<T, ?>>();

        if (listDefaultJoin != null) {
            for (JoinEnum joinEnum : listDefaultJoin) {
                Join<T, ?> join = root.join(joinEnum.toString(), JoinType.LEFT);
                mapJoin.put(joinEnum, join);
            }
        }

        List<JoinEnum> listUnhandled = new ArrayList<JoinEnum>();
        if (listMapJoin != null) {
            for (JoinEnum keyEnum : listMapJoin.keySet()) {
                if (mapJoin.containsKey(keyEnum)) {
                    List<JoinEnum> listJoinEnum = listMapJoin.get(keyEnum);
                    Join<T, ?> innerJoin = mapJoin.get(keyEnum);
                    for (JoinEnum joinEnum : listJoinEnum) {
                        Join<T, ?> join = innerJoin.join(joinEnum.toString(), JoinType.LEFT);
                        mapJoin.put(joinEnum, join);
                    }
                } else {
                    listUnhandled.add(keyEnum);
                }
            }
        }

        for (JoinEnum unhandled : listUnhandled) {
            List<JoinEnum> listJoinEnum = listMapJoin.get(unhandled);
            Join<T, ?> innerJoin = mapJoin.get(unhandled);
            for (JoinEnum joinEnum : listJoinEnum) {
                Join<T, ?> join = innerJoin.join(joinEnum.toString(), JoinType.LEFT);
                mapJoin.put(joinEnum, join);
            }
        }

        //add criteria to predicates
        for (SearchCriteria criteria : listCriteria) {
            Comparable<Object> value = (Comparable<Object>) criteria.getValue();
            JoinEnum joinEnum = criteria.getJoin();
            Join<T, ?> join = null;

            if (joinEnum != null) {
                join = mapJoin.get(joinEnum);
                if (join == null) {
                    join = root.join(joinEnum.toString());
                    mapJoin.put(joinEnum, join);
                }
            }

            @SuppressWarnings("rawtypes")

            String criteriaProperty = criteria.getProperty().toString();
            Expression firstPart = join != null ? join.get(criteriaProperty) : root.get(criteriaProperty);

            if (criteria.getSearchOperation().equals(SearchOperation.GREATER_THAN)) {
                listPredicate.add(criteriaBuilder.greaterThan(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.LESS_THAN)) {
                listPredicate.add(criteriaBuilder.lessThan(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                listPredicate.add(criteriaBuilder.greaterThanOrEqualTo(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                listPredicate.add(criteriaBuilder.lessThanOrEqualTo(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.NOT_EQUAL)) {
                listPredicate.add(criteriaBuilder.notEqual(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.EQUAL)) {
                listPredicate.add(criteriaBuilder.equal(firstPart, value));
            } else if (criteria.getSearchOperation().equals(SearchOperation.MATCH)) {
                listPredicate.add(criteriaBuilder.like(
                        criteriaBuilder.lower(firstPart),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getSearchOperation().equals(SearchOperation.MATCH_END)) {
                listPredicate.add(criteriaBuilder.like(
                        criteriaBuilder.lower(firstPart),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getSearchOperation().equals(SearchOperation.MATCH_START)) {
                listPredicate.add(criteriaBuilder.like(
                        criteriaBuilder.lower(firstPart),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getSearchOperation().equals(SearchOperation.IN)) {
                listPredicate.add(criteriaBuilder.in(firstPart).value(criteria.getValue()));
            } else if (criteria.getSearchOperation().equals(SearchOperation.NOT_IN)) {
                listPredicate.add(criteriaBuilder.not(firstPart).in(criteria.getValue()));
            } else if (criteria.getSearchOperation().equals(SearchOperation.NULL)) {
                listPredicate.add(criteriaBuilder.isNull(firstPart).in(criteria.getValue()));
            } else if (criteria.getSearchOperation().equals(SearchOperation.NOT_NULL)) {
                listPredicate.add(criteriaBuilder.isNotNull(firstPart).in(criteria.getValue()));
            }
        }

        return criteriaBuilder.and(listPredicate.toArray(new Predicate[0]));
    }

    private Subquery<Integer> getSubQueryForMaxId(CriteriaBuilder criteriaBuilder,
                                                  CriteriaQuery<?> criteriaQuery, Root<T> root) {
        Subquery<Integer> subQuery = criteriaQuery.subquery(Integer.class);
        subQuery.from(root.getJavaType());
        Root<T> subRoot = (Root<T>) subQuery.from(root.getJavaType());
        subQuery.select(criteriaBuilder.max(subRoot.get("id")));
        return subQuery;
    }
}
