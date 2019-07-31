package com.report.model.wp;

import java.util.List;

public class Category {
    private int termId;
    private String name;
    private String taxonomy;
    private String slug;
    private int termGroup;
    private int termOrder;
    private int parent;
    private int count;
    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(int termGroup) {
        this.termGroup = termGroup;
    }

    public int getTermOrder() {
        return termOrder;
    }

    public void setTermOrder(int termOrder) {
        this.termOrder = termOrder;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
