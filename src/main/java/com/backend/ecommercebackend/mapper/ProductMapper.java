package com.backend.ecommercebackend.mapper;

import com.backend.ecommercebackend.dto.ProductDto;
import com.backend.ecommercebackend.model.Product;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  List<ProductDto> mapToProductDtoList(List<Product> products);
  ProductDto mapToProductDto(Product product);
}
