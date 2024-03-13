package kz.alken1t.moviedatabaseprojectruntime.service;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceDirector {
    private final RepositoryDirector repositoryDirector;

    public List<Director> findAll() {
        return repositoryDirector.findAll();
    }

    public List<Director> findAll(String page, HttpSession httpSession) {
        List<Director> directors = findAll();

        if (page == null) {
            return directors.subList(0, 20);
        }

        if (httpSession.getAttribute("startPage") == null) {
            httpSession.setAttribute("startPage", 0);
            httpSession.setAttribute("endPage", 20);
        }
        Integer startPage = (Integer) httpSession.getAttribute("startPage");
        Integer endPage = (Integer) httpSession.getAttribute("endPage");


        if (page.equals("+")) {
            if (startPage + 20 > directors.size()) {

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


        List<Director> subList;
        if (startPage == 0)
            if (directors.isEmpty()) {
                subList = null;
            } else {
                subList = directors.subList(0, 20);
            }
        else {
            if (directors.size() >= endPage) {
                subList = directors.subList(startPage, endPage);
            } else {
                int temp = endPage - directors.size();
                int temp2 = endPage - temp;
                subList = directors.subList(startPage, temp2);
            }
        }


        return subList;
    }
}