package kz.alken1t.moviedatabaseprojectruntime.service;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryActor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceActor {
    private final RepositoryActor repositoryActor;

    public List<Actor> findAll() {
        return repositoryActor.findAll();
    }

    public List<Actor> findAll(String page, HttpSession httpSession) {
        List<Actor> actors = findAll();

        if (httpSession.getAttribute("startPage") == null) {
            httpSession.setAttribute("startPage", 0);
            httpSession.setAttribute("endPage", 20);
        }
        Integer startPage = (Integer) httpSession.getAttribute("startPage");
        Integer endPage = (Integer) httpSession.getAttribute("endPage");


        if (page.equals("+")) {
            if (startPage + 20 > actors.size()) {

            } else {
                startPage += 20;
                endPage += 20;
            }
        } else {
            if (startPage - 20 < 0) {

            } else {
                startPage -= 20;
                endPage -= 20;
            }
        }

        httpSession.setAttribute("startPage", startPage);
        httpSession.setAttribute("endPage", endPage);


        List<Actor> subList;
        if (startPage == 0)
            if (actors.isEmpty()) {
                subList = null;
            } else {
                subList = actors.subList(0, 20);
            }
        else {
            if (actors.size() >= endPage) {
                System.out.println(startPage);
                System.out.println(endPage);
                subList = actors.subList(startPage, endPage);
            } else {
                int temp = endPage - actors.size();
                int temp2 = endPage - temp;
                subList = actors.subList(startPage, temp2);
            }
        }


        return subList;
    }
}