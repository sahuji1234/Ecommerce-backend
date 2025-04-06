package com.suraj.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suraj.exception.ProductException;
import com.suraj.model.Category;
import com.suraj.model.Product;
import com.suraj.model.Size;
import com.suraj.repository.CategoryRepository;
import com.suraj.repository.ProductRepository;
import com.suraj.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;

	public ProductServiceImplementation(ProductRepository productRepository, UserService userService,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product createProduct(CreateProductRequest req) {
		Category topLevel = categoryRepository.findByName(req.getTopLavelCategory());

		if (topLevel == null) {
			System.out.println("1");
			Category topLavelCateory = new Category();
			topLavelCateory.setName(req.getTopLavelCategory());
			topLavelCateory.setLevel(1);
			topLevel = categoryRepository.save(topLavelCateory);

		}

		Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLavelCategory(), topLevel.getName());
		if (secondLevel == null) {
			System.out.println("2");
			Category secondLavelCategory = new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			secondLevel = categoryRepository.save(secondLavelCategory);
		}

		Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLavelCategory(),
				secondLevel.getName());
		if (thirdLevel == null) {
			System.out.println("3");

			Category thirdLevelcategory = new Category();
			thirdLevelcategory.setName(req.getThirdLavelCategory());
			thirdLevelcategory.setParentCategory(secondLevel);
			thirdLevelcategory.setLevel(3);
			thirdLevel = categoryRepository.save(thirdLevelcategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		if (product != null) {
			product.getSizes().clear();
			productRepository.delete(product);
			return "product deleted successfully";
		} else {
			return "please enter a valid product id";
		}

	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product = new Product();
		if (product != null) {
			if (req.getQuantity() != 0) {
				product.setQuantity(req.getQuantity());
			}
			if (req.getDescription() != null) {
				product.setDescription(req.getDescription());
			}
		}

		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		Optional<Product> opt = productRepository.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ProductException("product not fount with Id" + productId);

		}

	}

	@Override
	public List<Product> findProductByCategory(String category) {
		List<Product> products = productRepository.findByCategory(category);
		return products;
	}

	@Override
	public Page<Product> getAllProduct(String Category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productRepository.filterProducts(Category, minPrice, maxPrice, minDiscount, sort);
		System.out.print("prdousctnjdfnsd" + products);
		if (!colors.isEmpty()) {
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		if (stock != null) {
			if (stock.equals("instock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			}
		} else if (stock.equals("out_of_stock")) {
			products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
		}

		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		if (startIndex >= products.size()) {
			return new PageImpl<>(Collections.emptyList(), pageable, products.size());
		}
		List<Product> pageContent = products.subList(startIndex, endIndex);

		Page<Product> filteredProduct = new PageImpl<>(pageContent, pageable, products.size());

		return filteredProduct;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

}