package com.example.demo;

import com.example.demo.dao.AdminDaoI;
import com.example.demo.dao.CartDaoI;
import com.example.demo.dao.ProductDaoI;
import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.CartRepo;
import com.example.demo.jpaRepo.ProductRepo;
import com.example.demo.services.AdminServiceI;
import com.example.demo.services.CartServiceI;
import com.example.demo.services.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;




@SpringBootTest
 class SpringBootApplicationTests {

    @Autowired
     ProductServiceI productServiceI;


    @Autowired
    private AdminDaoI adminDaoI;

    @Autowired
    private CartDaoI cartDaoI;


    @Autowired
    private ProductDaoI productDaoI;


    @MockBean
    ProductRepo productRepo;

    @Autowired
     CartServiceI cartServiceI;

    @MockBean
     CartRepo cartRepo;

    @Autowired
     AdminServiceI adminServiceI;


    @Test
    void getProducts(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2, productServiceI.getProducts().size());

    }
    @Test
    void getProductById(){
        ProductEntity productEntity = new ProductEntity(1,"book", "http://book.png", 60.9);
        when(productRepo.getById(productEntity.getId())).thenReturn(productEntity);
        assertEquals(productEntity, productServiceI.getProductById(productEntity.getId()));

    }



    @Test
     void getCartItems( ){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);
        when(cartRepo.findByUsername(userEntity)).thenReturn(Stream.of(new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8)).collect(Collectors.toList()));
        assertEquals(1, cartServiceI.getCartItems(userEntity).size());
    }


    //get a cart item

    @Test
     void  getCartItem(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        when(cartRepo.findByUsernameAndId(userEntity, 1)).thenReturn(cartEntity);

        assertEquals(cartEntity, cartServiceI.getCartItem(userEntity, 1));
    };

    //save cart

    @Test
      void addToCart(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartServiceI.addToCart(cartEntity);
        verify(cartRepo, times(1)).save(cartEntity);
    }


    //delete cart item
    @Test
      void deleteById()
    {

        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartServiceI.deleteById(cartEntity.getId());
        verify(cartRepo, times(1)).deleteById(cartEntity.getId());


    }




    @Test
     void testSaveProduct( ){
        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);
        adminServiceI.saveProduct(productEntity);
        verify(productRepo,times(1)).save(productEntity);



    }

    //get

    @Test
     void testGetProducts(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2,adminServiceI.getProducts().size());
    }

    //update

    @Test

    void testUpdateProduct(){
        ProductEntity productEntity=new ProductEntity(3, "Book", "http:book.png", 20.5);

        Optional<ProductEntity> optionalProductEntity = Optional.of(productEntity);
        when(productRepo.findById(3)).thenReturn(optionalProductEntity);

        Optional<ProductEntity> productEntity1 =  adminServiceI.updateProduct(productEntity.getId());
        ProductEntity product = new ProductEntity();
        if(productEntity1.isPresent())
        {
            product = productEntity1.get();
        }

        assertEquals(product , productEntity);




    }


    //delete

    @Test
     void testDeleteProduct(){

        ProductEntity productEntity=new ProductEntity(1, "Book", "http://book.png", 34.5);
        adminServiceI.deleteProduct(productEntity.getId());
        verify(productRepo,times(1)).deleteById(productEntity.getId());

    }


    //dao testing

    @Test
     void getProductsDao(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2, productDaoI.getProducts().size());


    }

    @Test
     void getProductByIdDao(){
        ProductEntity productEntity = new ProductEntity(1,"book", "http://book.png", 60.9);
        when(productRepo.getById(productEntity.getId())).thenReturn(productEntity);
        assertEquals(productEntity, productDaoI.getProductById(productEntity.getId()));
    }



    @Test
      void  getCartItemsDao(){

        UserEntity userEntity = new UserEntity("kiran","kiran",1);
        when(cartRepo.findByUsername(userEntity)).thenReturn(Stream.of(new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8)).collect(Collectors.toList()));
        assertEquals(1, cartDaoI.getCartItems(userEntity).size());

    }


    //get a cart item

    @Test
     void getCartItemDao(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        when(cartRepo.findByUsernameAndId(userEntity, 1)).thenReturn(cartEntity);

        assertEquals(cartEntity, cartDaoI.getCartItem(userEntity, 1));

    }

    //save cart
    @Test
      void addToCartDap(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartDaoI.addToCart(cartEntity);
        verify(cartRepo, times(1)).save(cartEntity);

    }


    //delete cart item
    @Test
      void deleteByIdCartDao(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartDaoI.deleteById(cartEntity.getId());
        verify(cartRepo, times(1)).deleteById(cartEntity.getId());

    }


    //admin dao


    @Test
     void testSaveProductDao( ){
        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);
        adminDaoI.saveProduct(productEntity);
        verify(productRepo,times(1)).save(productEntity);



    }

    //get

    @Test
     void testGetProductsDao(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2,adminDaoI.getProducts().size());
    }

    //update

    @Test
     void testUpdateProductDao(){
        ProductEntity productEntity=new ProductEntity(3, "Book", "http:book.png", 20.5);

        Optional<ProductEntity> optionalProductEntity = Optional.of(productEntity);
        when(productRepo.findById(3)).thenReturn(optionalProductEntity);

        Optional<ProductEntity> productEntity1 =  adminDaoI.updateProduct(productEntity.getId());
        ProductEntity product = new ProductEntity();
        if(productEntity1.isPresent())
        {
            product = productEntity1.get();
        }

        assertEquals(product , productEntity);




    }


    //delete

    @Test
      void testDeleteProductDao(){

        ProductEntity productEntity=new ProductEntity(1, "Book", "http://book.png", 34.5);
        adminDaoI.deleteProduct(productEntity.getId());
        verify(productRepo,times(1)).deleteById(productEntity.getId());

    }




}
