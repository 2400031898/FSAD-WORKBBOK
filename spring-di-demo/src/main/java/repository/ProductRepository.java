package com.klu.springdidemo.repository;

import com.klu.springdidemo.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 3a: Sort by price ASC
    @Query("from Product order by price asc")
    List<Product> sortByPriceAsc();

    // 3b: Sort by price DESC
    @Query("from Product order by price desc")
    List<Product> sortByPriceDesc();

    // 4: Sort by quantity DESC
    @Query("from Product order by quantity desc")
    List<Product> sortByQuantityDesc();

    // 5: Pagination
    @Query("from Product")
    List<Product> paginate(Pageable pageable);

    // 6a: Count total products
    @Query("select count(p) from Product p")
    long totalCount();

    // 6b: Count products where quantity > 0
    @Query("select count(p) from Product p where quantity > 0")
    long availableCount();

    // 6c: Count products grouped by description
    @Query("select p.description, count(p) from Product p group by p.description")
    List<Object[]> countByDescription();

    // 6d: Min & Max price
    @Query("select min(p.price), max(p.price) from Product p")
    Object[] minMaxPrice();

    // 7: GROUP BY
    @Query("select p.description, sum(p.quantity) from Product p group by p.description")
    List<Object[]> groupByDescription();

    // 8: WHERE price range
    @Query("from Product p where p.price between :min and :max")
    List<Product> priceRange(@Param("min") double min,
                             @Param("max") double max);

    // 9a,b,c: LIKE
    @Query("from Product p where p.name like :pattern")
    List<Product> nameLike(@Param("pattern") String pattern);

    // 9d: Exact length
    @Query("from Product p where length(p.name) = :len")
    List<Product> nameLength(@Param("len") int len);
}
