package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Main {
    private final static String[] names = {"Johnny", "Betty", "Ragnar", "Umberto"};
    private final static String[] lastNames = {"Silverhand", "Paige", "Lodbrok", "Eco"};
    private final static String[] programTasks = {"Lab 1. Figures", "Lab 2. War and Peace", "Lab 3. File Tree"};
    private final static String[] practicalHistoryTasks = {"Shieldwalling", "Phalanxing", "Wedging", "Tercioing"};

    public static void main(String[] args) {
        Collecting cal = new Collecting();
        System.out.println(cal.averageScoresPerTask(historyResults(new Random(853))));
    }

    private static Stream<CourseResult> historyResults(final Random random) {
        int n = random.nextInt(names.length);
        int l = random.nextInt(lastNames.length);
        AtomicInteger t = new AtomicInteger(practicalHistoryTasks.length);

        return IntStream.iterate(0, i -> i + 1)
                .limit(3)
                .mapToObj(i -> new Person(
                        names[(n + i) % names.length],
                        lastNames[(l + i) % lastNames.length],
                        18 + random.nextInt(20)))
                .map(p -> new CourseResult(p,
                        IntStream.iterate(t.getAndIncrement(), i -> t.getAndIncrement())
                                .map(i -> i % practicalHistoryTasks.length)
                                .mapToObj(i -> practicalHistoryTasks[i])
                                .limit(3)
                                .collect(toMap(
                                        task -> task,
                                        task -> random.nextInt(51) + 50))));
    }


    private static Stream<CourseResult> programmingResults(final Random random) {
        int n = random.nextInt(names.length);
        int l = random.nextInt(lastNames.length);

        return IntStream.iterate(0, i -> i + 1)
                .limit(3)
                .mapToObj(i -> new Person(
                        names[(n + i) % names.length],
                        lastNames[(l + i) % lastNames.length],
                        18 + random.nextInt(20)))
                .map(p -> new CourseResult(p, Arrays.stream(programTasks).collect(toMap(
                        task -> task,
                        task -> random.nextInt(51) + 50))));
    }
}


