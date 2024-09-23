package com.backend.ecommercebackend.repository.product;

import com.backend.ecommercebackend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("""
//       select s from Product p inner join ProductSpecification s on u.id = t.user.id where u.id = :id
//"""
//    )
//    List<Token> findAllValidTokenByUserId(Long id);
}
