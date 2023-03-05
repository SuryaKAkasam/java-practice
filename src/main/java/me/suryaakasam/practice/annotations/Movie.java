package me.suryaakasam.practice.annotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@ClassDoc(
        author = "Surya",
        date = "2023-03-02",
        version = 1,
        reviewers = {"Lalitha", "Aadhya"}
)
public class Movie {
    private long id;
    @NotNull
    private String title;
    @NotNull
    private int duration;
    private String description;
    private String language;
    private Set<String> genres;
    private Set<String> actors;
    private LocalDate releaseDate;

    public Movie() {}

    private Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.language = builder.language;
        this.genres = builder.genres;
        this.actors = builder.actors;
        this.duration = builder.duration;
        this.releaseDate = builder.releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return new StringJoiner("\n", Movie.class.getSimpleName() + " [\n", "\n]")
                .add("  id=" + id)
                .add("  title='" + title + "'")
                .add("  description='" + description + "'")
                .add("  language='" + language + "'")
                .add("  genres=" + genres)
                .add("  actors=" + actors)
                .add("  duration=" + duration)
                .add("  releaseDate=" + releaseDate)
                .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String title;
        private String description;
        private String language;
        private Set<String> genres;
        private Set<String> actors;
        private int duration;
        private LocalDate releaseDate;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder genres(HashSet<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder actors(HashSet<String> actors) {
            this.actors = actors;
            return this;
        }

        public Builder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
