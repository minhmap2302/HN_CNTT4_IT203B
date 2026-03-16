package Bai6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(
                new Post(Arrays.asList("java", "backend")),
                new Post(Arrays.asList("python", "data"))
        );
        List<String> post=posts.stream().flatMap(p->p.getTags().stream()).collect(Collectors.toList());
        System.out.println(post);
    }
}
