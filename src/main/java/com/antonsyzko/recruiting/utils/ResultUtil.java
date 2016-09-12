package com.antonsyzko.recruiting.utils;

import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;

import javax.persistence.Query;
import java.util.List;
/**
 * @author ihor zadyra
 */

public class ResultUtil {

    @SuppressWarnings("unchecked")
    public static <T> List<T> resultList(Query query) {
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public static <T> T result(Query query) throws SingleResultNotFoundException {
        try {
            return (T) query.getResultList().get(0);
        } catch (Exception e){
            throw new SingleResultNotFoundException("Unable to find single entity.");
        }
    }
}
