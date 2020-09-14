package kr.co.jhta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.dao.CategoryDao;
import kr.co.jhta.dao.ProductDao;
import kr.co.jhta.vo.Category;
import kr.co.jhta.vo.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Category> getAllCategories() {
		
		return categoryDao.getAllCategories();
	}
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	@Override
	public Product addNewProduct(Product product) {
		//insert작업이 완료되면 product의 no 프로퍼티에 시퀀스값이 저장된다.
		productDao.insertProduct(product);
		
		//insert작업 완료후 product의 no에 저장된 상품번호로 저장된 상품상세정보를 조회한다.
		Product savedProduct = productDao.getProductByNo(product.getNo());
		return savedProduct;
	}
	
	@Override
	public Product getProductDetail(long productNo) {
		return productDao.getProductByNo(productNo);
	}
}
