package kz.alken1t.moviedatabaseprojectruntime;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterClass;
import kz.alken1t.moviedatabaseprojectruntime.dto.MovieEdit;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryActor;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryMovie;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceActor;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceDirector;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceMovie;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MovieDataBaseProjectRuntimeApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class MovieDataBaseProjectRuntimeApplicationTests {

    @Autowired
    private ServiceMovie serviceMovie;
    @Autowired
    private RepositoryMovie repositoryMovie;
    @Autowired
    private RepositoryDirector repositoryDirector;
    @Autowired
    private RepositoryActor repositoryActor;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ServiceDirector serviceDirector;
    @Autowired
    private ServiceActor serviceActor;
    @Mock
    private HttpSession httpSession;
    @Autowired
    private ModelMapper modelMapper;


//    @Autowired
//    MockMvc mockMvc;


    @Test
    void contextLoads() throws Exception {
        when(httpSession.getAttribute("startPage")).thenReturn(0);
        FilterClass filterClass  =serviceMovie.getMoviesList(null,null,null,null,null,null,null,httpSession);
        assertEquals(filterClass.getMovies().size(),repositoryMovie.findAll().subList(0,20).size(),"Проверка на размер массива");
    }


    @Test
    void testEditMovie(){
        Movie movie = new Movie("Война миров",2004,"Комедия",5.6,50);
        movie = repositoryMovie.save(movie);
        MovieEdit movieEdit = new MovieEdit(movie.getId(),"Планета обезьян",2010,"Фантастика",3.4);
        serviceMovie.editMovie(movieEdit);
        Movie movieConverter = modelMapper.map(movieEdit,Movie.class);
        Movie editMovie = repositoryMovie.findById(movieEdit.getId()).get();
        assertEquals(movieConverter,editMovie,"Проверка на изменение фильма");
        repositoryMovie.delete(editMovie);
    }


    @Test
    void  testEditWitchActorAndDirectpr(){
        Actor actorOld = new Actor("Джони","Деп");
        Actor actorNew = new Actor("Cтивен","Конг");
        Director directorOld = new Director("Брэд","Пит");
        Director directorNew = new Director("Эддисон","Бэттер");
        directorNew = repositoryDirector.save(directorNew);
        actorNew = repositoryActor.save(actorNew);
        Movie movie = new Movie("Война миров",2004,"Комедия",5.6,50);
        movie.setActors(List.of(actorOld));
        movie.setDirectors(List.of(directorOld));
        movie = repositoryMovie.save(movie);
        MovieEdit movieEdit = new MovieEdit(movie.getId(),"Планета обезьян",2010,"Фантастика",3.4);
        movieEdit.setActors(List.of(String.valueOf(actorNew.getId())));
        movieEdit.setDirectors(List.of(String.valueOf(directorNew.getId())));
        serviceMovie.editMovie(movieEdit);
        Movie editMovie = repositoryMovie.findById(movieEdit.getId()).get();
        actorNew = repositoryActor.findById(actorNew.getId()).get();
        directorNew = repositoryDirector.findById(directorNew.getId()).get();
        assertEquals(actorNew.getMovie().getId(),directorNew.getMovie().getId(),"Проверка на изменение актеров и режисера");
        repositoryMovie.delete(editMovie);
        repositoryActor.delete(actorNew);
        repositoryActor.delete(actorOld);
        repositoryDirector.delete(directorNew);
        repositoryDirector.delete(directorOld);
    }

    @Test
    public void testTop(){
        Query<List<Movie>> movies = (Query<List<Movie>>) entityManager.createQuery("select m from Movie m order by m.rating desc limit 10");
        List<Movie> movies1 = serviceMovie.findTop();
        assertEquals(movies.getResultList(),movies1,"Проверка на популярность фильмов");
    }

    @Test
    public void testDirectorPage(){
        if (httpSession.getAttribute("startPage") != null) {
            when(httpSession.getAttribute("startPage")).thenReturn(0);
        }
        List<Director> directors = serviceDirector.findAll(null,httpSession);
        assertEquals(directors.size(),serviceDirector.findAll().subList(0,20).size(),"Проверка на размер массива");
    }

    @Test
    public void testActorPage(){
        if (httpSession.getAttribute("startPage") != null) {
            when(httpSession.getAttribute("startPage")).thenReturn(0);
        }
        List<Actor> actors = serviceActor.findAll(null,httpSession);
        assertEquals(actors.size(),serviceActor.findAll().subList(0,20).size(),"Проверка на размер массива");
    }
}
