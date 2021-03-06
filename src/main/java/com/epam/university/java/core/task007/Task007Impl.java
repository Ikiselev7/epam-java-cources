package com.epam.university.java.core.task007;

import com.epam.university.java.core.task003.NullChecker;
import com.epam.university.java.core.task003.SimpleNullChecker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/** Created by ilya on 06.09.17. */
public class Task007Impl implements Task007 {
    NullChecker checker = new SimpleNullChecker();

    @Override
    public Collection<Integer> multiplyPolynomial(
        Collection<Integer> first, Collection<Integer> second) {
        checker.check(first, second);

        Map<Integer, Integer> result = new HashMap<>();
        Integer[] integersFirst = first.toArray(new Integer[first.size()]);
        Integer[] integersSecond = second.toArray(new Integer[second.size()]);
        IntStream.range(0, first.size())
            .map(
                index -> {
                    Integer e = integersFirst[index];
                    operationWithSecond(result, integersSecond, index, e);
                    return index;
                })
            .boxed()
            .count();

        List<Integer> resList = new ArrayList<>();

        result.forEach((k, v) -> resList.add(v));

        return resList;
    }

    private void operationWithSecond(
        Map<Integer, Integer> result, Integer[] integersSecond, int index, Integer e) {
        IntStream.range(index, index + integersSecond.length)
            .map(
                ind -> {
                    Integer i = integersSecond[ind - index];
                    if (result.containsKey(ind)) {
                        result.put(ind, result.get(ind).intValue() + i * e);
                    } else {
                        result.put(ind, i * e);
                    }
                    return ind;
                })
            .count();
    }
}
