package online.pdp.spring_advanced.resourse;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.product.ProductCriteria;
import online.pdp.spring_advanced.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerWebClient {

    private final WebClient webClient = WebClient.create("http://localhost:8080/v1/product");

    public ProductDTO create(ProductDTO productDTO) {
        return webClient.post()
                .uri("/create")
                .bodyValue(productDTO)
                .retrieve()
                .bodyToMono(ProductDTO.class).block();
    }

    public ProductDTO update(ProductDTO productDTO) {
        return webClient.put()
                .uri("/update")
                .bodyValue(productDTO)
                .retrieve()
                .bodyToMono(ProductDTO.class).block();
    }

    public void delete(Integer id) {
        webClient.delete()
                .uri("/delete/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public ProductDTO getProduct(Integer id) {
        return webClient.get()
                .uri("/get/" + id)
                .retrieve()
                .bodyToMono(ProductDTO.class).block();
    }

    public List<ProductDTO> listProducts(ProductCriteria criteria) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/list")
                        .queryParam("page", criteria.getPage())
                        .queryParam("size", criteria.getSize())
                        .build())
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList().block();
    }
}
