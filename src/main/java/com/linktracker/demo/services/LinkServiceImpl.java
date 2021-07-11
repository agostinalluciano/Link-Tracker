package com.linktracker.demo.services;

import com.linktracker.demo.exceptions.InvalidLinkException;
import com.linktracker.demo.exceptions.InvalidLinkIdException;
import com.linktracker.demo.converters.Base62Converter;
import com.linktracker.demo.dtos.RequestDTO;
import com.linktracker.demo.model.Link;
import com.linktracker.demo.repository.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public String createLink(RequestDTO requestDTO) {
        linkRepository.save(new Link(requestDTO.getUrl(), Base62Converter.encode(requestDTO.getUrl())));
        return Base62Converter.encode(requestDTO.getUrl());
    }

    @Override
    public String redirigir(String shortUrl) {
        Link link = obtainLink(shortUrl);
        isCurrent(link);
        link.setNRedirects(link.getNRedirects()+1);
        linkRepository.save(link);
        return link.getUrl();
    }

    private void isCurrent(Link link) {
        if (!link.isValid()){
            throw new InvalidLinkException() ;   }
    }

    @Override
    public Integer obtainMetrics(String shortUrl) {
        return obtainLink(shortUrl).getNRedirects();
    }

    @Override
    public void invalidateLink(String shortUrl) {
        Link link = obtainLink(shortUrl);
        link.setValid(false);
        linkRepository.save(link);
    }

    private Link obtainLink(String shortUrl) {
        return linkRepository.findAll().stream()
                .filter(links -> links.getShortUrl().equals(shortUrl)).findFirst().orElseThrow(InvalidLinkIdException::new);
    }


}
