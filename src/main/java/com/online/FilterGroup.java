package com.online;

import java.util.ArrayList;
import java.util.List;

import com.online.Filter.Operator;

public class FilterGroup {
	public enum JoinChar{
		and,
		or
	}
	public  FilterGroup(){
		
	};
	
	private List<Filter> filters = new ArrayList<>();
	
	private List<FilterGroup> filterGroups = new ArrayList<>();
	
	private JoinChar joinChar = JoinChar.and;

	public List<Filter> getFilters() {
		return filters;
	}

	public FilterGroup setFilters(List<Filter> filters) {
		this.filters = filters;
		return this;
	}

	public List<FilterGroup> getFilterGroups() {
		return filterGroups;
	}

	public FilterGroup setFilterGroups(List<FilterGroup> filterGroups) {
		this.filterGroups = filterGroups;
		return this;
	}
	
	
	public FilterGroup addFilter(Filter filter){
		filters.add(filter);
		return this;
	}
	
	public FilterGroup addFilter(String name,String value){
		filters.add(new Filter(name, Operator.eq, value));
		return this;
	}
	
	public void addFilterGroup(FilterGroup filterGroup){
		filterGroups.add(filterGroup);
	}

	public JoinChar getJoinChar() {
		return joinChar;
	}

	public void setJoinChar(JoinChar joinChar) {
		this.joinChar = joinChar;
	}
	
	public FilterGroup(Filter filter){
		addFilter(filter);
	}
	
	public FilterGroup(List<Filter> filters){
		this.filters = filters;
	}
	
}
