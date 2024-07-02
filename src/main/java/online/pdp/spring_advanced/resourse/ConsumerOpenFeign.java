package online.pdp.spring_advanced.resourse;

import online.pdp.spring_advanced.product.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "productClient", url = "http://localhost:8080/v1/product")
public interface ConsumerOpenFeign {

    @PostMapping("/create")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO);

    @PutMapping("/update")
    ProductDTO updateProduct(@RequestBody ProductDTO productDTO);

    @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable Integer id);

    @GetMapping("/get/{id}")
    ProductDTO getProduct(@PathVariable Integer id);

    @GetMapping("/list")
    List<ProductDTO> listProducts(@RequestParam Long page, @RequestParam Long size);

}
