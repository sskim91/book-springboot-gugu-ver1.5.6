package com.sskim;

import com.sskim.domain.Board;
import com.sskim.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void inspect() {
        //실제 객체의 클래스 이름
        Class<?> clz = boardRepository.getClass();

        System.out.println(clz.getName());
        //클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        //클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();

        System.out.println(superClasses.getName());

    }

    @Test
    public void testInsert() {

        Board board = new Board();
        board.setTitle("게시물의 제목");
        board.setContent("게시물 내용 넣기...");
        board.setWriter("user00");

        boardRepository.save(board);
    }

    @Test
    public void testRead() {
        Board board = boardRepository.findOne(1L);

        System.out.println(board);
    }

    @Test
    public void testUpdate() {

        System.out.println("Read First ....");
        Board board = boardRepository.findOne(1L);

        System.out.println("Update Title...");
        board.setTitle("수정된 제목입니다..");

        System.out.println("Call Save()...");
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {

        System.out.println("DELETE Entity..");
        boardRepository.delete(1L);
    }

    @Test
    public void 데이터_넣기() {

        for (int i = 1; i <= 200; i++) {

            Board board = new Board();
            board.setTitle("제목..." + i);
            board.setContent("내용..." + i + " 채우기");
            board.setWriter("user0" + (i % 10));
            boardRepository.save(board);
        }
    }

    @Test
    public void 제목으로_게시물찾기() {
//    before Java8
//        List<Board> list = boardRepository.findBoardByTitle("제목...177");
//
//        for(int i=0, len=list.size(); i<len; i++) {
//            System.out.println(list.get(i));
//        }

        //Java8
        boardRepository.findBoardByTitle("제목...177")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void 작성자로_검색하기() {
        Collection<Board> results = boardRepository.findByWriter("user00");

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void 제목_검색_05가포함() {
        Collection<Board> results = boardRepository.findByWriterContaining("05");
        //for 루프 대신 forEach
        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void 제목과_부등호검색() {

        Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 50L);

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void 정렬테스트() {

        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void 페이징_테스트() {

        Pageable paging = new PageRequest(0, 10);

        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);

        results.forEach(board -> System.out.println(board));
    }

    //    @Test
//    public void 페이지처리와_정렬테스트() {
//
//        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
//
//        Collection<Board> results = boardRepository.findByBnoGreaterThan(0L, paging);
//
//        results.forEach(board -> System.out.println(board));
//    }
    @Test
    public void 페이지처리와_정렬테스트_인터페이스() {

        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = boardRepository.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE : " + result.getSize());
        System.out.println("TOTAL PAGE : " + result.getTotalPages());
        System.out.println("TOTAL COUNT : " + result.getTotalElements());
        System.out.println("NEXT : " + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(board -> System.out.println(board));
    }

    @Test
    public void JPQL_테스트() {
        boardRepository.findByTitle("17")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void 필요한칼럼만_추출하는경우() {
        boardRepository.findByTitle2("17")
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void 쿼리와_페이지정렬() {

        Pageable pageable = new PageRequest(0, 10);

        boardRepository.findBypage(pageable)
                .forEach(board -> System.out.println(board));
    }

}
