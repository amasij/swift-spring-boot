package ng.swift.Swift.filters;

import com.querydsl.core.types.dsl.StringExpression;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import ng.swift.Swift.dto.PageDto;
import ng.swift.Swift.models.QMeal;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

@Getter
@Setter
public class MealFilter implements QuerydslBinderCustomizer<QMeal> {

    Long userId;

    @Hidden
    @Delegate(types = PageDto.class)
    private PageDto pager = new PageDto();

    @Override
    public void customize(QuerydslBindings bindings, QMeal root) {
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);

    }
}
