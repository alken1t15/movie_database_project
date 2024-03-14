package kz.alken1t.moviedatabaseprojectruntime.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCommon {
    public <T> List findAll(String page,String nameParam ,HttpSession httpSession, JpaRepository jpaRepository,T t) {
        int numberPage;
        if (httpSession.getAttribute(nameParam) == null){
            httpSession.setAttribute(nameParam,0);
        }
        numberPage = (int) httpSession.getAttribute(nameParam);
        if (page==null){
            return jpaRepository.findAll(PageRequest.of(0,20)).toList();
        }
        else if (page.equals("+")){
            if (jpaRepository.findAll(PageRequest.of(numberPage+1,20)).isEmpty()){
                return jpaRepository.findAll(PageRequest.of(numberPage,20)).toList();
            }
            else {
                httpSession.setAttribute(nameParam,numberPage+1);
                return jpaRepository.findAll(PageRequest.of(numberPage+1,20)).toList();
            }
        }else  {
            if (numberPage-1<0){
                return jpaRepository.findAll(PageRequest.of(numberPage,20)).toList();
            }
            else {
                httpSession.setAttribute(nameParam,numberPage-1);
                return jpaRepository.findAll(PageRequest.of(numberPage-1,20)).toList();
            }
        }
    }
}