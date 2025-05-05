package com.education.youngnam.domain.member.repository.impl;

import com.education.youngnam.domain.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository  // 데이터베이스와 상호작용하고 쿼리문을 적는 용도
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final DataSource dataSource; // 데이터소스라는 객체의 커넥션

    @Override
    public int save(Member member) throws SQLException {
        //자바에서 데이터에 저장
        String sql = "INSERT INTO MEMBER (EMAIL, PASSWORD, NAME, PHONE_NUM, ADDRESS) VALUES(?, ?, ?, ?, ?)";
        // Statement는 쿼리문이 변조될 수 있기 때문에 사용하지 않음 (보안 취약)
        PreparedStatement pstmt = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = dataSource.getConnection(); // 데이터베이스 연결정보를 가져온다.
            pstmt = conn.prepareStatement(sql);  // 연결정보를 토대로 쿼리문을 변조하지 못하게 미리 컴파일한다. (PreparedStatement 객체로 만듦)
            // 이렇게 PreparedStatement로 만든 객체는, 물음표'?'를 제외한 곳은 변경되지 않는다.
            // 물음표에만 값을 동적으로 바인딩 시켜 변경할 수 있다.

            // 어떻게 물음표에 값을 할당하지? 바인딩시키지??
            // -> PreparedStatement 객체는 setter를 제공함.
            // 단, 이때 setting해주는 값은 실제 DB 컬럼의 데이터타입하고 호환되는 자바 자료형이여야 함
            // => DB마다 자바와 자료형 매핑해서 알려주는 곳들이 있음 >> MySQL Datatype map to Java
            // https://dev.mysql.com/doc/ndbapi/en/mccj-using-clusterj-mappings.html

            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhoneNum());
            pstmt.setString(5,member.getAddress());
            // 이렇게 setter를 수행하게 되면 아래처럼 쿼리문이 완성되어 있는 상태임
            // INSERT INTO (EMAIL, PASSWORD, NAME, PHONE_NUM, ADRESS) VALUES('youngnam@naver.com', '1234', '신영남', '01012341234', '서울시 마포구')";

            // 그러면 이제 해당 쿼리문을 실행시키게 해야 한다.
            // workbench나 db 툴을 이용해서는 Ctrl + Enter를 눌러서 해야 하는데, 자바도 그러면 좋겠지만 프로그래밍적으로 접근해야 한다.
            // 이를 위해 PreparedStatement는 execute 기능을 제공한다.
            // executeQuery : read
            // executeUpdate : create, update, delete
            // -> 쿼리문에 맞게 메서드를 호출해야 함

            // 조회작업을 제외한 쿼리문이 반환하는 거는, 몇 개의 row에 영향을 미쳤는지를 반환함. => 1 row(s) affected
            // 따라서 조회 제외 작업은 int형으로 결과를 받으면 된다.
            // 조회 작업은 ResultSet에 결과를 받아야 한다.
            result = pstmt.executeUpdate();
        } finally {
            pstmt.close();
            conn.close();
        }
        return result;
    }

    @Override
    public Member findByEmail(String email) throws SQLException {
        String findSql = "SELECT ID, EMAIL, PASSWORD, NAME, PHONE_NUM, ADDRESS, CREATED_AT, UPDATED_AT, TOTAL_LIKES FROM MEMBER WHERE EMAIL = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        Member foundMember = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(findSql);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                String foundEmail = rs.getString(2);
                String password = rs.getString(3);
                String foundName = rs.getString(4);
                String foundPhone = rs.getString(5);
                String foundAdress = rs.getString(6);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createdAt = LocalDateTime.parse(rs.getString(7), formatter);
                LocalDateTime uppdatedAt = rs.getString(8) != null ?LocalDateTime.parse(rs.getString(8), formatter) : null;
                int foundLikes = rs.getInt(9);
                foundMember = new Member(id, foundEmail, password, foundName, foundPhone, foundAdress, createdAt, uppdatedAt,foundLikes);

            }
        } finally {
            pstmt.close();
            conn.close();
            rs.close();
        }
        return foundMember;
    }
    @Override
    public Member LoginMember(Member member) throws SQLException {

        String sql =  "SELECT EMAIL, PASSWORD, NAME, PHONE_NUM, ADDRESS, TOTAL_LIKES " +
                      "FROM MEMBER " +
                      "WHERE EMAIL = (?) AND PASSWORD = (?)";
        PreparedStatement psmt = null;
        Connection conn = null;
        ResultSet rs = null;
        Member postMember = null;

        try{
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, member.getEmail());
            psmt.setString(2, member.getPassword());
            rs = psmt.executeQuery();
            if (rs.next()){
                String currentEmail = rs.getString(1); // 쿼리가 조회 된다면 확인을 위해 이메일을 가져오고
                String currentPassword = rs.getString(2); // 확인을 위해 비밀번호를 가져온다.
                String currentName = rs.getString(3);
                String currentPhone = rs.getString(4);
                String currentAdress = rs.getString(5);
                int currentLikes = rs.getInt(6);
                postMember = new Member(currentEmail,currentPassword,currentName,currentPhone,currentAdress,currentLikes);
            }
        }finally {
            psmt.close();
            conn.close();
            rs.close();
        }
        return postMember;
    }

}
// 결과를 받아서 리턴해서 서비스에서 사용
// 쿼리의 결과를 int형으로 받고, 이를 호출하는 쪽(service)으로 반환해주도록
// 그리고 반환받은 service쪽은 해당 값을 sysout으로 출력해보기



