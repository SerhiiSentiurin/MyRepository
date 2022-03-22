package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.security.Key;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {
    public int sum(IntStream intStream) {
        return intStream.sum();

    }

    public int production(IntStream intStream) {
        return intStream
                .reduce((x1, x2) -> x1 * x2)
                .stream()
                .sum();
        //limit.reduce((i1,i2)->i1*i2).getAsInt();
    }

    public int oddSum(IntStream intStream) {
        return intStream
                .filter(x -> x % 2 != 0)
                .sum();
    }

    public Map<Integer, Integer> sumByRemainder(int i, IntStream intStream) {
        return intStream
                .boxed()
                .collect(Collectors.groupingBy(s -> s % i, Collectors.summingInt(Integer::intValue)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> programmingResults) {
        Set<CourseResult> set = programmingResults.collect(Collectors.toSet());
        Set<String> keys = new HashSet<>();

        CourseResult[] arr = set.toArray(CourseResult[]::new);

        for (CourseResult c : arr) {
            keys.addAll(c.getTaskResults().keySet());
        }

        return set.stream()
                .collect(Collectors.groupingBy(CourseResult::getPerson, Collectors.summingDouble(x -> x.getTaskResults().values().stream().mapToDouble(i -> i).sum() / keys.size())));
    }

    public double averageTotalScore(Stream<CourseResult> programmingResults) {
        return totalScores(programmingResults).values().stream().mapToDouble(x -> x).average().getAsDouble();

    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> programmingResults) {
//        Set<CourseResult> set = programmingResults.collect(Collectors.toSet());
//        Set<String> subjects = new HashSet<>();
//        Map<String, Double> vozvrat = new HashMap<>();
//        CourseResult[] marks = set.toArray(CourseResult[]::new);
//        for (CourseResult c : marks) {
//            subjects.addAll(c.getTaskResults().keySet());
//        }
//        for (String subject : subjects) {
//            vozvrat.put(subject, Arrays.stream(marks)
//                    .map(mark -> mark.getTaskResults().get(subject))
//                    .filter(Objects::nonNull)
//                    .mapToDouble(d -> d)
//                    .sum() / marks.length);
//        }

//        return vozvrat;


        return programmingResults
                .map(courseResult -> averageMap(courseResult.getTaskResults()))
                .findFirst().orElseThrow(RuntimeException::new);
    }
////////////////////////////
//    private Map<String, Double> averageMap(Map<String, Integer> result) {
//        Map<String, Double> res = new HashMap<>();
//        Set<Map.Entry<String, Integer>> entries = result.entrySet();
//        Set<String> keys = new HashSet<>();
//        Set<Double> values = new HashSet<>();
//        double sumMarks = 0;
//        int counter = 0;
//        for (Map.Entry<String, Integer> enter : entries) {
//            counter++;
//
//            if (result.containsKey(enter.getKey())) {
//                res.put(enter.getKey(), enter.getValue().doubleValue());
//                sumMarks= enter.getValue().doubleValue()+sumMarks;
//                keys.add(enter.getKey());
//                values.add(enter.getValue().doubleValue());
//
//            } else {
//                res.put(enter.getKey(),enter.getValue().doubleValue());
//                sumMarks= enter.getValue().doubleValue()+sumMarks;
//                keys.add(enter.getKey());
//                values.add(enter.getValue().doubleValue());
//            }
//        }
//        res.values().stream().forEach(System.out::println);
//        System.out.println(sumMarks);

/////////////////

    //res.values().stream().forEach(System.out::println);

    //values.stream().forEach(System.out::println);

    private Map<String, Double> averageMap(Map<String, Integer> result) {
        Map<String, Double> res = new HashMap<>();
        Set<Map.Entry<String, Integer>> entries = result.entrySet();
        //  Set<String> keys = new HashSet<>();

        for (Map.Entry<String, Integer> entry : entries) {
            String subject = entry.getKey();
            Integer mark = entry.getValue();
            // keys.add(result.keySet().toString());
            if (res.containsKey(subject)) {
                Double currentMark = res.get(subject);
                res.put(subject, currentMark + mark.doubleValue());
            } else {
                res.put(subject, mark.doubleValue());
            }
        }
//        res.forEach((key, value) -> {
//            double averageValue = value / entries.size();
//            res.put(key, averageValue);
//        });
        return res;
    }
//        res.put(s, entries.stream().map(x -> x.getValue())
//                    .filter(Objects::nonNull).mapToDouble(d -> d).sum() / counter);

//        for (String s : keys) {
//            res.put(s, entries.stream().map(x -> x.getValue())
//                    .filter(Objects::nonNull).mapToDouble(d -> d).sum() / counter);
//        }
    //res.values().stream().mapToDouble(x->x).sum()/counter


    public Map<Person, String> defineMarks(Stream<CourseResult> programmingResults) {
        return null;

    }

    public String easiestTask(Stream<CourseResult> programmingResults) {
        return null;

    }

    public Collector printableStringCollector() {
        return null;

    }
}
