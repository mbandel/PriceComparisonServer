package com.pc.product;

import com.pc.category.Category;
import com.pc.category.CategoryRepository;
import com.pc.comment.Comment;
import com.pc.store.Store;
import com.pc.store.StoreRepository;
import com.pc.user.User;
import com.pc.user.UserRepository;
import com.pc.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserRepository userRepository;

    private static Logger logger = Logger.getLogger(ProductController.class.getName());

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product  with id: " + productId + " not found");
        }
        logger.info("Product found: " + product.get().getName());
        return product.get();
    }

    public boolean validate(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategory().mapToDomain().getId());
        Optional<Store> store = storeRepository.findById(productDto.getStore().mapToDomain().getId());
        Optional<User> user = userRepository.findById(productDto.getUser().mapToDomain().getId());
        List<Product> optionalProducts = productRepository.findAllByName(productDto.getName());
        //jezeli produkt ma ta sama nazwe oraz ten sam sklep
        for (Product product: optionalProducts) {
            if (productRepository.existsByName(product.getName()) && product.getStore().getId()==productDto.getStore().getId()){
                logger.info("Product : " + productDto.getName() + " already exists");
                return false;
            }
        }
        Product product = new Product(productDto.getName(), category.get(), productDto.getPrice(), store.get(), user.get(), productDto.getDate());
        productRepository.save(product);
        logger.info("Product with ID: " + product.getId() + " added");
        return true;
    }

    public List<Product> getProducts(){
        return productRepository.findByOrderByName();
    }

    public List<Comment> getComments(Long id){
        return getProductById(id).getComments();
    }

    public Product findByName(String name){
            Optional<Product> optionalProduct = productRepository.findByName(name);
            if(!optionalProduct.isPresent()){
                throw new ProductNotFoundException("Product not found");
            }
            return optionalProduct.get();
    }
}
