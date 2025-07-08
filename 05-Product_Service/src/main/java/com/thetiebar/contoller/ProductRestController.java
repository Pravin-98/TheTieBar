package com.thetiebar.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thetiebar.dto.ProductDto;
import com.thetiebar.props.AppProps;
import com.thetiebar.response.ApiResponse;
import com.thetiebar.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private AppProps props;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestBody ProductDto productDto, @RequestParam("file") MultipartFile file){
		
		Map<String, String> messages = props.getMessages();
		
		ProductDto addedProduct = productService.addProduct(productDto);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(addedProduct != null ) {
			response.setMessage(messages.get("Product Added"));
			response.setStatusCode(201);
			response.setData(addedProduct);
		}else {
			response.setStatusCode(501);
			response.setMessage(messages.get("Product Add_Err"));
		}
		
		return 	ResponseEntity.status(HttpStatus.CREATED).body(response);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable("id") Integer productId,@RequestPart("product") ProductDto productDto, @RequestParam("file") MultipartFile file){
		
		Map<String, String> messages = props.getMessages();
		ProductDto updatedProduct = productService.updateProduct(productId, productDto);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(updatedProduct != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("ProductUpdated"));
			response.setData(updatedProduct);
		}else {
			response.setStatusCode(501);
			response.setMessage("Product Update Err");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts(){
//		Map<String, String> messages = props.getMessages();
		
		List<ProductDto> products = productService.getAllProducts();
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		
		if(products != null) {
			response.setMessage("Product Fetch Successfully");
			response.setStatusCode(200);
			response.setData(products);
		} else {
			response.setMessage("Product Not Available");
			response.setStatusCode(200);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable("id")Integer productId){
//		Map<String, String> messages = props.getMessages();
		
		ProductDto product = productService.getProductById(productId);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(product != null) {
			response.setStatusCode(200);
			response.setMessage("Product Got ");
			response.setData(product);
		}else {
			response.setMessage("Product not Found");
			response.setStatusCode(500);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> deleteProductById(@PathVariable("id") Integer productId){
		
//		Map<String, String> messages = props.getMessages();
		
		ProductDto updatedProduct = productService.deleteProductById(productId);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(updatedProduct != null) {
			response.setData(updatedProduct);
			response.setMessage("Product Updated Successfully");
			response.setStatusCode(201);
		}else {
			response.setStatusCode(500);
			response.setMessage("Product is Not Available");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PatchMapping("/{id}/stock")
	public ResponseEntity<String> updateStock(@PathVariable("id")Integer productId, @RequestParam("quantity") Integer quantity){
		
		boolean update = productService.updateStock(productId, quantity);
		
		if(update) {
			return ResponseEntity.ok("Stock Updated Succefully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update stock");
		}
		
	}
	
}
