package kr.co.jhta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jhta.form.ProductForm;
import kr.co.jhta.service.ProductService;
import kr.co.jhta.vo.Category;
import kr.co.jhta.vo.Product;


/*
 * @RestController
 *       - REST 요청과 응답을 처리하는 컨트롤러 클래스를 나타낸다
 *       - @ResponseBody 어노테이션을 생략해도 요청핸들러 메소드가 반환하는 객체가 JSON/XML 응답으로 제공된다
 *       - 클라이언트와 서버가 JSON 형식의 데이터를 주고 받기 위해서는 jack-databind.jar 라이브러리가 필요하다
 *    REST 형식의 URL
 *       - URL Mapping
 *          "/자원이름/파라미터값"
 *          예)  /rest/products/         -- 모든 상품
 *             @GetMapping("/rest/products/")
 *             public List<Product> getAllProducts() { ... }
 * 
 *             /rest/products/10      -- 10번 상품
 *             @GetMapping("/rest/products/{no}")
 *             public Product getProductDetail(@PathVariable("no") long productNo) { ... }
 * 
 *             /rest/products/3/10/가구   -- 가구 중에서 10개 표시했을 때 3번째 페이지
 *             @GetMapping("/rest/products/{pageNo}/{rows}/{category}")
 *             public List<Product> getProducts(@PathVariable("pageNo") int pageNo, @PathVariable("rows") int rows, @PathVariable("category") String category) { ... }
 * 
 * @PathVariable
 *       - REST URL의 경로에서 지정된 변수에 해당하는 값을 조회한다
 *       
 * @RequestBody 와 @ResponseBody
 * 		- @RequestBody는 요청메세지의 body부의 데이터를 자바객체에 담는다.
 * 			public Product addProduct(@RequestBody ProductForm productForm){...}
 * 			*클라이언트에서 서버로 요청메세지를 보낼 때 반드시 요청메세지의 body부에 포함되는 데이터의 컨텐츠 타입을 지정해야 한다.
 * 			*프로젝트에 지정된 컨텐츠 타입의 값을 자바객체로 변환하는 라이브러리가 포함되어 있어야 한다
 * 		- @ResponseBody는 요청핸들러 메소드가 반환하는 객체를 응답메세지의 body부에 담는다.
 * 			public @ResponseBody List<Product> getAllProducts() {...}
 * 			*클라이언트는 서버로부터 받은 응답데이터의 body부에 포함되는 데이터의 컨텐츠타입을 지정해야 한다.
 * 			*프로젝트에 자바객체를 지정된 컨텐츠 타입의 값으로 변환하는 라이브러리가 포함되어 있어야 한다
 */
@RestController
@RequestMapping("/rest")
public class RestProductController {

	@Autowired
	ProductService productService;
	
	   // 모든 상품 정보 조회
	   // 요청방식 : GET
	   // 요청URL : /rest/products/
	   // 요청 데이터 : 없음
	   // 응답 데이터 : [상품정보, 상품정보, 상품정보]
	   @GetMapping("/products/")
	   public List<Product> products() {
	      List<Product> products = productService.getAllProducts();
	      return products;
	   }
	   
	   // 지정된 번호의 상품정보 조회
	   // 요청방식 : GET
	   // 요청URL : /rest/product/상품전호
	   // 요청 데이터 : 없음
	   // 응답 데이터 : 상품정보
	   @GetMapping("/products/{no}")
	   public Product product(@PathVariable("no") int productNo) {
	      Product product = productService.getProductDetail(productNo);
	      return product;
	   }
	   
	   // 새로운 상품 정보 추가
	   // 요청방식 : POST
	   // 요청URL : /rest/products/
	   // 요청 데이터 : 상품정보 (추가할 상품정보)
	   // 응답 데이터 : 상품정보 (추가된 상품정보)
	   @PostMapping("/products/")
	   public Product postProduct(@RequestBody ProductForm productForm) {
		   Product product = new Product();
		   product.setCategory(new Category(productForm.getCatId()));
		   product.setName(product.getName());
		   product.setPrice(productForm.getPrice());
		   product.setDiscountPrice(productForm.getDiscountPrice());
		  
		   //새상품정보(카테고리, 상품명, 가격, 할인가격) 저장 후
		   //저장된 상품정보(번호, 카테고리, 상품명, 가격, 할인가격, 기본재고, 등록일)가 획득된다.
		   Product savedProduct = productService.addNewProduct(product);
		   return savedProduct;
	   }
	   // 지정된 상품번호의 상품정보 삭제
	   // 요청방식 : DELETE
	   // 요청 URL : /rest/products/상품번호
	   // 요청 데아터 : 없음
	   // 응답 데이터 : 없음 or 상품정보(삭제처리된 상품정보)

	   // 지정된 상품번호의 상품정보 변경
	   // 요청방식 : PUT
	   // 요청 URL : /rest/products/상품번호
	   // 요청 데이터 : 상품정보 (변경할 상품정보)
	   // 응답 데이터 : 상품정보 (변경된 상품정보)
	   
}
