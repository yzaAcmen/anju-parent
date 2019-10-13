package cn.itsource.anju.query;

/**
 * @author solargen
 * @version 1.0
 * @description TODO
 * @date 2019/10/12 11:13
 */
public class BaseQuery {

    private Integer page;//pageNum

    private Integer rows;//pageSize

    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
