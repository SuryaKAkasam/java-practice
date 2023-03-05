package me.suryaakasam.practice.annotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class Practice {
    public static void main(String[] args) {
        Movie avatarMovie = Movie.builder()
                .id(1)
                .duration(162)
                .title("Avatar")
                .language("English")
                .genres(new HashSet<>(Arrays.asList("Action", "Adventure", "Fantasy", "Sci-Fi")))
                .actors(new HashSet<>(Arrays.asList("Sam Worthington", "Zoe Saldana", "Stephen Lang")))
                .releaseDate(LocalDate.of(2009, 12, 18))
                .description("A paraplegic Marine dispatched to the moon Pandora on a unique mission " +
                        "becomes torn between following his orders and protecting the world he feels is his home")
                .build();

        System.out.println(avatarMovie);
    }
}
