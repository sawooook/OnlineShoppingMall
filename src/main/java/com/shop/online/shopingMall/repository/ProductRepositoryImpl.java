package com.shop.online.shopingMall.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductCategory;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.QProductPrice;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.dto.product.ProductSearchRequestDto;
import com.shop.online.shopingMall.dto.product.ProductSearchResponseDto;
import com.shop.online.shopingMall.dto.product.QProductSearchResponseDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shop.online.shopingMall.domain.QProduct.product;
import static com.shop.online.shopingMall.domain.QProductPrice.productPrice;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    /**
    * 검색조건을 받아서 맞는 데이터의 리스트를 보여준다.
    * 검색 조건 : 제품이름, 낮은 가격 , 높은가격
    * */
    @Override
    public List<ProductSearchResponseDto> searchProduct(ProductSearchRequestDto condition) {
        return jpaQueryFactory
                .select(new QProductSearchResponseDto
                        (product.id.as("id"),
                                product.name.as("name"),
                                productPrice.price.as("price"),
                                product.category.as("category")))
                .from(product)
                .join(product.productPrices, productPrice)
                .fetchJoin()
                .where(
                        isActive(),
                        nameEq(condition.getSearchName()),
                        typeEq(condition.getType())
                ).orderBy(priceCheck(condition)).fetch();
    }

    /**
    * 리스트를 보여줄때 판매중인 제품만 보여준다
    * */
    private BooleanExpression isActive() {
        return product.productStatus.eq(ProductStatus.ACTIVE);
    }

    private OrderSpecifier<?> priceCheck(ProductSearchRequestDto condition) {
        if (condition.isHighPrice()) {
            return productPrice.price.desc();
        } else if (condition.isLowPrice()) {
            return productPrice.price.asc();
        } else {
            return product.name.asc();
        }

    }

    private BooleanExpression typeEq(ProductCategory type) {
        return type != null ? product.category.eq(ProductCategory.COAT) : null;
    }

    private BooleanExpression nameEq(String searchName) {
        return StringUtils.hasText(searchName) ? product.name.like("%" + searchName + "%") : null;
    }
}
