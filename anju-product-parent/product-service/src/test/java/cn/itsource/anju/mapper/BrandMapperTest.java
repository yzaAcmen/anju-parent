package cn.itsource.anju.mapper;


import cn.itsource.anju.ProductApplicaiton;
import cn.itsource.anju.domain.Brand;
import cn.itsource.anju.query.BrandQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplicaiton.class)
public class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void queryPage() {
        BrandQuery query = new BrandQuery();
        query.setPage(1);
        query.setRows(10);
        query.setKeyword("七");
        /*List<Brand> brands = brandMapper.queryPage(query);
        for (Brand brand : brands) {
            System.out.println(brand);
        }*/

        IPage<Brand> ipage = brandMapper.queryPage(new Page(query.getPage(), query.getRows()), query);

        System.out.println(ipage.getTotal());
        for (Brand record : ipage.getRecords()) {
            System.out.println(record);
        }


    }
}