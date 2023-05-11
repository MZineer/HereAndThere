package com.mz.hat.controller;

import com.mz.hat.service.BoardService;
import com.mz.hat.service.RegionService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.BoardVo;
import com.mz.hat.vo.RegionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@MSP
@Slf4j
@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private RegionService regionService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("pages/board/list");
    }

    @GetMapping("/list/get")
    public ResponseEntity<MspResult> get_list() {
        MspResult mspResult;

        List<BoardVo> boardVos = boardService.list();

        int postVosSize = boardVos.size();

        if(postVosSize > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, boardVos);
        } else {
            mspResult = MspUtil.makeResult("8888", "등록된 게시글이 없습니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @GetMapping("/write")
    public ModelAndView get_write(Model model) {
        List<RegionVo> regionVos = regionService.list();
        model.addAttribute("regions", regionVos);
        return new ModelAndView("pages/board/write");
    }

    @PostMapping("/write")
    public ResponseEntity<MspResult> post_write(@RequestBody BoardVo boardVo, @RequestPart(value = "file", required = false) List<MultipartFile> img) {
        MspResult mspResult;

        int affectRow = boardService.write(boardVo);

        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, boardVo);
        } else {
            mspResult = MspUtil.makeResult("9999", "중복된 글입니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MspResult> post_modify(@PathVariable("id") String id ,@RequestBody BoardVo boardVo) {
        MspResult mspResult;

        boardVo.setId(Integer.parseInt(id));
        int affectRow = boardService.modify(boardVo);
        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, boardVo);
        } else {
            mspResult = MspUtil.makeResult("4444", "존재하지 않는 글입니다.", boardVo);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MspResult> del_delete(@PathVariable("id") String id) {
        MspResult mspResult;

        BoardVo vo = new BoardVo();
        vo.setId(Integer.parseInt(id));
        int affectRow = boardService.delete(vo);

        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, vo);
        } else {
            mspResult = MspUtil.makeResult("4444", "존재하지 않는 글입니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }
}
