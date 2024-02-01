package org.example.movie.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.*;
import org.example.movie.app.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;
    private final ActorService actorService;
    private final GenreService genreService;
    private final EpisodeService episodeService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movieList", movieList);
        return "admin/movie/index";
    }

    // Tạo movie
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return "admin/movie/create";
    }

    // Chi tiết movie
    @GetMapping("/{id}/detail")
    public String getDetailPage(@PathVariable Integer id, Model model) {
        /*
         * Trả về movie theo id
         * Trả về danh sách tất cả diễn viên
         * Trả về danh sách tất cả đạo diễn
         * Trả về danh sách tất cả thể loại
         * Trả về danh sách tập phim của movie đó
         * */
        Movie movie = movieService.getMovieById(id);
        List<Episode> episodeList = episodeService.getEpisodeListOfMovie(id);
        List<Actor> actorList = actorService.getAllActors();
        List<Director> directorList = directorService.getAllDirectors();
        List<Genre> genreList = genreService.getAllGenres();

        model.addAttribute("movie", movie);
        model.addAttribute("episodeList", episodeList);
        model.addAttribute("actorList", actorList);
        model.addAttribute("directorList", directorList);
        model.addAttribute("genreList", genreList);
        return "admin/movie/detail";
    }
}
