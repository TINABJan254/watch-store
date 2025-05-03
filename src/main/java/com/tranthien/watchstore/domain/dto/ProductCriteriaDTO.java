package com.tranthien.watchstore.domain.dto;

import java.util.List;
import java.util.Optional;

public class ProductCriteriaDTO {
    private Optional<String> page;
    private Optional<String> limit;
    private Optional<List<String>> factory;
    private Optional<List<String>> type;
    private Optional<List<String>> price;
    private Optional<String> sort;
    private Optional<String> search;

    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<String> getLimit() {
        return limit;
    }

    public void setLimit(Optional<String> limit) {
        this.limit = limit;
    }

    public Optional<List<String>> getFactory() {
        return factory;
    }

    public void setFactory(Optional<List<String>> factory) {
        this.factory = factory;
    }

    public Optional<List<String>> getType() {
        return type;
    }

    public void setType(Optional<List<String>> type) {
        this.type = type;
    }

    public Optional<List<String>> getPrice() {
        return price;
    }

    public void setPrice(Optional<List<String>> price) {
        this.price = price;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<String> getSearch() {
        return search;
    }

    public void setSearch(Optional<String> search) {
        this.search = search;
    }
    
}
