package com.example.bi.util;

import androidx.sqlite.db.SimpleSQLiteQuery;

public class QueryUtil {
    public static SimpleSQLiteQuery sortedQuery(SortType sortType) {
        StringBuilder query = new StringBuilder().append("SELECT * FROM course ");
        switch (sortType) {
            case TIME:
                query.append("ORDER BY day, strftime('%H:%M', startTime)");
                break;
            case COURSE_NAME:
                query.append("ORDER BY courseName COLLATE NOCASE");
                break;
            case LECTURER:
                query.append("ORDER BY lecturer COLLATE NOCASE");
                break;
        }
        return new SimpleSQLiteQuery(query.toString());
    }

    public static SimpleSQLiteQuery nearestQuery(QueryType type) {
        String query = "";
        switch (type) {
            case CURRENT_DAY:
                query = "SELECT * FROM course " +
                        "WHERE day = (strftime('%w', 'now', 'localtime') + 1) " +
                        "AND strftime('%H:%M', startTime) > strftime('%H:%M', 'now', 'localtime') " +
                        "ORDER BY strftime('%H:%M', startTime) ASC LIMIT 1";
                break;
            case NEXT_DAY:
                query = "SELECT * FROM course " +
                        "WHERE day > (strftime('%w', 'now', 'localtime') + 1) " +
                        "ORDER BY day,strftime('%H:%M', startTime) ASC LIMIT 1";
                break;
            case PAST_DAY:
                query = "SELECT * FROM course " +
                        "WHERE day >= 0 " +
                        "ORDER BY day, strftime('%H:%M', startTime) ASC LIMIT 1";
                break;
        }
        return new SimpleSQLiteQuery(query);
    }
}

