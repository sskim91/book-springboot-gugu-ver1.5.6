package com.sskim;

import com.sskim.domain.Board;
import com.sskim.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
