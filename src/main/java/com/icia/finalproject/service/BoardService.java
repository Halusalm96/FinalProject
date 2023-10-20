package com.icia.finalproject.service;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.BoardFileEntity;
import com.icia.finalproject.repository.BoardFileRepository;
import com.icia.finalproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardList(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> boardEntityById = boardRepository.findById(id);
        if(boardEntityById.isPresent()) {
            BoardEntity boardEntity = boardEntityById.get();
            return BoardDTO.toBoardList(boardEntity);
        }else {
            return null;
        }
    }

    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().get(0).isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSave(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            BoardEntity boardEntity = BoardEntity.toBoardEntityWithFIle(boardDTO);
            BoardEntity saveEntity = boardRepository.save(boardEntity);
            for(MultipartFile boardFIle : boardDTO.getBoardFile()) {
                String originalFilename = boardFIle.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "\\C:\\Date\\spring_boot_img\\" + storedFileName;
                boardFIle.transferTo(new File(savePath));
                BoardFileEntity boardFileEntity = BoardFileEntity.toSaveBoardFile(saveEntity, originalFilename, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }
}
