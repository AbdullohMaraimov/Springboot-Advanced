package online.pdp.spring_advanced.resourse;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.product.ProductCriteria;
import online.pdp.spring_advanced.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerOpenFeignImpl {

    private ConsumerOpenFeign consumerOpenFeign;

    public ProductDTO createProduct(ProductDTO productDTO) {
        return consumerOpenFeign.createProduct(productDTO);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        return consumerOpenFeign.updateProduct(productDTO);
    }

    public void deleteProduct(Integer id) {
        consumerOpenFeign.deleteProduct(id);
    }

    public ProductDTO getProduct(Integer id) {
        return consumerOpenFeign.getProduct(id);
    }

    public List<ProductDTO> listProducts(ProductCriteria criteria) {
        return consumerOpenFeign.listProducts(criteria.getPage(), criteria.getSize());
    }

}
