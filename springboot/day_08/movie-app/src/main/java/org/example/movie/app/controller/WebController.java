package org.example.movie.app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Episode;
import org.example.movie.app.entity.Movie;
import org.example.movie.app.entity.Review;
import org.example.movie.app.entity.User;
import org.example.movie.app.model.enums.MovieType;
import org.example.movie.app.service.EpisodeService;
import org.example.movie.app.service.ReviewService;
import org.example.movie.app.service.WebService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;
    private final ReviewService reviewService;
    private final HttpSession session;
    private final EpisodeService episodeService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        Page<Movie> pageDataPhimHot = webService.getHotMovies(true, 1, 8);
        Page<Movie> pageDataPhimLe = webService.getMoviesByType(MovieType.PHIM_LE, true, 1, 6);
        Page<Movie> pageDataPhimBo = webService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6);
        Page<Movie> pageDataPhimChieuRap = webService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6);

        model.addAttribute("listPhimHot", pageDataPhimHot.getContent());
        model.addAttribute("listPhimLe", pageDataPhimLe.getContent());
        model.addAttribute("listPhimBo", pageDataPhimBo.getContent());
        model.addAttribute("listPhimChieuRap", pageDataPhimChieuRap.getContent());
        return "web/index";
    }

    // phim-le?page=1&size=12 -> page = 1, size = 12
    // phim-le -> page = 1, size = 12
    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = webService.getMoviesByType(MovieType.PHIM_LE, true, page, size);
        model.addAttribute("pageData", pageData); // Hiển thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại
        return "web/phim-le";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = webService.getMoviesByType(MovieType.PHIM_BO, true, page, size);
        model.addAttribute("pageData", pageData); // Hiển thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại
        return "web/phim-bo";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                      @RequestParam(required = false, defaultValue = "1") Integer page,
                                      @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = webService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, size);
        model.addAttribute("pageData", pageData); // Hiển thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getPhimDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug) {
        Movie movie = webService.getMovie(id, slug, true);
        List<Movie> relatedMovies = webService.getRelatedMovies(id, movie.getType(), true, 6);
        List<Review> reviews = reviewService.getReviewsOfMovie(id);
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getXemPhimPage(Model model,
                                 @PathVariable Integer id,
                                 @PathVariable String slug,
                                 @RequestParam String tap) {
        Movie movie = webService.getMovie(id, slug, true);
        List<Movie> relatedMovies = webService.getRelatedMovies(id, movie.getType(), true, 6);
        List<Review> reviews = reviewService.getReviewsOfMovie(id);
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);
        Episode currentEpisode = episodeService.getEpisode(id, tap, true);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        model.addAttribute("currentEpisode", currentEpisode);
        return "web/xem-phim";
    }

    @GetMapping("/dang-ky")
    public String getDangKyPage() {
        User user = (User) session.getAttribute("currentUser"); // Lấy thông tin người dùng trong session
        if (user != null) { // Nếu đăng nhập thì chuyển hướng về trang chủ
            return "redirect:/";
        }
        return "web/dang-ky"; // Nếu chưa đăng nhập thì hiển thị trang đăng ký
    }

    @GetMapping("/dang-nhap")
    public String getDangNhapPage() {
        User user = (User) session.getAttribute("currentUser"); // Lấy thông tin người dùng trong session
        if (user != null) { // Nếu đăng nhập thì chuyển hướng về trang chủ
            return "redirect:/";
        }
        return "web/dang-nhap"; // Nếu chưa đăng nhập thì hiển thị trang đăng nhập
    }
}
