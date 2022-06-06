package com.Amigoscode.specification;


import com.Amigoscode.specification.key.JoinEnum;
import com.Amigoscode.specification.key.PropertyEnum;

public class SearchCriteria {

	private JoinEnum join;
    private PropertyEnum property;
    private Comparable<? extends Object> value;
    private SearchOperation searchOperation;

    public SearchCriteria() {
    }

    public SearchCriteria(PropertyEnum property, Comparable<? extends Object> value, SearchOperation searchOperation) {
        this.property = property;
        this.value = value;
        this.searchOperation = searchOperation;
    }
    
    public SearchCriteria(JoinEnum join, PropertyEnum property, Comparable<? extends Object> value, SearchOperation searchOperation) {
        this.join = join;
    	this.property = property;
        this.value = value;
        this.searchOperation = searchOperation;
    }
    
    /* GETTERS AND SETTERS */

    public Comparable<? extends Object> getValue() {
        return value;
    }

    public void setValue(Comparable<? extends Object> value) {
    	this.value = value;
    }

    public SearchOperation getSearchOperation() {
        return searchOperation;
    }

    public void setSearchOperation(SearchOperation searchOperation) {
        this.searchOperation = searchOperation;
    }

    public PropertyEnum getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "property='" + property + '\'' +
                ", value=" + value +
                ", searchOperation=" + searchOperation +
                '}';
    }

	public JoinEnum getJoin() {
		return join;
	}

}
