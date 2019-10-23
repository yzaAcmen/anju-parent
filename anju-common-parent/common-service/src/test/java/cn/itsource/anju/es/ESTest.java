package cn.itsource.anju.es;

import cn.itsource.anju.CommonApplication;
import cn.itsource.anju.domain.ProductDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class ESTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void test()throws Exception{
        //删库
        template.deleteIndex(ProductDoc.class);
        //建库
        template.createIndex(ProductDoc.class);
        //映射
        template.putMapping(ProductDoc.class);

    }

}