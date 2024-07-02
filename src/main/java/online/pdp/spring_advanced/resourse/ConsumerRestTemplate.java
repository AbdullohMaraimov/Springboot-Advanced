package online.pdp.spring_advanced.resourse;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.product.ProductCriteria;
import online.pdp.spring_advanced.product.ProductDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerRestTemplate {

    private final String url = "http://localhost:8080/v1/product";

    private final RestTemplate restTemplate;

    public ProductDTO create(ProductDTO productDTO) {
        return restTemplate.postForObject(url + "/create", productDTO, ProductDTO.class);
    }

    public ProductDTO update(ProductDTO productDTO) {
        HttpEntity<ProductDTO> httpEntity = new HttpEntity<>(productDTO);
        return restTemplate.exchange(url + "/update", HttpMethod.PUT, httpEntity, ProductDTO.class).getBody();
    }

    public void delete(Integer id) {
        restTemplate.delete(url + "/delete/" + id);
    }

    public ProductDTO getProduct(Integer id) {
        return restTemplate.getForObject(url + "/get/" + id, ProductDTO.class);
    }

    public List<ProductDTO> listProducts(ProductCriteria criteria) {
        String myUrl = url + "/list?page=" + criteria.getPage() + "&size=" + criteria.getSize();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, ProductDTO[].class)));
    }
}
