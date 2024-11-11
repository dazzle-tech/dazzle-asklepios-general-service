package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ListRequest {
    private List<ListRequestFilter> filters;
    private int pageNumber = 1;
    private int pageSize = 1000;
    private String sortBy;
    private String sortType;
    private String filterLogic = "and";

    public ListRequest() {
    }

    public ListRequest(Map<String, String> queryParams) {
        if (queryParams != null) {
            if (queryParams.containsKey("pageNumber")) {
                pageNumber = Integer.parseInt(queryParams.get("pageNumber"));
            }
            if (queryParams.containsKey("pageSize")) {
                pageSize = Integer.parseInt(queryParams.get("pageSize"));
            }
            if (queryParams.containsKey("sortBy")) {
                sortBy = queryParams.get("sortBy");
                if (sortBy.contains("_lvalue")) {
                    sortBy = sortBy.split("_lvalue")[0] + "_lkey";
                }
            }
            if (queryParams.containsKey("sortType")) {
                sortType = queryParams.get("sortType");
            }
            if (queryParams.containsKey("filterLogic")) {
                filterLogic = queryParams.get("filterLogic");
            }
            if (queryParams.containsKey("filters")) {
                if (filters == null)
                    filters = new ArrayList<>();
                String unparsedFiltersString = queryParams.get("filters");
                String[] filterSegments = unparsedFiltersString.split("_fspr_");
                for (String fSegment : filterSegments) {
                    String[] filterFields = fSegment.split(",");
                    ListRequestFilter filter = new ListRequestFilter();
                    filter.setFieldName(filterFields[0]);
                    filter.setOperator(filterFields[1]);
                    filter.setValue(filterFields[2]);
                    filters.add(filter);
                }
            }
        }
    }

    public String buildWhereStatement() {
        return buildWhereStatement(true, true, true);
    }

    public String buildWhereStatement(boolean doFilter, boolean doSorting, boolean doPagination, boolean useOrFilterLogic) {
        if (useOrFilterLogic)
            filterLogic = "or";
        return buildWhereStatement(doFilter, doSorting, doPagination);
    }

    public String buildWhereStatement(boolean doFilter, boolean doSorting, boolean doPagination) {

        System.out.println("FILTER LOGIC: " + filterLogic);
        StringBuilder where = new StringBuilder("1=1 ");
        // build filters
        if (doFilter && filters != null && !filters.isEmpty()) {
            where.append(" and ");
            int index = 0;
            for (ListRequestFilter filter : filters) {

                switch (filter.getOperator()) {
                    case "isNull":
                        where.append(filter.getFieldName()).append(" is null ");
                        break;
                    case "match":
                        where.append(filter.getFieldName()).append(" = '").append(filter.getValue()).append("'");
                        break;
                    case "startsWith":
                        where.append(filter.getFieldName()).append(" like '").append(filter.getValue()).append("%'");
                        break;
                    case "endsWith":
                        where.append(filter.getFieldName()).append(" like '%").append(filter.getValue()).append("'");
                        break;
                    case "contains":
                        where.append(filter.getFieldName()).append(" like '%").append(filter.getValue()).append("%'");
                        break;
                    case "startsWithIgnoreCase":
                        where.append("lower(").append("CAST("+filter.getFieldName()+" AS TEXT)").append(")").append(" like '").append(filter.getValue().toLowerCase()).append("%'");
                        break;
                    case "endsWithIgnoreCase":
                        where.append("lower(").append("CAST("+filter.getFieldName()+" AS TEXT)").append(")").append(" like '%").append(filter.getValue().toLowerCase()).append("'");
                        break;
                    case "containsIgnoreCase":
                        where.append("lower(").append("CAST("+filter.getFieldName()+" AS TEXT)").append(")").append(" like '%").append(filter.getValue().toLowerCase()).append("%'");
                        break;
                    case "gt":
                        where.append(filter.getFieldName()).append(" > '").append(filter.getValue()).append("'");
                        break;
                    case "gte":
                        where.append(filter.getFieldName()).append(" >= '").append(filter.getValue()).append("'");
                        break;
                    case "lt":
                        where.append(filter.getFieldName()).append(" < '").append(filter.getValue()).append("'");
                        break;
                    case "lte":
                        where.append(filter.getFieldName()).append(" <= '").append(filter.getValue()).append("'");
                        break;
                    case "between":
                        if (filter.getValue().split("_").length == 2) {
                            where.append(filter.getFieldName()).append(" between '").append(filter.getValue().split("_")[0]).append("'");
                            where.append(" and '").append(filter.getValue().split("_")[1]).append("'");
                        }
                        break;
                    default:
                        where.append(filter.getFieldName()).append(" = '").append(filter.getValue()).append("'");
                        break;
                }

                if (filterLogic != null && filterLogic.equals("or") && (index + 1) < filters.size()) {
                    where.append(" or ");
                } else if ((filterLogic == null || filterLogic.equals("and")) && (index + 1) < filters.size()) {
                    where.append(" and ");
                }
                index++;
            }
        }

        if (doSorting && sortBy != null && !sortBy.isBlank()) {
            where.append(" order by ").append(sortBy).append(" ").append(sortType);
        }

        if (doPagination) {
            where.append(" limit '").append(pageSize).append("' offset '").append(pageSize * (pageNumber - 1)).append("'");
        }

        System.out.println(where);
        return where.toString();
    }
}
