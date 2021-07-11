package com.linktracker.demo.services;

import com.linktracker.demo.dtos.RequestDTO;
import org.springframework.web.servlet.view.RedirectView;

public interface LinkService {

    public String createLink(RequestDTO requestDTO);

    String redirigir(String shortUrl);

    Integer obtainMetrics(String shortUrl);

    void invalidateLink(String shortUrl);
}
