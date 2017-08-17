package com.sskim.persistence;

import com.sskim.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QueryDslPredicateExecutor<Board>{

    //제목 찾기
    public List<Board> findBoardByTitle(String title);

    //작성자 찾기
    public Collection<Board> findByWriter(String writer);

    //작성자에 대한 like % 키워드 %
    public Collection<Board> findByWriterContaining(String writer);

    //OR 조건의 처리
    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    //title Like % ? % and bno > ?
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    //bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    //bno > ? ORDER BY bno DESC limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    //public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    //인터페이스에 페이징, 정렬 처리
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    //@Query 작성 - 제목에 대한 검색 처리
    @Query("SELECT b FROM  Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitle(String title);

    //내용에 대한 검색 처리 @Param
    @Query("SELECT b FROM  Board b WHERE b.title LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByContent(@Param("content") String content);

    //작성자에 대한 검색 처리 - #{entityName}
    //@Query("SELECT b FROM #{entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    //public List<Board> findByWriter2(String writer);

    @Query("SELECT b.bno, b.title, b.writer, b.regdate " + " FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Object[]> findByTitle2(String title);

    //nativeQuery 사용
    @Query(value = "SELECT bno, title, writer FROM tbl_boards WHERE title " +
            "LIKE CONCAT('%' ?1, '%') AND bno > 0 ORDER BY bno DESC", nativeQuery = true)
    public List<Object[]> findByTitle3(String title);

    //@Query와 Pagigng 처리/정렬
    @Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findBypage(Pageable pageable);

}
