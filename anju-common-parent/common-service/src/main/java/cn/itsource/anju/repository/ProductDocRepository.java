package cn.itsource.anju.repository;

import cn.itsource.anju.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,Long> {
}