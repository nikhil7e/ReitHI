package is.hi.hbv501g.reithi.Persistence.Entities;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CourseSpecification implements Specification<Course> {

    private SearchCriteria criteria;

    public CourseSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.function("LOWER", String.class, root.<String>get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if (criteria.getOperation().equalsIgnoreCase("!:")) {
            return builder.notLike(
                    builder.function("LOWER", String.class, root.<String>get(criteria.getKey())),
                    criteria.getValue().toString().toLowerCase());

        }
        else if (criteria.getOperation().equalsIgnoreCase("sum<")) {
            int max = (int) criteria.getValue();
            System.out.println("Less than " + max);
            Expression<Integer> nrReviews = root.get("nrReviews");
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()),
                    builder.prod(builder.literal(max), nrReviews));
        }
        else if (criteria.getOperation().equalsIgnoreCase("sum>")) {
            int min = (int) criteria.getValue();
            System.out.println("Greater than " + min);
            Expression<Integer> nrReviews = root.get("nrReviews");
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()),
                    builder.prod(builder.literal(min), nrReviews));
        }
        return null;
    }
}