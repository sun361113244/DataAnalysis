package util;

import java.io.Serializable;

public class Paging implements Serializable
{

    private static final long serialVersionUID = 1L;

    private int total = 0;

    private int page = 1;

    private int pageSize = 20;

    private int pages = 0;

    public Paging() {
        this(1, 20);
    }

    public Paging(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    private void calculatePages() {
        total = total > 0 ? total : 0;
        pageSize = pageSize > 0 ? pageSize : 20;
        pages = total / pageSize + (total % pageSize == 0 ? 0 : 1);
        page = pages >= 1 ? Math.min(Math.max(page, 1), pages) : 1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        calculatePages();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getStart() {
        return Math.max((page - 1) * pageSize, 0);
    }

    @Override
    public String toString() {
        return "[page=" + page + ", pageSize=" + pageSize + "]";
    }

}

