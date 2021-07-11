package com.linktracker.demo.controllers;

import com.linktracker.demo.dtos.RequestDTO;
import com.linktracker.demo.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/tracker")
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/generator")
    public ResponseEntity<String> createLink(@Valid @RequestBody RequestDTO requestDTO) {
        String shortUrl = linkService.createLink(requestDTO);
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }

    @GetMapping("/redirection/{shortUrl}")
    public ResponseEntity<Void> localRedirect(@PathVariable("shortUrl") String shortUrl) {
        String url = linkService.redirigir(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

    @GetMapping("/metrics/{shortUrl}")
    public ResponseEntity<Integer> obtainMetrics(@PathVariable("shortUrl") String shortUrl) {
        Integer nRedirects = linkService.obtainMetrics(shortUrl);
        return ResponseEntity.ok().body(nRedirects);
    }

    @PostMapping("/invalidate/{shortUrl}")
    public ResponseEntity<String> invalidarLink(@PathVariable("shortUrl") String shortUrl) {
        linkService.invalidateLink(shortUrl);
        return new ResponseEntity<String>(shortUrl + "  invalidated", HttpStatus.ACCEPTED);
    }
}

