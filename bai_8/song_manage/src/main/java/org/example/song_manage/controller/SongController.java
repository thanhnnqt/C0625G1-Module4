package org.example.song_manage.controller;

import jakarta.validation.Valid;
import org.example.song_manage.dto.SongDto;
import org.example.song_manage.entity.Song;
import org.example.song_manage.service.ISongService;
import org.example.song_manage.validate.SongValidate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final ISongService songService;

    public SongController(ISongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showList(@PageableDefault(page = 0, size = 2, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                           @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                           ModelMap model) {
        Page<Song> songPage = songService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("songPage", songPage);
        model.addAttribute("searchName", searchName);
        return "song/list";
    }

    @GetMapping("/add")
    public String createUsers(ModelMap model) {
        model.addAttribute("songDto", new SongDto());
        return "song/add";
    }

    @PostMapping("/add")
    public String createUser(@Valid @ModelAttribute("songDto") SongDto songDto, BindingResult bindingResult, RedirectAttributes redirect) {
        SongValidate songValidate = new SongValidate();
        songValidate.validate(songDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "song/add";
        }
        Song song = new Song();
        BeanUtils.copyProperties(songDto, song);
        String mess = "Sign up success";
        try {
            songService.addSong(song);
        } catch (Exception e) {
            mess = "Sign up fail!";
        }
        redirect.addFlashAttribute("mess", mess);
        return "redirect:/songs";
    }
}
